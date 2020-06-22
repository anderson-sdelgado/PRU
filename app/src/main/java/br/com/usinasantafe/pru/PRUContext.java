package br.com.usinasantafe.pru;

import android.app.Application;

import br.com.usinasantafe.pru.control.ConfigCTR;
import br.com.usinasantafe.pru.control.RuricolaCTR;

public class PRUContext extends Application {

    private ConfigCTR configCTR;
    private int verVerPosTela;  //1 - Inicio do Boletim; 2 - Trabalhando; 3 - Parada; 4 - Aloca Funcionario
    public static String versaoAplic = "1.0";
    private String verAtualCL;
    private RuricolaCTR ruricolaCTR;

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

    public int getVerPosTela() {
        return verVerPosTela;
    }

    public void setVerPosTela(int verPosTelaPrinc) {
        this.verVerPosTela = verPosTelaPrinc;
    }

    public String getVerAtualCL() {
        return verAtualCL;
    }

    public void setVerAtualCL(String verAtualCL) {
        this.verAtualCL = verAtualCL;
    }
}
