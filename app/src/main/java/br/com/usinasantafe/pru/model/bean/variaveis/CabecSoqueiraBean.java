package br.com.usinasantafe.pru.model.bean.variaveis;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.pru.model.pst.Entidade;

@DatabaseTable(tableName="tbcabecsoqvar")
public class CabecSoqueiraBean extends Entidade {

    private static final long serialVersionUID = 1L;

    @DatabaseField(id=true)
    private Long idCabecSoqueira;
    @DatabaseField
    private Long auditorCabecSoqueira;
    @DatabaseField
    private Long osCabecSoqueira;
    @DatabaseField
    private Long equipCabecSoqueira;
    @DatabaseField
    private Long statusCabecSoqueira;

    public CabecSoqueiraBean() {
    }

    public Long getIdCabecSoqueira() {
        return idCabecSoqueira;
    }

    public void setIdCabecSoqueira(Long idCabecSoqueira) {
        this.idCabecSoqueira = idCabecSoqueira;
    }

    public Long getAuditorCabecSoqueira() {
        return auditorCabecSoqueira;
    }

    public void setAuditorCabecSoqueira(Long auditorCabecSoqueira) {
        this.auditorCabecSoqueira = auditorCabecSoqueira;
    }

    public Long getOsCabecSoqueira() {
        return osCabecSoqueira;
    }

    public void setOsCabecSoqueira(Long osCabecSoqueira) {
        this.osCabecSoqueira = osCabecSoqueira;
    }

    public Long getEquipCabecSoqueira() {
        return equipCabecSoqueira;
    }

    public void setEquipCabecSoqueira(Long equipCabecSoqueira) {
        this.equipCabecSoqueira = equipCabecSoqueira;
    }

    public Long getStatusCabecSoqueira() {
        return statusCabecSoqueira;
    }

    public void setStatusCabecSoqueira(Long statusCabecSoqueira) {
        this.statusCabecSoqueira = statusCabecSoqueira;
    }
}
