package br.com.usinasantafe.pru.model.bean.estaticas;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.pru.model.pst.Entidade;

/**
 * Created by anderson on 29/06/2017.
 */

@DatabaseTable(tableName="tbrocaest")
public class ROCAFitoBean extends Entidade {

    private static final long serialVersionUID = 1L;

    @DatabaseField(id=true)
    private Long idROrganCarac;
    @DatabaseField
    private Long idOrgan;
    @DatabaseField
    private Long idCaracOrgan;
    @DatabaseField
    private Long idAmostraOrgan;

    public ROCAFitoBean() {
    }

    public Long getIdROrganCarac() {
        return idROrganCarac;
    }

    public void setIdROrganCarac(Long idROrganCarac) {
        this.idROrganCarac = idROrganCarac;
    }

    public Long getIdOrgan() {
        return idOrgan;
    }

    public void setIdOrgan(Long idOrgan) {
        this.idOrgan = idOrgan;
    }

    public Long getIdCaracOrgan() {
        return idCaracOrgan;
    }

    public void setIdCaracOrgan(Long idCaracOrgan) {
        this.idCaracOrgan = idCaracOrgan;
    }

    public Long getIdAmostraOrgan() {
        return idAmostraOrgan;
    }

    public void setIdAmostraOrgan(Long idAmostraOrgan) {
        this.idAmostraOrgan = idAmostraOrgan;
    }
}
