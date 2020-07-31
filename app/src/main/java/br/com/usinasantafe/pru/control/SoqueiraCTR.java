package br.com.usinasantafe.pru.control;

import java.util.List;

import br.com.usinasantafe.pru.model.bean.estaticas.EquipBean;
import br.com.usinasantafe.pru.model.bean.estaticas.FuncBean;
import br.com.usinasantafe.pru.model.bean.estaticas.OSBean;
import br.com.usinasantafe.pru.model.bean.variaveis.AmostraSoqueiraBean;
import br.com.usinasantafe.pru.model.bean.variaveis.CabecSoqueiraBean;
import br.com.usinasantafe.pru.model.dao.AmostraSoqueiraDAO;
import br.com.usinasantafe.pru.model.dao.CabecSoqueiraDAO;
import br.com.usinasantafe.pru.model.dao.EquipDAO;
import br.com.usinasantafe.pru.model.dao.FuncDAO;
import br.com.usinasantafe.pru.model.dao.OSDAO;

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

    public void salvarAmostraSoqueira(Long seqAmostraSoqueira){
        AmostraSoqueiraDAO amostraSoqueiraDAO = new AmostraSoqueiraDAO();
        amostraSoqueiraDAO.salvarAmostraSoqueira(amostraSoqueiraBean, seqAmostraSoqueira);
    }

    public void delAmostraSoqueira(Long seqAmostraSoqueira){
        AmostraSoqueiraDAO amostraSoqueiraDAO = new AmostraSoqueiraDAO();
        amostraSoqueiraDAO.delAmostraSoqueira(getCabecSoqueiraAberto().getIdCabecSoqueira(), seqAmostraSoqueira);
    }

    ////////////////////////////////// VERIFICAR CAMPOS ///////////////////////////////////////////

    public boolean verEquip(Long codEquip){
        EquipDAO equipDAO = new EquipDAO();
        return equipDAO.verEquip(codEquip);
    }

    public boolean hasCabecSoqueiraAberto(){
        CabecSoqueiraDAO cabecSoqueiraDAO = new CabecSoqueiraDAO();
        return cabecSoqueiraDAO.hasCabecSoqueiraAberto();
    }

    /////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////// GET DE CAMPOS ///////////////////////////////////////////

    public List<CabecSoqueiraBean> getCabecSoqueiraAbertoList(){
        CabecSoqueiraDAO cabecSoqueiraDAO = new CabecSoqueiraDAO();
        return cabecSoqueiraDAO.getCabecSoqueiraAbertoList();
    }

    public CabecSoqueiraBean getCabecSoqueiraAberto(){
        CabecSoqueiraDAO cabecSoqueiraDAO = new CabecSoqueiraDAO();
        return cabecSoqueiraDAO.getCabecSoqueiraAberto();
    }

    public EquipBean getEquip(){
        EquipDAO equipDAO = new EquipDAO();
        return equipDAO.getEquip(getCabecSoqueiraAberto().getEquipCabecSoqueira());
    }

    public FuncBean getFunc(){
        FuncDAO funcDAO = new FuncDAO();
        return funcDAO.getFunc(getCabecSoqueiraAberto().getAuditorCabecSoqueira());
    }

    public OSBean getOS(){
        OSDAO osDAO = new OSDAO();
        return osDAO.getOS(getCabecSoqueiraAberto().getOsCabecSoqueira());
    }

    public AmostraSoqueiraBean getAmostraSoqueiraBean() {
        return amostraSoqueiraBean;
    }

    public AmostraSoqueiraBean getAmostraSoqueira(Long seqAmostraSoqueira){
        AmostraSoqueiraDAO amostraSoqueiraDAO = new AmostraSoqueiraDAO();
        return amostraSoqueiraDAO.getAmostraSoqueira(getCabecSoqueiraAberto().getIdCabecSoqueira(), seqAmostraSoqueira);
    }

    /////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////// SET DE CAMPOS ///////////////////////////////////////////

    public void setAmostraSoqueira(AmostraSoqueiraBean amostraSoqueiraBean) {
        this.amostraSoqueiraBean = amostraSoqueiraBean;
    }

    public void setQuestaoAmostraSoqueira(Long valor, int posQuestao, Long seqAmostraSoqueira){
        AmostraSoqueiraDAO amostraSoqueiraDAO = new AmostraSoqueiraDAO();
        amostraSoqueiraDAO.setQuestaoAmostraSoqueira(valor, posQuestao, seqAmostraSoqueira);
    }

    /////////////////////////////////////////////////////////////////////////////////////////////


}
