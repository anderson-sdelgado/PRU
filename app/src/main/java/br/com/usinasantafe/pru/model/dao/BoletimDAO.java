package br.com.usinasantafe.pru.model.dao;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pru.control.RuricolaCTR;
import br.com.usinasantafe.pru.model.bean.variaveis.BoletimBean;
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

        BoletimBean boletimMMBean = new BoletimBean();
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

    public void salvarBolAberto(BoletimBean boletimBean){
        boletimBean.setIdExtBol(0L);
        boletimBean.setDthrInicioBol(Tempo.getInstance().data());
        boletimBean.setStatusBol(1L);
        boletimBean.insert();
    }

    public BoletimBean getBolAberto(){
        List boletimMMList = bolAbertoList();
        BoletimBean boletimBean = (BoletimBean) boletimMMList.get(0);
        boletimMMList.clear();
        return boletimBean;
    }

    private List bolAbertoList(){
        BoletimBean boletimBean = new BoletimBean();
        return boletimBean.get("statusBol", 1L);
    }

    public void salvarBolFechado() {
        BoletimBean boletimBean = getBolAberto();
        boletimBean.setDthrFimBol(Tempo.getInstance().data());
        boletimBean.setStatusBol(2L);
        boletimBean.update();
    }

    public String dadosEnvioBolAberto(){

        BoletimBean boletimBean = getBolAberto();

        Gson gsonCabec = new Gson();
        JsonArray jsonArrayBoletim = new JsonArray();
        jsonArrayBoletim.add(gsonCabec.toJsonTree(boletimBean, boletimBean.getClass()));

        ArrayList<Long> idBolList = new ArrayList<Long>();
        idBolList.add(boletimBean.getIdBol());

        RuricolaCTR ruricolaCTR = new RuricolaCTR();
        String dadosEnvioApont = ruricolaCTR.dadosEnvioApontBolMM(idBolList);

        JsonObject jsonBoletim = new JsonObject();
        jsonBoletim.add("boletim", jsonArrayBoletim);

        return jsonBoletim.toString() + "_" + dadosEnvioApont;

    }

    public List bolFechadoList() {
        BoletimBean boletimBean = new BoletimBean();
        return boletimBean.get("statusBol", 2L);
    }

    public String dadosEnvioBolFechado(){

        List boletimList = bolFechadoList();

        JsonArray jsonArrayBoletim = new JsonArray();
        String dadosEnvioApont = "";

        ArrayList<Long> idBolList = new ArrayList<Long>();
        for (int i = 0; i < boletimList.size(); i++) {

            BoletimBean boletimBean = (BoletimBean) boletimList.get(i);
            Gson gsonCabec = new Gson();
            jsonArrayBoletim.add(gsonCabec.toJsonTree(boletimBean, boletimBean.getClass()));

            idBolList.add(boletimBean.getIdBol());

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
