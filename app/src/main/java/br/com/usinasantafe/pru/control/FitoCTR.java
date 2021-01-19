package br.com.usinasantafe.pru.control;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pru.model.bean.estaticas.AmostraFitoBean;
import br.com.usinasantafe.pru.model.bean.estaticas.CaracOrganFitoBean;
import br.com.usinasantafe.pru.model.bean.estaticas.FuncBean;
import br.com.usinasantafe.pru.model.bean.estaticas.OSBean;
import br.com.usinasantafe.pru.model.bean.estaticas.OrganFitoBean;
import br.com.usinasantafe.pru.model.bean.estaticas.TalhaoBean;
import br.com.usinasantafe.pru.model.bean.variaveis.CabecFitoBean;
import br.com.usinasantafe.pru.model.bean.variaveis.RespFitoBean;
import br.com.usinasantafe.pru.model.dao.AmostraFitoDAO;
import br.com.usinasantafe.pru.model.dao.CabecFitoDAO;
import br.com.usinasantafe.pru.model.dao.CaracOrganDAO;
import br.com.usinasantafe.pru.model.dao.FuncDAO;
import br.com.usinasantafe.pru.model.dao.OSDAO;
import br.com.usinasantafe.pru.model.dao.OrganDAO;
import br.com.usinasantafe.pru.model.dao.RespFitoDAO;
import br.com.usinasantafe.pru.model.dao.TalhaoDAO;

public class FitoCTR {

    private CabecFitoBean cabecFitoBean;
    private Long idRespFito;

    public FitoCTR() {
    }

    public void salvarCabecFitoAberto(){

        RuricolaCTR ruricolaCTR = new RuricolaCTR();

        FuncDAO funcDAO = new FuncDAO();
        cabecFitoBean.setAuditorCabecFito(funcDAO.getFuncMatric(ruricolaCTR.getBolAberto().getMatricLiderBol()).getIdFunc());
        cabecFitoBean.setOSCabecFito(ruricolaCTR.getBolAberto().getOsBol());

        deleteEnviados();

        CabecFitoDAO cabecFitoDAO = new CabecFitoDAO();
        cabecFitoDAO.salvarCabecFitoAberto(cabecFitoBean);

        ConfigCTR configCTR = new ConfigCTR();
        configCTR.setPontoAmostraConfig(0L);
    }

    public void salvarRespFito(AmostraFitoBean amostraFitoBean, Long valor, Long ponto){
        RespFitoDAO respFitoDAO = new RespFitoDAO();
        respFitoDAO.salvarRespFito(amostraFitoBean, getCabecFitoAberto().getIdCabecFito(), valor, ponto);
    }

    public void atualRespFito(Long valor){
        RespFitoDAO respFitoDAO = new RespFitoDAO();
        respFitoDAO.atualRespFito(idRespFito, valor);
    }

    public void delFito(){
        CabecFitoDAO cabecFitoDAO = new CabecFitoDAO();
        RespFitoDAO respFitoDAO = new RespFitoDAO();
        Long idCabec = cabecFitoDAO.getCabecFitoAberto().getIdCabecFito();
        respFitoDAO.delRespFito(idCabec);
        cabecFitoDAO.delCabecFito(idCabec);
    }

    public void fecharCabecFito(){
        CabecFitoDAO cabecFitoDAO = new CabecFitoDAO();
        cabecFitoDAO.fecharCabecFito();
    }

    public void delRespPonto(Long ponto){
        RespFitoDAO respFitoDAO = new RespFitoDAO();
        cabecFitoBean = getCabecFitoAberto();
        respFitoDAO.delRespPonto(cabecFitoBean.getIdCabecFito(), ponto);
    }


    public void fecharRespFitoPonto(Long pontoAmostra){

        RespFitoDAO respFitoDAO = new RespFitoDAO();
        respFitoDAO.fecharRespFitoPonto();

        ConfigCTR configCTR = new ConfigCTR();
        configCTR.setPontoAmostraConfig(pontoAmostra);

    }

    ////////////////////////////////// VERIFICAR CAMPOS ///////////////////////////////////////////

    public boolean verTalhao(Long codTalhao){
        ConfigCTR configCTR = new ConfigCTR();
        TalhaoDAO talhaoDAO = new TalhaoDAO();
        if(configCTR.verOS(configCTR.getConfig().getNroOSConfig())){
            return  talhaoDAO.verTalhao(configCTR.getOS().getCodSecao(), codTalhao);
        }
        else{
            return true;
        }
    }

    public boolean verAmostra(){
        AmostraFitoDAO amostraFitoDAO = new AmostraFitoDAO();
        return amostraFitoDAO.verAmostra(cabecFitoBean.getIdOrgCabecFito(), cabecFitoBean.getIdCaracOrgCabecFito());
    }

    public boolean hasRespCabec(){
        RespFitoDAO respFitoDAO = new RespFitoDAO();
        cabecFitoBean = getCabecFitoBean();
        return respFitoDAO.hasRespFitoCabec(cabecFitoBean.getIdCabecFito());
    }

    public boolean hasTipoAmostraCabec(){
        AmostraFitoDAO amostraFitoDAO = new AmostraFitoDAO();
        cabecFitoBean = getCabecFitoBean();
        return amostraFitoDAO.hasAmostraCabec(cabecFitoBean.getIdOrgCabecFito(), cabecFitoBean.getIdCaracOrgCabecFito());
    }

    public boolean verCabecAberto(){
        CabecFitoDAO cabecFitoDAO = new CabecFitoDAO();
        return cabecFitoDAO.verCabecAberto();
    }

    public boolean verCabecFechado() {
        CabecFitoDAO cabecFitoDAO = new CabecFitoDAO();
        return cabecFitoDAO.verCabecFechado();
    }

    public boolean verCabecFechadoEnviado() {
        CabecFitoDAO cabecFitoDAO = new CabecFitoDAO();
        return cabecFitoDAO.verCabecFechadoEnviado();
    }

    public boolean verTermQuestaoCabec(int posQuestao){
        AmostraFitoDAO amostraFitoDAO = new AmostraFitoDAO();
        cabecFitoBean = getCabecFitoBean();
        List amostraCabecList = amostraFitoDAO.amostraCabecList(cabecFitoBean.getIdOrgCabecFito(), cabecFitoBean.getIdCaracOrgCabecFito());
        boolean ret = (posQuestao == amostraCabecList.size());
        amostraCabecList.clear();
        return ret;
    }

    public boolean verTermQuestao(int posQuestao){
        AmostraFitoDAO amostraFitoDAO = new AmostraFitoDAO();
        cabecFitoBean = getCabecFitoBean();
        List amostraCabecList = amostraFitoDAO.amostraList(cabecFitoBean.getIdOrgCabecFito(), cabecFitoBean.getIdCaracOrgCabecFito());
        boolean ret = (posQuestao == amostraCabecList.size());
        amostraCabecList.clear();
        return ret;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////// GET DE CAMPOS ///////////////////////////////////////////

    public CabecFitoBean getCabecFitoBean() {
        return cabecFitoBean;
    }

    public CabecFitoBean getCabecFitoAberto() {
        CabecFitoDAO cabecFitoDAO = new CabecFitoDAO();
        return cabecFitoDAO.getCabecFitoAberto();
    }

    public List cabecFechadoList() {
        CabecFitoDAO cabecFitoDAO = new CabecFitoDAO();
        return cabecFitoDAO.cabecFitoFechadoList();
    }

    public List cabecFechadoEnviadoList() {
        CabecFitoDAO cabecFitoDAO = new CabecFitoDAO();
        return cabecFitoDAO.cabecFitoFechadoEnviadoList();
    }

    public FuncBean getFunc(Long matric){
        FuncDAO funcDAO = new FuncDAO();
        return funcDAO.getFuncMatric(matric);
    }

    public List getOrganList(){
        OrganDAO organDAO = new OrganDAO();
        return organDAO.getOrganList();
    }

    public OrganFitoBean getOrganFito(Long idOrgan){
        OrganDAO organDAO = new OrganDAO();
        return organDAO.getOrganFito(idOrgan);
    }

    public CaracOrganFitoBean getCaracOrganFito(Long idCaracOrgan){
        CaracOrganDAO caracOrganDAO =  new CaracOrganDAO();
        return caracOrganDAO.getCaracOrgan(idCaracOrgan);
    }

    public List getCaracOrganList(){
        CaracOrganDAO caracOrganDAO = new CaracOrganDAO();
        return caracOrganDAO.getCaracOrganList(cabecFitoBean.getIdOrgCabecFito());
    }

    public FuncBean getFuncCabec(){
        FuncDAO funcDAO = new FuncDAO();
        return funcDAO.getFuncId(getCabecFitoAberto().getAuditorCabecFito());
    }

    public OSBean getOS(){
        OSDAO osDAO = new OSDAO();
        return osDAO.getOS(getCabecFitoAberto().getOSCabecFito());
    }

    public TalhaoBean getTalhao(){
        TalhaoDAO talhaoDAO = new TalhaoDAO();
        return talhaoDAO.getTalhao(getCabecFitoAberto().getTalhaoCabecFito());
    }

    public List<RespFitoBean> getRespPontoFitoList(Long ponto){
        cabecFitoBean = getCabecFitoBean();
        RespFitoDAO respFitoDAO = new RespFitoDAO();
        return respFitoDAO.getRespPontoFitoList(cabecFitoBean.getIdCabecFito(), ponto);
    }

    public AmostraFitoBean getAmostra(Long idAmostra){
        AmostraFitoDAO amostraFitoDAO = new AmostraFitoDAO();
        return amostraFitoDAO.getAmostra(idAmostra);
    }

    public RespFitoBean getRespFitoBean(){
        RespFitoDAO respFitoDAO = new RespFitoDAO();
        return respFitoDAO.getRespFitoBean(idRespFito);
    }

    public AmostraFitoBean getAmostraCabecResp(int posQuestao){
        AmostraFitoDAO amostraFitoDAO = new AmostraFitoDAO();
        cabecFitoBean = getCabecFitoBean();
        List amostraCabecList = amostraFitoDAO.amostraCabecList(cabecFitoBean.getIdOrgCabecFito(), cabecFitoBean.getIdCaracOrgCabecFito());
        AmostraFitoBean amostraFitoBean = (AmostraFitoBean) amostraCabecList.get(posQuestao - 1);
        amostraCabecList.clear();
        return amostraFitoBean;
    }

    public AmostraFitoBean getAmostraResp(int posQuestao){
        AmostraFitoDAO amostraFitoDAO = new AmostraFitoDAO();
        cabecFitoBean = getCabecFitoBean();
        List amostraCabecList = amostraFitoDAO.amostraList(cabecFitoBean.getIdOrgCabecFito(), cabecFitoBean.getIdCaracOrgCabecFito());
        Log.i("PRU", "QTDE AMOSTRA = " + amostraCabecList.size());
        AmostraFitoBean amostraFitoBean = (AmostraFitoBean) amostraCabecList.get(posQuestao - 1);
        amostraCabecList.clear();
        return amostraFitoBean;
    }

    public Long getIdRespFito() {
        return idRespFito;
    }

    public List getListResp(Long idCabec){
        RespFitoDAO respFitoDAO = new RespFitoDAO();
        return respFitoDAO.getListResp(idCabec);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////// SET DE CAMPOS ///////////////////////////////////////////

    public void setCabecFitoBean(CabecFitoBean cabecFitoBean) {
        this.cabecFitoBean = cabecFitoBean;
    }

    public void setIdRespFito(Long idRespFito) {
        this.idRespFito = idRespFito;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////

    //////////////////////////////////// DADOS PRA ENVIO //////////////////////////////////////

    public String dadosEnvioCabecFechado(){
        CabecFitoDAO cabecFitoDAO = new CabecFitoDAO();
        return cabecFitoDAO.dadosEnvioCabecFechado();
    }

    public ArrayList<Long> idCabecList(){
        CabecFitoDAO cabecFitoDAO = new CabecFitoDAO();
        return cabecFitoDAO.idCabecList();
    }

    public String dadosEnvioResp(ArrayList<Long> idCabecList){
        RespFitoDAO respFitoDAO = new RespFitoDAO();
        return respFitoDAO.dadosEnvioResp(respFitoDAO.getListRespEnvio(idCabecList));
    }

    public void updateDados(String retorno){
        CabecFitoDAO cabecFitoDAO = new CabecFitoDAO();
        cabecFitoDAO.updateCabecAberto(retorno);
    }

    public void deleteEnviados(){
        CabecFitoDAO cabecFitoDAO = new CabecFitoDAO();
        Long idCabec = cabecFitoDAO.delCabec();
        if(idCabec > 0L){
            RespFitoDAO respFitoDAO = new RespFitoDAO();
            List respList = respFitoDAO.getListResp(idCabec);
            respFitoDAO.delListResp(respList);
            respList.clear();
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////

}
