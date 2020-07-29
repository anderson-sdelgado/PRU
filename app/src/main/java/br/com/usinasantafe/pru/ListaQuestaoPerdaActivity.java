package br.com.usinasantafe.pru;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.j256.ormlite.field.DatabaseField;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pru.model.bean.variaveis.AmostraPerdaBean;
import br.com.usinasantafe.pru.model.bean.variaveis.RespFitoBean;

public class ListaQuestaoPerdaActivity extends ActivityGeneric {

    private PRUContext pruContext;
    private ListView questaoListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_questao_perda);

        TextView textViewTituloAmostra = (TextView) findViewById(R.id.textViewTituloAmostra);
        Button buttonListaQuestaoRetornar =  (Button) findViewById(R.id.buttonListaQuestaoRetornar);
        Button buttonListaQuestaoExcluir =  (Button) findViewById(R.id.buttonListaQuestaoExcluir);

        pruContext = (PRUContext) getApplication();

        Long pos = pruContext.getPosPontoAmostra() + 1;
        textViewTituloAmostra.setText("AMOSTRA " + pos);

        ArrayList<String> itens = new ArrayList<String>();

        AmostraPerdaBean amostraPerdaBean = pruContext.getPerdaCTR().getAmostraPerda(pos);

        itens.add("TARA = " + amostraPerdaBean.getTaraAmostraPerda());
        itens.add("TOLETE = " + amostraPerdaBean.getToleteAmostraPerda());
        itens.add("CANA INTEIRA = " + amostraPerdaBean.getCanaInteiraAmostraPerda());
        itens.add("TOCO = " + amostraPerdaBean.getTocoAmostraPerda());
        itens.add("PEDAÇO = " + amostraPerdaBean.getPedacoAmostraPerda());
        itens.add("PONTEIRO = " + amostraPerdaBean.getPonteiroAmostraPerda());
        itens.add("LASCAS = " + amostraPerdaBean.getLascasAmostraPerda());
        itens.add("PEDAÇO = " + amostraPerdaBean.getPedacoAmostraPerda());
        itens.add("REPIQUE = " + amostraPerdaBean.getRepiqueAmostraPerda());
        itens.add("OBSERVAÇÃO" +
                "" + ((amostraPerdaBean.getPedraAmostraPerda() == 1) ? "\nPEDRA" : "") +
                "" + ((amostraPerdaBean.getTocoArvoreAmostraPerda() == 1) ? "\nTOCO ARVORE" : "") +
                "" + ((amostraPerdaBean.getPlantaDaninhasAmostraPerda() == 1) ? "\nPLANTA DANINHAS" : "") +
                "" + ((amostraPerdaBean.getFormigueiroAmostraPerda() == 1) ? "\nFORMIGUEIROS" : ""));

        AdapterList adapterList = new AdapterList(this, itens);
        questaoListView = (ListView) findViewById(R.id.listViewQuestao);
        questaoListView.setAdapter(adapterList);

        questaoListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> l, View v, int position,
                                    long id) {

                pruContext.setVerPosTela(10);
                if(position < 8) {
                    pruContext.setPosQuestao(position + 1);
                    Intent it = new Intent(ListaQuestaoPerdaActivity.this, QuestaoPerdaActivity.class);
                    startActivity(it);
                    finish();
                }
                else{
                    Intent it = new Intent(ListaQuestaoPerdaActivity.this, ListaObsActivity.class);
                    startActivity(it);
                    finish();
                }

            }

        });

        buttonListaQuestaoRetornar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent it = new Intent(ListaQuestaoPerdaActivity.this, ListaAmostraPerdaActivity.class);
                startActivity(it);
                finish();

            }
        });

        buttonListaQuestaoExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder alerta = new AlertDialog.Builder(ListaQuestaoPerdaActivity.this);
                alerta.setTitle("ATENÇÃO");
                alerta.setMessage("DESEJAR REALMENTE EXCLUIR ESSA AMOSTRA?");

                alerta.setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        pruContext.getPerdaCTR().delAmostraPerda(pruContext.getPosPontoAmostra());
                        Intent it = new Intent(ListaQuestaoPerdaActivity.this, ListaAmostraPerdaActivity.class);
                        startActivity(it);
                        finish();

                    }

                });

                alerta.setNegativeButton("NÃO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }

                });

                alerta.show();

            }
        });

    }
}