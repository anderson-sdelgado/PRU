package br.com.usinasantafe.pru.model.dao;

import java.util.List;

import br.com.usinasantafe.pru.model.bean.variaveis.BoletimBean;
import br.com.usinasantafe.pru.util.Tempo;

public class BoletimDAO {

    public BoletimDAO() {
    }

    public boolean verBolAberto(){
        List boletimMMList = bolAbertoList();
        boolean ret = (boletimMMList.size() > 0);
        boletimMMList.clear();
        return ret;
    }

    public void salvarBolAberto1Colab(BoletimBean boletimBean){
        boletimBean.setIdExtBol(0L);
        boletimBean.setDthrInicioBol(Tempo.getInstance().data());
        boletimBean.setStatusBol(1L);
        boletimBean.insert();
    }


    public BoletimBean getBolAberto(){
        List boletimMMList = bolAbertoList();
        BoletimBean boletimBean = (BoletimBean) boletimMMList.get(0);
        boletimMMList.clear();
        return boletimBean;
    }

    private List bolAbertoList(){
        BoletimBean boletimBean = new BoletimBean();
        return boletimBean.get("statusBol", 1L);
    }

}
