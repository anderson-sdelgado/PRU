package br.com.usinasantafe.pru;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

import br.com.usinasantafe.pru.bo.ConexaoWeb;
import br.com.usinasantafe.pru.bo.ManipDadosVerif;
import br.com.usinasantafe.pru.to.tb.estaticas.OSTO;
import br.com.usinasantafe.pru.to.tb.estaticas.ROSAtivTO;

public class OSActivity extends ActivityGeneric {

    private PRUContext pruContext;
    private ProgressDialog progressBar;
    private Handler customHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_os);

        pruContext = (PRUContext) getApplication();

        Button buttonOkOS = (Button) findViewById(R.id.buttonOkPadrao);
        Button buttonCancOS = (Button) findViewById(R.id.buttonCancPadrao);

        buttonOkOS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (!editTextPadrao.getText().toString().equals("")) {

                    OSTO osTO = new OSTO();
                    if(pruContext.getVerPosTelaPrinc() == 1){
                        pruContext.getBoletimTO().setOsBoletim(Long.parseLong(editTextPadrao.getText().toString()));
                        osTO.deleteAll();
                        ROSAtivTO rosAtivTO = new ROSAtivTO();
                        rosAtivTO.deleteAll();
                    }
                    else{
                        pruContext.getApontamentoTO().setOsAponta(Long.parseLong(editTextPadrao.getText().toString()));
                    }

                    if (osTO.exists("nroOS", Long.parseLong(editTextPadrao.getText().toString()))) {

                        Intent it = new Intent(OSActivity.this, ListaAtivActivity.class);
                        startActivity(it);
                        finish();

                    } else {

                        ConexaoWeb conexaoWeb = new ConexaoWeb();

                        if (conexaoWeb.verificaConexao(OSActivity.this)) {

                            progressBar = new ProgressDialog(v.getContext());
                            progressBar.setCancelable(true);
                            progressBar.setMessage("Pequisando a OS...");
                            progressBar.show();

                            customHandler.postDelayed(updateTimerThread, 10000);

                            ManipDadosVerif.getInstance().verDados(editTextPadrao.getText().toString(), "OS"
                                    , OSActivity.this, ListaAtivActivity.class, progressBar);

                        } else {

                            Intent it = new Intent(OSActivity.this, ListaAtivActivity.class);
                            startActivity(it);
                            finish();

                        }

                    }

                }
            }
        });

        buttonCancOS.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (editTextPadrao.getText().toString().length() > 0) {
                    editTextPadrao.setText(editTextPadrao.getText().toString().substring(0, editTextPadrao.getText().toString().length() - 1));
                }
            }
        });

    }

    public void onBackPressed()  {
        if(pruContext.getVerPosTelaPrinc() == 1){
            Intent it = new Intent(OSActivity.this, PrincipalActivity.class);
            startActivity(it);
            finish();
        }
        else{
            Intent it = new Intent(OSActivity.this, MenuPrincipalActivity.class);
            startActivity(it);
            finish();
        }
    }

    private Runnable updateTimerThread = new Runnable() {

        public void run() {

            if(!ManipDadosVerif.getInstance().isVerTerm()) {

                ManipDadosVerif.getInstance().cancelVer();
                if (progressBar.isShowing()) {
                    progressBar.dismiss();
                }

                Intent it = new Intent(OSActivity.this, ListaAtivActivity.class);
                startActivity(it);
                finish();

            }

        }
    };

}
