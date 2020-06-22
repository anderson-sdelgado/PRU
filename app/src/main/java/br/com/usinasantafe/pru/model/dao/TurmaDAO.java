package br.com.usinasantafe.pru.model.dao;

import java.util.List;

import br.com.usinasantafe.pru.model.bean.estaticas.TurmaBean;

public class TurmaDAO {

    public TurmaDAO() {
    }

    public TurmaBean getTurma(Long idTurma){
        TurmaBean turmaBean = new TurmaBean();
        List turmaList = turmaBean.get("idTurma", idTurma);
        turmaBean = (TurmaBean) turmaList.get(0);
        turmaList.clear();
        return  turmaBean;
    }

    public List allTurma(){
        TurmaBean turmaBean = new TurmaBean();
        return turmaBean.orderBy("codTurma", true);
    }

}
