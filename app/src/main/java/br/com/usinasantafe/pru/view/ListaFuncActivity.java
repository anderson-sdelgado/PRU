package br.com.usinasantafe.pru.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pru.PRUContext;
import br.com.usinasantafe.pru.R;
import br.com.usinasantafe.pru.model.bean.estaticas.FuncBean;

public class ListaFuncActivity extends ActivityGeneric {

    private ListView funcListView;
    private PRUContext pruContext;
    private List<FuncBean> funcList;
    private AdapterListChoice adapterListChoice;
    private ArrayList<ViewHolderChoice> itens;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_funcionario);

        pruContext = (PRUContext) getApplication();

        Button buttonDesmarcarTodos = (Button) findViewById(R.id.buttonDesmarcarTodos);
        Button buttonMarcarTodos = (Button) findViewById(R.id.buttonMarcarTodos);
        Button buttonRetListaFunc = (Button) findViewById(R.id.buttonRetListaFunc);
        Button buttonSalvarListaFunc = (Button) findViewById(R.id.buttonSalvarListaFunc);

        itens = new ArrayList<>();

        funcList =  pruContext.getRuricolaCTR().getFuncBDTurmaList();

        int qtde = 0;
        for (FuncBean funcBean : funcList) {
            ViewHolderChoice viewHolderChoice = new ViewHolderChoice();
            if(funcBean.getTipoAlocaFunc() == 1){
                viewHolderChoice.setSelected(true);
            }
            else if(funcBean.getTipoAlocaFunc() == 2){
                viewHolderChoice.setSelected(false);
            }
            qtde++;
            viewHolderChoice.setDescrCheckBox(qtde + " - " + funcBean.getNomeFunc());
            itens.add(viewHolderChoice);
        }

        adapterListChoice = new AdapterListChoice(this, itens);
        funcListView = (ListView) findViewById(R.id.listFunc);
        funcListView.setAdapter(adapterListChoice);

        buttonDesmarcarTodos.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                itens.clear();
                for (FuncBean funcBean : funcList) {
                    funcBean.setTipoAlocaFunc(2L);
                    ViewHolderChoice viewHolderChoice = new ViewHolderChoice();
                    viewHolderChoice.setSelected(false);
                    viewHolderChoice.setDescrCheckBox(funcBean.getNomeFunc());
                    itens.add(viewHolderChoice);
                }

                adapterListChoice = new AdapterListChoice(ListaFuncActivity.this, itens);
                funcListView = (ListView) findViewById(R.id.listFunc);
                funcListView.setAdapter(adapterListChoice);

            }
        });

        buttonMarcarTodos.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                itens.clear();
                for (FuncBean funcBean : funcList) {
                    funcBean.setTipoAlocaFunc(1L);
                    ViewHolderChoice viewHolderChoice = new ViewHolderChoice();
                    viewHolderChoice.setSelected(true);
                    viewHolderChoice.setDescrCheckBox(funcBean.getNomeFunc());
                    itens.add(viewHolderChoice);
                }

                adapterListChoice = new AdapterListChoice(ListaFuncActivity.this, itens);
                funcListView = (ListView) findViewById(R.id.listFunc);
                funcListView.setAdapter(adapterListChoice);

            }
        });

        buttonRetListaFunc.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if(pruContext.getVerPosTela() == 1) {
                    Intent it = new Intent(ListaFuncActivity.this, MenuMotoMecActivity.class);
                    startActivity(it);
                    finish();
                }
                else if(pruContext.getVerPosTela() == 4) {
                    Intent it = new Intent(ListaFuncActivity.this, MenuMotoMecActivity.class);
                    startActivity(it);
                    finish();
                }

            }
        });

        buttonSalvarListaFunc.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                boolean verSelecao = false;

                for (FuncBean funcBean : funcList) {
                    if(funcBean.getTipoAlocaFunc() == 1){
                        verSelecao = true;
                    }
                }

                if(verSelecao){

                    if(pruContext.getVerPosTela() == 1) {
                        pruContext.getRuricolaCTR().salvarBolAberto(funcList);
                    }
                    else if(pruContext.getVerPosTela() == 4) {
                        pruContext.getRuricolaCTR().alocaFunc(funcList);
                    }

                    funcList.clear();
                    Intent it = new Intent(ListaFuncActivity.this, MenuMotoMecActivity.class);
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

                        }
                    });
                    alerta.show();
                }

            }
        });

    }

    public void onBackPressed()  {
    }

}
