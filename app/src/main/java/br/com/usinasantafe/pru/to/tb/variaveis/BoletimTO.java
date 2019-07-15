package br.com.usinasantafe.pru.to.tb.variaveis;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.pru.pst.Entidade;

@DatabaseTable(tableName="tbboletimvar")
public class BoletimTO extends Entidade {

	private static final long serialVersionUID = 1L;

	@DatabaseField(generatedId=true)
	private Long idBoletim;
	@DatabaseField
	private Long idExtBoletim;
	@DatabaseField
    private Long idLiderBoletim;
	@DatabaseField
	private Long idTurmaBoletim;
	@DatabaseField
	private String dthrInicioBoletim;
	@DatabaseField
	private String dthrFimBoletim;
	@DatabaseField
	private Long osBoletim;
	@DatabaseField
	private Long ativPrincBoletim;
	@DatabaseField
	private Long statusBoletim; //1 - Aberto; 2 - Encerrado

    public BoletimTO() {
		// TODO Auto-generated constructor stub
	}

	public Long getIdBoletim() {
		return idBoletim;
	}

	public void setIdBoletim(Long idBoletim) {
		this.idBoletim = idBoletim;
	}

	public Long getIdExtBoletim() {
		return idExtBoletim;
	}

	public void setIdExtBoletim(Long idExtBoletim) {
		this.idExtBoletim = idExtBoletim;
	}

	public Long getIdLiderBoletim() {
		return idLiderBoletim;
	}

	public void setIdLiderBoletim(Long idLiderBoletim) {
		this.idLiderBoletim = idLiderBoletim;
	}

	public Long getIdTurmaBoletim() {
		return idTurmaBoletim;
	}

	public void setIdTurmaBoletim(Long idTurmaBoletim) {
		this.idTurmaBoletim = idTurmaBoletim;
	}

	public String getDthrInicioBoletim() {
		return dthrInicioBoletim;
	}

	public void setDthrInicioBoletim(String dthrInicioBoletim) {
		this.dthrInicioBoletim = dthrInicioBoletim;
	}

	public String getDthrFimBoletim() {
		return dthrFimBoletim;
	}

	public void setDthrFimBoletim(String dthrFimBoletim) {
		this.dthrFimBoletim = dthrFimBoletim;
	}

	public Long getStatusBoletim() {
		return statusBoletim;
	}

	public void setStatusBoletim(Long statusBoletim) {
		this.statusBoletim = statusBoletim;
	}

	public Long getOsBoletim() {
		return osBoletim;
	}

	public void setOsBoletim(Long osBoletim) {
		this.osBoletim = osBoletim;
	}

	public Long getAtivPrincBoletim() {
		return ativPrincBoletim;
	}

	public void setAtivPrincBoletim(Long ativPrincBoletim) {
		this.ativPrincBoletim = ativPrincBoletim;
	}
}
