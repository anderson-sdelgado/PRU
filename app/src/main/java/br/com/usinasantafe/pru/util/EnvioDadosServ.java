package br.com.usinasantafe.pru.util;

import java.util.HashMap;
import java.util.Map;

import android.util.Log;

import br.com.usinasantafe.pru.control.ConfigCTR;
import br.com.usinasantafe.pru.control.RuricolaCTR;
import br.com.usinasantafe.pru.util.connHttp.PostCadGenerico;
import br.com.usinasantafe.pru.util.connHttp.UrlsConexaoHttp;
import br.com.usinasantafe.pru.view.ActivityGeneric;

public class EnvioDadosServ {

    private static EnvioDadosServ instance = null;
    private UrlsConexaoHttp urlsConexaoHttp;
    public static int status; //1 - Enviando; 2 - Existe Dados para Enviar; 3 - Todos os Dados Enviados

    public EnvioDadosServ() {
        urlsConexaoHttp = new UrlsConexaoHttp();
    }

    public static EnvioDadosServ getInstance() {
        if (instance == null) {
            instance = new EnvioDadosServ();
        }
        return instance;
    }

    public void enviarDadosRuricola() {

        Log.i("PRU", "AKI 7");

        ConfigCTR configCTR = new ConfigCTR();
        String dados = configCTR.dadosEnvioRuricola();

        Log.i("PMM", "BOLETIM RURICOLA = " + dados);

        UrlsConexaoHttp urlsConexaoHttp = new UrlsConexaoHttp();

        String[] url = {urlsConexaoHttp.getsInserirRuricola()};
        Map<String, Object> parametrosPost = new HashMap<>();
        parametrosPost.put("dado", dados);

        PostCadGenerico conHttpPostGenerico = new PostCadGenerico();
        conHttpPostGenerico.setParametrosPost(parametrosPost);
        conHttpPostGenerico.execute(url);

    }

    public boolean verifDadosRuricola() {
        ConfigCTR configCTR = new ConfigCTR();
        return configCTR.verifDadosRuricola();
    }

    public void envioDados() {
        status = 1;
        if(ActivityGeneric.connectNetwork) {
            status = 2;
            if (verifDadosRuricola()) {
                enviarDadosRuricola();
            } else {
                status = 3;
            }
        }
    }

    public boolean verifDadosEnvio() {
        if ((!verifDadosRuricola())){
            return false;
        } else {
            return true;
        }
    }

    ////////////////////////////////////MECANISMO RECEBIMENTO/////////////////////////////////////////

    public void recDados(String result){
        if(result.trim().startsWith("GRAVOU-RURICOLA")){
            RuricolaCTR ruricolaCTR = new RuricolaCTR();
            ruricolaCTR.updateDados(result);
        } else {
            status = 1;
        }
    }

}
