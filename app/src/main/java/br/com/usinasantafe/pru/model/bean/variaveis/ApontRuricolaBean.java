package br.com.usinasantafe.pru.model.bean.variaveis;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.pru.model.pst.Entidade;

@DatabaseTable(tableName="tbapontammvar")
public class ApontRuricolaBean extends Entidade  {

	private static final long serialVersionUID = 1L;

	@DatabaseField(generatedId=true)
	private Long idApont;
	@DatabaseField
	private Long idBolApont;
	@DatabaseField
	private Long idExtBolApont;
	@DatabaseField
	private Long osApont;
	@DatabaseField
	private Long ativApont;
	@DatabaseField
	private Long paradaApont;
	@DatabaseField
	private String dthrApont;
	@DatabaseField
	private Long funcApont;

	public ApontRuricolaBean() {
	}

	public Long getIdApont() {
		return idApont;
	}

	public Long getIdBolApont() {
		return idBolApont;
	}

	public void setIdBolApont(Long idBolApont) {
		this.idBolApont = idBolApont;
	}

	public Long getIdExtBolApont() {
		return idExtBolApont;
	}

	public void setIdExtBolApont(Long idExtBolApont) {
		this.idExtBolApont = idExtBolApont;
	}

	public Long getOsApont() {
		return osApont;
	}

	public void setOsApont(Long osApont) {
		this.osApont = osApont;
	}

	public Long getAtivApont() {
		return ativApont;
	}

	public void setAtivApont(Long ativApont) {
		this.ativApont = ativApont;
	}

	public Long getParadaApont() {
		return paradaApont;
	}

	public void setParadaApont(Long paradaApont) {
		this.paradaApont = paradaApont;
	}

	public String getDthrApont() {
		return dthrApont;
	}

	public void setDthrApont(String dthrApont) {
		this.dthrApont = dthrApont;
	}

	public Long getFuncApont() {
		return funcApont;
	}

	public void setFuncApont(Long funcApont) {
		this.funcApont = funcApont;
	}
}
