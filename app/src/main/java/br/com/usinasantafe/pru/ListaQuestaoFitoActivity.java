package br.com.usinasantafe.pru;

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
import java.util.List;

import br.com.usinasantafe.pru.model.bean.variaveis.RespFitoBean;

public class ListaQuestaoFitoActivity extends ActivityGeneric {

    private PRUContext pruContext;
    private ListView questaoListView;
    private List<RespFitoBean> respFitoBeanList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_questao_fito);

        TextView textViewTituloPonto = (TextView) findViewById(R.id.textViewTituloPonto);
        Button buttonListaQuestaoRetornar =  (Button) findViewById(R.id.buttonListaQuestaoRetornar);
        Button buttonListaQuestaoExcluir =  (Button) findViewById(R.id.buttonListaQuestaoExcluir);

        pruContext = (PRUContext) getApplication();

        textViewTituloPonto.setText("PONTO " + pruContext.getPosPontoAmostra());

        ArrayList<String> itens = new ArrayList<String>();

        respFitoBeanList = pruContext.getFitoCTR().getRespPontoFitoList(pruContext.getPosPontoAmostra());

        for (RespFitoBean respFitoBean : respFitoBeanList) {
            itens.add(pruContext.getFitoCTR().getAmostra(respFitoBean.getIdAmostraRespFito()).getDescrAmostra() + "\nVALOR: " + respFitoBean.getValorRespFito());
        }

        AdapterList adapterList = new AdapterList(this, itens);
        questaoListView = (ListView) findViewById(R.id.listViewQuestao);
        questaoListView.setAdapter(adapterList);

        questaoListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> l, View v, int position,
                                    long id) {

                pruContext.setVerPosTela(7);
                RespFitoBean respFitoBean = respFitoBeanList.get(position);
                pruContext.getFitoCTR().setIdRespFito(respFitoBean.getIdRespFito());
                Intent it = new Intent(ListaQuestaoFitoActivity.this, QuestaoFitoActivity.class);
                startActivity(it);
                finish();

            }

        });

        buttonListaQuestaoRetornar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent it = new Intent(ListaQuestaoFitoActivity.this, ListaPontosFitoActivity.class);
                startActivity(it);
                finish();

            }
        });

        buttonListaQuestaoExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder alerta = new AlertDialog.Builder(ListaQuestaoFitoActivity.this);
                alerta.setTitle("ATENÇÃO");
                alerta.setMessage("DESEJAR REALMENTE EXCLUIR ESSE PONTO?");

                alerta.setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        pruContext.getFitoCTR().delRespPonto(pruContext.getPosPontoAmostra());
                        Intent it = new Intent(ListaQuestaoFitoActivity.this, ListaPontosFitoActivity.class);
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
}