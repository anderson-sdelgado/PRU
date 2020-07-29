package br.com.usinasantafe.pru;

import android.app.Application;

import br.com.usinasantafe.pru.control.ConfigCTR;
import br.com.usinasantafe.pru.control.FitoCTR;
import br.com.usinasantafe.pru.control.PerdaCTR;
import br.com.usinasantafe.pru.control.RuricolaCTR;
import br.com.usinasantafe.pru.control.SoqueiraCTR;

public class PRUContext extends Application {

    private ConfigCTR configCTR;
    private int verPosTela;
    //1 - Inicio do Boletim;
    //2 - Trabalhando;
    //3 - Parada;
    //4 - Aloca Funcionario
    //5 - Inicio Questao Cabec Fito
    //6 - Inicio do Ponto Fito
    //7 - Altera Questão Fito
    //8 - Inicio do Amostra Perda
    //9 - Inserir Amostra Perda
    //10 - Altera Amostra Perda
    //11 - Inicio de Amostra Soqueira
    //12 - Inserir Amostra Soqueira
    //13 - Altera Amostra Soqueira

    public static String versaoAplic = "1.0";
    private RuricolaCTR ruricolaCTR;
    private FitoCTR fitoCTR;
    private PerdaCTR perdaCTR;
    private SoqueiraCTR soqueiraCTR;
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

    public PerdaCTR getPerdaCTR() {
        if (perdaCTR == null)
            perdaCTR = new PerdaCTR();
        return perdaCTR;
    }

    public SoqueiraCTR getSoqueiraCTR() {
        if (soqueiraCTR == null)
            soqueiraCTR = new SoqueiraCTR();
        return soqueiraCTR;
    }

    public int getVerPosTela() {
        return verPosTela;
    }

    public void setVerPosTela(int verPosTelaPrinc) {
        this.verPosTela = verPosTelaPrinc;
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
