package br.com.usinasantafe.pru.control;

import android.app.ProgressDialog;
import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pru.model.bean.estaticas.FuncBean;
import br.com.usinasantafe.pru.model.bean.estaticas.RFuncaoAtivParBean;
import br.com.usinasantafe.pru.model.bean.variaveis.BoletimRuricolaBean;
import br.com.usinasantafe.pru.model.bean.variaveis.ConfigBean;
import br.com.usinasantafe.pru.model.dao.AlocaFuncDAO;
import br.com.usinasantafe.pru.model.dao.ApontDAO;
import br.com.usinasantafe.pru.model.dao.AtivDAO;
import br.com.usinasantafe.pru.model.dao.BoletimDAO;
import br.com.usinasantafe.pru.model.dao.FuncDAO;
import br.com.usinasantafe.pru.model.dao.LiderDAO;
import br.com.usinasantafe.pru.model.dao.OSDAO;
import br.com.usinasantafe.pru.model.dao.ParadaDAO;
import br.com.usinasantafe.pru.model.dao.RFuncaoAtivParDAO;
import br.com.usinasantafe.pru.util.Tempo;

public class RuricolaCTR {

    private BoletimRuricolaBean boletimRuricolaBean;
    private Long idParada;

    public RuricolaCTR() {
    }

    public boolean verBolAberto(){
        BoletimDAO boletimDAO = new BoletimDAO();
        return boletimDAO.verBolAberto();
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

    public BoletimRuricolaBean getBolAberto(){
        BoletimDAO boletimDAO = new BoletimDAO();
        return boletimDAO.getBolAberto();
    }

    public FuncBean getFunc(){
        FuncDAO funcDAO = new FuncDAO();
        return funcDAO.getFunc(getBolAberto().getIdLiderBol());
    }

    public RFuncaoAtivParBean getFuncaoAtivParBean(Long idAtiv){
        RFuncaoAtivParDAO rFuncaoAtivParDAO = new RFuncaoAtivParDAO();
        return rFuncaoAtivParDAO.getRFuncaoAtivPar(idAtiv);
    }

    /////////////////////////////////////////////////////////////////////////////////////

    //////////////////////// MANIPULAR DADOS BOLETIM //////////////////////////////////////

    ///////////// SALVAR DADOS ///////////////

    public void salvarBolAberto(){
        ConfigCTR configCTR = new ConfigCTR();
        ConfigBean configBean = configCTR.getConfig();
        BoletimRuricolaBean boletimRuricolaBean = new BoletimRuricolaBean();
        AlocaFuncDAO alocaFuncDAO = new AlocaFuncDAO();
        boletimRuricolaBean.setIdLiderBol(configBean.getMatricFuncConfig());
        boletimRuricolaBean = salvarBolAberto(boletimRuricolaBean, configBean);
        alocaFuncDAO.alocaFunc(boletimRuricolaBean);
    }

    public void salvarBolAberto(Long matricColab){
        ConfigCTR configCTR = new ConfigCTR();
        ConfigBean configBean = configCTR.getConfig();
        BoletimRuricolaBean boletimRuricolaBean = new BoletimRuricolaBean();
        AlocaFuncDAO alocaFuncDAO = new AlocaFuncDAO();
        boletimRuricolaBean.setIdLiderBol(matricColab);
        boletimRuricolaBean = salvarBolAberto(boletimRuricolaBean, configBean);
        alocaFuncDAO.alocaFunc(boletimRuricolaBean);
    }

    public void salvarBolAberto(List funcAlocList){
        ConfigCTR configCTR = new ConfigCTR();
        ConfigBean configBean = configCTR.getConfig();
        BoletimRuricolaBean boletimRuricolaBean = new BoletimRuricolaBean();
        boletimRuricolaBean.setIdLiderBol(configBean.getMatricFuncConfig());
        boletimRuricolaBean = salvarBolAberto(boletimRuricolaBean, configBean);
        alocaFunc(funcAlocList, boletimRuricolaBean, configBean.getIdTurmaConfig());
    }

    private BoletimRuricolaBean salvarBolAberto(BoletimRuricolaBean boletimRuricolaBean, ConfigBean configBean){
        boletimRuricolaBean.setOsBol(configBean.getNroOSConfig());
        boletimRuricolaBean.setAtivPrincBol(configBean.getIdAtivConfig());
        boletimRuricolaBean.setIdTurmaBol(configBean.getIdTurmaConfig());
        boletimRuricolaBean.setTipoFuncBol(configBean.getIdTipoConfig());
        BoletimDAO boletimDAO = new BoletimDAO();
        boletimDAO.salvarBolAberto(boletimRuricolaBean);
        return boletimDAO.getBolAberto();
    }

    public void alocaFunc(List funcAlocList){
        BoletimDAO boletimDAO = new BoletimDAO();
        ConfigCTR configCTR = new ConfigCTR();
        ConfigBean configBean = configCTR.getConfig();
        alocaFunc(funcAlocList, boletimDAO.getBolAberto(), configBean.getIdTurmaConfig());
    }

    private void alocaFunc(List funcAlocList, BoletimRuricolaBean boletimRuricolaBean, Long idTurmaConfig){
        AlocaFuncDAO alocaFuncDAO = new AlocaFuncDAO();
        FuncDAO funcDAO = new FuncDAO();
        List funcList = funcDAO.getFuncAlocList(idTurmaConfig);
        alocaFuncDAO.alocaFunc(boletimRuricolaBean, funcAlocList, funcList);
        funcDAO.atualFuncAloc(funcAlocList);
        funcList.clear();
    }

    public void salvarBolFechado() {
        BoletimDAO boletimDAO = new BoletimDAO();
        boletimDAO.salvarBolFechado();
    }

    ////////// VERIFICAÇÃO PRA ENVIO ///////////////

    public boolean verBolFechado() {
        BoletimDAO boletimDAO = new BoletimDAO();
        return boletimDAO.verCabecFechado();
    }

    ////////// DADOS PRA ENVIO ///////////////

    public String dadosEnvioBolFechado(){
        BoletimDAO boletimDAO = new BoletimDAO();
        return boletimDAO.dadosEnvioBolFechado();
    }

    public ArrayList<Long> idBolList(){
        BoletimDAO boletimDAO = new BoletimDAO();
        return boletimDAO.idBolList();
    }

    public String dadosEnvioApont(ArrayList<Long> idBolList){
        ApontDAO apontDAO = new ApontDAO();
        return apontDAO.dadosEnvioApont(apontDAO.getListApontEnvio(idBolList));
    }

    ////////// MANIPULAÇÃO RETORNO DE ENVIO ///////////////

//    public void delBolFechadoMM(String retorno) {
//        BoletimMMDAO boletimMMDAO = new BoletimMMDAO();
//        boletimMMDAO.deleteBolFechado(retorno);
//    }

    //////////////////////////////////////////////////////////////////////////////////////////

    //////////////////////// MANIPULAR APONT DADOS MOTOMEC ////////////////////////////////////

    ////////// DADOS PRA ENVIO ///////////////



    ////////// MANIPULAÇÃO RETORNO DE ENVIO ///////////////
//
//    public void updateApontMM(String retorno) {
//        ApontMMDAO apontMMDAO = new ApontMMDAO();
//        apontMMDAO.updateApont(retorno);
//    }

    /////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////// ATUALIZAÇÃO DE DADOS POR CLASSE /////////////////////////////////////
//
//    public void atualDadosFunc(Context telaAtual, Class telaProx, ProgressDialog progressDialog){
//        ArrayList operadorArrayList = new ArrayList();
//        operadorArrayList.add("MotoristaBean");
//        AtualDadosServ.getInstance().atualGenericoBD(telaAtual, telaProx, progressDialog, operadorArrayList);
//    }
//
//    public void atualDadosTurno(Context telaAtual, Class telaProx, ProgressDialog progressDialog) {
//        ArrayList turnoArrayList = new ArrayList();
//        turnoArrayList.add("TurnoBean");
//        AtualDadosServ.getInstance().atualGenericoBD(telaAtual, telaProx, progressDialog, turnoArrayList);
//    }

    /////////////////////////////////////////////////////////////////////////////////////////////////

    //////////////////////////////// VERIFICAÇÃO E ATUALIZAÇÃO DE DADOS ////////////////////////////

    public void verOS(String dado, Context telaAtual, Class telaProx, ProgressDialog progressDialog){
        OSDAO osDAO = new OSDAO();
        osDAO.verOS(dado, telaAtual, telaProx, progressDialog);
    }
//
//    public void verAtiv(String dado, Context telaAtual, Class telaProx, ProgressDialog progressDialog){
//        ConfigCTR configCTR = new ConfigCTR();
//        AtividadeDAO atividadeDAO = new AtividadeDAO();
//        atividadeDAO.verAtiv(dado  + "_" + configCTR.getEquip().getNroEquip(), telaAtual, telaProx, progressDialog);
//    }

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
            AtivDAO ativDAO = new AtivDAO();
            return ativDAO.retAtivArrayList(nroOS);
        }

    //////////////////////////////////////////////////////////////////////////////////////

    //////////////////////////// SALVAR APONTAMENTO ////////////////////////////////////

    public void salvaApont() {
        ConfigCTR configCTR = new ConfigCTR();
        BoletimDAO boletimDAO = new BoletimDAO();
        ApontDAO apontDAO = new ApontDAO();
        String dataHora = Tempo.getInstance().data();
        apontDAO.salvaApont(boletimDAO.getBolAberto(), configCTR.getConfig(), idParada, dataHora);
        configCTR.setDtUltApontConfig(dataHora);
    }

    public void salvaApont(List<FuncBean> funcBeans) {
        ConfigCTR configCTR = new ConfigCTR();
        BoletimDAO boletimDAO = new BoletimDAO();
        ApontDAO apontDAO = new ApontDAO();
        String dataHora = Tempo.getInstance().data();
        apontDAO.salvaApont(boletimDAO.getBolAberto(), configCTR.getConfig(), idParada, dataHora, funcBeans);
        configCTR.setDtUltApontConfig(dataHora);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

}
