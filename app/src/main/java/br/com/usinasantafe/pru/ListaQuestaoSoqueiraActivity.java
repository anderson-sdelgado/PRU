package br.com.usinasantafe.pru;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import br.com.usinasantafe.pru.model.bean.variaveis.AmostraPerdaBean;
import br.com.usinasantafe.pru.model.bean.variaveis.AmostraSoqueiraBean;

public class ListaQuestaoSoqueiraActivity extends ActivityGeneric {

    private PRUContext pruContext;
    private ListView questaoListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_questao_soqueira);

        TextView textViewTituloAmostra = (TextView) findViewById(R.id.textViewTituloAmostra);
        Button buttonListaQuestaoRetornar =  (Button) findViewById(R.id.buttonListaQuestaoRetornar);
        Button buttonListaQuestaoExcluir =  (Button) findViewById(R.id.buttonListaQuestaoExcluir);

        pruContext = (PRUContext) getApplication();

        textViewTituloAmostra.setText("AMOSTRA " + pruContext.getPosPontoAmostra());

        ArrayList<String> itens = new ArrayList<String>();

        AmostraSoqueiraBean amostraSoqueiraBean = pruContext.getSoqueiraCTR().getAmostraSoqueira(pruContext.getPosPontoAmostra());

        itens.add("SOQUEIRA = " + amostraSoqueiraBean.getQtdeSoqueira());
        itens.add("ARRANQUIO = " + amostraSoqueiraBean.getQtdeArranquio());

        AdapterList adapterList = new AdapterList(this, itens);
        questaoListView = (ListView) findViewById(R.id.listViewQuestao);
        questaoListView.setAdapter(adapterList);

        questaoListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> l, View v, int position,
                                    long id) {

                pruContext.setVerPosTela(10);
                pruContext.setPosQuestao(position + 1);
                Intent it = new Intent(ListaQuestaoSoqueiraActivity.this, QuestaoSoqueiraActivity.class);
                startActivity(it);
                finish();

            }

        });

        buttonListaQuestaoRetornar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent it = new Intent(ListaQuestaoSoqueiraActivity.this, ListaAmostraSoqueiraActivity.class);
                startActivity(it);
                finish();

            }
        });

        buttonListaQuestaoExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder alerta = new AlertDialog.Builder(ListaQuestaoSoqueiraActivity.this);
                alerta.setTitle("ATENÇÃO");
                alerta.setMessage("DESEJAR REALMENTE EXCLUIR ESSA AMOSTRA?");

                alerta.setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        pruContext.getSoqueiraCTR().delAmostraSoqueira(pruContext.getPosPontoAmostra());
                        Intent it = new Intent(ListaQuestaoSoqueiraActivity.this, ListaAmostraSoqueiraActivity.class);
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