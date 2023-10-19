package br.com.usinasantafe.pru.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import br.com.usinasantafe.pru.PRUContext;
import br.com.usinasantafe.pru.R;
import br.com.usinasantafe.pru.util.EnvioDadosServ;

public class TelaInicialActivity extends ActivityGeneric {

    private PRUContext pruContext;
    private Handler customHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_inicial);

        pruContext = (PRUContext) getApplication();
        customHandler.postDelayed(excluirBDThread, 0);

    }

    private Runnable excluirBDThread = () -> {
        if(EnvioDadosServ.getInstance().verifDadosEnvio()){
            if(connectNetwork){
                EnvioDadosServ.getInstance().envioDados();
            } else {
                EnvioDadosServ.status = 1;
            }
        } else {
            EnvioDadosServ.status = 3;
        }
        goMenuInicial();
    };

    private Runnable encerraAtualThread = () -> goMenuInicial();

    public void goMenuInicial(){
        customHandler.removeCallbacks(encerraAtualThread);
        if(pruContext.getRuricolaCTR().verBolAberto()){
            Intent it = new Intent(TelaInicialActivity.this, MenuApontActivity.class);
            startActivity(it);
            finish();
        } else {
            Intent it = new Intent(TelaInicialActivity.this, MenuInicialActivity.class);
            startActivity(it);
            finish();
        }
    }

}