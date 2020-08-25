package br.com.usinasantafe.pru.model.bean.variaveis;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.pru.model.pst.Entidade;

/**
 * Created by anderson on 31/03/2017.
 */
@DatabaseTable(tableName="tbcabfitovar")
public class CabecFitoBean extends Entidade {

    private static final long serialVersionUID = 1L;

    @DatabaseField(generatedId=true)
    private Long idCabecFito;
    @DatabaseField
    private Long auditorCabecFito;
    @DatabaseField
    private String dthrCabecFito;
    @DatabaseField
    private Long osCabecFito;
    @DatabaseField
    private Long talhaoCabecFito;
    @DatabaseField
    private Long idOrgCabecFito;
    @DatabaseField
    private Long idCaracOrgCabecFito;
    @DatabaseField
    private Long ultPontoCabecFito;
    @DatabaseField
    private Long statusCabecFito; // 1 - ABERTA; 2 - FECHADA; 3 - ENVIADO

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

    public String getDthrCabecFito() {
        return dthrCabecFito;
    }

    public void setDthrCabecFito(String dthrCabecFito) {
        this.dthrCabecFito = dthrCabecFito;
    }

    public Long getOSCabecFito() {
        return osCabecFito;
    }

    public void setOSCabecFito(Long secaoCabecFito) {
        this.osCabecFito = secaoCabecFito;
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

    public Long getUltPontoCabecFito() {
        return ultPontoCabecFito;
    }

    public void setUltPontoCabecFito(Long ultPontoCabecFito) {
        this.ultPontoCabecFito = ultPontoCabecFito;
    }

    public Long getStatusCabecFito() {
        return statusCabecFito;
    }

    public void setStatusCabecFito(Long statusCabecFito) {
        this.statusCabecFito = statusCabecFito;
    }
}
