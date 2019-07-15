package br.com.usinasantafe.pru.to.tb.variaveis;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.pru.pst.Entidade;

/**
 * Created by anderson on 22/11/2017.
 */

@DatabaseTable(tableName="tbalocafuncvar")
public class AlocaFuncTO extends Entidade {

    private static final long serialVersionUID = 1L;

    @DatabaseField(generatedId=true)
    private Long idAlocaFunc;
    @DatabaseField
    private Long idBolAlocaFunc;
    @DatabaseField
    private Long idExtBolAlocaFunc;
    @DatabaseField
    private Long codFuncionarioAlocaFunc;
    @DatabaseField
    private String dthrAlocaFunc;
    @DatabaseField
    private Long tipoAlocaFunc; //1 - Inserido; 2 - Retirado

    public AlocaFuncTO() {
    }

    public Long getIdAlocaFunc() {
        return idAlocaFunc;
    }

    public Long getIdBolAlocaFunc() {
        return idBolAlocaFunc;
    }

    public void setIdBolAlocaFunc(Long idBolAlocaFunc) {
        this.idBolAlocaFunc = idBolAlocaFunc;
    }

    public Long getIdExtBolAlocaFunc() {
        return idExtBolAlocaFunc;
    }

    public void setIdExtBolAlocaFunc(Long idExtBolAlocaFunc) {
        this.idExtBolAlocaFunc = idExtBolAlocaFunc;
    }

    public Long getCodFuncionarioAlocaFunc() {
        return codFuncionarioAlocaFunc;
    }

    public void setCodFuncionarioAlocaFunc(Long codFuncionarioAlocaFunc) {
        this.codFuncionarioAlocaFunc = codFuncionarioAlocaFunc;
    }

    public String getDthrAlocaFunc() {
        return dthrAlocaFunc;
    }

    public void setDthrAlocaFunc(String dthrAlocaFunc) {
        this.dthrAlocaFunc = dthrAlocaFunc;
    }

    public Long getTipoAlocaFunc() {
        return tipoAlocaFunc;
    }

    public void setTipoAlocaFunc(Long tipoAlocaFunc) {
        this.tipoAlocaFunc = tipoAlocaFunc;
    }

}
