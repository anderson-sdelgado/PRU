package br.com.usinasantafe.pru.control;

import java.util.List;

import br.com.usinasantafe.pru.model.bean.estaticas.AmostraBean;
import br.com.usinasantafe.pru.model.bean.estaticas.FuncBean;
import br.com.usinasantafe.pru.model.bean.estaticas.OSBean;
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
        cabecFitoBean.setAuditorCabecFito(ruricolaCTR.getBolAberto().getIdLiderBol());
        cabecFitoBean.setOSCabecFito(ruricolaCTR.getBolAberto().getOsBol());

        CabecFitoDAO cabecFitoDAO = new CabecFitoDAO();
        cabecFitoDAO.salvarCabecFitoAberto(cabecFitoBean);

        ConfigCTR configCTR = new ConfigCTR();
        configCTR.setPontoAmostraConfig(0L);
    }

    public void salvarRespFito(AmostraBean amostraBean, Long valor, Long ponto){
        RespFitoDAO respFitoDAO = new RespFitoDAO();
        respFitoDAO.salvarRespFito(amostraBean, getCabecFitoAberto().getIdCabecFito(), valor, ponto);
    }

    public void atualRespFito(AmostraBean amostraBean, Long valor, Long ponto){
        RespFitoDAO respFitoDAO = new RespFitoDAO();
        respFitoDAO.atualRespFito(amostraBean, getCabecFitoAberto().getIdCabecFito(), valor, ponto);
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

    public boolean verCabecFitoAberto(){
        CabecFitoDAO cabecFitoDAO = new CabecFitoDAO();
        return cabecFitoDAO.verCabecFitoAberto();
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
        List amostraCabecList = amostraFitoDAO.amostraCabecList(cabecFitoBean.getIdOrgCabecFito(), cabecFitoBean.getIdCaracOrgCabecFito());
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

    public List getOrganList(){
        OrganDAO organDAO = new OrganDAO();
        return organDAO.getOrganList();
    }

    public List getCaracOrganList(){
        CaracOrganDAO caracOrganDAO = new CaracOrganDAO();
        return caracOrganDAO.getCaracOrganList(cabecFitoBean.getIdOrgCabecFito());
    }

    public FuncBean getFuncCabec(){
        FuncDAO funcDAO = new FuncDAO();
        return funcDAO.getFunc(getCabecFitoAberto().getAuditorCabecFito());
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

    public AmostraBean getAmostra(Long idAmostra){
        AmostraFitoDAO amostraFitoDAO = new AmostraFitoDAO();
        return amostraFitoDAO.getAmostra(idAmostra);
    }

    public RespFitoBean getRespFitoBean(){
        RespFitoDAO respFitoDAO = new RespFitoDAO();
        return respFitoDAO.getRespFitoBean(idRespFito);
    }

    public AmostraBean getAmostraCabec(int posQuestao){
        AmostraFitoDAO amostraFitoDAO = new AmostraFitoDAO();
        cabecFitoBean = getCabecFitoBean();
        List amostraCabecList = amostraFitoDAO.amostraCabecList(cabecFitoBean.getIdOrgCabecFito(), cabecFitoBean.getIdCaracOrgCabecFito());
        AmostraBean amostraBean = (AmostraBean) amostraCabecList.get(posQuestao - 1);
        amostraCabecList.clear();
        return amostraBean;
    }

    public AmostraBean getAmostraResp(int posQuestao){
        AmostraFitoDAO amostraFitoDAO = new AmostraFitoDAO();
        cabecFitoBean = getCabecFitoBean();
        List amostraCabecList = amostraFitoDAO.amostraCabecList(cabecFitoBean.getIdOrgCabecFito(), cabecFitoBean.getIdCaracOrgCabecFito());
        AmostraBean amostraBean = (AmostraBean) amostraCabecList.get(posQuestao - 1);
        amostraCabecList.clear();
        return amostraBean;
    }

    public Long getIdRespFito() {
        return idRespFito;
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

}
