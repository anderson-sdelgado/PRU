package br.com.usinasantafe.pru;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.List;

import br.com.usinasantafe.pru.model.bean.variaveis.AlocaFuncBean;
import br.com.usinasantafe.pru.model.bean.variaveis.AmostraPerdaBean;
import br.com.usinasantafe.pru.model.bean.variaveis.AmostraSoqueiraBean;
import br.com.usinasantafe.pru.model.bean.variaveis.ApontRuricolaBean;
import br.com.usinasantafe.pru.model.bean.variaveis.BoletimRuricolaBean;
import br.com.usinasantafe.pru.model.bean.variaveis.CabecFitoBean;
import br.com.usinasantafe.pru.model.bean.variaveis.CabecPerdaBean;
import br.com.usinasantafe.pru.model.bean.variaveis.CabecSoqueiraBean;
import br.com.usinasantafe.pru.model.bean.variaveis.RespFitoBean;
import br.com.usinasantafe.pru.model.pst.DatabaseHelper;
import br.com.usinasantafe.pru.util.EnvioDadosServ;
import br.com.usinasantafe.pru.util.Tempo;

public class ReceberAlarme extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        if(DatabaseHelper.getInstance() == null){
            new DatabaseHelper(context);
        }

        Log.i("PRU", "RODANDO");

        exibir();

        if(Tempo.getInstance().getDatahora() != null) {
            Tempo.getInstance().getDatahora().setTime(Tempo.getInstance().getDatahora().getTime() + 60000L);
        }

        Log.i("PRU", "DATA HORA = " + Tempo.getInstance().data());
        if(EnvioDadosServ.getInstance().verifDados()){
            Log.i("PRU", "ENVIANDO");
            EnvioDadosServ.getInstance().envioDados(context);
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
            Log.i("PRU", "idLiderBoletim = " + boletimRuricolaBean.getIdLiderBol());
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
            Log.i("PRU", "funcAponta = " + apontRuricolaBean.getFuncApont());
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

        CabecFitoBean cabecFitoBean = new CabecFitoBean();
        List cabecFitoList = cabecFitoBean.all();

        for (int l = 0; l < cabecFitoList.size(); l++) {
            cabecFitoBean = (CabecFitoBean) cabecFitoList.get(l);
            Log.i("PRU", "CABEC FITO");
            Log.i("PRU", "idCabecFito = " + cabecFitoBean.getIdCabecFito());
            Log.i("PRU", "auditorCabecFito = " + cabecFitoBean.getAuditorCabecFito());
            Log.i("PRU", "dtCabecFito = " + cabecFitoBean.getDthrCabecFito());
            Log.i("PRU", "osCabecFito = " + cabecFitoBean.getOSCabecFito());
            Log.i("PRU", "talhaoCabecFito = " + cabecFitoBean.getTalhaoCabecFito());
            Log.i("PRU", "idOrgCabecFito = " + cabecFitoBean.getIdOrgCabecFito());
            Log.i("PRU", "idCaracOrgCabecFito = " + cabecFitoBean.getIdCaracOrgCabecFito());
            Log.i("PRU", "ultPontoCabecFito = " + cabecFitoBean.getUltPontoCabecFito());
            Log.i("PRU", "statusCabecFito = " + cabecFitoBean.getStatusCabecFito());
        }

        RespFitoBean respFitoBean = new RespFitoBean();
        List respFistList = respFitoBean.all();

        for (int l = 0; l < respFistList.size(); l++) {
            respFitoBean = (RespFitoBean) respFistList.get(l);
            Log.i("PRU", "RESP FITO");
            Log.i("PRU", "idRespFito = " + respFitoBean.getIdRespFito());
            Log.i("PRU", "idCabecRespFito = " + respFitoBean.getIdCabecRespFito());
            Log.i("PRU", "idAmostraRespFito = " + respFitoBean.getIdAmostraRespFito());
            Log.i("PRU", "pontoRespFito = " + respFitoBean.getPontoRespFito());
            Log.i("PRU", "valorRespFito = " + respFitoBean.getValorRespFito());
            Log.i("PRU", "tipoRespFito = " + respFitoBean.getTipoRespFito());
            Log.i("PRU", "statusRespFito = " + respFitoBean.getStatusRespFito());
        }

        CabecPerdaBean cabecPerdaBean = new CabecPerdaBean();
        List cabecPerdaList = cabecPerdaBean.all();

        for (int l = 0; l < cabecPerdaList.size(); l++) {
            cabecPerdaBean = (CabecPerdaBean) cabecPerdaList.get(l);
            Log.i("PRU", "CABEC PERDA");
            Log.i("PRU", "idCabecPerda = " + cabecPerdaBean.getIdCabecPerda());
            Log.i("PRU", "tipoColheitaCabecPerda = " + cabecPerdaBean.getTipoColheitaCabecPerda());
            Log.i("PRU", "auditorCabecPerda = " + cabecPerdaBean.getAuditorCabecPerda());
            Log.i("PRU", "osCabecPerda = " + cabecPerdaBean.getOsCabecPerda());
            Log.i("PRU", "equipCabecPerda = " + cabecPerdaBean.getEquipCabecPerda());
            Log.i("PRU", "statusCabecPerda = " + cabecPerdaBean.getStatusCabecPerda());
        }

        AmostraPerdaBean amostraPerdaBean = new AmostraPerdaBean();
        List amostraPerdaList = amostraPerdaBean.all();

        for (int l = 0; l < amostraPerdaList.size(); l++) {
            amostraPerdaBean = (AmostraPerdaBean) amostraPerdaList.get(l);
            Log.i("PRU", "AMOSTRA PERDA");
            Log.i("PRU", "idAmostraPerda = " + amostraPerdaBean.getIdAmostraPerda());
            Log.i("PRU", "idCabecAmostraPerda = " + amostraPerdaBean.getIdCabecAmostraPerda());
            Log.i("PRU", "seqAmostraPerda = " + amostraPerdaBean.getSeqAmostraPerda());
            Log.i("PRU", "taraAmostraPerda = " + amostraPerdaBean.getTaraAmostraPerda());
            Log.i("PRU", "toleteAmostraPerda = " + amostraPerdaBean.getToleteAmostraPerda());
            Log.i("PRU", "canaInteiraAmostraPerda = " + amostraPerdaBean.getCanaInteiraAmostraPerda());
            Log.i("PRU", "tocoAmostraPerda = " + amostraPerdaBean.getTocoAmostraPerda());
            Log.i("PRU", "pedacoAmostraPerda = " + amostraPerdaBean.getPedacoAmostraPerda());
            Log.i("PRU", "ponteiroAmostraPerda = " + amostraPerdaBean.getPonteiroAmostraPerda());
            Log.i("PRU", "lascasAmostraPerda = " + amostraPerdaBean.getLascasAmostraPerda());
            Log.i("PRU", "repiqueAmostraPerda = " + amostraPerdaBean.getRepiqueAmostraPerda());
            Log.i("PRU", "pedraAmostraPerda = " + amostraPerdaBean.getPedraAmostraPerda());
            Log.i("PRU", "tocoArvoreAmostraPerda = " + amostraPerdaBean.getTocoArvoreAmostraPerda());
            Log.i("PRU", "plantaDaninhasAmostraPerda = " + amostraPerdaBean.getPlantaDaninhasAmostraPerda());
            Log.i("PRU", "formigueiroAmostraPerda = " + amostraPerdaBean.getFormigueiroAmostraPerda());
        }

        CabecSoqueiraBean cabecSoqueiraBean = new CabecSoqueiraBean();
        List cabecSoqueiraList = cabecSoqueiraBean.all();

        for (int l = 0; l < cabecSoqueiraList.size(); l++) {
            cabecSoqueiraBean = (CabecSoqueiraBean) cabecSoqueiraList.get(l);
            Log.i("PRU", "CABEC SOQUEIRA");
            Log.i("PRU", "idCabecSoqueira = " + cabecSoqueiraBean.getIdCabecSoqueira());
            Log.i("PRU", "auditorCabecSoqueira = " + cabecSoqueiraBean.getAuditorCabecSoqueira());
            Log.i("PRU", "osCabecSoqueira = " + cabecSoqueiraBean.getOsCabecSoqueira());
            Log.i("PRU", "equipCabecSoqueira = " + cabecSoqueiraBean.getEquipCabecSoqueira());
            Log.i("PRU", "statusCabecSoqueira = " + cabecSoqueiraBean.getStatusCabecSoqueira());
        }

        AmostraSoqueiraBean amostraSoqueiraBean = new AmostraSoqueiraBean();
        List amostraSoqueiraList = amostraSoqueiraBean.all();

        for (int l = 0; l < amostraSoqueiraList.size(); l++) {
            amostraSoqueiraBean = (AmostraSoqueiraBean) amostraSoqueiraList.get(l);
            Log.i("PRU", "AMOSTRA SOQUEIRA");
            Log.i("PRU", "idAmostraSoqueira = " + amostraSoqueiraBean.getIdAmostraSoqueira());
            Log.i("PRU", "idCabecAmostraSoqueira = " + amostraSoqueiraBean.getIdCabecAmostraSoqueira());
            Log.i("PRU", "seqAmostraSoqueira = " + amostraSoqueiraBean.getSeqAmostraSoqueira());
            Log.i("PRU", "qtdeSoqueira = " + amostraSoqueiraBean.getQtdeSoqueira());
            Log.i("PRU", "qtdeArranquio = " + amostraSoqueiraBean.getQtdeArranquio());
        }

    }

}
