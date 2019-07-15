package br.com.usinasantafe.pru;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pru.bo.ManipDadosEnvio;
import br.com.usinasantafe.pru.to.tb.estaticas.ParadaTO;
import br.com.usinasantafe.pru.to.tb.variaveis.BoletimTO;
import br.com.usinasantafe.pru.to.tb.variaveis.ConfiguracaoTO;

public class MenuParadaActivity extends ActivityGeneric {

    private ListView lista;
    private PRUContext pruContext;
    private List listParada;
    private ProgressDialog progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_parada);

        pruContext = (PRUContext) getApplication();

        Button buttonAtualParada = (Button) findViewById(R.id.buttonAtualParada);
        Button buttonRetMenuParada = (Button) findViewById(R.id.buttonRetMenuParada);

        buttonAtualParada.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

//                progressBar = new ProgressDialog(v.getContext());
//                progressBar.setCancelable(true);
//                progressBar.setMessage("Atualizando Paradas...");
//                progressBar.show();
//
//                ConfiguracaoTO configuracaoTO = new ConfiguracaoTO();
//                List configList = configuracaoTO.all();
//                configuracaoTO = (ConfiguracaoTO) configList.get(0);
//
//                ManipDadosVerif.getInstance().verDados(String.valueOf(configuracaoTO.getCodFunc() .getEquipConfig()), "Parada"
//                        , MenuParadaActivity.this, MenuParadaActivity.class, progressBar);

            }
        });

        buttonRetMenuParada.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                Intent it = new Intent(MenuParadaActivity.this, ListaAtivActivity.class);
                startActivity(it);
                finish();

            }
        });

        ParadaTO paradaTO = new ParadaTO();
        listParada = paradaTO.all();

        ArrayList<String> itens = new ArrayList<String>();

        for (int i = 0; i < listParada.size(); i++) {
            paradaTO = (ParadaTO) listParada.get(i);
            itens.add(paradaTO.getCodParada() + " - " + paradaTO.getDescrParada());
        }

        AdapterList adapterList = new AdapterList(this, itens);
        lista = (ListView) findViewById(R.id.listViewMotParada);
        lista.setAdapter(adapterList);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> l, View v, int position,
                                    long id) {
                // TODO Auto-generated method stub

                ParadaTO paradaTO = new ParadaTO();
                paradaTO = (ParadaTO) listParada.get(position);

                pruContext.getApontamentoTO().setParadaAponta(paradaTO.getIdParada());

                ConfiguracaoTO configuracaoTO = new ConfiguracaoTO();
                List configList = configuracaoTO.all();
                configuracaoTO = (ConfiguracaoTO) configList.get(0);

                Intent it;
                switch ((int) configuracaoTO.getIdTipo().longValue()) {
                    case 1:
                        it = new Intent( MenuParadaActivity.this, ListaFuncApontActivity.class);
                        break;
                    default:

                        BoletimTO boletimTO = new BoletimTO();
                        List boletimList = boletimTO.get("statusBoletim", 1L);
                        boletimTO = (BoletimTO) boletimList.get(0);

                        ManipDadosEnvio.getInstance().salvaAponta(pruContext.getApontamentoTO(), boletimTO.getIdLiderBoletim());

                        it = new Intent(MenuParadaActivity.this, MenuPrincipalActivity.class);
                        break;
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
