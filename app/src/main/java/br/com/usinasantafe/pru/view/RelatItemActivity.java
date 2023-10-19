package br.com.usinasantafe.pru.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pru.PRUContext;
import br.com.usinasantafe.pru.R;
import br.com.usinasantafe.pru.model.bean.variaveis.ApontRuricolaBean;

public class RelatItemActivity extends ActivityGeneric {

    private TextView textViewTituloRelatItem;
    private ListView listViewRelatItem;
    private PRUContext pruContext;
    private List dadosList;
    private ApontRuricolaBean apontRuricolaBean;
    private ArrayList<String> itens;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relat_item);

        textViewTituloRelatItem = findViewById(R.id.textViewTituloRelatItem);
        Button buttonAntRelatItem = findViewById(R.id.buttonAntRelatItem);
        Button buttonProxRelatItem = findViewById(R.id.buttonProxRelatItem);
        Button buttonRetRelatItem = findViewById(R.id.buttonRetRelatItem);

        pruContext = (PRUContext) getApplication();

        itens = new ArrayList();

        carregDados();
        exibirTituloItem();
        exibirItem();

        buttonAntRelatItem.setOnClickListener(v -> {
            if(pruContext.getPosQuestao() > 0){
                pruContext.setPosCabec(pruContext.getPosQuestao() - 1);
                exibirTituloItem();
                exibirItem();
            }
        });

        buttonProxRelatItem.setOnClickListener(v -> {
            if(dadosList.size() < pruContext.getPosQuestao()){
                pruContext.setPosCabec(pruContext.getPosQuestao() - 1);
                exibirTituloItem();
                exibirItem();
            }
        });

        buttonRetRelatItem.setOnClickListener(v -> {
            Intent it = new Intent(RelatItemActivity.this, RelatCabecActivity.class);
            startActivity(it);
            finish();
        });

    }

    public void carregDados(){
        switch (pruContext.getVerPosTela()) {
            case 16: {
                dadosList = pruContext.getRuricolaCTR().getListApont(pruContext.getId());
                break;
            }
        }
    }

    public void exibirTituloItem(){
        switch (pruContext.getVerPosTela()) {
            case 16: {
                textViewTituloRelatItem.setText("APONTAMENTO " + (pruContext.getPosQuestao() + 1));
                dadosList = pruContext.getRuricolaCTR().getListApont(pruContext.getId());
                break;
            }
            case 17:
            case 18:
            case 19: {
                textViewTituloRelatItem.setText("AMOSTRA " + (pruContext.getPosQuestao() + 1));
                break;
            }
        }
    }

    public void exibirItem(){

        switch (pruContext.getVerPosTela()) {
            case 16: {
                apontRuricolaBean = (ApontRuricolaBean) dadosList.get(pruContext.getPosCabec());
                setItemRuricola(itens);
                break;
            }
        }

        AdapterList adapterList = new AdapterList(this, itens);
        listViewRelatItem = findViewById(R.id.listViewRelatItem);
        listViewRelatItem.setAdapter(adapterList);

    }

    public void setItemRuricola(ArrayList<String> itens){
        itens.add("DATA HORA = " + apontRuricolaBean.getDthrApont());
        itens.add("FUNCIONÁRIO = " + pruContext.getRuricolaCTR().getFuncMatric(apontRuricolaBean.getMatricFuncApont()).getMatricFunc() + " - " + pruContext.getRuricolaCTR().getFuncMatric(apontRuricolaBean.getMatricFuncApont()).getNomeFunc());
        itens.add("NRO OS = " + apontRuricolaBean.getOsApont());
        if(apontRuricolaBean.getParadaApont() == 0) {
            itens.add("ATIVIDADE = " + pruContext.getRuricolaCTR().getAtividade(apontRuricolaBean.getAtivApont()).getCodAtiv() + " - " +  pruContext.getRuricolaCTR().getAtividade(apontRuricolaBean.getAtivApont()).getDescrAtiv());
        } else {
            itens.add("PARADA = " + pruContext.getRuricolaCTR().getParada(apontRuricolaBean.getParadaApont()).getCodParada() + " - " +  pruContext.getRuricolaCTR().getParada(apontRuricolaBean.getParadaApont()).getDescrParada());
        }

    }

}