package br.com.usinasantafe.pru.model.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pru.model.bean.estaticas.AmostraBean;
import br.com.usinasantafe.pru.model.bean.estaticas.ROrganCaracAmosBean;
import br.com.usinasantafe.pru.model.pst.EspecificaPesquisa;

public class AmostraFitoDAO {

    public AmostraFitoDAO() {
    }

    public AmostraBean getAmostra(Long idAmostra){
        List<AmostraBean> amostraList = amostraList(idAmostra);
        AmostraBean amostraBean = amostraList.get(0);
        amostraList.clear();
        return amostraBean;
    }

    public boolean verAmostra(Long idOrgan, Long idCaracOrgan){
        List<AmostraBean> amostraList = getAmostraOrganList(idOrgan, idCaracOrgan);
        boolean ret = amostraList.size() > 0;
        amostraList.clear();
        return ret;
    }

    public boolean hasAmostraCabec(Long idOrgan, Long idCaracOrgan){
        List<AmostraBean> amostraList = amostraCabecList(idOrgan, idCaracOrgan);
        boolean ret = amostraList.size() > 0;
        amostraList.clear();
        return ret;
    }

    private List<AmostraBean> getAmostraOrganList(Long idOrgan, Long idCaracOrgan){
        ArrayList<Long> idAmostraList = getIdAmostraList(idOrgan, idCaracOrgan);
        List amostraList = inAmostraOrgan(idAmostraList);
        idAmostraList.clear();
        return amostraList;
    }

    public List<AmostraBean> amostraCabecList(Long idOrgan, Long idCaracOrgan){
        ArrayList<Long> idAmostraList = getIdAmostraList(idOrgan, idCaracOrgan);
        List<AmostraBean> amostraList = inAndGetAmostraCabec(idAmostraList);
        idAmostraList.clear();
        return amostraList;
    }

    private List<AmostraBean> amostraList(Long idAmostra){
        AmostraBean amostraBean = new AmostraBean();
        return amostraBean.get("idAmostra", idAmostra);
    }

    private List<AmostraBean> inAmostraOrgan(ArrayList<Long> idAmostraOrganList){
        AmostraBean amostraBean = new AmostraBean();
        return amostraBean.in("idAmostraOrgan", idAmostraOrganList);
    }

    private List<AmostraBean> inAndGetAmostraCabec(ArrayList<Long> idAmostraList){
        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqTipoAmostraQC());
        AmostraBean amostraBean = new AmostraBean();
        return amostraBean.inAndGet("idAmostraOrgan", idAmostraList, pesqArrayList);
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
        ROrganCaracAmosBean rOrganCaracAmosBeanBD = new ROrganCaracAmosBean();
        List<ROrganCaracAmosBean> rOrganCaracList = rOrganCaracAmosBeanBD.get(pesqArrayList);
        pesqArrayList.clear();

        ArrayList<Long> idAmostraList = new ArrayList<Long>();
        for (ROrganCaracAmosBean rOrganCaracAmosBean : rOrganCaracList) {
            idAmostraList.add(rOrganCaracAmosBean.getIdCaracOrgan());
        }

        return idAmostraList;

    }

}
