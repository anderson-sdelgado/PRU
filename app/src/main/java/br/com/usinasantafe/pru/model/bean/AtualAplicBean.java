package br.com.usinasantafe.pru.model.bean;

/**
 * Created by anderson on 24/07/2017.
 */

public class AtualAplicBean {

    private Long nroAparelho;
    private Long nroOS;
    private String versao;
    private String dthr;
    private String token;

    public AtualAplicBean() {
    }

    public Long getNroAparelho() {
        return nroAparelho;
    }

    public void setNroAparelho(Long nroAparelho) {
        this.nroAparelho = nroAparelho;
    }

    public Long getNroOS() {
        return nroOS;
    }

    public void setNroOS(Long nroOS) {
        this.nroOS = nroOS;
    }

    public String getVersao() {
        return versao;
    }

    public void setVersao(String versao) {
        this.versao = versao;
    }

    public String getDthr() {
        return dthr;
    }

    public void setDthr(String dthr) {
        this.dthr = dthr;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
