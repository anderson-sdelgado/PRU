package br.com.usinasantafe.pru.model.bean.estaticas;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.pru.model.pst.Entidade;

/**
 * Created by anderson on 06/09/2017.
 */

@DatabaseTable(tableName="tbfuncest")
public class FuncBean extends Entidade {

    private static final long serialVersionUID = 1L;

    @DatabaseField(generatedId=true)
    private Long idFuncCel;
    @DatabaseField
    private Long idFunc;
    @DatabaseField
    private Long idTurma;
    @DatabaseField
    private Long matricFunc;
    @DatabaseField
    private String nomeFunc;
    @DatabaseField
    private Long tipoAlocaFunc;  //1 - Alocado; 2 - Desalocado

    public FuncBean() {
    }

    public Long getIdFuncCel() {
        return idFuncCel;
    }

    public void setIdFuncCel(Long idFuncCel) {
        this.idFuncCel = idFuncCel;
    }

    public Long getIdFunc() {
        return idFunc;
    }

    public void setIdFunc(Long idFunc) {
        this.idFunc = idFunc;
    }

    public Long getIdTurma() {
        return idTurma;
    }

    public void setIdTurma(Long idTurma) {
        this.idTurma = idTurma;
    }

    public Long getMatricFunc() {
        return matricFunc;
    }

    public void setMatricFunc(Long matricFunc) {
        this.matricFunc = matricFunc;
    }

    public String getNomeFunc() {
        return nomeFunc;
    }

    public void setNomeFunc(String nomeFunc) {
        this.nomeFunc = nomeFunc;
    }

    public Long getTipoAlocaFunc() {
        return tipoAlocaFunc;
    }

    public void setTipoAlocaFunc(Long tipoAlocaFunc) {
        this.tipoAlocaFunc = tipoAlocaFunc;
    }
}
