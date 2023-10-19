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
import br.com.usinasantafe.pru.model.bean.variaveis.BoletimRuricolaBean;

public class RelatCabecActivity extends ActivityGeneric {

    private ListView listViewRelatCabec;
    private PRUContext pruContext;
    private List dadosList;
    private BoletimRuricolaBean boletimRuricolaBean;
    private TextView textViewTituloRelatCabec;
    private Button buttonItemCabec;
    private ArrayList<String> itens;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relat_cabec);

        textViewTituloRelatCabec = findViewById(R.id.textViewTituloRelatCabec);
        buttonItemCabec = findViewById(R.id.buttonItemCabec);
        Button buttonAntRelatCabec = findViewById(R.id.buttonAntRelatCabec);
        Button buttonProxRelatCabec = findViewById(R.id.buttonProxRelatCabec);
        Button buttonRetRelatCabec = findViewById(R.id.buttonRetRelatCabec);

        pruContext = (PRUContext) getApplication();

        itens = new ArrayList();

        exibirTituloCabec();
        exibirCabec();

        buttonItemCabec.setOnClickListener(v -> {

            pruContext.setId(boletimRuricolaBean.getIdBol());
            pruContext.setPosQuestao(0);
            Intent it = new Intent(RelatCabecActivity.this, RelatItemActivity.class);
            startActivity(it);
            finish();

        });

        buttonAntRelatCabec.setOnClickListener(v -> {
            if(pruContext.getPosCabec() > 0){
                pruContext.setPosCabec(pruContext.getPosCabec() - 1);
                exibirCabec();
            }
        });

        buttonProxRelatCabec.setOnClickListener(v -> {
            if(dadosList.size() < pruContext.getPosCabec()){
                pruContext.setPosCabec(pruContext.getPosCabec() + 1);
                exibirCabec();
            }
        });

        buttonRetRelatCabec.setOnClickListener(v -> {
            Intent it = new Intent(RelatCabecActivity.this, TelaInicialActivity.class);
            startActivity(it);
            finish();
        });

    }

    public void exibirTituloCabec(){
        textViewTituloRelatCabec.setText("BOLETIM RURÍCOLA");
        buttonItemCabec.setText("APONTAMENTO(S)");
        dadosList = pruContext.getRuricolaCTR().bolFechadoEnviadoList();
    }

    public void exibirCabec(){

        boletimRuricolaBean = (BoletimRuricolaBean) dadosList.get(pruContext.getPosCabec());
        setItemRuricola(itens);

        AdapterList adapterList = new AdapterList(this, itens);
        listViewRelatCabec = findViewById(R.id.listViewRelatCabec);
        listViewRelatCabec.setAdapter(adapterList);

    }

    public void setItemRuricola(ArrayList<String> itens){
        itens.add("DATA HORA INICIAL = " + boletimRuricolaBean.getDthrInicioBol());
        itens.add("DATA HORA FINAL = " + boletimRuricolaBean.getDthrFimBol());
        itens.add("LÍDER = " + pruContext.getRuricolaCTR().getFuncMatric(boletimRuricolaBean.getMatricLiderBol()).getMatricFunc() + " - " + pruContext.getRuricolaCTR().getFuncMatric(boletimRuricolaBean.getMatricLiderBol()).getNomeFunc());
        itens.add("TURMA = " + pruContext.getRuricolaCTR().getTurma(boletimRuricolaBean.getIdTurmaBol()).getCodTurma() + " - " + pruContext.getRuricolaCTR().getTurma(boletimRuricolaBean.getIdTurmaBol()).getDescrTurma());
        itens.add("NRO OS = " + boletimRuricolaBean.getOsBol());
        itens.add("ATIVIDADE = " + pruContext.getRuricolaCTR().getAtividade(boletimRuricolaBean.getAtivPrincBol()).getCodAtiv() + " - " +  pruContext.getRuricolaCTR().getAtividade(boletimRuricolaBean.getAtivPrincBol()).getDescrAtiv());
    }

}