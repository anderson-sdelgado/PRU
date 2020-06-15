package br.com.usinasantafe.pru.util.connHttp;

public class UrlsConexaoHttp {

    private int tipoEnvio = 1;

    public static String datahorahttp = "http://www.usinasantafe.com.br/pru/datahora.php";
    public static String atualizaaplichttp = "http://www.usinasantafe.com.br/pru/atualizaaplic.php";

    public static String urlPrincipal = "http://www.usinasantafe.com.br/prudev/";
    public static String urlPrincEnvio = "http://www.usinasantafe.com.br/prudev/";

    //public static String localPSTVariavel = "br.com.usinasantafe.pru.to.tb.variaveis.";
    public static String localPSTEstatica = "br.com.usinasantafe.pru.to.tb.estaticas.";
    public static String localUrl = "br.com.usinasantafe.pru.util.connHttp.UrlsConexaoHttp";

    public UrlsConexaoHttp() {
        // TODO Auto-generated constructor stub
    }

    public static String AtividadeTO = urlPrincipal + "atividade.php";
    public static String FuncTO = urlPrincipal + "func.php";
    public static String LiderTO = urlPrincipal + "lider.php";
    public static String TurmaTO = urlPrincipal + "turma.php";
    public static String TipoApontamentoTO = urlPrincipal + "tipoaponta.php";
    public static String ParadaTO = urlPrincipal + "parada.php";

    public String getsInsertAponta() {
        return urlPrincEnvio + "insapont.php";
    }

    public String getsInsertBolAberto() {
        return urlPrincEnvio + "insbolaberto.php";
    }

    public String getsInsertBolFechado() {
        return urlPrincEnvio + "insbolfechado.php";
    }

    public String urlVerifica(String classe) {
        String retorno = "";
        if (classe.equals("OS")) {
            retorno = urlPrincEnvio + "veros.php";
        } else if (classe.equals("Ativ")) {
            retorno = urlPrincEnvio + "atualosativ.php";
        } else if (classe.equals("Atualiza")) {
            retorno = urlPrincEnvio + "atualizaaplic.php";
        }
        return retorno;
    }

}
