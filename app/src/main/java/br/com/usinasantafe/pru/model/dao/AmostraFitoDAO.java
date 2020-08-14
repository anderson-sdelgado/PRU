package br.com.usinasantafe.pru.model.dao;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pru.model.bean.estaticas.AmostraFitoBean;
import br.com.usinasantafe.pru.model.bean.estaticas.ROCAFitoBean;
import br.com.usinasantafe.pru.model.pst.EspecificaPesquisa;

public class AmostraFitoDAO {

    public AmostraFitoDAO() {
    }

    public AmostraFitoBean getAmostra(Long idAmostra){
        List<AmostraFitoBean> amostraList = amostraCabecList(idAmostra);
        AmostraFitoBean amostraFitoBean = amostraList.get(0);
        amostraList.clear();
        return amostraFitoBean;
    }

    public boolean verAmostra(Long idOrgan, Long idCaracOrgan){
        List<AmostraFitoBean> amostraList = amostraList(idOrgan, idCaracOrgan);
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

    public List<AmostraFitoBean> amostraList(Long idOrgan, Long idCaracOrgan){
        ArrayList<Long> idAmostraList = getIdAmostraList(idOrgan, idCaracOrgan);
        List<AmostraFitoBean> amostraList = inAmostraOrgan(idAmostraList);
        idAmostraList.clear();
        return amostraList;
    }

    public List<AmostraFitoBean> amostraCabecList(Long idOrgan, Long idCaracOrgan){
        ArrayList<Long> idAmostraList = getIdAmostraList(idOrgan, idCaracOrgan);
        List<AmostraFitoBean> amostraList = inAndGetAmostraCabec(idAmostraList);
        idAmostraList.clear();
        return amostraList;
    }

    private List<AmostraFitoBean> amostraCabecList(Long idAmostra){
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

        ROCAFitoBean ROCAFitoBeanBD = new ROCAFitoBean();
        List<ROCAFitoBean> rOrganCaracList = ROCAFitoBeanBD.get(pesqArrayList);
        pesqArrayList.clear();

        ArrayList<Long> idAmostraList = new ArrayList<Long>();
        Log.i("PRU", "CHEGOU AKI");
        for (ROCAFitoBean rOCAFitoBean : rOrganCaracList) {
            Log.i("PRU", "rOCAFitoBean.getIdAmostraOrgan() = " + rOCAFitoBean.getIdAmostraOrgan());
            idAmostraList.add(rOCAFitoBean.getIdAmostraOrgan());
        }

        return idAmostraList;

    }

}
