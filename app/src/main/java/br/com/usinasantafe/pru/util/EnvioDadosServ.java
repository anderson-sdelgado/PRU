package br.com.usinasantafe.pru.util;

import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.util.Log;

import br.com.usinasantafe.pru.control.ConfigCTR;
import br.com.usinasantafe.pru.util.connHttp.PostCadGenerico;
import br.com.usinasantafe.pru.util.connHttp.UrlsConexaoHttp;

public class EnvioDadosServ {

    private static EnvioDadosServ instance = null;
    private UrlsConexaoHttp urlsConexaoHttp;

    private int statusEnvio; //1 - Enviando; 2 - Existe Dados para Enviar; 3 - Todos os Dados Enviados
    private boolean enviando = false;

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

    public void enviarDadosFito() {

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

    public void enviarDadosPerda() {

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

    public void enviarDadosSoqueira() {

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

    public boolean verifDados() {
        ConfigCTR configCTR = new ConfigCTR();
        return configCTR.verifDadosRuricola();
    }

    public void envioDados(Context context) {
        enviando = true;
        ConexaoWeb conexaoWeb = new ConexaoWeb();
        if (conexaoWeb.verificaConexao(context)) {
            envioDadosPrinc();
        }
        else{
            enviando = false;
        }
    }

    public void envioDadosPrinc() {
        if (verifDados()) {
            enviarDadosRuricola();
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
            if(configCTR.verifDadosFito()){
                enviarDadosFito();
            }
            else if(configCTR.verifDadosPerda()){
                enviarDadosPerda();
            }
            else if(configCTR.verifDadosSoqueira()){
                enviarDadosSoqueira();
            }
            else{
                configCTR.delAllDados();
            }
        } else if (result.trim().startsWith("GRAVOU-FITO")) {
            if(configCTR.verifDadosPerda()){
                enviarDadosPerda();
            }
            else if(configCTR.verifDadosSoqueira()){
                enviarDadosSoqueira();
            }
            else{
                configCTR.delAllDados();
            }
        } else if (result.trim().contains("GRAVOU-PERDA")) {
            if(configCTR.verifDadosSoqueira()){
                enviarDadosSoqueira();
            }
            else{
                configCTR.delAllDados();
            }
        } else if (result.trim().contains("GRAVOU-SOQUEIRA")) {
            configCTR.delAllDados();
        }

    }

    ///////////////////////////////////////////////////////////////////////////////////////////////

    public void setEnviando(boolean enviando) {
        this.enviando = enviando;
    }

    public void setStatusEnvio(int statusEnvio) {
        this.statusEnvio = statusEnvio;
    }

}
