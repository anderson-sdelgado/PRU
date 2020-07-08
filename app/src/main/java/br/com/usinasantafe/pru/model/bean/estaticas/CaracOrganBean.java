package br.com.usinasantafe.pru.model.bean.estaticas;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.pru.model.pst.Entidade;

/**
 * Created by anderson on 29/06/2017.
 */
@DatabaseTable(tableName="tbcaracorgest")
public class CaracOrganBean extends Entidade {

    private static final long serialVersionUID = 1L;

    @DatabaseField(id=true)
    private Long idCaracOrgan;
    @DatabaseField
    private Long codCaracOrgan;
    @DatabaseField
    private String descrCaracOrgan;

    public CaracOrganBean() {
    }

    public Long getIdCaracOrgan() {
        return idCaracOrgan;
    }

    public void setIdCaracOrgan(Long idCaracOrgan) {
        this.idCaracOrgan = idCaracOrgan;
    }

    public Long getCodCaracOrgan() {
        return codCaracOrgan;
    }

    public void setCodCaracOrgan(Long codCaracOrgan) {
        this.codCaracOrgan = codCaracOrgan;
    }

    public String getDescrCaracOrgan() {
        return descrCaracOrgan;
    }

    public void setDescrCaracOrgan(String descrCaracOrgan) {
        this.descrCaracOrgan = descrCaracOrgan;
    }
}
