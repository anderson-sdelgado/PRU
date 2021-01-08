package br.com.usinasantafe.pru.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import br.com.usinasantafe.pru.PRUContext;
import br.com.usinasantafe.pru.R;
import br.com.usinasantafe.pru.model.bean.variaveis.AmostraPerdaBean;

public class ListaQuestaoPerdaActivity extends ActivityGeneric {

    private PRUContext pruContext;
    private ListView questaoListView;
    private Long amostra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_questao_perda);

        TextView textViewTituloAmostra = (TextView) findViewById(R.id.textViewTituloAmostra);
        Button buttonListaQuestaoRetornar =  (Button) findViewById(R.id.buttonListaQuestaoRetornar);
        Button buttonListaQuestaoExcluir =  (Button) findViewById(R.id.buttonListaQuestaoExcluir);

        pruContext = (PRUContext) getApplication();

        textViewTituloAmostra.setText("AMOSTRA " + pruContext.getPosPontoAmostra());

        ArrayList<String> itens = new ArrayList<String>();

        AmostraPerdaBean amostraPerdaBean = pruContext.getPerdaCTR().getAmostraPerda(pruContext.getPosPontoAmostra());

        itens.add("TARA = " + amostraPerdaBean.getTaraAmostraPerda());
        itens.add("TOLETE = " + amostraPerdaBean.getToleteAmostraPerda());
        itens.add("CANA INTEIRA = " + amostraPerdaBean.getCanaInteiraAmostraPerda());
        itens.add("TOCO = " + amostraPerdaBean.getTocoAmostraPerda());
        itens.add("PEDAÇO = " + amostraPerdaBean.getPedacoAmostraPerda());
        itens.add("PONTEIRO = " + amostraPerdaBean.getPonteiroAmostraPerda());
        itens.add("LASCAS = " + amostraPerdaBean.getLascasAmostraPerda());
        itens.add("PEDAÇO = " + amostraPerdaBean.getPedacoAmostraPerda());
        itens.add("REPIQUE = " + amostraPerdaBean.getRepiqueAmostraPerda());
        itens.add("OBSERVAÇÃO" +
                "\n" + ((amostraPerdaBean.getPedraAmostraPerda() == 1) ? "CONTÉM PEDRA" : "NÃO CONTÉM PEDRA") +
                "\n" + ((amostraPerdaBean.getTocoArvoreAmostraPerda() == 1) ? "CONTÉM TOCO ARVORE" : "NÃO CONTÉM  TOCO ARVORE") +
                "\n" + ((amostraPerdaBean.getPlantaDaninhasAmostraPerda() == 1) ? "CONTÉM PLANTA DANINHAS" : "NÃO CONTÉM PLANTA DANINHAS") +
                "\n" + ((amostraPerdaBean.getFormigueiroAmostraPerda() == 1) ? "CONTÉM FORMIGUEIROS" : "NÃO CONTÉM FORMIGUEIROS"));

        AdapterList adapterList = new AdapterList(this, itens);
        questaoListView = (ListView) findViewById(R.id.listViewQuestao);
        questaoListView.setAdapter(adapterList);

        questaoListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> l, View v, int position,
                                    long id) {

                pruContext.setVerPosTela(10);
                if(position < 8) {
                    pruContext.setPosQuestao(position + 1);
                    Intent it = new Intent(ListaQuestaoPerdaActivity.this, QuestaoPerdaActivity.class);
                    startActivity(it);
                    finish();
                }
                else{
                    Intent it = new Intent(ListaQuestaoPerdaActivity.this, ListaObsActivity.class);
                    startActivity(it);
                    finish();
                }

            }

        });

        buttonListaQuestaoRetornar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent it = new Intent(ListaQuestaoPerdaActivity.this, ListaAmostraPerdaActivity.class);
                startActivity(it);
                finish();

            }
        });

        buttonListaQuestaoExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder alerta = new AlertDialog.Builder(ListaQuestaoPerdaActivity.this);
                alerta.setTitle("ATENÇÃO");
                alerta.setMessage("DESEJAR REALMENTE EXCLUIR ESSA AMOSTRA?");

                alerta.setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        pruContext.getPerdaCTR().delAmostraPerda(pruContext.getPosPontoAmostra());
                        Intent it = new Intent(ListaQuestaoPerdaActivity.this, ListaAmostraPerdaActivity.class);
                        startActivity(it);
                        finish();

                    }

                });

                alerta.setNegativeButton("NÃO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }

                });

                alerta.show();

            }
        });

    }

    public void onBackPressed()  {
    }

}