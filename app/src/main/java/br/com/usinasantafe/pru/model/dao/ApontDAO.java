package br.com.usinasantafe.pru.model.dao;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pru.model.bean.estaticas.FuncBean;
import br.com.usinasantafe.pru.model.bean.variaveis.ApontRuricolaBean;
import br.com.usinasantafe.pru.model.bean.variaveis.BoletimRuricolaBean;
import br.com.usinasantafe.pru.model.bean.variaveis.ConfigBean;
import br.com.usinasantafe.pru.model.pst.EspecificaPesquisa;

public class ApontDAO {

    public ApontDAO() {
    }

    public void salvaApont(BoletimRuricolaBean boletimRuricolaBean, ConfigBean configBean, Long idParada, String dataHora) {

        ApontRuricolaBean apontRuricolaBean = new ApontRuricolaBean();
        apontRuricolaBean.setIdBolApont(boletimRuricolaBean.getIdBol());
        apontRuricolaBean.setIdExtBolApont(boletimRuricolaBean.getIdExtBol());
        apontRuricolaBean.setOsApont(configBean.getNroOSConfig());
        apontRuricolaBean.setAtivApont(configBean.getIdAtivConfig());
        apontRuricolaBean.setParadaApont(idParada);
        apontRuricolaBean.setDthrApont(dataHora);
        apontRuricolaBean.setFuncApont(boletimRuricolaBean.getIdLiderBol());
        apontRuricolaBean.insert();

    }

    public void salvaApont(BoletimRuricolaBean boletimRuricolaBean, ConfigBean configBean, Long idParada, String dataHora, List<FuncBean> funcBeans) {

        for (FuncBean funcBean : funcBeans) {
            ApontRuricolaBean apontRuricolaBean = new ApontRuricolaBean();
            apontRuricolaBean.setIdBolApont(boletimRuricolaBean.getIdBol());
            apontRuricolaBean.setIdExtBolApont(boletimRuricolaBean.getIdExtBol());
            apontRuricolaBean.setOsApont(configBean.getNroOSConfig());
            apontRuricolaBean.setAtivApont(configBean.getIdAtivConfig());
            apontRuricolaBean.setParadaApont(idParada);
            apontRuricolaBean.setDthrApont(dataHora);
            apontRuricolaBean.setFuncApont(funcBean.getIdFunc());
            apontRuricolaBean.insert();
        }

    }

    public List getListApontEnvio(ArrayList<Long> idBolList){

        ApontRuricolaBean apontRuricolaBean = new ApontRuricolaBean();
        return apontRuricolaBean.in("idBolApont", idBolList);

    }

    public List getListApont(Long idBol){
        ApontRuricolaBean apontRuricolaBean = new ApontRuricolaBean();
        return apontRuricolaBean.get("idBolApont", idBol);
    }

    public void delListApont(List apontList){
        for(int i = 0; i < apontList.size(); i++) {
            ApontRuricolaBean apontRuricolaBean = (ApontRuricolaBean) apontList.get(i);
            apontRuricolaBean.delete();
        }
    }

    public String dadosEnvioApont(List apontaList){

        JsonArray jsonArrayAponta = new JsonArray();

        for (int i = 0; i < apontaList.size(); i++) {

            ApontRuricolaBean apontMMBean = (ApontRuricolaBean) apontaList.get(i);
            Gson gson = new Gson();
            jsonArrayAponta.add(gson.toJsonTree(apontMMBean, apontMMBean.getClass()));

        }

        apontaList.clear();

        JsonObject jsonAponta = new JsonObject();
        jsonAponta.add("aponta", jsonArrayAponta);

        return jsonAponta.toString();

    }

}
