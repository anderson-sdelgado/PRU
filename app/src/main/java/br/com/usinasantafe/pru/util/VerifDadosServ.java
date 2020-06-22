package br.com.usinasantafe.pru.util;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import br.com.usinasantafe.pru.MenuInicialActivity;
import br.com.usinasantafe.pru.control.ConfigCTR;
import br.com.usinasantafe.pru.model.bean.AtualAplicBean;
import br.com.usinasantafe.pru.util.connHttp.PostVerGenerico;
import br.com.usinasantafe.pru.util.connHttp.UrlsConexaoHttp;
import br.com.usinasantafe.pru.model.pst.GenericRecordable;

/**
 * Created by anderson on 16/11/2015.
 */
public class VerifDadosServ {

    private static VerifDadosServ instance = null;
    private GenericRecordable genericRecordable;
    private UrlsConexaoHttp urlsConexaoHttp;
    private Context telaAtual;
    private Class telaProx;
    private String variavel;
    private ProgressDialog progressDialog;
    private String dado;
    private String tipo;
    private br.com.usinasantafe.pru.to.tb.variaveis.AtualizaBean atualizaBean;
    private MenuInicialActivity menuInicialActivity;
    private boolean verTerm;
    private PostVerGenerico postVerGenerico;

    public VerifDadosServ() {
        //genericRecordable = new GenericRecordable();
    }

    public static VerifDadosServ getInstance() {
        if (instance == null)
            instance = new VerifDadosServ();
        return instance;
    }

    public void manipularDadosHttp(String result) {

        if (!result.equals("")) {
            retornoVerifNormal(result);
        }

    }

    public String manipLocalClasse(String classe) {
        if (classe.contains("TO")) {
            classe = urlsConexaoHttp.localPSTEstatica + classe;
        }
        return classe;
    }

    public void verDados(String dado, String tipo, Context telaAtual, Class telaProx, ProgressDialog progressDialog) {

        verTerm = false;
        urlsConexaoHttp = new UrlsConexaoHttp();
        this.telaAtual = telaAtual;
        this.telaProx = telaProx;
        this.progressDialog = progressDialog;
        this.dado = dado;
        this.tipo = tipo;

        envioDados();

    }

    public void verDados(String dado, String tipo, Context telaAtual, Class telaProx, String variavel) {

        urlsConexaoHttp = new UrlsConexaoHttp();
        this.telaAtual = telaAtual;
        this.telaProx = telaProx;
        this.variavel = variavel;
        this.dado = dado;
        this.tipo = tipo;

        envioDados();

    }

    public void verAtualizacao(br.com.usinasantafe.pru.to.tb.variaveis.AtualizaBean atualizaBean, MenuInicialActivity menuInicialActivity, ProgressDialog progressDialog) {

        urlsConexaoHttp = new UrlsConexaoHttp();
        this.atualizaBean = atualizaBean;
        this.progressDialog = progressDialog;
        this.tipo = "Atualiza";
        this.menuInicialActivity = menuInicialActivity;

        envioAtualizacao();

    }

    public void envioDados() {

        String[] url = {urlsConexaoHttp.urlVerifica(tipo)};
        Map<String, Object> parametrosPost = new HashMap<String, Object>();
        parametrosPost.put("dado", String.valueOf(dado));

        postVerGenerico = new PostVerGenerico();
        postVerGenerico.setParametrosPost(parametrosPost);
        postVerGenerico.execute(url);

    }


    public void retornoVerifNormal(String result) {

        try {

            if(this.tipo.equals("OS")) {

                int posicao = result.indexOf("_") + 1;
                String objPrinc = result.substring(0, result.indexOf("_"));
                String objSeg = result.substring(posicao, result.length());

                JSONObject jObj = new JSONObject(objPrinc);
                JSONArray jsonArray = jObj.getJSONArray("dados");
                Class classe = Class.forName(urlsConexaoHttp.localPSTEstatica + "OSTO");

                if (jsonArray.length() > 0) {

                    genericRecordable = new GenericRecordable();
                    genericRecordable.deleteAll(classe);

                    for (int i = 0; i < jsonArray.length(); i++) {

                        JSONObject objeto = jsonArray.getJSONObject(i);
                        Gson gson = new Gson();
                        genericRecordable.insert(gson.fromJson(objeto.toString(), classe), classe);

                    }

                    jObj = new JSONObject(objSeg);
                    jsonArray = jObj.getJSONArray("dados");
                    classe = Class.forName(urlsConexaoHttp.localPSTEstatica + "ROSAtivTO");

                    genericRecordable.deleteAll(classe);

                    for (int j = 0; j < jsonArray.length(); j++) {

                        JSONObject objeto = jsonArray.getJSONObject(j);
                        Gson gson = new Gson();
                        genericRecordable.insert(gson.fromJson(objeto.toString(), classe), classe);

                    }

                    if(!verTerm) {

                        verTerm = true;
                        this.progressDialog.dismiss();

                        Intent it = new Intent(telaAtual, telaProx);
                        telaAtual.startActivity(it);
                    }

                } else {

                    if(!verTerm) {

                        verTerm = true;
                        this.progressDialog.dismiss();

                        AlertDialog.Builder alerta = new AlertDialog.Builder(telaAtual);
                        alerta.setTitle("ATENÇÃO");
                        alerta.setMessage("OS INEXISTENTE NA BASE DE DADOS! FAVOR VERIFICA A NUMERAÇÃO.");

                        alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // TODO Auto-generated method stub

                            }
                        });
                        alerta.show();

                    }

                }

            }
            else if(this.tipo.equals("Atualiza")) {

                String verAtualizacao = result.trim();

                if(verAtualizacao.equals("S")){

                    AtualizarAplicativo atualizarAplicativo = new AtualizarAplicativo();
                    atualizarAplicativo.setContext(this.menuInicialActivity);
                    atualizarAplicativo.execute();

                }
                else{

                    this.progressDialog.dismiss();
                    this.menuInicialActivity.startTimer();

                }

            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            Log.i("PMM", "Erro Manip = " + e);
        }

    }

    public void verAtualAplic(String versaoAplic, MenuInicialActivity menuInicialActivity, ProgressDialog progressDialog) {

        AtualAplicBean atualAplicBean = new AtualAplicBean();
        ConfigCTR configCTR = new ConfigCTR();
        atualAplicBean.setIdCelularAtual(configCTR.getConfig().getNumLinhaConfig());
        atualAplicBean.setVersaoAtual(versaoAplic);

        urlsConexaoHttp = new UrlsConexaoHttp();
        this.progressDialog = progressDialog;
        this.tipo = "Atualiza";
        this.menuInicialActivity = menuInicialActivity;

        JsonArray jsonArray = new JsonArray();

        Gson gson = new Gson();
        jsonArray.add(gson.toJsonTree(atualAplicBean, atualAplicBean.getClass()));

        JsonObject json = new JsonObject();
        json.add("dados", jsonArray);

        Log.i("PMM", "LISTA = " + json.toString());

        String[] url = {urlsConexaoHttp.urlVerifica(tipo)};
        Map<String, Object> parametrosPost = new HashMap<String, Object>();
        parametrosPost.put("dado", json.toString());

        postVerGenerico = new PostVerGenerico();
        postVerGenerico.setParametrosPost(parametrosPost);
        postVerGenerico.execute(url);

    }

//    public void envioAtualizacao(){
//
//        JsonArray jsonArray = new JsonArray();
//
//        Gson gson = new Gson();
//        jsonArray.add(gson.toJsonTree(atualizaBean, atualizaBean.getClass()));
//
//        JsonObject json = new JsonObject();
//        json.add("dados", jsonArray);
//
//        Log.i("PCOMP", "LISTA = " + json.toString());
//
//        String[] url = {urlsConexaoHttp.urlVerifica(tipo)};
//        Map<String, Object> parametrosPost = new HashMap<String, Object>();
//        parametrosPost.put("dado", json.toString());
//
//        PostVerGenerico postVerGenerico = new PostVerGenerico();
//        postVerGenerico.setParametrosPost(parametrosPost);
//        postVerGenerico.execute(url);
//
//    }

    public void cancelVer(){
        verTerm = true;
        if(postVerGenerico.getStatus() == AsyncTask.Status.RUNNING){
            postVerGenerico.cancel(true);
        }
    }

    public boolean isVerTerm() {
        return verTerm;
    }

}
