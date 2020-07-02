package br.com.usinasantafe.pru.model.bean.variaveis;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.pru.model.pst.Entidade;

/**
 * Created by anderson on 22/11/2017.
 */

@DatabaseTable(tableName="tbalocafuncvar")
public class AlocaFuncBean extends Entidade {

    private static final long serialVersionUID = 1L;

    @DatabaseField(generatedId=true)
    private Long idAlocaFunc;
    @DatabaseField
    private Long idBolAlocaFunc;
    @DatabaseField
    private Long idExtBolAlocaFunc;
    @DatabaseField
    private Long matricFuncAlocaFunc;
    @DatabaseField
    private String dthrAlocaFunc;
    @DatabaseField
    private Long tipoAlocaFunc; //1 - Inserido; 2 - Retirado

    public AlocaFuncBean() {
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

    public Long getMatricFuncAlocaFunc() {
        return matricFuncAlocaFunc;
    }

    public void setMatricFuncAlocaFunc(Long matricFuncAlocaFunc) {
        this.matricFuncAlocaFunc = matricFuncAlocaFunc;
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
