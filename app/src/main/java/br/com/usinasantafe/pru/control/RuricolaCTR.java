package br.com.usinasantafe.pru.control;

import android.app.ProgressDialog;
import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pru.model.bean.estaticas.AtividadeBean;
import br.com.usinasantafe.pru.model.bean.estaticas.FuncBean;
import br.com.usinasantafe.pru.model.bean.estaticas.ParadaBean;
import br.com.usinasantafe.pru.model.bean.estaticas.RFuncaoAtivParBean;
import br.com.usinasantafe.pru.model.bean.estaticas.TurmaBean;
import br.com.usinasantafe.pru.model.bean.variaveis.AlocaFuncBean;
import br.com.usinasantafe.pru.model.bean.variaveis.ApontRuricolaBean;
import br.com.usinasantafe.pru.model.bean.variaveis.BoletimRuricolaBean;
import br.com.usinasantafe.pru.model.bean.variaveis.ConfigBean;
import br.com.usinasantafe.pru.model.dao.AlocaFuncDAO;
import br.com.usinasantafe.pru.model.dao.ApontRuricolaDAO;
import br.com.usinasantafe.pru.model.dao.AtividadeDAO;
import br.com.usinasantafe.pru.model.dao.BoletimRuricolaDAO;
import br.com.usinasantafe.pru.model.dao.FuncDAO;
import br.com.usinasantafe.pru.model.dao.LiderDAO;
import br.com.usinasantafe.pru.model.dao.OSDAO;
import br.com.usinasantafe.pru.model.dao.ParadaDAO;
import br.com.usinasantafe.pru.model.dao.RFuncaoAtivParDAO;
import br.com.usinasantafe.pru.model.dao.TurmaDAO;
import br.com.usinasantafe.pru.util.Tempo;

public class RuricolaCTR {

    private BoletimRuricolaBean boletimRuricolaBean;
    private Long idParada;

    public RuricolaCTR() {
    }

    public boolean verBolAberto(){
        BoletimRuricolaDAO boletimRuricolaDAO = new BoletimRuricolaDAO();
        return boletimRuricolaDAO.verBolAberto();
    }

    public boolean verBolFechadoEnviado() {
        BoletimRuricolaDAO boletimRuricolaDAO = new BoletimRuricolaDAO();
        return boletimRuricolaDAO.verBolFechadoEnviado();
    }

    public boolean verApont() {
        ConfigCTR configCTR = new ConfigCTR();
        BoletimRuricolaDAO boletimRuricolaDAO = new BoletimRuricolaDAO();
        ApontRuricolaDAO apontRuricolaDAO = new ApontRuricolaDAO();
        return apontRuricolaDAO.verApont(boletimRuricolaDAO.getBolAberto(), configCTR.getConfig(), idParada);
    }

    public Long verApont(List<FuncBean> funcBeans) {
        ConfigCTR configCTR = new ConfigCTR();
        BoletimRuricolaDAO boletimRuricolaDAO = new BoletimRuricolaDAO();
        ApontRuricolaDAO apontRuricolaDAO = new ApontRuricolaDAO();
        return apontRuricolaDAO.verApont(boletimRuricolaDAO.getBolAberto(), configCTR.getConfig(), idParada, funcBeans);
    }

    public boolean verQtdeApont(){
        BoletimRuricolaDAO boletimRuricolaDAO = new BoletimRuricolaDAO();
        ApontRuricolaDAO apontRuricolaDAO = new ApontRuricolaDAO();
        return apontRuricolaDAO.verQtdeApont(boletimRuricolaDAO.getBolAberto());
    }

    //////////////////////////// SETAR CAMPOS ///////////////////////////////////////////////

    public void setIdParada(Long idParada) {
        this.idParada = idParada;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////// GET DE CAMPOS ///////////////////////////////////////////

    public List getFuncBDTurmaList() {
        ConfigCTR configCTR = new ConfigCTR();
        FuncDAO funcDAO = new FuncDAO();
        return funcDAO.getFuncAlocList(configCTR.getConfig().getIdTurmaConfig());
    }

    public List getFuncAlocTurmaList() {
        FuncDAO funcDAO = new FuncDAO();
        return funcDAO.getFuncAlocList();
    }

    public List getParadaList(){
        ParadaDAO paradaDAO = new ParadaDAO();
        return paradaDAO.getParadaList();
    }

    public ParadaBean getParada(Long idParada){
        ParadaDAO paradaDAO = new ParadaDAO();
        return paradaDAO.getParada(idParada);
    }

    public BoletimRuricolaBean getBolAberto(){
        BoletimRuricolaDAO boletimRuricolaDAO = new BoletimRuricolaDAO();
        return boletimRuricolaDAO.getBolAberto();
    }

    public FuncBean getFuncMatric(){
        FuncDAO funcDAO = new FuncDAO();
        return funcDAO.getFuncMatric(getBolAberto().getMatricLiderBol());
    }

    public FuncBean getFuncMatric(Long matric){
        FuncDAO funcDAO = new FuncDAO();
        return funcDAO.getFuncMatric(matric);
    }

    public FuncBean getFuncId(Long idFunc){
        FuncDAO funcDAO = new FuncDAO();
        return funcDAO.getFuncId(idFunc);
    }

    public TurmaBean getTurma(Long idTurma){
        TurmaDAO turmaDAO = new TurmaDAO();
        return turmaDAO.getTurma(idTurma);
    }

    public AtividadeBean getAtividade(Long idAtiv){
        AtividadeDAO atividadeDAO = new AtividadeDAO();
        return atividadeDAO.getAtividade(idAtiv);
    }

    public RFuncaoAtivParBean getFuncaoAtivParBean(Long idAtiv){
        RFuncaoAtivParDAO rFuncaoAtivParDAO = new RFuncaoAtivParDAO();
        return rFuncaoAtivParDAO.getRFuncaoAtivPar(idAtiv);
    }

    public List bolFechadoEnviadoList(){
        BoletimRuricolaDAO boletimRuricolaDAO = new BoletimRuricolaDAO();
        return boletimRuricolaDAO.boletimFechadoEnviadoList();
    }

    public List getListApont(Long idBol){
        ApontRuricolaDAO apontRuricolaDAO = new ApontRuricolaDAO();
        return apontRuricolaDAO.getListApont(idBol);
    }

    /////////////////////////////////////////////////////////////////////////////////////

    //////////////////////// MANIPULAR DADOS BOLETIM //////////////////////////////////////

    ///////////// SALVAR DADOS ///////////////

    public void salvarBolAberto(){
        ConfigCTR configCTR = new ConfigCTR();
        ConfigBean configBean = configCTR.getConfig();
        BoletimRuricolaBean boletimRuricolaBean = new BoletimRuricolaBean();
        boletimRuricolaBean.setMatricLiderBol(configBean.getMatricFuncConfig());
        boletimRuricolaBean = salvarBolAberto(boletimRuricolaBean, configBean);
        FuncDAO funcDAO = new FuncDAO();
        funcDAO.alocFunc(funcDAO.getFuncId(boletimRuricolaBean.getMatricLiderBol()));
        AlocaFuncDAO alocaFuncDAO = new AlocaFuncDAO();
        alocaFuncDAO.alocaFunc(boletimRuricolaBean);
    }

    public void salvarBolAberto(Long matricColab){
        ConfigCTR configCTR = new ConfigCTR();
        ConfigBean configBean = configCTR.getConfig();
        BoletimRuricolaBean boletimRuricolaBean = new BoletimRuricolaBean();
        FuncDAO funcDAO = new FuncDAO();
        FuncBean funcBean = funcDAO.getFuncMatric(matricColab);
        boletimRuricolaBean.setMatricLiderBol(matricColab);
        salvarBolAberto(boletimRuricolaBean, configBean);
        funcDAO.alocFunc(funcBean);
        AlocaFuncDAO alocaFuncDAO = new AlocaFuncDAO();
        alocaFuncDAO.alocaFunc(boletimRuricolaBean);
    }

    public void salvarBolAberto(List funcAlocList){
        ConfigCTR configCTR = new ConfigCTR();
        ConfigBean configBean = configCTR.getConfig();
        BoletimRuricolaBean boletimRuricolaBean = new BoletimRuricolaBean();
        boletimRuricolaBean.setMatricLiderBol(configBean.getMatricFuncConfig());
        boletimRuricolaBean = salvarBolAberto(boletimRuricolaBean, configBean);
        alocaFunc(funcAlocList, boletimRuricolaBean, configBean.getIdTurmaConfig());
    }

    private BoletimRuricolaBean salvarBolAberto(BoletimRuricolaBean boletimRuricolaBean, ConfigBean configBean){
        deleteEnviados();
        boletimRuricolaBean.setOsBol(configBean.getNroOSConfig());
        boletimRuricolaBean.setAtivPrincBol(configBean.getIdAtivConfig());
        boletimRuricolaBean.setIdTurmaBol(configBean.getIdTurmaConfig());
        boletimRuricolaBean.setTipoFuncBol(configBean.getIdTipoConfig());
        BoletimRuricolaDAO boletimRuricolaDAO = new BoletimRuricolaDAO();
        boletimRuricolaDAO.salvarBolAberto(boletimRuricolaBean);
        return boletimRuricolaDAO.getBolAberto();
    }

    public void alocaFunc(List funcAlocList){
        BoletimRuricolaDAO boletimRuricolaDAO = new BoletimRuricolaDAO();
        ConfigCTR configCTR = new ConfigCTR();
        ConfigBean configBean = configCTR.getConfig();
        alocaFunc(funcAlocList, boletimRuricolaDAO.getBolAberto(), configBean.getIdTurmaConfig());
    }

    private void alocaFunc(List<FuncBean> funcAlocList, BoletimRuricolaBean boletimRuricolaBean, Long idTurmaConfig){
        FuncDAO funcDAO = new FuncDAO();
        List<FuncBean> funcList = funcDAO.getFuncAlocList(idTurmaConfig);
        AlocaFuncDAO alocaFuncDAO = new AlocaFuncDAO();
        alocaFuncDAO.alocaFunc(boletimRuricolaBean, funcAlocList, funcList);
        funcDAO.atualFuncAloc(funcAlocList);
        funcList.clear();
    }

    public void salvarBolFechado() {
        BoletimRuricolaDAO boletimRuricolaDAO = new BoletimRuricolaDAO();
        boletimRuricolaDAO.salvarBolFechado();
    }

    public void deleteEnviados(){
        BoletimRuricolaDAO boletimRuricolaDAO = new BoletimRuricolaDAO();
        Long idBol = boletimRuricolaDAO.delBoletim();
        if(idBol > 0L){
            ApontRuricolaDAO apontRuricolaDAO = new ApontRuricolaDAO();
            List<ApontRuricolaBean> apontList = getListApont(idBol);
            apontRuricolaDAO.delListApont(apontList);
            apontList.clear();
            AlocaFuncDAO alocaFuncDAO = new AlocaFuncDAO();
            List<AlocaFuncBean> alocaFuncList = alocaFuncDAO.getListAlocaFunc(idBol);
            alocaFuncDAO.delListAlocaFunc(alocaFuncList);
            alocaFuncList.clear();
        }
    }

    ////////// VERIFICAÇÃO PRA ENVIO ///////////////

    public boolean verBolFechado() {
        BoletimRuricolaDAO boletimRuricolaDAO = new BoletimRuricolaDAO();
        return boletimRuricolaDAO.verBolFechado();
    }

    ////////// DADOS PRA ENVIO ///////////////

    public String dadosEnvioBolFechado(){
        BoletimRuricolaDAO boletimRuricolaDAO = new BoletimRuricolaDAO();
        return boletimRuricolaDAO.dadosEnvioBolFechado();
    }

    public ArrayList<Long> idBolList(){
        BoletimRuricolaDAO boletimRuricolaDAO = new BoletimRuricolaDAO();
        return boletimRuricolaDAO.idBolList();
    }

    public String dadosEnvioApont(ArrayList<Long> idBolList){
        ApontRuricolaDAO apontRuricolaDAO = new ApontRuricolaDAO();
        return apontRuricolaDAO.dadosEnvioApont(apontRuricolaDAO.getListApontEnvio(idBolList));
    }

    public String dadosEnvioAlocaFunc(ArrayList<Long> idBolList){
        AlocaFuncDAO alocaFuncDAO = new AlocaFuncDAO();
        return alocaFuncDAO.dadosEnvioAlocaFunc(alocaFuncDAO.getListAlocaFuncEnvio(idBolList));
    }

    ////////// MANIPULAÇÃO RETORNO DE ENVIO ///////////////

    public void updateDados(String retorno){
        BoletimRuricolaDAO boletimRuricolaDAO = new BoletimRuricolaDAO();
        boletimRuricolaDAO.updateBolAberto(retorno);
    }

    //////////////////////////////////////////////////////////////////////////////////////////

    //////////////////////////////// VERIFICAÇÃO E ATUALIZAÇÃO DE DADOS ////////////////////////////

    public void verOS(String dado, Context telaAtual, Class telaProx, ProgressDialog progressDialog){
        OSDAO osDAO = new OSDAO();
        osDAO.verOS(dado, telaAtual, telaProx, progressDialog);
    }

    public boolean verLider(Long matricLider){
        LiderDAO liderDAO = new LiderDAO();
        return liderDAO.verLider(matricLider);
    }

    public boolean verFunc(Long matricFunc){
        FuncDAO funcDAO = new FuncDAO();
        ConfigCTR configCTR = new ConfigCTR();
        return funcDAO.verFunc(matricFunc, configCTR.getConfig().getIdTurmaConfig());
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    //////////////////// RETORNO DE LISTA DAS ATIVIDADES DA OS /////////////////////////////

        public ArrayList getAtivArrayList(Long nroOS){
            AtividadeDAO atividadeDAO = new AtividadeDAO();
            return atividadeDAO.retAtivArrayList(nroOS);
        }

    //////////////////////////////////////////////////////////////////////////////////////

    //////////////////////////// SALVAR APONTAMENTO ////////////////////////////////////

    public void salvaApont() {
        ConfigCTR configCTR = new ConfigCTR();
        BoletimRuricolaDAO boletimRuricolaDAO = new BoletimRuricolaDAO();
        ApontRuricolaDAO apontRuricolaDAO = new ApontRuricolaDAO();
        String dataHora = Tempo.getInstance().data();
        apontRuricolaDAO.salvaApont(boletimRuricolaDAO.getBolAberto(), configCTR.getConfig(), idParada, dataHora);
        configCTR.setDtUltApontConfig(dataHora);
    }

    public void salvaApont(List<FuncBean> funcBeans) {
        ConfigCTR configCTR = new ConfigCTR();
        BoletimRuricolaDAO boletimRuricolaDAO = new BoletimRuricolaDAO();
        ApontRuricolaDAO apontRuricolaDAO = new ApontRuricolaDAO();
        String dataHora = Tempo.getInstance().data();
        apontRuricolaDAO.salvaApont(boletimRuricolaDAO.getBolAberto(), configCTR.getConfig(), idParada, dataHora, funcBeans);
        configCTR.setDtUltApontConfig(dataHora);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

}
