package br.com.usinasantafe.pru.model.dao;

import java.util.List;

import br.com.usinasantafe.pru.model.bean.variaveis.BoletimBean;

public class BoletimDAO {

    public BoletimDAO() {
    }

    public boolean verBolAberto(){
        BoletimBean boletimBean = new BoletimBean();
        List boletimMMList = boletimBean.get("statusBol", 1L);
        boolean ret = (boletimMMList.size() > 0);
        boletimMMList.clear();
        return ret;
    }

}
