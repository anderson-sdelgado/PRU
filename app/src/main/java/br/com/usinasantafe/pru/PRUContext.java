package br.com.usinasantafe.pru;

import android.app.Application;

import br.com.usinasantafe.pru.control.ConfigCTR;
import br.com.usinasantafe.pru.control.RuricolaCTR;

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
    //14 - Parada Fito
    //15 - Parada Perda
    //16 - Relatorio Ruricola
    //17 - Relatorio Fito
    //18 - Relatorio Perda
    //19 - Relatorio Soqueira
    private RuricolaCTR ruricolaCTR;
    private Long posPontoAmostra;
    private int posQuestao;
    private int posCabec;
    private Long id;

    public static String versaoWS = "3.04";

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

    public int getPosCabec() {
        return posCabec;
    }

    public void setPosCabec(int posCabec) {
        this.posCabec = posCabec;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
