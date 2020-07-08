package br.com.usinasantafe.pru.model.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pru.model.bean.estaticas.TalhaoBean;
import br.com.usinasantafe.pru.model.pst.EspecificaPesquisa;

public class TalaoDAO {

    public TalaoDAO() {
    }

    public boolean verTalhao(Long codTalhao, Long idSecao){

        ArrayList pesqList = new ArrayList();

        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("idSecao");
        pesquisa.setValor(idSecao);
        pesqList.add(pesquisa);

        EspecificaPesquisa pesquisa2 = new EspecificaPesquisa();
        pesquisa2.setCampo("codTalhao");
        pesquisa2.setValor(codTalhao);
        pesqList.add(pesquisa2);

        TalhaoBean talhaoBean = new TalhaoBean();
        List talhaoList = talhaoBean.get(pesqList);
        boolean ret = talhaoList.size() > 0;
        talhaoList.clear();

        return  ret;

    }

}
