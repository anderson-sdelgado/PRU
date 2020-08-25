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

public class CabecPerdaDAO {

    public CabecPerdaDAO() {
    }

    public boolean verCabecPerdaAberto(){
        List cabecPerdaList = cabecPerdaAbertoList();
        boolean ret = cabecPerdaList.size() > 0;
        cabecPerdaList.clear();
        return ret;
    }

    public boolean verCabecPerdaFechado(){
        List cabecPerdaList = cabecPerdaFechadoList();
        boolean ret = cabecPerdaList.size() > 0;
        cabecPerdaList.clear();
        return ret;
    }

    public CabecPerdaBean getCabecPerdaAberto(){
        List cabecPerdaList = cabecPerdaAbertoList();
        CabecPerdaBean cabecPerdaBean = (CabecPerdaBean) cabecPerdaList.get(0);
        cabecPerdaList.clear();
        return cabecPerdaBean;
    }

    public List<CabecPerdaBean> cabecPerdaAbertoList(){
        CabecPerdaBean cabecPerdaBean = new CabecPerdaBean();
        return cabecPerdaBean.get("statusCabecPerda", 1L);
    }

    public List<CabecPerdaBean> cabecPerdaFechadoList(){
        CabecPerdaBean cabecPerdaBean = new CabecPerdaBean();
        return cabecPerdaBean.get("statusCabecPerda", 2L);
    }

    public void salvarCabecPerdaAberto(CabecPerdaBean cabecPerdaBean){
        cabecPerdaBean.setStatusCabecPerda(1L);
        cabecPerdaBean.insert();
    }

    public void delCabecPerda(Long idCabecPerda){
        CabecPerdaBean cabecPerdaBean = new CabecPerdaBean();
        List cabecPerdaList = cabecPerdaBean.get("idCabecPerda", idCabecPerda);
        cabecPerdaBean = (CabecPerdaBean) cabecPerdaList.get(0);
        cabecPerdaBean.delete();
        cabecPerdaList.clear();
    }

    public void fecharCabecPerda(){
        CabecPerdaBean cabecPerdaBean = getCabecPerdaAberto();
        cabecPerdaBean.setStatusCabecPerda(2L);
        cabecPerdaBean.update();
        cabecPerdaBean.commit();
    }

    public List cabecListCresc(){
        CabecPerdaBean cabecPerdaBean = new CabecPerdaBean();
        List cabecList = cabecPerdaBean.getAndOrderBy("statusCabecPerda", 3L, "idCabecPerda", true);
        return cabecList;
    }

    public Long delCabec(){
        List cabecList = cabecListCresc();
        int qtdeCEC = cabecList.size();
        if (qtdeCEC > 10) {
            CabecPerdaBean cabecPerdaBean = (CabecPerdaBean) cabecList.get(0);
            cabecPerdaBean.delete();
            return cabecPerdaBean.getIdCabecPerda();
        }
        else{
            return 0L;
        }
    }

    public String dadosEnvioCabecFechado(){

        List cabecList = cabecPerdaFechadoList();

        JsonArray jsonArrayCabec = new JsonArray();

        for (int i = 0; i < cabecList.size(); i++) {

            CabecPerdaBean cabecPerdaBean = (CabecPerdaBean) cabecList.get(i);
            Gson gsonCabec = new Gson();
            jsonArrayCabec.add(gsonCabec.toJsonTree(cabecPerdaBean, cabecPerdaBean.getClass()));

        }

        cabecList.clear();

        JsonObject jsonCabec = new JsonObject();
        jsonCabec.add("cabec", jsonArrayCabec);

        return jsonCabec.toString();

    }

    public ArrayList<Long> idCabecList(){

        List cabecList = cabecPerdaFechadoList();

        ArrayList<Long> idCabecList = new ArrayList<>();

        for (int i = 0; i < cabecList.size(); i++) {
            CabecPerdaBean cabecPerdaBean = (CabecPerdaBean) cabecList.get(i);
            idCabecList.add(cabecPerdaBean.getIdCabecPerda());
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

            CabecPerdaBean cabecPerdaBean = new CabecPerdaBean();

            for (int i = 0; i < jsonArrayCabec.length(); i++) {

                JSONObject objCabec = jsonArrayCabec.getJSONObject(i);
                Gson gsonCabec = new Gson();
                cabecPerdaBean = gsonCabec.fromJson(objCabec.toString(), CabecPerdaBean.class);

                List cabecPerdaList = cabecPerdaBean.get("idCabecPerda", cabecPerdaBean.getIdCabecPerda());
                CabecPerdaBean cabecPerdaBeanBD = (CabecPerdaBean) cabecPerdaList.get(0);
                cabecPerdaList.clear();

                cabecPerdaBeanBD.setStatusCabecPerda(3L);
                cabecPerdaBeanBD.update();

            }

        }
        catch(Exception e){
        }

    }

}
