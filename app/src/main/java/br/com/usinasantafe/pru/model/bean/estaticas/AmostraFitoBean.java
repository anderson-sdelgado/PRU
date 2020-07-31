package br.com.usinasantafe.pru.model.bean.estaticas;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.pru.model.pst.Entidade;

/**
 * Created by anderson on 06/06/2017.
 */
@DatabaseTable(tableName="tbamostraest")
public class AmostraFitoBean extends Entidade {

    private static final long serialVersionUID = 1L;

    @DatabaseField(id=true)
    private Long idAmostra;
    @DatabaseField
    private Long idAmostraOrgan;
    @DatabaseField
    private Long codAmostra;
    @DatabaseField
    private Long seqAmostra;
    @DatabaseField
    private String descrAmostra;
    @DatabaseField
    private Long tipoAmostra;

    public AmostraFitoBean() {
    }

    public Long getIdAmostra() {
        return idAmostra;
    }

    public void setIdAmostra(Long idAmostra) {
        this.idAmostra = idAmostra;
    }

    public Long getIdAmostraOrgan() {
        return idAmostraOrgan;
    }

    public void setIdAmostraOrgan(Long idAmostraOrgan) {
        this.idAmostraOrgan = idAmostraOrgan;
    }

    public Long getCodAmostra() {
        return codAmostra;
    }

    public void setCodAmostra(Long codAmostra) {
        this.codAmostra = codAmostra;
    }

    public Long getSeqAmostra() {
        return seqAmostra;
    }

    public void setSeqAmostra(Long seqAmostra) {
        this.seqAmostra = seqAmostra;
    }

    public String getDescrAmostra() {
        return descrAmostra;
    }

    public void setDescrAmostra(String descrAmostra) {
        this.descrAmostra = descrAmostra;
    }

    public Long getTipoAmostra() {
        return tipoAmostra;
    }

    public void setTipoAmostra(Long tipoAmostra) {
        this.tipoAmostra = tipoAmostra;
    }

}
