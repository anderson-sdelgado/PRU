package br.com.usinasantafe.pru.control;

import android.app.ProgressDialog;
import android.content.Context;

import java.util.List;

import br.com.usinasantafe.pru.model.bean.estaticas.TipoApontBean;
import br.com.usinasantafe.pru.model.bean.estaticas.TurmaBean;
import br.com.usinasantafe.pru.model.bean.variaveis.ConfigBean;
import br.com.usinasantafe.pru.model.dao.ConfigDAO;
import br.com.usinasantafe.pru.model.dao.FuncDAO;
import br.com.usinasantafe.pru.model.dao.LiderDAO;
import br.com.usinasantafe.pru.model.dao.TipoApontDAO;
import br.com.usinasantafe.pru.model.dao.TurmaDAO;
import br.com.usinasantafe.pru.util.AtualDadosServ;

public class ConfigCTR {

    public ConfigCTR() {
    }

    ///////////////////////////////////////// CONFIG //////////////////////////////////////////////

    public boolean hasElements(){
        ConfigDAO configDAO = new ConfigDAO();
        return configDAO.hasElements();
    }

    public ConfigBean getConfig(){
        ConfigDAO configDAO = new ConfigDAO();
        return configDAO.getConfig();
    }

    public boolean hasElementsTipoApont(){
        TipoApontDAO tipoApontDAO = new TipoApontDAO();
        return tipoApontDAO.hasElementsTipoApont();
    }

    public void salvarConfig(String senha){
        ConfigDAO configDAO = new ConfigDAO();
        configDAO.salvarConfig(senha);
    }

    public boolean verLider(Long codLider){
        LiderDAO liderDAO = new LiderDAO();
        return liderDAO.verLider(codLider);
    }

    public boolean verFunc(Long codFunc){
        FuncDAO funcDAO = new FuncDAO();
        return funcDAO.verFunc(codFunc);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////


    //////////////////////////////// GET CONFIG, EQUIP E COLAB ////////////////////////////////////

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

    ///////////////////////////////////////////////////////////////////////////////////////////////

    ///////////////////////////// ATUALIZAÇÃO DE DADOS ////////////////////////////////////////////

    public void atualTodasTabelas(Context tela, ProgressDialog progressDialog){
        AtualDadosServ.getInstance().atualTodasTabBD(tela, progressDialog);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////

}
