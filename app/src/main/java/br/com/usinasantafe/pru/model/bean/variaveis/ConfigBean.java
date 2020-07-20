package br.com.usinasantafe.pru.model.bean.variaveis;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.pru.model.pst.Entidade;

/**
 * Created by anderson on 22/11/2017.
 */
@DatabaseTable(tableName="tbconfigvar")
public class ConfigBean extends Entidade {

    private static final long serialVersionUID = 1L;

    @DatabaseField(generatedId=true)
    private Long idConfig;
    @DatabaseField
    private Long idTipoConfig;
    @DatabaseField
    private Long idTurmaConfig;
    @DatabaseField
    private Long matricFuncConfig;
    @DatabaseField
    private String dtUltApontConfig;
    @DatabaseField
    private Long numLinhaConfig;
    @DatabaseField
    private String senhaConfig;
    @DatabaseField
    private Long nroOSConfig;
    @DatabaseField
    private Long idAtivConfig;
    @DatabaseField
    private Long statusConConfig;  //0 - Offline; 1 - Online
    @DatabaseField
    private String dtServConfig;
    @DatabaseField
    private Long pontoConfig;

    public ConfigBean() {
    }

    public Long getIdTipoConfig() {
        return idTipoConfig;
    }

    public void setIdTipoConfig(Long idTipoConfig) {
        this.idTipoConfig = idTipoConfig;
    }

    public Long getIdTurmaConfig() {
        return idTurmaConfig;
    }

    public void setIdTurmaConfig(Long idTurmaConfig) {
        this.idTurmaConfig = idTurmaConfig;
    }

    public Long getMatricFuncConfig() {
        return matricFuncConfig;
    }

    public void setMatricFuncConfig(Long matricFuncConfig) {
        this.matricFuncConfig = matricFuncConfig;
    }

    public String getDtUltApontConfig() {
        return dtUltApontConfig;
    }

    public void setDtUltApontConfig(String dtUltApontConfig) {
        this.dtUltApontConfig = dtUltApontConfig;
    }

    public Long getNumLinhaConfig() {
        return numLinhaConfig;
    }

    public void setNumLinhaConfig(Long numLinhaConfig) {
        this.numLinhaConfig = numLinhaConfig;
    }

    public String getSenhaConfig() {
        return senhaConfig;
    }

    public void setSenhaConfig(String senhaConfig) {
        this.senhaConfig = senhaConfig;
    }

    public Long getNroOSConfig() {
        return nroOSConfig;
    }

    public void setNroOSConfig(Long nroOSConfig) {
        this.nroOSConfig = nroOSConfig;
    }

    public Long getIdAtivConfig() {
        return idAtivConfig;
    }

    public void setIdAtivConfig(Long idAtivConfig) {
        this.idAtivConfig = idAtivConfig;
    }

    public Long getStatusConConfig() {
        return statusConConfig;
    }

    public void setStatusConConfig(Long statusConConfig) {
        this.statusConConfig = statusConConfig;
    }

    public String getDtServConfig() {
        return dtServConfig;
    }

    public void setDtServConfig(String dtServConfig) {
        this.dtServConfig = dtServConfig;
    }

    public Long getPontoConfig() {
        return pontoConfig;
    }

    public void setPontoConfig(Long pontoConfig) {
        this.pontoConfig = pontoConfig;
    }
}
