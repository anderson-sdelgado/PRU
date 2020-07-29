package br.com.usinasantafe.pru;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class EquipActivity extends ActivityGeneric {

    private PRUContext pruContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equip);

        pruContext = (PRUContext) getApplication();

        Button buttonOkEquip = (Button) findViewById(R.id.buttonOkPadrao);
        Button buttonCancEquip = (Button) findViewById(R.id.buttonCancPadrao);

        buttonOkEquip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!editTextPadrao.getText().toString().equals("")) {

                    Long codEquip = Long.parseLong(editTextPadrao.getText().toString());

                    if (pruContext.getVerPosTela() == 8) {

                        if (pruContext.getPerdaCTR().verEquip(codEquip)) {

                            pruContext.getPerdaCTR().salvarCabecPerdaAberto(codEquip);
                            Intent it = new Intent(EquipActivity.this, ListaAmostraPerdaActivity.class);
                            startActivity(it);
                            finish();

                        } else {

                            AlertDialog.Builder alerta = new AlertDialog.Builder(EquipActivity.this);
                            alerta.setTitle("ATENÇÃO");
                            alerta.setMessage("EQUIPAMENTO INEXISTENTE!");

                            alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    editTextPadrao.setText("");
                                }
                            });
                            alerta.show();

                        }

                    } else if (pruContext.getVerPosTela() == 11) {

                        if (pruContext.getSoqueiraCTR().verEquip(codEquip)) {

                            pruContext.getSoqueiraCTR().salvarCabecSoqueiraAberto(codEquip);
                            Intent it = new Intent(EquipActivity.this, ListaAmostraSoqueiraActivity.class);
                            startActivity(it);
                            finish();

                        } else {

                            AlertDialog.Builder alerta = new AlertDialog.Builder(EquipActivity.this);
                            alerta.setTitle("ATENÇÃO");
                            alerta.setMessage("EQUIPAMENTO INEXISTENTE!");

                            alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    editTextPadrao.setText("");
                                }
                            });
                            alerta.show();

                        }

                    }

                }

            }
        });


        buttonCancEquip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(editTextPadrao.getText().toString().length() > 0){
                    editTextPadrao.setText(editTextPadrao.getText().toString().substring(0, editTextPadrao.getText().toString().length() - 1));
                }

            }
        });

    }

    public void onBackPressed()  {
    }

}