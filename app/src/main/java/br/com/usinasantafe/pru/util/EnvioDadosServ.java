package br.com.usinasantafe.pru.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import android.content.Context;
import android.util.Log;

import br.com.usinasantafe.pru.util.connHttp.PostCadGenerico;
import br.com.usinasantafe.pru.util.connHttp.UrlsConexaoHttp;
import br.com.usinasantafe.pru.model.pst.EspecificaPesquisa;

public class EnvioDadosServ {

    private static EnvioDadosServ instance = null;
    private UrlsConexaoHttp urlsConexaoHttp;
    private List listDatasFrenteTO;

    private int statusEnvio; //1 - Enviando; 2 - Existe Dados para Enviar; 3 - Todos os Dados Enviados
    private boolean enviando = false;

    public EnvioDadosServ() {
        // TODO Auto-generated constructor stub
        urlsConexaoHttp = new UrlsConexaoHttp();
    }

    public static EnvioDadosServ getInstance() {
        if (instance == null) {
            instance = new EnvioDadosServ();
        }
        return instance;
    }


    public void salvaBoletimAberto(br.com.usinasantafe.pru.to.tb.variaveis.BoletimBean boletimBean) {

        br.com.usinasantafe.pru.to.tb.variaveis.ConfigBean configBean = new br.com.usinasantafe.pru.to.tb.variaveis.ConfigBean();
        List configList = configBean.all();
        configBean = (br.com.usinasantafe.pru.to.tb.variaveis.ConfigBean) configList.get(0);
        configList.clear();

        boletimBean.setIdExtBoletim(0L);
        boletimBean.setIdTurmaBoletim(configBean.getIdTurma());
        boletimBean.setDthrInicioBoletim(Tempo.getInstance().data());
        boletimBean.setStatusBoletim(1L);
        boletimBean.insert();

    }

    public void salvaFuncBoletim(Long lider, Long tipo) {

        br.com.usinasantafe.pru.to.tb.variaveis.BoletimBean boletimBean = new br.com.usinasantafe.pru.to.tb.variaveis.BoletimBean();
        List boletimList = boletimBean.get("statusBoletim", 1L);
        boletimBean = (br.com.usinasantafe.pru.to.tb.variaveis.BoletimBean) boletimList.get(0);
        boletimList.clear();

        br.com.usinasantafe.pru.to.tb.variaveis.AlocaFuncBean alocaFuncBean = new br.com.usinasantafe.pru.to.tb.variaveis.AlocaFuncBean();
        alocaFuncBean.setIdBolAlocaFunc(boletimBean.getIdBoletim());
        alocaFuncBean.setIdExtBolAlocaFunc(boletimBean.getIdExtBoletim());
        alocaFuncBean.setCodFuncionarioAlocaFunc(lider);
        alocaFuncBean.setDthrAlocaFunc(Tempo.getInstance().data());
        alocaFuncBean.setTipoAlocaFunc(tipo);
        alocaFuncBean.insert();

    }


    public void salvaAponta(br.com.usinasantafe.pru.to.tb.variaveis.ApontBean apontaPrincTO, ArrayList<Long> funcSel) {

        br.com.usinasantafe.pru.to.tb.variaveis.BoletimBean boletimBean = new br.com.usinasantafe.pru.to.tb.variaveis.BoletimBean();
        List lBol = boletimBean.get("statusBoletim", 1L);
        boletimBean = (br.com.usinasantafe.pru.to.tb.variaveis.BoletimBean) lBol.get(0);

        for (int i = 0; i < funcSel.size(); i++) {
            br.com.usinasantafe.pru.to.tb.variaveis.ApontBean apontBean = new br.com.usinasantafe.pru.to.tb.variaveis.ApontBean();
            apontBean.setIdBolAponta(boletimBean.getIdBoletim());
            apontBean.setIdExtBolAponta(boletimBean.getIdExtBoletim());
            apontBean.setOsAponta(apontaPrincTO.getOsAponta());
            apontBean.setAtivAponta(apontaPrincTO.getAtivAponta());
            apontBean.setParadaAponta(apontaPrincTO.getParadaAponta());
            apontBean.setDthrAponta(Tempo.getInstance().data());
            apontBean.setFuncAponta(funcSel.get(i));
            apontBean.insert();
        }

        br.com.usinasantafe.pru.to.tb.variaveis.ConfigBean configBean = new br.com.usinasantafe.pru.to.tb.variaveis.ConfigBean();
        List configList = configBean.all();
        configBean = (br.com.usinasantafe.pru.to.tb.variaveis.ConfigBean) configList.get(0);
        configBean.setDtUltApontConfig(Tempo.getInstance().data());
        configBean.update();

        envioDadosPrinc();

    }

    public void salvaAponta(br.com.usinasantafe.pru.to.tb.variaveis.ApontBean apontaPrincTO, Long funcSel) {

        br.com.usinasantafe.pru.to.tb.variaveis.BoletimBean boletimBean = new br.com.usinasantafe.pru.to.tb.variaveis.BoletimBean();
        List lBol = boletimBean.get("statusBoletim", 1L);
        boletimBean = (br.com.usinasantafe.pru.to.tb.variaveis.BoletimBean) lBol.get(0);

        br.com.usinasantafe.pru.to.tb.variaveis.ApontBean apontBean = new br.com.usinasantafe.pru.to.tb.variaveis.ApontBean();
        apontBean.setIdBolAponta(boletimBean.getIdBoletim());
        apontBean.setIdExtBolAponta(boletimBean.getIdExtBoletim());
        apontBean.setOsAponta(apontaPrincTO.getOsAponta());
        apontBean.setAtivAponta(apontaPrincTO.getAtivAponta());
        apontBean.setParadaAponta(apontaPrincTO.getParadaAponta());
        apontBean.setDthrAponta(Tempo.getInstance().data());
        apontBean.setFuncAponta(funcSel);
        apontBean.insert();

        br.com.usinasantafe.pru.to.tb.variaveis.ConfigBean configBean = new br.com.usinasantafe.pru.to.tb.variaveis.ConfigBean();
        List configList = configBean.all();
        configBean = (br.com.usinasantafe.pru.to.tb.variaveis.ConfigBean) configList.get(0);
        configBean.setDtUltApontConfig(Tempo.getInstance().data());
        configBean.update();

        envioDadosPrinc();

    }


    public void salvaBoletimFechado() {

        br.com.usinasantafe.pru.to.tb.variaveis.BoletimBean boletimBean = new br.com.usinasantafe.pru.to.tb.variaveis.BoletimBean();
        List listBoletim = boletimBean.get("statusBoletim", 1L);
        boletimBean = (br.com.usinasantafe.pru.to.tb.variaveis.BoletimBean) listBoletim.get(0);
        listBoletim.clear();

        boletimBean.setDthrFimBoletim(Tempo.getInstance().data());
        boletimBean.setStatusBoletim(2L);
        boletimBean.update();

        envioDadosPrinc();

    }

    public void delBolFechado() {

        br.com.usinasantafe.pru.to.tb.variaveis.BoletimBean boletimBean = new br.com.usinasantafe.pru.to.tb.variaveis.BoletimBean();
        List listBoletim = boletimBean.get("statusBoletim", 2L);
        ArrayList<Long> rLista = new ArrayList<Long>();

        for (int i = 0; i < listBoletim.size(); i++) {
            boletimBean = (br.com.usinasantafe.pru.to.tb.variaveis.BoletimBean) listBoletim.get(i);
            rLista.add(boletimBean.getIdBoletim());
        }

        br.com.usinasantafe.pru.to.tb.variaveis.ApontBean apontaMMTO = new br.com.usinasantafe.pru.to.tb.variaveis.ApontBean();
        List apontaList = apontaMMTO.in("idBolAponta", rLista);

        for (int j = 0; j < apontaList.size(); j++) {
            apontaMMTO = (br.com.usinasantafe.pru.to.tb.variaveis.ApontBean) apontaList.get(j);
            apontaMMTO.delete();
        }

        br.com.usinasantafe.pru.to.tb.variaveis.AlocaFuncBean alocaFuncBean = new br.com.usinasantafe.pru.to.tb.variaveis.AlocaFuncBean();
        List alocaFuncList = alocaFuncBean.in("idBolAlocaFunc", rLista);

        for (int j = 0; j < alocaFuncList.size(); j++) {
            alocaFuncBean = (br.com.usinasantafe.pru.to.tb.variaveis.AlocaFuncBean) alocaFuncList.get(j);
            alocaFuncBean.delete();
        }

        for (int i = 0; i < listBoletim.size(); i++) {
            boletimBean = (br.com.usinasantafe.pru.to.tb.variaveis.BoletimBean) listBoletim.get(i);
            boletimBean.delete();
        }

    }

    public void delApontaMM() {

        br.com.usinasantafe.pru.to.tb.variaveis.ApontBean apontBean = new br.com.usinasantafe.pru.to.tb.variaveis.ApontBean();
        apontBean.deleteAll();

        br.com.usinasantafe.pru.to.tb.variaveis.AlocaFuncBean alocaFuncBean = new br.com.usinasantafe.pru.to.tb.variaveis.AlocaFuncBean();
        List alocaFuncList = alocaFuncBean.dif("idExtBolAlocaFunc", 0);

        for (int i = 0; i < alocaFuncList.size(); i++) {
            alocaFuncBean = (br.com.usinasantafe.pru.to.tb.variaveis.AlocaFuncBean) alocaFuncList.get(i);
            alocaFuncBean.delete();
        }

    }

    public List boletinsAbertoSemEnvio() {

        br.com.usinasantafe.pru.to.tb.variaveis.BoletimBean boletimBean = new br.com.usinasantafe.pru.to.tb.variaveis.BoletimBean();
        ArrayList listaPesq = new ArrayList();

        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("statusBoletim");
        pesquisa.setValor(1L);
        listaPesq.add(pesquisa);

        EspecificaPesquisa pesquisa2 = new EspecificaPesquisa();
        pesquisa2.setCampo("idExtBoletim");
        pesquisa2.setValor(0);
        listaPesq.add(pesquisa2);

        return boletimBean.get(listaPesq);

    }

    public List boletinsFechado() {
        br.com.usinasantafe.pru.to.tb.variaveis.BoletimBean boletimMMTO = new br.com.usinasantafe.pru.to.tb.variaveis.BoletimBean();
        return boletimMMTO.get("statusBoletim", 2L);
    }

    public Boolean verifBolAbertoSemEnvio() {
        return boletinsAbertoSemEnvio().size() > 0;
    }

    public Boolean verifBolFechado() {
        return boletinsFechado().size() > 0;
    }

    public Boolean verifAponta() {

        br.com.usinasantafe.pru.to.tb.variaveis.ApontBean apontaMMTO = new br.com.usinasantafe.pru.to.tb.variaveis.ApontBean();
        boolean verifAponta = apontaMMTO.hasElements();

        br.com.usinasantafe.pru.to.tb.variaveis.AlocaFuncBean alocaFuncBean = new br.com.usinasantafe.pru.to.tb.variaveis.AlocaFuncBean();
        boolean verifAlocaFunc = alocaFuncBean.hasElements();

        if (verifAponta || verifAlocaFunc){
            return true;
        }
        else{
            return false;
        }

    }

    public void envioApontaMM() {

        JsonArray jsonArrayAponta = new JsonArray();
        JsonArray jsonArrayAlocaFunc = new JsonArray();

        br.com.usinasantafe.pru.to.tb.variaveis.ApontBean apontBean = new br.com.usinasantafe.pru.to.tb.variaveis.ApontBean();
        List apontaList = apontBean.all();

        for (int i = 0; i < apontaList.size(); i++) {
            apontBean = (br.com.usinasantafe.pru.to.tb.variaveis.ApontBean) apontaList.get(i);
            Gson gson = new Gson();
            jsonArrayAponta.add(gson.toJsonTree(apontBean, apontBean.getClass()));
        }

        br.com.usinasantafe.pru.to.tb.variaveis.AlocaFuncBean alocaFuncBean = new br.com.usinasantafe.pru.to.tb.variaveis.AlocaFuncBean();
        List alocaFuncTOList = alocaFuncBean.dif("idExtBolAlocaFunc", 0);

        for (int j = 0; j < alocaFuncTOList.size(); j++) {
            alocaFuncBean = (br.com.usinasantafe.pru.to.tb.variaveis.AlocaFuncBean) alocaFuncTOList.get(j);
            Gson gsonItem = new Gson();
            jsonArrayAlocaFunc.add(gsonItem.toJsonTree(alocaFuncBean, alocaFuncBean.getClass()));
        }

        JsonObject jsonAponta = new JsonObject();
        jsonAponta.add("aponta", jsonArrayAponta);

        JsonObject jsonAlocaFunc = new JsonObject();
        jsonAlocaFunc.add("alocafunc", jsonArrayAlocaFunc);

        String dados = jsonAponta.toString() + "|" + jsonAlocaFunc.toString();

        Log.i("PMM", "APONTAMENTO = " + dados);

        String[] url = {urlsConexaoHttp.getsInsertAponta()};
        Map<String, Object> parametrosPost = new HashMap<String, Object>();
        parametrosPost.put("dado", dados);

        PostCadGenerico postCadGenerico = new PostCadGenerico();
        postCadGenerico.setParametrosPost(parametrosPost);
        postCadGenerico.execute(url);

    }

    public void enviarBolFechados() {

        br.com.usinasantafe.pru.to.tb.variaveis.BoletimBean boletimBean = new br.com.usinasantafe.pru.to.tb.variaveis.BoletimBean();
        List listBoletim = boletinsFechado();

        JsonArray jsonArrayBoletim = new JsonArray();
        JsonArray jsonArrayAponta = new JsonArray();
        JsonArray jsonArrayAlocaFunc = new JsonArray();

        for (int i = 0; i < listBoletim.size(); i++) {

            boletimBean = (br.com.usinasantafe.pru.to.tb.variaveis.BoletimBean) listBoletim.get(i);
            Gson gsonCabec = new Gson();
            jsonArrayBoletim.add(gsonCabec.toJsonTree(boletimBean, boletimBean.getClass()));

            br.com.usinasantafe.pru.to.tb.variaveis.ApontBean apontBean = new br.com.usinasantafe.pru.to.tb.variaveis.ApontBean();
            List listaAponta = apontBean.get("idBolAponta", boletimBean.getIdBoletim());

            for (int j = 0; j < listaAponta.size(); j++) {
                apontBean = (br.com.usinasantafe.pru.to.tb.variaveis.ApontBean) listaAponta.get(j);
                Gson gsonItem = new Gson();
                jsonArrayAponta.add(gsonItem.toJsonTree(apontBean, apontBean.getClass()));
            }

            br.com.usinasantafe.pru.to.tb.variaveis.AlocaFuncBean alocaFuncBean = new br.com.usinasantafe.pru.to.tb.variaveis.AlocaFuncBean();
            List alocaFuncList = alocaFuncBean.get("idBolAlocaFunc", boletimBean.getIdBoletim());

            for (int j = 0; j <  alocaFuncList.size(); j++) {
                alocaFuncBean = (br.com.usinasantafe.pru.to.tb.variaveis.AlocaFuncBean)  alocaFuncList.get(j);
                Gson gsonItem = new Gson();
                jsonArrayAlocaFunc.add(gsonItem.toJsonTree(alocaFuncBean, alocaFuncBean.getClass()));
            }

        }

        JsonObject jsonBoletim = new JsonObject();
        jsonBoletim.add("boletim", jsonArrayBoletim);

        JsonObject jsonAponta = new JsonObject();
        jsonAponta.add("aponta", jsonArrayAponta);

        JsonObject jsonAlocaFunc = new JsonObject();
        jsonAlocaFunc.add("alocafunc", jsonArrayAlocaFunc);

        String dados = jsonBoletim.toString() + "_" + jsonAponta.toString() + "|" + jsonAlocaFunc.toString();

        Log.i("PMM", "FECHADO = " + dados);

        UrlsConexaoHttp urlsConexaoHttp = new UrlsConexaoHttp();

        String[] url = {urlsConexaoHttp.getsInsertBolFechado()};
        Map<String, Object> parametrosPost = new HashMap<String, Object>();
        parametrosPost.put("dado", dados);

        PostCadGenerico conHttpPostGenerico = new PostCadGenerico();
        conHttpPostGenerico.setParametrosPost(parametrosPost);
        conHttpPostGenerico.execute(url);

    }

    public void enviarBolAberto() {

        br.com.usinasantafe.pru.to.tb.variaveis.BoletimBean boletimBean = new br.com.usinasantafe.pru.to.tb.variaveis.BoletimBean();
        List listBoletim = boletinsAbertoSemEnvio();

        JsonArray jsonArrayBoletim = new JsonArray();
        JsonArray jsonArrayAponta = new JsonArray();
        JsonArray jsonArrayAlocaFunc = new JsonArray();

        for (int i = 0; i < listBoletim.size(); i++) {

            boletimBean = (br.com.usinasantafe.pru.to.tb.variaveis.BoletimBean) listBoletim.get(i);
            Gson gsonCabec = new Gson();
            jsonArrayBoletim.add(gsonCabec.toJsonTree(boletimBean, boletimBean.getClass()));

            br.com.usinasantafe.pru.to.tb.variaveis.ApontBean apontBean = new br.com.usinasantafe.pru.to.tb.variaveis.ApontBean();
            List apontaList = apontBean.get("idBolAponta", boletimBean.getIdBoletim());

            for (int j = 0; j < apontaList.size(); j++) {
                apontBean = (br.com.usinasantafe.pru.to.tb.variaveis.ApontBean) apontaList.get(j);
                Gson gsonItem = new Gson();
                jsonArrayAponta.add(gsonItem.toJsonTree(apontBean, apontBean.getClass()));
            }

            br.com.usinasantafe.pru.to.tb.variaveis.AlocaFuncBean alocaFuncBean = new br.com.usinasantafe.pru.to.tb.variaveis.AlocaFuncBean();
            List implementoList = alocaFuncBean.get("idBolAlocaFunc", boletimBean.getIdBoletim());

            for (int j = 0; j < implementoList.size(); j++) {
                alocaFuncBean = (br.com.usinasantafe.pru.to.tb.variaveis.AlocaFuncBean) implementoList.get(j);
                Gson gsonItem = new Gson();
                jsonArrayAlocaFunc.add(gsonItem.toJsonTree(alocaFuncBean, alocaFuncBean.getClass()));
            }

        }

        JsonObject jsonBoletim = new JsonObject();
        jsonBoletim.add("boletim", jsonArrayBoletim);

        JsonObject jsonAponta = new JsonObject();
        jsonAponta.add("aponta", jsonArrayAponta);

        JsonObject jsonImplemento = new JsonObject();
        jsonImplemento.add("alocafunc", jsonArrayAlocaFunc);

        String dados = jsonBoletim.toString() + "_" + jsonAponta.toString() + "|" + jsonImplemento.toString();

        Log.i("PMM", "ABERTO = " + dados);

        UrlsConexaoHttp urlsConexaoHttp = new UrlsConexaoHttp();

        String[] url = {urlsConexaoHttp.getsInsertBolAberto()};
        Map<String, Object> parametrosPost = new HashMap<String, Object>();
        parametrosPost.put("dado", dados);

        PostCadGenerico conHttpPostGenerico = new PostCadGenerico();
        conHttpPostGenerico.setParametrosPost(parametrosPost);
        conHttpPostGenerico.execute(url);

    }

    public void envioDados(Context context) {
        enviando = true;
        ConexaoWeb conexaoWeb = new ConexaoWeb();
        if (conexaoWeb.verificaConexao(context)) {
            envioDadosPrinc();
        }
        else{
            enviando = false;
        }
    }

    public void envioDadosPrinc() {
        if (verifBolFechado()) {
            enviarBolFechados();
        } else {
            if (verifBolAbertoSemEnvio()) {
                enviarBolAberto();
            } else {
                if (verifAponta()) {
                    envioApontaMM();
                }
            }
        }
    }


    public boolean verifDadosEnvio() {
        if ((!verifBolFechado())
                && (!verifBolAbertoSemEnvio())
                && (!verifAponta())){
            enviando = false;
            return false;
        } else {
            return true;
        }
    }

    public void atualDelBoletim(String retorno){

        try{

            int pos1 = retorno.indexOf("=") + 1;
            int pos2 = retorno.indexOf("_") + 1;
            String id = retorno.substring(pos1, (pos2 - 1));

            br.com.usinasantafe.pru.to.tb.variaveis.BoletimBean boletimBean = new br.com.usinasantafe.pru.to.tb.variaveis.BoletimBean();
            List listBoletim = boletimBean.get("statusBoletim", 1L);
            boletimBean = (br.com.usinasantafe.pru.to.tb.variaveis.BoletimBean) listBoletim.get(0);
            boletimBean.setIdExtBoletim(Long.parseLong(id.trim()));
            boletimBean.update();

            br.com.usinasantafe.pru.to.tb.variaveis.ApontBean apontBean = new br.com.usinasantafe.pru.to.tb.variaveis.ApontBean();
            List apontaList = apontBean.get("idBolAponta", boletimBean.getIdBoletim());

            for (int j = 0; j < apontaList.size(); j++) {
                apontBean = (br.com.usinasantafe.pru.to.tb.variaveis.ApontBean) apontaList.get(j);
                apontBean.delete();
            }

            br.com.usinasantafe.pru.to.tb.variaveis.AlocaFuncBean alocaFuncBean = new br.com.usinasantafe.pru.to.tb.variaveis.AlocaFuncBean();
            List alocaFuncList = alocaFuncBean.get("idBolAlocaFunc", boletimBean.getIdBoletim());

            for (int j = 0; j < alocaFuncList.size(); j++) {
                alocaFuncBean = (br.com.usinasantafe.pru.to.tb.variaveis.AlocaFuncBean) alocaFuncList.get(j);
                alocaFuncBean.delete();
            }

        }
        catch(Exception e){
            Tempo.getInstance().setEnvioDado(true);
        }

    }

    public int getStatusEnvio() {
        if (enviando) {
            statusEnvio = 1;
        } else {
            if (!verifDadosEnvio()) {
                statusEnvio = 3;
            } else {
                statusEnvio = 2;
            }
        }
        return statusEnvio;
    }

    public void setEnviando(boolean enviando) {
        this.enviando = enviando;
    }

    public void setStatusEnvio(int statusEnvio) {
        this.statusEnvio = statusEnvio;
    }

}
