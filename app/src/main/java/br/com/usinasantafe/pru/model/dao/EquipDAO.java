package br.com.usinasantafe.pru.model.dao;

import java.util.List;

import br.com.usinasantafe.pru.model.bean.estaticas.EquipBean;
import br.com.usinasantafe.pru.model.bean.estaticas.FuncBean;

public class EquipDAO {

    public EquipDAO() {
    }

    public boolean verEquip(Long nroEquip){
        List equipList = equipList(nroEquip);
        boolean ret = equipList.size() > 0;
        equipList.clear();
        return ret;
    }

    public EquipBean getEquip(Long nroEquip){
        List equipList = equipList(nroEquip);
        EquipBean equipBean = (EquipBean) equipList.get(0);
        equipList.clear();
        return equipBean;
    }

    private List equipList(Long nroEquip){
        EquipBean equipBean = new EquipBean();
        List equipList = equipBean.get("nroEquip", nroEquip);
        return equipList;
    }

}
