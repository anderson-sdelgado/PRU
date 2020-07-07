package br.com.usinasantafe.pru.model.bean.variaveis;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.pru.model.pst.Entidade;

/**
 * Created by anderson on 30/03/2017.
 */

@DatabaseTable(tableName="tbitemvar")
public class ItemFitoBean extends Entidade {

    private static final long serialVersionUID = 1L;

    @DatabaseField(generatedId=true)
    private Long idItemFito;
    @DatabaseField
    private Long idCabecItemFito;
    @DatabaseField
    private Long idAmostraItemFito;
    @DatabaseField
    private String descrItemFito;
    @DatabaseField
    private Long valorAmostraItemFito;
    @DatabaseField
    private Long tipoAmostraItemFito;

    public ItemFitoBean() {
    }

    public Long getIdItemFito() {
        return idItemFito;
    }

    public Long getIdCabecItemFito() {
        return idCabecItemFito;
    }

    public void setIdCabecItemFito(Long idCabecItemFito) {
        this.idCabecItemFito = idCabecItemFito;
    }

    public Long getIdAmostraItemFito() {
        return idAmostraItemFito;
    }

    public void setIdAmostraItemFito(Long idAmostraItemFito) {
        this.idAmostraItemFito = idAmostraItemFito;
    }

    public String getDescrItemFito() {
        return descrItemFito;
    }

    public void setDescrItemFito(String descrItemFito) {
        this.descrItemFito = descrItemFito;
    }

    public Long getValorAmostraItemFito() {
        return valorAmostraItemFito;
    }

    public void setValorAmostraItemFito(Long valorAmostraItemFito) {
        this.valorAmostraItemFito = valorAmostraItemFito;
    }

    public Long getTipoAmostraItemFito() {
        return tipoAmostraItemFito;
    }

    public void setTipoAmostraItemFito(Long tipoAmostraItemFito) {
        this.tipoAmostraItemFito = tipoAmostraItemFito;
    }
}
