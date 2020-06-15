package br.com.usinasantafe.pru;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pru.util.EnvioDadosServ;
import br.com.usinasantafe.pru.model.pst.EspecificaPesquisa;

public class FuncionarioActivity extends ActivityGeneric {

    private PRUContext pruContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_funcionario);

        pruContext = (PRUContext) getApplication();

        Button buttonOkLider = (Button) findViewById(R.id.buttonOkPadrao);
        Button buttonCancLider = (Button) findViewById(R.id.buttonCancPadrao);

        buttonOkLider.setOnClickListener(new View.OnClickListener() {
            @SuppressWarnings("rawtypes")
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                if (!editTextPadrao.getText().toString().equals("")) {

                    br.com.usinasantafe.pru.to.tb.estaticas.LiderBean liderBean = new br.com.usinasantafe.pru.to.tb.estaticas.LiderBean();
                    List liderList = liderBean.get("codLider", Long.parseLong(editTextPadrao.getText().toString()));

                    br.com.usinasantafe.pru.to.tb.variaveis.ConfigBean configBean = new br.com.usinasantafe.pru.to.tb.variaveis.ConfigBean();
                    List configList = configBean.all();
                    configBean = (br.com.usinasantafe.pru.to.tb.variaveis.ConfigBean) configList.get(0);
                    configList.clear();

                    br.com.usinasantafe.pru.to.tb.estaticas.FuncBean funcBean = new br.com.usinasantafe.pru.to.tb.estaticas.FuncBean();
                    ArrayList listaPesq = new ArrayList();
                    EspecificaPesquisa pesquisa = new EspecificaPesquisa();
                    pesquisa.setCampo("idTurma");
                    pesquisa.setValor(configBean.getIdTurma());
                    listaPesq.add(pesquisa);

                    EspecificaPesquisa pesquisa2 = new EspecificaPesquisa();
                    pesquisa2.setCampo("codFunc");
                    pesquisa2.setValor(Long.parseLong(editTextPadrao.getText().toString()));
                    listaPesq.add(pesquisa2);

                    List funcList = funcBean.get(listaPesq);

                    if ((liderList.size() > 0) && (funcList.size() > 0)) {

                        pruContext.getBoletimBean().setIdLiderBoletim(Long.valueOf(editTextPadrao.getText().toString()));
                        EnvioDadosServ.getInstance().salvaBoletimAberto(pruContext.getBoletimBean());
                        EnvioDadosServ.getInstance().salvaFuncBoletim(Long.valueOf(editTextPadrao.getText().toString()), 1L);
                        EnvioDadosServ.getInstance().envioDadosPrinc();

                        Intent it = new Intent(FuncionarioActivity.this, MenuMotoMecActivity.class);
                        startActivity(it);
                        finish();

                    }

                    liderList.clear();
                    funcList.clear();

                }

            }

        });

        buttonCancLider.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (editTextPadrao.getText().toString().length() > 0) {
                    editTextPadrao.setText(editTextPadrao.getText().toString().substring(0, editTextPadrao.getText().toString().length() - 1));
                }
            }
        });

    }

    public void onBackPressed()  {
        Intent it = new Intent(FuncionarioActivity.this, ListaAtivActivity.class);
        startActivity(it);
        finish();
    }

}
