package br.com.usinasantafe.pru;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import br.com.usinasantafe.pru.model.bean.estaticas.AtividadeBean;
import br.com.usinasantafe.pru.util.ConexaoWeb;
import br.com.usinasantafe.pru.util.VerifDadosServ;

public class ListaAtividadeActivity extends ActivityGeneric {

    private ListView ativListView;
    private PRUContext pruContext;
    private ProgressDialog progressBar;
    private Long nroOS = 0L;
    private ArrayList ativArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_atividade);

        pruContext = (PRUContext) getApplication();

        Button buttonAtualAtividade = (Button) findViewById(R.id.buttonAtualAtividade);
        Button buttonRetAtividade = (Button) findViewById(R.id.buttonRetAtividade);

        buttonAtualAtividade.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                ConexaoWeb conexaoWeb = new ConexaoWeb();

                if (conexaoWeb.verificaConexao(ListaAtividadeActivity.this)) {
                    progressBar = new ProgressDialog(v.getContext());
                    progressBar.setCancelable(true);
                    progressBar.setMessage("Atualizando Atividades...");
                    progressBar.show();
                    VerifDadosServ.getInstance().verDados(String.valueOf(nroOS), "OS"
                            , ListaAtividadeActivity.this, ListaAtividadeActivity.class, progressBar);
                }

            }
        });

        buttonRetAtividade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(ListaAtividadeActivity.this, OSActivity.class);
                startActivity(it);
            }
        });

        ativArrayList = pruContext.getRuricolaCTR().getAtivArrayList(nroOS);

        ArrayList<String> itens = new ArrayList<String>();
        for (int i = 0; i < ativArrayList.size(); i++) {
            AtividadeBean atividadeBean = (AtividadeBean) ativArrayList.get(i);
            itens.add(atividadeBean.getCodAtiv() + " - " + atividadeBean.getDescrAtiv());
        }

        AdapterList adapterList = new AdapterList(this, itens);
        ativListView = (ListView) findViewById(R.id.listAtividade);
        ativListView.setAdapter(adapterList);

        ativListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> l, View v, int position,
                                    long id) {
                // TODO Auto-generated method stub

                AtividadeBean atividadeBean = new AtividadeBean();
                atividadeBean = (AtividadeBean) ativArrayList.get(position);

                pruContext.getConfigCTR().setAtivConfig(atividadeBean.getIdAtiv());

                if(pruContext.getVerPosTela() == 1){

                    Intent it;
                    switch ((int) pruContext.getConfigCTR().getConfig().getIdTipoConfig().longValue()) {
                        case 1:
                            it = new Intent(ListaAtividadeActivity.this, ListaFuncActivity.class);
                            break;
                        case 2:
                            pruContext.getRuricolaCTR().salvarBolAberto();
                            it = new Intent(ListaAtividadeActivity.this, MenuMotoMecActivity.class);
                            break;
                        default:
                            it = new Intent(ListaAtividadeActivity.this, FuncActivity.class);
                            break;
                    }

                    startActivity(it);
                    finish();

                }
                else if (pruContext.getVerPosTela() == 2) {

                    pruContext.getRuricolaCTR().setIdParada(0L);

                    Intent it;
                    if(pruContext.getConfigCTR().getConfig().getIdTipoConfig() == 1){
                        it = new Intent(ListaAtividadeActivity.this, ListaFuncApontActivity.class);
                    }
                    else{
                        pruContext.getRuricolaCTR().salvaApont();
                        it = new Intent(ListaAtividadeActivity.this, MenuMotoMecActivity.class);

                    }

                    startActivity(it);
                    finish();

                } else if (pruContext.getVerPosTela() == 3) {
                    Intent it = new Intent(ListaAtividadeActivity.this, ListaParadaActivity.class);
                    startActivity(it);
                    finish();
                }

            }

        });

    }

    public void onBackPressed()  {
    }

}
