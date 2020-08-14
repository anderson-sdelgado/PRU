package br.com.usinasantafe.pru.model.dao;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

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

}
