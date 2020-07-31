package br.com.usinasantafe.pru.model.dao;

import java.util.List;

import br.com.usinasantafe.pru.model.bean.estaticas.OrganFitoBean;

public class OrganDAO {

    public OrganDAO() {
    }

    public List getOrganList(){
        OrganFitoBean organFitoBean = new OrganFitoBean();
        return organFitoBean.all();
    }

}
