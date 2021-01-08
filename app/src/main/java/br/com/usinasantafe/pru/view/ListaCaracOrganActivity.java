package br.com.usinasantafe.pru.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
import br.com.usinasantafe.pru.model.bean.estaticas.CaracOrganFitoBean;

public class ListaCaracOrganActivity extends ActivityGeneric {

    private ListView caracOrganListView;
    private PRUContext pruContext;
    private List<CaracOrganFitoBean> caracOrganList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_carac_organ);

        pruContext = (PRUContext) getApplication();

        Button buttonRetCaracOrgan = (Button) findViewById(R.id.buttonRetCaracOrgan);

        caracOrganList = pruContext.getFitoCTR().getCaracOrganList();

        ArrayList<String> itens = new ArrayList<String>();

        for (CaracOrganFitoBean caracOrganFitoBean : caracOrganList) {
            itens.add(caracOrganFitoBean.getDescrCaracOrgan());
        }

        AdapterList adapterList = new AdapterList(this, itens);
        caracOrganListView = (ListView) findViewById(R.id.listCaracOrgan);
        caracOrganListView.setAdapter(adapterList);

        caracOrganListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> l, View v, int position,
                                    long id) {

                pruContext.getFitoCTR().getCabecFitoBean().setIdCaracOrgCabecFito(caracOrganList.get(position).getIdCaracOrgan());
                if(pruContext.getFitoCTR().verAmostra()){

                    pruContext.getFitoCTR().salvarCabecFitoAberto();
                    pruContext.getConfigCTR().setPontoAmostraConfig(0L);

                    if(pruContext.getFitoCTR().hasTipoAmostraCabec()){
                        pruContext.setVerPosTela(5);
                        pruContext.setPosQuestao(1);
                        Intent it = new Intent(ListaCaracOrganActivity.this, QuestaoFitoActivity.class);
                        startActivity(it);
                        finish();
                    }
                    else{
                        Intent it = new Intent(ListaCaracOrganActivity.this, ListaPontosFitoActivity.class);
                        startActivity(it);
                        finish();
                    }

                }
                else{

                    AlertDialog.Builder alerta = new AlertDialog.Builder(ListaCaracOrganActivity.this);
                    alerta.setTitle("ATENCAO");
                    alerta.setMessage("ESSA CARACTERÍSTICA NÃO CONTÉM ITEM. POR FAVOR, VERIFIQUE SE CARACTERÍSTICA QUE DESEJA APONTAR.");
                    alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });

                    alerta.show();

                }

            }

        });

        buttonRetCaracOrgan.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent it = new Intent(ListaCaracOrganActivity.this, ListaOrganActivity.class);
                startActivity(it);
                finish();
            }
        });

    }

    public void onBackPressed()  {
    }

}