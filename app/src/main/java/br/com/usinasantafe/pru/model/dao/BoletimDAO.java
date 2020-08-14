package br.com.usinasantafe.pru.model.dao;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pru.control.RuricolaCTR;
import br.com.usinasantafe.pru.model.bean.variaveis.BoletimRuricolaBean;
import br.com.usinasantafe.pru.util.Tempo;

public class BoletimDAO {

    public BoletimDAO() {
    }

    public boolean verCabecFechado(){
        List boletimMMList = boletimFechadoList();
        boolean ret = (boletimMMList.size() > 0);
        boletimMMList.clear();
        return ret;
    }

    public boolean verBolAberto(){
        List boletimMMList = boletimAbertoList();
        boolean ret = (boletimMMList.size() > 0);
        boletimMMList.clear();
        return ret;
    }

    public void salvarBolAberto(BoletimRuricolaBean boletimRuricolaBean){
        boletimRuricolaBean.setIdExtBol(0L);
        boletimRuricolaBean.setDthrInicioBol(Tempo.getInstance().data());
        boletimRuricolaBean.setStatusBol(1L);
        boletimRuricolaBean.insert();
    }

    public BoletimRuricolaBean getBolAberto(){
        List boletimMMList = boletimAbertoList();
        BoletimRuricolaBean boletimRuricolaBean = (BoletimRuricolaBean) boletimMMList.get(0);
        boletimMMList.clear();
        return boletimRuricolaBean;
    }

    private List boletimAbertoList(){
        BoletimRuricolaBean boletimRuricolaBean = new BoletimRuricolaBean();
        return boletimRuricolaBean.get("statusBol", 1L);
    }

    private List boletimFechadoList(){
        BoletimRuricolaBean boletimRuricolaBean = new BoletimRuricolaBean();
        return boletimRuricolaBean.get("statusBol", 2L);
    }

    public void salvarBolFechado() {
        BoletimRuricolaBean boletimRuricolaBean = getBolAberto();
        boletimRuricolaBean.setDthrFimBol(Tempo.getInstance().data());
        boletimRuricolaBean.setStatusBol(2L);
        boletimRuricolaBean.update();
    }


    public String dadosEnvioBolFechado(){

        List boletimList = boletimFechadoList();

        JsonArray jsonArrayBoletim = new JsonArray();

        for (int i = 0; i < boletimList.size(); i++) {

            BoletimRuricolaBean boletimRuricolaBean = (BoletimRuricolaBean) boletimList.get(i);
            Gson gsonCabec = new Gson();
            jsonArrayBoletim.add(gsonCabec.toJsonTree(boletimRuricolaBean, boletimRuricolaBean.getClass()));

        }

        boletimList.clear();

        JsonObject jsonBoletim = new JsonObject();
        jsonBoletim.add("boletim", jsonArrayBoletim);

        return jsonBoletim.toString();

    }

    public ArrayList<Long> idBolList(){

        List boletimList = boletimFechadoList();

        ArrayList<Long> idBolList = new ArrayList<>();
        for (int i = 0; i < boletimList.size(); i++) {
            BoletimRuricolaBean boletimRuricolaBean = (BoletimRuricolaBean) boletimList.get(i);
            idBolList.add(boletimRuricolaBean.getIdBol());
        }

        boletimList.clear();

        return idBolList;

    }

}
