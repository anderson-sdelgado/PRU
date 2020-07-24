package br.com.usinasantafe.pru;

import android.content.Intent;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pru.model.bean.estaticas.FuncBean;

public class ListaObsActivity extends ActivityGeneric {

    private PRUContext pruContext;
    private ListView obsListView;
    private AdapterListChoice adapterListChoice;
    private ArrayList<ViewHolderChoice> itens;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_obs);

        pruContext = (PRUContext) getApplication();

        Button buttonSalvarListObs = (Button) findViewById(R.id.buttonSalvarListaObs);
        Button buttonCancListObs = (Button) findViewById(R.id.buttonCancListaObs);

        itens = new ArrayList<>();
        ViewHolderChoice viewHolderChoice = new ViewHolderChoice();
        viewHolderChoice.setSelected(false);
        viewHolderChoice.setDescrCheckBox("PEDRA");
        itens.add(viewHolderChoice);

        viewHolderChoice = new ViewHolderChoice();
        viewHolderChoice.setSelected(false);
        viewHolderChoice.setDescrCheckBox("TOCO DE ARVORE");
        itens.add(viewHolderChoice);

        viewHolderChoice = new ViewHolderChoice();
        viewHolderChoice.setSelected(false);
        viewHolderChoice.setDescrCheckBox("PLANTAS DANINHAS");
        itens.add(viewHolderChoice);

        viewHolderChoice = new ViewHolderChoice();
        viewHolderChoice.setSelected(false);
        viewHolderChoice.setDescrCheckBox("FORMIGUEIROS");
        itens.add(viewHolderChoice);

        adapterListChoice = new AdapterListChoice(this, itens);
        obsListView = (ListView) findViewById(R.id.listFunc);
        obsListView.setAdapter(adapterListChoice);

        buttonSalvarListObs.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                ViewHolderChoice viewHolderChoice = itens.get(0);
                if(viewHolderChoice.isSelected()){
                    pruContext.getPerdaColheitaCTR().getAmostraPerdaBean().setPedraAmostraPerda(1L);
                } else {
                    pruContext.getPerdaColheitaCTR().getAmostraPerdaBean().setPedraAmostraPerda(0L);
                }

                viewHolderChoice = itens.get(1);
                if(viewHolderChoice.isSelected()){
                    pruContext.getPerdaColheitaCTR().getAmostraPerdaBean().setTocoArvoreAmostraPerda(1L);
                } else {
                    pruContext.getPerdaColheitaCTR().getAmostraPerdaBean().setTocoArvoreAmostraPerda(0L);
                }

                viewHolderChoice = itens.get(2);
                if(viewHolderChoice.isSelected()){
                    pruContext.getPerdaColheitaCTR().getAmostraPerdaBean().setPlantaDaninhasAmostraPerda(1L);
                } else {
                    pruContext.getPerdaColheitaCTR().getAmostraPerdaBean().setPlantaDaninhasAmostraPerda(0L);
                }

                viewHolderChoice = itens.get(3);
                if(viewHolderChoice.isSelected()){
                    pruContext.getPerdaColheitaCTR().getAmostraPerdaBean().setFormigueiroAmostraPerda(1L);
                } else {
                    pruContext.getPerdaColheitaCTR().getAmostraPerdaBean().setFormigueiroAmostraPerda(0L);
                }

                pruContext.getPerdaColheitaCTR().salvarAmostraPerda();
 
                Intent it = new Intent(ListaObsActivity.this, ListaAmostraPerdaActivity.class);
                startActivity(it);
                finish();

            }
        });

        buttonCancListObs.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (pruContext.getPerdaColheitaCTR().getCabecPerdaAberto().getTipoColheitaCabecPerda() == 1L) {
                    Intent it = new Intent(ListaObsActivity.this, LascasActivity.class);
                    startActivity(it);
                }
                else {
                    Intent it = new Intent(ListaObsActivity.this, PonteiroActivity.class);
                    startActivity(it);
                }

            }
        });

    }
}