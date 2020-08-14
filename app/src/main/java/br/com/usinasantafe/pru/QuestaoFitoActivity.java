package br.com.usinasantafe.pru;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import br.com.usinasantafe.pru.model.bean.estaticas.AmostraFitoBean;
import br.com.usinasantafe.pru.model.bean.variaveis.RespFitoBean;

public class QuestaoFitoActivity extends ActivityGeneric {

    private PRUContext pruContext;
    private AmostraFitoBean amostraFitoBean;
    private TextView textViewPadrao;
    private int posQuestao = 1;
    private Long ponto;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questao_fito);

        pruContext = (PRUContext) getApplication();

        editText = (EditText) findViewById(R.id.editTextPadrao);
        textViewPadrao = (TextView) findViewById(R.id.textViewPadrao);
        Button buttonOkQuestao = (Button) findViewById(R.id.buttonOkPadrao);
        Button buttonCancQuestao = (Button) findViewById(R.id.buttonCancPadrao);

        if(pruContext.getVerPosTela() == 5){
            amostraFitoBean = pruContext.getFitoCTR().getAmostraCabecResp(posQuestao);
            ponto = 0L;
            textViewPadrao.setText("CABECALHO\n" + amostraFitoBean.getDescrAmostra());
        }
        else if(pruContext.getVerPosTela() == 6){
            amostraFitoBean = pruContext.getFitoCTR().getAmostraResp(posQuestao);
            ponto = (pruContext.getConfigCTR().getConfig().getPontoAmostraConfig() + 1);
            textViewPadrao.setText("PONTO " + ponto + "\n" + amostraFitoBean.getDescrAmostra());
        }
        else if(pruContext.getVerPosTela() == 7){
            RespFitoBean respFitoBean = pruContext.getFitoCTR().getRespFitoBean();
            textViewPadrao.setText("PONTO " + respFitoBean.getPontoRespFito() + "\n" + pruContext.getFitoCTR().getAmostra(respFitoBean.getIdAmostraRespFito()).getDescrAmostra());
            editText.setText(String.valueOf(respFitoBean.getValorRespFito()));
        }

        buttonOkQuestao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!editTextPadrao.getText().toString().equals("")) {

                    if(pruContext.getVerPosTela() == 5){

                        pruContext.getFitoCTR().salvarRespFito(amostraFitoBean, Long.parseLong(editTextPadrao.getText().toString()), ponto);

                        if(pruContext.getFitoCTR().verTermQuestaoCabec(posQuestao)){
                            pruContext.getFitoCTR().fecharRespFitoPonto(ponto);
                            Intent it = new Intent(QuestaoFitoActivity.this, ListaPontosFitoActivity.class);
                            startActivity(it);
                            finish();
                        }
                        else{
                            posQuestao++;
                            amostraFitoBean = pruContext.getFitoCTR().getAmostraCabecResp(posQuestao);
                            textViewPadrao.setText("CABECALHO\n" + amostraFitoBean.getDescrAmostra());
                        }
                    }
                    else if(pruContext.getVerPosTela() == 6){

                        pruContext.getFitoCTR().salvarRespFito(amostraFitoBean, Long.parseLong(editTextPadrao.getText().toString()), ponto);

                        if(pruContext.getFitoCTR().verTermQuestao(posQuestao)){
                            pruContext.getFitoCTR().fecharRespFitoPonto(ponto);
                            Intent it = new Intent(QuestaoFitoActivity.this, ListaPontosFitoActivity.class);
                            startActivity(it);
                            finish();
                        }
                        else{
                            posQuestao++;
                            amostraFitoBean = pruContext.getFitoCTR().getAmostraResp(posQuestao);
                            textViewPadrao.setText("PONTO " + ponto + "\n" + amostraFitoBean.getDescrAmostra());
                            editTextPadrao.setText("");
                        }

                    }
                    else if(pruContext.getVerPosTela() == 7){
                        pruContext.getFitoCTR().atualRespFito(Long.parseLong(editTextPadrao.getText().toString()));
                        Intent it = new Intent(QuestaoFitoActivity.this, ListaQuestaoFitoActivity.class);
                        startActivity(it);
                        finish();
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
        if(pruContext.getVerPosTela() == 5){
            if(posQuestao > 1){
                posQuestao--;
                amostraFitoBean = pruContext.getFitoCTR().getAmostraCabecResp(posQuestao);
                textViewPadrao.setText("CABECALHO\n" + amostraFitoBean.getDescrAmostra());
            }
        }
        else if(pruContext.getVerPosTela() == 6){
            if(posQuestao == 1){
                Intent it = new Intent(QuestaoFitoActivity.this, ListaPontosFitoActivity.class);
                startActivity(it);
                finish();
            }
            else{
                posQuestao--;
                amostraFitoBean = pruContext.getFitoCTR().getAmostraResp(posQuestao);
                textViewPadrao.setText("PONTO " + ponto + "\n" + amostraFitoBean.getDescrAmostra());
            }
        }
        else if(pruContext.getVerPosTela() == 7){
            Intent it = new Intent(QuestaoFitoActivity.this, ListaQuestaoFitoActivity.class);
            startActivity(it);
            finish();
        }
    }

}