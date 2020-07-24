package br.com.usinasantafe.pru;

import android.app.Application;

import br.com.usinasantafe.pru.control.ConfigCTR;
import br.com.usinasantafe.pru.control.FitoCTR;
import br.com.usinasantafe.pru.control.PerdaColheitaCTR;
import br.com.usinasantafe.pru.control.RuricolaCTR;

public class PRUContext extends Application {

    private ConfigCTR configCTR;
    private int verVerPosTela;
    //1 - Inicio do Boletim;
    //2 - Trabalhando;
    //3 - Parada;
    //4 - Aloca Funcionario
    //5 - Inicio de Fito Com Questao Cabec
    //6 - Inicio do Ponto Fito
    //7 - Altera Questão Fito
    //6 - Inicio do Amostra Perda
    public static String versaoAplic = "1.0";
    private String verAtualCL;
    private RuricolaCTR ruricolaCTR;
    private FitoCTR fitoCTR;
    private PerdaColheitaCTR perdaColheitaCTR;
    private Long posPontoAmostra;
    private int posQuestao;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public ConfigCTR getConfigCTR() {
        if (configCTR == null)
            configCTR = new ConfigCTR();
        return configCTR;
    }

    public RuricolaCTR getRuricolaCTR() {
        if (ruricolaCTR == null)
            ruricolaCTR = new RuricolaCTR();
        return ruricolaCTR;
    }

    public FitoCTR getFitoCTR(){
        if (fitoCTR == null)
            fitoCTR = new FitoCTR();
        return fitoCTR;
    }

    public PerdaColheitaCTR getPerdaColheitaCTR() {
        if (perdaColheitaCTR == null)
            perdaColheitaCTR = new PerdaColheitaCTR();
        return perdaColheitaCTR;
    }

    public int getVerPosTela() {
        return verVerPosTela;
    }

    public void setVerPosTela(int verPosTelaPrinc) {
        this.verVerPosTela = verPosTelaPrinc;
    }

    public Long getPosPontoAmostra() {
        return posPontoAmostra;
    }

    public void setPosPontoAmostra(Long posPontoAmostra) {
        this.posPontoAmostra = posPontoAmostra;
    }

    public int getPosQuestao() {
        return posQuestao;
    }

    public void setPosQuestao(int posQuestao) {
        this.posQuestao = posQuestao;
    }
}
