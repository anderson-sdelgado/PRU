package br.com.usinasantafe.pru.control;

import android.app.ProgressDialog;
import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pru.model.bean.variaveis.BoletimBean;
import br.com.usinasantafe.pru.model.bean.variaveis.ConfigBean;
import br.com.usinasantafe.pru.model.dao.AlocaFuncDAO;
import br.com.usinasantafe.pru.model.dao.AtividadeDAO;
import br.com.usinasantafe.pru.model.dao.BoletimDAO;
import br.com.usinasantafe.pru.model.dao.FuncDAO;
import br.com.usinasantafe.pru.model.dao.LiderDAO;
import br.com.usinasantafe.pru.model.dao.OSDAO;

public class RuricolaCTR {

    private BoletimBean boletimBean;

    public RuricolaCTR() {
    }

    public boolean verBolAberto(){
        BoletimDAO boletimDAO = new BoletimDAO();
        return boletimDAO.verBolAberto();
    }


//    public void atualApont(){
//        ApontDAO apontMMDAO = new ApontMMDAO();
//        ApontMMBean apontMMBean = apontMMDAO.getApontMMAberto();
//        apontMMDAO.updApont(apontMMBean);
//    }

    //////////////////////////// SETAR CAMPOS ///////////////////////////////////////////////

//    public void setFuncBol(Long matric){
//        boletimMMBean = new BoletimMMBean();
//        boletimMMBean.setMatricFuncBolMM(matric);
//    }
//
//    public void setEquipBol(){
//        ConfigCTR configCTR = new ConfigCTR();
//        boletimMMBean.setIdEquipBolMM(configCTR.getEquip().getIdEquip());
//    }
//
//    public void setTurnoBol(Long idTurno){
//        boletimMMBean.setIdTurnoBolMM(idTurno);
//    }

//    public void setMotoMecBean(MotoMecBean motoMecBean) {
//        this.motoMecBean = motoMecBean;
//    }

    /////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////// GET DE CAMPOS ///////////////////////////////////////////
//
//    public Long getTurno(){
//        return boletimMMBean.getIdTurnoBolMM();
//    }
//
//    public Long getFunc(){
//        BoletimMMDAO boletimMMDAO = new BoletimMMDAO();
//        return boletimMMDAO.getMatricNomeFunc().getMatricFunc();
//    }
//
//    public FuncionarioBean getMatricNomeFunc(){
//        BoletimMMDAO boletimMMDAO = new BoletimMMDAO();
//        return boletimMMDAO.getMatricNomeFunc();
//    }

    public List getFuncAlocList() {
        ConfigCTR configCTR = new ConfigCTR();
        FuncDAO funcDAO = new FuncDAO();
        return funcDAO.getFuncAlocList(configCTR.getConfig().getIdTurmaConfig());
    }

    /////////////////////////////////////////////////////////////////////////////////////

    //////////////////////// MANIPULAR DADOS BOLETIM //////////////////////////////////////

    ///////////// SALVAR DADOS ///////////////

    public void salvarBolAberto(){
        ConfigCTR configCTR = new ConfigCTR();
        ConfigBean configBean = configCTR.getConfig();
        BoletimBean boletimBean = new BoletimBean();
        AlocaFuncDAO alocaFuncDAO = new AlocaFuncDAO();
        boletimBean.setIdLiderBol(configBean.getMatricFuncConfig());
        boletimBean = salvarBolAberto(boletimBean, configBean);
        alocaFuncDAO.alocaFunc(boletimBean);
    }

    public void salvarBolAberto(Long matricColab){
        ConfigCTR configCTR = new ConfigCTR();
        ConfigBean configBean = configCTR.getConfig();
        BoletimBean boletimBean = new BoletimBean();
        AlocaFuncDAO alocaFuncDAO = new AlocaFuncDAO();
        boletimBean.setIdLiderBol(matricColab);
        boletimBean = salvarBolAberto(boletimBean, configBean);
        alocaFuncDAO.alocaFunc(boletimBean);
    }

    private BoletimBean salvarBolAberto(BoletimBean boletimBean, ConfigBean configBean){
        boletimBean.setOsBol(configBean.getNroOSConfig());
        boletimBean.setAtivPrincBol(configBean.getIdAtivConfig());
        boletimBean.setIdTurmaBol(configBean.getIdTurmaConfig());
        boletimBean.setTipoFuncBol(configBean.getIdTipoConfig());
        BoletimDAO boletimDAO = new BoletimDAO();
        boletimDAO.salvarBolAberto1Colab(boletimBean);
        return boletimDAO.getBolAberto();
    }

//
//    public void salvarBolFechadoMM(){
//        BoletimMMDAO boletimMMDAO = new BoletimMMDAO();
//        boletimMMDAO.salvarBolFechado(boletimMMBean);
//    }

    ////////// VERIFICAÇÃO PRA ENVIO ///////////////
//
//    public boolean verEnvioBolAbertoMM(){
//        BoletimMMDAO boletimMMDAO = new BoletimMMDAO();
//        return boletimMMDAO.bolAbertoSemEnvioList().size() > 0;
//    }
//
//    public boolean verEnvioBolFechMM() {
//        BoletimMMDAO boletimMMDAO = new BoletimMMDAO();
//        return boletimMMDAO.bolFechadoList().size() > 0;
//    }

    ////////// DADOS PRA ENVIO ///////////////
//
//    public String dadosEnvioBolAbertoMM(){
//        BoletimMMDAO boletimMMDAO = new BoletimMMDAO();
//        return boletimMMDAO.dadosEnvioBolAberto();
//    }
//
//    public String dadosEnvioBolFechadoMM(){
//        BoletimMMDAO boletimMMDAO = new BoletimMMDAO();
//        return boletimMMDAO.dadosEnvioBolFechado();
//    }

    ////////// MANIPULAÇÃO RETORNO DE ENVIO ///////////////
//
//    public void updBolAbertoMM(String retorno){
//        BoletimMMDAO boletimMMDAO = new BoletimMMDAO();
//        boletimMMDAO.updateBolAberto(retorno);
//    }
//
//    public void delBolFechadoMM(String retorno) {
//        BoletimMMDAO boletimMMDAO = new BoletimMMDAO();
//        boletimMMDAO.deleteBolFechado(retorno);
//    }

    //////////////////////////////////////////////////////////////////////////////////////////

    //////////////////////// MANIPULAR APONT DADOS MOTOMEC ////////////////////////////////////

    ////////// VERIFICAÇÃO PRA ENVIO ///////////////
//
//    public Boolean verEnvioDadosApont(){
//        ApontMMDAO apontMMDAO = new ApontMMDAO();
//        return (apontMMDAO.getListApontEnvio().size() > 0);
//    }

    ////////// DADOS PRA ENVIO ///////////////
//
//    public String dadosEnvioApontBolMM(Long idBol){
//        ApontMMDAO apontMMDAO = new ApontMMDAO();
//        return apontMMDAO.dadosEnvioApontMM(apontMMDAO.getListApontEnvio(idBol));
//    }
//
//    public String dadosEnvioApontMM(){
//        ApontMMDAO apontMMDAO = new ApontMMDAO();
//        return apontMMDAO.dadosEnvioApontMM(apontMMDAO.getListApontEnvio());
//    }

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
            ConfigCTR configCTR = new ConfigCTR();
            AtividadeDAO atividadeDAO = new AtividadeDAO();
            return atividadeDAO.retAtivArrayList(nroOS);
        }

    //////////////////////////////////////////////////////////////////////////////////////

    //////////////////////////// CRIAR E ATUALIZAR APONTAMENTO ////////////////////////////////////
//
//    public void insApontMM(Double longitude, Double latitude, Long statusCon){
//
//        ApontMMDAO apontMMDAO = new ApontMMDAO();
//        ConfigCTR configCTR = new ConfigCTR();
//        BoletimMMDAO boletimMMDAO = new BoletimMMDAO();
//        apontMMDAO.salvarApont(motoMecBean, configCTR.getConfig(), boletimMMDAO.getBolMMAberto(), longitude, latitude, statusCon);
//
//        atualQtdeApontBol();
//
//        configCTR.setDtUltApontConfig(Tempo.getInstance().dataComHora().getDataHora());
//
//    }
//
//    public boolean verifBackupApont() {
//        ConfigCTR configCTR = new ConfigCTR();
//        ApontMMDAO apontMMDAO = new ApontMMDAO();
//        return apontMMDAO.verifBackupApont(motoMecBean, configCTR.getConfig());
//    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    /////////////////// ATUALIZAR QTDE DE APONTAMENTO DO BOLETIM ///////////////////////////
//
//    public void atualQtdeApontBol(){
//        BoletimMMDAO boletimMMDAO = new BoletimMMDAO();
//        boletimMMDAO.atualQtdeApontBol();
//    }

    ////////////////////////////////////////////////////////////////////////////////////



}
