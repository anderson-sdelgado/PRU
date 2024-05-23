package br.com.usinasantafe.pru.control;

import android.app.ProgressDialog;
import android.content.Context;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pru.model.bean.AtualAplicBean;
import br.com.usinasantafe.pru.model.bean.estaticas.ROSAtivBean;
import br.com.usinasantafe.pru.model.dao.AtualAplicDAO;
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
import br.com.usinasantafe.pru.view.TelaInicialActivity;

public class ConfigCTR {

    public ConfigCTR() {
    }

    ///////////////////////////////////////// CONFIG //////////////////////////////////////////////

    public boolean hasElemConfig(){
        ConfigDAO configDAO = new ConfigDAO();
        return configDAO.hasElements();
    }

    public boolean hasElementsTipoApont(){
        TipoApontDAO tipoApontDAO = new TipoApontDAO();
        return tipoApontDAO.hasElementsTipoApont();
    }

    public void salvarConfig(Long nroAparelho){
        ConfigDAO configDAO = new ConfigDAO();
        configDAO.salvarConfig(nroAparelho);
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

    ///////////////////////////////////////////////////////////////////////////////////////////////

    //////////////////////////////// GET CONFIG, EQUIP E COLAB ////////////////////////////////////

    public ConfigBean getConfig(){
        ConfigDAO configDAO = new ConfigDAO();
        return configDAO.getConfig();
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

    public List<TipoApontBean> allTipoApont(){
        TipoApontDAO tipoApontDAO = new TipoApontDAO();
        return tipoApontDAO.allTipoApont();
    }

    public List<TurmaBean> allTurma(){
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

    public void setPontoAmostraConfig(Long ponto){
        ConfigDAO configDAO = new ConfigDAO();
        configDAO.setPontoAmostraConfig(ponto);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////

    ///////////////////////////////////// ATUALIZAR APLIC /////////////////////////////////////////

    public void verToken(String versao, Long nroAparelho, Context telaAtual, ProgressDialog progressDialog){
        AtualAplicDAO atualAplicDAO = new AtualAplicDAO();
        VerifDadosServ.getInstance().verifDados(atualAplicDAO.dadosAplic(nroAparelho, versao), telaAtual, progressDialog);
    }

    public void recToken(String result, Context telaAtual, ProgressDialog progressDialog) {

        AtualAplicBean atualAplicBean = new AtualAplicBean();

        try {

            JSONObject jObj = new JSONObject(result);
            JSONArray jsonArray = jObj.getJSONArray("dados");

            if (jsonArray.length() > 0) {
                AtualAplicDAO atualAplicDAO = new AtualAplicDAO();
                atualAplicBean = atualAplicDAO.recAparelho(jsonArray);
            }

            salvarConfig(atualAplicBean.getNroAparelho());
            progressDialog.dismiss();
            progressDialog = new ProgressDialog(telaAtual);
            progressDialog.setCancelable(true);
            progressDialog.setMessage("ATUALIZANDO ...");
            progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            progressDialog.setProgress(0);
            progressDialog.setMax(100);
            progressDialog.show();

            AtualDadosServ.getInstance().atualTodasTabBD(telaAtual, progressDialog);

        } catch (Exception e) {
            VerifDadosServ.getInstance().msgSemTerm("FALHA EM SALVAR DADOS DO APARELHO! POR FAVOR, TENTAR NOVAMENTE COM UM SINAL MELHOR.");
        }
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

    /////////////////////////////////////////////////

    ////////////////ENVIO DADOS/////////////////////

    public String dadosEnvioRuricola(){

        RuricolaCTR ruricolaCTR = new RuricolaCTR();
        String dadosEnvioBoletim = ruricolaCTR.dadosEnvioBolFechado();
        ArrayList<Long> idBolList = ruricolaCTR.idBolList();
        String dadosEnvioApont = ruricolaCTR.dadosEnvioApont(idBolList);
        String dadosEnvioAlocaFunc = ruricolaCTR.dadosEnvioAlocaFunc(idBolList);
        idBolList.clear();

        return dadosEnvioBoletim + "_" + dadosEnvioApont + "|" + dadosEnvioAlocaFunc;

    }


    /////////////////////////////////////////////////

    ////////////////////////////// ATUALIZAÇÃO DE DADOS POR CLASSE /////////////////////////////////////

    public void atualDadosParada(Context telaAtual, Class telaProx, ProgressDialog progressDialog){
        ArrayList classeArrayList = new ArrayList();
        classeArrayList.add("ParadaBean");
        AtualDadosServ.getInstance().atualGenericoBD(telaAtual, telaProx, progressDialog, classeArrayList);
    }

    public void atualDadosEquip(Context telaAtual, Class telaProx, ProgressDialog progressDialog){
        ArrayList classeArrayList = new ArrayList();
        classeArrayList.add("EquipBean");
        AtualDadosServ.getInstance().atualGenericoBD(telaAtual, telaProx, progressDialog, classeArrayList);
    }

    public void atualDadosFunc(Context telaAtual, Class telaProx, ProgressDialog progressDialog){
        ArrayList classeArrayList = new ArrayList();
        classeArrayList.add("LiderBean");
        classeArrayList.add("FuncBean");
        AtualDadosServ.getInstance().atualGenericoBD(telaAtual, telaProx, progressDialog, classeArrayList);
    }

    public void atualDadosCaracOrgan(Context telaAtual, Class telaProx, ProgressDialog progressDialog){
        ArrayList classeArrayList = new ArrayList();
        classeArrayList.add("ROCAFitoBean");
        classeArrayList.add("CaracOrganFitoBean");
        AtualDadosServ.getInstance().atualGenericoBD(telaAtual, telaProx, progressDialog, classeArrayList);
    }

    public void atualDadosAlocFunc(Context telaAtual, Class telaProx, ProgressDialog progressDialog){
        ArrayList classeArrayList = new ArrayList();
        classeArrayList.add("FuncBean");
        AtualDadosServ.getInstance().atualGenericoBD(telaAtual, telaProx, progressDialog, classeArrayList);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    //////////////////////////////// VERIFICAÇÃO E ATUALIZAÇÃO DE DADOS ////////////////////////////

    public void verOS(String nroOS, Context telaAtual, Class telaProx, ProgressDialog progressDialog){
        OSDAO osDAO = new OSDAO();
        AtualAplicDAO atualAplicDAO = new AtualAplicDAO();
        osDAO.verOS(atualAplicDAO.getAtualNroOS(Long.parseLong(nroOS)), telaAtual, telaProx, progressDialog);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////
}
