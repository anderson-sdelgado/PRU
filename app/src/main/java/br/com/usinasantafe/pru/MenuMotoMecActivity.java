package br.com.usinasantafe.pru;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pru.util.EnvioDadosServ;

public class MenuMotoMecActivity extends ActivityGeneric {

    private PRUContext pruContext;
    private ListView lista;

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

        br.com.usinasantafe.pru.to.tb.variaveis.ConfigBean configBean = new br.com.usinasantafe.pru.to.tb.variaveis.ConfigBean();
        List configList = configBean.all();
        configBean = (br.com.usinasantafe.pru.to.tb.variaveis.ConfigBean) configList.get(0);

        if(configBean.getIdTipo() == 1L){
            itens.add("FUNCIONÁRIOS");
        }

        AdapterList adapterList = new AdapterList(this, itens);
        lista = (ListView) findViewById(R.id.listViewMenuPrinc);
        lista.setAdapter(adapterList);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> l, View v, int position,
                                    long id) {
                // TODO Auto-generated method stub

                br.com.usinasantafe.pru.to.tb.variaveis.ConfigBean configBean = new br.com.usinasantafe.pru.to.tb.variaveis.ConfigBean();
                List configList = configBean.all();
                configBean = (br.com.usinasantafe.pru.to.tb.variaveis.ConfigBean) configList.get(0);

                if (position == 0) {
                    pruContext.setVerPosTela(2);
                    Intent it = new Intent(MenuMotoMecActivity.this, OSActivity.class);
                    startActivity(it);
                    finish();
                } else if (position == 1) {
                    pruContext.setVerPosTela(3);
                    Intent it = new Intent(MenuMotoMecActivity.this, OSActivity.class);
                    startActivity(it);
                    finish();
                } else if (position == 2) {
                    EnvioDadosServ.getInstance().salvaBoletimFechado();
                    Intent it = new Intent(MenuMotoMecActivity.this, MenuInicialActivity.class);
                    startActivity(it);
                    finish();
                }
                else if (position == 3) {
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
