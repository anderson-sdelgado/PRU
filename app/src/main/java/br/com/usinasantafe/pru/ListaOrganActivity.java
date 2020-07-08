package br.com.usinasantafe.pru;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pru.model.bean.estaticas.FuncBean;
import br.com.usinasantafe.pru.model.bean.estaticas.OrganBean;

public class ListaOrganActivity extends ActivityGeneric {

    private PRUContext pruContext;
    private ListView organListView;
    private List<OrganBean> organList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_organ);

        pruContext = (PRUContext) getApplication();

        Button buttonRetOrgan = (Button) findViewById(R.id.buttonRetOrgan);

        ArrayList<String> itens = new ArrayList<String>();

        organList = pruContext.getFitoCTR().getOrganList();

        for (OrganBean organBean : organList) {
            itens.add(organBean.getDescrOrgan());
        }

        AdapterList adapterList = new AdapterList(this, itens);
        organListView = (ListView) findViewById(R.id.listOrgan);
        organListView.setAdapter(adapterList);

        organListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> l, View v, int position,
                                    long id) {
                // TODO Auto-generated method stub

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
                // TODO Auto-generated method stub
                Intent it = new Intent(ListaOrganActivity.this, TalhaoActivity.class);
                startActivity(it);
                finish();
            }
        });

    }

    public void onBackPressed()  {
    }

}