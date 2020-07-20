package br.com.usinasantafe.pru;

import android.app.Application;

import br.com.usinasantafe.pru.control.ConfigCTR;
import br.com.usinasantafe.pru.control.FitoCTR;
import br.com.usinasantafe.pru.control.RuricolaCTR;

public class PRUContext extends Application {

    private ConfigCTR configCTR;
    private int verVerPosTela;
    //1 - Inicio do Boletim;
    //2 - Trabalhando;
    //3 - Parada;
    //4 - Aloca Funcionario
    //5 - Inicio de Fito Com Questao Cabec
    //6 - Inicio do Ponto
    //7 - Altera Questão
    public static String versaoAplic = "1.0";
    private String verAtualCL;
    private RuricolaCTR ruricolaCTR;
    private FitoCTR fitoCTR;
    private Long posPonto;

    @Override
    public void onCreate() {
        // TODO Auto-generated method stub
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

    public int getVerPosTela() {
        return verVerPosTela;
    }

    public void setVerPosTela(int verPosTelaPrinc) {
        this.verVerPosTela = verPosTelaPrinc;
    }

    public Long getPosPonto() {
        return posPonto;
    }

    public void setPosPonto(Long posPonto) {
        this.posPonto = posPonto;
    }

}
