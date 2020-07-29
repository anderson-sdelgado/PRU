package br.com.usinasantafe.pru.model.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pru.model.bean.estaticas.ROrganCaracAmosBean;
import br.com.usinasantafe.pru.model.bean.variaveis.AmostraPerdaBean;
import br.com.usinasantafe.pru.model.pst.EspecificaPesquisa;

public class AmostraPerdaDAO {

    public AmostraPerdaDAO() {
    }

    public void salvarAmostraPerda(AmostraPerdaBean amostraPerdaBean){
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

    public void setQuestaoAmostraPerda(Double valor, int posQuestao, Long seqAmostraPerda){
        AmostraPerdaBean amostraPerdaBean = getAmostraPerda(seqAmostraPerda);
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

    public void setQuestaoAmostraPerda(Long pedra, Long tocoArvore, Long plantaDaninha, Long formigueiro, Long seqAmostraPerda){
        AmostraPerdaBean amostraPerdaBean = getAmostraPerda(seqAmostraPerda);
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


}
