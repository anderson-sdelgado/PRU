package br.com.usinasantafe.pru.model.dao;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pru.model.bean.variaveis.AmostraPerdaBean;
import br.com.usinasantafe.pru.model.bean.variaveis.RespFitoBean;
import br.com.usinasantafe.pru.model.pst.EspecificaPesquisa;

public class AmostraPerdaDAO {

    public AmostraPerdaDAO() {
    }

    public void salvarAmostraPerda(AmostraPerdaBean amostraPerdaBean, Long seqAmostraPerda, Long idCabecPerda){
        amostraPerdaBean.setIdCabecAmostraPerda(idCabecPerda);
        amostraPerdaBean.setSeqAmostraPerda(seqAmostraPerda);
        amostraPerdaBean.insert();
    }

    public AmostraPerdaBean getAmostraPerda(Long idCabecPerda, Long seqAmostraPerda){

        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqIdCabecPerda(idCabecPerda));
        pesqArrayList.add(getPesqSeqAmostraPerda(seqAmostraPerda));
        AmostraPerdaBean amostraPerdaBean = new AmostraPerdaBean();
        List<AmostraPerdaBean> amostraPerdaBeanList = amostraPerdaBean.get(pesqArrayList);
        pesqArrayList.clear();

        amostraPerdaBean = (AmostraPerdaBean) amostraPerdaBeanList.get(0);
        amostraPerdaBeanList.clear();

        return amostraPerdaBean;

    }

    public void setQuestaoAmostraPerda(Double valor, int posQuestao, Long seqAmostraPerda, Long idCabecPerda){
        AmostraPerdaBean amostraPerdaBean = getAmostraPerda(idCabecPerda, seqAmostraPerda);
        if (posQuestao == 1) {
            amostraPerdaBean.setTaraAmostraPerda(valor);
        } else if (posQuestao == 2) {
            amostraPerdaBean.setToleteAmostraPerda(valor);
        } else if (posQuestao == 3) {
            amostraPerdaBean.setCanaInteiraAmostraPerda(valor);
        } else if (posQuestao == 4) {
            amostraPerdaBean.setTocoAmostraPerda(valor);
        } else if (posQuestao == 5) {
            amostraPerdaBean.setPedacoAmostraPerda(valor);
        } else if (posQuestao == 6) {
            amostraPerdaBean.setRepiqueAmostraPerda(valor);
        } else if (posQuestao == 7) {
            amostraPerdaBean.setPonteiroAmostraPerda(valor);
        } else if (posQuestao == 8) {
            amostraPerdaBean.setLascasAmostraPerda(valor);
        }
        amostraPerdaBean.update();
    }

    public void setQuestaoAmostraPerda(Long pedra, Long tocoArvore, Long plantaDaninha, Long formigueiro, Long seqAmostraPerda, Long idCabecPerda){
        AmostraPerdaBean amostraPerdaBean = getAmostraPerda(idCabecPerda, seqAmostraPerda);
        amostraPerdaBean.setPedraAmostraPerda(pedra);
        amostraPerdaBean.setTocoArvoreAmostraPerda(tocoArvore);
        amostraPerdaBean.setPlantaDaninhasAmostraPerda(plantaDaninha);
        amostraPerdaBean.setFormigueiroAmostraPerda(formigueiro);
        amostraPerdaBean.update();
    }

    public void delAmostraPerda(Long idCabecPerda, Long seqAmostraPerda){

        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqIdCabecPerda(idCabecPerda));
        pesqArrayList.add(getPesqSeqAmostraPerda(seqAmostraPerda));
        AmostraPerdaBean amostraPerdaBean = new AmostraPerdaBean();
        List<AmostraPerdaBean> amostraPerdaBeanList = amostraPerdaBean.get(pesqArrayList);
        pesqArrayList.clear();

        amostraPerdaBean = (AmostraPerdaBean) amostraPerdaBeanList.get(0);
        amostraPerdaBean.delete();
        amostraPerdaBeanList.clear();

    }

    public void delAmostraPerda(Long idCabecPerda){

        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqIdCabecPerda(idCabecPerda));
        AmostraPerdaBean amostraPerdaBean = new AmostraPerdaBean();
        List<AmostraPerdaBean> amostraPerdaBeanList = amostraPerdaBean.get(pesqArrayList);
        pesqArrayList.clear();

        amostraPerdaBean = (AmostraPerdaBean) amostraPerdaBeanList.get(0);
        amostraPerdaBean.delete();
        amostraPerdaBeanList.clear();

    }

    public boolean verAmostraPerda(Long idCabecPerda){
        List<AmostraPerdaBean> amostraList = amostraPerdaList(idCabecPerda);
        boolean ret = amostraList.size() > 0;
        amostraList.clear();
        return ret;
    }

    public int qtdeAmostraPerda(Long idCabecPerda){
        List<AmostraPerdaBean> amostraList = amostraPerdaList(idCabecPerda);
        int ret = amostraList.size() + 1;
        amostraList.clear();
        return ret;
    }

    public AmostraPerdaBean getAmostraPerda(Long idCabecPerda){
        List<AmostraPerdaBean> amostraList = amostraPerdaList(idCabecPerda);
        AmostraPerdaBean amostraBean = amostraList.get(0);
        amostraList.clear();
        return amostraBean;
    }

    private List<AmostraPerdaBean> amostraPerdaList(Long idCabecPerda){
        AmostraPerdaBean amostraPerdaBean = new AmostraPerdaBean();
        return amostraPerdaBean.get("idCabecAmostraPerda", idCabecPerda);
    }

    private EspecificaPesquisa getPesqIdCabecPerda(Long idCabecPerda){
        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("idCabecAmostraPerda");
        pesquisa.setValor(idCabecPerda);
        pesquisa.setTipo(1);
        return pesquisa;
    }

    private EspecificaPesquisa getPesqSeqAmostraPerda(Long seqAmostraPerda){
        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("seqAmostraPerda");
        pesquisa.setValor(seqAmostraPerda);
        pesquisa.setTipo(1);
        return pesquisa;
    }

    public List getListAmostraEnvio(ArrayList<Long> idCabecList){
        AmostraPerdaBean amostraPerdaBean = new AmostraPerdaBean();
        return amostraPerdaBean.in("idCabecAmostraPerda", idCabecList);
    }

    public List getListAmostra(Long idCabec){
        AmostraPerdaBean amostraPerdaBean = new AmostraPerdaBean();
        return amostraPerdaBean.get("idCabecAmostraPerda", idCabec);
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

            AmostraPerdaBean amostraPerdaBean = (AmostraPerdaBean) amostraList.get(i);
            Gson gson = new Gson();
            jsonArrayAmostra.add(gson.toJsonTree(amostraPerdaBean, amostraPerdaBean.getClass()));

        }

        amostraList.clear();

        JsonObject jsonAmostra = new JsonObject();
        jsonAmostra.add("amostra", jsonArrayAmostra);

        return jsonAmostra.toString();

    }

}
