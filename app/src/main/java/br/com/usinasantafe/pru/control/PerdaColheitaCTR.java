package br.com.usinasantafe.pru.control;

import java.util.List;

import br.com.usinasantafe.pru.model.bean.estaticas.EquipBean;
import br.com.usinasantafe.pru.model.bean.estaticas.FuncBean;
import br.com.usinasantafe.pru.model.bean.estaticas.OSBean;
import br.com.usinasantafe.pru.model.bean.estaticas.TurnoBean;
import br.com.usinasantafe.pru.model.bean.variaveis.AmostraPerdaBean;
import br.com.usinasantafe.pru.model.bean.variaveis.CabecPerdaBean;
import br.com.usinasantafe.pru.model.dao.AmostraPerdaDAO;
import br.com.usinasantafe.pru.model.dao.CabecPerdaDAO;
import br.com.usinasantafe.pru.model.dao.EquipDAO;
import br.com.usinasantafe.pru.model.dao.FuncDAO;
import br.com.usinasantafe.pru.model.dao.OSDAO;
import br.com.usinasantafe.pru.model.dao.TurnoDAO;

public class PerdaColheitaCTR {

    private AmostraPerdaBean amostraPerdaBean;

    public PerdaColheitaCTR() {
    }

    public void salvarCabecPerdaAberto(Long codEquip){
        RuricolaCTR ruricolaCTR = new RuricolaCTR();
        CabecPerdaDAO cabecPerdaDAO = new CabecPerdaDAO();
        CabecPerdaBean cabecPerdaBean = new CabecPerdaBean();
        cabecPerdaBean.setAuditorCabecPerda(ruricolaCTR.getFunc().getMatricFunc());
        cabecPerdaBean.setOsCabecPerda(ruricolaCTR.getBolAberto().getOsBol());
        cabecPerdaBean.setEquipCabecPerda(codEquip);
        cabecPerdaBean.setTipoColheitaCabecPerda(1L);
        cabecPerdaDAO.salvarCabecPerdaAberto(cabecPerdaBean);

        ConfigCTR configCTR = new ConfigCTR();
        configCTR.setAmostraConfig(0L);

    }

    public void salvarAmostraPerda(){
        AmostraPerdaDAO amostraPerdaDAO = new AmostraPerdaDAO();
        amostraPerdaDAO.salvarAmostraPerda(amostraPerdaBean);
    }

    ////////////////////////////////// VERIFICAR CAMPOS ///////////////////////////////////////////

    public boolean verEquip(Long codEquip){
        EquipDAO equipDAO = new EquipDAO();
        return equipDAO.verEquip(codEquip);
    }

    public boolean hasCabecPerdaAberto(){
        CabecPerdaDAO cabecPerdaDAO = new CabecPerdaDAO();
        return cabecPerdaDAO.hasCabecPerdaAberto();
    }

    /////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////// GET DE CAMPOS ///////////////////////////////////////////

    public List<CabecPerdaBean> getCabecPerdaAbertoList(){
        CabecPerdaDAO cabecPerdaDAO = new CabecPerdaDAO();
        return cabecPerdaDAO.getCabecPerdaAbertoList();
    }

    public CabecPerdaBean getCabecPerdaAberto(){
        CabecPerdaDAO cabecPerdaDAO = new CabecPerdaDAO();
        return cabecPerdaDAO.getCabecPerdaAberto();
    }

    public EquipBean getEquip(){
        EquipDAO equipDAO = new EquipDAO();
        return equipDAO.getEquip(getCabecPerdaAberto().getEquipCabecPerda());
    }

    public FuncBean getFunc(){
        FuncDAO funcDAO = new FuncDAO();
        return funcDAO.getFunc(getCabecPerdaAberto().getAuditorCabecPerda());
    }

    public OSBean getOS(){
        OSDAO osDAO = new OSDAO();
        return osDAO.getOS(getCabecPerdaAberto().getOsCabecPerda());
    }

    public TurnoBean getTurno(){
        TurnoDAO turnoDAO = new TurnoDAO();
        return turnoDAO.getTurno(getCabecPerdaAberto().getTurnoCabecPerda());
    }

    public AmostraPerdaBean getAmostraPerdaBean() {
        return amostraPerdaBean;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////// GET DE CAMPOS ///////////////////////////////////////////

    public void setAmostraPerdaBean(AmostraPerdaBean amostraPerdaBean) {
        this.amostraPerdaBean = amostraPerdaBean;
    }


    /////////////////////////////////////////////////////////////////////////////////////////////

}
