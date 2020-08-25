package br.com.usinasantafe.pru.model.dao;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pru.model.bean.variaveis.CabecFitoBean;
import br.com.usinasantafe.pru.model.bean.variaveis.CabecPerdaBean;
import br.com.usinasantafe.pru.model.bean.variaveis.CabecSoqueiraBean;

public class CabecSoqueiraDAO {

    public CabecSoqueiraDAO() {
    }

    public boolean verCabecAberto(){
        List cabecSoqueiraList = cabecSoqueiraAbertoList();
        boolean ret = cabecSoqueiraList.size() > 0;
        cabecSoqueiraList.clear();
        return ret;
    }

    public CabecSoqueiraBean getCabecSoqueiraAberto(){
        List cabecSoqueiraList = cabecSoqueiraAbertoList();
        CabecSoqueiraBean cabecSoqueiraBean = (CabecSoqueiraBean) cabecSoqueiraList.get(0);
        cabecSoqueiraList.clear();
        return cabecSoqueiraBean;
    }

    public List<CabecSoqueiraBean> cabecSoqueiraAbertoList(){
        CabecSoqueiraBean cabecSoqueiraBean = new CabecSoqueiraBean();
        return cabecSoqueiraBean.get("statusCabecSoqueira", 1L);
    }

    public List<CabecSoqueiraBean> cabecSoqueiraFechadoList(){
        CabecSoqueiraBean cabecSoqueiraBean = new CabecSoqueiraBean();
        return cabecSoqueiraBean.get("statusCabecSoqueira", 2L);
    }

    public void salvarCabecSoqueiraAberto(CabecSoqueiraBean cabecSoqueiraBean){
        cabecSoqueiraBean.setStatusCabecSoqueira(1L);
        cabecSoqueiraBean.insert();
    }

    public void delCabecSoqueira(Long idCabecPerda){
        CabecSoqueiraBean cabecSoqueiraBean = new CabecSoqueiraBean();
        List cabecPerdaList = cabecSoqueiraBean.get("idCabecSoqueira", idCabecPerda);
        cabecSoqueiraBean = (CabecSoqueiraBean) cabecPerdaList.get(0);
        cabecSoqueiraBean.delete();
        cabecPerdaList.clear();
    }

    public void fecharCabecSoqueira(){
        CabecSoqueiraBean cabecSoqueiraBean = getCabecSoqueiraAberto();
        cabecSoqueiraBean.setStatusCabecSoqueira(2L);
        cabecSoqueiraBean.update();
        cabecSoqueiraBean.commit();
    }

    public List cabecListCresc(){
        CabecSoqueiraBean cabecSoqueiraBean = new CabecSoqueiraBean();
        List cabecList = cabecSoqueiraBean.getAndOrderBy("statusCabecSoqueira", 3L, "idCabecSoqueira", true);
        return cabecList;
    }

    public Long delCabec(){
        List cabecList = cabecListCresc();
        int qtdeCEC = cabecList.size();
        if (qtdeCEC > 10) {
            CabecSoqueiraBean cabecSoqueiraBean = (CabecSoqueiraBean) cabecList.get(0);
            cabecSoqueiraBean.delete();
            return cabecSoqueiraBean.getIdCabecSoqueira();
        }
        else{
            return 0L;
        }
    }

    public String dadosEnvioCabecFechado(){

        List cabecList = cabecSoqueiraFechadoList();

        JsonArray jsonArrayCabec = new JsonArray();

        for (int i = 0; i < cabecList.size(); i++) {

            CabecSoqueiraBean cabecSoqueiraBean = (CabecSoqueiraBean) cabecList.get(i);
            Gson gsonCabec = new Gson();
            jsonArrayCabec.add(gsonCabec.toJsonTree(cabecSoqueiraBean, cabecSoqueiraBean.getClass()));

        }

        cabecList.clear();

        JsonObject jsonCabec = new JsonObject();
        jsonCabec.add("cabec", jsonArrayCabec);

        return jsonCabec.toString();

    }

    public ArrayList<Long> idCabecList(){

        List cabecList = cabecSoqueiraFechadoList();

        ArrayList<Long> idCabecList = new ArrayList<>();

        for (int i = 0; i < cabecList.size(); i++) {
            CabecSoqueiraBean cabecFitoBean = (CabecSoqueiraBean) cabecList.get(i);
            idCabecList.add(cabecFitoBean.getIdCabecSoqueira());
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

            CabecSoqueiraBean cabecSoqueiraBean = new CabecSoqueiraBean();

            for (int i = 0; i < jsonArrayCabec.length(); i++) {

                JSONObject objCabec = jsonArrayCabec.getJSONObject(i);
                Gson gsonBol = new Gson();
                cabecSoqueiraBean = gsonBol.fromJson(objCabec.toString(), CabecSoqueiraBean.class);

                List cabecSoqueiraList = cabecSoqueiraBean.get("idCabecSoqueira", cabecSoqueiraBean.getIdCabecSoqueira());
                CabecSoqueiraBean cabecSoqueiraBeanBD = (CabecSoqueiraBean) cabecSoqueiraList.get(0);
                cabecSoqueiraList.clear();

                cabecSoqueiraBeanBD.setStatusCabecSoqueira(3L);
                cabecSoqueiraBeanBD.update();

            }

        }
        catch(Exception e){
        }

    }

}
