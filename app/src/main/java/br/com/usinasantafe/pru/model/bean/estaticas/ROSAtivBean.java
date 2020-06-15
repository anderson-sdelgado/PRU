package br.com.usinasantafe.pru.model.bean.estaticas;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.pru.model.pst.Entidade;

/**
 * Created by anderson on 05/05/2017.
 */
@DatabaseTable(tableName="tbrosativest")
public class ROSAtivBean extends Entidade {

    private static final long serialVersionUID = 1L;

    @DatabaseField(id=true)
    private Long idROSAtiv;
    @DatabaseField
    private Long nroOS;
    @DatabaseField
    private Long codAtiv;

    public ROSAtivBean() {
    }

    public Long getIdROSAtiv() {
        return idROSAtiv;
    }

    public void setIdROSAtiv(Long idROSAtiv) {
        this.idROSAtiv = idROSAtiv;
    }

    public Long getNroOS() {
        return nroOS;
    }

    public void setNroOS(Long nroOS) {
        this.nroOS = nroOS;
    }

    public Long getCodAtiv() {
        return codAtiv;
    }

    public void setCodAtiv(Long codAtiv) {
        this.codAtiv = codAtiv;
    }
}
