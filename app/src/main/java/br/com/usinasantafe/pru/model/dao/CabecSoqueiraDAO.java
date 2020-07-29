package br.com.usinasantafe.pru.model.dao;

import java.util.List;

import br.com.usinasantafe.pru.model.bean.variaveis.CabecSoqueiraBean;

public class CabecSoqueiraDAO {

    public CabecSoqueiraDAO() {
    }

    public boolean hasCabecSoqueiraAberto(){
        List cabecSoqueiraList = getCabecSoqueiraAbertoList();
        boolean ret = cabecSoqueiraList.size() > 0;
        cabecSoqueiraList.clear();
        return ret;
    }

    public CabecSoqueiraBean getCabecSoqueiraAberto(){
        List cabecSoqueiraList = getCabecSoqueiraAbertoList();
        CabecSoqueiraBean cabecSoqueiraBean = (CabecSoqueiraBean) cabecSoqueiraList.get(0);
        cabecSoqueiraList.clear();
        return cabecSoqueiraBean;
    }

    public List<CabecSoqueiraBean> getCabecSoqueiraAbertoList(){
        CabecSoqueiraBean cabecSoqueiraBean = new CabecSoqueiraBean();
        return cabecSoqueiraBean.get("statusSoqueiraPerda", 1L);
    }

    public void salvarCabecSoqueiraAberto(CabecSoqueiraBean cabecSoqueiraBean){
        cabecSoqueiraBean.setStatusCabecSoqueira(1L);
        cabecSoqueiraBean.insert();
    }

}
