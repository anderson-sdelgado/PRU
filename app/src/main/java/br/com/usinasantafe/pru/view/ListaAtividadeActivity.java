package br.com.usinasantafe.pru.view;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import br.com.usinasantafe.pru.PRUContext;
import br.com.usinasantafe.pru.R;
import br.com.usinasantafe.pru.model.bean.estaticas.AtividadeBean;
import br.com.usinasantafe.pru.util.ConexaoWeb;

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

        buttonAtualAtividade.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                ConexaoWeb conexaoWeb = new ConexaoWeb();

                if (conexaoWeb.verificaConexao(ListaAtividadeActivity.this)) {

                    progressBar = new ProgressDialog(v.getContext());
                    progressBar.setCancelable(true);
                    progressBar.setMessage("ATUALIZANDO ATIVIDADES...");
                    progressBar.show();

                    pruContext.getConfigCTR().verOS(String.valueOf(pruContext.getConfigCTR().getConfig().getNroOSConfig())
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

        ativArrayList = pruContext.getRuricolaCTR().getAtivArrayList(pruContext.getConfigCTR().getConfig().getNroOSConfig());

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

                AtividadeBean atividadeBean = new AtividadeBean();
                atividadeBean = (AtividadeBean) ativArrayList.get(position);

                pruContext.getConfigCTR().setAtivConfig(atividadeBean.getIdAtiv());

                Intent it;

                if(pruContext.getVerPosTela() == 1){

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

                }
                else if (pruContext.getVerPosTela() == 2) {

                    pruContext.getRuricolaCTR().setIdParada(0L);

                    if(pruContext.getConfigCTR().getConfig().getIdTipoConfig() == 1){
                        it = new Intent(ListaAtividadeActivity.this, ListaFuncApontActivity.class);
                    }
                    else{
                        pruContext.getRuricolaCTR().salvaApont();
//                        RFuncaoAtivParBean rFuncaoAtivParBean = pruContext.getRuricolaCTR().getFuncaoAtivParBean(atividadeBean.getIdAtiv());
//                        if(rFuncaoAtivParBean.getCodFuncao() == 1L){
//                            it = new Intent(ListaAtividadeActivity.this, TalhaoActivity.class);
//                        }
//                        else if(rFuncaoAtivParBean.getCodFuncao() == 2L){
//                            pruContext.setVerPosTela(8);
//                            it = new Intent(ListaAtividadeActivity.this, EquipActivity.class);
//                        }
//                        else if(rFuncaoAtivParBean.getCodFuncao() == 3L){
//                            pruContext.setVerPosTela(11);
//                            it = new Intent(ListaAtividadeActivity.this, EquipActivity.class);
//                        }
//                        else{
                            it = new Intent(ListaAtividadeActivity.this, MenuMotoMecActivity.class);
//                        }
                    }

                }
                else //if (pruContext.getVerPosTela() == 3)
                {
                    it = new Intent(ListaAtividadeActivity.this, ListaParadaActivity.class);
                }

                startActivity(it);
                finish();
            }

        });

    }

    public void onBackPressed()  {
    }

}
