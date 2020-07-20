package br.com.usinasantafe.pru;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MsgPontoActivity extends ActivityGeneric {

    private PRUContext pruContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_msg_ponto);

        TextView textViewPonto = (TextView) findViewById(R.id.textViewMsgPonto);
        Button buttonSimPonto = (Button) findViewById(R.id.buttonSimPonto);
        Button buttonNaoPonto = (Button) findViewById(R.id.buttonNaoPonto);

        pruContext = (PRUContext) getApplication();

        textViewPonto.setText("DESEJA INSERIR PONTO " + pruContext.getPosPonto() + "?");

        buttonSimPonto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                Intent it = new Intent(MsgPontoActivity.this, QuestaoAmostraActivity.class);
                startActivity(it);
                finish();

            }
        });

        buttonNaoPonto.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                Intent it = new Intent(MsgPontoActivity.this, ListaPontosActivity.class);
                startActivity(it);
                finish();

            }
        });

    }
}