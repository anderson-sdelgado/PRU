package br.com.usinasantafe.pru.model.dao;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pru.control.RuricolaCTR;
import br.com.usinasantafe.pru.model.bean.variaveis.BoletimRuricolaBean;
import br.com.usinasantafe.pru.model.pst.EspecificaPesquisa;
import br.com.usinasantafe.pru.util.Tempo;

public class BoletimDAO {

    public BoletimDAO() {
    }

    public boolean verBolAberto(){
        List boletimMMList = bolAbertoList();
        boolean ret = (boletimMMList.size() > 0);
        boletimMMList.clear();
        return ret;
    }

    public List bolAbertoSemEnvioList() {

        BoletimRuricolaBean boletimMMBean = new BoletimRuricolaBean();
        ArrayList listaPesq = new ArrayList();

        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("statusBol");
        pesquisa.setValor(1L);
        pesquisa.setTipo(1);
        listaPesq.add(pesquisa);

        EspecificaPesquisa pesquisa2 = new EspecificaPesquisa();
        pesquisa2.setCampo("idExtBol");
        pesquisa2.setValor(0);
        pesquisa2.setTipo(1);
        listaPesq.add(pesquisa2);

        return boletimMMBean.get(listaPesq);

    }

    public void salvarBolAberto(BoletimRuricolaBean boletimRuricolaBean){
        boletimRuricolaBean.setIdExtBol(0L);
        boletimRuricolaBean.setDthrInicioBol(Tempo.getInstance().data());
        boletimRuricolaBean.setStatusBol(1L);
        boletimRuricolaBean.insert();
    }

    public BoletimRuricolaBean getBolAberto(){
        List boletimMMList = bolAbertoList();
        BoletimRuricolaBean boletimRuricolaBean = (BoletimRuricolaBean) boletimMMList.get(0);
        boletimMMList.clear();
        return boletimRuricolaBean;
    }

    private List bolAbertoList(){
        BoletimRuricolaBean boletimRuricolaBean = new BoletimRuricolaBean();
        return boletimRuricolaBean.get("statusBol", 1L);
    }

    public void salvarBolFechado() {
        BoletimRuricolaBean boletimRuricolaBean = getBolAberto();
        boletimRuricolaBean.setDthrFimBol(Tempo.getInstance().data());
        boletimRuricolaBean.setStatusBol(2L);
        boletimRuricolaBean.update();
    }

    public String dadosEnvioBolAberto(){

        BoletimRuricolaBean boletimRuricolaBean = getBolAberto();

        Gson gsonCabec = new Gson();
        JsonArray jsonArrayBoletim = new JsonArray();
        jsonArrayBoletim.add(gsonCabec.toJsonTree(boletimRuricolaBean, boletimRuricolaBean.getClass()));

        ArrayList<Long> idBolList = new ArrayList<Long>();
        idBolList.add(boletimRuricolaBean.getIdBol());

        RuricolaCTR ruricolaCTR = new RuricolaCTR();
        String dadosEnvioApont = ruricolaCTR.dadosEnvioApontBolMM(idBolList);

        JsonObject jsonBoletim = new JsonObject();
        jsonBoletim.add("boletim", jsonArrayBoletim);

        return jsonBoletim.toString() + "_" + dadosEnvioApont;

    }

    public List bolFechadoList() {
        BoletimRuricolaBean boletimRuricolaBean = new BoletimRuricolaBean();
        return boletimRuricolaBean.get("statusBol", 2L);
    }

    public String dadosEnvioBolFechado(){

        List boletimList = bolFechadoList();

        JsonArray jsonArrayBoletim = new JsonArray();
        String dadosEnvioApont = "";

        ArrayList<Long> idBolList = new ArrayList<Long>();
        for (int i = 0; i < boletimList.size(); i++) {

            BoletimRuricolaBean boletimRuricolaBean = (BoletimRuricolaBean) boletimList.get(i);
            Gson gsonCabec = new Gson();
            jsonArrayBoletim.add(gsonCabec.toJsonTree(boletimRuricolaBean, boletimRuricolaBean.getClass()));

            idBolList.add(boletimRuricolaBean.getIdBol());

        }

        boletimList.clear();

        RuricolaCTR ruricolaCTR = new RuricolaCTR();
        dadosEnvioApont = ruricolaCTR.dadosEnvioApontBolMM(idBolList);

        idBolList.clear();

        JsonObject jsonBoletim = new JsonObject();
        jsonBoletim.add("boletim", jsonArrayBoletim);

        return jsonBoletim.toString() + "_" + dadosEnvioApont;

    }

}
