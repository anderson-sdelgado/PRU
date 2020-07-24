package br.com.usinasantafe.pru;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pru.model.bean.estaticas.ParadaBean;
import br.com.usinasantafe.pru.util.EnvioDadosServ;

public class ListaParadaActivity extends ActivityGeneric {

    private ListView lista;
    private PRUContext pruContext;
    private List listParada;
    private ProgressDialog progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_parada);

        pruContext = (PRUContext) getApplication();

        Button buttonAtualParada = (Button) findViewById(R.id.buttonAtualParada);
        Button buttonRetMenuParada = (Button) findViewById(R.id.buttonRetMenuParada);

        buttonAtualParada.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                progressBar = new ProgressDialog(v.getContext());
                progressBar.setCancelable(true);
                progressBar.setMessage("Atualizando Paradas...");
                progressBar.show();

//                ConfiguracaoTO configuracaoTO = new ConfiguracaoTO();
//                List configList = configuracaoTO.all();
//                configuracaoTO = (ConfiguracaoTO) configList.get(0);
//
//                ManipDadosVerif.getInstance().verDados(String.valueOf(configuracaoTO.getCodFunc() .getEquipConfig()), "Parada"
//                        , ListaParadaActivity.this, ListaParadaActivity.class, progressBar);

            }
        });

        buttonRetMenuParada.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent it = new Intent(ListaParadaActivity.this, ListaAtividadeActivity.class);
                startActivity(it);
                finish();

            }
        });

        listParada = pruContext.getRuricolaCTR().getParadaList();

        ArrayList<String> itens = new ArrayList<String>();

        for (int i = 0; i < listParada.size(); i++) {
            ParadaBean paradaBean = (ParadaBean) listParada.get(i);
            itens.add(paradaBean.getCodParada() + " - " + paradaBean.getDescrParada());
        }

        AdapterList adapterList = new AdapterList(this, itens);
        lista = (ListView) findViewById(R.id.listViewMotParada);
        lista.setAdapter(adapterList);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> l, View v, int position,
                                    long id) {

                ParadaBean paradaBean = (ParadaBean) listParada.get(position);

                pruContext.getRuricolaCTR().setIdParada(paradaBean.getIdParada());

                Intent it;
                if(pruContext.getConfigCTR().getConfig().getIdTipoConfig() == 1) {
                    it = new Intent(ListaParadaActivity.this, ListaFuncApontActivity.class);
                }
                else{
                    pruContext.getRuricolaCTR().salvaApont();
                    it = new Intent(ListaParadaActivity.this, MenuMotoMecActivity.class);
                }

                startActivity(it);
                finish();

                listParada.clear();
            }

        });

    }

    public void onBackPressed()  {
    }

}
