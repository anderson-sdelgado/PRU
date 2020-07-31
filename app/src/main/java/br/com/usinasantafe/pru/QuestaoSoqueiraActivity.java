package br.com.usinasantafe.pru;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import br.com.usinasantafe.pru.model.bean.variaveis.AmostraSoqueiraBean;

public class QuestaoSoqueiraActivity extends ActivityGeneric {

    private PRUContext pruContext;
    private TextView textViewPadrao;
    private Long ponto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questao_soqueira);

        pruContext = (PRUContext) getApplication();

        textViewPadrao = (TextView) findViewById(R.id.textViewPadrao);
        Button buttonOkQuestao = (Button) findViewById(R.id.buttonOkPadrao);
        Button buttonCancQuestao = (Button) findViewById(R.id.buttonCancPadrao);

        ponto = (pruContext.getConfigCTR().getConfig().getPontoAmostraConfig() + 1);

        if(pruContext.getVerPosTela() == 12){
            textViewPadrao.setText("AMOSTRA " + ponto + "\n" + "SOQUEIRA");
        }
        else{
            if(pruContext.getPosQuestao() == 1){
                textViewPadrao.setText("AMOSTRA " + pruContext.getPosPontoAmostra() + "\n" + "SOQUEIRA");
            }
            else{
                textViewPadrao.setText("AMOSTRA " + pruContext.getPosPontoAmostra() + "\n" + "ARRANQUIO");
            }
        }

        buttonOkQuestao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Long valor;
                if (!editTextPadrao.getText().toString().equals("")) {
                    valor = Long.parseLong(editTextPadrao.getText().toString());
                }
                else{
                    valor = 0L;
                }

                if(pruContext.getVerPosTela() == 12){
                    if(pruContext.getPosQuestao() == 1){
                        pruContext.getSoqueiraCTR().setAmostraSoqueira(new AmostraSoqueiraBean());
                        pruContext.getSoqueiraCTR().getAmostraSoqueiraBean().setQtdeSoqueira(valor);
                        pruContext.setPosQuestao(2);
                        textViewPadrao.setText("AMOSTRA " + ponto + "\n" + "ARRANQUIO");
                        editTextPadrao.setText("");
                    }
                    else{
                        pruContext.getSoqueiraCTR().getAmostraSoqueiraBean().setQtdeArranquio(valor);
                        pruContext.getSoqueiraCTR().salvarAmostraSoqueira(ponto);
                        Intent it = new Intent(QuestaoSoqueiraActivity.this, ListaAmostraSoqueiraActivity.class);
                        startActivity(it);
                        finish();
                    }
                }
                else {
                    pruContext.getSoqueiraCTR().setQuestaoAmostraSoqueira(valor, pruContext.getPosQuestao(), pruContext.getPosPontoAmostra());
                    Intent it = new Intent(QuestaoSoqueiraActivity.this, ListaQuestaoSoqueiraActivity.class);
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
        if(pruContext.getVerPosTela() == 12){
            if(pruContext.getPosQuestao() == 1){
                Intent it = new Intent(QuestaoSoqueiraActivity.this, ListaAmostraSoqueiraActivity.class);
                startActivity(it);
                finish();
            }
            else{
                pruContext.setPosQuestao(1);
                textViewPadrao.setText("AMOSTRA " + ponto + "\n" + "SOQUEIRA");
                editTextPadrao.setText("");
            }
        }
        else {
            Intent it = new Intent(QuestaoSoqueiraActivity.this, ListaQuestaoSoqueiraActivity.class);
            startActivity(it);
            finish();
        }
    }

}