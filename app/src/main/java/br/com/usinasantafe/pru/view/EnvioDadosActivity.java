package br.com.usinasantafe.pru.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;
import android.widget.TextView;

import br.com.usinasantafe.pru.PRUContext;
import br.com.usinasantafe.pru.R;
import br.com.usinasantafe.pru.util.EnvioDadosServ;

public class EnvioDadosActivity extends ActivityGeneric {

    private TextView textEnvioDados;
    private PRUContext pruContext;
    private Handler customHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_envio_dados);

        pruContext = (PRUContext) getApplication();
        textEnvioDados = (TextView) findViewById(R.id.textEnvioDados);

        pruContext.getConfigCTR().envioDados(this);

        customHandler.postDelayed(updateTimerThread, 0);

    }

    private Runnable updateTimerThread = new Runnable() {

        public void run() {

            if(EnvioDadosServ.getInstance().getPosEnvio() == -1){
                if(!((Activity) EnvioDadosActivity.this).isFinishing()) {

                    AlertDialog.Builder alerta = new AlertDialog.Builder(EnvioDadosActivity.this);
                    alerta.setTitle("ATENÇÃO");
                    alerta.setMessage("FALHA NO ENVIO DE DADOS! TENTE NOVAMENTE NUM LUGAR COM MELHOR SINAL.");

                    alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent it = new Intent(EnvioDadosActivity.this, MenuInicialActivity.class);
                            startActivity(it);
                            finish();
                        }
                    });
                    alerta.show();

                }
            }
            if(EnvioDadosServ.getInstance().getPosEnvio() == 1){
                textEnvioDados.setText("ENVIANDO DADOS DO BOLETIM RURÍCOLA...");
            }
            else if(EnvioDadosServ.getInstance().getPosEnvio() == 2){
                textEnvioDados.setText("ENVIANDO DADOS DAS ANALISE FITO...");
            }
            else if(EnvioDadosServ.getInstance().getPosEnvio() == 3){
                textEnvioDados.setText("ENVIANDO DADOS DAS ANALISE PERDA DE COLHEITA...");
            }
            else if(EnvioDadosServ.getInstance().getPosEnvio() == 4){
                textEnvioDados.setText("ENVIANDO DADOS DAS ANALISE DE SOQUEIRA/ARRANQUIO...");
            }
            else if(EnvioDadosServ.getInstance().getPosEnvio() == 5){
                if(!((Activity) EnvioDadosActivity.this).isFinishing()) {

                    AlertDialog.Builder alerta = new AlertDialog.Builder( EnvioDadosActivity.this);
                    alerta.setTitle("ATENÇÃO");
                    alerta.setMessage("TODOS OS DADOS FORAM ENVIADOS COM SUCESSO.");

                    alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent it = new Intent(EnvioDadosActivity.this, MenuInicialActivity.class);
                            startActivity(it);
                            finish();
                        }
                    });

                    alerta.show();

                }
            }

            customHandler.postDelayed(this, 10000);

        }
    };

}