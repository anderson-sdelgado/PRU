package br.com.usinasantafe.pru.view;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pru.PRUContext;
import br.com.usinasantafe.pru.R;
import br.com.usinasantafe.pru.model.bean.estaticas.ParadaBean;

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

        Button buttonAtualParada = findViewById(R.id.buttonAtualParada);
        Button buttonRetMenuParada = findViewById(R.id.buttonRetMenuParada);

        buttonAtualParada.setOnClickListener(v -> {

            progressBar = new ProgressDialog(v.getContext());
            progressBar.setCancelable(true);
            progressBar.setMessage("Atualizando Paradas...");
            progressBar.show();

            pruContext.getConfigCTR().atualDadosParada(ListaParadaActivity.this, ListaParadaActivity.class, progressBar);

        });

        buttonRetMenuParada.setOnClickListener(v -> {

            Intent it = new Intent(ListaParadaActivity.this, ListaAtividadeActivity.class);
            startActivity(it);
            finish();

        });

        listParada = pruContext.getRuricolaCTR().getParadaList();

        ArrayList<String> itens = new ArrayList();

        for (int i = 0; i < listParada.size(); i++) {
            ParadaBean paradaBean = (ParadaBean) listParada.get(i);
            itens.add(paradaBean.getCodParada() + " - " + paradaBean.getDescrParada());
        }

        AdapterList adapterList = new AdapterList(this, itens);
        lista = findViewById(R.id.listViewMotParada);
        lista.setAdapter(adapterList);

        lista.setOnItemClickListener((l, v, position, id) -> {

            ParadaBean paradaBean = (ParadaBean) listParada.get(position);

            pruContext.getRuricolaCTR().setIdParada(paradaBean.getIdParada());

            Intent it;
            if(pruContext.getConfigCTR().getConfig().getIdTipoConfig() == 1) {
                it = new Intent(ListaParadaActivity.this, ListaFuncApontActivity.class);
                startActivity(it);
                finish();


            } else {
                if(pruContext.getRuricolaCTR().verApont()){
                    pruContext.getRuricolaCTR().salvaApont();
                    it = new Intent(ListaParadaActivity.this, MenuApontActivity.class);
                    startActivity(it);
                    finish();


                } else {
                    AlertDialog.Builder alerta = new AlertDialog.Builder( ListaParadaActivity.this);
                    alerta.setTitle("ATENÇÃO");
                    alerta.setMessage("PARADA JÁ APONTADA PARA O COLABORADOR!");
                    alerta.setPositiveButton("OK", (dialog, which) -> {
                    });
                    alerta.show();
                }
            }

        });

    }

    public void onBackPressed()  {
    }

}
