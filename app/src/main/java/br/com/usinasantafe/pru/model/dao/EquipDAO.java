package br.com.usinasantafe.pru.model.dao;

import java.util.List;

import br.com.usinasantafe.pru.model.bean.estaticas.EquipBean;
import br.com.usinasantafe.pru.model.bean.estaticas.FuncBean;

public class EquipDAO {

    public EquipDAO() {
    }

    public boolean verEquip(Long codEquip){
        List equipList = equipList(codEquip);
        boolean ret = equipList.size() > 0;
        equipList.clear();
        return ret;
    }

    public EquipBean getEquip(Long codEquip){
        List equipList = equipList(codEquip);
        EquipBean equipBean = (EquipBean) equipList.get(0);
        equipList.clear();
        return equipBean;
    }

    private List equipList(Long codEquip){
        FuncBean funcBean = new FuncBean();
        List funcList = funcBean.get("codEquip", codEquip);
        return funcList;
    }

}
