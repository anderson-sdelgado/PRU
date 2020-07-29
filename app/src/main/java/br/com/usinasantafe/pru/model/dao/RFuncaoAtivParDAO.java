package br.com.usinasantafe.pru.model.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pru.model.bean.estaticas.RFuncaoAtivParBean;
import br.com.usinasantafe.pru.model.pst.EspecificaPesquisa;

public class RFuncaoAtivParDAO {

    public RFuncaoAtivParDAO() {
    }

    public RFuncaoAtivParBean getRFuncaoAtivPar(Long idAtiv){
        List listFuncaoAtiv = getListFuncaoAtividade(idAtiv);
        RFuncaoAtivParBean rFuncaoAtivParBean = new RFuncaoAtivParBean();
        if(listFuncaoAtiv.size() > 0){
            rFuncaoAtivParBean = (RFuncaoAtivParBean) listFuncaoAtiv.get(0);
        }
        else{
            rFuncaoAtivParBean.setCodFuncao(0L);
        }
        listFuncaoAtiv.clear();
        return rFuncaoAtivParBean;
    }

    private List getListFuncaoAtividade(Long idAtiv){

        RFuncaoAtivParBean rFuncaoAtivParBean = new RFuncaoAtivParBean();
        ArrayList pesqList = new ArrayList();

        EspecificaPesquisa pesquisa1 = new EspecificaPesquisa();
        pesquisa1.setCampo("idAtivPar");
        pesquisa1.setValor(idAtiv);
        pesquisa1.setTipo(1);
        pesqList.add(pesquisa1);

        EspecificaPesquisa pesquisa2 = new EspecificaPesquisa();
        pesquisa2.setCampo("tipoFuncao");
        pesquisa2.setValor(1L);
        pesquisa2.setTipo(1);
        pesqList.add(pesquisa2);

        return rFuncaoAtivParBean.get(pesqList);
    }

}
