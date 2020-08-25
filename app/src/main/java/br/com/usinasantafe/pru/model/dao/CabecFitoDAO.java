package br.com.usinasantafe.pru.model.dao;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pru.model.bean.variaveis.BoletimRuricolaBean;
import br.com.usinasantafe.pru.model.bean.variaveis.CabecFitoBean;

public class CabecFitoDAO {

    public CabecFitoDAO() {
    }

    public void salvarCabecFitoAberto(CabecFitoBean cabecFitoBean){
        cabecFitoBean.setUltPontoCabecFito(0L);
        cabecFitoBean.setStatusCabecFito(1L);
        cabecFitoBean.insert();
    }

    public boolean verCabecAberto(){
        List cabecFitoList = cabeFitoAbertoList();
        boolean ret = cabecFitoList.size() > 0;
        cabecFitoList.clear();
        return ret;
    }

    public boolean verCabecFechado(){
        List cabecFitoList = cabecFitoFechadoList();
        boolean ret = cabecFitoList.size() > 0;
        cabecFitoList.clear();
        return ret;
    }

    public CabecFitoBean getCabecFitoAberto(){
        List cabecFitoList = cabeFitoAbertoList();
        CabecFitoBean cabecFitoBean = (CabecFitoBean) cabecFitoList.get(0);
        cabecFitoList.clear();
        return cabecFitoBean;
    }

    private List cabeFitoAbertoList(){
        CabecFitoBean cabecFitoBean = new CabecFitoBean();
        return cabecFitoBean.get("statusCabecFito", 1L);
    }

    public List cabecFitoFechadoList(){
        CabecFitoBean cabecFitoBean = new CabecFitoBean();
        return cabecFitoBean.get("statusCabecFito", 2L);
    }

    public void delCabecFito(Long idCabecFito){
        CabecFitoBean cabecFitoBean = new CabecFitoBean();
        List cabecFitoList = cabecFitoBean.get("idCabecFito", idCabecFito);
        cabecFitoBean = (CabecFitoBean) cabecFitoList.get(0);
        cabecFitoBean.delete();
        cabecFitoList.clear();
    }

    public void fecharCabecFito(){
        CabecFitoBean cabecFitoBean = getCabecFitoAberto();
        cabecFitoBean.setStatusCabecFito(2L);
        cabecFitoBean.update();
        cabecFitoBean.commit();
    }

    public List cabecListCresc(){
        CabecFitoBean cabecFitoBean = new CabecFitoBean();
        List cabecList = cabecFitoBean.getAndOrderBy("statusCabecFito", 3L, "idCabecFito", true);
        return cabecList;
    }

    public Long delCabec(){
        List cabecList = cabecListCresc();
        int qtdeCEC = cabecList.size();
        if (qtdeCEC > 10) {
            CabecFitoBean cabecFitoBean = (CabecFitoBean) cabecList.get(0);
            cabecFitoBean.delete();
            return cabecFitoBean.getIdCabecFito();
        }
        else{
            return 0L;
        }
    }

    public String dadosEnvioCabecFechado(){

        List cabecList = cabecFitoFechadoList();

        JsonArray jsonArrayCabec = new JsonArray();

        for (int i = 0; i < cabecList.size(); i++) {

            CabecFitoBean cabecFitoBean = (CabecFitoBean) cabecList.get(i);
            Gson gsonCabec = new Gson();
            jsonArrayCabec.add(gsonCabec.toJsonTree(cabecFitoBean, cabecFitoBean.getClass()));

        }

        cabecList.clear();

        JsonObject jsonCabec = new JsonObject();
        jsonCabec.add("cabec", jsonArrayCabec);

        return jsonCabec.toString();

    }

    public ArrayList<Long> idCabecList(){

        List cabecList = cabecFitoFechadoList();

        ArrayList<Long> idCabecList = new ArrayList<>();

        for (int i = 0; i < cabecList.size(); i++) {
            CabecFitoBean cabecFitoBean = (CabecFitoBean) cabecList.get(i);
            idCabecList.add(cabecFitoBean.getIdCabecFito());
        }

        cabecList.clear();

        return idCabecList;

    }

    public void updateCabecAberto(String retorno){

        try{

            int pos1 = retorno.indexOf("_") + 1;
            String objPrinc = retorno.substring(pos1);

            JSONObject jObjCabec = new JSONObject(objPrinc);
            JSONArray jsonArrayCabec = jObjCabec.getJSONArray("cabec");

            CabecFitoBean cabecFitoBean = new CabecFitoBean();

            for (int i = 0; i < jsonArrayCabec.length(); i++) {

                JSONObject objCabec = jsonArrayCabec.getJSONObject(i);
                Gson gsonCabec = new Gson();
                cabecFitoBean = gsonCabec.fromJson(objCabec.toString(), CabecFitoBean.class);

                List cabecFitoList = cabecFitoBean.get("idCabecFito", cabecFitoBean.getIdCabecFito());
                CabecFitoBean cabecFitoBeanBD = (CabecFitoBean) cabecFitoList.get(0);
                cabecFitoList.clear();

                cabecFitoBeanBD.setStatusCabecFito(3L);
                cabecFitoBeanBD.update();

            }

        }
        catch(Exception e){
        }

    }

}
