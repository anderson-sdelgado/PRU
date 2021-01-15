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

public class ApontRuricolaDAO {

    public ApontRuricolaDAO() {
    }

    public boolean verQtdeApont(BoletimRuricolaBean boletimRuricolaBean){
        List<ApontRuricolaBean> apontRuricolaList = getListApont(boletimRuricolaBean.getIdBol());
        boolean ret = apontRuricolaList.size() > 0;
        apontRuricolaList.clear();
        return ret;
    }

    public boolean verApont(BoletimRuricolaBean boletimRuricolaBean, ConfigBean configBean, Long idParada) {
        ApontRuricolaBean apontRuricolaBean = new ApontRuricolaBean();
        apontRuricolaBean.setIdBolApont(boletimRuricolaBean.getIdBol());
        apontRuricolaBean.setIdExtBolApont(boletimRuricolaBean.getIdExtBol());
        apontRuricolaBean.setOsApont(configBean.getNroOSConfig());
        apontRuricolaBean.setAtivApont(configBean.getIdAtivConfig());
        apontRuricolaBean.setParadaApont(idParada);
        apontRuricolaBean.setIdFuncApont(boletimRuricolaBean.getIdLiderBol());
        if(verApont(apontRuricolaBean)){
            return true;
        }
        else{
            return false;
        }
    }

    public Long verApont(BoletimRuricolaBean boletimRuricolaBean,  ConfigBean configBean, Long idParada, List<FuncBean> funcBeans){

        Long ret = 0L;

        for (FuncBean funcBean : funcBeans) {

            List<ApontRuricolaBean> apontRuricolaList = getApont(boletimRuricolaBean.getIdBol(), funcBean.getIdFunc());

            if(apontRuricolaList.size() > 0){

                ApontRuricolaBean apontRuricolaBean = apontRuricolaList.get(0);

                if ((configBean.getNroOSConfig().equals(apontRuricolaBean.getOsApont()))
                        && (configBean.getIdAtivConfig().equals(apontRuricolaBean.getAtivApont()))
                        && (idParada.equals(apontRuricolaBean.getParadaApont()))
                        && (funcBean.getIdFunc().equals(apontRuricolaBean.getIdFuncApont()))) {
                    return funcBean.getIdFunc();
                }

            }

        }

        return ret;

    }

    public void salvaApont(BoletimRuricolaBean boletimRuricolaBean, ConfigBean configBean, Long idParada, String dataHora) {
        ApontRuricolaBean apontRuricolaBean = new ApontRuricolaBean();
        apontRuricolaBean.setIdBolApont(boletimRuricolaBean.getIdBol());
        apontRuricolaBean.setIdExtBolApont(boletimRuricolaBean.getIdExtBol());
        apontRuricolaBean.setOsApont(configBean.getNroOSConfig());
        apontRuricolaBean.setAtivApont(configBean.getIdAtivConfig());
        apontRuricolaBean.setParadaApont(idParada);
        apontRuricolaBean.setDthrApont(dataHora);
        apontRuricolaBean.setIdFuncApont(boletimRuricolaBean.getIdLiderBol());
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
            apontRuricolaBean.setIdFuncApont(funcBean.getIdFunc());
            apontRuricolaBean.insert();
        }
    }

    public boolean verApont(ApontRuricolaBean apontRuricolaBean){
        List<ApontRuricolaBean> apontRuricolaList = getListApontOrdDesc(apontRuricolaBean.getIdBolApont());
        if(apontRuricolaList.size() > 0) {
            ApontRuricolaBean apontRuricolaBD = apontRuricolaList.get(0);
            apontRuricolaList.clear();
            if ((apontRuricolaBD.getOsApont().equals(apontRuricolaBean.getOsApont()))
                    && (apontRuricolaBD.getAtivApont().equals(apontRuricolaBean.getAtivApont()))
                    && (apontRuricolaBD.getParadaApont().equals(apontRuricolaBean.getParadaApont()))
                    && (apontRuricolaBD.getIdFuncApont().equals(apontRuricolaBean.getIdFuncApont()))) {
                return false;
            } else {
                return true;
            }
        }
        else{
            return true;
        }
    }

    public List<ApontRuricolaBean> getApont(Long idBol, Long idFunc){

        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqIdBolApont(idBol));
        pesqArrayList.add(getPesqIdFuncApont(idFunc));

        ApontRuricolaBean apontRuricolaBean = new ApontRuricolaBean();
        return apontRuricolaBean.getAndOrderBy(pesqArrayList, "idApont", false);

    }

    public List<ApontRuricolaBean> getListApontOrdDesc(Long idBol){
        ApontRuricolaBean apontRuricolaBean = new ApontRuricolaBean();
        return apontRuricolaBean.getAndOrderBy("idBolApont", idBol, "idApont", false);
    }

    public List<ApontRuricolaBean> getListApontEnvio(ArrayList<Long> idBolList){
        ApontRuricolaBean apontRuricolaBean = new ApontRuricolaBean();
        return apontRuricolaBean.in("idBolApont", idBolList);
    }

    public List<ApontRuricolaBean> getListApont(Long idBol){
        ApontRuricolaBean apontRuricolaBean = new ApontRuricolaBean();
        return apontRuricolaBean.get("idBolApont", idBol);
    }

    public void delListApont(List<ApontRuricolaBean> apontList){
        for(ApontRuricolaBean apontRuricolaBean : apontList) {
            apontRuricolaBean.delete();
        }
    }

    public String dadosEnvioApont(List<ApontRuricolaBean> apontaList){

        JsonArray jsonArrayAponta = new JsonArray();

        for (ApontRuricolaBean apontRuricolaBean : apontaList) {
            Gson gson = new Gson();
            jsonArrayAponta.add(gson.toJsonTree(apontRuricolaBean, apontRuricolaBean.getClass()));
        }

        apontaList.clear();

        JsonObject jsonAponta = new JsonObject();
        jsonAponta.add("aponta", jsonArrayAponta);

        return jsonAponta.toString();

    }

    private EspecificaPesquisa getPesqIdBolApont(Long idBolApont){
        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("idBolApont");
        pesquisa.setValor(idBolApont);
        pesquisa.setTipo(1);
        return pesquisa;
    }

    private EspecificaPesquisa getPesqIdFuncApont(Long idFuncApont){
        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("idFuncApont");
        pesquisa.setValor(idFuncApont);
        pesquisa.setTipo(1);
        return pesquisa;
    }

}
