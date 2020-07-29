package br.com.usinasantafe.pru.control;

import java.util.List;

import br.com.usinasantafe.pru.model.bean.estaticas.EquipBean;
import br.com.usinasantafe.pru.model.bean.estaticas.FuncBean;
import br.com.usinasantafe.pru.model.bean.estaticas.OSBean;
import br.com.usinasantafe.pru.model.bean.estaticas.TurnoBean;
import br.com.usinasantafe.pru.model.bean.variaveis.AmostraPerdaBean;
import br.com.usinasantafe.pru.model.bean.variaveis.AmostraSoqueiraBean;
import br.com.usinasantafe.pru.model.bean.variaveis.CabecPerdaBean;
import br.com.usinasantafe.pru.model.bean.variaveis.CabecSoqueiraBean;
import br.com.usinasantafe.pru.model.dao.AmostraPerdaDAO;
import br.com.usinasantafe.pru.model.dao.AmostraSoqueiraDAO;
import br.com.usinasantafe.pru.model.dao.CabecPerdaDAO;
import br.com.usinasantafe.pru.model.dao.CabecSoqueiraDAO;
import br.com.usinasantafe.pru.model.dao.EquipDAO;
import br.com.usinasantafe.pru.model.dao.FuncDAO;
import br.com.usinasantafe.pru.model.dao.OSDAO;
import br.com.usinasantafe.pru.model.dao.TurnoDAO;

public class SoqueiraCTR {

    private AmostraSoqueiraBean amostraSoqueiraBean;

    public SoqueiraCTR() {
    }

    public void salvarCabecSoqueiraAberto(Long codEquip){

        RuricolaCTR ruricolaCTR = new RuricolaCTR();
        CabecSoqueiraDAO cabecPerdaDAO = new CabecSoqueiraDAO();
        CabecSoqueiraBean cabecSoqueiraBean = new CabecSoqueiraBean();
        cabecSoqueiraBean.setAuditorCabecSoqueira(ruricolaCTR.getFunc().getMatricFunc());
        cabecSoqueiraBean.setOsCabecSoqueira(ruricolaCTR.getBolAberto().getOsBol());
        cabecSoqueiraBean.setEquipCabecSoqueira(codEquip);
        cabecPerdaDAO.salvarCabecSoqueiraAberto(cabecSoqueiraBean);

        ConfigCTR configCTR = new ConfigCTR();
        configCTR.setPontoAmostraConfig(0L);

    }

    public void salvarAmostraSoqueira(){
        AmostraSoqueiraDAO amostraSoqueiraDAO = new AmostraSoqueiraDAO();
        amostraSoqueiraDAO.salvarAmostraSoqueira(amostraSoqueiraBean);
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

    public AmostraSoqueiraBean getAmostraSoqueiraBean() {
        return amostraSoqueiraBean;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////// SET DE CAMPOS ///////////////////////////////////////////

    public void setAmostraSoqueiraBean(AmostraSoqueiraBean amostraSoqueiraBean) {
        this.amostraSoqueiraBean = amostraSoqueiraBean;
    }

    public void setQuestaoAmostraSoqueira(Long valor, int posQuestao, Long seqAmostraPerda){
        AmostraPerdaDAO amostraPerdaDAO = new AmostraPerdaDAO();
        amostraPerdaDAO.setQuestaoAmostraPerda(valor, posQuestao, seqAmostraPerda);
    }

    /////////////////////////////////////////////////////////////////////////////////////////////


}
