package br.com.usinasantafe.pru.util;

import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.util.Log;

import br.com.usinasantafe.pru.control.RuricolaCTR;
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

    public void enviarBolFechados() {

        RuricolaCTR ruricolaCTR = new RuricolaCTR();
        String dados = ruricolaCTR.dadosEnvioBolFechado();

        Log.i("PMM", "FECHADO = " + dados);

        UrlsConexaoHttp urlsConexaoHttp = new UrlsConexaoHttp();

        String[] url = {urlsConexaoHttp.getsInsertBolFechado()};
        Map<String, Object> parametrosPost = new HashMap<String, Object>();
        parametrosPost.put("dado", dados);

        PostCadGenerico conHttpPostGenerico = new PostCadGenerico();
        conHttpPostGenerico.setParametrosPost(parametrosPost);
        conHttpPostGenerico.execute(url);

    }

    public void enviarBolAberto() {

        RuricolaCTR ruricolaCTR = new RuricolaCTR();
        String dados = ruricolaCTR.dadosEnvioBolAberto();

        Log.i("PMM", "ABERTO = " + dados);

        UrlsConexaoHttp urlsConexaoHttp = new UrlsConexaoHttp();

        String[] url = {urlsConexaoHttp.getsInsertBolAberto()};
        Map<String, Object> parametrosPost = new HashMap<String, Object>();
        parametrosPost.put("dado", dados);

        PostCadGenerico conHttpPostGenerico = new PostCadGenerico();
        conHttpPostGenerico.setParametrosPost(parametrosPost);
        conHttpPostGenerico.execute(url);

    }

    public void envioApont() {

        RuricolaCTR ruricolaCTR = new RuricolaCTR();
        String dados = ruricolaCTR.dadosEnvioApont();

        Log.i("PMM", "APONTAMENTO = " + dados);

        String[] url = {urlsConexaoHttp.getsInsertAponta()};
        Map<String, Object> parametrosPost = new HashMap<String, Object>();
        parametrosPost.put("dado", dados);

        PostCadGenerico postCadGenerico = new PostCadGenerico();
        postCadGenerico.setParametrosPost(parametrosPost);
        postCadGenerico.execute(url);

    }

    public Boolean verifBolFechado() {
        RuricolaCTR ruricolaCTR = new RuricolaCTR();
        return ruricolaCTR.verEnvioBolFech();
    }

    public Boolean verifBolAbertoSemEnvio() {
        RuricolaCTR ruricolaCTR = new RuricolaCTR();
        return ruricolaCTR.verEnvioBolAberto();
    }

    public Boolean verifApont() {
        RuricolaCTR ruricolaCTR = new RuricolaCTR();
        return ruricolaCTR.verEnvioDadosApont();
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
        if (verifBolFechado()) {
            enviarBolFechados();
        } else {
            if (verifBolAbertoSemEnvio()) {
                enviarBolAberto();
            } else {
                if (verifApont()) {
                    envioApont();
                }
            }
        }
    }


    public boolean verifDadosEnvio() {
        if ((!verifBolFechado())
                && (!verifBolAbertoSemEnvio())
                && (!verifApont())){
            enviando = false;
            return false;
        } else {
            return true;
        }
    }

    public int getStatusEnvio() {
        if (enviando) {
            statusEnvio = 1;
        } else {
            if (!verifDadosEnvio()) {
                statusEnvio = 3;
            } else {
                statusEnvio = 2;
            }
        }
        return statusEnvio;
    }

    public void setEnviando(boolean enviando) {
        this.enviando = enviando;
    }

    public void setStatusEnvio(int statusEnvio) {
        this.statusEnvio = statusEnvio;
    }

}
