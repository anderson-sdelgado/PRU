package br.com.usinasantafe.pru;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import br.com.usinasantafe.pru.model.bean.variaveis.AmostraPerdaBean;

public class QuestaoPerdaActivity extends ActivityGeneric {

    private PRUContext pruContext;
    private TextView textViewPadrao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questao_perda);

        pruContext = (PRUContext) getApplication();

        textViewPadrao = (TextView) findViewById(R.id.textViewPadrao);
        Button buttonOkQuestao = (Button) findViewById(R.id.buttonOkPadrao);
        Button buttonCancQuestao = (Button) findViewById(R.id.buttonCancPadrao);

        textViewPadrao.setText("AMOSTRA " + pruContext.getPosPontoAmostra() + "\n" + "TARA");

        buttonOkQuestao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Double valorNum = 0D;
                boolean verTara = false;

                if (!editTextPadrao.getText().toString().equals("")) {

                    String valor = editTextPadrao.getText().toString();
                    valorNum = Double.valueOf(valor.replace(",", "."));

                    if(pruContext.getPosQuestao() > 1){
                        if (pruContext.getPerdaColheitaCTR().getAmostraPerdaBean().getTaraAmostraPerda() < valorNum) {
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
                    }
                    else{
                        verTara = true;
                    }

                } else {
                    if(pruContext.getPosQuestao() == 1) {
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

                if(verTara) {
                    if (pruContext.getPosQuestao() == 1) {
                        pruContext.getPerdaColheitaCTR().setAmostraPerdaBean(new AmostraPerdaBean());
                        pruContext.getPerdaColheitaCTR().getAmostraPerdaBean().setTaraAmostraPerda(valorNum);
                        if (pruContext.getPerdaColheitaCTR().getCabecPerdaAberto().getTipoColheitaCabecPerda() == 1L) {
                            pruContext.setPosQuestao(2);
                        } else {
                            pruContext.getPerdaColheitaCTR().getAmostraPerdaBean().setToleteAmostraPerda(0D);
                            pruContext.setPosQuestao(3);
                        }
                    }
                    else if (pruContext.getPosQuestao() == 2) {
                        pruContext.getPerdaColheitaCTR().getAmostraPerdaBean().setToleteAmostraPerda(valorNum);
                        pruContext.setPosQuestao(3);
                    }
                    else if (pruContext.getPosQuestao() == 3) {
                        pruContext.getPerdaColheitaCTR().getAmostraPerdaBean().setCanaInteiraAmostraPerda(valorNum);
                        pruContext.setPosQuestao(4);
                    }
                    else if (pruContext.getPosQuestao() == 4) {
                        pruContext.getPerdaColheitaCTR().getAmostraPerdaBean().setTocoAmostraPerda(valorNum);
                        pruContext.setPosQuestao(5);
                    }
                    else if (pruContext.getPosQuestao() == 5) {
                        pruContext.getPerdaColheitaCTR().getAmostraPerdaBean().setPedacoAmostraPerda(valorNum);
                        if (pruContext.getPerdaColheitaCTR().getCabecPerdaAberto().getTipoColheitaCabecPerda() == 1L) {
                            pruContext.getPerdaColheitaCTR().getAmostraPerdaBean().setRepiqueAmostraPerda(0D);
                            pruContext.setPosQuestao(7);
                        } else {
                            pruContext.setPosQuestao(6);
                        }
                    }
                    else if (pruContext.getPosQuestao() == 6) {
                        pruContext.getPerdaColheitaCTR().getAmostraPerdaBean().setRepiqueAmostraPerda(valorNum);
                        pruContext.setPosQuestao(7);
                    }
                    else if (pruContext.getPosQuestao() == 7) {
                        pruContext.getPerdaColheitaCTR().getAmostraPerdaBean().setPonteiroAmostraPerda(valorNum);
                        if (pruContext.getPerdaColheitaCTR().getCabecPerdaAberto().getTipoColheitaCabecPerda() == 1L) {
                            pruContext.setPosQuestao(8);
                        } else {
                            pruContext.getPerdaColheitaCTR().getAmostraPerdaBean().setLascasAmostraPerda(0D);
                            finalizarQuestao();
                        }
                    }
                    else if (pruContext.getPosQuestao() == 8) {
                        pruContext.getPerdaColheitaCTR().getAmostraPerdaBean().setLascasAmostraPerda(valorNum);
                        finalizarQuestao();
                    }

                    if(pruContext.getPosQuestao() == 2){
                        textViewPadrao.setText("TOLETE");
                    }
                    else if(pruContext.getPosQuestao() == 3){
                        textViewPadrao.setText("CANA INTEIRA");
                    }
                    else if(pruContext.getPosQuestao() == 4){
                        textViewPadrao.setText("TOCO");
                    }
                    else if(pruContext.getPosQuestao() == 5){
                        textViewPadrao.setText("PEDACO");
                    }
                    else if(pruContext.getPosQuestao() == 6){
                        textViewPadrao.setText("REPIQUE");
                    }
                    else if(pruContext.getPosQuestao() == 7){
                        textViewPadrao.setText("PONTEIRO");
                    }
                    else if(pruContext.getPosQuestao() == 8){
                        textViewPadrao.setText("LASCA");
                    }

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
        Intent it = new Intent(QuestaoPerdaActivity.this, ListaAmostraPerdaActivity.class);
        startActivity(it);
        finish();
    }

    public void finalizarQuestao(){

        pruContext.getPerdaColheitaCTR().getAmostraPerdaBean().setSoqueiraKgAmostraPerda(0D);
        pruContext.getPerdaColheitaCTR().getAmostraPerdaBean().setSoqueiraNumAmostraPerda(0D);

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

                pruContext.getPerdaColheitaCTR().getAmostraPerdaBean().setPedraAmostraPerda(0L);
                pruContext.getPerdaColheitaCTR().getAmostraPerdaBean().setTocoArvoreAmostraPerda(0L);
                pruContext.getPerdaColheitaCTR().getAmostraPerdaBean().setPlantaDaninhasAmostraPerda(0L);
                pruContext.getPerdaColheitaCTR().getAmostraPerdaBean().setFormigueiroAmostraPerda(0L);
                pruContext.getPerdaColheitaCTR().salvarAmostraPerda();

                Intent it = new Intent(QuestaoPerdaActivity.this, ListaAmostraPerdaActivity.class);
                startActivity(it);

            }
        });

        alerta.show();
    }

}