package br.com.usinasantafe.pru.control;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pru.model.bean.estaticas.EquipBean;
import br.com.usinasantafe.pru.model.bean.estaticas.FuncBean;
import br.com.usinasantafe.pru.model.bean.estaticas.OSBean;
import br.com.usinasantafe.pru.model.bean.variaveis.AmostraPerdaBean;
import br.com.usinasantafe.pru.model.bean.variaveis.CabecPerdaBean;
import br.com.usinasantafe.pru.model.dao.AmostraPerdaDAO;
import br.com.usinasantafe.pru.model.dao.CabecPerdaDAO;
import br.com.usinasantafe.pru.model.dao.EquipDAO;
import br.com.usinasantafe.pru.model.dao.FuncDAO;
import br.com.usinasantafe.pru.model.dao.OSDAO;

public class PerdaCTR {

    private AmostraPerdaBean amostraPerdaBean;

    public PerdaCTR() {
    }

    public void salvarCabecPerdaAberto(Long codEquip){

        RuricolaCTR ruricolaCTR = new RuricolaCTR();
        CabecPerdaBean cabecPerdaBean = new CabecPerdaBean();
        cabecPerdaBean.setAuditorCabecPerda(ruricolaCTR.getFuncMatric().getMatricFunc());
        cabecPerdaBean.setOsCabecPerda(ruricolaCTR.getBolAberto().getOsBol());
        cabecPerdaBean.setEquipCabecPerda(codEquip);
        cabecPerdaBean.setTipoColheitaCabecPerda(1L);

        deleteEnviados();

        CabecPerdaDAO cabecPerdaDAO = new CabecPerdaDAO();
        cabecPerdaDAO.salvarCabecPerdaAberto(cabecPerdaBean);

        ConfigCTR configCTR = new ConfigCTR();
        configCTR.setPontoAmostraConfig(0L);

    }

    public void salvarAmostraPerda(Long seqAmostraPerda){

        AmostraPerdaDAO amostraPerdaDAO = new AmostraPerdaDAO();
        amostraPerdaDAO.salvarAmostraPerda(amostraPerdaBean, seqAmostraPerda, getCabecPerdaAberto().getIdCabecPerda());

        ConfigCTR configCTR = new ConfigCTR();
        configCTR.setPontoAmostraConfig(seqAmostraPerda);

    }

    public void delAmostraPerda(Long seqAmostraPerda){
        AmostraPerdaDAO amostraPerdaDAO = new AmostraPerdaDAO();
        amostraPerdaDAO.delAmostraPerda(getCabecPerdaAberto().getIdCabecPerda(), seqAmostraPerda);
    }

    public void delPerda(){
        CabecPerdaDAO cabecPerdaDAO = new CabecPerdaDAO();
        AmostraPerdaDAO amostraPerdaDAO = new AmostraPerdaDAO();
        Long idCabec = cabecPerdaDAO.getCabecPerdaAberto().getIdCabecPerda();
        amostraPerdaDAO.delAmostraPerda(idCabec);
        cabecPerdaDAO.delCabecPerda(idCabec);
    }

    public void fecharCabecPerda(){
        CabecPerdaDAO cabecPerdaDAO = new CabecPerdaDAO();
        cabecPerdaDAO.fecharCabecPerda();
    }

    ////////////////////////////////// VERIFICAR CAMPOS ///////////////////////////////////////////

    public boolean verEquip(Long codEquip){
        EquipDAO equipDAO = new EquipDAO();
        return equipDAO.verEquip(codEquip);
    }

    public boolean verCabecAberto(){
        CabecPerdaDAO cabecPerdaDAO = new CabecPerdaDAO();
        return cabecPerdaDAO.verCabecPerdaAberto();
    }

    public boolean verCabecFechadoEnviado(){
        CabecPerdaDAO cabecPerdaDAO = new CabecPerdaDAO();
        return cabecPerdaDAO.verCabecPerdaFechadoEnviado();
    }

    /////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////// GET DE CAMPOS ///////////////////////////////////////////

    public List<CabecPerdaBean> cabecAbertoList(){
        CabecPerdaDAO cabecPerdaDAO = new CabecPerdaDAO();
        return cabecPerdaDAO.cabecPerdaAbertoList();
    }

    public List<CabecPerdaBean> cabecFechadoList(){
        CabecPerdaDAO cabecPerdaDAO = new CabecPerdaDAO();
        return cabecPerdaDAO.cabecPerdaFechadoList();
    }

    public List<CabecPerdaBean> cabecFechadoEnviadoList(){
        CabecPerdaDAO cabecPerdaDAO = new CabecPerdaDAO();
        return cabecPerdaDAO.cabecPerdaFechadoEnviadoList();
    }

    public CabecPerdaBean getCabecPerdaAberto(){
        CabecPerdaDAO cabecPerdaDAO = new CabecPerdaDAO();
        return cabecPerdaDAO.getCabecPerdaAberto();
    }

    public EquipBean getEquip(){
        EquipDAO equipDAO = new EquipDAO();
        return equipDAO.getEquip(getCabecPerdaAberto().getEquipCabecPerda());
    }

    public EquipBean getEquip(Long nroEquip){
        EquipDAO equipDAO = new EquipDAO();
        return equipDAO.getEquip(nroEquip);
    }

    public FuncBean getFunc(){
        FuncDAO funcDAO = new FuncDAO();
        return funcDAO.getFuncMatric(getCabecPerdaAberto().getAuditorCabecPerda());
    }

    public FuncBean getFunc(Long matric){
        FuncDAO funcDAO = new FuncDAO();
        return funcDAO.getFuncMatric(matric);
    }

    public OSBean getOS(){
        OSDAO osDAO = new OSDAO();
        return osDAO.getOS(getCabecPerdaAberto().getOsCabecPerda());
    }

    public AmostraPerdaBean getAmostraPerdaBean() {
        return amostraPerdaBean;
    }

    public AmostraPerdaBean getAmostraPerda(Long seqAmostraPerda){
        AmostraPerdaDAO amostraPerdaDAO = new AmostraPerdaDAO();
        return amostraPerdaDAO.getAmostraPerda(getCabecPerdaAberto().getIdCabecPerda(), seqAmostraPerda);
    }

    public List getListAmostra(Long idCabec){
        AmostraPerdaDAO amostraPerdaDAO = new AmostraPerdaDAO();
        return amostraPerdaDAO.getListAmostra(idCabec);
    }

    /////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////// SET DE CAMPOS ///////////////////////////////////////////

    public void setAmostraPerdaBean(AmostraPerdaBean amostraPerdaBean) {
        this.amostraPerdaBean = amostraPerdaBean;
    }

    public void salvarAmostraPerda(Double valor, int posQuestao, Long seqAmostraPerda){
        AmostraPerdaDAO amostraPerdaDAO = new AmostraPerdaDAO();
        amostraPerdaDAO.setQuestaoAmostraPerda(valor, posQuestao, seqAmostraPerda, getCabecPerdaAberto().getIdCabecPerda());
    }

    public void setQuestaoAmostraPerda(Long pedra, Long tocoArvore, Long plantaDaninha, Long formigueiro, Long seqAmostraPerda){
        AmostraPerdaDAO amostraPerdaDAO = new AmostraPerdaDAO();
        amostraPerdaDAO.setQuestaoAmostraPerda(pedra, tocoArvore, plantaDaninha, formigueiro, seqAmostraPerda, getCabecPerdaAberto().getIdCabecPerda());
    }

    /////////////////////////////////////////////////////////////////////////////////////////////

    ///////////////////////////////// VERIFICAÇÃO PRA ENVIO //////////////////////////////////

    public boolean verCabecFechado() {
        CabecPerdaDAO cabecPerdaDAO = new CabecPerdaDAO();
        return cabecPerdaDAO.verCabecPerdaFechado();
    }

    ///////////////////////////////////////////////////////////////////////////////////////////

    //////////////////////////////////// DADOS PRA ENVIO //////////////////////////////////////

    public String dadosEnvioCabecFechado(){
        CabecPerdaDAO cabecPerdaDAO = new CabecPerdaDAO();
        return cabecPerdaDAO.dadosEnvioCabecFechado();
    }

    public ArrayList<Long> idCabecList(){
        CabecPerdaDAO cabecPerdaDAO = new CabecPerdaDAO();
        return cabecPerdaDAO.idCabecList();
    }

    public String dadosEnvioAmostra(ArrayList<Long> idCabecList){
        AmostraPerdaDAO amostraPerdaDAO = new AmostraPerdaDAO();
        return amostraPerdaDAO.dadosEnvioAmostra(amostraPerdaDAO.getListAmostraEnvio(idCabecList));
    }

    public void updateDados(String retorno){
        CabecPerdaDAO cabecPerdaDAO = new CabecPerdaDAO();
        cabecPerdaDAO.updateCabecAberto(retorno);
    }

    public void deleteEnviados(){
        CabecPerdaDAO cabecPerdaDAO = new CabecPerdaDAO();
        Long idCabec = cabecPerdaDAO.delCabec();
        if(idCabec > 0L){
            AmostraPerdaDAO amostraPerdaDAO = new AmostraPerdaDAO();
            List amostraList = amostraPerdaDAO.getListAmostra(idCabec);
            amostraPerdaDAO.delListAmostra(amostraList);
            amostraList.clear();
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////

}
