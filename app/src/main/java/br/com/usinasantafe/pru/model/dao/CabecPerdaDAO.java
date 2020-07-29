package br.com.usinasantafe.pru.model.dao;

import java.util.List;

import br.com.usinasantafe.pru.model.bean.variaveis.CabecPerdaBean;

public class CabecPerdaDAO {

    public CabecPerdaDAO() {
    }

    public boolean hasCabecPerdaAberto(){
        List cabecPerdaList = getCabecPerdaAbertoList();
        boolean ret = cabecPerdaList.size() > 0;
        cabecPerdaList.clear();
        return ret;
    }

    public CabecPerdaBean getCabecPerdaAberto(){
        List cabecPerdaList = getCabecPerdaAbertoList();
        CabecPerdaBean cabecPerdaBean = (CabecPerdaBean) cabecPerdaList.get(0);
        cabecPerdaList.clear();
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
