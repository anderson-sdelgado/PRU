package br.com.usinasantafe.pru.model.dao;

import java.util.List;

import br.com.usinasantafe.pru.model.bean.estaticas.TipoApontBean;

public class TipoApontDAO {

    public TipoApontDAO() {
    }

    public TipoApontBean getTipoApont(Long idTipo){
        TipoApontBean tipoApontBean = new TipoApontBean();
        List<TipoApontBean> tipoApontList = tipoApontBean.get("idTipo", idTipo);
        tipoApontBean = (TipoApontBean) tipoApontList.get(0);
        tipoApontList.clear();
        return  tipoApontBean;
    }

    public boolean hasElementsTipoApont(){
        return new TipoApontBean().hasElements();
    }

    public List<TipoApontBean> allTipoApont(){
        TipoApontBean tipoApontBean = new TipoApontBean();
        return tipoApontBean.all();
    }

}
