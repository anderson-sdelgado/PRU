package br.com.usinasantafe.pru.model.dao;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pru.model.bean.estaticas.FuncBean;
import br.com.usinasantafe.pru.model.bean.variaveis.AlocaFuncBean;
import br.com.usinasantafe.pru.model.bean.variaveis.BoletimRuricolaBean;
import br.com.usinasantafe.pru.util.Tempo;

public class AlocaFuncDAO {

    public AlocaFuncDAO() {
    }

    public void alocaFunc(BoletimRuricolaBean boletimRuricolaBean) {
        AlocaFuncBean alocaFuncBean = new AlocaFuncBean();
        alocaFuncBean.setIdBolAlocaFunc(boletimRuricolaBean.getIdBol());
        alocaFuncBean.setIdExtBolAlocaFunc(boletimRuricolaBean.getIdExtBol());
        alocaFuncBean.setMatricFuncAlocaFunc(boletimRuricolaBean.getMatricLiderBol());
        alocaFuncBean.setDthrAlocaFunc(Tempo.getInstance().data());
        alocaFuncBean.setTipoAlocaFunc(1L);
        alocaFuncBean.insert();
    }

    public void alocaFunc(BoletimRuricolaBean boletimRuricolaBean, List<FuncBean> funcAlocList, List<FuncBean> funcList) {
        for (FuncBean funcAlocBean : funcAlocList) {
            for (FuncBean funcBean : funcList) {
                if((funcAlocBean.getMatricFunc().equals(funcBean.getMatricFunc()))
                        && (!funcAlocBean.getTipoAlocaFunc().equals(funcBean.getTipoAlocaFunc()))){
                    AlocaFuncBean alocaFuncBean = new AlocaFuncBean();
                    alocaFuncBean.setIdBolAlocaFunc(boletimRuricolaBean.getIdBol());
                    alocaFuncBean.setIdExtBolAlocaFunc(boletimRuricolaBean.getIdExtBol());
                    alocaFuncBean.setMatricFuncAlocaFunc(funcAlocBean.getMatricFunc());
                    alocaFuncBean.setDthrAlocaFunc(Tempo.getInstance().data());
                    alocaFuncBean.setTipoAlocaFunc(funcAlocBean.getTipoAlocaFunc());
                    alocaFuncBean.insert();
                }
            }
        }

    }

    public List getListAlocaFunc(Long idBol){
        AlocaFuncBean alocaFuncBean = new AlocaFuncBean();
        return alocaFuncBean.get("idBolAlocaFunc", idBol);
    }

    public void delListAlocaFunc(List alocaFuncList){
        for(int i = 0; i < alocaFuncList.size(); i++) {
            AlocaFuncBean alocaFuncBean = (AlocaFuncBean) alocaFuncList.get(i);
            alocaFuncBean.delete();
        }
    }

    public List getListAlocaFuncEnvio(ArrayList<Long> idBolList){
        AlocaFuncBean alocaFuncBean = new AlocaFuncBean();
        return alocaFuncBean.in("idBolAlocaFunc", idBolList);
    }

    public String dadosEnvioAlocaFunc(List alocaFuncList){

        JsonArray jsonArrayAlocaFunc = new JsonArray();

        for (int i = 0; i < alocaFuncList.size(); i++) {

            AlocaFuncBean alocaFuncBean = (AlocaFuncBean) alocaFuncList.get(i);
            Gson gson = new Gson();
            jsonArrayAlocaFunc.add(gson.toJsonTree(alocaFuncBean, alocaFuncBean.getClass()));

        }

        alocaFuncList.clear();

        JsonObject jsonAlocaFunc = new JsonObject();
        jsonAlocaFunc.add("alocafunc", jsonArrayAlocaFunc);

        return jsonAlocaFunc.toString();

    }

}
