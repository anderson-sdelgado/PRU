package br.com.usinasantafe.pru;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import br.com.usinasantafe.pru.model.bean.estaticas.AmostraBean;
import br.com.usinasantafe.pru.model.bean.variaveis.RespFitoBean;

public class QuestaoFitoActivity extends ActivityGeneric {

    private PRUContext pruContext;
    private AmostraBean amostraBean;
    private TextView textViewPadrao;
    private int posQuestao = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questao_fito);

        pruContext = (PRUContext) getApplication();

        textViewPadrao = (TextView) findViewById(R.id.textViewPadrao);
        Button buttonOkQuestao = (Button) findViewById(R.id.buttonOkPadrao);
        Button buttonCancQuestao = (Button) findViewById(R.id.buttonCancPadrao);

        if(pruContext.getVerPosTela() == 5){
            amostraBean = pruContext.getFitoCTR().getAmostraCabec(posQuestao);
            textViewPadrao.setText("CABECALHO\n" + amostraBean.getDescrAmostra());
        }
        else if(pruContext.getVerPosTela() == 6){
            amostraBean = pruContext.getFitoCTR().getAmostraResp(posQuestao);
            textViewPadrao.setText("PONTO " + pruContext.getPosPontoAmostra() + "\n" + amostraBean.getDescrAmostra());
        }
        else if(pruContext.getVerPosTela() == 7){
            RespFitoBean respFitoBean = pruContext.getFitoCTR().getRespFitoBean();
            textViewPadrao.setText("PONTO " + respFitoBean.getPontoRespFito() + "\n" + pruContext.getFitoCTR().getAmostra(respFitoBean.getIdAmostraRespFito()).getDescrAmostra());
            editTextPadrao.setText(String.valueOf(respFitoBean.getValorRespFito()));
        }

        buttonOkQuestao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!editTextPadrao.getText().toString().equals("")) {

                    posQuestao++;
                    if(pruContext.getVerPosTela() == 5){

                        pruContext.getFitoCTR().salvarRespFito(amostraBean, Long.parseLong(editTextPadrao.getText().toString()), pruContext.getPosPontoAmostra());

                        if(pruContext.getFitoCTR().verTermQuestaoCabec(posQuestao)){
                            pruContext.getFitoCTR().fecharRespFitoPonto(pruContext.getPosPontoAmostra());
                            Intent it = new Intent(QuestaoFitoActivity.this, ListaPontosActivity.class);
                            startActivity(it);
                            finish();
                        }
                        else{
                            amostraBean = pruContext.getFitoCTR().getAmostraCabec(posQuestao);
                            textViewPadrao.setText("CABECALHO\n" + amostraBean.getDescrAmostra());
                        }
                    }
                    else if(pruContext.getVerPosTela() == 6){

                        pruContext.getFitoCTR().salvarRespFito(amostraBean, Long.parseLong(editTextPadrao.getText().toString()), pruContext.getPosPontoAmostra());

                        if(pruContext.getFitoCTR().verTermQuestao(posQuestao)){
                            pruContext.getFitoCTR().fecharRespFitoPonto(pruContext.getPosPontoAmostra());
                            Intent it = new Intent(QuestaoFitoActivity.this, ListaPontosActivity.class);
                            startActivity(it);
                            finish();
                        }
                        else{
                            amostraBean = pruContext.getFitoCTR().getAmostraResp(posQuestao);
                            textViewPadrao.setText("PONTO " + pruContext.getPosPontoAmostra() + "\n" + amostraBean.getDescrAmostra());
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
                amostraBean = pruContext.getFitoCTR().getAmostraCabec(posQuestao);
                textViewPadrao.setText("CABECALHO\n" + amostraBean.getDescrAmostra());
            }
        }
        else if(pruContext.getVerPosTela() == 6){
            if(posQuestao == 1){
                Intent it = new Intent(QuestaoFitoActivity.this, ListaPontosActivity.class);
                startActivity(it);
                finish();
            }
            else{
                posQuestao--;
                amostraBean = pruContext.getFitoCTR().getAmostraResp(posQuestao);
                textViewPadrao.setText("PONTO " + pruContext.getPosPontoAmostra() + "\n" + amostraBean.getDescrAmostra());
            }
        }
        else if(pruContext.getVerPosTela() == 7){
            Intent it = new Intent(QuestaoFitoActivity.this, ListaQuestaoFitoActivity.class);
            startActivity(it);
            finish();
        }
    }

}