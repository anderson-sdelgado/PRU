/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.usinasantafe.pru.model.bean.estaticas;

/**
 *
 * @author anderson
 */
import br.com.usinasantafe.pru.model.pst.Entidade;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName="tbosest")
public class OSBean extends Entidade {

	private static final long serialVersionUID = 1L;
	
	@DatabaseField(id=true)
    private Long nroOS;
	@DatabaseField
	private Long idSecao;
	@DatabaseField
	private Long codSecao;
	@DatabaseField
	private String descrSecao;
	@DatabaseField
	private String idFrente;
	@DatabaseField
	private String descrFrente;

    public OSBean() {
    }

	public Long getNroOS() {
		return nroOS;
	}

	public void setNroOS(Long nroOS) {
		this.nroOS = nroOS;
	}

	public Long getIdSecao() {
		return idSecao;
	}

	public void setIdSecao(Long idSecao) {
		this.idSecao = idSecao;
	}

	public Long getCodSecao() {
		return codSecao;
	}

	public void setCodSecao(Long codSecao) {
		this.codSecao = codSecao;
	}

	public String getDescrSecao() {
		return descrSecao;
	}

	public void setDescrSecao(String descrSecao) {
		this.descrSecao = descrSecao;
	}

	public String getIdFrente() {
		return idFrente;
	}

	public void setIdFrente(String idFrente) {
		this.idFrente = idFrente;
	}

	public String getDescrFrente() {
		return descrFrente;
	}

	public void setDescrFrente(String descrFrente) {
		this.descrFrente = descrFrente;
	}
}
