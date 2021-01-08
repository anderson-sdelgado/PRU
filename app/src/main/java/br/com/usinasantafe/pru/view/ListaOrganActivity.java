package br.com.usinasantafe.pru.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pru.PRUContext;
import br.com.usinasantafe.pru.R;
import br.com.usinasantafe.pru.model.bean.estaticas.OrganFitoBean;

public class ListaOrganActivity extends ActivityGeneric {

    private PRUContext pruContext;
    private ListView organListView;
    private List<OrganFitoBean> organList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_organ);

        pruContext = (PRUContext) getApplication();

        Button buttonRetOrgan = (Button) findViewById(R.id.buttonRetOrgan);

        ArrayList<String> itens = new ArrayList<String>();

        organList = pruContext.getFitoCTR().getOrganList();

        for (OrganFitoBean organFitoBean : organList) {
            itens.add(organFitoBean.getDescrOrgan());
        }

        AdapterList adapterList = new AdapterList(this, itens);
        organListView = (ListView) findViewById(R.id.listOrgan);
        organListView.setAdapter(adapterList);

        organListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> l, View v, int position,
                                    long id) {

                pruContext.getFitoCTR().getCabecFitoBean().setIdOrgCabecFito(organList.get(position).getIdOrgan());
                organList.clear();

                Intent it = new Intent(ListaOrganActivity.this, ListaCaracOrganActivity.class);
                startActivity(it);
                finish();

            }

        });

        buttonRetOrgan.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent it = new Intent(ListaOrganActivity.this, TalhaoActivity.class);
                startActivity(it);
                finish();
            }
        });

    }

    public void onBackPressed()  {
    }

}