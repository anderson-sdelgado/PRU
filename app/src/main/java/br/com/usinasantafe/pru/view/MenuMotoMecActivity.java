package br.com.usinasantafe.pru.view;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import br.com.usinasantafe.pru.PRUContext;
import br.com.usinasantafe.pru.R;
import br.com.usinasantafe.pru.util.EnvioDadosServ;
import br.com.usinasantafe.pru.util.Tempo;

public class MenuMotoMecActivity extends ActivityGeneric {

    private PRUContext pruContext;
    private ListView menuListView;

    private TextView textViewProcessoNormal;
    private Handler customHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_moto_mec);

        pruContext = (PRUContext) getApplication();
        textViewProcessoNormal = (TextView) findViewById(R.id.textViewProcessoNormal);

        customHandler.postDelayed(updateTimerThread, 0);

        ArrayList<String> itens = new ArrayList<String>();

        itens.add("TRABALHANDO");
        itens.add("PARADO");
        itens.add("FINALIZAR BOLETIM");

        if(pruContext.getConfigCTR().getConfig().getIdTipoConfig() == 1L){
            itens.add("ALOCA/DESALOCA FUNCIONÁRIO");
        }

        AdapterList adapterList = new AdapterList(this, itens);
        menuListView = (ListView) findViewById(R.id.listViewMenuPrinc);
        menuListView.setAdapter(adapterList);

        menuListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> l, View v, int position,
                                    long id) {

                TextView textView = (TextView) v.findViewById(R.id.textViewItemList);
                String text = textView.getText().toString();

                if (text.equals("TRABALHANDO")) {
                    if (pruContext.getConfigCTR().getConfig().getDtUltApontConfig().equals(Tempo.getInstance().data())) {
                        Toast.makeText(MenuMotoMecActivity.this, "POR FAVOR! ESPERE 1 MINUTO PARA REALIZAR UM NOVO APONTAMENTO.",
                                Toast.LENGTH_LONG).show();
                    } else {
                        pruContext.setVerPosTela(2);
                        Intent it = new Intent(MenuMotoMecActivity.this, OSActivity.class);
                        startActivity(it);
                        finish();
                    }
                } else if (text.equals("PARADO")) {
                    if (pruContext.getConfigCTR().getConfig().getDtUltApontConfig().equals(Tempo.getInstance().data())) {
                        Toast.makeText(MenuMotoMecActivity.this, "POR FAVOR! ESPERE 1 MINUTO PARA REALIZAR UM NOVO APONTAMENTO.",
                                Toast.LENGTH_LONG).show();
                    } else {
                        pruContext.setVerPosTela(3);
                        Intent it = new Intent(MenuMotoMecActivity.this, OSActivity.class);
                        startActivity(it);
                        finish();
                    }
                } else if (text.equals("FINALIZAR BOLETIM")) {
                    pruContext.getRuricolaCTR().salvarBolFechado();
                    Intent it = new Intent(MenuMotoMecActivity.this, MenuInicialActivity.class);
                    startActivity(it);
                    finish();
                }
                else if (text.equals("ALOCA/DESALOCA FUNCIONÁRIO")) {
                    pruContext.setVerPosTela(4);
                    Intent it = new Intent(MenuMotoMecActivity.this, ListaFuncActivity.class);
                    startActivity(it);
                    finish();
                }
            }

        });

    }

    private Runnable updateTimerThread = new Runnable() {

        public void run() {

            if(EnvioDadosServ.getInstance().getStatusEnvio() == 1){
                textViewProcessoNormal.setTextColor(Color.YELLOW);
                textViewProcessoNormal.setText("Enviando Dados...");
            }
            else if(EnvioDadosServ.getInstance().getStatusEnvio() == 2){
                textViewProcessoNormal.setTextColor(Color.RED);
                textViewProcessoNormal.setText("Existem Dados para serem Enviados");
            }
            else if(EnvioDadosServ.getInstance().getStatusEnvio() == 3){
                textViewProcessoNormal.setTextColor(Color.GREEN);
                textViewProcessoNormal.setText("Todos os Dados já foram Enviados");
            }

            customHandler.postDelayed(this, 10000);
        }
    };

    public void onBackPressed()  {
    }

}
