package br.com.usinasantafe.pru.view;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import br.com.usinasantafe.pru.PRUContext;
import br.com.usinasantafe.pru.R;
import br.com.usinasantafe.pru.model.bean.variaveis.CabecFitoBean;

public class TalhaoActivity extends ActivityGeneric {

    private PRUContext pruContext;
    private ProgressDialog progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_talhao);

        pruContext = (PRUContext) getApplication();

        Button buttonOkTalhao = (Button) findViewById(R.id.buttonOkPadrao);
        Button buttonCancTalhao = (Button) findViewById(R.id.buttonCancPadrao);
        Button buttonAtualPadrao = (Button) findViewById(R.id.buttonAtualPadrao);

        buttonAtualPadrao.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                progressBar = new ProgressDialog(v.getContext());
                progressBar.setCancelable(true);
                progressBar.setMessage("Atualizando Talhão...");
                progressBar.show();

                pruContext.getConfigCTR().atualDadosTalhao(TalhaoActivity.this, TalhaoActivity.class, progressBar);

            }

        });

        buttonOkTalhao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!editTextPadrao.getText().toString().equals("")){

                    if(pruContext.getFitoCTR().verTalhao(Long.parseLong(editTextPadrao.getText().toString()))) {

                        pruContext.getFitoCTR().setCabecFitoBean(new CabecFitoBean());
                        pruContext.getFitoCTR().getCabecFitoBean().setTalhaoCabecFito(Long.parseLong(editTextPadrao.getText().toString()));

                        Intent it = new Intent(TalhaoActivity.this, ListaOrganActivity.class);
                        startActivity(it);
                        finish();

                    }

                }

            }
        });

        buttonCancTalhao.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(editTextPadrao.getText().toString().length() > 0){
                    editTextPadrao.setText(editTextPadrao.getText().toString().substring(0, editTextPadrao.getText().toString().length() - 1));
                }
            }
        });

    }

    public void onBackPressed()  {
        Intent it = new Intent(TalhaoActivity.this, MenuMotoMecActivity.class);
        startActivity(it);
        finish();
    }

}