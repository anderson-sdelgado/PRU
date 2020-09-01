package br.com.usinasantafe.pru;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pru.model.bean.variaveis.BoletimRuricolaBean;
import br.com.usinasantafe.pru.model.bean.variaveis.CabecFitoBean;
import br.com.usinasantafe.pru.model.bean.variaveis.CabecPerdaBean;
import br.com.usinasantafe.pru.model.bean.variaveis.CabecSoqueiraBean;

public class RelatCabecActivity extends ActivityGeneric {

    private ListView listViewRelatCabec;
    private PRUContext pruContext;
    private List dadosList;
    private BoletimRuricolaBean boletimRuricolaBean;
    private CabecFitoBean cabecFitoBean;
    private CabecPerdaBean cabecPerdaBean;
    private CabecSoqueiraBean cabecSoqueiraBean;
    private TextView textViewTituloRelatCabec;
    private Button buttonItemCabec;
    private ArrayList<String> itens;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relat_cabec);

        textViewTituloRelatCabec = (TextView) findViewById(R.id.textViewTituloRelatCabec);
        buttonItemCabec = (Button) findViewById(R.id.buttonItemCabec);
        Button buttonAntRelatCabec = (Button) findViewById(R.id.buttonAntRelatCabec);
        Button buttonProxRelatCabec = (Button) findViewById(R.id.buttonProxRelatCabec);
        Button buttonRetRelatCabec = (Button) findViewById(R.id.buttonRetRelatCabec);

        pruContext = (PRUContext) getApplication();

        itens = new ArrayList<String>();

        exibirTituloCabec();
        exibirCabec();

        buttonItemCabec.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if(pruContext.getVerPosTela() == 16){
                    pruContext.setId(boletimRuricolaBean.getIdBol());
                }
                else if(pruContext.getVerPosTela() == 17){
                    pruContext.setId(cabecFitoBean.getIdCabecFito());
                }
                else if(pruContext.getVerPosTela() == 18){
                    pruContext.setId(cabecFitoBean.getIdCabecFito());
                }
                else if(pruContext.getVerPosTela() == 19){
                    pruContext.setId(cabecSoqueiraBean.getIdCabecSoqueira());
                }

                pruContext.setPosQuestao(0);
                Intent it = new Intent(RelatCabecActivity.this, RelatItemActivity.class);
                startActivity(it);
                finish();

            }
        });

        buttonAntRelatCabec.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if(pruContext.getPosCabec() > 0){
                    pruContext.setPosCabec(pruContext.getPosCabec() - 1);
                    exibirCabec();
                }

            }
        });

        buttonProxRelatCabec.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if(dadosList.size() < pruContext.getPosCabec()){
                    pruContext.setPosCabec(pruContext.getPosCabec() + 1);
                    exibirCabec();
                }

            }
        });

        buttonRetRelatCabec.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent it = new Intent(RelatCabecActivity.this, MenuRelatorioActivity.class);
                startActivity(it);
                finish();

            }
        });

    }

    public void exibirTituloCabec(){

        if(pruContext.getVerPosTela() == 16){
            textViewTituloRelatCabec.setText("BOLETIM RURÍCOLA");
            buttonItemCabec.setText("APONTAMENTO(S)");
            dadosList = pruContext.getRuricolaCTR().bolFechadoEnviadoList();
        }
        else if(pruContext.getVerPosTela() == 17){
            textViewTituloRelatCabec.setText("BOLETIM FITO");
            buttonItemCabec.setText("AMOSTRA(S)");
            dadosList = pruContext.getFitoCTR().cabecFechadoEnviadoList();
        }
        else if(pruContext.getVerPosTela() == 18){
            textViewTituloRelatCabec.setText("BOLETIM PERDA");
            buttonItemCabec.setText("AMOSTRA(S)");
            dadosList = pruContext.getPerdaCTR().cabecFechadoEnviadoList();
        }
        else if(pruContext.getVerPosTela() == 19){
            textViewTituloRelatCabec.setText("BOLETIM SOQUEIRA");
            buttonItemCabec.setText("AMOSTRA(S)");
            dadosList = pruContext.getSoqueiraCTR().cabecFechadoEnviadoList();
        }

    }

    public void exibirCabec(){

        if(pruContext.getVerPosTela() == 16){
            boletimRuricolaBean = (BoletimRuricolaBean) dadosList.get(pruContext.getPosCabec());
            setItemRuricola(itens);
        }
        else if(pruContext.getVerPosTela() == 17){
            cabecFitoBean = (CabecFitoBean) dadosList.get(pruContext.getPosCabec());
            setItemFito(itens);
        }
        else if(pruContext.getVerPosTela() == 18){
            cabecPerdaBean = (CabecPerdaBean) dadosList.get(pruContext.getPosCabec());
            setItemPerda(itens);
        }
        else if(pruContext.getVerPosTela() == 19){
            cabecSoqueiraBean = (CabecSoqueiraBean) dadosList.get(pruContext.getPosCabec());
            setItemSoqueira(itens);
        }

        AdapterList adapterList = new AdapterList(this, itens);
        listViewRelatCabec = (ListView) findViewById(R.id.listViewRelatCabec);
        listViewRelatCabec.setAdapter(adapterList);

    }

    public void setItemRuricola(ArrayList<String> itens){
        itens.add("DATA HORA INICIAL = " + boletimRuricolaBean.getDthrInicioBol());
        itens.add("DATA HORA FINAL = " + boletimRuricolaBean.getDthrFimBol());
        itens.add("LÍDER = " + pruContext.getRuricolaCTR().getFunc(boletimRuricolaBean.getIdLiderBol()).getMatricFunc() + " - " + pruContext.getRuricolaCTR().getFunc(boletimRuricolaBean.getIdLiderBol()).getNomeFunc());
        itens.add("TURMA = " + pruContext.getRuricolaCTR().getTurma(boletimRuricolaBean.getIdTurmaBol()).getCodTurma() + " - " + pruContext.getRuricolaCTR().getTurma(boletimRuricolaBean.getIdTurmaBol()).getDescrTurma());
        itens.add("NRO OS = " + boletimRuricolaBean.getOsBol());
        itens.add("ATIVIDADE = " + pruContext.getRuricolaCTR().getAtividade(boletimRuricolaBean.getAtivPrincBol()).getCodAtiv() + " - " +  pruContext.getRuricolaCTR().getAtividade(boletimRuricolaBean.getAtivPrincBol()).getDescrAtiv());
    }

    public void setItemFito(ArrayList<String> itens){
        itens.add("DATA HORA = " + cabecFitoBean.getDthrCabecFito());
        itens.add("AUDITOR = " + pruContext.getFitoCTR().getFunc(cabecFitoBean.getAuditorCabecFito()).getMatricFunc() + " - " + pruContext.getFitoCTR().getFunc(cabecFitoBean.getAuditorCabecFito()).getNomeFunc());
        itens.add("TALHÃO = " +  cabecFitoBean.getTalhaoCabecFito());
        itens.add("NRO OS = " + cabecFitoBean.getOSCabecFito());
        itens.add("ORGANISMO = " +  pruContext.getFitoCTR().getOrganFito(cabecFitoBean.getIdOrgCabecFito()).getCodOrgan() + " - " + pruContext.getFitoCTR().getOrganFito(cabecFitoBean.getIdOrgCabecFito()).getDescrOrgan());
        itens.add("CARAC. ORGANISMO = " + pruContext.getFitoCTR().getCaracOrganFito(cabecFitoBean.getIdCaracOrgCabecFito()).getCodCaracOrgan() + " - " + pruContext.getFitoCTR().getCaracOrganFito(cabecFitoBean.getIdCaracOrgCabecFito()).getDescrCaracOrgan());
    }

    public void setItemPerda(ArrayList<String> itens){
        itens.add("DATA HORA = " + cabecPerdaBean.getDthrCabecPerda());
        itens.add("AUDITOR = " + pruContext.getPerdaCTR().getFunc(cabecPerdaBean.getAuditorCabecPerda()).getMatricFunc() + " - " + pruContext.getPerdaCTR().getFunc(cabecPerdaBean.getAuditorCabecPerda()).getNomeFunc());
        itens.add("NRO OS = " + cabecPerdaBean.getOsCabecPerda());
        itens.add("EQUIPAMENTO = " +  pruContext.getPerdaCTR().getEquip(cabecPerdaBean.getEquipCabecPerda()).getNroEquip() + " - " + pruContext.getPerdaCTR().getEquip(cabecPerdaBean.getEquipCabecPerda()).getDescrClasseEquip());
    }

    public void setItemSoqueira(ArrayList<String> itens){
        itens.add("DATA HORA = " + cabecSoqueiraBean.getDthrCabecSoqueira());
        itens.add("AUDITOR = " + pruContext.getSoqueiraCTR().getFunc(cabecSoqueiraBean.getAuditorCabecSoqueira()).getMatricFunc() + " - " + pruContext.getSoqueiraCTR().getFunc(cabecSoqueiraBean.getAuditorCabecSoqueira()).getNomeFunc());
        itens.add("NRO OS = " + cabecSoqueiraBean.getOsCabecSoqueira());
        itens.add("EQUIPAMENTO = " +  pruContext.getSoqueiraCTR().getEquip(cabecSoqueiraBean.getEquipCabecSoqueira()).getNroEquip() + " - " + pruContext.getSoqueiraCTR().getEquip(cabecSoqueiraBean.getEquipCabecSoqueira()).getDescrClasseEquip());
    }

}