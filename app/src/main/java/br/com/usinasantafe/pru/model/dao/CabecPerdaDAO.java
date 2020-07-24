package br.com.usinasantafe.pru.model.dao;

import java.util.List;

import br.com.usinasantafe.pru.model.bean.variaveis.CabecPerdaBean;

public class CabecPerdaDAO {

    public CabecPerdaDAO() {
    }

    public boolean hasCabecPerdaAberto(){
        List cabecFitoList = getCabecPerdaAbertoList();
        boolean ret = cabecFitoList.size() > 0;
        cabecFitoList.clear();
        return ret;
    }

    public CabecPerdaBean getCabecPerdaAberto(){
        List cabecFitoList = getCabecPerdaAbertoList();
        CabecPerdaBean cabecPerdaBean = (CabecPerdaBean) cabecFitoList.get(0);
        cabecFitoList.clear();
        return cabecPerdaBean;
    }

    public List<CabecPerdaBean> getCabecPerdaAbertoList(){
        CabecPerdaBean cabecPerdaBean = new CabecPerdaBean();
        return cabecPerdaBean.get("statusCabecPerda", 1L);
    }

    public void salvarCabecPerdaAberto(CabecPerdaBean cabecPerdaBean){
        cabecPerdaBean.setStatusCabecPerda(1L);
        cabecPerdaBean.insert();
    }

}
