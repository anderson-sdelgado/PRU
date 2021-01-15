package br.com.usinasantafe.pru.control;

import android.app.ProgressDialog;
import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pru.view.EnvioDadosActivity;
import br.com.usinasantafe.pru.view.MenuInicialActivity;
import br.com.usinasantafe.pru.model.bean.estaticas.OSBean;
import br.com.usinasantafe.pru.model.bean.estaticas.TipoApontBean;
import br.com.usinasantafe.pru.model.bean.estaticas.TurmaBean;
import br.com.usinasantafe.pru.model.bean.variaveis.ConfigBean;
import br.com.usinasantafe.pru.model.dao.AtividadeDAO;
import br.com.usinasantafe.pru.model.dao.ConfigDAO;
import br.com.usinasantafe.pru.model.dao.FuncDAO;
import br.com.usinasantafe.pru.model.dao.LiderDAO;
import br.com.usinasantafe.pru.model.dao.OSDAO;
import br.com.usinasantafe.pru.model.dao.TipoApontDAO;
import br.com.usinasantafe.pru.model.dao.TurmaDAO;
import br.com.usinasantafe.pru.util.AtualDadosServ;
import br.com.usinasantafe.pru.util.EnvioDadosServ;
import br.com.usinasantafe.pru.util.VerifDadosServ;

public class ConfigCTR {

    public ConfigCTR() {
    }

    ///////////////////////////////////////// CONFIG //////////////////////////////////////////////

    public boolean hasElements(){
        ConfigDAO configDAO = new ConfigDAO();
        return configDAO.hasElements();
    }

    public boolean hasElementsTipoApont(){
        TipoApontDAO tipoApontDAO = new TipoApontDAO();
        return tipoApontDAO.hasElementsTipoApont();
    }

    public void salvarConfig(ConfigBean configBean){
        ConfigDAO configDAO = new ConfigDAO();
        configDAO.salvarConfig(configBean);
    }

    public boolean verLider(Long matricLider){
        LiderDAO liderDAO = new LiderDAO();
        return liderDAO.verLider(matricLider);
    }

    public boolean verFunc(Long matricFunc){
        FuncDAO funcDAO = new FuncDAO();
        return funcDAO.verFunc(matricFunc);
    }

    public void verAtualAplic(String versaoAplic, MenuInicialActivity menuInicialActivity, ProgressDialog progressDialog) {
        VerifDadosServ.getInstance().verAtualAplic(versaoAplic, menuInicialActivity, progressDialog);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////

    //////////////////////////////// GET CONFIG, EQUIP E COLAB ////////////////////////////////////

    public ConfigBean getConfig(){
        ConfigDAO configDAO = new ConfigDAO();
        return configDAO.getConfig();
    }

    public OSBean getOS(){
        OSDAO osDAO = new OSDAO();
        return osDAO.getOS(getConfig().getNroOSConfig());
    }

    public boolean getConfigSenha(String senha){
        ConfigDAO configDAO = new ConfigDAO();
        return configDAO.getConfigSenha(senha);
    }

    public TipoApontBean getTipoApont(Long idTipo){
        TipoApontDAO tipoApontDAO = new TipoApontDAO();
        return tipoApontDAO.getTipoApont(idTipo);
    }

    public TurmaBean getTurma(Long idTurma){
        TurmaDAO turmaDAO = new TurmaDAO();
        return turmaDAO.getTurma(idTurma);
    }

    public List allTipoApont(){
        TipoApontDAO tipoApontDAO = new TipoApontDAO();
        return tipoApontDAO.allTipoApont();
    }

    public List allTurma(){
        TurmaDAO turmaDAO = new TurmaDAO();
        return turmaDAO.allTurma();
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////

    /////////////////////////////////////// SET CAMPOS ////////////////////////////////////////////

    public void setStatusConConfig(Long status){
        ConfigDAO configDAO = new ConfigDAO();
        configDAO.setStatusConConfig(status);
    }

    public void setDtUltApontConfig(String data){
        ConfigDAO configDAO = new ConfigDAO();
        configDAO.setDtUltApontConfig(data);
    }

    public void setOsConfig(Long nroOS){
        ConfigDAO configDAO = new ConfigDAO();
        configDAO.setOsConfig(nroOS);
    }

    public void setAtivConfig(Long idAtiv){
        ConfigDAO configDAO = new ConfigDAO();
        configDAO.setAtivConfig(idAtiv);
    }

    public void setDtServConfig(String dtServConfig){
        ConfigDAO configDAO = new ConfigDAO();
        configDAO.setDtServConfig(dtServConfig);
    }

    public void setPontoAmostraConfig(Long ponto){
        ConfigDAO configDAO = new ConfigDAO();
        configDAO.setPontoAmostraConfig(ponto);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////

    ///////////////////////////// ATUALIZAÇÃO DE DADOS ////////////////////////////////////////////

    public void atualTodasTabelas(Context tela, ProgressDialog progressDialog){
        AtualDadosServ.getInstance().atualTodasTabBD(tela, progressDialog);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////

    public boolean verOS(Long nroOS){

        OSDAO osDAO = new OSDAO();
        return osDAO.verOS(nroOS);

    }

    public void clearBD() {

        OSDAO osDAO = new OSDAO();
        osDAO.deleteAll();

        AtividadeDAO atividadeDAO = new AtividadeDAO();
        atividadeDAO.deleteAll();

    }

    /////////////////////////////////////////////////////////////////////////////////////////

    //////////////////////////////ENVIO DE DADOS/////////////////////////////////////////////

    ////////////////VERIF DADOS/////////////////////

    public boolean verifDadosRuricola(){

        RuricolaCTR ruricolaCTR = new RuricolaCTR();

        boolean verif = false;

        if(ruricolaCTR.verBolFechado()){
            verif = true;
        }

        return verif;
    }

    public boolean verifDadosFito(){

        FitoCTR fitoCTR = new FitoCTR();

        boolean verif = false;

        if(fitoCTR.verCabecFechado()){
            verif = true;
        }

        return verif;
    }

    public boolean verifDadosPerda(){

        PerdaCTR perdaCTR = new PerdaCTR();

        boolean verif = false;

        if(perdaCTR.verCabecFechado()){
            verif = true;
        }

        return verif;
    }

    public boolean verifDadosSoqueira(){

        SoqueiraCTR soqueiraCTR = new SoqueiraCTR();

        boolean verif = false;

        if(soqueiraCTR.verCabecFechado()){
            verif = true;
        }

        return verif;
    }

    /////////////////////////////////////////////////

    ////////////////ENVIO DADOS/////////////////////

    public void envioDados(EnvioDadosActivity envioDadosActivity){
        EnvioDadosServ.getInstance().envioDados(envioDadosActivity);
    }


    public String dadosEnvioRuricola(){

        RuricolaCTR ruricolaCTR = new RuricolaCTR();
        String dadosEnvioBoletim = ruricolaCTR.dadosEnvioBolFechado();
        ArrayList<Long> idBolList = ruricolaCTR.idBolList();
        String dadosEnvioApont = ruricolaCTR.dadosEnvioApont(idBolList);
        String dadosEnvioAlocaFunc = ruricolaCTR.dadosEnvioAlocaFunc(idBolList);
        idBolList.clear();

        return dadosEnvioBoletim + "_" + dadosEnvioApont + "|" + dadosEnvioAlocaFunc;

    }

    public String dadosEnvioFito(){

        FitoCTR fitoCTR = new FitoCTR();
        String dadosEnvioCabec = fitoCTR.dadosEnvioCabecFechado();
        String dadosEnvioResp = fitoCTR.dadosEnvioResp(fitoCTR.idCabecList());

        return dadosEnvioCabec + "_" + dadosEnvioResp;

    }

    public String dadosEnvioPerda(){

        PerdaCTR perdaCTR = new PerdaCTR();
        String dadosEnvioCabec = perdaCTR.dadosEnvioCabecFechado();
        String dadosEnvioAmostra = perdaCTR.dadosEnvioAmostra(perdaCTR.idCabecList());

        return dadosEnvioCabec + "_" + dadosEnvioAmostra;

    }

    public String dadosEnvioSoqueira(){

        SoqueiraCTR soqueiraCTR = new SoqueiraCTR();
        String dadosEnvioCabec = soqueiraCTR.dadosEnvioCabecFechado();
        String dadosEnvioAmostra = soqueiraCTR.dadosEnvioAmostra(soqueiraCTR.idCabecList());

        return dadosEnvioCabec + "_" + dadosEnvioAmostra;

    }

    /////////////////////////////////////////////////

    ////////////////////////////// ATUALIZAÇÃO DE DADOS POR CLASSE /////////////////////////////////////

    public void atualDadosParada(Context telaAtual, Class telaProx, ProgressDialog progressDialog){
        ArrayList operadorArrayList = new ArrayList();
        operadorArrayList.add("ParadaBean");
        AtualDadosServ.getInstance().atualGenericoBD(telaAtual, telaProx, progressDialog, operadorArrayList);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    //////////////////////////////// VERIFICAÇÃO E ATUALIZAÇÃO DE DADOS ////////////////////////////

    public void verOS(String dado, Context telaAtual, Class telaProx, ProgressDialog progressDialog){
        OSDAO osDAO = new OSDAO();
        osDAO.verOS(dado, telaAtual, telaProx, progressDialog);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

}
