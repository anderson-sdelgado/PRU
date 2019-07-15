package br.com.usinasantafe.pru;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import br.com.usinasantafe.pru.to.tb.estaticas.OSTO;

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

        OSTO osTO = new OSTO();
        List osList;
        if(pruContext.getVerPosTelaPrinc() == 1){
            osList = osTO.get("nroOS", pruContext.getBoletimTO().getOsBoletim());
        }
        else{
            osList = osTO.get("nroOS", pruContext.getApontamentoTO().getOsAponta());
        }

        osTO = (OSTO) osList.get(0);

        textViewNroOS.setText("OS: " + osTO.getNroOS() + "\nSEÇÃO: " + osTO.getCodSecao() + "\n" + osTO.getDescrSecao());

        buttonOkDescrOS.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent it = new Intent(DescricaoOSActivity.this, ListaAtivActivity.class);
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
