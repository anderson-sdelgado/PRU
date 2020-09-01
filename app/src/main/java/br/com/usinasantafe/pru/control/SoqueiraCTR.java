package br.com.usinasantafe.pru.control;

import java.util.ArrayList;
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
        CabecSoqueiraBean cabecSoqueiraBean = new CabecSoqueiraBean();
        cabecSoqueiraBean.setAuditorCabecSoqueira(ruricolaCTR.getFunc().getMatricFunc());
        cabecSoqueiraBean.setOsCabecSoqueira(ruricolaCTR.getBolAberto().getOsBol());
        cabecSoqueiraBean.setEquipCabecSoqueira(codEquip);

        deleteEnviados();

        CabecSoqueiraDAO cabecPerdaDAO = new CabecSoqueiraDAO();
        cabecPerdaDAO.salvarCabecSoqueiraAberto(cabecSoqueiraBean);

        ConfigCTR configCTR = new ConfigCTR();
        configCTR.setPontoAmostraConfig(0L);

    }

    public void salvarAmostraSoqueira(Long seqAmostraSoqueira){
        AmostraSoqueiraDAO amostraSoqueiraDAO = new AmostraSoqueiraDAO();
        amostraSoqueiraDAO.salvarAmostraSoqueira(amostraSoqueiraBean, seqAmostraSoqueira, getCabecSoqueiraAberto().getIdCabecSoqueira());

        ConfigCTR configCTR = new ConfigCTR();
        configCTR.setPontoAmostraConfig(seqAmostraSoqueira);

    }

    public void delAmostraSoqueira(Long seqAmostraSoqueira){
        AmostraSoqueiraDAO amostraSoqueiraDAO = new AmostraSoqueiraDAO();
        amostraSoqueiraDAO.delAmostraSoqueira(getCabecSoqueiraAberto().getIdCabecSoqueira(), seqAmostraSoqueira);
    }

    public void delSoqueira(){
        CabecSoqueiraDAO cabecSoqueiraDAO = new CabecSoqueiraDAO();
        AmostraSoqueiraDAO amostraSoqueiraDAO = new AmostraSoqueiraDAO();
        Long idCabec = cabecSoqueiraDAO.getCabecSoqueiraAberto().getIdCabecSoqueira();
        amostraSoqueiraDAO.delAmostraSoqueira(idCabec);
        cabecSoqueiraDAO.delCabecSoqueira(idCabec);
    }

    public void fecharCabecSoqueira(){
        CabecSoqueiraDAO cabecSoqueiraDAO = new CabecSoqueiraDAO();
        cabecSoqueiraDAO.fecharCabecSoqueira();
    }

    ////////////////////////////////// VERIFICAR CAMPOS ///////////////////////////////////////////

    public boolean verEquip(Long codEquip){
        EquipDAO equipDAO = new EquipDAO();
        return equipDAO.verEquip(codEquip);
    }

    public boolean verCabecAberto(){
        CabecSoqueiraDAO cabecSoqueiraDAO = new CabecSoqueiraDAO();
        return cabecSoqueiraDAO.verCabecAberto();
    }

    public boolean verCabecFechadoEnviado(){
        CabecSoqueiraDAO cabecSoqueiraDAO = new CabecSoqueiraDAO();
        return cabecSoqueiraDAO.verCabecFechadoEnviado();
    }

    /////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////// GET DE CAMPOS ///////////////////////////////////////////

    public List<CabecSoqueiraBean> cabecAbertoList(){
        CabecSoqueiraDAO cabecSoqueiraDAO = new CabecSoqueiraDAO();
        return cabecSoqueiraDAO.cabecSoqueiraAbertoList();
    }

    public List<CabecSoqueiraBean> cabecFechadoList(){
        CabecSoqueiraDAO cabecSoqueiraDAO = new CabecSoqueiraDAO();
        return cabecSoqueiraDAO.cabecSoqueiraFechadoList();
    }

    public List<CabecSoqueiraBean> cabecFechadoEnviadoList(){
        CabecSoqueiraDAO cabecSoqueiraDAO = new CabecSoqueiraDAO();
        return cabecSoqueiraDAO.cabecSoqueiraFechadoEnviadoList();
    }

    public CabecSoqueiraBean getCabecSoqueiraAberto(){
        CabecSoqueiraDAO cabecSoqueiraDAO = new CabecSoqueiraDAO();
        return cabecSoqueiraDAO.getCabecSoqueiraAberto();
    }

    public EquipBean getEquip(){
        EquipDAO equipDAO = new EquipDAO();
        return equipDAO.getEquip(getCabecSoqueiraAberto().getEquipCabecSoqueira());
    }

    public EquipBean getEquip(Long nroEquip){
        EquipDAO equipDAO = new EquipDAO();
        return equipDAO.getEquip(nroEquip);
    }

    public FuncBean getFunc(){
        FuncDAO funcDAO = new FuncDAO();
        return funcDAO.getFuncMatric(getCabecSoqueiraAberto().getAuditorCabecSoqueira());
    }

    public FuncBean getFunc(Long matric){
        FuncDAO funcDAO = new FuncDAO();
        return funcDAO.getFuncMatric(matric);
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

    public List getListAmostra(Long idCabec){
        AmostraSoqueiraDAO amostraSoqueiraDAO = new AmostraSoqueiraDAO();
        return amostraSoqueiraDAO.getListAmostra(idCabec);
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

    ///////////////////////////////// VERIFICAÇÃO PRA ENVIO //////////////////////////////////

    public boolean verCabecFechado() {
        CabecSoqueiraDAO cabecSoqueiraDAO = new CabecSoqueiraDAO();
        return cabecSoqueiraDAO.verCabecFechado();
    }

    ///////////////////////////////////////////////////////////////////////////////////////////


    //////////////////////////////////// DADOS PRA ENVIO //////////////////////////////////////

    public String dadosEnvioCabecFechado(){
        CabecSoqueiraDAO cabecSoqueiraDAO = new CabecSoqueiraDAO();
        return cabecSoqueiraDAO.dadosEnvioCabecFechado();
    }

    public ArrayList<Long> idCabecList(){
        CabecSoqueiraDAO cabecSoqueiraDAO = new CabecSoqueiraDAO();
        return cabecSoqueiraDAO.idCabecList();
    }

    public String dadosEnvioAmostra(ArrayList<Long> idCabecList){
        AmostraSoqueiraDAO amostraSoqueiraDAO = new AmostraSoqueiraDAO();
        return amostraSoqueiraDAO.dadosEnvioAmostra(amostraSoqueiraDAO.getListAmostraEnvio(idCabecList));
    }

    public void updateDados(String retorno){
        CabecSoqueiraDAO cabecSoqueiraDAO = new CabecSoqueiraDAO();
        cabecSoqueiraDAO.updateCabecAberto(retorno);
    }

    public void deleteEnviados(){
        CabecSoqueiraDAO cabecSoqueiraDAO = new CabecSoqueiraDAO();
        Long idCabec = cabecSoqueiraDAO.delCabec();
        if(idCabec > 0L){
            AmostraSoqueiraDAO amostraSoqueiraDAO = new AmostraSoqueiraDAO();
            List amostraList = amostraSoqueiraDAO.getListAmostra(idCabec);
            amostraSoqueiraDAO.delListAmostra(amostraList);
            amostraList.clear();
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////

}
