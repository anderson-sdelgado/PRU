package br.com.usinasantafe.pru;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import br.com.usinasantafe.pru.bo.ManipDadosEnvio;
import br.com.usinasantafe.pru.bo.Tempo;
import br.com.usinasantafe.pru.pst.DatabaseHelper;

public class ReceberAlarme extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        if(DatabaseHelper.getInstance() == null){
            new DatabaseHelper(context);
        }

        if(Tempo.getInstance().getDatahora() != null) {
            Tempo.getInstance().getDatahora().setTime(Tempo.getInstance().getDatahora().getTime() + 60000L);
        }

        Log.i("PRU", "DATA HORA = " + Tempo.getInstance().data());
        if(ManipDadosEnvio.getInstance().verifDadosEnvio()){
            Log.i("PRU", "ENVIANDO");
            ManipDadosEnvio.getInstance().envioDados(context);
        }
    }

}
