package br.com.usinasantafe.pru.model.bean.variaveis;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.pru.model.pst.Entidade;

@DatabaseTable(tableName="tbboletimvar")
public class BoletimRuricolaBean extends Entidade {

	private static final long serialVersionUID = 1L;

	@DatabaseField(generatedId=true)
	private Long idBol;
	@DatabaseField
	private Long idExtBol;
	@DatabaseField
    private Long idLiderBol;
	@DatabaseField
	private Long idTurmaBol;
	@DatabaseField
	private String dthrInicioBol;
	@DatabaseField
	private String dthrFimBol;
	@DatabaseField
	private Long osBol;
	@DatabaseField
	private Long ativPrincBol;
	@DatabaseField
	private Long statusBol; //1 - ABERTO; 2 - FECHADO; 3 - ENVIADO
	@DatabaseField
	private Long tipoFuncBol;

    public BoletimRuricolaBean() {
	}

	public Long getIdBol() {
		return idBol;
	}

	public void setIdBol(Long idBol) {
		this.idBol = idBol;
	}

	public Long getIdExtBol() {
		return idExtBol;
	}

	public void setIdExtBol(Long idExtBol) {
		this.idExtBol = idExtBol;
	}

	public Long getIdLiderBol() {
		return idLiderBol;
	}

	public void setIdLiderBol(Long idLiderBol) {
		this.idLiderBol = idLiderBol;
	}

	public Long getIdTurmaBol() {
		return idTurmaBol;
	}

	public void setIdTurmaBol(Long idTurmaBol) {
		this.idTurmaBol = idTurmaBol;
	}

	public String getDthrInicioBol() {
		return dthrInicioBol;
	}

	public void setDthrInicioBol(String dthrInicioBol) {
		this.dthrInicioBol = dthrInicioBol;
	}

	public String getDthrFimBol() {
		return dthrFimBol;
	}

	public void setDthrFimBol(String dthrFimBol) {
		this.dthrFimBol = dthrFimBol;
	}

	public Long getStatusBol() {
		return statusBol;
	}

	public void setStatusBol(Long statusBol) {
		this.statusBol = statusBol;
	}

	public Long getOsBol() {
		return osBol;
	}

	public void setOsBol(Long osBol) {
		this.osBol = osBol;
	}

	public Long getAtivPrincBol() {
		return ativPrincBol;
	}

	public void setAtivPrincBol(Long ativPrincBol) {
		this.ativPrincBol = ativPrincBol;
	}

	public Long getTipoFuncBol() {
		return tipoFuncBol;
	}

	public void setTipoFuncBol(Long tipoFuncBol) {
		this.tipoFuncBol = tipoFuncBol;
	}
}
