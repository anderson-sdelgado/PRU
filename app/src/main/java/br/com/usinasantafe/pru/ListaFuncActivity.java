package br.com.usinasantafe.pru;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pru.bo.ManipDadosEnvio;
import br.com.usinasantafe.pru.to.tb.estaticas.FuncTO;
import br.com.usinasantafe.pru.to.tb.variaveis.ConfiguracaoTO;
import br.com.usinasantafe.pru.to.tb.variaveis.FuncBoletimTO;

public class ListaFuncActivity extends ActivityGeneric {

    private ListView lista;
    private PRUContext pruContext;
    private List funcList;
    private AdapterListChoice adapterListChoice;
    private ArrayList<ViewHolderChoice> itens;
    private ConfiguracaoTO configuracaoTO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_funcionario);

        pruContext = (PRUContext) getApplication();

        Button buttonDesmarcarTodos = (Button) findViewById(R.id.buttonDesmarcarTodos);
        Button buttonMarcarTodos = (Button) findViewById(R.id.buttonMarcarTodos);
        Button buttonRetListaFunc = (Button) findViewById(R.id.buttonRetListaFunc);
        Button buttonSalvarListaFunc = (Button) findViewById(R.id.buttonSalvarListaFunc);

        configuracaoTO = new ConfiguracaoTO();
        List configList = configuracaoTO.all();
        configuracaoTO = (ConfiguracaoTO) configList.get(0);

        itens = new ArrayList<ViewHolderChoice>();

        FuncTO funcTO = new FuncTO();
        funcList =  funcTO.getAndOrderBy("idTurma", configuracaoTO.getIdTurma(), "nomeFunc", true);

        if(pruContext.getVerPosTelaPrinc() == 1) {

            for (int i = 0; i < funcList.size(); i++) {
                funcTO = (FuncTO) funcList.get(i);
                ViewHolderChoice viewHolderChoice = new ViewHolderChoice();
                viewHolderChoice.setSelected(false);
                viewHolderChoice.setDescrCheckBox(funcTO.getNomeFunc());
                itens.add(viewHolderChoice);
            }

        }
        else if(pruContext.getVerPosTelaPrinc() == 4) {

            for (int i = 0; i < funcList.size(); i++) {
                funcTO = (FuncTO) funcList.get(i);
                FuncBoletimTO funcBoletimTO = new FuncBoletimTO();
                List funcBoletimList = funcBoletimTO.get("codFuncBoletim", funcTO.getCodFunc());
                ViewHolderChoice viewHolderChoice = new ViewHolderChoice();
                if(funcBoletimList.size() == 0) {
                    viewHolderChoice.setSelected(false);
                }
                else{
                    viewHolderChoice.setSelected(true);
                }
                viewHolderChoice.setDescrCheckBox(funcTO.getNomeFunc());
                itens.add(viewHolderChoice);
            }

        }

        adapterListChoice = new AdapterListChoice(this, itens);
        lista = (ListView) findViewById(R.id.listFunc);
        lista.setAdapter(adapterListChoice);

        buttonDesmarcarTodos.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                itens.clear();
                FuncTO funcTO = new FuncTO();
                for (int i = 0; i < funcList.size(); i++) {
                    funcTO = (FuncTO) funcList.get(i);
                    ViewHolderChoice viewHolderChoice = new ViewHolderChoice();
                    viewHolderChoice.setSelected(false);
                    viewHolderChoice.setDescrCheckBox(funcTO.getNomeFunc());
                    itens.add(viewHolderChoice);
                }

                adapterListChoice = new AdapterListChoice(ListaFuncActivity.this, itens);
                lista = (ListView) findViewById(R.id.listFunc);
                lista.setAdapter(adapterListChoice);

            }
        });

        buttonMarcarTodos.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                itens.clear();
                FuncTO funcTO = new FuncTO();
                for (int i = 0; i < funcList.size(); i++) {
                    funcTO = (FuncTO) funcList.get(i);
                    ViewHolderChoice viewHolderChoice = new ViewHolderChoice();
                    viewHolderChoice.setSelected(true);
                    viewHolderChoice.setDescrCheckBox(funcTO.getNomeFunc());
                    itens.add(viewHolderChoice);
                }

                adapterListChoice = new AdapterListChoice(ListaFuncActivity.this, itens);
                lista = (ListView) findViewById(R.id.listFunc);
                lista.setAdapter(adapterListChoice);

            }
        });

        buttonRetListaFunc.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                if(pruContext.getVerPosTelaPrinc() == 1) {
                    Intent it = new Intent(ListaFuncActivity.this, MenuPrincipalActivity.class);
                    startActivity(it);
                    finish();
                }
                else if(pruContext.getVerPosTelaPrinc() == 4) {
                    Intent it = new Intent(ListaFuncActivity.this, MenuPrincipalActivity.class);
                    startActivity(it);
                    finish();
                }

            }
        });

        buttonSalvarListaFunc.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                ArrayList<Long> funcSelectedList = new ArrayList<Long>();

                for (int i = 0; i < itens.size(); i++) {
                    ViewHolderChoice viewHolderChoice = itens.get(i);

                    if(viewHolderChoice.isSelected()){
                        FuncTO funcTO = (FuncTO) funcList.get(i);
                        funcSelectedList.add(funcTO.getCodFunc());
                    }

                }

                if(funcSelectedList.size() > 0){

                    if(pruContext.getVerPosTelaPrinc() == 1) {

                        pruContext.getBoletimTO().setIdLiderBoletim(configuracaoTO.getCodFunc());
                        ManipDadosEnvio.getInstance().salvaBoletimAberto(pruContext.getBoletimTO());

                        FuncBoletimTO funcBoletimTO = new FuncBoletimTO();
                        funcBoletimTO.deleteAll();

                        for (int i = 0; i < funcSelectedList.size(); i++) {
                            ManipDadosEnvio.getInstance().salvaFuncBoletim(funcSelectedList.get(i), 1L);
                            funcBoletimTO.setCodFuncBoletim(funcSelectedList.get(i));
                            funcBoletimTO.insert();
                        }

                    }
                    else if(pruContext.getVerPosTelaPrinc() == 4) {

                        FuncBoletimTO funcBoletimTO = new FuncBoletimTO();
                        List funcBoletimList = funcBoletimTO.all();

                        for (int i = 0; i < funcBoletimList.size(); i++) {
                            funcBoletimTO = (FuncBoletimTO) funcBoletimList.get(i);
                            int cont = 0;
                            for (int j = 0; j < funcSelectedList.size(); j++) {
                                if(funcBoletimTO.getCodFuncBoletim().equals(funcSelectedList.get(j))){
                                    cont = cont + 1;
                                }
                            }

                            if(cont == 0){
                                ManipDadosEnvio.getInstance().salvaFuncBoletim(funcBoletimTO.getCodFuncBoletim(), 2L);
                                funcBoletimTO.delete();
                            }

                        }

                        for (int i = 0; i < funcSelectedList.size(); i++) {
                            int cont = 0;
                            for (int j = 0; j < funcBoletimList.size(); j++) {
                                funcBoletimTO = (FuncBoletimTO) funcBoletimList.get(j);
                                if(funcBoletimTO.getCodFuncBoletim().equals(funcSelectedList.get(i))){
                                    cont = cont + 1;
                                }
                            }

                            if(cont == 0){
                                ManipDadosEnvio.getInstance().salvaFuncBoletim(funcSelectedList.get(i), 1L);
                                funcBoletimTO.setCodFuncBoletim(funcSelectedList.get(i));
                                funcBoletimTO.insert();
                            }

                        }

                        funcBoletimList.clear();

                    }


                    ManipDadosEnvio.getInstance().envioDadosPrinc();
                    Intent it = new Intent(ListaFuncActivity.this, MenuPrincipalActivity.class);
                    startActivity(it);
                    finish();

                }
                else{
                    AlertDialog.Builder alerta = new AlertDialog.Builder(ListaFuncActivity.this);
                    alerta.setTitle("ATENÇÃO");
                    alerta.setMessage("POR FAVOR! SELECIONE O(S) COLABORE(S) DA TURMA.");
                    alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // TODO Auto-generated method stub

                        }
                    });
                    alerta.show();
                }

                funcSelectedList.clear();

            }
        });

    }

    public void onBackPressed()  {
    }

}
