package br.com.usinasantafe.pru;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class DescricaoOSActivity extends ActivityGeneric {

    private PRUContext pruContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_descricao_os);

        pruContext = (PRUContext) getApplication();

        TextView textViewNroOS = (TextView) findViewById(R.id.textViewNroOS);
        Button buttonCancDescrOS = (Button) findViewById(R.id.buttonCancDescrOS);
        Button buttonOkDescrOS = (Button) findViewById(R.id.buttonOkDescrOS);

        br.com.usinasantafe.pru.to.tb.estaticas.OSBean osTO = new br.com.usinasantafe.pru.to.tb.estaticas.OSBean();
        List osList;
        if(pruContext.getVerPosTela() == 1){
            osList = osTO.get("nroOS", pruContext.getBoletimBean().getOsBoletim());
        }
        else{
            osList = osTO.get("nroOS", pruContext.getApontamentoTO().getOsAponta());
        }

        osTO = (br.com.usinasantafe.pru.to.tb.estaticas.OSBean) osList.get(0);

        textViewNroOS.setText("OS: " + osTO.getNroOS() + "\nSEÇÃO: " + osTO.getCodSecao() + "\n" + osTO.getDescrSecao());

        buttonOkDescrOS.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent it = new Intent(DescricaoOSActivity.this, ListaAtividadeActivity.class);
                startActivity(it);
                finish();
            }
        });

        buttonCancDescrOS.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent it = new Intent(DescricaoOSActivity.this, OSActivity.class);
                startActivity(it);
                finish();
            }
        });

    }

    public void onBackPressed()  {
    }

}
