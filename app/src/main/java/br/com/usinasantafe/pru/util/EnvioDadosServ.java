package br.com.usinasantafe.pru.util;

import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.util.Log;

import br.com.usinasantafe.pru.control.ConfigCTR;
import br.com.usinasantafe.pru.control.FitoCTR;
import br.com.usinasantafe.pru.control.PerdaCTR;
import br.com.usinasantafe.pru.control.RuricolaCTR;
import br.com.usinasantafe.pru.control.SoqueiraCTR;
import br.com.usinasantafe.pru.util.connHttp.PostCadGenerico;
import br.com.usinasantafe.pru.util.connHttp.UrlsConexaoHttp;

public class EnvioDadosServ {

    private static EnvioDadosServ instance = null;
    private UrlsConexaoHttp urlsConexaoHttp;

    private int statusEnvio; //1 - Enviando; 2 - Existe Dados para Enviar; 3 - Todos os Dados Enviados
    private int posEnvio;
    private Context context;

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

        ConexaoWeb conexaoWeb = new ConexaoWeb();
        if (conexaoWeb.verificaConexao(context)) {

            ConfigCTR configCTR = new ConfigCTR();
            String dados = configCTR.dadosEnvioRuricola();

            Log.i("PMM", "BOLETIM RURICOLA = " + dados);

            UrlsConexaoHttp urlsConexaoHttp = new UrlsConexaoHttp();

            String[] url = {urlsConexaoHttp.getsInserirRuricola()};
            Map<String, Object> parametrosPost = new HashMap<String, Object>();
            parametrosPost.put("dado", dados);

            PostCadGenerico conHttpPostGenerico = new PostCadGenerico();
            conHttpPostGenerico.setParametrosPost(parametrosPost);
            conHttpPostGenerico.execute(url);
        }
        else{
            posEnvio = -1;
        }

    }

    public void enviarDadosFito() {

        ConexaoWeb conexaoWeb = new ConexaoWeb();
        if (conexaoWeb.verificaConexao(context)) {

            posEnvio = 2;

            ConfigCTR configCTR = new ConfigCTR();
            String dados = configCTR.dadosEnvioFito();

            Log.i("PMM", "BOLETIM FITO = " + dados);

            UrlsConexaoHttp urlsConexaoHttp = new UrlsConexaoHttp();

            String[] url = {urlsConexaoHttp.getsInserirFito()};
            Map<String, Object> parametrosPost = new HashMap<String, Object>();
            parametrosPost.put("dado", dados);

            PostCadGenerico conHttpPostGenerico = new PostCadGenerico();
            conHttpPostGenerico.setParametrosPost(parametrosPost);
            conHttpPostGenerico.execute(url);

        }
        else{
            posEnvio = -1;
        }

    }

    public void enviarDadosPerda() {

        ConexaoWeb conexaoWeb = new ConexaoWeb();
        if (conexaoWeb.verificaConexao(context)) {

            posEnvio = 3;

            ConfigCTR configCTR = new ConfigCTR();
            String dados = configCTR.dadosEnvioPerda();

            Log.i("PMM", "BOLETIM PERDA = " + dados);

            UrlsConexaoHttp urlsConexaoHttp = new UrlsConexaoHttp();

            String[] url = {urlsConexaoHttp.getsInserirPerda()};
            Map<String, Object> parametrosPost = new HashMap<String, Object>();
            parametrosPost.put("dado", dados);

            PostCadGenerico conHttpPostGenerico = new PostCadGenerico();
            conHttpPostGenerico.setParametrosPost(parametrosPost);
            conHttpPostGenerico.execute(url);

        }
        else{
            posEnvio = -1;
        }

    }

    public void enviarDadosSoqueira() {

        ConexaoWeb conexaoWeb = new ConexaoWeb();
        if (conexaoWeb.verificaConexao(context)) {

            posEnvio = 4;

            ConfigCTR configCTR = new ConfigCTR();
            String dados = configCTR.dadosEnvioSoqueira();

            Log.i("PMM", "BOLETIM SOQUEIRA = " + dados);

            UrlsConexaoHttp urlsConexaoHttp = new UrlsConexaoHttp();

            String[] url = {urlsConexaoHttp.getsInserirSoqueira()};
            Map<String, Object> parametrosPost = new HashMap<String, Object>();
            parametrosPost.put("dado", dados);

            PostCadGenerico conHttpPostGenerico = new PostCadGenerico();
            conHttpPostGenerico.setParametrosPost(parametrosPost);
            conHttpPostGenerico.execute(url);

        }
        else{
            posEnvio = -1;
        }

    }

    public boolean verifDados() {
        ConfigCTR configCTR = new ConfigCTR();
        return configCTR.verifDadosRuricola();
    }

    public void envioDados(Context context) {
        this.context = context;
        if (verifDados()) {
            posEnvio = 1;
            enviarDadosRuricola();
        }
        else{
            posEnvio = 0;
        }
    }

    public int getStatusEnvio() {
        if (verifDados()) {
            statusEnvio = 1;
        } else {
            statusEnvio = 2;
        }
        return statusEnvio;
    }

    ////////////////////////////////////MECANISMO RECEBIMENTO/////////////////////////////////////////

    public void recDados(String result){
        ConfigCTR configCTR = new ConfigCTR();
        if(result.trim().startsWith("GRAVOU-RURICOLA")){
            RuricolaCTR ruricolaCTR = new RuricolaCTR();
            ruricolaCTR.updateBolAberto(result);
            if(configCTR.verifDadosFito()){
                posEnvio = 2;
                enviarDadosFito();
            }
            else if(configCTR.verifDadosPerda()){
                posEnvio = 3;
                enviarDadosPerda();
            }
            else if(configCTR.verifDadosSoqueira()){
                posEnvio = 4;
                enviarDadosSoqueira();
            }
            else{
                posEnvio = 5;
            }
        } else if (result.trim().startsWith("GRAVOU-FITO")) {
            FitoCTR fitoCTR = new FitoCTR();
            fitoCTR.updateCabecAberto(result);
            if(configCTR.verifDadosPerda()){
                posEnvio = 3;
                enviarDadosPerda();
            }
            else if(configCTR.verifDadosSoqueira()){
                posEnvio = 4;
                enviarDadosSoqueira();
            }
            else{
                posEnvio = 5;
            }
        } else if (result.trim().contains("GRAVOU-PERDA")) {
            PerdaCTR perdaCTR = new PerdaCTR();
            perdaCTR.updateCabecAberto(result);
            if(configCTR.verifDadosSoqueira()){
                posEnvio = 4;
                enviarDadosSoqueira();
            }
            else{
                posEnvio = 5;
            }
        } else if (result.trim().contains("GRAVOU-SOQUEIRA")) {
            SoqueiraCTR soqueiraCTR = new SoqueiraCTR();
            soqueiraCTR.updateCabecAberto(result);
            posEnvio = 5;
        }

    }

    ///////////////////////////////////////////////////////////////////////////////////////////////

    public void setStatusEnvio(int statusEnvio) {
        this.statusEnvio = statusEnvio;
    }

    public int getPosEnvio() {
        return posEnvio;
    }

    public void setPosEnvio(int posEnvio) {
        this.posEnvio = posEnvio;
    }
}
