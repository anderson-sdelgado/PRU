package br.com.usinasantafe.pru.model.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pru.model.bean.estaticas.CaracOrganFitoBean;
import br.com.usinasantafe.pru.model.bean.estaticas.ROrganCaracAmosFitoBean;

public class CaracOrganDAO {

    public CaracOrganDAO() {
    }

    public List getCaracOrganList(Long idOrgan){

        ROrganCaracAmosFitoBean rOrganCaracAmosFitoBeanBD = new ROrganCaracAmosFitoBean();
        List<ROrganCaracAmosFitoBean> rOrganCaracList = rOrganCaracAmosFitoBeanBD.get("idOrgan", idOrgan);

        ArrayList<Long> idCaracOrganList = new ArrayList<Long>();

        for (ROrganCaracAmosFitoBean rOrganCaracAmosFitoBean : rOrganCaracList) {
            idCaracOrganList.add(rOrganCaracAmosFitoBean.getIdCaracOrgan());
        }

        CaracOrganFitoBean caracOrganFitoBean = new CaracOrganFitoBean();
        return caracOrganFitoBean.in("idCaracOrgan", idCaracOrganList);

    }

}
