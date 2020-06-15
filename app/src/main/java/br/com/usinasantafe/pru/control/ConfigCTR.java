package br.com.usinasantafe.pru.control;

import java.util.List;

import br.com.usinasantafe.pru.model.bean.estaticas.TipoApontBean;
import br.com.usinasantafe.pru.model.bean.estaticas.TurmaBean;
import br.com.usinasantafe.pru.model.bean.variaveis.ConfigBean;
import br.com.usinasantafe.pru.model.dao.ConfigDAO;
import br.com.usinasantafe.pru.model.dao.TipoApontDAO;
import br.com.usinasantafe.pru.model.dao.TurmaDAO;

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

    public void salvarConfig(String senha){
        ConfigDAO configDAO = new ConfigDAO();
        configDAO.salvarConfig(senha);
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

    public boolean hasElementsTipoApont(){
        TipoApontDAO tipoApontDAO = new TipoApontDAO();
        return tipoApontDAO.hasElementsTipoApont();
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////

    /////////////////////////////////////// SET CAMPOS ////////////////////////////////////////////

    public void setCheckListConfig(Long idTurno){
        ConfigDAO configDAO = new ConfigDAO();
        configDAO.setCheckListConfig(idTurno);
    }

    public void setDifDthrConfig(Long status){
        ConfigDAO configDAO = new ConfigDAO();
        configDAO.setDifDthrConfig(status);
    }

    public void setStatusConConfig(Long status){
        ConfigDAO configDAO = new ConfigDAO();
        configDAO.setStatusConConfig(status);
    }

    public void setHorimetroConfig(Double horimetro){
        ConfigDAO configDAO = new ConfigDAO();
        configDAO.setHorimetroConfig(horimetro);
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

    public void setVerInforConfig(Long tipo){
        ConfigDAO configDAO = new ConfigDAO();
        configDAO.setVerInforConfig(tipo);
    }

    public void setDtServConfig(String dtServConfig){
        ConfigDAO configDAO = new ConfigDAO();
        configDAO.setDtServConfig(dtServConfig);
    }

    public void setStatusApontConfig(Long statusApont){
        ConfigDAO configDAO = new ConfigDAO();
        configDAO.setStatusApontConfig(statusApont);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////

}
