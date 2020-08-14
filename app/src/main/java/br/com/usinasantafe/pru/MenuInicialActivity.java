package br.com.usinasantafe.pru;

import android.Manifest;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;

import br.com.usinasantafe.pru.util.ConexaoWeb;
import br.com.usinasantafe.pru.util.EnvioDadosServ;

public class MenuInicialActivity extends ActivityGeneric {

    private ListView menuInicialListView;
    private PRUContext pruContext;
    private ProgressDialog progressBar;

    private TextView textViewProcessoNormal;
    private Handler customHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_inicial);

        pruContext = (PRUContext) getApplication();
        textViewProcessoNormal = (TextView) findViewById(R.id.textViewProcesso);

        if(!checkPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)){
            String[] PERMISSIONS = {android.Manifest.permission.WRITE_EXTERNAL_STORAGE};
            ActivityCompat.requestPermissions((Activity) this, PERMISSIONS, 112);
        }

        customHandler.postDelayed(updateTimerThread, 0);

        ConexaoWeb conexaoWeb = new ConexaoWeb();

        if(conexaoWeb.verificaConexao(this))
        {
            progressBar = new ProgressDialog(this);
            if(pruContext.getConfigCTR().hasElements()){
                progressBar.setCancelable(true);
                progressBar.setMessage("Buscando Atualização...");
                progressBar.show();
                pruContext.getConfigCTR().verAtualAplic(pruContext.versaoAplic, this, progressBar);
            }
            else{
                progressBar.dismiss();
            }

        }
        else{
            startTimer("N_SD");
        }

        listarMenuInicial();

    }

    private void listarMenuInicial() {

        ArrayList<String> itens = new ArrayList<String>();

        itens.add("BOLETIM");
        itens.add("CONFIGURAÇÃO");
        itens.add("SAIR");

        AdapterList adapterList = new AdapterList(this, itens);
        menuInicialListView = (ListView) findViewById(R.id.listaMenuInicial);
        menuInicialListView.setAdapter(adapterList);

        menuInicialListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> l, View v, int position,
                                    long id) {

                if (position == 0) {

                    if(pruContext.getConfigCTR().hasElements()){

                        pruContext.getConfigCTR().clearBD();
                        pruContext.setVerPosTela(1);
                        Intent it = new Intent(MenuInicialActivity.this, OSActivity.class);
                        startActivity(it);
                        finish();
                    }

                } else if (position == 1) {
                    Intent it = new Intent(MenuInicialActivity.this, SenhaActivity.class);
                    startActivity(it);
                    finish();
                } else if (position == 2) {
                    Intent intent = new Intent(Intent.ACTION_MAIN);
                    intent.addCategory(Intent.CATEGORY_HOME);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
            }

        });

    }

    public void startTimer(String verAtual) {

        Log.i("PMM", "VERATUAL = " + verAtual);

        if(!verAtual.equals("N_SD")){
            int pos1 = verAtual.indexOf("#") + 1;
            String dthr = verAtual.substring(pos1);
            Log.i("PMM", "DTHR = " + dthr);
            pruContext.getConfigCTR().setDtServConfig(dthr);
        }

        Intent intent = new Intent(this, ReceberAlarme.class);
        boolean alarmeAtivo = (PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_NO_CREATE) == null);

        if(progressBar.isShowing()){
            progressBar.dismiss();
        }

        if(alarmeAtivo){

            Log.i("PRU", "NOVO TIMER");
            PendingIntent p = PendingIntent.getBroadcast(getApplicationContext(), 0,
                    intent, PendingIntent.FLAG_UPDATE_CURRENT);

            Calendar c = Calendar.getInstance();
            c.setTimeInMillis(System.currentTimeMillis());
            c.add(Calendar.SECOND, 1);

            AlarmManager alarme = (AlarmManager) getSystemService(ALARM_SERVICE);
            alarme.setRepeating(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), 60000, p);

        }
        else{
            Log.i("PRU", "TIMER já ativo");
        }

    }

    public boolean checkPermission(String permission){
        int check = ContextCompat.checkSelfPermission(this, permission);
        return (check == PackageManager.PERMISSION_GRANTED);
    }

    private Runnable updateTimerThread = new Runnable() {

        public void run() {

            if(EnvioDadosServ.getInstance().getStatusEnvio() == 1){
                textViewProcessoNormal.setTextColor(Color.RED);
                textViewProcessoNormal.setText("Existem Dados para serem Enviados");
            }
            else if(EnvioDadosServ.getInstance().getStatusEnvio() == 2){
                textViewProcessoNormal.setTextColor(Color.GREEN);
                textViewProcessoNormal.setText("Todos os Dados já foram Enviados");
            }

            customHandler.postDelayed(this, 10000);
        }
    };

    public void onBackPressed()  {
    }

}
