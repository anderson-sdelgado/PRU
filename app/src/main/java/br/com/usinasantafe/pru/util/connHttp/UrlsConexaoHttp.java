package br.com.usinasantafe.pru.util.connHttp;

import br.com.usinasantafe.pru.PRUContext;

public class UrlsConexaoHttp {

    private int tipoEnvio = 1;

    public static String urlPrincipal = "http://www.usinasantafe.com.br/prudev/view/";
    public static String urlPrincEnvio = "http://www.usinasantafe.com.br/prudev/view/";

    public static String localPSTEstatica = "br.com.usinasantafe.pru.model.bean.estaticas.";
    public static String localUrl = "br.com.usinasantafe.pru.util.connHttp.UrlsConexaoHttp";

    public static String put = "?versao=" + PRUContext.versaoAplic.replace(".", "_");

    public static String AmostraFitoBean = urlPrincipal + "amostrafito.php" + put;
    public static String AtividadeBean = urlPrincipal + "atividade.php" + put;
    public static String CaracOrganFitoBean = urlPrincipal + "caracorganfito.php" + put;
    public static String EquipBean = urlPrincipal + "equip.php" + put;
    public static String FuncBean = urlPrincipal + "func.php" + put;
    public static String LiderBean = urlPrincipal + "lider.php" + put;
    public static String OrganFitoBean = urlPrincipal + "organfito.php" + put;
    public static String ParadaBean = urlPrincipal + "parada.php" + put;
    public static String RFuncaoAtivParBean = urlPrincipal + "rfuncaoativpar.php" + put;
    public static String ROCAFitoBean = urlPrincipal + "rocafito.php" + put;
    public static String TalhaoBean = urlPrincipal + "talhao.php" + put;
    public static String TipoApontBean = urlPrincipal + "tipoapont.php" + put;
    public static String TurmaBean = urlPrincipal + "turma.php" + put;

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
            retorno = urlPrincEnvio + "os.php" + put;
        } else if (classe.equals("Ativ")) {
            retorno = urlPrincEnvio + "atualosativ.php" + put;
        } else if (classe.equals("Atualiza")) {
            retorno = urlPrincEnvio + "atualaplic.php" + put;
        }
        return retorno;
    }

}
