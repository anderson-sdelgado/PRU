package br.com.usinasantafe.pru.model.dao;

import java.util.List;

import br.com.usinasantafe.pru.model.bean.variaveis.ConfigBean;

public class ConfigDAO {

    public ConfigDAO() {
    }

    public boolean hasElements(){
        ConfigBean configBean = new ConfigBean();
        return configBean.hasElements();
    }

    public ConfigBean getConfig(){
        ConfigBean configBean = new ConfigBean();
        List configList = configBean.all();
        configBean = (ConfigBean) configList.get(0);
        configList.clear();
        return configBean;
    }

    public void salvarConfig(Long nroAparelho){
        ConfigBean configBean = new ConfigBean();
        configBean.deleteAll();
        configBean.setNroAparelhoConfig(nroAparelho);
        configBean.insert();
        configBean.commit();
    }

    public void salvarConfig(ConfigBean configBean){
        ConfigBean configBeanBD = getConfig();
        configBean.deleteAll();
        configBean.setNroAparelhoConfig(configBeanBD.getNroAparelhoConfig());
        configBean.setDtUltApontConfig("");
        configBean.insert();
        configBean.commit();
    }

    public void setStatusConConfig(Long status){
        ConfigBean configBean = getConfig();
        configBean.setStatusConConfig(status);
        configBean.update();
    }

    public void setDtUltApontConfig(String data){
        ConfigBean configBean = getConfig();
        configBean.setDtUltApontConfig(data);
        configBean.update();
    }

    public boolean getConfigSenha(String senha){
        ConfigBean configBean = new ConfigBean();
        List configList = configBean.get("senhaConfig", senha);
        boolean ret = configList.size() > 0;
        configList.clear();
        return ret;
    }

    public void setOsConfig(Long nroOS){
        ConfigBean configBean = getConfig();
        configBean.setNroOSConfig(nroOS);
        configBean.update();
    }

    public void setAtivConfig(Long idAtiv){
        ConfigBean configBean = getConfig();
        configBean.setIdAtivConfig(idAtiv);
        configBean.update();
    }

    public void setPontoAmostraConfig(Long ponto){
        ConfigBean configBean = getConfig();
        configBean.setPontoAmostraConfig(ponto);
        configBean.update();
    }


}
