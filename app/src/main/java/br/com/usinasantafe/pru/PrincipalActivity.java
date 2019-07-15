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
import java.util.List;

import br.com.usinasantafe.pru.bo.ConexaoWeb;
import br.com.usinasantafe.pru.bo.ManipDadosEnvio;
import br.com.usinasantafe.pru.bo.ManipDadosReceb;
import br.com.usinasantafe.pru.bo.ManipDadosVerif;
import br.com.usinasantafe.pru.to.tb.variaveis.AlocaFuncTO;
import br.com.usinasantafe.pru.to.tb.variaveis.ApontTO;
import br.com.usinasantafe.pru.to.tb.variaveis.AtualizaTO;
import br.com.usinasantafe.pru.to.tb.variaveis.BoletimTO;
import br.com.usinasantafe.pru.to.tb.variaveis.ConfiguracaoTO;

public class PrincipalActivity extends ActivityGeneric {

    private ListView lista;
    private PRUContext pruContext;
    private ProgressDialog progressBar;

    private TextView textViewProcesso;
    private Handler customHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        pruContext = (PRUContext) getApplication();
        textViewProcesso = (TextView) findViewById(R.id.textViewProcesso);

//        exibir();


        if(!checkPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)){
            String[] PERMISSIONS = {android.Manifest.permission.WRITE_EXTERNAL_STORAGE};
            ActivityCompat.requestPermissions((Activity) this, PERMISSIONS, 112);
        }

        customHandler.postDelayed(updateTimerThread, 0);

        ConexaoWeb conexaoWeb = new ConexaoWeb();

        if(conexaoWeb.verificaConexao(this))
        {

            progressBar = new ProgressDialog(this);
            progressBar.setCancelable(true);
            progressBar.setMessage("Buscando Atualização...");
            progressBar.show();

            ConfiguracaoTO configuracaoTO = new ConfiguracaoTO();
            List configList = configuracaoTO.all();
            if(configList.size() > 0){
                configuracaoTO = (ConfiguracaoTO) configList.get(0);
                AtualizaTO atualizaTO = new AtualizaTO();
                atualizaTO.setIdCelularAtual(configuracaoTO.getNumLinha());
                atualizaTO.setVersaoAtual(pruContext.versaoAplic);
                ManipDadosVerif.getInstance().verAtualizacao(atualizaTO, this, progressBar);
            }
            else{
                progressBar.dismiss();
            }

        }
        else{
            startTimer();
        }


        BoletimTO boletimTO = new BoletimTO();
        List boletimList = boletimTO.get("statusBoletim", 1L);

        if(boletimList.size() > 0) {
            Intent it = new Intent(PrincipalActivity.this, MenuPrincipalActivity.class);
            startActivity(it);
            finish();
        }

        listarMenuInicial();

    }

    private void listarMenuInicial() {

        ArrayList<String> itens = new ArrayList<String>();

        itens.add("BOLETIM");
        itens.add("CONFIGURAÇÃO");
        itens.add("SAIR");

        AdapterList adapterList = new AdapterList(this, itens);
        lista = (ListView) findViewById(R.id.listaMenuInicial);
        lista.setAdapter(adapterList);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> l, View v, int position,
                                    long id) {
                // TODO Auto-generated method stub
                if (position == 0) {

                    ConfiguracaoTO configuracaoTO = new ConfiguracaoTO();

                    if(configuracaoTO.hasElements()){

                        pruContext.setVerPosTelaPrinc(1);
                        Intent it = new Intent(PrincipalActivity.this, OSActivity.class);
                        startActivity(it);
                        finish();
                    }

                } else if (position == 1) {
                    Intent it = new Intent(PrincipalActivity.this, ConfiguracaoActivity.class);
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

    public void startTimer() {

        boolean alarmeAtivo = (PendingIntent.getBroadcast(this, 0, new Intent("ALARME_DISPARADO"), PendingIntent.FLAG_NO_CREATE) == null);

        if(alarmeAtivo){

            ManipDadosReceb.getInstance().tempo();
            Log.i("PMM", "NOVO TIMER");

            Intent intent = new Intent("EXECUTAR_ALARME");
            PendingIntent p = PendingIntent.getBroadcast(this, 0, intent, 0);

            Calendar c = Calendar.getInstance();
            c.setTimeInMillis(System.currentTimeMillis());
            c.add(Calendar.SECOND, 1);

            AlarmManager alarme = (AlarmManager) getSystemService(ALARM_SERVICE);
            alarme.setRepeating(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), 60000, p);

        }
        else{
            Log.i("PMM", "TIMER já ativo");
        }
    }

    public boolean checkPermission(String permission){
        int check = ContextCompat.checkSelfPermission(this, permission);
        return (check == PackageManager.PERMISSION_GRANTED);
    }

    private Runnable updateTimerThread = new Runnable() {

        public void run() {

            if (ManipDadosEnvio.getInstance().getStatusEnvio() == 1) {
                textViewProcesso.setTextColor(Color.YELLOW);
                textViewProcesso.setText("Enviando Dados...");
            } else if (ManipDadosEnvio.getInstance().getStatusEnvio() == 2) {
                textViewProcesso.setTextColor(Color.RED);
                textViewProcesso.setText("Existem Dados para serem Enviados");
            } else if (ManipDadosEnvio.getInstance().getStatusEnvio() == 3) {
                textViewProcesso.setTextColor(Color.GREEN);
                textViewProcesso.setText("Todos os Dados já foram Enviados");
            }

            customHandler.postDelayed(this, 10000);
        }
    };

    public void onBackPressed()  {
    }


    public void exibir(){

        BoletimTO boletimTO = new BoletimTO();
        List boletimList = boletimTO.all();

        Log.i("PRU", "AKI");

        for (int i = 0; i < boletimList.size(); i++) {

            boletimTO = (BoletimTO) boletimList.get(i);
            Log.i("PRU", "BOLETIM");
            Log.i("PRU", "idBoletim = " + boletimTO.getIdBoletim());
            Log.i("PRU", "idExtBoletim = " + boletimTO.getIdExtBoletim());
            Log.i("PRU", "idLiderBoletim = " + boletimTO.getIdLiderBoletim());
            Log.i("PRU", "idTurmaBoletim = " + boletimTO.getIdTurmaBoletim());
            Log.i("PRU", "osBoletim = " + boletimTO.getOsBoletim());
            Log.i("PRU", "ativPrincBoletim = " + boletimTO.getAtivPrincBoletim());
            Log.i("PRU", "dthrInicioBoletim = " + boletimTO.getDthrInicioBoletim());
            Log.i("PRU", "dthrFimBoletim = " + boletimTO.getDthrFimBoletim());
            Log.i("PRU", "statusBoletim = " + boletimTO.getStatusBoletim());

        }

        ApontTO apontTO = new ApontTO();
        List apontaList = apontTO.all();

        for (int i = 0; i < apontaList.size(); i++) {

            apontTO = (ApontTO) apontaList.get(i);
            Log.i("PRU", "APONTAMENTO");
            Log.i("PRU", "idAponta = " + apontTO.getIdAponta());
            Log.i("PRU", "idBolAponta = " + apontTO.getIdBolAponta());
            Log.i("PRU", "idExtBolAponta = " + apontTO.getIdExtBolAponta());
            Log.i("PRU", "osAponta = " + apontTO.getOsAponta());
            Log.i("PRU", "atividadeAponta = " + apontTO.getAtivAponta());
            Log.i("PRU", "paradaAponta = " + apontTO.getParadaAponta());
            Log.i("PRU", "funcAponta = " + apontTO.getFuncAponta());
            Log.i("PRU", "dthrAponta = " + apontTO.getDthrAponta());

        }

        AlocaFuncTO alocaFuncTO = new AlocaFuncTO();
        List alocaFuncList = alocaFuncTO.all();

        for (int l = 0; l < alocaFuncList.size(); l++) {
            alocaFuncTO = (AlocaFuncTO) alocaFuncList.get(l);
            Log.i("PRU", "ALOCA FUNC");
            Log.i("PRU", "idAlocaFunc = " + alocaFuncTO.getIdAlocaFunc());
            Log.i("PRU", "idBolAlocaFunc = " + alocaFuncTO.getIdBolAlocaFunc());
            Log.i("PRU", "idExtBolAlocaFunc = " + alocaFuncTO.getIdExtBolAlocaFunc());
            Log.i("PRU", "codFuncionarioAlocaFunc = " + alocaFuncTO.getCodFuncionarioAlocaFunc());
            Log.i("PRU", "dthrAlocaFunc = " + alocaFuncTO.getDthrAlocaFunc());
            Log.i("PRU", "tipoAlocaFunc = " + alocaFuncTO.getTipoAlocaFunc());
        }


    }

}
