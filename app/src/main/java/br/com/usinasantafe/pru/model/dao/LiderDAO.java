package br.com.usinasantafe.pru.model.dao;

import java.util.List;

import br.com.usinasantafe.pru.model.bean.estaticas.LiderBean;

public class LiderDAO {

    public LiderDAO() {
    }

    public boolean verLider(Long matricLider){
        LiderBean liderBean = new LiderBean();
        List liderList = liderBean.get("matricLider", matricLider);
        boolean ret = liderList.size() > 0;
        liderList.clear();
        return ret;
    }

}
