package br.com.usinasantafe.pru.model.dao;

import java.util.List;

import br.com.usinasantafe.pru.model.bean.estaticas.TurnoBean;

public class TurnoDAO  {

    public TurnoDAO() {
    }

    public TurnoBean getTurno(Long idTurno){
        List<TurnoBean> turnoList = getTurnoList(idTurno);
        TurnoBean turnoBean = (TurnoBean) turnoList.get(0);
        turnoList.clear();
        return turnoBean;
    }

    public List<TurnoBean> getTurnoList(){
        TurnoBean turnoBean = new TurnoBean();
        return turnoBean.all();
    }

    private List getTurnoList(Long idTurno){
        TurnoBean turnoBean = new TurnoBean();
        return turnoBean.get("idTurno", idTurno);
    }

}
