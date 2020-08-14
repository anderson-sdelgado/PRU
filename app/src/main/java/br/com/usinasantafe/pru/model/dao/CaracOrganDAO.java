package br.com.usinasantafe.pru.model.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pru.model.bean.estaticas.CaracOrganFitoBean;
import br.com.usinasantafe.pru.model.bean.estaticas.ROCAFitoBean;

public class CaracOrganDAO {

    public CaracOrganDAO() {
    }

    public List getCaracOrganList(Long idOrgan){

        ROCAFitoBean ROCAFitoBeanBD = new ROCAFitoBean();
        List<ROCAFitoBean> rOrganCaracList = ROCAFitoBeanBD.get("idOrgan", idOrgan);

        ArrayList<Long> idCaracOrganList = new ArrayList<Long>();

        for (ROCAFitoBean ROCAFitoBean : rOrganCaracList) {
            idCaracOrganList.add(ROCAFitoBean.getIdCaracOrgan());
        }

        CaracOrganFitoBean caracOrganFitoBean = new CaracOrganFitoBean();
        return caracOrganFitoBean.in("idCaracOrgan", idCaracOrganList);

    }

}
