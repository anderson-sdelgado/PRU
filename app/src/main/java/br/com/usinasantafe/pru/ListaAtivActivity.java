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

import br.com.usinasantafe.pru.bo.ConexaoWeb;
import br.com.usinasantafe.pru.bo.ManipDadosEnvio;
import br.com.usinasantafe.pru.bo.ManipDadosVerif;
import br.com.usinasantafe.pru.to.tb.estaticas.AtividadeTO;
import br.com.usinasantafe.pru.to.tb.estaticas.ROSAtivTO;
import br.com.usinasantafe.pru.to.tb.variaveis.BoletimTO;
import br.com.usinasantafe.pru.to.tb.variaveis.ConfiguracaoTO;

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
            nroOS = pruContext.getBoletimTO().getOsBoletim();
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

                    ManipDadosVerif.getInstance().verDados(String.valueOf(nroOS), "OS"
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

        AtividadeTO atividadeTO = new AtividadeTO();
        listAtiv = atividadeTO.all();

        lAtivExib = new ArrayList();

        ROSAtivTO rOSAtivTO = new ROSAtivTO();
        List lroa = rOSAtivTO.get("nroOS", nroOS);

        if(lroa.size() > 0){

            for (int i = 0; i < listAtiv.size(); i++) {
                atividadeTO = (AtividadeTO) listAtiv.get(i);
                for (int j = 0; j < lroa.size(); j++) {
                    rOSAtivTO = (ROSAtivTO) lroa.get(j);
                    if (Objects.equals(atividadeTO.getCodAtiv(), rOSAtivTO.getCodAtiv())) {
                        lAtivExib.add(atividadeTO);
                    }
                }
            }

        } else {

            for (int i = 0; i < listAtiv.size(); i++) {
                atividadeTO = (AtividadeTO) listAtiv.get(i);
                lAtivExib.add(atividadeTO);
            }

        }

        for (int i = 0; i < lAtivExib.size(); i++) {
            atividadeTO = (AtividadeTO) lAtivExib.get(i);
            itens.add(atividadeTO.getCodAtiv() + " - " + atividadeTO.getDescrAtiv());
        }

        AdapterList adapterList = new AdapterList(this, itens);
        lista = (ListView) findViewById(R.id.listAtividade);
        lista.setAdapter(adapterList);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> l, View v, int position,
                                    long id) {
                // TODO Auto-generated method stub

                AtividadeTO atividadeTO = new AtividadeTO();
                atividadeTO = (AtividadeTO) lAtivExib.get(position);

                if(pruContext.getVerPosTelaPrinc() == 1){

                    pruContext.getBoletimTO().setAtivPrincBoletim(atividadeTO.getIdAtiv());

                    ConfiguracaoTO configuracaoTO = new ConfiguracaoTO();
                    List configList = configuracaoTO.all();
                    configuracaoTO = (ConfiguracaoTO) configList.get(0);

                    Intent it;
                    switch ((int) configuracaoTO.getIdTipo().longValue()) {
                        case 1:
                            it = new Intent(ListaAtivActivity.this, ListaFuncActivity.class);
                            break;
                        case 2:
                            pruContext.getBoletimTO().setIdLiderBoletim(configuracaoTO.getCodFunc());
                            ManipDadosEnvio.getInstance().salvaBoletimAberto(pruContext.getBoletimTO());
                            ManipDadosEnvio.getInstance().salvaFuncBoletim(configuracaoTO.getCodFunc(), 1L);
                            ManipDadosEnvio.getInstance().envioDadosPrinc();
                            it = new Intent(ListaAtivActivity.this, MenuPrincipalActivity.class);
                            break;
                        default:
                            it = new Intent(ListaAtivActivity.this, FuncionarioActivity.class);
                            break;
                    }

                    startActivity(it);
                    finish();

                }
                else if (pruContext.getVerPosTelaPrinc() == 2) {

                    pruContext.getApontamentoTO().setAtivAponta(atividadeTO.getIdAtiv());
                    pruContext.getApontamentoTO().setParadaAponta(0L);

                    ConfiguracaoTO configuracaoTO = new ConfiguracaoTO();
                    List configList = configuracaoTO.all();
                    configuracaoTO = (ConfiguracaoTO) configList.get(0);

                    Intent it;
                    switch ((int) configuracaoTO.getIdTipo().longValue()) {
                        case 1:
                            it = new Intent(ListaAtivActivity.this, ListaFuncApontActivity.class);
                            break;
                        default:

                            BoletimTO boletimTO = new BoletimTO();
                            List boletimList = boletimTO.get("statusBoletim", 1L);
                            boletimTO = (BoletimTO) boletimList.get(0);

                            ManipDadosEnvio.getInstance().salvaAponta(pruContext.getApontamentoTO(), boletimTO.getIdLiderBoletim());

                            it = new Intent(ListaAtivActivity.this, MenuPrincipalActivity.class);
                            break;
                    }

                    startActivity(it);
                    finish();


                } else if (pruContext.getVerPosTelaPrinc() == 3) {

                    pruContext.getApontamentoTO().setAtivAponta(atividadeTO.getIdAtiv());
                    Intent it = new Intent(ListaAtivActivity.this, MenuParadaActivity.class);
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
