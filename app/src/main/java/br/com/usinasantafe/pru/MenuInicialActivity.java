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

import br.com.usinasantafe.pru.model.bean.variaveis.AlocaFuncBean;
import br.com.usinasantafe.pru.model.bean.variaveis.ApontRuricolaBean;
import br.com.usinasantafe.pru.model.bean.variaveis.BoletimRuricolaBean;
import br.com.usinasantafe.pru.util.ConexaoWeb;
import br.com.usinasantafe.pru.util.EnvioDadosServ;
import br.com.usinasantafe.pru.util.VerifDadosServ;

public class MenuInicialActivity extends ActivityGeneric {

    private ListView lista;
    private PRUContext pruContext;
    private ProgressDialog progressBar;

    private TextView textViewProcesso;
    private Handler customHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_inicial);

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
            if(pruContext.getConfigCTR().hasElements()){
                progressBar.setCancelable(true);
                progressBar.setMessage("Buscando Atualização...");
                progressBar.show();
                VerifDadosServ.getInstance().verAtualAplic(pruContext.versaoAplic, this, progressBar);
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
        lista = (ListView) findViewById(R.id.listaMenuInicial);
        lista.setAdapter(adapterList);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> l, View v, int position,
                                    long id) {

                if (position == 0) {

                    if(pruContext.getConfigCTR().hasElements()){

                        pruContext.setVerPosTela(1);
                        Intent it = new Intent(MenuInicialActivity.this, OSActivity.class);
                        startActivity(it);
                        finish();
                    }

                } else if (position == 1) {
                    Intent it = new Intent(MenuInicialActivity.this, ConfigActivity.class);
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

        int pos1 = verAtual.indexOf("_") + 1;
        String dthr = verAtual.substring(pos1);
        pruContext.getConfigCTR().setDtServConfig(dthr);

        Intent intent = new Intent(this, ReceberAlarme.class);
        boolean alarmeAtivo = (PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_NO_CREATE) == null);

        if(progressBar.isShowing()){
            progressBar.dismiss();
        }

        if(alarmeAtivo){

            Log.i("PMM", "NOVO TIMER");
            PendingIntent p = PendingIntent.getBroadcast(getApplicationContext(), 0,
                    intent, PendingIntent.FLAG_UPDATE_CURRENT);

            Calendar c = Calendar.getInstance();
            c.setTimeInMillis(System.currentTimeMillis());
            c.add(Calendar.SECOND, 1);

            AlarmManager alarme = (AlarmManager) getSystemService(ALARM_SERVICE);
            alarme.setRepeating(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), 60000, p);

        }
        else{
            Log.i("PMM", "TIMER já ativo");
        }

        if(pruContext.getRuricolaCTR().verBolAberto()){
            if(pruContext.getFitoCTR().verCabecFitoAberto()){
                if(pruContext.getFitoCTR().hasTipoAmostraCabec() && !pruContext.getFitoCTR().verTermQuestaoCabec()){
                    pruContext.setVerPosTela(5);
                    Intent it = new Intent(MenuInicialActivity.this, QuestaoFitoActivity.class);
                    startActivity(it);
                    finish();
                }
                else{
                    Intent it = new Intent(MenuInicialActivity.this, ListaPontosFitoActivity.class);
                    startActivity(it);
                    finish();
                }
            }
            else{
                Intent it = new Intent(MenuInicialActivity.this, MenuMotoMecActivity.class);
                startActivity(it);
                finish();
            }
        }

    }

    public boolean checkPermission(String permission){
        int check = ContextCompat.checkSelfPermission(this, permission);
        return (check == PackageManager.PERMISSION_GRANTED);
    }

    private Runnable updateTimerThread = new Runnable() {

        public void run() {

            if (EnvioDadosServ.getInstance().getStatusEnvio() == 1) {
                textViewProcesso.setTextColor(Color.YELLOW);
                textViewProcesso.setText("Enviando Dados...");
            } else if (EnvioDadosServ.getInstance().getStatusEnvio() == 2) {
                textViewProcesso.setTextColor(Color.RED);
                textViewProcesso.setText("Existem Dados para serem Enviados");
            } else if (EnvioDadosServ.getInstance().getStatusEnvio() == 3) {
                textViewProcesso.setTextColor(Color.GREEN);
                textViewProcesso.setText("Todos os Dados já foram Enviados");
            }

            customHandler.postDelayed(this, 10000);
        }
    };

    public void onBackPressed()  {
    }


    public void exibir(){

        BoletimRuricolaBean boletimRuricolaBean = new BoletimRuricolaBean();
        List boletimList = boletimRuricolaBean.all();

        Log.i("PRU", "AKI");

        for (int i = 0; i < boletimList.size(); i++) {

            boletimRuricolaBean = (BoletimRuricolaBean) boletimList.get(i);
            Log.i("PRU", "BOLETIM");
            Log.i("PRU", "idBoletim = " + boletimRuricolaBean.getIdBol());
            Log.i("PRU", "idExtBoletim = " + boletimRuricolaBean.getIdExtBol());
            Log.i("PRU", "idLiderBoletim = " + boletimRuricolaBean.getIdLiderBol());
            Log.i("PRU", "idTurmaBoletim = " + boletimRuricolaBean.getIdTurmaBol());
            Log.i("PRU", "osBoletim = " + boletimRuricolaBean.getOsBol());
            Log.i("PRU", "ativPrincBoletim = " + boletimRuricolaBean.getAtivPrincBol());
            Log.i("PRU", "dthrInicioBoletim = " + boletimRuricolaBean.getDthrInicioBol());
            Log.i("PRU", "dthrFimBoletim = " + boletimRuricolaBean.getDthrFimBol());
            Log.i("PRU", "statusBoletim = " + boletimRuricolaBean.getStatusBol());

        }

        ApontRuricolaBean apontRuricolaBean = new ApontRuricolaBean();
        List apontaList = apontRuricolaBean.all();

        for (int i = 0; i < apontaList.size(); i++) {

            apontRuricolaBean = (ApontRuricolaBean) apontaList.get(i);
            Log.i("PRU", "APONTAMENTO");
            Log.i("PRU", "idAponta = " + apontRuricolaBean.getIdApont());
            Log.i("PRU", "idBolAponta = " + apontRuricolaBean.getIdBolApont());
            Log.i("PRU", "idExtBolAponta = " + apontRuricolaBean.getIdExtBolApont());
            Log.i("PRU", "osAponta = " + apontRuricolaBean.getOsApont());
            Log.i("PRU", "atividadeAponta = " + apontRuricolaBean.getAtivApont());
            Log.i("PRU", "paradaAponta = " + apontRuricolaBean.getParadaApont());
            Log.i("PRU", "funcAponta = " + apontRuricolaBean.getFuncApont());
            Log.i("PRU", "dthrAponta = " + apontRuricolaBean.getDthrApont());

        }

        AlocaFuncBean alocaFuncBean = new AlocaFuncBean();
        List alocaFuncList = alocaFuncBean.all();

        for (int l = 0; l < alocaFuncList.size(); l++) {
            alocaFuncBean = (AlocaFuncBean) alocaFuncList.get(l);
            Log.i("PRU", "ALOCA FUNC");
            Log.i("PRU", "idAlocaFunc = " + alocaFuncBean.getIdAlocaFunc());
            Log.i("PRU", "idBolAlocaFunc = " + alocaFuncBean.getIdBolAlocaFunc());
            Log.i("PRU", "idExtBolAlocaFunc = " + alocaFuncBean.getIdExtBolAlocaFunc());
            Log.i("PRU", "codFuncionarioAlocaFunc = " + alocaFuncBean.getMatricFuncAlocaFunc());
            Log.i("PRU", "dthrAlocaFunc = " + alocaFuncBean.getDthrAlocaFunc());
            Log.i("PRU", "tipoAlocaFunc = " + alocaFuncBean.getTipoAlocaFunc());
        }


    }

}
