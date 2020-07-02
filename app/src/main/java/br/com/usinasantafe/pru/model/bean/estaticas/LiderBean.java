package br.com.usinasantafe.pru.model.bean.estaticas;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.pru.model.pst.Entidade;

/**
 * Created by anderson on 06/09/2017.
 */

@DatabaseTable(tableName="tbliderest")
public class LiderBean extends Entidade {

    private static final long serialVersionUID = 1L;

    @DatabaseField(id=true)
    private Long idLider;
    @DatabaseField
    private Long matricLider;
    @DatabaseField
    private String nomeLider;

    public LiderBean() {
    }

    public Long getIdLider() {
        return idLider;
    }

    public void setIdLider(Long idLider) {
        this.idLider = idLider;
    }

    public Long getMatricLider() {
        return matricLider;
    }

    public void setMatricLider(Long matricLider) {
        this.matricLider = matricLider;
    }

    public String getNomeLider() {
        return nomeLider;
    }

    public void setNomeLider(String nomeLider) {
        this.nomeLider = nomeLider;
    }

}
