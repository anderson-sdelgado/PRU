package br.com.usinasantafe.pru.view;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pru.BuildConfig;
import br.com.usinasantafe.pru.PRUContext;
import br.com.usinasantafe.pru.R;
import br.com.usinasantafe.pru.model.bean.estaticas.TipoApontBean;
import br.com.usinasantafe.pru.model.bean.estaticas.TurmaBean;
import br.com.usinasantafe.pru.model.bean.variaveis.ConfigBean;

public class ConfigActivity extends ActivityGeneric {

    private ProgressDialog progressBar;
    private AdapterList adapterList;
    private AlertDialog alerta;
    private List<TipoApontBean> tipoApontList;
    private List<TurmaBean> turmaList;
    private TextView textViewTipoConfig;
    private TextView textViewTurmaConfig;
    private TextView textViewFuncConfig;
    private EditText editTextFuncConfig;
    private EditText editTextNroAparelhoConfig;
    private EditText editTextSenhaConfig;
    private PRUContext pruContext;
    private ConfigBean configBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);

        Button buttonAtualBDConfig = findViewById(R.id.buttonAtualizarDados);
        Button buttonSalvarConfig =  findViewById(R.id.buttonSalvarConfig);
        Button buttonCancConfig = findViewById(R.id.buttonCancConfig);
        textViewTipoConfig = findViewById(R.id.textViewTipoConfig);
        textViewTurmaConfig = findViewById(R.id.textViewTurmaConfig);
        textViewFuncConfig = findViewById(R.id.textViewFuncConfig);
        editTextFuncConfig = findViewById(R.id.editTextFuncConfig);
        editTextNroAparelhoConfig = findViewById(R.id.editTextNroAparelhoConfig);
        editTextSenhaConfig = findViewById(R.id.editTextSenhaConfig);

        pruContext = (PRUContext) getApplication();

        configBean = new ConfigBean();

        if(!pruContext.getConfigCTR().hasElemConfig()) {

            textViewFuncConfig.setText("");
            textViewFuncConfig.setEnabled(false);
            editTextFuncConfig.setEnabled(false);
            configBean.setIdTipoConfig(0L);
            configBean.setIdTurmaConfig(0L);

        } else {

            configBean = pruContext.getConfigCTR().getConfig();

            TipoApontBean tipoApontBean = pruContext.getConfigCTR().getTipoApont(configBean.getIdTipoConfig());
            textViewTipoConfig.setText(tipoApontBean.getIdTipo() + " - " + tipoApontBean.getDescrTipo());

            TurmaBean turmaBean = pruContext.getConfigCTR().getTurma(configBean.getIdTurmaConfig());
            textViewTurmaConfig.setText(turmaBean.getCodTurma() + " - " + turmaBean.getDescrTurma());

            editTextNroAparelhoConfig.setText(String.valueOf(configBean.getNroAparelhoConfig()));
            editTextSenhaConfig.setText(configBean.getSenhaConfig());

            switch ((int) configBean.getIdTipoConfig().longValue()) {
                case 1: {
                    textViewFuncConfig.setText("LÍDER:");
                    editTextFuncConfig.setText(String.valueOf(configBean.getMatricFuncConfig()));
                    textViewFuncConfig.setEnabled(true);
                    editTextFuncConfig.setEnabled(true);
                    break;
                }
                case 2: {
                    textViewFuncConfig.setText("COLAB.:");
                    editTextFuncConfig.setText(String.valueOf(configBean.getMatricFuncConfig()));
                    textViewFuncConfig.setEnabled(true);
                    editTextFuncConfig.setEnabled(true);
                    break;
                }
                case 3: {
                    textViewFuncConfig.setText("");
                    editTextFuncConfig.setText("");
                    textViewFuncConfig.setEnabled(false);
                    editTextFuncConfig.setEnabled(false);
                    break;
                }
            }

        }

        textViewTipoConfig.setOnClickListener(v -> {

            if(pruContext.getConfigCTR().hasElementsTipoApont()) {

                tipoApontList = pruContext.getConfigCTR().allTipoApont();
                ArrayList<String> itens = new ArrayList<>();
                for (int i = 0; i < tipoApontList.size(); i++) {
                    TipoApontBean tipoApontBean = (TipoApontBean) tipoApontList.get(i);
                    itens.add(tipoApontBean.getIdTipo() + " - " + tipoApontBean.getDescrTipo());
                }

                adapterList = new AdapterList(ConfigActivity.this, itens);

                AlertDialog.Builder builder = new AlertDialog.Builder(ConfigActivity.this);
                builder.setTitle("TIPO:");
                builder.setSingleChoiceItems(adapterList, 0, (arg0, arg1) -> {

                    editTextFuncConfig.setText("");
                    TipoApontBean tipoApontBean = tipoApontList.get(arg1);
                    textViewTipoConfig.setText(tipoApontBean.getIdTipo() + " - " + tipoApontBean.getDescrTipo());
                    configBean.setIdTipoConfig(tipoApontBean.getIdTipo());

                    switch ((int) tipoApontBean.getIdTipo().longValue()) {
                        case 1: {
                            textViewFuncConfig.setText("LÍDER:");
                            textViewFuncConfig.setEnabled(true);
                            editTextFuncConfig.setEnabled(true);
                            break;
                        }
                        case 2: {
                            textViewFuncConfig.setText("COLAB.:");
                            textViewFuncConfig.setEnabled(true);
                            editTextFuncConfig.setEnabled(true);
                            break;
                        }
                        case 3: {
                            textViewFuncConfig.setText("");
                            textViewFuncConfig.setEnabled(false);
                            editTextFuncConfig.setEnabled(false);
                            break;
                        }
                    }

                    alerta.dismiss();
                });

                alerta = builder.create();
                alerta.show();

            }

        });

        textViewTurmaConfig.setOnClickListener(v -> {

            turmaList = pruContext.getConfigCTR().allTurma();
            if(turmaList.size() > 0) {

                ArrayList<String> itens = new ArrayList<>();

                for (int i = 0; i < turmaList.size(); i++) {
                    TurmaBean turmaBean = turmaList.get(i);
                    itens.add(turmaBean.getCodTurma() + " - " + turmaBean.getDescrTurma());
                }

                adapterList = new AdapterList(ConfigActivity.this, itens);

                AlertDialog.Builder builder = new AlertDialog.Builder(ConfigActivity.this);
                builder.setTitle("TURMA:");
                builder.setSingleChoiceItems(adapterList, 0, (arg0, arg1) -> {

                    TurmaBean turmaBean = turmaList.get(arg1);
                    textViewTurmaConfig.setText(turmaBean.getCodTurma() + " - " + turmaBean.getDescrTurma());
                    configBean.setIdTurmaConfig(turmaBean.getIdTurma());

                    alerta.dismiss();
                });

                alerta = builder.create();
                alerta.show();

            }

        });

        buttonAtualBDConfig.setOnClickListener(v -> {

            if(connectNetwork){

                progressBar = new ProgressDialog(v.getContext());
                progressBar.setCancelable(true);
                progressBar.setMessage("ATUALIZANDO ...");
                progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                progressBar.setProgress(0);
                progressBar.setMax(100);
                progressBar.show();

                pruContext.getConfigCTR().atualTodasTabelas(ConfigActivity.this, progressBar);

            }
            else{
                AlertDialog.Builder alerta = new AlertDialog.Builder(ConfigActivity.this);
                alerta.setTitle("ATENÇÃO");
                alerta.setMessage("FALHA NA CONEXÃO DE DADOS. O CELULAR ESTA SEM SINAL. POR FAVOR, TENTE NOVAMENTE QUANDO O CELULAR ESTIVE COM SINAL.");

                alerta.setPositiveButton("OK", (dialog, which) -> {
                });
                alerta.show();
            }

        });

        buttonSalvarConfig.setOnClickListener(v -> {

            if(!editTextNroAparelhoConfig.getText().toString().equals("")){

                if(!pruContext.getRuricolaCTR().hasTurma()){

                    progressBar = new ProgressDialog(v.getContext());
                    progressBar.setCancelable(true);
                    progressBar.setMessage("Enviando dados do Aparelho...");
                    progressBar.show();

                    pruContext.getConfigCTR().verToken(BuildConfig.VERSION_NAME, Long.valueOf(editTextNroAparelhoConfig.getText().toString()), ConfigActivity.this, progressBar);

                } else {

                    if(configBean.getIdTurmaConfig() > 0) {

                        AlertDialog.Builder alerta;

                        if(!editTextNroAparelhoConfig.getText().toString().equals("")
                                && !editTextSenhaConfig.getText().toString().equals("")) {

                            boolean ver = true;

                            switch ((int) configBean.getIdTipoConfig().longValue()) {
                                case 0: {
                                    ver = false;
                                    alerta = new AlertDialog.Builder(ConfigActivity.this);
                                    alerta.setTitle("ATENÇÃO");
                                    alerta.setMessage("POR FAVOR! SELECIONE ALGUM TIPO.");
                                    alerta.setPositiveButton("OK", (dialog, which) -> {
                                    });
                                    alerta.show();
                                    break;
                                }
                                case 1: {
                                    if (!editTextFuncConfig.getText().toString().equals("")) {
                                        if (pruContext.getConfigCTR().verLider(Long.valueOf(editTextFuncConfig.getText().toString()))) {
                                            configBean.setMatricFuncConfig(Long.valueOf(editTextFuncConfig.getText().toString()));
                                        } else {
                                            ver = false;
                                            alerta = new AlertDialog.Builder(ConfigActivity.this);
                                            alerta.setTitle("ATENÇÃO");
                                            alerta.setMessage("POR FAVOR! VERIFIQUE O CRACHÁ DO COLABORADOR DIGITADO.");
                                            alerta.setPositiveButton("OK", (dialog, which) -> {
                                            });
                                            alerta.show();
                                        }

                                    } else {
                                        ver = false;
                                        alerta = new AlertDialog.Builder(ConfigActivity.this);
                                        alerta.setTitle("ATENÇÃO");
                                        alerta.setMessage("POR FAVOR! DIGITE O NUMERO DA LINHA.");
                                        alerta.setPositiveButton("OK", (dialog, which) -> {
                                        });
                                        alerta.show();
                                    }
                                    break;
                                }
                                case 2: {
                                    if (!editTextFuncConfig.getText().toString().equals("")) {
                                        if (pruContext.getConfigCTR().verFunc(Long.valueOf(editTextFuncConfig.getText().toString()))) {
                                            configBean.setMatricFuncConfig(Long.valueOf(editTextFuncConfig.getText().toString()));
                                        } else {
                                            ver = false;
                                            alerta = new AlertDialog.Builder(ConfigActivity.this);
                                            alerta.setTitle("ATENÇÃO");
                                            alerta.setMessage("POR FAVOR! VERIFIQUE O CRACHÁ DO COLABORADOR DIGITADO.");
                                            alerta.setPositiveButton("OK", (dialog, which) -> {
                                            });
                                            alerta.show();
                                        }
                                    } else {
                                        ver = false;
                                        alerta = new AlertDialog.Builder(ConfigActivity.this);
                                        alerta.setTitle("ATENÇÃO");
                                        alerta.setMessage("POR FAVOR! DIGITE O CRACHA DO LÍDER.");
                                        alerta.setPositiveButton("OK", (dialog, which) -> {
                                        });
                                        alerta.show();
                                    }
                                    break;
                                }
                                case 3: {
                                    configBean.setMatricFuncConfig(0L);
                                    break;
                                }
                            }

                            if(ver){

                                pruContext.getConfigCTR().salvarConfig(configBean);

                                Intent it = new Intent(ConfigActivity.this, TelaInicialActivity.class);
                                startActivity(it);
                                finish();

                            }

                        } else {

                            alerta = new AlertDialog.Builder(ConfigActivity.this);
                            alerta.setTitle("ATENÇÃO");
                            alerta.setMessage("POR FAVOR! DIGITE O NUMERO DA LINHA DO TELEFONE E A SENHA.");
                            alerta.setPositiveButton("OK", (dialog, which) -> {
                            });
                            alerta.show();

                        }

                    } else {
                        AlertDialog.Builder alerta = new AlertDialog.Builder(ConfigActivity.this);
                        alerta.setTitle("ATENÇÃO");
                        alerta.setMessage("POR FAVOR! SELECIONE ALGUMA TURMA.");
                        alerta.setPositiveButton("OK", (dialog, which) -> {
                        });
                        alerta.show();
                    }

                }

            }

        });

        buttonCancConfig.setOnClickListener(v -> {
            Intent it = new Intent(ConfigActivity.this, TelaInicialActivity.class);
            startActivity(it);
            finish();
        });

    }

    public void onBackPressed()  {
    }

}
