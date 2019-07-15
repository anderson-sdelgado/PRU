package br.com.usinasantafe.pru;

import android.app.Activity;
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
import br.com.usinasantafe.pru.to.tb.variaveis.FuncBoletimTO;

public class ListaFuncApontActivity extends ActivityGeneric {

    private ArrayList<ViewHolderChoice> itens;
    private AdapterListChoice adapterListChoice;
    private ListView lista;
    private List funcList;
    private PRUContext pruContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_func_apont);

        Button buttonDesmarcarTodos = (Button) findViewById(R.id.buttonDesmarcarTodosFuncAloc);
        Button buttonMarcarTodos = (Button) findViewById(R.id.buttonMarcarTodosFuncAloc);
        Button buttonRetListaFunc = (Button) findViewById(R.id.buttonRetFuncAloc);
        Button buttonSalvarListaFunc = (Button) findViewById(R.id.buttonSalvarFuncAloc);

        pruContext = (PRUContext) getApplication();
        itens = new ArrayList<ViewHolderChoice>();

        FuncBoletimTO funcBoletimTO = new FuncBoletimTO();
        List funcBolList = funcBoletimTO.all();
        ArrayList<Long> rLista = new ArrayList<Long>();

        for (int i = 0; i < funcBolList.size(); i++) {
            funcBoletimTO = (FuncBoletimTO) funcBolList.get(i);
            rLista.add(funcBoletimTO.getCodFuncBoletim());
        }

        FuncTO funcTO = new FuncTO();
        funcList = funcTO.inAndOrderBy("codFunc", rLista, "nomeFunc", true);

        for (int i = 0; i < funcList.size(); i++) {
            funcTO = (FuncTO) funcList.get(i);
            ViewHolderChoice viewHolderChoice = new ViewHolderChoice();
            viewHolderChoice.setSelected(false);
            viewHolderChoice.setDescrCheckBox(funcTO.getNomeFunc());
            itens.add(viewHolderChoice);
        }

        adapterListChoice = new AdapterListChoice(this, itens);
        lista = (ListView) findViewById(R.id.listFuncAloc);
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

                adapterListChoice = new AdapterListChoice( ListaFuncApontActivity.this, itens);
                lista = (ListView) findViewById(R.id.listFuncAloc);
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

                adapterListChoice = new AdapterListChoice(ListaFuncApontActivity.this, itens);
                lista = (ListView) findViewById(R.id.listFuncAloc);
                lista.setAdapter(adapterListChoice);

            }
        });

        buttonRetListaFunc.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                if(pruContext.getVerPosTelaPrinc() == 2L){
                    Intent it = new Intent(ListaFuncApontActivity.this, ListaAtivActivity.class);
                    startActivity(it);
                    finish();
                }
                else if(pruContext.getVerPosTelaPrinc() == 3L){
                    Intent it = new Intent(ListaFuncApontActivity.this, MenuParadaActivity.class);
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

                    ManipDadosEnvio.getInstance().salvaAponta(pruContext.getApontamentoTO(), funcSelectedList);

                    Intent it = new Intent(ListaFuncApontActivity.this, MenuPrincipalActivity.class);
                    startActivity(it);
                    finish();

                }
                else{
                    AlertDialog.Builder alerta = new AlertDialog.Builder( ListaFuncApontActivity.this);
                    alerta.setTitle("ATENÇÃO");
                    alerta.setMessage("POR FAVOR! SELECIONE O(S) COLABOR(ES) DA TURMA.");
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
