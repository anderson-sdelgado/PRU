package br.com.usinasantafe.pru;

import android.app.Application;

import br.com.usinasantafe.pru.to.tb.variaveis.ApontTO;
import br.com.usinasantafe.pru.to.tb.variaveis.BoletimTO;

public class PRUContext extends Application {

    private BoletimTO boletimTO;
    private ApontTO apontamentoTO;
    private int verPosTelaPrinc;  //1 - Inicio do Boletim; 2 - Trabalhando; 3 - Parada; 4 - Aloca Funcionario
    public static String versaoAplic = "1.0";

    @Override
    public void onCreate() {
        // TODO Auto-generated method stub
        super.onCreate();
    }

    public ApontTO getApontamentoTO() {
        if (apontamentoTO == null)
            apontamentoTO = new ApontTO();
        return apontamentoTO;
    }

    public BoletimTO getBoletimTO() {
        if (boletimTO == null)
            boletimTO = new BoletimTO();
        return boletimTO;
    }

    public int getVerPosTelaPrinc() {
        return verPosTelaPrinc;
    }

    public void setVerPosTelaPrinc(int verPosTelaPrinc) {
        this.verPosTelaPrinc = verPosTelaPrinc;
    }

}
