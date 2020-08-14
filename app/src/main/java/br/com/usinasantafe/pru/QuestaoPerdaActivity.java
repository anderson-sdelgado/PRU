package br.com.usinasantafe.pru;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import br.com.usinasantafe.pru.model.bean.variaveis.AmostraPerdaBean;

public class QuestaoPerdaActivity extends ActivityGeneric {

    private PRUContext pruContext;
    private TextView textViewPadrao;
    private Long ponto;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questao_perda);

        pruContext = (PRUContext) getApplication();

        editText = (EditText) findViewById(R.id.editTextPadrao);
        textViewPadrao = (TextView) findViewById(R.id.textViewPadrao);
        Button buttonOkQuestao = (Button) findViewById(R.id.buttonOkPadrao);
        Button buttonCancQuestao = (Button) findViewById(R.id.buttonCancPadrao);

        if(pruContext.getVerPosTela() == 9) {
            ponto = (pruContext.getConfigCTR().getConfig().getPontoAmostraConfig() + 1);
            if(pruContext.getPosQuestao() == 1){
                textViewPadrao.setText("AMOSTRA " + ponto + "\n" + "TARA");
            }
            else if(pruContext.getPosQuestao() == 7){
                textViewPadrao.setText("AMOSTRA " + ponto + "\n" + "PONTEIRO");
            }
            else if(pruContext.getPosQuestao() == 8){
                textViewPadrao.setText("AMOSTRA " + ponto + "\n" + "LASCA");
            }
        }
        else if(pruContext.getVerPosTela() == 10) {
            AmostraPerdaBean amostraPerdaBean = pruContext.getPerdaCTR().getAmostraPerda(pruContext.getPosPontoAmostra());
            if (pruContext.getPosQuestao() == 1) {
                textViewPadrao.setText("AMOSTRA " + pruContext.getPosPontoAmostra() + "\n" + "TARA");
                editText.setText(String.valueOf(amostraPerdaBean.getTaraAmostraPerda()));
            } else if (pruContext.getPosQuestao() == 2) {
                textViewPadrao.setText("AMOSTRA " + pruContext.getPosPontoAmostra() + "\n" + "TOLETE");
                editText.setText(String.valueOf(amostraPerdaBean.getToleteAmostraPerda()));
            } else if (pruContext.getPosQuestao() == 3) {
                textViewPadrao.setText("AMOSTRA " + pruContext.getPosPontoAmostra() + "\n" + "CANA INTEIRA");
                editText.setText(String.valueOf(amostraPerdaBean.getCanaInteiraAmostraPerda()));
            } else if (pruContext.getPosQuestao() == 4) {
                textViewPadrao.setText("AMOSTRA " + pruContext.getPosPontoAmostra() + "\n" + "TOCO");
                editText.setText(String.valueOf(amostraPerdaBean.getTocoAmostraPerda()));
            } else if (pruContext.getPosQuestao() == 5) {
                textViewPadrao.setText("AMOSTRA " + pruContext.getPosPontoAmostra() + "\n" + "PEDACO");
                editText.setText(String.valueOf(amostraPerdaBean.getPedacoAmostraPerda()));
            } else if (pruContext.getPosQuestao() == 6) {
                textViewPadrao.setText("AMOSTRA " + pruContext.getPosPontoAmostra() + "\n" + "REPIQUE");
                editText.setText(String.valueOf(amostraPerdaBean.getRepiqueAmostraPerda()));
            } else if (pruContext.getPosQuestao() == 7) {
                textViewPadrao.setText("AMOSTRA " + pruContext.getPosPontoAmostra() + "\n" + "PONTEIRO");
                editText.setText(String.valueOf(amostraPerdaBean.getPonteiroAmostraPerda()));
            } else if (pruContext.getPosQuestao() == 8) {
                textViewPadrao.setText("AMOSTRA " + pruContext.getPosPontoAmostra() + "\n" + "LASCA");
                editText.setText(String.valueOf(amostraPerdaBean.getLascasAmostraPerda()));
            }
        }

        buttonOkQuestao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (pruContext.getVerPosTela() == 9) {

                    Double valorNum = 0D;
                    boolean verTara = false;

                    if (!editTextPadrao.getText().toString().equals("")) {

                        String valor = editTextPadrao.getText().toString();
                        valorNum = Double.valueOf(valor.replace(",", "."));

                        if (pruContext.getPosQuestao() > 1) {
                            if (pruContext.getPerdaCTR().getAmostraPerdaBean().getTaraAmostraPerda() < valorNum) {
                                verTara = true;
                            } else {
                                AlertDialog.Builder alerta = new AlertDialog.Builder(QuestaoPerdaActivity.this);
                                alerta.setTitle("ATENÇÃO");
                                alerta.setMessage("O PESO ESTA ABAIXO DO PESO TARA. POR FAVOR DIGITE NOVAMENTE O PESO.");

                                alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        editTextPadrao.setText("");
                                    }
                                });
                                alerta.show();
                            }
                        } else {
                            verTara = true;
                        }

                    } else {
                        if (pruContext.getPosQuestao() == 1) {
                            AlertDialog.Builder alerta = new AlertDialog.Builder(QuestaoPerdaActivity.this);
                            alerta.setTitle("ATENÇÃO");
                            alerta.setMessage("POR FAVOR, INSIRA O PESO TARA.");

                            alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    editTextPadrao.setText("");
                                }
                            });
                            alerta.show();
                        }
                    }

                    if (verTara) {
                        if (pruContext.getPosQuestao() == 1) {
                            pruContext.getPerdaCTR().setAmostraPerdaBean(new AmostraPerdaBean());
                            pruContext.getPerdaCTR().getAmostraPerdaBean().setTaraAmostraPerda(valorNum);
                            if (pruContext.getPerdaCTR().getCabecPerdaAberto().getTipoColheitaCabecPerda() == 1L) {
                                pruContext.setPosQuestao(2);
                            } else {
                                pruContext.getPerdaCTR().getAmostraPerdaBean().setToleteAmostraPerda(0D);
                                pruContext.setPosQuestao(3);
                            }
                        } else if (pruContext.getPosQuestao() == 2) {
                            pruContext.getPerdaCTR().getAmostraPerdaBean().setToleteAmostraPerda(valorNum);
                            pruContext.setPosQuestao(3);
                        } else if (pruContext.getPosQuestao() == 3) {
                            pruContext.getPerdaCTR().getAmostraPerdaBean().setCanaInteiraAmostraPerda(valorNum);
                            pruContext.setPosQuestao(4);
                        } else if (pruContext.getPosQuestao() == 4) {
                            pruContext.getPerdaCTR().getAmostraPerdaBean().setTocoAmostraPerda(valorNum);
                            pruContext.setPosQuestao(5);
                        } else if (pruContext.getPosQuestao() == 5) {
                            pruContext.getPerdaCTR().getAmostraPerdaBean().setPedacoAmostraPerda(valorNum);
                            if (pruContext.getPerdaCTR().getCabecPerdaAberto().getTipoColheitaCabecPerda() == 1L) {
                                pruContext.getPerdaCTR().getAmostraPerdaBean().setRepiqueAmostraPerda(0D);
                                pruContext.setPosQuestao(7);
                            } else {
                                pruContext.setPosQuestao(6);
                            }
                        } else if (pruContext.getPosQuestao() == 6) {
                            pruContext.getPerdaCTR().getAmostraPerdaBean().setRepiqueAmostraPerda(valorNum);
                            pruContext.setPosQuestao(7);
                        } else if (pruContext.getPosQuestao() == 7) {
                            pruContext.getPerdaCTR().getAmostraPerdaBean().setPonteiroAmostraPerda(valorNum);
                            if (pruContext.getPerdaCTR().getCabecPerdaAberto().getTipoColheitaCabecPerda() == 1L) {
                                pruContext.setPosQuestao(8);
                            } else {
                                pruContext.getPerdaCTR().getAmostraPerdaBean().setLascasAmostraPerda(0D);
                                finalizarQuestao();
                            }
                        } else if (pruContext.getPosQuestao() == 8) {
                            pruContext.setPosQuestao(9);
                            pruContext.getPerdaCTR().getAmostraPerdaBean().setLascasAmostraPerda(valorNum);
                            finalizarQuestao();
                        }

                        if (pruContext.getPosQuestao() == 2) {
                            textViewPadrao.setText("AMOSTRA " + ponto + "\n" + "TOLETE");
                            editTextPadrao.setText("");
                        } else if (pruContext.getPosQuestao() == 3) {
                            textViewPadrao.setText("AMOSTRA " + ponto + "\n" + "CANA INTEIRA");
                            editTextPadrao.setText("");
                        } else if (pruContext.getPosQuestao() == 4) {
                            textViewPadrao.setText("AMOSTRA " + ponto + "\n" + "TOCO");
                            editTextPadrao.setText("");
                        } else if (pruContext.getPosQuestao() == 5) {
                            textViewPadrao.setText("AMOSTRA " + ponto + "\n" + "PEDACO");
                            editTextPadrao.setText("");
                        } else if (pruContext.getPosQuestao() == 6) {
                            textViewPadrao.setText("AMOSTRA " + ponto + "\n" + "REPIQUE");
                            editTextPadrao.setText("");
                        } else if (pruContext.getPosQuestao() == 7) {
                            textViewPadrao.setText("AMOSTRA " + ponto + "\n" + "PONTEIRO");
                            editTextPadrao.setText("");
                        } else if (pruContext.getPosQuestao() == 8) {
                            textViewPadrao.setText("AMOSTRA " + ponto + "\n" + "LASCA");
                            editTextPadrao.setText("");
                        }
                    }
                }
                else if(pruContext.getVerPosTela() == 10) {
                    Double valorNum = 0D;
                    if (!editTextPadrao.getText().toString().equals("")) {
                        String valor = editTextPadrao.getText().toString();
                        valorNum = Double.valueOf(valor.replace(",", "."));
                    } else {
                        if (pruContext.getPosQuestao() == 1) {
                            AlertDialog.Builder alerta = new AlertDialog.Builder(QuestaoPerdaActivity.this);
                            alerta.setTitle("ATENÇÃO");
                            alerta.setMessage("POR FAVOR, INSIRA O PESO TARA.");

                            alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    editTextPadrao.setText("");
                                }
                            });
                            alerta.show();
                        }
                        else{
                            valorNum = 0D;
                        }
                    }
                    pruContext.getPerdaCTR().salvarAmostraPerda(valorNum, pruContext.getPosQuestao(), pruContext.getPosPontoAmostra());
                    Intent it = new Intent(QuestaoPerdaActivity.this, ListaQuestaoPerdaActivity.class);
                    startActivity(it);
                    finish();
                }
            }
        });

        buttonCancQuestao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editTextPadrao.getText().toString().length() > 0){
                    editTextPadrao.setText(editTextPadrao.getText().toString().substring(0, editTextPadrao.getText().toString().length() - 1));
                }
            }
        });

    }

    public void onBackPressed()  {
        if (pruContext.getVerPosTela() == 10) {
            if(pruContext.getPosQuestao() == 1){
                Intent it = new Intent(QuestaoPerdaActivity.this, ListaAmostraPerdaActivity.class);
                startActivity(it);
                finish();
            }
            else{

                if (pruContext.getPosQuestao() == 2) {
                    pruContext.setPosQuestao(1);
                    textViewPadrao.setText("TARA");

                } else if (pruContext.getPosQuestao() == 3) {
                    if (pruContext.getPerdaCTR().getCabecPerdaAberto().getTipoColheitaCabecPerda() == 1L) {
                        pruContext.setPosQuestao(2);
                        textViewPadrao.setText("TOLETE");
                    } else {
                        pruContext.setPosQuestao(1);
                        textViewPadrao.setText("TARA");
                    }
                } else if (pruContext.getPosQuestao() == 4) {
                    pruContext.setPosQuestao(3);
                    textViewPadrao.setText("CANA INTEIRA");
                } else if (pruContext.getPosQuestao() == 5) {
                    pruContext.setPosQuestao(4);
                    textViewPadrao.setText("TOCO");
                } else if (pruContext.getPosQuestao() == 6) {
                    pruContext.setPosQuestao(5);
                    textViewPadrao.setText("PEDACO");
                } else if (pruContext.getPosQuestao() == 7) {
                    if (pruContext.getPerdaCTR().getCabecPerdaAberto().getTipoColheitaCabecPerda() == 1L) {
                        pruContext.setPosQuestao(5);
                        textViewPadrao.setText("PEDACO");
                    } else {
                        pruContext.setPosQuestao(6);
                        textViewPadrao.setText("REPIQUE");
                    }
                } else if (pruContext.getPosQuestao() == 8) {
                    pruContext.setPosQuestao(7);
                    textViewPadrao.setText("PONTEIRO");
                }

            }

        }
    }

    public void finalizarQuestao(){

        AlertDialog.Builder alerta = new AlertDialog.Builder(QuestaoPerdaActivity.this);
        alerta.setTitle("ATENÇÃO");
        alerta.setMessage("DESEJA INSERIR OBSERVAÇÃO NA AMOSTRA?");

        alerta.setPositiveButton("SIM", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Intent it = new Intent(QuestaoPerdaActivity.this, ListaObsActivity.class);
                startActivity(it);
                finish();

            }
        });

        alerta.setNegativeButton("NÃO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                pruContext.getPerdaCTR().getAmostraPerdaBean().setPedraAmostraPerda(0L);
                pruContext.getPerdaCTR().getAmostraPerdaBean().setTocoArvoreAmostraPerda(0L);
                pruContext.getPerdaCTR().getAmostraPerdaBean().setPlantaDaninhasAmostraPerda(0L);
                pruContext.getPerdaCTR().getAmostraPerdaBean().setFormigueiroAmostraPerda(0L);
                pruContext.getPerdaCTR().salvarAmostraPerda(ponto);

                Intent it = new Intent(QuestaoPerdaActivity.this, ListaAmostraPerdaActivity.class);
                startActivity(it);
                finish();

            }
        });

        alerta.show();
    }

}