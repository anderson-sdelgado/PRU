package br.com.usinasantafe.pru.view;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import br.com.usinasantafe.pru.PRUContext;
import br.com.usinasantafe.pru.R;
import br.com.usinasantafe.pru.model.bean.estaticas.AtividadeBean;

public class ListaAtividadeActivity extends ActivityGeneric {

    private ListView ativListView;
    private PRUContext pruContext;
    private ProgressDialog progressBar;
    private ArrayList ativArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_atividade);

        pruContext = (PRUContext) getApplication();

        Button buttonAtualAtividade = (Button) findViewById(R.id.buttonAtualAtividade);
        Button buttonRetAtividade = (Button) findViewById(R.id.buttonRetAtividade);

        buttonAtualAtividade.setOnClickListener(v -> {

            if(connectNetwork){

                progressBar = new ProgressDialog(v.getContext());
                progressBar.setCancelable(true);
                progressBar.setMessage("ATUALIZANDO ATIVIDADES...");
                progressBar.show();

                pruContext.getConfigCTR().verOS(String.valueOf(pruContext.getConfigCTR().getConfig().getNroOSConfig())
                        , ListaAtividadeActivity.this, ListaAtividadeActivity.class, progressBar);

            }

        });

        buttonRetAtividade.setOnClickListener(v -> {
            Intent it = new Intent(ListaAtividadeActivity.this, OSActivity.class);
            startActivity(it);
        });

        ativArrayList = pruContext.getRuricolaCTR().getAtivArrayList(pruContext.getConfigCTR().getConfig().getNroOSConfig());

        ArrayList<String> itens = new ArrayList<>();
        for (int i = 0; i < ativArrayList.size(); i++) {
            AtividadeBean atividadeBean = (AtividadeBean) ativArrayList.get(i);
            itens.add(atividadeBean.getCodAtiv() + " - " + atividadeBean.getDescrAtiv());
        }

        AdapterList adapterList = new AdapterList(this, itens);
        ativListView = (ListView) findViewById(R.id.listAtividade);
        ativListView.setAdapter(adapterList);

        ativListView.setOnItemClickListener((l, v, position, id) -> {

            AtividadeBean atividadeBean = (AtividadeBean) ativArrayList.get(position);

            pruContext.getConfigCTR().setAtivConfig(atividadeBean.getIdAtiv());

            Intent it;

            if(pruContext.getVerPosTela() == 1){

                switch ((int) pruContext.getConfigCTR().getConfig().getIdTipoConfig().longValue()) {
                    case 1: {
                        it = new Intent(ListaAtividadeActivity.this, ListaFuncAlocActivity.class);
                        break;
                    }
                    case 2: {
                        pruContext.getRuricolaCTR().salvarBolAberto();
                        it = new Intent(ListaAtividadeActivity.this, MenuApontActivity.class);
                        break;
                    }
                    default: {
                        it = new Intent(ListaAtividadeActivity.this, FuncActivity.class);
                        break;
                    }
                }

                ativArrayList.clear();
                startActivity(it);
                finish();

            }
            else if (pruContext.getVerPosTela() == 2) {

                pruContext.getRuricolaCTR().setIdParada(0L);

                if(pruContext.getConfigCTR().getConfig().getIdTipoConfig() == 1){
                    ativArrayList.clear();
                    it = new Intent(ListaAtividadeActivity.this, ListaFuncApontActivity.class);
                    startActivity(it);
                    finish();
                } else {

                    if(pruContext.getRuricolaCTR().verApont()){
                        pruContext.getRuricolaCTR().salvaApont();
                        ativArrayList.clear();
                        it = new Intent(ListaAtividadeActivity.this, MenuApontActivity.class);
                        startActivity(it);
                        finish();
                    } else {
                        AlertDialog.Builder alerta = new AlertDialog.Builder( ListaAtividadeActivity.this);
                        alerta.setTitle("ATENÇÃO");
                        alerta.setMessage("OPERAÇÃO JÁ APONTADA PARA O COLABORADOR!");
                        alerta.setPositiveButton("OK", (dialog, which) -> {
                        });
                        alerta.show();
                    }

                }

            } else {
                ativArrayList.clear();
                it = new Intent(ListaAtividadeActivity.this, ListaParadaActivity.class);
                startActivity(it);
                finish();
            }

        });

    }

    public void onBackPressed()  {
    }

}
