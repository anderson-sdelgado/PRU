package br.com.usinasantafe.pru.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pru.PRUContext;
import br.com.usinasantafe.pru.R;
import br.com.usinasantafe.pru.model.bean.variaveis.AmostraPerdaBean;
import br.com.usinasantafe.pru.model.bean.variaveis.AmostraSoqueiraBean;
import br.com.usinasantafe.pru.model.bean.variaveis.ApontRuricolaBean;
import br.com.usinasantafe.pru.model.bean.variaveis.RespFitoBean;

public class RelatItemActivity extends ActivityGeneric {

    private TextView textViewTituloRelatItem;
    private ListView listViewRelatItem;
    private PRUContext pruContext;
    private List dadosList;
    private ApontRuricolaBean apontRuricolaBean;
    private RespFitoBean respFitoBean;
    private AmostraPerdaBean amostraPerdaBean;
    private AmostraSoqueiraBean amostraSoqueiraBean;
    private ArrayList<String> itens;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relat_item);

        textViewTituloRelatItem = (TextView) findViewById(R.id.textViewTituloRelatItem);
        Button buttonAntRelatItem = (Button) findViewById(R.id.buttonAntRelatItem);
        Button buttonProxRelatItem = (Button) findViewById(R.id.buttonProxRelatItem);
        Button buttonRetRelatItem = (Button) findViewById(R.id.buttonRetRelatItem);

        pruContext = (PRUContext) getApplication();

        itens = new ArrayList<String>();

        carregDados();
        exibirTituloItem();
        exibirItem();

        buttonAntRelatItem.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(pruContext.getPosQuestao() > 0){
                    pruContext.setPosCabec(pruContext.getPosQuestao() - 1);
                    exibirTituloItem();
                    exibirItem();
                }
            }
        });

        buttonProxRelatItem.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(dadosList.size() < pruContext.getPosQuestao()){
                    pruContext.setPosCabec(pruContext.getPosQuestao() - 1);
                    exibirTituloItem();
                    exibirItem();
                }
            }
        });

        buttonRetRelatItem.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent it = new Intent(RelatItemActivity.this, RelatCabecActivity.class);
                startActivity(it);
                finish();
            }
        });

    }

    public void carregDados(){
        if(pruContext.getVerPosTela() == 16){
            dadosList = pruContext.getRuricolaCTR().getListApont(pruContext.getId());
        }
        else if(pruContext.getVerPosTela() == 17){
            dadosList = pruContext.getFitoCTR().getListResp(pruContext.getId());
        }
        else if(pruContext.getVerPosTela() == 18){
            dadosList = pruContext.getPerdaCTR().getListAmostra(pruContext.getId());
        }
        else if(pruContext.getVerPosTela() == 19){
            dadosList = pruContext.getSoqueiraCTR().getListAmostra(pruContext.getId());
        }
    }

    public void exibirTituloItem(){

        if(pruContext.getVerPosTela() == 16){
            textViewTituloRelatItem.setText("APONTAMENTO " + (pruContext.getPosQuestao() + 1));
            dadosList = pruContext.getRuricolaCTR().getListApont(pruContext.getId());
        }
        else if(pruContext.getVerPosTela() == 17){
            textViewTituloRelatItem.setText("AMOSTRA " + (pruContext.getPosQuestao() + 1));
        }
        else if(pruContext.getVerPosTela() == 18){
            textViewTituloRelatItem.setText("AMOSTRA " + (pruContext.getPosQuestao() + 1));
        }
        else if(pruContext.getVerPosTela() == 19){
            textViewTituloRelatItem.setText("AMOSTRA " + (pruContext.getPosQuestao() + 1));
        }

    }

    public void exibirItem(){

        if(pruContext.getVerPosTela() == 16){
            apontRuricolaBean = (ApontRuricolaBean) dadosList.get(pruContext.getPosCabec());
            setItemRuricola(itens);
        }
        else if(pruContext.getVerPosTela() == 17){
            setItemFito();
        }
        else if(pruContext.getVerPosTela() == 18){
            amostraPerdaBean = (AmostraPerdaBean) dadosList.get(pruContext.getPosCabec());
            setItemPerda(itens);
        }
        else if(pruContext.getVerPosTela() == 19){
            amostraSoqueiraBean = (AmostraSoqueiraBean) dadosList.get(pruContext.getPosCabec());
            setItemSoqueira(itens);
        }

        AdapterList adapterList = new AdapterList(this, itens);
        listViewRelatItem = (ListView) findViewById(R.id.listViewRelatItem);
        listViewRelatItem.setAdapter(adapterList);

    }

    public void setItemRuricola(ArrayList<String> itens){
        itens.add("DATA HORA = " + apontRuricolaBean.getDthrApont());
        itens.add("FUNCIONÁRIO = " + pruContext.getRuricolaCTR().getFunc(apontRuricolaBean.getFuncApont()).getMatricFunc() + " - " + pruContext.getRuricolaCTR().getFunc(apontRuricolaBean.getFuncApont()).getNomeFunc());
        itens.add("NRO OS = " + apontRuricolaBean.getOsApont());
        if(apontRuricolaBean.getParadaApont() == 0) {
            itens.add("ATIVIDADE = " + pruContext.getRuricolaCTR().getAtividade(apontRuricolaBean.getAtivApont()).getCodAtiv() + " - " +  pruContext.getRuricolaCTR().getAtividade(apontRuricolaBean.getAtivApont()).getDescrAtiv());
        }
        else{
            itens.add("PARADA = " + pruContext.getRuricolaCTR().getParada(apontRuricolaBean.getParadaApont()).getCodParada() + " - " +  pruContext.getRuricolaCTR().getParada(apontRuricolaBean.getParadaApont()).getDescrParada());
        }

    }

    public void setItemFito(){
        for (int i = 0; i < dadosList.size(); i++) {
            respFitoBean = (RespFitoBean) dadosList.get(i);
            if(respFitoBean.getPontoRespFito() == (pruContext.getPosQuestao() + 1)){
                itens.add(pruContext.getFitoCTR().getAmostra(respFitoBean.getIdAmostraRespFito()).getDescrAmostra() + " = " + respFitoBean.getValorRespFito());
            }
        }
    }

    public void setItemPerda(ArrayList<String> itens){
        itens.add("TARA = " + amostraPerdaBean.getTaraAmostraPerda());
        itens.add("TOLETE = " + amostraPerdaBean.getToleteAmostraPerda());
        itens.add("CANA INTEIRA = " + amostraPerdaBean.getCanaInteiraAmostraPerda());
        itens.add("TOCO = " + amostraPerdaBean.getTocoAmostraPerda());
        itens.add("PEDAÇO = " + amostraPerdaBean.getPedacoAmostraPerda());
        itens.add("PONTEIRO = " + amostraPerdaBean.getPonteiroAmostraPerda());
        itens.add("LASCAS = " + amostraPerdaBean.getLascasAmostraPerda());
        itens.add("PEDAÇO = " + amostraPerdaBean.getPedacoAmostraPerda());
        itens.add("REPIQUE = " + amostraPerdaBean.getRepiqueAmostraPerda());
        itens.add("OBSERVAÇÃO" +
                "\n" + ((amostraPerdaBean.getPedraAmostraPerda() == 1) ? "CONTÉM PEDRA" : "NÃO CONTÉM PEDRA") +
                "\n" + ((amostraPerdaBean.getTocoArvoreAmostraPerda() == 1) ? "CONTÉM TOCO ARVORE" : "NÃO CONTÉM  TOCO ARVORE") +
                "\n" + ((amostraPerdaBean.getPlantaDaninhasAmostraPerda() == 1) ? "CONTÉM PLANTA DANINHAS" : "NÃO CONTÉM PLANTA DANINHAS") +
                "\n" + ((amostraPerdaBean.getFormigueiroAmostraPerda() == 1) ? "CONTÉM FORMIGUEIROS" : "NÃO CONTÉM FORMIGUEIROS"));
    }

    public void setItemSoqueira(ArrayList<String> itens){
        itens.add("SOQUEIRA = " + amostraSoqueiraBean.getQtdeSoqueira());
        itens.add("ARRANQUIO = " + amostraSoqueiraBean.getQtdeArranquio());
    }

}