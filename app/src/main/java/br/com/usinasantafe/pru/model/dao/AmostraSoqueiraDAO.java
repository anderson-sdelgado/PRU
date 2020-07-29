package br.com.usinasantafe.pru.model.dao;

import br.com.usinasantafe.pru.model.bean.variaveis.AmostraSoqueiraBean;

public class AmostraSoqueiraDAO {

    public AmostraSoqueiraDAO() {
    }

    public void salvarAmostraSoqueira(AmostraSoqueiraBean amostraSoqueiraBean){
        amostraSoqueiraBean.insert();
    }

}
