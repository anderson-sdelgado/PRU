package br.com.usinasantafe.pru.util.connHttp;

public class UrlsConexaoHttp {

    private int tipoEnvio = 1;

    public static String urlPrincipal = "http://www.usinasantafe.com.br/prudev/";
    public static String urlPrincEnvio = "http://www.usinasantafe.com.br/prudev/";

    public static String localPSTEstatica = "br.com.usinasantafe.pru.model.bean.estaticas.";
    public static String localUrl = "br.com.usinasantafe.pru.util.connHttp.UrlsConexaoHttp";

    public UrlsConexaoHttp() {
    }

    public static String AmostraFitoBean = urlPrincipal + "amostrafito.php";
    public static String AtividadeBean = urlPrincipal + "atividade.php";
    public static String CaracOrganFitoBean = urlPrincipal + "caracorganfito.php";
    public static String EquipBean = urlPrincipal + "equip.php";
    public static String FuncBean = urlPrincipal + "func.php";
    public static String LiderBean = urlPrincipal + "lider.php";
    public static String OrganFitoBean = urlPrincipal + "organfito.php";
    public static String ParadaBean = urlPrincipal + "parada.php";
    public static String RFuncaoAtivParBean = urlPrincipal + "rfuncaoativpar.php";
    public static String ROrganCaracAmosFitoBean = urlPrincipal + "rorgancaracamosfito.php";
    public static String TalhaoBean = urlPrincipal + "talhao.php";
    public static String TipoApontBean = urlPrincipal + "tipoapont.php";
    public static String TurmaBean = urlPrincipal + "turma.php";

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
