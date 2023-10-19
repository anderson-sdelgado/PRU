package br.com.usinasantafe.pru.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import br.com.usinasantafe.pru.PRUContext;
import br.com.usinasantafe.pru.R;

public class DescricaoOSActivity extends ActivityGeneric {

    private PRUContext pruContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_descricao_os);

        pruContext = (PRUContext) getApplication();

        Button buttonCancDescrOS = (Button) findViewById(R.id.buttonCancDescrOS);
        Button buttonOkDescrOS = (Button) findViewById(R.id.buttonOkDescrOS);

        buttonOkDescrOS.setOnClickListener(v -> {
            Intent it = new Intent(DescricaoOSActivity.this, ListaAtividadeActivity.class);
            startActivity(it);
            finish();
        });

        buttonCancDescrOS.setOnClickListener(v -> {
            Intent it = new Intent(DescricaoOSActivity.this, OSActivity.class);
            startActivity(it);
            finish();
        });

    }

    public void onBackPressed()  {
    }

}
