package br.com.usinasantafe.pru.view;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import br.com.usinasantafe.pru.PRUContext;
import br.com.usinasantafe.pru.R;
import br.com.usinasantafe.pru.util.EnvioDadosServ;
import br.com.usinasantafe.pru.util.Tempo;

public class MenuApontActivity extends ActivityGeneric {

    private PRUContext pruContext;
    private ListView menuListView;

    private TextView textViewProcesso;
    private Handler customHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_apont);

        pruContext = (PRUContext) getApplication();
        textViewProcesso = findViewById(R.id.textViewProcessoNormal);

        customHandler.postDelayed(updateTimerThread, 0);

        ArrayList<String> itens = new ArrayList();

        itens.add("TRABALHANDO");
        itens.add("PARADO");
        itens.add("FINALIZAR BOLETIM");

        if(pruContext.getConfigCTR().getConfig().getIdTipoConfig() == 1L){
            itens.add("ALOCA/DESALOCA FUNCIONÁRIO");
        }

        AdapterList adapterList = new AdapterList(this, itens);
        menuListView = findViewById(R.id.listViewMenuPrinc);
        menuListView.setAdapter(adapterList);

        menuListView.setOnItemClickListener((l, v, position, id) -> {

            TextView textView = v.findViewById(R.id.textViewItemList);
            String text = textView.getText().toString();

            switch (text) {
                case "TRABALHANDO": {
                    if (pruContext.getConfigCTR().getConfig().getDtUltApontConfig().equals(Tempo.getInstance().data())) {
                        Toast.makeText(MenuApontActivity.this, "POR FAVOR! ESPERE 1 MINUTO PARA REALIZAR UM NOVO APONTAMENTO.",
                                Toast.LENGTH_LONG).show();
                    } else {
                        pruContext.setVerPosTela(2);
                        Intent it = new Intent(MenuApontActivity.this, OSActivity.class);
                        startActivity(it);
                        finish();
                    }
                    break;
                }
                case "PARADO": {
                    if (pruContext.getConfigCTR().getConfig().getDtUltApontConfig().equals(Tempo.getInstance().data())) {
                        Toast.makeText(MenuApontActivity.this, "POR FAVOR! ESPERE 1 MINUTO PARA REALIZAR UM NOVO APONTAMENTO.",
                                Toast.LENGTH_LONG).show();
                    } else {
                        pruContext.setVerPosTela(3);
                        Intent it = new Intent(MenuApontActivity.this, OSActivity.class);
                        startActivity(it);
                        finish();
                    }
                    break;
                }
                case "FINALIZAR BOLETIM": {
                    if (pruContext.getRuricolaCTR().verQtdeApont()) {
                        pruContext.getRuricolaCTR().salvarBolFechado();
                        Intent it = new Intent(MenuApontActivity.this, TelaInicialActivity.class);
                        startActivity(it);
                        finish();
                    } else {
                        AlertDialog.Builder alerta = new AlertDialog.Builder(MenuApontActivity.this);
                        alerta.setTitle("ATENÇÃO");
                        alerta.setMessage("POR FAVOR! REALIZE APONTAMENTOS NO BOLETIM PARA PODER ENCERRÁ-LO.");
                        alerta.setPositiveButton("OK", (dialog, which) -> {
                        });
                        alerta.show();
                    }
                    break;
                }
                case "ALOCA/DESALOCA FUNCIONÁRIO": {
                    pruContext.setVerPosTela(4);
                    Intent it = new Intent(MenuApontActivity.this, ListaFuncAlocActivity.class);
                    startActivity(it);
                    finish();
                    break;
                }
            }
        });

    }

    private Runnable updateTimerThread = new Runnable() {

        public void run() {

            if (pruContext.getConfigCTR().hasElemConfig()) {
                if (EnvioDadosServ.status == 1) {
                    textViewProcesso.setTextColor(Color.RED);
                    textViewProcesso.setText("Existem Dados para serem Enviados");
                } else if (EnvioDadosServ.status == 2) {
                    textViewProcesso.setTextColor(Color.YELLOW);
                    textViewProcesso.setText("Enviando Dados...");
                } else if (EnvioDadosServ.status == 3) {
                    textViewProcesso.setTextColor(Color.GREEN);
                    textViewProcesso.setText("Todos os Dados já foram Enviados");
                }
            } else {
                textViewProcesso.setTextColor(Color.RED);
                textViewProcesso.setText("Aparelho sem Equipamento");
            }

            if(EnvioDadosServ.status != 3){
                customHandler.postDelayed(this, 10000);
            }
        }
    };

    public void onBackPressed()  {
    }

}
