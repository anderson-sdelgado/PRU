package br.com.usinasantafe.pru.view;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pru.PRUContext;
import br.com.usinasantafe.pru.R;
import br.com.usinasantafe.pru.model.bean.estaticas.FuncBean;

public class ListaFuncAlocActivity extends ActivityGeneric {

    private ListView funcListView;
    private PRUContext pruContext;
    private List<FuncBean> funcList;
    private AdapterListChoice adapterListChoice;
    private ArrayList<ViewHolderChoice> itens;
    private ProgressDialog progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_func_aloca);

        pruContext = (PRUContext) getApplication();

        Button buttonDesmarcarTodosFuncAloc = (Button) findViewById(R.id.buttonDesmarcarTodosFuncAloc);
        Button buttonMarcarTodosFuncAloc = (Button) findViewById(R.id.buttonMarcarTodosFuncAloc);
        Button buttonRetFuncAloc = (Button) findViewById(R.id.buttonRetFuncAloc);
        Button buttonSalvarFuncAloc = (Button) findViewById(R.id.buttonSalvarFuncAloc);
        Button buttonAtualFuncAloc = (Button) findViewById(R.id.buttonAtualFuncAloc);

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

        buttonAtualFuncAloc.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if(pruContext.getVerPosTela() == 1) {

                    progressBar = new ProgressDialog(v.getContext());
                    progressBar.setCancelable(true);
                    progressBar.setMessage("Atualizando Funcionários...");
                    progressBar.show();

                    pruContext.getConfigCTR().atualDadosAlocFunc( ListaFuncAlocActivity.this, ListaFuncAlocActivity.class, progressBar);

                }
                else if(pruContext.getVerPosTela() == 4) {

                    AlertDialog.Builder alerta = new AlertDialog.Builder(ListaFuncAlocActivity.this);
                    alerta.setTitle("ATENÇÃO");
                    alerta.setMessage("ATUALIZAÇÃO DOS FUNCIONÁRIOS SÓ PODEM SER REALIZADA NO INÍCIO DO BOLETIM.");
                    alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    alerta.show();

                }


            }
        });

        adapterListChoice = new AdapterListChoice(this, itens);
        funcListView = (ListView) findViewById(R.id.listFuncAloc);
        funcListView.setAdapter(adapterListChoice);

        buttonDesmarcarTodosFuncAloc.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                itens.clear();
                int qtde = 0;
                for (FuncBean funcBean : funcList) {
                    funcBean.setTipoAlocaFunc(2L);
                    ViewHolderChoice viewHolderChoice = new ViewHolderChoice();
                    viewHolderChoice.setSelected(false);
                    qtde++;
                    viewHolderChoice.setDescrCheckBox(qtde + " - " + funcBean.getNomeFunc());
                    itens.add(viewHolderChoice);
                }

                adapterListChoice = new AdapterListChoice(ListaFuncAlocActivity.this, itens);
                funcListView = (ListView) findViewById(R.id.listFuncAloc);
                funcListView.setAdapter(adapterListChoice);

            }
        });

        buttonMarcarTodosFuncAloc.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                itens.clear();
                int qtde = 0;
                for (FuncBean funcBean : funcList) {
                    funcBean.setTipoAlocaFunc(1L);
                    ViewHolderChoice viewHolderChoice = new ViewHolderChoice();
                    viewHolderChoice.setSelected(true);
                    qtde++;
                    viewHolderChoice.setDescrCheckBox(qtde + " - " + funcBean.getNomeFunc());
                    itens.add(viewHolderChoice);
                }

                adapterListChoice = new AdapterListChoice(ListaFuncAlocActivity.this, itens);
                funcListView = (ListView) findViewById(R.id.listFuncAloc);
                funcListView.setAdapter(adapterListChoice);

            }
        });

        buttonRetFuncAloc.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if(pruContext.getVerPosTela() == 1) {
                    Intent it = new Intent(ListaFuncAlocActivity.this, ListaAtividadeActivity.class);
                    startActivity(it);
                    finish();
                }
                else if(pruContext.getVerPosTela() == 4) {
                    Intent it = new Intent(ListaFuncAlocActivity.this, MenuMotoMecActivity.class);
                    startActivity(it);
                    finish();
                }

            }
        });

        buttonSalvarFuncAloc.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                boolean verSelecao = false;

                for (int i = 0; i < itens.size(); i++) {
                    ViewHolderChoice viewHolderChoice = itens.get(i);
                    FuncBean funcBean = funcList.get(i);
                    if(viewHolderChoice.isSelected()){
                        funcBean.setTipoAlocaFunc(1L);
                        verSelecao = true;
                    }
                    else{
                        funcBean.setTipoAlocaFunc(2L);
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
                    Intent it = new Intent(ListaFuncAlocActivity.this, MenuMotoMecActivity.class);
                    startActivity(it);
                    finish();

                }
                else{

                    AlertDialog.Builder alerta = new AlertDialog.Builder(ListaFuncAlocActivity.this);
                    alerta.setTitle("ATENÇÃO");
                    alerta.setMessage("POR FAVOR! SELECIONE O(S) COLABORADOR(ES) DA TURMA.");
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
