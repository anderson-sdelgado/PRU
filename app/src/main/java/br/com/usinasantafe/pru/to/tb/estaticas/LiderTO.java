package br.com.usinasantafe.pru.to.tb.estaticas;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.pru.pst.Entidade;

/**
 * Created by anderson on 06/09/2017.
 */

@DatabaseTable(tableName="tbliderest")
public class LiderTO extends Entidade {

    private static final long serialVersionUID = 1L;

    @DatabaseField(id=true)
    private Long idLider;
    @DatabaseField
    private Long codLider;
    @DatabaseField
    private String nomeLider;

    public LiderTO() {
    }

    public Long getIdLider() {
        return idLider;
    }

    public void setIdLider(Long idLider) {
        this.idLider = idLider;
    }

    public Long getCodLider() {
        return codLider;
    }

    public void setCodLider(Long codLider) {
        this.codLider = codLider;
    }

    public String getNomeLider() {
        return nomeLider;
    }

    public void setNomeLider(String nomeLider) {
        this.nomeLider = nomeLider;
    }

}
