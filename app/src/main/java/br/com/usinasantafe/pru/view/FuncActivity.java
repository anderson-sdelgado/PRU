package br.com.usinasantafe.pru.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import br.com.usinasantafe.pru.PRUContext;
import br.com.usinasantafe.pru.R;

public class FuncActivity extends ActivityGeneric {

    private PRUContext pruContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_func);

        pruContext = (PRUContext) getApplication();

        Button buttonOkLider = (Button) findViewById(R.id.buttonOkPadrao);
        Button buttonCancLider = (Button) findViewById(R.id.buttonCancPadrao);

        buttonOkLider.setOnClickListener(new View.OnClickListener() {
            @SuppressWarnings("rawtypes")
            @Override
            public void onClick(View v) {

                if (!editTextPadrao.getText().toString().equals("")) {

                    Long matricFunc = Long.parseLong(editTextPadrao.getText().toString());

                    if (pruContext.getRuricolaCTR().verLider(matricFunc) && (pruContext.getRuricolaCTR().verFunc(matricFunc))){

                        pruContext.getRuricolaCTR().salvarBolAberto(matricFunc);
                        Intent it = new Intent(FuncActivity.this, MenuMotoMecActivity.class);
                        startActivity(it);
                        finish();

                    }

                }

            }

        });

        buttonCancLider.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (editTextPadrao.getText().toString().length() > 0) {
                    editTextPadrao.setText(editTextPadrao.getText().toString().substring(0, editTextPadrao.getText().toString().length() - 1));
                }
            }
        });

    }

    public void onBackPressed()  {
        Intent it = new Intent(FuncActivity.this, ListaAtividadeActivity.class);
        startActivity(it);
        finish();
    }

}
