package br.com.usinasantafe.pru.model.dao;

import br.com.usinasantafe.pru.model.bean.variaveis.CabecFitoBean;

public class CabecFitoDAO {

    public CabecFitoDAO() {
    }

    public void salvarCabecFitoAberto(CabecFitoBean cabecFitoBean){
        cabecFitoBean.setUltPontoCabecFito(0L);
        cabecFitoBean.setStatusCabecFito(1L);
        cabecFitoBean.insert();
    }

}
