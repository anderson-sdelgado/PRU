package br.com.usinasantafe.pru;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.List;

import br.com.usinasantafe.pru.model.bean.variaveis.AlocaFuncBean;
import br.com.usinasantafe.pru.model.bean.variaveis.ApontRuricolaBean;
import br.com.usinasantafe.pru.model.bean.variaveis.BoletimRuricolaBean;
import br.com.usinasantafe.pru.util.ConnectNetwork;
import br.com.usinasantafe.pru.util.EnvioDadosServ;
import br.com.usinasantafe.pru.view.ActivityGeneric;

public class NetworkChangeListerner extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if(ConnectNetwork.isConnected(context)){
            ActivityGeneric.connectNetwork = true;
            if (EnvioDadosServ.status == 1) {
                EnvioDadosServ.getInstance().envioDados();
            }
        } else {
            ActivityGeneric.connectNetwork = false;
        }

    }

    public void exibir(){

        BoletimRuricolaBean boletimRuricolaBean = new BoletimRuricolaBean();
        List boletimList = boletimRuricolaBean.all();

        Log.i("PRU", "AKI");

        for (int i = 0; i < boletimList.size(); i++) {

            boletimRuricolaBean = (BoletimRuricolaBean) boletimList.get(i);
            Log.i("PRU", "BOLETIM");
            Log.i("PRU", "idBoletim = " + boletimRuricolaBean.getIdBol());
            Log.i("PRU", "idExtBoletim = " + boletimRuricolaBean.getIdExtBol());
            Log.i("PRU", "idLiderBoletim = " + boletimRuricolaBean.getMatricLiderBol());
            Log.i("PRU", "idTurmaBoletim = " + boletimRuricolaBean.getIdTurmaBol());
            Log.i("PRU", "osBoletim = " + boletimRuricolaBean.getOsBol());
            Log.i("PRU", "ativPrincBoletim = " + boletimRuricolaBean.getAtivPrincBol());
            Log.i("PRU", "dthrInicioBoletim = " + boletimRuricolaBean.getDthrInicioBol());
            Log.i("PRU", "dthrFimBoletim = " + boletimRuricolaBean.getDthrFimBol());
            Log.i("PRU", "statusBoletim = " + boletimRuricolaBean.getStatusBol());

        }

        ApontRuricolaBean apontRuricolaBean = new ApontRuricolaBean();
        List apontaList = apontRuricolaBean.all();

        for (int i = 0; i < apontaList.size(); i++) {

            apontRuricolaBean = (ApontRuricolaBean) apontaList.get(i);
            Log.i("PRU", "APONTAMENTO");
            Log.i("PRU", "idAponta = " + apontRuricolaBean.getIdApont());
            Log.i("PRU", "idBolAponta = " + apontRuricolaBean.getIdBolApont());
            Log.i("PRU", "idExtBolAponta = " + apontRuricolaBean.getIdExtBolApont());
            Log.i("PRU", "osAponta = " + apontRuricolaBean.getOsApont());
            Log.i("PRU", "atividadeAponta = " + apontRuricolaBean.getAtivApont());
            Log.i("PRU", "paradaAponta = " + apontRuricolaBean.getParadaApont());
            Log.i("PRU", "funcAponta = " + apontRuricolaBean.getMatricFuncApont());
            Log.i("PRU", "dthrAponta = " + apontRuricolaBean.getDthrApont());

        }

        AlocaFuncBean alocaFuncBean = new AlocaFuncBean();
        List alocaFuncList = alocaFuncBean.all();

        for (int l = 0; l < alocaFuncList.size(); l++) {
            alocaFuncBean = (AlocaFuncBean) alocaFuncList.get(l);
            Log.i("PRU", "ALOCA FUNC");
            Log.i("PRU", "idAlocaFunc = " + alocaFuncBean.getIdAlocaFunc());
            Log.i("PRU", "idBolAlocaFunc = " + alocaFuncBean.getIdBolAlocaFunc());
            Log.i("PRU", "idExtBolAlocaFunc = " + alocaFuncBean.getIdExtBolAlocaFunc());
            Log.i("PRU", "codFuncionarioAlocaFunc = " + alocaFuncBean.getMatricFuncAlocaFunc());
            Log.i("PRU", "dthrAlocaFunc = " + alocaFuncBean.getDthrAlocaFunc());
            Log.i("PRU", "tipoAlocaFunc = " + alocaFuncBean.getTipoAlocaFunc());
        }

    }

}
