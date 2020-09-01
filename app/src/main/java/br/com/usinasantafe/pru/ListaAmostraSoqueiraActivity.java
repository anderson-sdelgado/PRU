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

import br.com.usinasantafe.pru.model.bean.estaticas.EquipBean;
import br.com.usinasantafe.pru.model.bean.estaticas.FuncBean;
import br.com.usinasantafe.pru.model.bean.estaticas.OSBean;

public class ListaAmostraSoqueiraActivity extends ActivityGeneric {

    private PRUContext pruContext;
    private ListView amostraListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_amostra_soqueira);

        TextView textViewColhedora = (TextView) findViewById(R.id.textViewColhedora);
        TextView textViewAuditor = (TextView) findViewById(R.id.textViewAuditor);
        TextView textViewSecao = (TextView) findViewById(R.id.textViewSecao);
        TextView textViewFrente = (TextView) findViewById(R.id.textViewFrente);
        Button buttonInserirAmostra = (Button) findViewById(R.id.buttonInserirAmostra);
        Button buttonExcluirAnalise = (Button) findViewById(R.id.buttonExcluirAnalise);
        Button buttonFecharAnalise = (Button)  findViewById(R.id.buttonFecharAnalise);
        Button buttonParadaSoqueira = (Button)  findViewById(R.id.buttonParadaSoqueira);

        pruContext = (PRUContext) getApplication();

        EquipBean equipBean = pruContext.getSoqueiraCTR().getEquip();
        textViewColhedora.setText(equipBean.getNroEquip() + " - " + equipBean.getDescrClasseEquip());

        final FuncBean funcBean = pruContext.getSoqueiraCTR().getFunc();
        textViewAuditor.setText("AUDITOR: " + funcBean.getMatricFunc() + " - " + funcBean.getNomeFunc());

        OSBean osBean = pruContext.getSoqueiraCTR().getOS();
        textViewSecao.setText("SECAO: " + osBean.getCodSecao() + " - " + osBean.getCodSecao());
        textViewFrente.setText(osBean.getDescrFrente());

        ArrayList<String> itens = new ArrayList<String>();
        for (int i = 0; i < pruContext.getConfigCTR().getConfig().getPontoAmostraConfig(); i++) {
            int pos = i + 1;
            itens.add("AMOSTRA " + pos);
        }

        AdapterList adapterList = new AdapterList(this, itens);
        amostraListView = (ListView) findViewById(R.id.listViewAmostra);
        amostraListView.setAdapter(adapterList);

        amostraListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> l, View v, int position,
                                    long id) {

                pruContext.setPosPontoAmostra(Long.valueOf(position) + 1);
                Intent it = new Intent(ListaAmostraSoqueiraActivity.this,  ListaQuestaoSoqueiraActivity.class);
                startActivity(it);
                finish();

            }

        });

        buttonInserirAmostra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                pruContext.setVerPosTela(12);
                pruContext.setPosQuestao(1);
                Intent it = new Intent(ListaAmostraSoqueiraActivity.this, QuestaoSoqueiraActivity.class);
                startActivity(it);
                finish();
            }
        });

        buttonExcluirAnalise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder alerta = new AlertDialog.Builder(ListaAmostraSoqueiraActivity.this);
                alerta.setTitle("ATENÇÃO");
                alerta.setMessage("DESEJAR REALMENTE EXCLUIR ESSE ANALISE?");

                alerta.setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        pruContext.getSoqueiraCTR().delSoqueira();

                        Intent it = new Intent(ListaAmostraSoqueiraActivity.this, MenuMotoMecActivity.class);
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

        buttonFecharAnalise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!pruContext.getSoqueiraCTR().verCabecAberto()){

                    String mensagem = "POR FAVOR, INSIRA AMOSTRA ANTES DE ENVIAR OS DADOS.";

                    AlertDialog.Builder alerta = new AlertDialog.Builder( ListaAmostraSoqueiraActivity.this);
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

                    pruContext.getSoqueiraCTR().fecharCabecSoqueira();
                    Intent it = new Intent( ListaAmostraSoqueiraActivity.this, MenuMotoMecActivity.class);
                    startActivity(it);
                    finish();

                }

            }
        });

        buttonParadaSoqueira.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                pruContext.setVerPosTela(16);
                Intent it = new Intent(ListaAmostraSoqueiraActivity.this, OSActivity.class);
                startActivity(it);
                finish();

            }
        });

    }

    public void onBackPressed()  {
    }

}