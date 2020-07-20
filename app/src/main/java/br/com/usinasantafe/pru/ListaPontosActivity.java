package br.com.usinasantafe.pru;

import android.app.AlertDialog;
import android.app.ProgressDialog;
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

import br.com.usinasantafe.pru.model.bean.estaticas.FuncBean;
import br.com.usinasantafe.pru.model.bean.estaticas.OSBean;
import br.com.usinasantafe.pru.model.bean.estaticas.TalhaoBean;

public class ListaPontosActivity extends ActivityGeneric {

    private PRUContext pruContext;
    private ListView pontoListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_pontos);

        TextView textViewAuditor = (TextView) findViewById(R.id.textViewAuditor);
        TextView textViewSecao = (TextView) findViewById(R.id.textViewSecao);
        TextView textViewTalhao = (TextView) findViewById(R.id.textViewTalhao);
        Button buttonInserirPonto = (Button) findViewById(R.id.buttonInserirPonto);
        Button buttonExcluirAnalise = (Button) findViewById(R.id.buttonExcluirAnalise);
        Button buttonEnviarAnalise = (Button)  findViewById(R.id.buttonEnviarAnalise);

        pruContext = (PRUContext) getApplication();

        FuncBean funcBean = pruContext.getFitoCTR().getFuncCabec();
        textViewAuditor.setText(funcBean.getMatricFunc() + " - " + funcBean.getNomeFunc());

        OSBean osBean = pruContext.getFitoCTR().getOS();
        textViewSecao.setText("SECAO: " + osBean.getCodSecao() + " - " + osBean.getCodSecao());

        TalhaoBean talhaoBean = pruContext.getFitoCTR().getTalhao();
        textViewTalhao.setText("TALHAO: " + talhaoBean.getCodTalhao());

        ArrayList<String> itens = new ArrayList<String>();
        for (int i = 0; i < pruContext.getConfigCTR().getConfig().getPontoConfig(); i++) {
            int pos = i + 1;
            itens.add("PONTO " + pos);
        }

        AdapterList adapterList = new AdapterList(this, itens);
        pontoListView = (ListView) findViewById(R.id.listViewPontos);
        pontoListView.setAdapter(adapterList);

        pontoListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> l, View v, int position,
                                    long id) {

                pruContext.setPosPonto(Long.valueOf(position));
                Intent it = new Intent(ListaPontosActivity.this, ListaQuestaoActivity.class);
                startActivity(it);

            }

        });

        buttonInserirPonto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                Long ponto = pruContext.getConfigCTR().getConfig().getPontoConfig();
                pruContext.setPosPonto(ponto + 1);
                pruContext.setVerPosTela(6);
                Intent it = new Intent(ListaPontosActivity.this, MsgPontoActivity.class);
                startActivity(it);

            }
        });

        buttonExcluirAnalise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                AlertDialog.Builder alerta = new AlertDialog.Builder(ListaPontosActivity.this);
                alerta.setTitle("ATENÇÃO");
                alerta.setMessage("DESEJAR REALMENTE EXCLUIR ESSE ANALISE?");

                alerta.setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        pruContext.getFitoCTR().delFito();

                        Intent it = new Intent(ListaPontosActivity.this, MenuInicialActivity.class);
                        startActivity(it);

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

        buttonEnviarAnalise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                if(!pruContext.getFitoCTR().hasRespCabec()){

                    String mensagem = "POR FAVOR, INSIRA PONTOS ANTES DE ENVIAR OS DADOS.";

                    AlertDialog.Builder alerta = new AlertDialog.Builder( ListaPontosActivity.this);
                    alerta.setTitle("ATENÇÃO");
                    alerta.setMessage(mensagem);

                    alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
                    alerta.show();

                }
                else{

                    pruContext.getFitoCTR().fecharCabecFito();
                    Intent it = new Intent( ListaPontosActivity.this, MenuInicialActivity.class);
                    startActivity(it);
                    finish();

                }

            }
        });

    }

    public void onBackPressed()  {
    }

}