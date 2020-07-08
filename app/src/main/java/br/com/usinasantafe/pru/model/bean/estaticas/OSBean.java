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
	private String descrSecao;

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

	public String getDescrSecao() {
		return descrSecao;
	}

	public void setDescrSecao(String descrSecao) {
		this.descrSecao = descrSecao;
	}
}
