package br.com.usinasantafe.pru.to.tb.variaveis;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.pru.pst.Entidade;

@DatabaseTable(tableName="tbapontammvar")
public class ApontTO extends Entidade  {

	private static final long serialVersionUID = 1L;

	@DatabaseField(generatedId=true)
	private Long idAponta;
	@DatabaseField
	private Long idBolAponta;
	@DatabaseField
	private Long idExtBolAponta;
	@DatabaseField
	private Long osAponta;
	@DatabaseField
	private Long ativAponta;
	@DatabaseField
	private Long paradaAponta;
	@DatabaseField
	private String dthrAponta;
	@DatabaseField
	private Long funcAponta;

	public ApontTO() {
		// TODO Auto-generated constructor stub
	}

	public Long getIdAponta() {
		return idAponta;
	}

	public Long getIdBolAponta() {
		return idBolAponta;
	}

	public void setIdBolAponta(Long idBolAponta) {
		this.idBolAponta = idBolAponta;
	}

	public Long getIdExtBolAponta() {
		return idExtBolAponta;
	}

	public void setIdExtBolAponta(Long idExtBolAponta) {
		this.idExtBolAponta = idExtBolAponta;
	}

	public Long getOsAponta() {
		return osAponta;
	}

	public void setOsAponta(Long osAponta) {
		this.osAponta = osAponta;
	}

	public Long getAtivAponta() {
		return ativAponta;
	}

	public void setAtivAponta(Long ativAponta) {
		this.ativAponta = ativAponta;
	}

	public Long getParadaAponta() {
		return paradaAponta;
	}

	public void setParadaAponta(Long paradaAponta) {
		this.paradaAponta = paradaAponta;
	}

	public String getDthrAponta() {
		return dthrAponta;
	}

	public void setDthrAponta(String dthrAponta) {
		this.dthrAponta = dthrAponta;
	}

	public Long getFuncAponta() {
		return funcAponta;
	}

	public void setFuncAponta(Long funcAponta) {
		this.funcAponta = funcAponta;
	}
}
