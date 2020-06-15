package br.com.usinasantafe.pru.model.bean.estaticas;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.pru.model.pst.Entidade;

/**
 * Created by anderson on 28/04/2017.
 */

@DatabaseTable(tableName="tbatividadeest")
public class AtividadeBean extends Entidade {

    private static final long serialVersionUID = 1L;

    @DatabaseField(id=true)
    private Long idAtiv;
    @DatabaseField
    private Long codAtiv;
    @DatabaseField
    private String descrAtiv;

    public AtividadeBean() {
    }

    public Long getIdAtiv() {
        return idAtiv;
    }

    public void setIdAtiv(Long idAtiv) {
        this.idAtiv = idAtiv;
    }

    public Long getCodAtiv() {
        return codAtiv;
    }

    public void setCodAtiv(Long codAtiv) {
        this.codAtiv = codAtiv;
    }

    public String getDescrAtiv() {
        return descrAtiv;
    }

    public void setDescrAtiv(String descrAtiv) {
        this.descrAtiv = descrAtiv;
    }
}
