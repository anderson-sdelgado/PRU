package br.com.usinasantafe.pru.util.connHttp;

import br.com.usinasantafe.pru.PRUContext;

public class UrlsConexaoHttp {

    public static String versao = "?versao=" + PRUContext.versaoWS.replace(".", "_");

    public static String urlPrincipal = "https://www.usinasantafe.com.br/prudev/view/";
    public static String urlPrincEnvio = "https://www.usinasantafe.com.br/prudev/view/";

//    public static String urlPrincipal = "https://www.usinasantafe.com.br/pruqa/view/";
//    public static String urlPrincEnvio = "https://www.usinasantafe.com.br/pruqa/view/";

//    public static String urlPrincipal = "https://www.usinasantafe.com.br/pruprod/" + versao + "/view/";
//    public static String urlPrincEnvio = "https://www.usinasantafe.com.br/pruprod/" + versao + "/view/";

    public static String localPSTEstatica = "br.com.usinasantafe.pru.model.bean.estaticas.";
    public static String localUrl = "br.com.usinasantafe.pru.util.connHttp.UrlsConexaoHttp";

    public static String AmostraFitoBean = urlPrincipal + "amostrafito.php";
    public static String AtividadeBean = urlPrincipal + "atividade.php";
    public static String CaracOrganFitoBean = urlPrincipal + "caracorganfito.php";
    public static String EquipBean = urlPrincipal + "equip.php";
    public static String FuncBean = urlPrincipal + "func.php";
    public static String LiderBean = urlPrincipal + "lider.php";
    public static String OrganFitoBean = urlPrincipal + "organfito.php";
    public static String ParadaBean = urlPrincipal + "parada.php";
    public static String RFuncaoAtivParBean = urlPrincipal + "rfuncaoativpar.php";
    public static String ROCAFitoBean = urlPrincipal + "rocafito.php";
//    public static String TalhaoBean = urlPrincipal + "talhao.php";
    public static String TipoApontBean = urlPrincipal + "tipoapont.php";
    public static String TurmaBean = urlPrincipal + "turma.php";

    public UrlsConexaoHttp() {
    }

    public String getsInserirRuricola() {
        return urlPrincEnvio + "inserirruricola.php";
    }

    public String getsInserirFito() {
        return urlPrincEnvio + "inserirfito.php";
    }

    public String getsInserirPerda() {
        return urlPrincEnvio + "inserirperda.php";
    }

    public String getsInserirSoqueira() {
        return urlPrincEnvio + "inserirsoqueira.php";
    }

    public String urlVerifica(String classe) {
        String retorno = "";
        if (classe.equals("OS")) {
            retorno = urlPrincEnvio + "os.php";
        } else if (classe.equals("Ativ")) {
            retorno = urlPrincEnvio + "atualosativ.php";
        } else if (classe.equals("Atualiza")) {
            retorno = urlPrincEnvio + "atualaplic.php";
        }
        return retorno;
    }

}
