package br.com.usinasantafe.pru.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import br.com.usinasantafe.pru.PRUContext;
import br.com.usinasantafe.pru.R;

public class MenuRelatorioActivity extends ActivityGeneric {

    private ListView listViewMenuRelatorio;
    private PRUContext pruContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_relatorio);

        pruContext = (PRUContext) getApplication();

        Button buttonRetMenuRelatorio = (Button) findViewById(R.id.buttonRetMenuRelatorio);

        buttonRetMenuRelatorio.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent it = new Intent(MenuRelatorioActivity.this, MenuInicialActivity.class);
                startActivity(it);
                finish();

            }
        });

        ArrayList<String> itens = new ArrayList<String>();

        itens.add("RURÍCOLA");
        itens.add("FITO");
        itens.add("PERDA");
        itens.add("SOQUEIRA");

        AdapterList adapterList = new AdapterList(this, itens);
        listViewMenuRelatorio = (ListView) findViewById(R.id.listViewMenuRelatorio);
        listViewMenuRelatorio.setAdapter(adapterList);

        listViewMenuRelatorio.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> l, View v, int position,
                                    long id) {

            TextView textView = v.findViewById(R.id.textViewItemList);
            String text = textView.getText().toString();

            boolean verAcesso = false;

            if(text.equals("RURÍCOLA")) {

                if(pruContext.getRuricolaCTR().verBolFechadoEnviado()){
                    verAcesso = true;
                    pruContext.setVerPosTela(16);
                }
                else{

                    AlertDialog.Builder alerta = new AlertDialog.Builder( MenuRelatorioActivity.this);
                    alerta.setTitle("ATENÇÃO");
                    alerta.setMessage("NÃO CONTÉM BOLETIM RURÍCOLAS NA BASE DE DADOS.");

                    alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
                    alerta.show();

                }
            }
            else if(text.equals("FITO")) {

                if(pruContext.getFitoCTR().verCabecFechadoEnviado()){
                    verAcesso = true;
                    pruContext.setVerPosTela(17);
                }
                else{

                    AlertDialog.Builder alerta = new AlertDialog.Builder( MenuRelatorioActivity.this);
                    alerta.setTitle("ATENÇÃO");
                    alerta.setMessage("NÃO CONTÉM ANALISE FITO NA BASE DE DADOS.");

                    alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
                    alerta.show();

                }

            }
            else if(text.equals("PERDA")) {

                if(pruContext.getPerdaCTR().verCabecFechadoEnviado()){
                    verAcesso = true;
                    pruContext.setVerPosTela(18);
                }
                else{

                    AlertDialog.Builder alerta = new AlertDialog.Builder( MenuRelatorioActivity.this);
                    alerta.setTitle("ATENÇÃO");
                    alerta.setMessage("NÃO CONTÉM ANALISE PERDA NA BASE DE DADOS.");

                    alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
                    alerta.show();

                }

            }
            else if(text.equals("SOQUEIRA")) {

                if(pruContext.getSoqueiraCTR().verCabecFechadoEnviado()){
                    verAcesso = true;
                    pruContext.setVerPosTela(19);
                }
                else{

                    AlertDialog.Builder alerta = new AlertDialog.Builder( MenuRelatorioActivity.this);
                    alerta.setTitle("ATENÇÃO");
                    alerta.setMessage("NÃO CONTÉM ANALISE SOQUEIRA NA BASE DE DADOS.");

                    alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
                    alerta.show();

                }

            }

            if(verAcesso){
                pruContext.setPosCabec(0);
                Intent it = new Intent(MenuRelatorioActivity.this, RelatCabecActivity.class);
                startActivity(it);
                finish();
            }

            }

        });

    }

}