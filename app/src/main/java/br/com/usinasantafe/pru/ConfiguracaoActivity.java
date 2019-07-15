package br.com.usinasantafe.pru;

import android.app.Activity;
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

import br.com.usinasantafe.pru.bo.ConexaoWeb;
import br.com.usinasantafe.pru.bo.ManipDadosReceb;
import br.com.usinasantafe.pru.to.tb.estaticas.FuncTO;
import br.com.usinasantafe.pru.to.tb.estaticas.LiderTO;
import br.com.usinasantafe.pru.to.tb.estaticas.TipoApontamentoTO;
import br.com.usinasantafe.pru.to.tb.estaticas.TurmaTO;
import br.com.usinasantafe.pru.to.tb.variaveis.ConfiguracaoTO;

public class ConfiguracaoActivity extends ActivityGeneric {

    private ProgressDialog progressBar;
    private AdapterList adapterList;
    private AlertDialog alerta;
    private List tipoList;
    private List turmaList;
    private TextView textViewTipoConfig;
    private TextView textViewTurmaConfig;
    private TextView textViewFuncConfig;
    private ConfiguracaoTO configuracaoTO;
    private EditText editTextFuncConfig;
    private EditText editTextNLinhaConfig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracao);

        Button buttonAtualBDConfig = (Button) findViewById(R.id.buttonAtualizarDados);
        Button buttonSalvarConfig =  (Button) findViewById(R.id.buttonSalvarConfig);
        Button buttonCancConfig = (Button) findViewById(R.id.buttonCancConfig);
        textViewTipoConfig = (TextView) findViewById(R.id.textViewTipoConfig);
        textViewTurmaConfig = (TextView) findViewById(R.id.textViewTurmaConfig);
        textViewFuncConfig = (TextView) findViewById(R.id.textViewFuncConfig);
        editTextFuncConfig = (EditText) findViewById(R.id.editTextFuncConfig);
        editTextNLinhaConfig = (EditText) findViewById(R.id.editTextNLinhaConfig);

        configuracaoTO = new ConfiguracaoTO();

        if(!configuracaoTO.hasElements()) {
            textViewFuncConfig.setText("");
            textViewFuncConfig.setEnabled(false);
            editTextFuncConfig.setEnabled(false);
            configuracaoTO.setIdTipo(0L);
            configuracaoTO.setIdTurma(0L);
        }
        else{

            List configList = configuracaoTO.all();
            configuracaoTO = (ConfiguracaoTO) configList.get(0);

            TipoApontamentoTO tipoApontamentoTO = new TipoApontamentoTO();
            List tipoList = tipoApontamentoTO.get("idTipo", configuracaoTO.getIdTipo());
            tipoApontamentoTO = (TipoApontamentoTO) tipoList.get(0);
            textViewTipoConfig.setText(tipoApontamentoTO.getIdTipo() + " - " + tipoApontamentoTO.getDescrTipo());

            TurmaTO turmaTO = new TurmaTO();
            List turmaList = turmaTO.get("idTurma", configuracaoTO.getIdTurma());
            turmaTO = (TurmaTO) turmaList.get(0);
            textViewTurmaConfig.setText(turmaTO.getCodTurma() + " - " + turmaTO.getDescrTurma());

            editTextNLinhaConfig.setText(String.valueOf(configuracaoTO.getNumLinha()));

            switch ((int) configuracaoTO.getIdTipo().longValue()) {
                case 1:
                    textViewFuncConfig.setText("LÍDER:");
                    editTextFuncConfig.setText(String.valueOf(configuracaoTO.getCodFunc()));
                    textViewFuncConfig.setEnabled(true);
                    editTextFuncConfig.setEnabled(true);
                    break;
                case 2:
                    textViewFuncConfig.setText("COLAB.:");
                    editTextFuncConfig.setText(String.valueOf(configuracaoTO.getCodFunc()));
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

                TipoApontamentoTO tipoApontamentoTO = new TipoApontamentoTO();
                tipoList = tipoApontamentoTO.all();

                if(tipoList.size() > 0) {

                    ArrayList<String> itens = new ArrayList<String>();
                    for (int i = 0; i < tipoList.size(); i++) {
                        tipoApontamentoTO = (TipoApontamentoTO) tipoList.get(i);
                        itens.add(tipoApontamentoTO.getIdTipo() + " - " + tipoApontamentoTO.getDescrTipo());
                    }

                    adapterList = new AdapterList(ConfiguracaoActivity.this, itens);

                    AlertDialog.Builder builder = new AlertDialog.Builder(ConfiguracaoActivity.this);
                    builder.setTitle("TIPO:");
                    builder.setSingleChoiceItems(adapterList, 0, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface arg0, int arg1) {

                            editTextFuncConfig.setText("");
                            TipoApontamentoTO tipoApontamentoTO = (TipoApontamentoTO) tipoList.get(arg1);
                            textViewTipoConfig.setText(tipoApontamentoTO.getIdTipo() + " - " + tipoApontamentoTO.getDescrTipo());
                            configuracaoTO.setIdTipo(tipoApontamentoTO.getIdTipo());

                            switch ((int) tipoApontamentoTO.getIdTipo().longValue()) {
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

                TurmaTO turmaTO = new TurmaTO();
                turmaList = turmaTO.orderBy("codTurma", true);

                if(turmaList.size() > 0) {

                    ArrayList<String> itens = new ArrayList<String>();

                    for (int i = 0; i < turmaList.size(); i++) {
                        turmaTO = (TurmaTO) turmaList.get(i);
                        itens.add(turmaTO.getCodTurma() + " - " + turmaTO.getDescrTurma());
                    }

                    adapterList = new AdapterList(ConfiguracaoActivity.this, itens);

                    AlertDialog.Builder builder = new AlertDialog.Builder(ConfiguracaoActivity.this);
                    builder.setTitle("TURMA:");
                    builder.setSingleChoiceItems(adapterList, 0, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface arg0, int arg1) {

                            TurmaTO turmaTO = (TurmaTO) turmaList.get(arg1);
                            textViewTurmaConfig.setText(turmaTO.getCodTurma() + " - " + turmaTO.getDescrTurma());
                            configuracaoTO.setIdTurma(turmaTO.getIdTurma());

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

                if(conexaoWeb.verificaConexao(ConfiguracaoActivity.this)){

                    progressBar = new ProgressDialog(v.getContext());
                    progressBar.setCancelable(true);
                    progressBar.setMessage("ATUALIZANDO ...");
                    progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                    progressBar.setProgress(0);
                    progressBar.setMax(100);
                    progressBar.show();

                    ManipDadosReceb.getInstance().atualizarBD(progressBar);
                    ManipDadosReceb.getInstance().setContext(ConfiguracaoActivity.this);

                }
                else{
                    AlertDialog.Builder alerta = new AlertDialog.Builder(ConfiguracaoActivity.this);
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

                if(configuracaoTO.getIdTurma() > 0) {

                    AlertDialog.Builder alerta;

                    if(!editTextNLinhaConfig.getText().toString().equals("")) {

                        switch ((int) configuracaoTO.getIdTipo().longValue()) {
                            case 0:
                                alerta = new AlertDialog.Builder(ConfiguracaoActivity.this);
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

                                    configuracaoTO.deleteAll();

                                    LiderTO liderTO = new LiderTO();
                                    List liderList = liderTO.get("codLider", Long.valueOf(editTextFuncConfig.getText().toString()));

                                    if (liderList.size() > 0){

                                        configuracaoTO.deleteAll();
                                        configuracaoTO.setCodFunc(Long.valueOf(editTextFuncConfig.getText().toString()));

                                        Intent it = new Intent(ConfiguracaoActivity.this, PrincipalActivity.class);
                                        startActivity(it);
                                        finish();

                                    } else {
                                        alerta = new AlertDialog.Builder(ConfiguracaoActivity.this);
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

                                    alerta = new AlertDialog.Builder(ConfiguracaoActivity.this);
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

                                    FuncTO funcTO = new FuncTO();
                                    List funcList = funcTO.get("codFunc", Long.valueOf(editTextFuncConfig.getText().toString()));

                                    if (funcList.size() > 0) {

                                        configuracaoTO.deleteAll();
                                        configuracaoTO.setCodFunc(Long.valueOf(editTextFuncConfig.getText().toString()));

                                        Intent it = new Intent(ConfiguracaoActivity.this, PrincipalActivity.class);
                                        startActivity(it);
                                        finish();

                                    } else {
                                        alerta = new AlertDialog.Builder(ConfiguracaoActivity.this);
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
                                    alerta = new AlertDialog.Builder(ConfiguracaoActivity.this);
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

                                configuracaoTO.deleteAll();
                                configuracaoTO.setCodFunc(0L);

                                Intent it = new Intent(ConfiguracaoActivity.this, PrincipalActivity.class);
                                startActivity(it);
                                finish();

                                break;
                        }

                        configuracaoTO.setDtUltApontConfig("nulo");
                        configuracaoTO.setNumLinha(Long.valueOf(editTextNLinhaConfig.getText().toString()));
                        configuracaoTO.insert();

                    }else{

                        alerta = new AlertDialog.Builder(ConfiguracaoActivity.this);
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
                    AlertDialog.Builder alerta = new AlertDialog.Builder(ConfiguracaoActivity.this);
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

                Intent it = new Intent(ConfiguracaoActivity.this, PrincipalActivity.class);
                startActivity(it);
                finish();

            }
        });

    }

    public void onBackPressed()  {
    }

}
