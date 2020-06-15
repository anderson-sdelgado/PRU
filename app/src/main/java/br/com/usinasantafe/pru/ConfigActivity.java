package br.com.usinasantafe.pru;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pru.model.bean.estaticas.TipoApontBean;
import br.com.usinasantafe.pru.model.bean.estaticas.TurmaBean;
import br.com.usinasantafe.pru.model.bean.variaveis.ConfigBean;
import br.com.usinasantafe.pru.util.ConexaoWeb;
import br.com.usinasantafe.pru.util.AtualDadosServ;

public class ConfigActivity extends ActivityGeneric {

    private ProgressDialog progressBar;
    private AdapterList adapterList;
    private AlertDialog alerta;
    private List tipoList;
    private List turmaList;
    private TextView textViewTipoConfig;
    private TextView textViewTurmaConfig;
    private TextView textViewFuncConfig;
    private EditText editTextFuncConfig;
    private EditText editTextNLinhaConfig;
    private PRUContext pruContext;
    private ConfigBean configBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);

        Button buttonAtualBDConfig = (Button) findViewById(R.id.buttonAtualizarDados);
        Button buttonSalvarConfig =  (Button) findViewById(R.id.buttonSalvarConfig);
        Button buttonCancConfig = (Button) findViewById(R.id.buttonCancConfig);
        textViewTipoConfig = (TextView) findViewById(R.id.textViewTipoConfig);
        textViewTurmaConfig = (TextView) findViewById(R.id.textViewTurmaConfig);
        textViewFuncConfig = (TextView) findViewById(R.id.textViewFuncConfig);
        editTextFuncConfig = (EditText) findViewById(R.id.editTextFuncConfig);
        editTextNLinhaConfig = (EditText) findViewById(R.id.editTextNLinhaConfig);

        if(!pruContext.getConfigCTR().hasElements()) {
            textViewFuncConfig.setText("");
            textViewFuncConfig.setEnabled(false);
            editTextFuncConfig.setEnabled(false);
            configBean.setIdTipo(0L);
            configBean.setIdTurma(0L);
        }
        else{

            configBean = pruContext.getConfigCTR().getConfig();

            TipoApontBean tipoApontBean = pruContext.getConfigCTR().getTipoApont(configBean.getIdTipo());
            textViewTipoConfig.setText(tipoApontBean.getIdTipo() + " - " + tipoApontBean.getDescrTipo());

            TurmaBean turmaBean = pruContext.getConfigCTR().getTurma(configBean.getIdTurma());
            textViewTurmaConfig.setText(turmaBean.getCodTurma() + " - " + turmaBean.getDescrTurma());

            editTextNLinhaConfig.setText(String.valueOf(configBean.getNumLinha()));

            switch ((int) configBean.getIdTipo().longValue()) {
                case 1:
                    textViewFuncConfig.setText("LÍDER:");
                    editTextFuncConfig.setText(String.valueOf(configBean.getCodFunc()));
                    textViewFuncConfig.setEnabled(true);
                    editTextFuncConfig.setEnabled(true);
                    break;
                case 2:
                    textViewFuncConfig.setText("COLAB.:");
                    editTextFuncConfig.setText(String.valueOf(configBean.getCodFunc()));
                    textViewFuncConfig.setEnabled(true);
                    editTextFuncConfig.setEnabled(true);
                    break;
                case 3:
                    textViewFuncConfig.setText("");
                    editTextFuncConfig.setText("");
                    textViewFuncConfig.setEnabled(false);
                    editTextFuncConfig.setEnabled(false);
                    break;
            }


        }

        textViewTipoConfig.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                if(pruContext.getConfigCTR().hasElementsTipoApont()) {

                    ArrayList<String> itens = new ArrayList<String>();
                    for (int i = 0; i < tipoList.size(); i++) {
                        tipoApontBean = (br.com.usinasantafe.pru.to.tb.estaticas.TipoApontBean) tipoList.get(i);
                        itens.add(tipoApontBean.getIdTipo() + " - " + tipoApontBean.getDescrTipo());
                    }

                    adapterList = new AdapterList(ConfigActivity.this, itens);

                    AlertDialog.Builder builder = new AlertDialog.Builder(ConfigActivity.this);
                    builder.setTitle("TIPO:");
                    builder.setSingleChoiceItems(adapterList, 0, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface arg0, int arg1) {

                            editTextFuncConfig.setText("");
                            br.com.usinasantafe.pru.to.tb.estaticas.TipoApontBean tipoApontBean = (br.com.usinasantafe.pru.to.tb.estaticas.TipoApontBean) tipoList.get(arg1);
                            textViewTipoConfig.setText(tipoApontBean.getIdTipo() + " - " + tipoApontBean.getDescrTipo());
                            configBean.setIdTipo(tipoApontBean.getIdTipo());

                            switch ((int) tipoApontBean.getIdTipo().longValue()) {
                                case 1:
                                    textViewFuncConfig.setText("LÍDER:");
                                    textViewFuncConfig.setEnabled(true);
                                    editTextFuncConfig.setEnabled(true);
                                    break;
                                case 2:
                                    textViewFuncConfig.setText("COLAB.:");
                                    textViewFuncConfig.setEnabled(true);
                                    editTextFuncConfig.setEnabled(true);
                                    break;
                                case 3:
                                    textViewFuncConfig.setText("");
                                    textViewFuncConfig.setEnabled(false);
                                    editTextFuncConfig.setEnabled(false);
                                    break;
                            }

                            alerta.dismiss();
                        }
                    });

                    alerta = builder.create();
                    alerta.show();

                }

            }

        });

        textViewTurmaConfig.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                br.com.usinasantafe.pru.to.tb.estaticas.TurmaBean turmaBean = new br.com.usinasantafe.pru.to.tb.estaticas.TurmaBean();
                turmaList = turmaBean.orderBy("codTurma", true);

                if(turmaList.size() > 0) {

                    ArrayList<String> itens = new ArrayList<String>();

                    for (int i = 0; i < turmaList.size(); i++) {
                        turmaBean = (br.com.usinasantafe.pru.to.tb.estaticas.TurmaBean) turmaList.get(i);
                        itens.add(turmaBean.getCodTurma() + " - " + turmaBean.getDescrTurma());
                    }

                    adapterList = new AdapterList(ConfigActivity.this, itens);

                    AlertDialog.Builder builder = new AlertDialog.Builder(ConfigActivity.this);
                    builder.setTitle("TURMA:");
                    builder.setSingleChoiceItems(adapterList, 0, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface arg0, int arg1) {

                            br.com.usinasantafe.pru.to.tb.estaticas.TurmaBean turmaBean = (br.com.usinasantafe.pru.to.tb.estaticas.TurmaBean) turmaList.get(arg1);
                            textViewTurmaConfig.setText(turmaBean.getCodTurma() + " - " + turmaBean.getDescrTurma());
                            configBean.setIdTurma(turmaBean.getIdTurma());

                            alerta.dismiss();
                        }
                    });

                    alerta = builder.create();
                    alerta.show();

                }

            }

        });



        buttonAtualBDConfig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                ConexaoWeb conexaoWeb = new ConexaoWeb();

                if(conexaoWeb.verificaConexao(ConfigActivity.this)){

                    progressBar = new ProgressDialog(v.getContext());
                    progressBar.setCancelable(true);
                    progressBar.setMessage("ATUALIZANDO ...");
                    progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                    progressBar.setProgress(0);
                    progressBar.setMax(100);
                    progressBar.show();

                    AtualDadosServ.getInstance().atualizarBD(progressBar);
                    AtualDadosServ.getInstance().setContext(ConfigActivity.this);

                }
                else{
                    AlertDialog.Builder alerta = new AlertDialog.Builder(ConfigActivity.this);
                    alerta.setTitle("ATENÇÃO");
                    alerta.setMessage("FALHA NA CONEXÃO DE DADOS. O CELULAR ESTA SEM SINAL. POR FAVOR, TENTE NOVAMENTE QUANDO O CELULAR ESTIVE COM SINAL.");

                    alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // TODO Auto-generated method stub

                        }
                    });
                    alerta.show();
                }

            }
        });


        buttonSalvarConfig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                if(configBean.getIdTurma() > 0) {

                    AlertDialog.Builder alerta;

                    if(!editTextNLinhaConfig.getText().toString().equals("")) {

                        switch ((int) configBean.getIdTipo().longValue()) {
                            case 0:
                                alerta = new AlertDialog.Builder(ConfigActivity.this);
                                alerta.setTitle("ATENÇÃO");
                                alerta.setMessage("POR FAVOR! SELECIONE ALGUM TIPO.");
                                alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        // TODO Auto-generated method stub

                                    }
                                });
                                alerta.show();
                                break;
                            case 1:

                                if (!editTextFuncConfig.getText().toString().equals("")) {

                                    configBean.deleteAll();

                                    br.com.usinasantafe.pru.to.tb.estaticas.LiderBean liderBean = new br.com.usinasantafe.pru.to.tb.estaticas.LiderBean();
                                    List liderList = liderBean.get("codLider", Long.valueOf(editTextFuncConfig.getText().toString()));

                                    if (liderList.size() > 0){

                                        configBean.deleteAll();
                                        configBean.setCodFunc(Long.valueOf(editTextFuncConfig.getText().toString()));

                                        Intent it = new Intent(ConfigActivity.this, MenuInicialActivity.class);
                                        startActivity(it);
                                        finish();

                                    } else {
                                        alerta = new AlertDialog.Builder(ConfigActivity.this);
                                        alerta.setTitle("ATENÇÃO");
                                        alerta.setMessage("POR FAVOR! VERIFIQUE O CRACHÁ DO COLABORADOR DIGITADO.");
                                        alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                // TODO Auto-generated method stub

                                            }
                                        });
                                        alerta.show();
                                    }

                                } else {

                                    alerta = new AlertDialog.Builder(ConfigActivity.this);
                                    alerta.setTitle("ATENÇÃO");
                                    alerta.setMessage("POR FAVOR! DIGITE O NUMERO DA LINHA.");
                                    alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            // TODO Auto-generated method stub

                                        }
                                    });
                                    alerta.show();

                                }

                                break;
                            case 2:

                                if (!editTextFuncConfig.getText().toString().equals("")) {

                                    br.com.usinasantafe.pru.to.tb.estaticas.FuncBean funcBean = new br.com.usinasantafe.pru.to.tb.estaticas.FuncBean();
                                    List funcList = funcBean.get("codFunc", Long.valueOf(editTextFuncConfig.getText().toString()));

                                    if (funcList.size() > 0) {

                                        configBean.deleteAll();
                                        configBean.setCodFunc(Long.valueOf(editTextFuncConfig.getText().toString()));

                                        Intent it = new Intent(ConfigActivity.this, MenuInicialActivity.class);
                                        startActivity(it);
                                        finish();

                                    } else {
                                        alerta = new AlertDialog.Builder(ConfigActivity.this);
                                        alerta.setTitle("ATENÇÃO");
                                        alerta.setMessage("POR FAVOR! VERIFIQUE O CRACHÁ DO COLABORADOR DIGITADO.");
                                        alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                // TODO Auto-generated method stub

                                            }
                                        });
                                        alerta.show();
                                    }
                                } else {
                                    alerta = new AlertDialog.Builder(ConfigActivity.this);
                                    alerta.setTitle("ATENÇÃO");
                                    alerta.setMessage("POR FAVOR! DIGITE O CRACHA DO LÍDER.");
                                    alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            // TODO Auto-generated method stub

                                        }
                                    });
                                    alerta.show();
                                }

                                break;
                            case 3:

                                configBean.deleteAll();
                                configBean.setCodFunc(0L);

                                Intent it = new Intent(ConfigActivity.this, MenuInicialActivity.class);
                                startActivity(it);
                                finish();

                                break;
                        }

                        configBean.setDtUltApontConfig("nulo");
                        configBean.setNumLinha(Long.valueOf(editTextNLinhaConfig.getText().toString()));
                        configBean.insert();

                    }else{

                        alerta = new AlertDialog.Builder(ConfigActivity.this);
                        alerta.setTitle("ATENÇÃO");
                        alerta.setMessage("POR FAVOR! SELECIONE ALGUMA TURMA.");
                        alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // TODO Auto-generated method stub

                            }
                        });
                        alerta.show();

                    }

                }
                else{
                    AlertDialog.Builder alerta = new AlertDialog.Builder(ConfigActivity.this);
                    alerta.setTitle("ATENÇÃO");
                    alerta.setMessage("POR FAVOR! SELECIONE ALGUMA TURMA.");
                    alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // TODO Auto-generated method stub

                        }
                    });
                    alerta.show();
                }

            }
        });

        buttonCancConfig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                Intent it = new Intent(ConfigActivity.this, MenuInicialActivity.class);
                startActivity(it);
                finish();

            }
        });

    }

    public void onBackPressed()  {
    }

}
