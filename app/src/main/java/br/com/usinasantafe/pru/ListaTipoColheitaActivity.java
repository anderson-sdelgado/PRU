package br.com.usinasantafe.pru;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class ListaTipoColheitaActivity extends AppCompatActivity {

    private ListView tipoColheitaList;
    private PRUContext pruContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_tipo_colheita);

        pruContext = (PRUContext) getApplication();

        Button buttonRetMenu = (Button) findViewById(R.id.buttonRetTipoColheita);
        ArrayList<String> itens = new ArrayList<String>();

        itens.add("MECANIZADA");
        itens.add("MANUAL");

        AdapterList adapterList = new AdapterList(this, itens);
        tipoColheitaList = (ListView) findViewById(R.id.listaTipoColheita);
        tipoColheitaList.setAdapter(adapterList);

        tipoColheitaList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> l, View v, int position,
                                    long id) {

//                if(position == 0){
//                    PPCContext.getCabecalhoVARTO().setTipo(1L);
//                }
//                else if(position == 1){
//                    PPCContext.getCabecalhoVARTO().setTipo(2L);
//                }

                Intent it = new Intent(ListaTipoColheitaActivity.this, EquipActivity.class);
                startActivity(it);

            }

        });

        buttonRetMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent it = new Intent(ListaMenuActivity.this, ListaCabecalhoActivity.class);
//                startActivity(it);
            }
        });

    }
}