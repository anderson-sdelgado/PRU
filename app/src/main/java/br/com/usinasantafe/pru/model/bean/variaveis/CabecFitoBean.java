package br.com.usinasantafe.pru.model.bean.variaveis;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.pru.model.pst.Entidade;

/**
 * Created by anderson on 31/03/2017.
 */
@DatabaseTable(tableName="tbcabamostravar")
public class CabecFitoBean extends Entidade {

    private static final long serialVersionUID = 1L;

    @DatabaseField(generatedId=true)
    private Long idCabecFito;
    @DatabaseField
    private Long auditorCabecFito;
    @DatabaseField
    private String dtCabecFito;
    @DatabaseField
    private Long secaoCabecFito;
    @DatabaseField
    private Long talhaoCabecFito;
    @DatabaseField
    private Long idOrgCabecFito;
    @DatabaseField
    private Long idCaracOrgCabecFito;
    @DatabaseField
    private Long ultPontoFito;
    @DatabaseField
    private Long statusAmostraFito; // 1 - ABERTA; 2 - FECHADA;

    public CabecFitoBean() {
    }

    public Long getIdCabecFito() {
        return idCabecFito;
    }

    public Long getAuditorCabecFito() {
        return auditorCabecFito;
    }

    public void setAuditorCabecFito(Long auditorCabecFito) {
        this.auditorCabecFito = auditorCabecFito;
    }

    public String getDtCabecFito() {
        return dtCabecFito;
    }

    public void setDtCabecFito(String dtCabecFito) {
        this.dtCabecFito = dtCabecFito;
    }

    public Long getSecaoCabecFito() {
        return secaoCabecFito;
    }

    public void setSecaoCabecFito(Long secaoCabecFito) {
        this.secaoCabecFito = secaoCabecFito;
    }

    public Long getTalhaoCabecFito() {
        return talhaoCabecFito;
    }

    public void setTalhaoCabecFito(Long talhaoCabecFito) {
        this.talhaoCabecFito = talhaoCabecFito;
    }

    public Long getIdOrgCabecFito() {
        return idOrgCabecFito;
    }

    public void setIdOrgCabecFito(Long idOrgCabecFito) {
        this.idOrgCabecFito = idOrgCabecFito;
    }

    public Long getIdCaracOrgCabecFito() {
        return idCaracOrgCabecFito;
    }

    public void setIdCaracOrgCabecFito(Long idCaracOrgCabecFito) {
        this.idCaracOrgCabecFito = idCaracOrgCabecFito;
    }

    public Long getUltPontoFito() {
        return ultPontoFito;
    }

    public void setUltPontoFito(Long ultPontoFito) {
        this.ultPontoFito = ultPontoFito;
    }

    public Long getStatusAmostraFito() {
        return statusAmostraFito;
    }

    public void setStatusAmostraFito(Long statusAmostraFito) {
        this.statusAmostraFito = statusAmostraFito;
    }
}
