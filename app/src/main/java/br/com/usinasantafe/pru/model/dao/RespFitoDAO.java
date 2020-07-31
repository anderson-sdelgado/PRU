package br.com.usinasantafe.pru.model.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pru.model.bean.estaticas.AmostraFitoBean;
import br.com.usinasantafe.pru.model.bean.variaveis.RespFitoBean;
import br.com.usinasantafe.pru.model.pst.EspecificaPesquisa;

public class RespFitoDAO {

    public RespFitoDAO() {
    }

    public void salvarRespFito(AmostraFitoBean amostraFitoBean, Long idCabec, Long valor, Long ponto){
        RespFitoBean respFitoBean = new RespFitoBean();
        respFitoBean.setIdAmostraRespFito(amostraFitoBean.getIdAmostra());
        respFitoBean.setIdCabecRespFito(idCabec);
        respFitoBean.setPontoRespFito(ponto);
        respFitoBean.setTipoRespFito(amostraFitoBean.getTipoAmostra());
        respFitoBean.setValorRespFito(valor);
        respFitoBean.setStatusRespFito(0L);
        respFitoBean.insert();
    }

    public void fecharRespFitoPonto(){
        List<RespFitoBean> respFitoList = respFitoAbertoList();
        for(RespFitoBean respFitoBeanBD : respFitoList){
            respFitoBeanBD.setStatusRespFito(1L);
            respFitoBeanBD.update();
        }
        respFitoList.clear();
    }

    public void atualRespFito(AmostraFitoBean amostraFitoBean, Long ponto, Long valor, Long idCabecFito){
        RespFitoBean respFitoBean = getRespFitoBean(idCabecFito, ponto, amostraFitoBean.getIdAmostra());
        respFitoBean.setValorRespFito(valor);
        respFitoBean.update();
    }

    public void atualRespFito(Long idRespFito,Long valor){
        RespFitoBean respFitoBean = getRespFitoBean(idRespFito);
        respFitoBean.setValorRespFito(valor);
        respFitoBean.update();
    }

    public List<RespFitoBean> respFitoList(Long idCabecFito, Long ponto, Long idAmostra){

        ArrayList listaPesq = new ArrayList();

        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("idCabecRespFito");
        pesquisa.setValor(idCabecFito);
        pesquisa.setTipo(1);
        listaPesq.add(pesquisa);

        EspecificaPesquisa pesquisa2 = new EspecificaPesquisa();
        pesquisa2.setCampo("pontoRespFito");
        pesquisa2.setValor(ponto);
        pesquisa2.setTipo(1);
        listaPesq.add(pesquisa2);

        EspecificaPesquisa pesquisa3 = new EspecificaPesquisa();
        pesquisa3.setCampo("idAmostraRespFito");
        pesquisa3.setValor(idAmostra);
        pesquisa3.setTipo(1);
        listaPesq.add(pesquisa3);

        RespFitoBean respFitoBean = new RespFitoBean();
        List<RespFitoBean> respFitoList = respFitoBean.get(listaPesq);
        listaPesq.clear();

        return respFitoList;
    }

    public List<RespFitoBean> getRespPontoFitoList(Long idCabecFito, Long ponto){

        ArrayList listaPesq = new ArrayList();

        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("idCabecRespFito");
        pesquisa.setValor(idCabecFito);
        pesquisa.setTipo(1);
        listaPesq.add(pesquisa);

        EspecificaPesquisa pesquisa2 = new EspecificaPesquisa();
        pesquisa2.setCampo("pontoRespFito");
        pesquisa2.setValor(ponto);
        pesquisa2.setTipo(1);
        listaPesq.add(pesquisa2);

        RespFitoBean respFitoBean = new RespFitoBean();
        List<RespFitoBean> respFitoList = respFitoBean.get(listaPesq);
        listaPesq.clear();

        return respFitoList;
    }

    public RespFitoBean getRespFitoBean(Long idCabecFito, Long ponto, Long idAmostra){
        List respFitoList = respFitoList(idCabecFito, ponto, idAmostra);
        RespFitoBean respFitoBean = (RespFitoBean) respFitoList.get(0);
        respFitoList.clear();
        return respFitoBean;
    }

    public RespFitoBean getRespFitoBean(Long idRespFito){
        List respFitoList = respFitoList(idRespFito);
        RespFitoBean respFitoBean = (RespFitoBean) respFitoList.get(0);
        respFitoList.clear();
        return respFitoBean;
    }

    public void delRespFito(Long idCabec){
        RespFitoBean respFitoBean = new RespFitoBean();
        List<RespFitoBean> respFitoList = respFitoBean.get("idCabecRespFito", idCabec);
        for(RespFitoBean respFitoBeanBD : respFitoList){
            respFitoBeanBD.delete();
            respFitoBeanBD.commit();
        }
        respFitoList.clear();
    }

    public void delRespPonto(Long idCabec, Long ponto){
        List<RespFitoBean> respFitoList = getRespPontoFitoList(idCabec, ponto);
        for(RespFitoBean respFitoBeanBD : respFitoList){
            respFitoBeanBD.delete();
            respFitoBeanBD.commit();
        }
        respFitoList.clear();
    }

    public boolean hasRespFitoCabec(Long idCabecFito){
        List<RespFitoBean> respFitoBeanList = respFitoList(idCabecFito);
        boolean ret = respFitoBeanList.size() > 0;
        respFitoBeanList.clear();
        return ret;
    }

    private List<RespFitoBean> respFitoList(Long idRespFito){
        RespFitoBean respFitoBean = new RespFitoBean();
        return respFitoBean.get("idRespFito", idRespFito);
    }

    private List<RespFitoBean> respFitoAbertoList(){
        RespFitoBean respFitoBean = new RespFitoBean();
        return respFitoBean.get("statusRespFito", 0L);
    }



}
