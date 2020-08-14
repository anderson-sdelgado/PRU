package br.com.usinasantafe.pru.model.dao;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

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

    private List cabecFitoFechadoList(){
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

}
