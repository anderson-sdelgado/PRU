package br.com.usinasantafe.pru.to.tb.variaveis;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.pru.pst.Entidade;

/**
 * Created by anderson on 24/11/2017.
 */

@DatabaseTable(tableName="tbfuncboletimvar")
public class FuncBoletimTO extends Entidade {

    private static final long serialVersionUID = 1L;

    @DatabaseField(id=true)
    private Long codFuncBoletim;

    public FuncBoletimTO() {
    }

    public Long getCodFuncBoletim() {
        return codFuncBoletim;
    }

    public void setCodFuncBoletim(Long codFuncBoletim) {
        this.codFuncBoletim = codFuncBoletim;
    }
}
