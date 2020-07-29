package br.com.usinasantafe.pru;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import br.com.usinasantafe.pru.model.bean.variaveis.AmostraPerdaBean;

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

        if (pruContext.getVerPosTela() == 10) {

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

        }
        else{

            AmostraPerdaBean amostraPerdaBean = pruContext.getPerdaCTR().getAmostraPerda(pruContext.getPosPontoAmostra());

            ViewHolderChoice viewHolderChoice = new ViewHolderChoice();
            if(amostraPerdaBean.getPedraAmostraPerda() == 1L){
                viewHolderChoice.setSelected(true);
            } else {
                viewHolderChoice.setSelected(false);
            }
            viewHolderChoice.setDescrCheckBox("PEDRA");
            itens.add(viewHolderChoice);

            viewHolderChoice = new ViewHolderChoice();
            if(amostraPerdaBean.getTocoArvoreAmostraPerda() == 1L){
                viewHolderChoice.setSelected(true);
            } else {
                viewHolderChoice.setSelected(false);
            }
            viewHolderChoice.setDescrCheckBox("TOCO DE ARVORE");
            itens.add(viewHolderChoice);

            viewHolderChoice = new ViewHolderChoice();
            if(amostraPerdaBean.getPlantaDaninhasAmostraPerda() == 1L){
                viewHolderChoice.setSelected(true);
            } else {
                viewHolderChoice.setSelected(false);
            }
            viewHolderChoice.setDescrCheckBox("PLANTAS DANINHAS");
            itens.add(viewHolderChoice);

            viewHolderChoice = new ViewHolderChoice();
            if(amostraPerdaBean.getFormigueiroAmostraPerda() == 1L){
                viewHolderChoice.setSelected(true);
            } else {
                viewHolderChoice.setSelected(false);
            }
            viewHolderChoice.setDescrCheckBox("FORMIGUEIROS");
            itens.add(viewHolderChoice);

        }

        adapterListChoice = new AdapterListChoice(this, itens);
        obsListView = (ListView) findViewById(R.id.listFunc);
        obsListView.setAdapter(adapterListChoice);

        buttonSalvarListObs.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (pruContext.getVerPosTela() == 10) {

                    ViewHolderChoice viewHolderChoice = itens.get(0);
                    if(viewHolderChoice.isSelected()){
                        pruContext.getPerdaCTR().getAmostraPerdaBean().setPedraAmostraPerda(1L);
                    } else {
                        pruContext.getPerdaCTR().getAmostraPerdaBean().setPedraAmostraPerda(0L);
                    }

                    viewHolderChoice = itens.get(1);
                    if(viewHolderChoice.isSelected()){
                        pruContext.getPerdaCTR().getAmostraPerdaBean().setTocoArvoreAmostraPerda(1L);
                    } else {
                        pruContext.getPerdaCTR().getAmostraPerdaBean().setTocoArvoreAmostraPerda(0L);
                    }

                    viewHolderChoice = itens.get(2);
                    if(viewHolderChoice.isSelected()){
                        pruContext.getPerdaCTR().getAmostraPerdaBean().setPlantaDaninhasAmostraPerda(1L);
                    } else {
                        pruContext.getPerdaCTR().getAmostraPerdaBean().setPlantaDaninhasAmostraPerda(0L);
                    }

                    viewHolderChoice = itens.get(3);
                    if(viewHolderChoice.isSelected()){
                        pruContext.getPerdaCTR().getAmostraPerdaBean().setFormigueiroAmostraPerda(1L);
                    } else {
                        pruContext.getPerdaCTR().getAmostraPerdaBean().setFormigueiroAmostraPerda(0L);
                    }

                    pruContext.getPerdaCTR().salvarAmostraPerda();

                    Intent it = new Intent(ListaObsActivity.this, ListaAmostraPerdaActivity.class);
                    startActivity(it);
                    finish();

                }
                else{

                    Long pedra = 0L;
                    Long tocoArvore = 0L;
                    Long plantaDaninha = 0L;
                    Long formigueiro = 0L;

                    ViewHolderChoice viewHolderChoice = itens.get(0);
                    if(viewHolderChoice.isSelected()){
                        pedra = 1L;
                    }

                    viewHolderChoice = itens.get(1);
                    if(viewHolderChoice.isSelected()){
                        tocoArvore = 1L;
                    }

                    viewHolderChoice = itens.get(2);
                    if(viewHolderChoice.isSelected()){
                        plantaDaninha = 1L;
                    }

                    viewHolderChoice = itens.get(3);
                    if(viewHolderChoice.isSelected()){
                        formigueiro = 1L;
                    }

                    pruContext.getPerdaCTR().setQuestaoAmostraPerda(pedra, tocoArvore, plantaDaninha, formigueiro, pruContext.getPosPontoAmostra());

                }

            }
        });

        buttonCancListObs.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (pruContext.getVerPosTela() == 10) {

                    if (pruContext.getPerdaCTR().getCabecPerdaAberto().getTipoColheitaCabecPerda() == 1L) {
                        pruContext.setPosQuestao(8);
                    } else {
                        pruContext.setPosQuestao(7);
                    }
                    Intent it = new Intent(ListaObsActivity.this, QuestaoPerdaActivity.class);
                    startActivity(it);
                    finish();

                }
                else{

                    Intent it = new Intent(ListaObsActivity.this, ListaQuestaoPerdaActivity.class);
                    startActivity(it);
                    finish();

                }
            }
        });

    }
}