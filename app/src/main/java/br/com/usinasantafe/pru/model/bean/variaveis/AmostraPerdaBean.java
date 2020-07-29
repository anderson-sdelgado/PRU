package br.com.usinasantafe.pru.model.bean.variaveis;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.pru.model.pst.Entidade;

@DatabaseTable(tableName="tbamostraperdavar")
public class AmostraPerdaBean extends Entidade {

    private static final long serialVersionUID = 1L;

    @DatabaseField(id=true)
    private Long idAmostraPerda;
    @DatabaseField
    private Long idCabecAmostraPerda;
    @DatabaseField
    private Long seqAmostraPerda;
    @DatabaseField
    private Double taraAmostraPerda;
    @DatabaseField
    private Double toleteAmostraPerda;
    @DatabaseField
    private Double canaInteiraAmostraPerda;
    @DatabaseField
    private Double tocoAmostraPerda;
    @DatabaseField
    private Double pedacoAmostraPerda;
    @DatabaseField
    private Double ponteiroAmostraPerda;
    @DatabaseField
    private Double lascasAmostraPerda;
    @DatabaseField
    private Double repiqueAmostraPerda;
    @DatabaseField
    private Long pedraAmostraPerda;
    @DatabaseField
    private Long tocoArvoreAmostraPerda;
    @DatabaseField
    private Long plantaDaninhasAmostraPerda;
    @DatabaseField
    private Long formigueiroAmostraPerda;

    public AmostraPerdaBean() {
    }

    public Long getIdAmostraPerda() {
        return idAmostraPerda;
    }

    public void setIdAmostraPerda(Long idAmostraPerda) {
        this.idAmostraPerda = idAmostraPerda;
    }

    public Long getIdCabecAmostraPerda() {
        return idCabecAmostraPerda;
    }

    public void setIdCabecAmostraPerda(Long idCabecAmostraPerda) {
        this.idCabecAmostraPerda = idCabecAmostraPerda;
    }

    public Long getSeqAmostraPerda() {
        return seqAmostraPerda;
    }

    public void setSeqAmostraPerda(Long seqAmostraPerda) {
        this.seqAmostraPerda = seqAmostraPerda;
    }

    public Double getTaraAmostraPerda() {
        return taraAmostraPerda;
    }

    public void setTaraAmostraPerda(Double taraAmostraPerda) {
        this.taraAmostraPerda = taraAmostraPerda;
    }

    public Double getToleteAmostraPerda() {
        return toleteAmostraPerda;
    }

    public void setToleteAmostraPerda(Double toleteAmostraPerda) {
        this.toleteAmostraPerda = toleteAmostraPerda;
    }

    public Double getCanaInteiraAmostraPerda() {
        return canaInteiraAmostraPerda;
    }

    public void setCanaInteiraAmostraPerda(Double canaInteiraAmostraPerda) {
        this.canaInteiraAmostraPerda = canaInteiraAmostraPerda;
    }

    public Double getTocoAmostraPerda() {
        return tocoAmostraPerda;
    }

    public void setTocoAmostraPerda(Double tocoAmostraPerda) {
        this.tocoAmostraPerda = tocoAmostraPerda;
    }

    public Double getPedacoAmostraPerda() {
        return pedacoAmostraPerda;
    }

    public void setPedacoAmostraPerda(Double pedacoAmostraPerda) {
        this.pedacoAmostraPerda = pedacoAmostraPerda;
    }

    public Double getPonteiroAmostraPerda() {
        return ponteiroAmostraPerda;
    }

    public void setPonteiroAmostraPerda(Double ponteiroAmostraPerda) {
        this.ponteiroAmostraPerda = ponteiroAmostraPerda;
    }

    public Double getLascasAmostraPerda() {
        return lascasAmostraPerda;
    }

    public void setLascasAmostraPerda(Double lascasAmostraPerda) {
        this.lascasAmostraPerda = lascasAmostraPerda;
    }

    public Double getRepiqueAmostraPerda() {
        return repiqueAmostraPerda;
    }

    public void setRepiqueAmostraPerda(Double repiqueAmostraPerda) {
        this.repiqueAmostraPerda = repiqueAmostraPerda;
    }

    public Long getPedraAmostraPerda() {
        return pedraAmostraPerda;
    }

    public void setPedraAmostraPerda(Long pedraAmostraPerda) {
        this.pedraAmostraPerda = pedraAmostraPerda;
    }

    public Long getTocoArvoreAmostraPerda() {
        return tocoArvoreAmostraPerda;
    }

    public void setTocoArvoreAmostraPerda(Long tocoArvoreAmostraPerda) {
        this.tocoArvoreAmostraPerda = tocoArvoreAmostraPerda;
    }

    public Long getPlantaDaninhasAmostraPerda() {
        return plantaDaninhasAmostraPerda;
    }

    public void setPlantaDaninhasAmostraPerda(Long plantaDaninhasAmostraPerda) {
        this.plantaDaninhasAmostraPerda = plantaDaninhasAmostraPerda;
    }

    public Long getFormigueiroAmostraPerda() {
        return formigueiroAmostraPerda;
    }

    public void setFormigueiroAmostraPerda(Long formigueiroAmostraPerda) {
        this.formigueiroAmostraPerda = formigueiroAmostraPerda;
    }
}
