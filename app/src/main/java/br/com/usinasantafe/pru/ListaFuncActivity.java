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

public class ListaFuncActivity extends ActivityGeneric {

    private ListView lista;
    private PRUContext pruContext;
    private List funcList;
    private AdapterListChoice adapterListChoice;
    private ArrayList<ViewHolderChoice> itens;
    private br.com.usinasantafe.pru.to.tb.variaveis.ConfigBean configBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_funcionario);

        pruContext = (PRUContext) getApplication();

        Button buttonDesmarcarTodos = (Button) findViewById(R.id.buttonDesmarcarTodos);
        Button buttonMarcarTodos = (Button) findViewById(R.id.buttonMarcarTodos);
        Button buttonRetListaFunc = (Button) findViewById(R.id.buttonRetListaFunc);
        Button buttonSalvarListaFunc = (Button) findViewById(R.id.buttonSalvarListaFunc);

        configBean = new br.com.usinasantafe.pru.to.tb.variaveis.ConfigBean();
        List configList = configBean.all();
        configBean = (br.com.usinasantafe.pru.to.tb.variaveis.ConfigBean) configList.get(0);

        itens = new ArrayList<ViewHolderChoice>();

        br.com.usinasantafe.pru.to.tb.estaticas.FuncBean funcBean = new br.com.usinasantafe.pru.to.tb.estaticas.FuncBean();
        funcList =  funcBean.getAndOrderBy("idTurma", configBean.getIdTurma(), "nomeFunc", true);

        if(pruContext.getVerPosTela() == 1) {

            for (int i = 0; i < funcList.size(); i++) {
                funcBean = (br.com.usinasantafe.pru.to.tb.estaticas.FuncBean) funcList.get(i);
                ViewHolderChoice viewHolderChoice = new ViewHolderChoice();
                viewHolderChoice.setSelected(false);
                viewHolderChoice.setDescrCheckBox(funcBean.getNomeFunc());
                itens.add(viewHolderChoice);
            }

        }
        else if(pruContext.getVerPosTela() == 4) {

            for (int i = 0; i < funcList.size(); i++) {
                funcBean = (br.com.usinasantafe.pru.to.tb.estaticas.FuncBean) funcList.get(i);
                br.com.usinasantafe.pru.to.tb.variaveis.FuncBoletimBean funcBoletimBean = new br.com.usinasantafe.pru.to.tb.variaveis.FuncBoletimBean();
                List funcBoletimList = funcBoletimBean.get("codFuncBoletim", funcBean.getCodFunc());
                ViewHolderChoice viewHolderChoice = new ViewHolderChoice();
                if(funcBoletimList.size() == 0) {
                    viewHolderChoice.setSelected(false);
                }
                else{
                    viewHolderChoice.setSelected(true);
                }
                viewHolderChoice.setDescrCheckBox(funcBean.getNomeFunc());
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
                br.com.usinasantafe.pru.to.tb.estaticas.FuncBean funcBean = new br.com.usinasantafe.pru.to.tb.estaticas.FuncBean();
                for (int i = 0; i < funcList.size(); i++) {
                    funcBean = (br.com.usinasantafe.pru.to.tb.estaticas.FuncBean) funcList.get(i);
                    ViewHolderChoice viewHolderChoice = new ViewHolderChoice();
                    viewHolderChoice.setSelected(false);
                    viewHolderChoice.setDescrCheckBox(funcBean.getNomeFunc());
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
                br.com.usinasantafe.pru.to.tb.estaticas.FuncBean funcBean = new br.com.usinasantafe.pru.to.tb.estaticas.FuncBean();
                for (int i = 0; i < funcList.size(); i++) {
                    funcBean = (br.com.usinasantafe.pru.to.tb.estaticas.FuncBean) funcList.get(i);
                    ViewHolderChoice viewHolderChoice = new ViewHolderChoice();
                    viewHolderChoice.setSelected(true);
                    viewHolderChoice.setDescrCheckBox(funcBean.getNomeFunc());
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

                    if(pruContext.getVerPosTela() == 1) {

                        pruContext.getBoletimBean().setIdLiderBoletim(configBean.getCodFunc());
                        EnvioDadosServ.getInstance().salvaBoletimAberto(pruContext.getBoletimBean());

                        br.com.usinasantafe.pru.to.tb.variaveis.FuncBoletimBean funcBoletimBean = new br.com.usinasantafe.pru.to.tb.variaveis.FuncBoletimBean();
                        funcBoletimBean.deleteAll();

                        for (int i = 0; i < funcSelectedList.size(); i++) {
                            EnvioDadosServ.getInstance().salvaFuncBoletim(funcSelectedList.get(i), 1L);
                            funcBoletimBean.setCodFuncBoletim(funcSelectedList.get(i));
                            funcBoletimBean.insert();
                        }

                    }
                    else if(pruContext.getVerPosTela() == 4) {

                        br.com.usinasantafe.pru.to.tb.variaveis.FuncBoletimBean funcBoletimBean = new br.com.usinasantafe.pru.to.tb.variaveis.FuncBoletimBean();
                        List funcBoletimList = funcBoletimBean.all();

                        for (int i = 0; i < funcBoletimList.size(); i++) {
                            funcBoletimBean = (br.com.usinasantafe.pru.to.tb.variaveis.FuncBoletimBean) funcBoletimList.get(i);
                            int cont = 0;
                            for (int j = 0; j < funcSelectedList.size(); j++) {
                                if(funcBoletimBean.getCodFuncBoletim().equals(funcSelectedList.get(j))){
                                    cont = cont + 1;
                                }
                            }

                            if(cont == 0){
                                EnvioDadosServ.getInstance().salvaFuncBoletim(funcBoletimBean.getCodFuncBoletim(), 2L);
                                funcBoletimBean.delete();
                            }

                        }

                        for (int i = 0; i < funcSelectedList.size(); i++) {
                            int cont = 0;
                            for (int j = 0; j < funcBoletimList.size(); j++) {
                                funcBoletimBean = (br.com.usinasantafe.pru.to.tb.variaveis.FuncBoletimBean) funcBoletimList.get(j);
                                if(funcBoletimBean.getCodFuncBoletim().equals(funcSelectedList.get(i))){
                                    cont = cont + 1;
                                }
                            }

                            if(cont == 0){
                                EnvioDadosServ.getInstance().salvaFuncBoletim(funcSelectedList.get(i), 1L);
                                funcBoletimBean.setCodFuncBoletim(funcSelectedList.get(i));
                                funcBoletimBean.insert();
                            }

                        }

                        funcBoletimList.clear();

                    }


                    EnvioDadosServ.getInstance().envioDadosPrinc();
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
