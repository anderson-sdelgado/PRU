package br.com.usinasantafe.pru.model.dao;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pru.model.bean.variaveis.BoletimRuricolaBean;
import br.com.usinasantafe.pru.model.pst.EspecificaPesquisa;
import br.com.usinasantafe.pru.util.Tempo;

public class BoletimRuricolaDAO {

    public BoletimRuricolaDAO() {
    }

    public boolean verBolFechadoEnviado(){
        List boletimList = boletimFechadoEnviadoList();
        boolean ret = (boletimList.size() > 0);
        boletimList.clear();
        return ret;
    }

    public boolean verBolFechado(){
        List boletimList = boletimFechadoList();
        boolean ret = (boletimList.size() > 0);
        boletimList.clear();
        return ret;
    }

    public boolean verBolAberto(){
        List boletimList = boletimAbertoList();
        boolean ret = (boletimList.size() > 0);
        boletimList.clear();
        return ret;
    }

    public void salvarBolAberto(Long matricLider, Long nroOSConfig, Long idAtivConfig, Long idTurmaConfig, Long idTipoConfig){
        BoletimRuricolaBean boletimRuricolaBean = new BoletimRuricolaBean();
        boletimRuricolaBean.setMatricLiderBol(matricLider);
        boletimRuricolaBean.setOsBol(nroOSConfig);
        boletimRuricolaBean.setAtivPrincBol(idAtivConfig);
        boletimRuricolaBean.setIdTurmaBol(idTurmaConfig);
        boletimRuricolaBean.setTipoFuncBol(idTipoConfig);
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

    public List boletimFechadoList(){
        BoletimRuricolaBean boletimRuricolaBean = new BoletimRuricolaBean();
        return boletimRuricolaBean.get("statusBol", 2L);
    }

    public List boletimFechadoEnviadoList(){

        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("statusBol");
        pesquisa.setValor(1L);
        pesquisa.setTipo(2);

        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(pesquisa);

        BoletimRuricolaBean boletimRuricolaBean = new BoletimRuricolaBean();
        return boletimRuricolaBean.get(pesqArrayList);
    }

    public void salvarBolFechado() {
        BoletimRuricolaBean boletimRuricolaBean = getBolAberto();
        boletimRuricolaBean.setDthrFimBol(Tempo.getInstance().data());
        boletimRuricolaBean.setStatusBol(2L);
        boletimRuricolaBean.update();
    }

    public List boletimListCresc(){
        BoletimRuricolaBean boletimRuricolaBean = new BoletimRuricolaBean();
        List boletimList = boletimRuricolaBean.getAndOrderBy("statusBol", 3L, "idBol", true);
        return boletimList;
    }

    public Long delBoletim(){
        List boletimList = boletimListCresc();
        int qtdeCEC = boletimList.size();
        if (qtdeCEC > 10) {
            BoletimRuricolaBean boletimRuricolaBean = (BoletimRuricolaBean) boletimList.get(0);
            boletimRuricolaBean.delete();
            return boletimRuricolaBean.getIdBol();
        }
        else{
            return 0L;
        }
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

    public void updateBolAberto(String retorno){

        try{

            int pos1 = retorno.indexOf("_") + 1;
            String objPrinc = retorno.substring(pos1);

            JSONObject jObjBoletim = new JSONObject(objPrinc);
            JSONArray jsonArrayBoletim = jObjBoletim.getJSONArray("boletim");

            BoletimRuricolaBean boletimRuricolaBean = new BoletimRuricolaBean();

            for (int i = 0; i < jsonArrayBoletim.length(); i++) {

                JSONObject objBol = jsonArrayBoletim.getJSONObject(i);
                Gson gsonBol = new Gson();
                boletimRuricolaBean = gsonBol.fromJson(objBol.toString(), BoletimRuricolaBean.class);

                List bolRuricolaList = boletimRuricolaBean.get("idBol", boletimRuricolaBean.getIdBol());
                BoletimRuricolaBean boletimRuricolaBeanBD = (BoletimRuricolaBean) bolRuricolaList.get(0);
                bolRuricolaList.clear();

                boletimRuricolaBeanBD.setStatusBol(3L);
                boletimRuricolaBeanBD.update();

            }

        }
        catch(Exception e){
        }

    }

}
