package br.com.usinasantafe.pru.model.dao;

import java.util.List;

import br.com.usinasantafe.pru.model.bean.estaticas.FuncBean;

public class FuncDAO {

    public boolean verFunc(Long codFunc){
        FuncBean funcBean = new FuncBean();
        List funcList = funcBean.get("codFunc", codFunc);
        boolean ret = funcList.size() > 0;
        funcList.clear();
        return ret;
    }

}
