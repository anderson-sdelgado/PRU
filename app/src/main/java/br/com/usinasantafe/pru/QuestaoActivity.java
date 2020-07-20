package br.com.usinasantafe.pru;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import br.com.usinasantafe.pru.model.bean.estaticas.AmostraBean;

public class QuestaoActivity extends ActivityGeneric {

    private PRUContext pruContext;
    private AmostraBean amostraBean;
    private Long ponto = 0L;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questao);

        pruContext = (PRUContext) getApplication();

        TextView textViewPadrao = (TextView) findViewById(R.id.textViewPadrao);
        Button buttonOkQuestao = (Button) findViewById(R.id.buttonOkPadrao);
        Button buttonCancQuestao = (Button) findViewById(R.id.buttonCancPadrao);

        String descrAmostra = "";

        if(pruContext.getVerPosTela() == 5){
            amostraBean = pruContext.getFitoCTR().getAmostraCabec();
            textViewPadrao.setText(amostraBean.getDescrAmostra());
        }
        else if(pruContext.getVerPosTela() == 6){
            ponto = pruContext.getPosPonto() + 1;
            amostraBean = pruContext.getFitoCTR().getAmostraResp(ponto);
            textViewPadrao.setText(amostraBean.getDescrAmostra());
        }
        else if(pruContext.getVerPosTela() == 7){
            ponto = pruContext.getPosPonto() + 1;
            amostraBean = pruContext.getFitoCTR().getAmostraResp(ponto);
            textViewPadrao.setText(pruContext.getFitoCTR().getAmostra(pruContext.getFitoCTR().getRespFitoBean().getIdAmostraRespFito()).getDescrAmostra());
            editTextPadrao.setText(String.valueOf(pruContext.getFitoCTR().getRespFitoBean().getValorRespFito()));
        }

        buttonOkQuestao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                if (!editTextPadrao.getText().toString().equals("")) {

                    if(pruContext.getVerPosTela() == 5){

                        pruContext.getFitoCTR().salvarRespFito(amostraBean, Long.parseLong(editTextPadrao.getText().toString()));

                        if(pruContext.getFitoCTR().verTermQuestaoCabec()){
                            pruContext.getFitoCTR().fecharRespFitoPonto();
                            Intent it = new Intent(QuestaoActivity.this, ListaPontosActivity.class);
                            startActivity(it);
                            finish();
                        }
                        else{
                            Intent it = new Intent(QuestaoActivity.this, QuestaoActivity.class);
                            startActivity(it);
                            finish();
                        }
                    }
                    else if(pruContext.getVerPosTela() == 6){

                        pruContext.getFitoCTR().salvarRespFito(amostraBean, Long.parseLong(editTextPadrao.getText().toString()), ponto);

                        if(pruContext.getFitoCTR().verTermQuestao(ponto)){
                            pruContext.getFitoCTR().fecharRespFitoPonto();
                            pruContext.getConfigCTR().setPontoConfig(ponto);
                            Intent it = new Intent(QuestaoActivity.this, ListaPontosActivity.class);
                            startActivity(it);
                            finish();
                        }
                        else{
                            Intent it = new Intent(QuestaoActivity.this, QuestaoActivity.class);
                            startActivity(it);
                            finish();
                        }

                    }
                    else if(pruContext.getVerPosTela() == 7){
                        pruContext.getFitoCTR().atualRespFito(Long.parseLong(editTextPadrao.getText().toString()));
                    }

                }

            }
        });

        buttonCancQuestao.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                if(editTextPadrao.getText().toString().length() > 0){
                    editTextPadrao.setText(editTextPadrao.getText().toString().substring(0, editTextPadrao.getText().toString().length() - 1));
                }

            }
        });

    }

    public void onBackPressed()  {
        Intent it = new Intent(QuestaoActivity.this, ListaCaracOrganActivity.class);
        startActivity(it);
        finish();
    }

}