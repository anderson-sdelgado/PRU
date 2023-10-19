package br.com.usinasantafe.pru.view;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;

import br.com.usinasantafe.pru.PRUContext;
import br.com.usinasantafe.pru.R;
import br.com.usinasantafe.pru.util.VerifDadosServ;

public class OSActivity extends ActivityGeneric {

    private PRUContext pruContext;
    private ProgressDialog progressBar;
    private Handler customHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_os);

        pruContext = (PRUContext) getApplication();

        Button buttonOkOS = findViewById(R.id.buttonOkPadrao);
        Button buttonCancOS = findViewById(R.id.buttonCancPadrao);

        buttonOkOS.setOnClickListener(v -> {

            if (!editTextPadrao.getText().toString().equals("")) {

                try{

                    Long nroOS = Long.parseLong(editTextPadrao.getText().toString());
                    pruContext.getConfigCTR().setOsConfig(nroOS);

                    if (pruContext.getConfigCTR().verOS(nroOS)) {

                        if(connectNetwork){
                            pruContext.getConfigCTR().setStatusConConfig(1L);
                        } else {
                            pruContext.getConfigCTR().setStatusConConfig(0L);
                        }

                        Intent it = new Intent(OSActivity.this, ListaAtividadeActivity.class);
                        startActivity(it);
                        finish();

                    } else {

                        if(connectNetwork){

                            progressBar = new ProgressDialog(v.getContext());
                            progressBar.setCancelable(true);
                            progressBar.setMessage("PESQUISANDO OS...");
                            progressBar.show();

                            customHandler.postDelayed(updateTimerThread, 10000);

                            pruContext.getConfigCTR().verOS(editTextPadrao.getText().toString()
                                    , OSActivity.this, ListaAtividadeActivity.class, progressBar);

                        } else {

                            pruContext.getConfigCTR().setStatusConConfig(0L);

                            Intent it = new Intent(OSActivity.this, ListaAtividadeActivity.class);
                            startActivity(it);
                            finish();

                        }

                    }

                } catch (NumberFormatException e){

                    AlertDialog.Builder alerta = new AlertDialog.Builder( OSActivity.this);
                    alerta.setTitle("ATENÇÃO");
                    alerta.setMessage("VALOR DE OS INCORRETO! FAVOR VERIFICAR.");
                    alerta.setPositiveButton("OK", (dialog, which) -> {
                    });

                    alerta.show();

                }

            }
        });

        buttonCancOS.setOnClickListener(v -> {
            if (editTextPadrao.getText().toString().length() > 0) {
                editTextPadrao.setText(editTextPadrao.getText().toString().substring(0, editTextPadrao.getText().toString().length() - 1));
            }
        });

    }

    public void onBackPressed()  {
        Intent it;
        if(pruContext.getVerPosTela() == 1) {
            it = new Intent(OSActivity.this, TelaInicialActivity.class);
        } else {
            it = new Intent(OSActivity.this, MenuApontActivity.class);
        }
        startActivity(it);
        finish();
    }

    private Runnable updateTimerThread = new Runnable() {

        public void run() {

            if(VerifDadosServ.status < 3) {

                VerifDadosServ.getInstance().cancel();
                if (progressBar.isShowing()) {
                    progressBar.dismiss();
                }

                Intent it = new Intent(OSActivity.this, ListaAtividadeActivity.class);
                startActivity(it);
                finish();

            }

        }
    };

}
