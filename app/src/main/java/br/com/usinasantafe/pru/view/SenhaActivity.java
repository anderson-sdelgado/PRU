package br.com.usinasantafe.pru.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import br.com.usinasantafe.pru.PRUContext;
import br.com.usinasantafe.pru.R;

public class SenhaActivity extends ActivityGeneric {

    private EditText editTextSenha;
    private PRUContext pruContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_senha);

        editTextSenha = findViewById(R.id.editTextSenha);
        Button btOkSenha =  findViewById(R.id.buttonOkSenha);
        Button btCancSenha = findViewById(R.id.buttonCancSenha);

        pruContext = (PRUContext) getApplication();

        btOkSenha.setOnClickListener(v -> {

            if (!pruContext.getConfigCTR().hasElemConfig()) {
                Intent it = new Intent(SenhaActivity.this, ConfigActivity.class);
                startActivity(it);
                finish();
            } else {
                if (pruContext.getConfigCTR().getConfigSenha(editTextSenha.getText().toString())) {
                    Intent it = new Intent(SenhaActivity.this, ConfigActivity.class);
                    startActivity(it);
                    finish();
                }
            }

        });

        btCancSenha.setOnClickListener(v -> {
            Intent it = new Intent(SenhaActivity.this, TelaInicialActivity.class);
            startActivity(it);
            finish();
        });

    }

    public void onBackPressed()  {
    }

}
