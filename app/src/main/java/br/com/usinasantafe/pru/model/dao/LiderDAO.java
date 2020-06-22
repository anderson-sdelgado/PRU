package br.com.usinasantafe.pru.model.dao;

import java.util.List;

import br.com.usinasantafe.pru.model.bean.estaticas.LiderBean;

public class LiderDAO {

    public LiderDAO() {
    }

    public boolean verLider(Long codLider){
        LiderBean liderBean = new LiderBean();
        List liderList = liderBean.get("codLider", codLider);
        boolean ret = liderList.size() > 0;
        liderList.clear();
        return ret;
    }

}
