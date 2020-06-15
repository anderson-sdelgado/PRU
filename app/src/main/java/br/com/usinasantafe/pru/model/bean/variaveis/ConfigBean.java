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

    @DatabaseField(id=true)
    private Long idTipo;
    @DatabaseField
    private Long idTurma;
    @DatabaseField
    private Long codFunc;
    @DatabaseField
    private String dtUltApontConfig;
    @DatabaseField
    private Long numLinha;

    public ConfigBean() {
    }

    public Long getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(Long idTipo) {
        this.idTipo = idTipo;
    }

    public Long getIdTurma() {
        return idTurma;
    }

    public void setIdTurma(Long idTurma) {
        this.idTurma = idTurma;
    }

    public Long getCodFunc() {
        return codFunc;
    }

    public void setCodFunc(Long codFunc) {
        this.codFunc = codFunc;
    }

    public String getDtUltApontConfig() {
        return dtUltApontConfig;
    }

    public void setDtUltApontConfig(String dtUltApontConfig) {
        this.dtUltApontConfig = dtUltApontConfig;
    }

    public Long getNumLinha() {
        return numLinha;
    }

    public void setNumLinha(Long numLinha) {
        this.numLinha = numLinha;
    }
}
