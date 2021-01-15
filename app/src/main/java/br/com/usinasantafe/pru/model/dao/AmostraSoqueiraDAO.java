package br.com.usinasantafe.pru.model.dao;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pru.model.bean.variaveis.AmostraPerdaBean;
import br.com.usinasantafe.pru.model.bean.variaveis.AmostraSoqueiraBean;
import br.com.usinasantafe.pru.model.pst.EspecificaPesquisa;

public class AmostraSoqueiraDAO {

    public AmostraSoqueiraDAO() {
    }

    public void salvarAmostraSoqueira(AmostraSoqueiraBean amostraSoqueiraBean, Long seqAmostraSoqueira, Long idCabecSoqueira){
        amostraSoqueiraBean.setIdCabecAmostraSoqueira(idCabecSoqueira);
        amostraSoqueiraBean.setSeqAmostraSoqueira(seqAmostraSoqueira);
        amostraSoqueiraBean.insert();
    }

    public void setQuestaoAmostraSoqueira(Long valor, int posQuestao, Long seqAmostraSoqueira){
        AmostraSoqueiraBean amostraSoqueiraBean = getAmostraSoqueira(seqAmostraSoqueira);
        if (posQuestao == 1) {
            amostraSoqueiraBean.setQtdeSoqueira(valor);
        } else if (posQuestao == 2) {
            amostraSoqueiraBean.setQtdeArranquio(valor);
        }
        amostraSoqueiraBean.update();
    }

    public AmostraSoqueiraBean getAmostraSoqueira(Long idCabecSoqueira, Long seqAmostraSoqueira){

        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqIdCabecSoqueira(idCabecSoqueira));
        pesqArrayList.add(getPesqSeqAmostraSoqueira(seqAmostraSoqueira));
        AmostraSoqueiraBean amostraSoqueiraBean = new AmostraSoqueiraBean();
        List<AmostraSoqueiraBean> amostraSoqueiraList = amostraSoqueiraBean.get(pesqArrayList);
        pesqArrayList.clear();

        amostraSoqueiraBean = (AmostraSoqueiraBean) amostraSoqueiraList.get(0);
        amostraSoqueiraList.clear();

        return amostraSoqueiraBean;

    }

    public void delAmostraSoqueira(Long idCabecSoqueira, Long seqAmostraSoqueira){

        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqIdCabecSoqueira(idCabecSoqueira));
        pesqArrayList.add(getPesqSeqAmostraSoqueira(seqAmostraSoqueira));
        AmostraPerdaBean amostraPerdaBean = new AmostraPerdaBean();
        List<AmostraPerdaBean> amostraPerdaBeanList = amostraPerdaBean.get(pesqArrayList);
        pesqArrayList.clear();

        amostraPerdaBean = (AmostraPerdaBean) amostraPerdaBeanList.get(0);
        amostraPerdaBean.delete();
        amostraPerdaBeanList.clear();

    }

    public void delAmostraSoqueira(Long idCabecSoqueira){

        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqIdCabecSoqueira(idCabecSoqueira));
        AmostraSoqueiraBean amostraSoqueiraBean = new AmostraSoqueiraBean();
        List<AmostraSoqueiraBean> amostraSoqueiraBeanList = amostraSoqueiraBean.get(pesqArrayList);
        pesqArrayList.clear();

        amostraSoqueiraBean = amostraSoqueiraBeanList.get(0);
        amostraSoqueiraBean.delete();
        amostraSoqueiraBeanList.clear();

    }

    public AmostraSoqueiraBean getAmostraSoqueira(Long idCabecSoqueira){
        List<AmostraSoqueiraBean> amostraList = amostraSoqueiraList(idCabecSoqueira);
        AmostraSoqueiraBean amostraBean = amostraList.get(0);
        amostraList.clear();
        return amostraBean;
    }

    private List<AmostraSoqueiraBean> amostraSoqueiraList(Long idCabecSoqueira){
        AmostraSoqueiraBean amostraSoqueiraBean = new AmostraSoqueiraBean();
        return amostraSoqueiraBean.get("idCabecAmostraSoqueira", idCabecSoqueira);
    }

    private EspecificaPesquisa getPesqIdCabecSoqueira(Long idCabecSoqueira){
        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("idCabecAmostraSoqueira");
        pesquisa.setValor(idCabecSoqueira);
        pesquisa.setTipo(1);
        return pesquisa;
    }

    private EspecificaPesquisa getPesqSeqAmostraSoqueira(Long seqAmostraSoqueira){
        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("seqAmostraSoqueira");
        pesquisa.setValor(seqAmostraSoqueira);
        pesquisa.setTipo(1);
        return pesquisa;
    }

    public List getListAmostraEnvio(ArrayList<Long> idCabecList){
        AmostraSoqueiraBean amostraPerdaBean = new AmostraSoqueiraBean();
        return amostraPerdaBean.in("idCabecAmostraSoqueira", idCabecList);
    }

    public List getListAmostra(Long idCabec){
        AmostraPerdaBean amostraPerdaBean = new AmostraPerdaBean();
        return amostraPerdaBean.get("idCabecAmostraSoqueira", idCabec);
    }

    public void delListAmostra(List amostraList){
        for(int i = 0; i < amostraList.size(); i++) {
            AmostraPerdaBean amostraPerdaBean = (AmostraPerdaBean) amostraList.get(i);
            amostraPerdaBean.delete();
        }
    }

    public String dadosEnvioAmostra(List amostraList){

        JsonArray jsonArrayAmostra = new JsonArray();

        for (int i = 0; i < amostraList.size(); i++) {

            AmostraSoqueiraBean amostraSoqueiraBean = (AmostraSoqueiraBean) amostraList.get(i);
            Gson gson = new Gson();
            jsonArrayAmostra.add(gson.toJsonTree(amostraSoqueiraBean, amostraSoqueiraBean.getClass()));

        }

        amostraList.clear();

        JsonObject jsonAmostra = new JsonObject();
        jsonAmostra.add("amostra", jsonArrayAmostra);

        return jsonAmostra.toString();

    }

}
