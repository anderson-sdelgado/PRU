package br.com.usinasantafe.pru.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import br.com.usinasantafe.pru.PRUContext;
import br.com.usinasantafe.pru.R;
import br.com.usinasantafe.pru.model.bean.variaveis.CabecFitoBean;

public class TalhaoActivity extends ActivityGeneric {

    private PRUContext pruContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_talhao);

        pruContext = (PRUContext) getApplication();

        Button buttonOkTalhao = (Button) findViewById(R.id.buttonOkPadrao);
        Button buttonCancTalhao = (Button) findViewById(R.id.buttonCancPadrao);

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