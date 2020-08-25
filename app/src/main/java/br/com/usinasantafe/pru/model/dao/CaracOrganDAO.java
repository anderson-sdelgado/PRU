package br.com.usinasantafe.pru.model.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pru.model.bean.estaticas.CaracOrganFitoBean;
import br.com.usinasantafe.pru.model.bean.estaticas.ROCAFitoBean;

public class CaracOrganDAO {

    public CaracOrganDAO() {
    }

    public List getCaracOrganList(Long idOrgan){

        ROCAFitoBean rOCAFitoBeanBD = new ROCAFitoBean();
        List<ROCAFitoBean> rOrganCaracList = rOCAFitoBeanBD.get("idOrgan", idOrgan);

        ArrayList<Long> idCaracOrganList = new ArrayList<Long>();

        for (ROCAFitoBean ROCAFitoBean : rOrganCaracList) {
            idCaracOrganList.add(ROCAFitoBean.getIdCaracOrgan());
        }

        CaracOrganFitoBean caracOrganFitoBean = new CaracOrganFitoBean();
        return caracOrganFitoBean.in("idCaracOrgan", idCaracOrganList);

    }

    public CaracOrganFitoBean getCaracOrgan(Long idCaracOrgan){

        CaracOrganFitoBean caracOrganFitoBean = new CaracOrganFitoBean();
        List caracOrganList = caracOrganFitoBean.get("idCaracOrgan", idCaracOrgan);
        caracOrganFitoBean = (CaracOrganFitoBean) caracOrganList.get(0);
        caracOrganList.clear();
        return caracOrganFitoBean;

    }

}
