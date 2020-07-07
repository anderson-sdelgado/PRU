package br.com.usinasantafe.pru.model.bean.variaveis;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.pru.model.pst.Entidade;

/**
 * Created by anderson on 14/06/2017.
 */

@DatabaseTable(tableName="tbrespitemvar")
public class RespItemFitoBean extends Entidade {

    private static final long serialVersionUID = 1L;

    @DatabaseField(generatedId=true)
    private Long idRespItemFito;
    @DatabaseField
    private Long idCabecRespItemFito;
    @DatabaseField
    private Long idAmostraRespItemFito;
    @DatabaseField
    private Long pontoRespItemFito;
    @DatabaseField
    private Long valorRespItemFito;

    public RespItemFitoBean() {
    }

    public Long getIdRespItemFito() {
        return idRespItemFito;
    }

    public Long getIdCabecRespItemFito() {
        return idCabecRespItemFito;
    }

    public void setIdCabecRespItemFito(Long idCabecRespItemFito) {
        this.idCabecRespItemFito = idCabecRespItemFito;
    }

    public Long getIdAmostraRespItemFito() {
        return idAmostraRespItemFito;
    }

    public void setIdAmostraRespItemFito(Long idAmostraRespItemFito) {
        this.idAmostraRespItemFito = idAmostraRespItemFito;
    }

    public Long getPontoRespItemFito() {
        return pontoRespItemFito;
    }

    public void setPontoRespItemFito(Long pontoRespItemFito) {
        this.pontoRespItemFito = pontoRespItemFito;
    }

    public Long getValorRespItemFito() {
        return valorRespItemFito;
    }

    public void setValorRespItemFito(Long valorRespItemFito) {
        this.valorRespItemFito = valorRespItemFito;
    }

}
