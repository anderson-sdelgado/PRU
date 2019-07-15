package br.com.usinasantafe.pru.to.tb.estaticas;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.pru.pst.Entidade;

/**
 * Created by anderson on 22/11/2017.
 */

@DatabaseTable(tableName="tbtipoapontaest")
public class TipoApontamentoTO extends Entidade {

    private static final long serialVersionUID = 1L;

    @DatabaseField(id=true)
    private Long idTipo;
    @DatabaseField
    private String descrTipo;

    public TipoApontamentoTO() {
    }

    public Long getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(Long idTipo) {
        this.idTipo = idTipo;
    }

    public String getDescrTipo() {
        return descrTipo;
    }

    public void setDescrTipo(String descrTipo) {
        this.descrTipo = descrTipo;
    }
}
