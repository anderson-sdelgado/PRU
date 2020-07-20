package br.com.usinasantafe.pru.model.dao;

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

    public boolean verCabecFitoAberto(){
        List cabecFitoList = getCabeFitoAbertoList();
        boolean ret = cabecFitoList.size() > 0;
        cabecFitoList.clear();
        return ret;
    }

    public CabecFitoBean getCabecFitoAberto(){
        List cabecFitoList = getCabeFitoAbertoList();
        CabecFitoBean cabecFitoBean = (CabecFitoBean) cabecFitoList.get(0);
        cabecFitoList.clear();
        return cabecFitoBean;
    }

    private List getCabeFitoAbertoList(){
        CabecFitoBean cabecFitoBean = new CabecFitoBean();
        return cabecFitoBean.get("statusCabecFito", 1L);
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


}
