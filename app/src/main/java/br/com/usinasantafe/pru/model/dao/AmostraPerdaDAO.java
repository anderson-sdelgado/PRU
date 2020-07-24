package br.com.usinasantafe.pru.model.dao;

import java.util.List;

import br.com.usinasantafe.pru.model.bean.variaveis.AmostraPerdaBean;

public class AmostraPerdaDAO {

    public AmostraPerdaDAO() {
    }

    public void salvarAmostraPerda(AmostraPerdaBean amostraPerdaBean){
        amostraPerdaBean.insert();
    }

    public boolean verAmostraPerda(Long idCabecPerda){
        List<AmostraPerdaBean> amostraList = amostraPerdaList(idCabecPerda);
        boolean ret = amostraList.size() > 0;
        amostraList.clear();
        return ret;
    }

    public int qtdeAmostraPerda(Long idCabecPerda){
        List<AmostraPerdaBean> amostraList = amostraPerdaList(idCabecPerda);
        int ret = amostraList.size() + 1;
        amostraList.clear();
        return ret;
    }

    public AmostraPerdaBean getAmostraPerda(Long idCabecPerda){
        List<AmostraPerdaBean> amostraList = amostraPerdaList(idCabecPerda);
        AmostraPerdaBean amostraBean = amostraList.get(0);
        amostraList.clear();
        return amostraBean;
    }

    private List<AmostraPerdaBean> amostraPerdaList(Long idCabecPerda){
        AmostraPerdaBean amostraPerdaBean = new AmostraPerdaBean();
        return amostraPerdaBean.get("idCabecAmostraPerda", idCabecPerda);
    }

}
