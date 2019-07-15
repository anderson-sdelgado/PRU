package br.com.usinasantafe.pru.bo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import android.content.Context;
import android.util.Log;

import br.com.usinasantafe.pru.conWEB.ConHttpPostCadGenerico;
import br.com.usinasantafe.pru.conWEB.UrlsConexaoHttp;
import br.com.usinasantafe.pru.pst.EspecificaPesquisa;
import br.com.usinasantafe.pru.to.tb.variaveis.ApontTO;
import br.com.usinasantafe.pru.to.tb.variaveis.BoletimTO;
import br.com.usinasantafe.pru.to.tb.variaveis.ConfiguracaoTO;
import br.com.usinasantafe.pru.to.tb.variaveis.AlocaFuncTO;

public class ManipDadosEnvio {

    private static ManipDadosEnvio instance = null;
    private UrlsConexaoHttp urlsConexaoHttp;
    private List listDatasFrenteTO;

    private int statusEnvio; //1 - Enviando; 2 - Existe Dados para Enviar; 3 - Todos os Dados Enviados
    private boolean enviando = false;

    public ManipDadosEnvio() {
        // TODO Auto-generated constructor stub
        urlsConexaoHttp = new UrlsConexaoHttp();
    }

    public static ManipDadosEnvio getInstance() {
        if (instance == null) {
            instance = new ManipDadosEnvio();
        }
        return instance;
    }


    public void salvaBoletimAberto(BoletimTO boletimTO) {

        ConfiguracaoTO configuracaoTO = new ConfiguracaoTO();
        List configList = configuracaoTO.all();
        configuracaoTO = (ConfiguracaoTO) configList.get(0);
        configList.clear();

        boletimTO.setIdExtBoletim(0L);
        boletimTO.setIdTurmaBoletim(configuracaoTO.getIdTurma());
        boletimTO.setDthrInicioBoletim(Tempo.getInstance().data());
        boletimTO.setStatusBoletim(1L);
        boletimTO.insert();

    }

    public void salvaFuncBoletim(Long lider, Long tipo) {

        BoletimTO boletimTO = new BoletimTO();
        List boletimList = boletimTO.get("statusBoletim", 1L);
        boletimTO = (BoletimTO) boletimList.get(0);
        boletimList.clear();

        AlocaFuncTO alocaFuncTO = new AlocaFuncTO();
        alocaFuncTO.setIdBolAlocaFunc(boletimTO.getIdBoletim());
        alocaFuncTO.setIdExtBolAlocaFunc(boletimTO.getIdExtBoletim());
        alocaFuncTO.setCodFuncionarioAlocaFunc(lider);
        alocaFuncTO.setDthrAlocaFunc(Tempo.getInstance().data());
        alocaFuncTO.setTipoAlocaFunc(tipo);
        alocaFuncTO.insert();

    }


    public void salvaAponta(ApontTO apontaPrincTO, ArrayList<Long> funcSel) {


        BoletimTO boletimTO = new BoletimTO();
        List lBol = boletimTO.get("statusBoletim", 1L);
        boletimTO = (BoletimTO) lBol.get(0);

        for (int i = 0; i < funcSel.size(); i++) {
            ApontTO apontTO = new ApontTO();
            apontTO.setIdBolAponta(boletimTO.getIdBoletim());
            apontTO.setIdExtBolAponta(boletimTO.getIdExtBoletim());
            apontTO.setOsAponta(apontaPrincTO.getOsAponta());
            apontTO.setAtivAponta(apontaPrincTO.getAtivAponta());
            apontTO.setParadaAponta(apontaPrincTO.getParadaAponta());
            apontTO.setDthrAponta(Tempo.getInstance().data());
            apontTO.setFuncAponta(funcSel.get(i));
            apontTO.insert();
        }

        ConfiguracaoTO configuracaoTO = new ConfiguracaoTO();
        List configList = configuracaoTO.all();
        configuracaoTO = (ConfiguracaoTO) configList.get(0);
        configuracaoTO.setDtUltApontConfig(Tempo.getInstance().data());
        configuracaoTO.update();

        envioDadosPrinc();

    }

    public void salvaAponta(ApontTO apontaPrincTO, Long funcSel) {

        BoletimTO boletimTO = new BoletimTO();
        List lBol = boletimTO.get("statusBoletim", 1L);
        boletimTO = (BoletimTO) lBol.get(0);

        ApontTO apontTO = new ApontTO();
        apontTO.setIdBolAponta(boletimTO.getIdBoletim());
        apontTO.setIdExtBolAponta(boletimTO.getIdExtBoletim());
        apontTO.setOsAponta(apontaPrincTO.getOsAponta());
        apontTO.setAtivAponta(apontaPrincTO.getAtivAponta());
        apontTO.setParadaAponta(apontaPrincTO.getParadaAponta());
        apontTO.setDthrAponta(Tempo.getInstance().data());
        apontTO.setFuncAponta(funcSel);
        apontTO.insert();

        ConfiguracaoTO configuracaoTO = new ConfiguracaoTO();
        List configList = configuracaoTO.all();
        configuracaoTO = (ConfiguracaoTO) configList.get(0);
        configuracaoTO.setDtUltApontConfig(Tempo.getInstance().data());
        configuracaoTO.update();

        envioDadosPrinc();

    }


    public void salvaBoletimFechado() {

        BoletimTO boletimTO = new BoletimTO();
        List listBoletim = boletimTO.get("statusBoletim", 1L);
        boletimTO = (BoletimTO) listBoletim.get(0);
        listBoletim.clear();

        boletimTO.setDthrFimBoletim(Tempo.getInstance().data());
        boletimTO.setStatusBoletim(2L);
        boletimTO.update();

        envioDadosPrinc();

    }

    public void delBolFechado() {

        BoletimTO boletimTO = new BoletimTO();
        List listBoletim = boletimTO.get("statusBoletim", 2L);
        ArrayList<Long> rLista = new ArrayList<Long>();

        for (int i = 0; i < listBoletim.size(); i++) {
            boletimTO = (BoletimTO) listBoletim.get(i);
            rLista.add(boletimTO.getIdBoletim());
        }

        ApontTO apontaMMTO = new ApontTO();
        List apontaList = apontaMMTO.in("idBolAponta", rLista);

        for (int j = 0; j < apontaList.size(); j++) {
            apontaMMTO = (ApontTO) apontaList.get(j);
            apontaMMTO.delete();
        }

        AlocaFuncTO alocaFuncTO = new AlocaFuncTO();
        List alocaFuncList = alocaFuncTO.in("idBolAlocaFunc", rLista);

        for (int j = 0; j < alocaFuncList.size(); j++) {
            alocaFuncTO = (AlocaFuncTO) alocaFuncList.get(j);
            alocaFuncTO.delete();
        }

        for (int i = 0; i < listBoletim.size(); i++) {
            boletimTO = (BoletimTO) listBoletim.get(i);
            boletimTO.delete();
        }

    }

    public void delApontaMM() {

        ApontTO apontTO = new ApontTO();
        apontTO.deleteAll();

        AlocaFuncTO alocaFuncTO = new AlocaFuncTO();
        List alocaFuncList = alocaFuncTO.dif("idExtBolAlocaFunc", 0);

        for (int i = 0; i < alocaFuncList.size(); i++) {
            alocaFuncTO = (AlocaFuncTO) alocaFuncList.get(i);
            alocaFuncTO.delete();
        }

    }

    public List boletinsAbertoSemEnvio() {

        BoletimTO boletimTO = new BoletimTO();
        ArrayList listaPesq = new ArrayList();

        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("statusBoletim");
        pesquisa.setValor(1L);
        listaPesq.add(pesquisa);

        EspecificaPesquisa pesquisa2 = new EspecificaPesquisa();
        pesquisa2.setCampo("idExtBoletim");
        pesquisa2.setValor(0);
        listaPesq.add(pesquisa2);

        return boletimTO.get(listaPesq);

    }

    public List boletinsFechado() {
        BoletimTO boletimMMTO = new BoletimTO();
        return boletimMMTO.get("statusBoletim", 2L);
    }

    public Boolean verifBolAbertoSemEnvio() {
        return boletinsAbertoSemEnvio().size() > 0;
    }

    public Boolean verifBolFechado() {
        return boletinsFechado().size() > 0;
    }

    public Boolean verifAponta() {

        ApontTO apontaMMTO = new ApontTO();
        boolean verifAponta = apontaMMTO.hasElements();

        AlocaFuncTO alocaFuncTO = new AlocaFuncTO();
        boolean verifAlocaFunc = alocaFuncTO.hasElements();

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

        ApontTO apontTO = new ApontTO();
        List apontaList = apontTO.all();

        for (int i = 0; i < apontaList.size(); i++) {
            apontTO = (ApontTO) apontaList.get(i);
            Gson gson = new Gson();
            jsonArrayAponta.add(gson.toJsonTree(apontTO, apontTO.getClass()));
        }

        AlocaFuncTO alocaFuncTO = new AlocaFuncTO();
        List alocaFuncTOList = alocaFuncTO.dif("idExtBolAlocaFunc", 0);

        for (int j = 0; j < alocaFuncTOList.size(); j++) {
            alocaFuncTO = (AlocaFuncTO) alocaFuncTOList.get(j);
            Gson gsonItem = new Gson();
            jsonArrayAlocaFunc.add(gsonItem.toJsonTree(alocaFuncTO, alocaFuncTO.getClass()));
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

        ConHttpPostCadGenerico conHttpPostCadGenerico = new ConHttpPostCadGenerico();
        conHttpPostCadGenerico.setParametrosPost(parametrosPost);
        conHttpPostCadGenerico.execute(url);

    }

    public void enviarBolFechados() {

        BoletimTO boletimTO = new BoletimTO();
        List listBoletim = boletinsFechado();

        JsonArray jsonArrayBoletim = new JsonArray();
        JsonArray jsonArrayAponta = new JsonArray();
        JsonArray jsonArrayAlocaFunc = new JsonArray();

        for (int i = 0; i < listBoletim.size(); i++) {

            boletimTO = (BoletimTO) listBoletim.get(i);
            Gson gsonCabec = new Gson();
            jsonArrayBoletim.add(gsonCabec.toJsonTree(boletimTO, boletimTO.getClass()));

            ApontTO apontTO = new ApontTO();
            List listaAponta = apontTO.get("idBolAponta", boletimTO.getIdBoletim());

            for (int j = 0; j < listaAponta.size(); j++) {
                apontTO = (ApontTO) listaAponta.get(j);
                Gson gsonItem = new Gson();
                jsonArrayAponta.add(gsonItem.toJsonTree(apontTO, apontTO.getClass()));
            }

            AlocaFuncTO alocaFuncTO = new AlocaFuncTO();
            List alocaFuncList = alocaFuncTO.get("idBolAlocaFunc", boletimTO.getIdBoletim());

            for (int j = 0; j <  alocaFuncList.size(); j++) {
                alocaFuncTO = (AlocaFuncTO)  alocaFuncList.get(j);
                Gson gsonItem = new Gson();
                jsonArrayAlocaFunc.add(gsonItem.toJsonTree(alocaFuncTO, alocaFuncTO.getClass()));
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

        ConHttpPostCadGenerico conHttpPostGenerico = new ConHttpPostCadGenerico();
        conHttpPostGenerico.setParametrosPost(parametrosPost);
        conHttpPostGenerico.execute(url);

    }

    public void enviarBolAberto() {

        BoletimTO boletimTO = new BoletimTO();
        List listBoletim = boletinsAbertoSemEnvio();

        JsonArray jsonArrayBoletim = new JsonArray();
        JsonArray jsonArrayAponta = new JsonArray();
        JsonArray jsonArrayAlocaFunc = new JsonArray();

        for (int i = 0; i < listBoletim.size(); i++) {

            boletimTO = (BoletimTO) listBoletim.get(i);
            Gson gsonCabec = new Gson();
            jsonArrayBoletim.add(gsonCabec.toJsonTree(boletimTO, boletimTO.getClass()));

            ApontTO apontTO = new ApontTO();
            List apontaList = apontTO.get("idBolAponta", boletimTO.getIdBoletim());

            for (int j = 0; j < apontaList.size(); j++) {
                apontTO = (ApontTO) apontaList.get(j);
                Gson gsonItem = new Gson();
                jsonArrayAponta.add(gsonItem.toJsonTree(apontTO, apontTO.getClass()));
            }

            AlocaFuncTO alocaFuncTO = new AlocaFuncTO();
            List implementoList = alocaFuncTO.get("idBolAlocaFunc", boletimTO.getIdBoletim());

            for (int j = 0; j < implementoList.size(); j++) {
                alocaFuncTO = (AlocaFuncTO) implementoList.get(j);
                Gson gsonItem = new Gson();
                jsonArrayAlocaFunc.add(gsonItem.toJsonTree(alocaFuncTO, alocaFuncTO.getClass()));
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

        ConHttpPostCadGenerico conHttpPostGenerico = new ConHttpPostCadGenerico();
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

            BoletimTO boletimTO = new BoletimTO();
            List listBoletim = boletimTO.get("statusBoletim", 1L);
            boletimTO = (BoletimTO) listBoletim.get(0);
            boletimTO.setIdExtBoletim(Long.parseLong(id.trim()));
            boletimTO.update();

            ApontTO apontTO = new ApontTO();
            List apontaList = apontTO.get("idBolAponta", boletimTO.getIdBoletim());

            for (int j = 0; j < apontaList.size(); j++) {
                apontTO = (ApontTO) apontaList.get(j);
                apontTO.delete();
            }

            AlocaFuncTO alocaFuncTO = new AlocaFuncTO();
            List alocaFuncList = alocaFuncTO.get("idBolAlocaFunc", boletimTO.getIdBoletim());

            for (int j = 0; j < alocaFuncList.size(); j++) {
                alocaFuncTO = (AlocaFuncTO) alocaFuncList.get(j);
                alocaFuncTO.delete();
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
