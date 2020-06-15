package br.com.usinasantafe.pru;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import br.com.usinasantafe.pru.util.ConexaoWeb;
import br.com.usinasantafe.pru.util.EnvioDadosServ;
import br.com.usinasantafe.pru.util.VerifDadosServ;

public class ListaAtivActivity extends ActivityGeneric {

    private ListView lista;
    private PRUContext pruContext;
    private List ativList;
    private ProgressDialog progressBar;
    private Long nroOS = 0L;
    private List listAtiv;
    private ArrayList lAtivExib;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_ativ);

        pruContext = (PRUContext) getApplication();

        if(pruContext.getVerPosTelaPrinc() == 1){
            nroOS = pruContext.getBoletimBean().getOsBoletim();
        }
        else{
            nroOS = pruContext.getApontamentoTO().getOsAponta();
        }

        Button buttonAtualAtividade = (Button) findViewById(R.id.buttonAtualAtividade);
        Button buttonRetAtividade = (Button) findViewById(R.id.buttonRetAtividade);

        buttonAtualAtividade.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                ConexaoWeb conexaoWeb = new ConexaoWeb();

                if (conexaoWeb.verificaConexao(ListaAtivActivity.this)) {

                    progressBar = new ProgressDialog(v.getContext());
                    progressBar.setCancelable(true);
                    progressBar.setMessage("Atualizando Atividades...");
                    progressBar.show();

                    VerifDadosServ.getInstance().verDados(String.valueOf(nroOS), "OS"
                            , ListaAtivActivity.this, ListaAtivActivity.class, progressBar);

                }

            }
        });

        buttonRetAtividade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent it = new Intent(ListaAtivActivity.this, OSActivity.class);
                startActivity(it);
            }
        });

        ArrayList<String> itens = new ArrayList<String>();

        br.com.usinasantafe.pru.to.tb.estaticas.AtividadeBean atividadeBean = new br.com.usinasantafe.pru.to.tb.estaticas.AtividadeBean();
        listAtiv = atividadeBean.all();

        lAtivExib = new ArrayList();

        br.com.usinasantafe.pru.to.tb.estaticas.ROSAtivBean rOSAtivBean = new br.com.usinasantafe.pru.to.tb.estaticas.ROSAtivBean();
        List lroa = rOSAtivBean.get("nroOS", nroOS);

        if(lroa.size() > 0){

            for (int i = 0; i < listAtiv.size(); i++) {
                atividadeBean = (br.com.usinasantafe.pru.to.tb.estaticas.AtividadeBean) listAtiv.get(i);
                for (int j = 0; j < lroa.size(); j++) {
                    rOSAtivBean = (br.com.usinasantafe.pru.to.tb.estaticas.ROSAtivBean) lroa.get(j);
                    if (Objects.equals(atividadeBean.getCodAtiv(), rOSAtivBean.getCodAtiv())) {
                        lAtivExib.add(atividadeBean);
                    }
                }
            }

        } else {

            for (int i = 0; i < listAtiv.size(); i++) {
                atividadeBean = (br.com.usinasantafe.pru.to.tb.estaticas.AtividadeBean) listAtiv.get(i);
                lAtivExib.add(atividadeBean);
            }

        }

        for (int i = 0; i < lAtivExib.size(); i++) {
            atividadeBean = (br.com.usinasantafe.pru.to.tb.estaticas.AtividadeBean) lAtivExib.get(i);
            itens.add(atividadeBean.getCodAtiv() + " - " + atividadeBean.getDescrAtiv());
        }

        AdapterList adapterList = new AdapterList(this, itens);
        lista = (ListView) findViewById(R.id.listAtividade);
        lista.setAdapter(adapterList);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> l, View v, int position,
                                    long id) {
                // TODO Auto-generated method stub

                br.com.usinasantafe.pru.to.tb.estaticas.AtividadeBean atividadeBean = new br.com.usinasantafe.pru.to.tb.estaticas.AtividadeBean();
                atividadeBean = (br.com.usinasantafe.pru.to.tb.estaticas.AtividadeBean) lAtivExib.get(position);

                if(pruContext.getVerPosTelaPrinc() == 1){

                    pruContext.getBoletimBean().setAtivPrincBoletim(atividadeBean.getIdAtiv());

                    br.com.usinasantafe.pru.to.tb.variaveis.ConfigBean configBean = new br.com.usinasantafe.pru.to.tb.variaveis.ConfigBean();
                    List configList = configBean.all();
                    configBean = (br.com.usinasantafe.pru.to.tb.variaveis.ConfigBean) configList.get(0);

                    Intent it;
                    switch ((int) configBean.getIdTipo().longValue()) {
                        case 1:
                            it = new Intent(ListaAtivActivity.this, ListaFuncActivity.class);
                            break;
                        case 2:
                            pruContext.getBoletimBean().setIdLiderBoletim(configBean.getCodFunc());
                            EnvioDadosServ.getInstance().salvaBoletimAberto(pruContext.getBoletimBean());
                            EnvioDadosServ.getInstance().salvaFuncBoletim(configBean.getCodFunc(), 1L);
                            EnvioDadosServ.getInstance().envioDadosPrinc();
                            it = new Intent(ListaAtivActivity.this, MenuMotoMecActivity.class);
                            break;
                        default:
                            it = new Intent(ListaAtivActivity.this, FuncionarioActivity.class);
                            break;
                    }

                    startActivity(it);
                    finish();

                }
                else if (pruContext.getVerPosTelaPrinc() == 2) {

                    pruContext.getApontamentoTO().setAtivAponta(atividadeBean.getIdAtiv());
                    pruContext.getApontamentoTO().setParadaAponta(0L);

                    br.com.usinasantafe.pru.to.tb.variaveis.ConfigBean configBean = new br.com.usinasantafe.pru.to.tb.variaveis.ConfigBean();
                    List configList = configBean.all();
                    configBean = (br.com.usinasantafe.pru.to.tb.variaveis.ConfigBean) configList.get(0);

                    Intent it;
                    switch ((int) configBean.getIdTipo().longValue()) {
                        case 1:
                            it = new Intent(ListaAtivActivity.this, ListaFuncApontActivity.class);
                            break;
                        default:

                            br.com.usinasantafe.pru.to.tb.variaveis.BoletimBean boletimBean = new br.com.usinasantafe.pru.to.tb.variaveis.BoletimBean();
                            List boletimList = boletimBean.get("statusBoletim", 1L);
                            boletimBean = (br.com.usinasantafe.pru.to.tb.variaveis.BoletimBean) boletimList.get(0);

                            EnvioDadosServ.getInstance().salvaAponta(pruContext.getApontamentoTO(), boletimBean.getIdLiderBoletim());

                            it = new Intent(ListaAtivActivity.this, MenuMotoMecActivity.class);
                            break;
                    }

                    startActivity(it);
                    finish();


                } else if (pruContext.getVerPosTelaPrinc() == 3) {

                    pruContext.getApontamentoTO().setAtivAponta(atividadeBean.getIdAtiv());
                    Intent it = new Intent(ListaAtivActivity.this, ListaParadaActivity.class);
                    startActivity(it);
                    finish();

                }

                listAtiv.clear();
                lAtivExib.clear();

            }

        });

    }

    public void onBackPressed()  {
    }

}
