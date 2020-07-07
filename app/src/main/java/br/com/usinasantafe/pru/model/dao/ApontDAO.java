package br.com.usinasantafe.pru.model.dao;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pru.model.bean.estaticas.FuncBean;
import br.com.usinasantafe.pru.model.bean.variaveis.ApontBean;
import br.com.usinasantafe.pru.model.bean.variaveis.BoletimBean;
import br.com.usinasantafe.pru.model.bean.variaveis.ConfigBean;
import br.com.usinasantafe.pru.model.pst.EspecificaPesquisa;
import br.com.usinasantafe.pru.util.Tempo;

public class ApontDAO {

    public ApontDAO() {
    }

    public void salvaApont(BoletimBean boletimBean, ConfigBean configBean, Long idParada, String dataHora) {

        ApontBean apontBean = new ApontBean();
        apontBean.setIdBolApont(boletimBean.getIdBol());
        apontBean.setIdExtBolApont(boletimBean.getIdExtBol());
        apontBean.setOsApont(configBean.getNroOSConfig());
        apontBean.setAtivApont(configBean.getIdAtivConfig());
        apontBean.setParadaApont(idParada);
        apontBean.setDthrApont(dataHora);
        apontBean.setFuncApont(boletimBean.getIdLiderBol());
        apontBean.insert();

    }

    public void salvaApont(BoletimBean boletimBean, ConfigBean configBean, Long idParada, String dataHora, List<FuncBean> funcBeans) {

        for (FuncBean funcBean : funcBeans) {
            ApontBean apontBean = new ApontBean();
            apontBean.setIdBolApont(boletimBean.getIdBol());
            apontBean.setIdExtBolApont(boletimBean.getIdExtBol());
            apontBean.setOsApont(configBean.getNroOSConfig());
            apontBean.setAtivApont(configBean.getIdAtivConfig());
            apontBean.setParadaApont(idParada);
            apontBean.setDthrApont(dataHora);
            apontBean.setFuncApont(funcBean.getIdFunc());
            apontBean.insert();
        }

    }

    public List getListApontEnvio(ArrayList<Long> idBolList){

        ApontBean apontBean = new ApontBean();

        ArrayList pesqArrayList = new ArrayList();
        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("statusApont");
        pesquisa.setValor(1L);
        pesquisa.setTipo(1);
        pesqArrayList.add(pesquisa);

        return apontBean.inAndGet("idBolApont", idBolList, pesqArrayList);

    }

    public List getListApontEnvio() {
        ApontBean apontBean = new ApontBean();
        return apontBean.get("statusApont", 1L);
    }

    public String dadosEnvioApont(List apontaList){

        JsonArray jsonArrayAponta = new JsonArray();

        for (int i = 0; i < apontaList.size(); i++) {

            ApontBean apontMMBean = (ApontBean) apontaList.get(i);
            Gson gson = new Gson();
            jsonArrayAponta.add(gson.toJsonTree(apontMMBean, apontMMBean.getClass()));

        }

        apontaList.clear();

        JsonObject jsonAponta = new JsonObject();
        jsonAponta.add("aponta", jsonArrayAponta);

        return jsonAponta.toString();

    }

}
