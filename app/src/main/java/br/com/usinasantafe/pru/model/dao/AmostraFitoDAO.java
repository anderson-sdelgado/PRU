package br.com.usinasantafe.pru.model.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pru.model.bean.estaticas.AmostraFitoBean;
import br.com.usinasantafe.pru.model.bean.estaticas.ROrganCaracAmosFitoBean;
import br.com.usinasantafe.pru.model.pst.EspecificaPesquisa;

public class AmostraFitoDAO {

    public AmostraFitoDAO() {
    }

    public AmostraFitoBean getAmostra(Long idAmostra){
        List<AmostraFitoBean> amostraList = amostraList(idAmostra);
        AmostraFitoBean amostraFitoBean = amostraList.get(0);
        amostraList.clear();
        return amostraFitoBean;
    }

    public boolean verAmostra(Long idOrgan, Long idCaracOrgan){
        List<AmostraFitoBean> amostraList = getAmostraOrganList(idOrgan, idCaracOrgan);
        boolean ret = amostraList.size() > 0;
        amostraList.clear();
        return ret;
    }

    public boolean hasAmostraCabec(Long idOrgan, Long idCaracOrgan){
        List<AmostraFitoBean> amostraList = amostraCabecList(idOrgan, idCaracOrgan);
        boolean ret = amostraList.size() > 0;
        amostraList.clear();
        return ret;
    }

    private List<AmostraFitoBean> getAmostraOrganList(Long idOrgan, Long idCaracOrgan){
        ArrayList<Long> idAmostraList = getIdAmostraList(idOrgan, idCaracOrgan);
        List amostraList = inAmostraOrgan(idAmostraList);
        idAmostraList.clear();
        return amostraList;
    }

    public List<AmostraFitoBean> amostraCabecList(Long idOrgan, Long idCaracOrgan){
        ArrayList<Long> idAmostraList = getIdAmostraList(idOrgan, idCaracOrgan);
        List<AmostraFitoBean> amostraList = inAndGetAmostraCabec(idAmostraList);
        idAmostraList.clear();
        return amostraList;
    }

    private List<AmostraFitoBean> amostraList(Long idAmostra){
        AmostraFitoBean amostraFitoBean = new AmostraFitoBean();
        return amostraFitoBean.get("idAmostra", idAmostra);
    }

    private List<AmostraFitoBean> inAmostraOrgan(ArrayList<Long> idAmostraOrganList){
        AmostraFitoBean amostraFitoBean = new AmostraFitoBean();
        return amostraFitoBean.in("idAmostraOrgan", idAmostraOrganList);
    }

    private List<AmostraFitoBean> inAndGetAmostraCabec(ArrayList<Long> idAmostraList){
        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqTipoAmostraQC());
        AmostraFitoBean amostraFitoBean = new AmostraFitoBean();
        return amostraFitoBean.inAndGet("idAmostraOrgan", idAmostraList, pesqArrayList);
    }

    private EspecificaPesquisa getPesqOrgan(Long idOrgan){
        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("idOrgan");
        pesquisa.setValor(idOrgan);
        pesquisa.setTipo(1);
        return pesquisa;
    }

    private EspecificaPesquisa getPesqCaracOrgan(Long idCaracOrgan){
        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("idCaracOrgan");
        pesquisa.setValor(idCaracOrgan);
        pesquisa.setTipo(1);
        return pesquisa;
    }

    private EspecificaPesquisa getPesqTipoAmostraQC(){
        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("tipoAmostra");
        pesquisa.setValor(2L);
        pesquisa.setTipo(1);
        return pesquisa;
    }

    private ArrayList<Long> getIdAmostraList(Long idOrgan, Long idCaracOrgan){

        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqOrgan(idOrgan));
        pesqArrayList.add(getPesqCaracOrgan(idCaracOrgan));
        ROrganCaracAmosFitoBean rOrganCaracAmosFitoBeanBD = new ROrganCaracAmosFitoBean();
        List<ROrganCaracAmosFitoBean> rOrganCaracList = rOrganCaracAmosFitoBeanBD.get(pesqArrayList);
        pesqArrayList.clear();

        ArrayList<Long> idAmostraList = new ArrayList<Long>();
        for (ROrganCaracAmosFitoBean rOrganCaracAmosFitoBean : rOrganCaracList) {
            idAmostraList.add(rOrganCaracAmosFitoBean.getIdCaracOrgan());
        }

        return idAmostraList;

    }

}
