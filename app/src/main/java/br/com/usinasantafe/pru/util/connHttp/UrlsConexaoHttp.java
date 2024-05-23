package br.com.usinasantafe.pru.util.connHttp;

import br.com.usinasantafe.pru.PRUContext;

public class UrlsConexaoHttp {

    public static String versao = "versao_" + PRUContext.versaoWS.replace(".", "_");

//    public static String url = "https://www.usinasantafe.com.br/prudev/view/";
//    public static String url = "https://www.usinasantafe.com.br/pruqa/view/";
    public static String url = "https://www.usinasantafe.com.br/pruprod/" + versao + "/view/";

    public static String localPSTEstatica = "br.com.usinasantafe.pru.model.bean.estaticas.";
    public static String localUrl = "br.com.usinasantafe.pru.util.connHttp.UrlsConexaoHttp";

    public static String AtividadeBean = url + "atividade.php";
    public static String FuncBean = url + "func.php";
    public static String LiderBean = url + "lider.php";
    public static String ParadaBean = url + "parada.php";
    public static String RFuncaoAtivParBean = url + "rfuncaoativpar.php";
    public static String TipoApontBean = url + "tipoapont.php";
    public static String TurmaBean = url + "turma.php";

    public UrlsConexaoHttp() {
    }
    public String getsInserirRuricola() {
        return url + "inserirruricola.php";
    }

    public String urlVerifica(String classe) {
        String retorno = "";
        if (classe.equals("OS")) {
            retorno = url + "os.php";
        } else if (classe.equals("Atualiza")) {
            retorno = url + "atualaplic.php";
        } else if (classe.equals("Token")) {
            retorno = url + "aparelho.php";
        }
        return retorno;
    }

}
