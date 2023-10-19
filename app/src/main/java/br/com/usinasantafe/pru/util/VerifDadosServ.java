package br.com.usinasantafe.pru.util;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import java.util.HashMap;
import java.util.Map;

import br.com.usinasantafe.pru.control.ConfigCTR;
import br.com.usinasantafe.pru.model.dao.OSDAO;
import br.com.usinasantafe.pru.util.connHttp.PostVerGenerico;
import br.com.usinasantafe.pru.util.connHttp.UrlsConexaoHttp;

/**
 * Created by anderson on 16/11/2015.
 */
public class VerifDadosServ {

    private static VerifDadosServ instance = null;
    private UrlsConexaoHttp urlsConexaoHttp;
    private Context telaAtual;
    private Class telaProx;
    private ProgressDialog progressDialog;
    private String dado;
    private String classe;
    private PostVerGenerico postVerGenerico;
    public static int status;

    public static VerifDadosServ getInstance() {
        if (instance == null)
            instance = new VerifDadosServ();
        return instance;
    }

    public void manipularDadosHttp(String result) {

        if (!result.equals("")) {
            if (this.classe.equals("OS")) {
                OSDAO osDAO = new OSDAO();
                osDAO.recDadosOS(result.trim());
            } else if (this.classe.equals("Token")) {
                ConfigCTR configCTR = new ConfigCTR();
                configCTR.recToken(result.trim(), telaAtual, progressDialog);
            }
        }

    }

    public void verDados(String dado, String tipo, Context telaAtual, Class telaProx, ProgressDialog progressDialog) {

        urlsConexaoHttp = new UrlsConexaoHttp();
        this.telaAtual = telaAtual;
        this.telaProx = telaProx;
        this.progressDialog = progressDialog;
        this.dado = dado;
        this.classe = tipo;

        envioVerif();

    }

    public void verifDados(String dado, Context telaAtual, ProgressDialog progressDialog) {

        this.urlsConexaoHttp = new UrlsConexaoHttp();
        this.telaAtual = telaAtual;
        this.progressDialog = progressDialog;
        this.classe = "Token";
        this.dado = dado;

        envioVerif();

    }

    public void envioVerif() {

        status = 2;
        String[] url = {urlsConexaoHttp.urlVerifica(classe)};
        Map<String, Object> parametrosPost = new HashMap<>();
        parametrosPost.put("dado", String.valueOf(dado));

        postVerGenerico = new PostVerGenerico();
        postVerGenerico.setParametrosPost(parametrosPost);
        postVerGenerico.execute(url);

    }

    public void cancel() {
        status = 3;
        if (postVerGenerico.getStatus() == AsyncTask.Status.RUNNING) {
            postVerGenerico.cancel(true);
        }
    }

    public void pulaTelaSemTerm(){
        this.progressDialog.dismiss();
        Intent it = new Intent(telaAtual, telaProx);
        telaAtual.startActivity(it);
    }

    public void msgSemTerm(String texto){
        this.progressDialog.dismiss();
        AlertDialog.Builder alerta = new AlertDialog.Builder(telaAtual);
        alerta.setTitle("ATENÇÃO");
        alerta.setMessage(texto);
        alerta.setPositiveButton("OK", (dialog, which) -> {
        });
        alerta.show();
    }

    public void pulaTelaComTerm(){
        if(status < 3){
            status = 3;
            this.progressDialog.dismiss();
            Intent it = new Intent(telaAtual, telaProx);
            telaAtual.startActivity(it);
        }
    }

    public void msgComTerm(String texto){
        if(status < 3){
            status = 3;
            this.progressDialog.dismiss();
            AlertDialog.Builder alerta = new AlertDialog.Builder(telaAtual);
            alerta.setTitle("ATENÇÃO");
            alerta.setMessage(texto);
            alerta.setPositiveButton("OK", (dialog, which) -> {
            });
            alerta.show();
        }
    }

}
