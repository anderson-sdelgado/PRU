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

import br.com.usinasantafe.pru.util.EnvioDadosServ;

public class ListaFuncApontActivity extends ActivityGeneric {

    private ArrayList<ViewHolderChoice> itens;
    private AdapterListChoice adapterListChoice;
    private ListView funcListView;
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

        br.com.usinasantafe.pru.to.tb.variaveis.FuncBoletimBean funcBoletimBean = new br.com.usinasantafe.pru.to.tb.variaveis.FuncBoletimBean();
        List funcBolList = funcBoletimBean.all();
        ArrayList<Long> rLista = new ArrayList<Long>();

        for (int i = 0; i < funcBolList.size(); i++) {
            funcBoletimBean = (br.com.usinasantafe.pru.to.tb.variaveis.FuncBoletimBean) funcBolList.get(i);
            rLista.add(funcBoletimBean.getCodFuncBoletim());
        }

        br.com.usinasantafe.pru.to.tb.estaticas.FuncBean funcBean = new br.com.usinasantafe.pru.to.tb.estaticas.FuncBean();
        funcList = funcBean.inAndOrderBy("codFunc", rLista, "nomeFunc", true);

        for (int i = 0; i < funcList.size(); i++) {
            funcBean = (br.com.usinasantafe.pru.to.tb.estaticas.FuncBean) funcList.get(i);
            ViewHolderChoice viewHolderChoice = new ViewHolderChoice();
            viewHolderChoice.setSelected(false);
            viewHolderChoice.setDescrCheckBox(funcBean.getNomeFunc());
            itens.add(viewHolderChoice);
        }

        adapterListChoice = new AdapterListChoice(this, itens);
        funcListView = (ListView) findViewById(R.id.listFuncAloc);
        funcListView.setAdapter(adapterListChoice);

        buttonDesmarcarTodos.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                itens.clear();
                br.com.usinasantafe.pru.to.tb.estaticas.FuncBean funcBean = new br.com.usinasantafe.pru.to.tb.estaticas.FuncBean();
                for (int i = 0; i < funcList.size(); i++) {
                    funcBean = (br.com.usinasantafe.pru.to.tb.estaticas.FuncBean) funcList.get(i);
                    ViewHolderChoice viewHolderChoice = new ViewHolderChoice();
                    viewHolderChoice.setSelected(false);
                    viewHolderChoice.setDescrCheckBox(funcBean.getNomeFunc());
                    itens.add(viewHolderChoice);
                }

                adapterListChoice = new AdapterListChoice( ListaFuncApontActivity.this, itens);
                funcListView = (ListView) findViewById(R.id.listFuncAloc);
                funcListView.setAdapter(adapterListChoice);

            }
        });

        buttonMarcarTodos.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                itens.clear();
                br.com.usinasantafe.pru.to.tb.estaticas.FuncBean funcBean = new br.com.usinasantafe.pru.to.tb.estaticas.FuncBean();
                for (int i = 0; i < funcList.size(); i++) {
                    funcBean = (br.com.usinasantafe.pru.to.tb.estaticas.FuncBean) funcList.get(i);
                    ViewHolderChoice viewHolderChoice = new ViewHolderChoice();
                    viewHolderChoice.setSelected(true);
                    viewHolderChoice.setDescrCheckBox(funcBean.getNomeFunc());
                    itens.add(viewHolderChoice);
                }

                adapterListChoice = new AdapterListChoice(ListaFuncApontActivity.this, itens);
                funcListView = (ListView) findViewById(R.id.listFuncAloc);
                funcListView.setAdapter(adapterListChoice);

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
                    Intent it = new Intent(ListaFuncApontActivity.this, ListaParadaActivity.class);
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
                        br.com.usinasantafe.pru.to.tb.estaticas.FuncBean funcBean = (br.com.usinasantafe.pru.to.tb.estaticas.FuncBean) funcList.get(i);
                        funcSelectedList.add(funcBean.getCodFunc());
                    }

                }

                if(funcSelectedList.size() > 0){

                    EnvioDadosServ.getInstance().salvaAponta(pruContext.getApontamentoTO(), funcSelectedList);

                    Intent it = new Intent(ListaFuncApontActivity.this, MenuMotoMecActivity.class);
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
