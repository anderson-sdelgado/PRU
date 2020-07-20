package br.com.usinasantafe.pru.model.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pru.model.bean.estaticas.CaracOrganBean;
import br.com.usinasantafe.pru.model.bean.estaticas.ROrganCaracAmosBean;

public class CaracOrganDAO {

    public CaracOrganDAO() {
    }

    public List getCaracOrganList(Long idOrgan){

        ROrganCaracAmosBean rOrganCaracAmosBeanBD = new ROrganCaracAmosBean();
        List<ROrganCaracAmosBean> rOrganCaracList = rOrganCaracAmosBeanBD.get("idOrgan", idOrgan);

        ArrayList<Long> idCaracOrganList = new ArrayList<Long>();

        for (ROrganCaracAmosBean rOrganCaracAmosBean : rOrganCaracList) {
            idCaracOrganList.add(rOrganCaracAmosBean.getIdCaracOrgan());
        }

        CaracOrganBean caracOrganBean = new CaracOrganBean();
        return caracOrganBean.in("idCaracOrgan", idCaracOrganList);

    }

}
