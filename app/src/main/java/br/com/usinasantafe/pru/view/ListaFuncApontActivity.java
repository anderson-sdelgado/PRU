package br.com.usinasantafe.pru.view;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pru.PRUContext;
import br.com.usinasantafe.pru.R;
import br.com.usinasantafe.pru.model.bean.estaticas.FuncBean;

public class ListaFuncApontActivity extends ActivityGeneric {

    private ArrayList<ViewHolderChoice> itens;
    private AdapterListChoice adapterListChoice;
    private ListView funcListView;
    private List<FuncBean> funcList;
    private PRUContext pruContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_func_apont);

        Button buttonDesmarcarTodosFuncApont = findViewById(R.id.buttonDesmarcarTodosFuncApont);
        Button buttonMarcarTodosFuncApont = findViewById(R.id.buttonMarcarTodosFuncApont);
        Button buttonRetFuncApont = findViewById(R.id.buttonRetFuncApont);
        Button buttonSalvarFuncApont = findViewById(R.id.buttonSalvarFuncApont);

        pruContext = (PRUContext) getApplication();
        itens = new ArrayList<>();

        funcList =  pruContext.getRuricolaCTR().getFuncAlocTurmaList();

        int qtde = 0;
        for (FuncBean funcBean : funcList) {
            ViewHolderChoice viewHolderChoice = new ViewHolderChoice();
            viewHolderChoice.setSelected(false);
            qtde++;
            viewHolderChoice.setDescrCheckBox(qtde + " - " + funcBean.getNomeFunc());
            itens.add(viewHolderChoice);
        }

        adapterListChoice = new AdapterListChoice(this, itens);
        funcListView = (ListView) findViewById(R.id.listFuncApont);
        funcListView.setAdapter(adapterListChoice);

        buttonDesmarcarTodosFuncApont.setOnClickListener(v -> {

            itens.clear();
            for (FuncBean funcBean : funcList) {
                ViewHolderChoice viewHolderChoice = new ViewHolderChoice();
                viewHolderChoice.setSelected(false);
                viewHolderChoice.setDescrCheckBox(funcBean.getNomeFunc());
                itens.add(viewHolderChoice);
            }

            adapterListChoice = new AdapterListChoice(ListaFuncApontActivity.this, itens);
            funcListView = (ListView) findViewById(R.id.listFuncApont);
            funcListView.setAdapter(adapterListChoice);

        });

        buttonMarcarTodosFuncApont.setOnClickListener(v -> {

            itens.clear();
            for (FuncBean funcBean : funcList) {
                ViewHolderChoice viewHolderChoice = new ViewHolderChoice();
                viewHolderChoice.setSelected(true);
                viewHolderChoice.setDescrCheckBox(funcBean.getNomeFunc());
                itens.add(viewHolderChoice);
            }

            adapterListChoice = new AdapterListChoice(ListaFuncApontActivity.this, itens);
            funcListView = (ListView) findViewById(R.id.listFuncApont);
            funcListView.setAdapter(adapterListChoice);

        });

        buttonRetFuncApont.setOnClickListener(v -> {

            if(pruContext.getVerPosTela() == 2L){
                Intent it = new Intent(ListaFuncApontActivity.this, ListaAtividadeActivity.class);
                startActivity(it);
                finish();
            } else if(pruContext.getVerPosTela() == 3L){
                Intent it = new Intent(ListaFuncApontActivity.this, ListaParadaActivity.class);
                startActivity(it);
                finish();
            }

        });

        buttonSalvarFuncApont.setOnClickListener(v -> {

            ArrayList<FuncBean> funcSelectedList = new ArrayList();

            for (int i = 0; i < itens.size(); i++) {
                ViewHolderChoice viewHolderChoice = itens.get(i);
                if(viewHolderChoice.isSelected()){
                    FuncBean funcBean = (FuncBean) funcList.get(i);
                    funcSelectedList.add(funcBean);
                }

            }

            if(funcSelectedList.size() > 0){

                Long idFuncRet = pruContext.getRuricolaCTR().verApont(funcSelectedList);

                if(idFuncRet == 0L){

                    pruContext.getRuricolaCTR().salvaApont(funcSelectedList);

                    funcSelectedList.clear();

                    Intent it = new Intent(ListaFuncApontActivity.this, MenuApontActivity.class);
                    startActivity(it);
                    finish();

                } else {

                    funcSelectedList.clear();

                    FuncBean funcBean = pruContext.getRuricolaCTR().getFuncId(idFuncRet);

                    AlertDialog.Builder alerta = new AlertDialog.Builder( ListaFuncApontActivity.this);
                    alerta.setTitle("ATENÇÃO");
                    alerta.setMessage("OPERAÇÃO/PARADA JÁ APONTADA PARA O COLABORADOR: " + funcBean.getNomeFunc() + "!");
                    alerta.setPositiveButton("OK", (dialog, which) -> {

                    });
                    alerta.show();

                }

            } else {

                funcSelectedList.clear();

                AlertDialog.Builder alerta = new AlertDialog.Builder( ListaFuncApontActivity.this);
                alerta.setTitle("ATENÇÃO");
                alerta.setMessage("POR FAVOR! SELECIONE O(S) COLABOR(ES) DA TURMA.");
                alerta.setPositiveButton("OK", (dialog, which) -> {
                });
                alerta.show();

            }

        });

    }

    public void onBackPressed()  {
    }

}
