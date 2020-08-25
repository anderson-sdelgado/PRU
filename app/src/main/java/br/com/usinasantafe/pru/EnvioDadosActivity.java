package br.com.usinasantafe.pru;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import br.com.usinasantafe.pru.util.EnvioDadosServ;

public class EnvioDadosActivity extends AppCompatActivity {

    private TextView textEnvioDados;
    private PRUContext pruContext;
    private Handler customHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_envio_dados);

        pruContext = (PRUContext) getApplication();
        textEnvioDados = (TextView) findViewById(R.id.textEnvioDados);

    }

    private Runnable updateTimerThread = new Runnable() {

        public void run() {

            if(EnvioDadosServ.getInstance().getPosEnvio() == -1){

                AlertDialog.Builder alerta = new AlertDialog.Builder( EnvioDadosActivity.this);
                alerta.setTitle("ATENÇÃO");
                alerta.setMessage("FALHA NO ENVIO DE DADOS! TENTE NOVAMENTE NUM LUGAR COM MELHOR SINAL.");

                alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                alerta.show();

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
            else if(EnvioDadosServ.getInstance().getStatusEnvio() == 4){
                textEnvioDados.setText("ENVIANDO DADOS DAS ANALISE DE SOQUEIRA/ARRANQUIO...");
            }
            else if(EnvioDadosServ.getInstance().getStatusEnvio() == 5){
                AlertDialog.Builder alerta = new AlertDialog.Builder( EnvioDadosActivity.this);
                alerta.setTitle("ATENÇÃO");
                alerta.setMessage("TODOS OS DADOS FORAM ENVIADOS COM SUCESSO.");

                alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                alerta.show();
            }

            customHandler.postDelayed(this, 10000);
        }
    };

}