package br.com.usinasantafe.pru.control;

import java.util.List;

import br.com.usinasantafe.pru.model.bean.estaticas.AmostraBean;
import br.com.usinasantafe.pru.model.bean.estaticas.FuncBean;
import br.com.usinasantafe.pru.model.bean.estaticas.OSBean;
import br.com.usinasantafe.pru.model.bean.estaticas.TalhaoBean;
import br.com.usinasantafe.pru.model.bean.variaveis.CabecFitoBean;
import br.com.usinasantafe.pru.model.bean.variaveis.RespFitoBean;
import br.com.usinasantafe.pru.model.dao.AmostraDAO;
import br.com.usinasantafe.pru.model.dao.CabecFitoDAO;
import br.com.usinasantafe.pru.model.dao.CaracOrganDAO;
import br.com.usinasantafe.pru.model.dao.FuncDAO;
import br.com.usinasantafe.pru.model.dao.OSDAO;
import br.com.usinasantafe.pru.model.dao.OrganDAO;
import br.com.usinasantafe.pru.model.dao.RespFitoDAO;
import br.com.usinasantafe.pru.model.dao.TalhaoDAO;

public class FitoCTR {

    private CabecFitoBean cabecFitoBean;
    private RespFitoBean respFitoBean;

    public FitoCTR() {
    }

    public void salvarCabecFitoAberto(){

        RuricolaCTR ruricolaCTR = new RuricolaCTR();
        cabecFitoBean.setAuditorCabecFito(ruricolaCTR.getBolAberto().getIdLiderBol());
        cabecFitoBean.setOSCabecFito(ruricolaCTR.getBolAberto().getOsBol());

        CabecFitoDAO cabecFitoDAO = new CabecFitoDAO();
        cabecFitoDAO.salvarCabecFitoAberto(cabecFitoBean);

        ConfigCTR configCTR = new ConfigCTR();
        configCTR.setPontoConfig(0L);
    }

    public void salvarRespFito(AmostraBean amostraBean, Long valor){
        ConfigCTR configCTR = new ConfigCTR();
        RespFitoDAO respFitoDAO = new RespFitoDAO();
        respFitoDAO.salvarRespFito(amostraBean, getCabecFitoAberto().getIdCabecFito(), valor, configCTR.getConfig().getPontoConfig());
    }

    public void salvarRespFito(AmostraBean amostraBean, Long valor, Long ponto){
        ConfigCTR configCTR = new ConfigCTR();
        configCTR.setPontoConfig(ponto);
        RespFitoDAO respFitoDAO = new RespFitoDAO();
        respFitoDAO.salvarRespFito(amostraBean, getCabecFitoAberto().getIdCabecFito(), valor, ponto);
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

    public void atualRespFito(Long valor){
        RespFitoDAO respFitoDAO = new RespFitoDAO();
        respFitoBean.setValorRespFito(valor);
        respFitoDAO.atualRespFito(respFitoBean);
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
        AmostraDAO amostraDAO = new AmostraDAO();
        return amostraDAO.verAmostra(cabecFitoBean.getIdOrgCabecFito(), cabecFitoBean.getIdCaracOrgCabecFito());
    }

    public boolean hasRespCabec(){
        RespFitoDAO respFitoDAO = new RespFitoDAO();
        cabecFitoBean = getCabecFitoBean();
        return respFitoDAO.hasRespFitoCabec(cabecFitoBean.getIdCabecFito());
    }

    public boolean hasTipoAmostraCabec(){
        AmostraDAO amostraDAO = new AmostraDAO();
        cabecFitoBean = getCabecFitoBean();
        return amostraDAO.hasAmostraCabec(cabecFitoBean.getIdOrgCabecFito(), cabecFitoBean.getIdCaracOrgCabecFito());
    }

    public boolean verCabecFitoAberto(){
        CabecFitoDAO cabecFitoDAO = new CabecFitoDAO();
        return cabecFitoDAO.verCabecFitoAberto();
    }

    public AmostraBean getAmostraCabec(){
        RespFitoDAO respFitoDAO = new RespFitoDAO();
        AmostraDAO amostraDAO = new AmostraDAO();
        cabecFitoBean = getCabecFitoBean();
        List respCabecList = respFitoDAO.respCabecFitoList(cabecFitoBean.getIdCabecFito());
        List amostraCabecList = amostraDAO.amostraCabecList(cabecFitoBean.getIdOrgCabecFito(), cabecFitoBean.getIdCaracOrgCabecFito());
        AmostraBean amostraBean = (AmostraBean) amostraCabecList.get(respCabecList.size());
        respCabecList.clear();
        amostraCabecList.clear();
        return amostraBean;
    }

    public AmostraBean getAmostraResp(Long ponto){
        RespFitoDAO respFitoDAO = new RespFitoDAO();
        AmostraDAO amostraDAO = new AmostraDAO();
        cabecFitoBean = getCabecFitoBean();
        List respCabecList = respFitoDAO.respFitoListAberto(cabecFitoBean.getIdCabecFito(), ponto);
        List amostraCabecList = amostraDAO.amostraCabecList(cabecFitoBean.getIdOrgCabecFito(), cabecFitoBean.getIdCaracOrgCabecFito());
        AmostraBean amostraBean = (AmostraBean) amostraCabecList.get(respCabecList.size());
        respCabecList.clear();
        amostraCabecList.clear();
        return amostraBean;
    }

    public boolean verTermQuestaoCabec(){
        RespFitoDAO respFitoDAO = new RespFitoDAO();
        AmostraDAO amostraDAO = new AmostraDAO();
        cabecFitoBean = getCabecFitoBean();
        List respCabecList = respFitoDAO.respCabecFitoList(cabecFitoBean.getIdCabecFito());
        List amostraCabecList = amostraDAO.amostraCabecList(cabecFitoBean.getIdOrgCabecFito(), cabecFitoBean.getIdCaracOrgCabecFito());
        boolean ret = (respCabecList.size() == amostraCabecList.size());
        respCabecList.clear();
        amostraCabecList.clear();
        return ret;
    }

    public boolean verTermQuestao(Long ponto){
        RespFitoDAO respFitoDAO = new RespFitoDAO();
        AmostraDAO amostraDAO = new AmostraDAO();
        cabecFitoBean = getCabecFitoBean();
        List respCabecList = respFitoDAO.respFitoListAberto(cabecFitoBean.getIdCabecFito(), ponto);
        List amostraCabecList = amostraDAO.amostraCabecList(cabecFitoBean.getIdOrgCabecFito(), cabecFitoBean.getIdCaracOrgCabecFito());
        boolean ret = (respCabecList.size() == amostraCabecList.size());
        respCabecList.clear();
        amostraCabecList.clear();
        return ret;
    }

    public void fecharRespFitoPonto(){
        RespFitoDAO respFitoDAO = new RespFitoDAO();
        respFitoDAO.fecharRespFitoPonto();
    }

    /////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////// GET DE CAMPOS ///////////////////////////////////////////

    public CabecFitoBean getCabecFitoBean() {
        return cabecFitoBean;
    }

    public RespFitoBean getRespFitoBean() {
        return respFitoBean;
    }

    public CabecFitoBean getCabecFitoAberto() {
        CabecFitoDAO cabecFitoDAO = new CabecFitoDAO();
        return cabecFitoDAO.getCabecFitoAberto();
    }

    public List getOrganList(){
        OrganDAO organDAO = new OrganDAO();
        return organDAO.getOrganList();
    }

    public List getCaracOrganList(){
        CaracOrganDAO caracOrganDAO = new CaracOrganDAO();
        return caracOrganDAO.getCaracOrganList(cabecFitoBean.getIdOrgCabecFito());
    }

    public FuncBean getFuncCabec(){
        CabecFitoDAO cabecFitoDAO = new CabecFitoDAO();
        FuncDAO funcDAO = new FuncDAO();
        return funcDAO.getFunc(cabecFitoDAO.getCabecFitoAberto().getAuditorCabecFito());
    }

    public OSBean getOS(){
        CabecFitoDAO cabecFitoDAO = new CabecFitoDAO();
        OSDAO osDAO = new OSDAO();
        return osDAO.getOS(cabecFitoDAO.getCabecFitoAberto().getOSCabecFito());
    }

    public TalhaoBean getTalhao(){
        CabecFitoDAO cabecFitoDAO = new CabecFitoDAO();
        TalhaoDAO talhaoDAO = new TalhaoDAO();
        return talhaoDAO.getTalhao(cabecFitoDAO.getCabecFitoAberto().getTalhaoCabecFito());
    }

    public List<RespFitoBean> getRespPontoFitoList(Long ponto){
        cabecFitoBean = getCabecFitoBean();
        RespFitoDAO respFitoDAO = new RespFitoDAO();
        return respFitoDAO.getRespPontoFitoList(cabecFitoBean.getIdCabecFito(), ponto);
    }

    public AmostraBean getAmostra(Long idAmostra){
        AmostraDAO amostraDAO = new AmostraDAO();
        return amostraDAO.getAmostra(idAmostra);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////// SET DE CAMPOS ///////////////////////////////////////////

    public void setCabecFitoBean(CabecFitoBean cabecFitoBean) {
        this.cabecFitoBean = cabecFitoBean;
    }

    public void setRespFitoBean(RespFitoBean respFitoBean) {
        this.respFitoBean = respFitoBean;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////

}
