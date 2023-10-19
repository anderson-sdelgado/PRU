package br.com.usinasantafe.pru.model.dao;

import java.util.List;

import br.com.usinasantafe.pru.model.bean.estaticas.TurmaBean;

public class TurmaDAO {

    public TurmaDAO() {
    }

    public boolean hasTurma(){
        TurmaBean turmaBean = new TurmaBean();
        return turmaBean.hasElements();
    }

    public TurmaBean getTurma(Long idTurma){
        TurmaBean turmaBean = new TurmaBean();
        List<TurmaBean> turmaList = turmaBean.get("idTurma", idTurma);
        turmaBean = turmaList.get(0);
        turmaList.clear();
        return  turmaBean;
    }

    public List<TurmaBean> allTurma(){
        TurmaBean turmaBean = new TurmaBean();
        return turmaBean.orderBy("codTurma", true);
    }

}
