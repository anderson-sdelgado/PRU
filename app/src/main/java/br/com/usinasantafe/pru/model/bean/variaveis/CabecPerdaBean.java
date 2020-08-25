package br.com.usinasantafe.pru.model.bean.variaveis;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.pru.model.pst.Entidade;

@DatabaseTable(tableName="tbcabecperdavar")
public class CabecPerdaBean extends Entidade {

    private static final long serialVersionUID = 1L;

    @DatabaseField(generatedId=true)
    private Long idCabecPerda;
    @DatabaseField
    private Long tipoColheitaCabecPerda;
    @DatabaseField
    private Long auditorCabecPerda;
    @DatabaseField
    private Long osCabecPerda;
    @DatabaseField
    private Long equipCabecPerda;
    @DatabaseField
    private String dthrCabecPerda;
    @DatabaseField
    private Long statusCabecPerda;// 1 - ABERTA; 2 - FECHADA; 3 - ENVIADO

    public CabecPerdaBean() {
    }

    public Long getIdCabecPerda() {
        return idCabecPerda;
    }

    public void setIdCabecPerda(Long idCabecPerda) {
        this.idCabecPerda = idCabecPerda;
    }

    public Long getTipoColheitaCabecPerda() {
        return tipoColheitaCabecPerda;
    }

    public void setTipoColheitaCabecPerda(Long tipoColheitaCabecPerda) {
        this.tipoColheitaCabecPerda = tipoColheitaCabecPerda;
    }

    public Long getAuditorCabecPerda() {
        return auditorCabecPerda;
    }

    public void setAuditorCabecPerda(Long auditorCabecPerda) {
        this.auditorCabecPerda = auditorCabecPerda;
    }

    public Long getOsCabecPerda() {
        return osCabecPerda;
    }

    public void setOsCabecPerda(Long osCabecPerda) {
        this.osCabecPerda = osCabecPerda;
    }

    public Long getEquipCabecPerda() {
        return equipCabecPerda;
    }

    public void setEquipCabecPerda(Long equipCabecPerda) {
        this.equipCabecPerda = equipCabecPerda;
    }

    public Long getStatusCabecPerda() {
        return statusCabecPerda;
    }

    public void setStatusCabecPerda(Long statusCabecPerda) {
        this.statusCabecPerda = statusCabecPerda;
    }

    public String getDthrCabecPerda() {
        return dthrCabecPerda;
    }

    public void setDthrCabecPerda(String dthrCabecPerda) {
        this.dthrCabecPerda = dthrCabecPerda;
    }
}
