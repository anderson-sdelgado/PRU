package br.com.usinasantafe.pru.model.dao;

import java.util.List;

import br.com.usinasantafe.pru.model.bean.estaticas.OrganBean;

public class OrganDAO {

    public OrganDAO() {
    }

    public List getOrganList(){
        OrganBean organBean = new OrganBean();
        return organBean.all();
    }

}
