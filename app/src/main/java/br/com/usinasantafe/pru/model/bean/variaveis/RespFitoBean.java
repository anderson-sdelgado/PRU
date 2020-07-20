package br.com.usinasantafe.pru.model.bean.variaveis;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.pru.model.pst.Entidade;

/**
 * Created by anderson on 14/06/2017.
 */

@DatabaseTable(tableName="tbrespitemfitovar")
public class RespFitoBean extends Entidade {

    private static final long serialVersionUID = 1L;

    @DatabaseField(generatedId=true)
    private Long idRespFito;
    @DatabaseField
    private Long idCabecRespFito;
    @DatabaseField
    private Long idAmostraRespFito;
    @DatabaseField
    private Long pontoRespFito;
    @DatabaseField
    private Long valorRespFito;
    @DatabaseField
    private Long tipoRespFito;
    @DatabaseField
    private Long statusRespFito; //0 - Apontando o ponto; 1 - Terminado o apontamento do ponto

    public RespFitoBean() {
    }

    public Long getIdRespFito() {
        return idRespFito;
    }

    public Long getIdCabecRespFito() {
        return idCabecRespFito;
    }

    public void setIdCabecRespFito(Long idCabecRespFito) {
        this.idCabecRespFito = idCabecRespFito;
    }

    public Long getIdAmostraRespFito() {
        return idAmostraRespFito;
    }

    public void setIdAmostraRespFito(Long idAmostraRespFito) {
        this.idAmostraRespFito = idAmostraRespFito;
    }

    public Long getPontoRespFito() {
        return pontoRespFito;
    }

    public void setPontoRespFito(Long pontoRespFito) {
        this.pontoRespFito = pontoRespFito;
    }

    public Long getValorRespFito() {
        return valorRespFito;
    }

    public void setValorRespFito(Long valorRespFito) {
        this.valorRespFito = valorRespFito;
    }

    public Long getTipoRespFito() {
        return tipoRespFito;
    }

    public void setTipoRespFito(Long tipoRespFito) {
        this.tipoRespFito = tipoRespFito;
    }

    public Long getStatusRespFito() {
        return statusRespFito;
    }

    public void setStatusRespFito(Long statusRespFito) {
        this.statusRespFito = statusRespFito;
    }
}
