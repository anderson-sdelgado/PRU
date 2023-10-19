package br.com.usinasantafe.pru.view;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;

import android.os.Handler;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import br.com.usinasantafe.pru.BuildConfig;
import br.com.usinasantafe.pru.PRUContext;
import br.com.usinasantafe.pru.R;
import br.com.usinasantafe.pru.util.EnvioDadosServ;

public class MenuInicialActivity extends ActivityGeneric {

    private ListView menuInicialListView;
    private PRUContext pruContext;

    private TextView textViewProcesso;
    private TextView textViewPrincipal;

    private Handler customHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_inicial);

        pruContext = (PRUContext) getApplication();
        textViewProcesso = findViewById(R.id.textViewProcesso);
        textViewPrincipal = findViewById(R.id.textViewPrincipal);

        textViewPrincipal.setText("PRINCIPAL - V " + BuildConfig.VERSION_NAME);

        if(!checkPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)){
            String[] PERMISSIONS = {android.Manifest.permission.WRITE_EXTERNAL_STORAGE};
            ActivityCompat.requestPermissions((Activity) this, PERMISSIONS, 112);
        }

        customHandler.postDelayed(updateTimerThread, 0);

        listarMenuInicial();

    }

    private void listarMenuInicial() {

        ArrayList<String> itens = new ArrayList<String>();

        itens.add("BOLETIM");
        itens.add("CONFIGURAÇÃO");
        itens.add("RELATÓRIO");
        itens.add("SAIR");

        AdapterList adapterList = new AdapterList(this, itens);
        menuInicialListView = findViewById(R.id.listaMenuInicial);
        menuInicialListView.setAdapter(adapterList);

        menuInicialListView.setOnItemClickListener((l, v, position, id) -> {

            TextView textView = v.findViewById(R.id.textViewItemList);
            String text = textView.getText().toString();

            switch (text) {
                case "BOLETIM": {
                    if (pruContext.getConfigCTR().hasElemConfig()) {
                        pruContext.getConfigCTR().clearBD();
                        pruContext.setVerPosTela(1);
                        Intent it = new Intent(MenuInicialActivity.this, OSActivity.class);
                        startActivity(it);
                        finish();
                    }
                    break;
                }
                case "CONFIGURAÇÃO": {
                    Intent it = new Intent(MenuInicialActivity.this, SenhaActivity.class);
                    startActivity(it);
                    finish();
                    break;
                }
                case "RELATÓRIO": {
                    if(pruContext.getRuricolaCTR().verBolFechadoEnviado()){
                        pruContext.setPosCabec(0);
                        Intent it = new Intent(MenuInicialActivity.this, RelatCabecActivity.class);
                        startActivity(it);
                        finish();
                    } else {
                        AlertDialog.Builder alerta = new AlertDialog.Builder( MenuInicialActivity.this);
                        alerta.setTitle("ATENÇÃO");
                        alerta.setMessage("NÃO CONTÉM BOLETIM RURÍCOLAS NA BASE DE DADOS.");
                        alerta.setPositiveButton("OK", (dialog, which) -> {
                        });
                        alerta.show();
                    }
                }
                case "SAIR": {
                    Intent intent = new Intent(Intent.ACTION_MAIN);
                    intent.addCategory(Intent.CATEGORY_HOME);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    break;
                }
            }
        });

    }

    public boolean checkPermission(String permission){
        int check = ContextCompat.checkSelfPermission(this, permission);
        return (check == PackageManager.PERMISSION_GRANTED);
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
