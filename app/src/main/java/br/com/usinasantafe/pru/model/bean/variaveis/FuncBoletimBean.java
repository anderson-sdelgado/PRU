package br.com.usinasantafe.pru.model.bean.variaveis;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.pru.model.pst.Entidade;

/**
 * Created by anderson on 24/11/2017.
 */

@DatabaseTable(tableName="tbfuncboletimvar")
public class FuncBoletimBean extends Entidade {

    private static final long serialVersionUID = 1L;

    @DatabaseField(id=true)
    private Long codFuncBoletim;

    public FuncBoletimBean() {
    }

    public Long getCodFuncBoletim() {
        return codFuncBoletim;
    }

    public void setCodFuncBoletim(Long codFuncBoletim) {
        this.codFuncBoletim = codFuncBoletim;
    }
}
