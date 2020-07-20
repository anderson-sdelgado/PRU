package br.com.usinasantafe.pru;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

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

        br.com.usinasantafe.pru.to.tb.estaticas.TurmaBean turmaBean = new br.com.usinasantafe.pru.to.tb.estaticas.TurmaBean();
        turmaList = turmaBean.all();

        for (int i = 0; i < turmaList.size(); i++) {
            turmaBean = (br.com.usinasantafe.pru.to.tb.estaticas.TurmaBean) turmaList.get(i);
            itens.add(turmaBean.getDescrTurma());
        }

        AdapterList adapterList = new AdapterList(this, itens);
        lista = (ListView) findViewById(R.id.listTurma);
        lista.setAdapter(adapterList);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> l, View v, int position,
                                    long id) {
                // TODO Auto-generated method stub

                br.com.usinasantafe.pru.to.tb.estaticas.TurmaBean turmaBean = (br.com.usinasantafe.pru.to.tb.estaticas.TurmaBean) turmaList.get(position);

                pruContext.getBoletimBean().setIdTurmaBoletim(turmaBean.getIdTurma());

                Intent it = new Intent(TurmaActivity.this, ListaFuncActivity.class);
                startActivity(it);
                finish();

            }

        });

        buttonRetTurma.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent it = new Intent(TurmaActivity.this, FuncActivity.class);
                startActivity(it);
                finish();
            }
        });

    }

    public void onBackPressed()  {
    }

}
