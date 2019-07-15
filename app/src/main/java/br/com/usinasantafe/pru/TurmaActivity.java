package br.com.usinasantafe.pru;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pru.to.tb.estaticas.TurmaTO;

public class TurmaActivity extends ActivityGeneric {

    private ListView lista;
    private PRUContext pruContext;
    private List turmaList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_turma);

        pruContext = (PRUContext) getApplication();

        Button buttonRetTurma = (Button) findViewById(R.id.buttonRetTurma);

        ArrayList<String> itens = new ArrayList<String>();

        TurmaTO turmaTO = new TurmaTO();
        turmaList = turmaTO.all();

        for (int i = 0; i < turmaList.size(); i++) {
            turmaTO = (TurmaTO) turmaList.get(i);
            itens.add(turmaTO.getDescrTurma());
        }

        AdapterList adapterList = new AdapterList(this, itens);
        lista = (ListView) findViewById(R.id.listTurma);
        lista.setAdapter(adapterList);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> l, View v, int position,
                                    long id) {
                // TODO Auto-generated method stub

                TurmaTO turmaTO = (TurmaTO) turmaList.get(position);

                pruContext.getBoletimTO().setIdTurmaBoletim(turmaTO.getIdTurma());

                Intent it = new Intent(TurmaActivity.this, ListaFuncActivity.class);
                startActivity(it);
                finish();

            }

        });

        buttonRetTurma.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent it = new Intent(TurmaActivity.this, FuncionarioActivity.class);
                startActivity(it);
                finish();
            }
        });

    }

    public void onBackPressed()  {
    }

}
