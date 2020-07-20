package br.com.usinasantafe.pru.model.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pru.model.bean.estaticas.AmostraBean;
import br.com.usinasantafe.pru.model.bean.variaveis.RespFitoBean;
import br.com.usinasantafe.pru.model.pst.EspecificaPesquisa;

public class RespFitoDAO {

    public RespFitoDAO() {
    }

    public void salvarRespFito(AmostraBean amostraBean, Long idCabec, Long valor, Long ponto){
        RespFitoBean respFitoBean = new RespFitoBean();
        respFitoBean.setIdAmostraRespFito(amostraBean.getIdAmostra());
        respFitoBean.setIdCabecRespFito(idCabec);
        respFitoBean.setPontoRespFito(ponto);
        respFitoBean.setTipoRespFito(amostraBean.getTipoAmostra());
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

    public void atualRespFito(RespFitoBean respFitoBean){
        respFitoBean.update();
    }

    public List<RespFitoBean> respCabecFitoList(Long idCabecFito){

        ArrayList listaPesq = new ArrayList();

        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("idCabecRespFito");
        pesquisa.setValor(idCabecFito);
        pesquisa.setTipo(1);
        listaPesq.add(pesquisa);

        EspecificaPesquisa pesquisa2 = new EspecificaPesquisa();
        pesquisa2.setCampo("tipoRespFito");
        pesquisa2.setValor(2L);
        pesquisa2.setTipo(1);
        listaPesq.add(pesquisa2);

        EspecificaPesquisa pesquisa3 = new EspecificaPesquisa();
        pesquisa3.setCampo("statusRespFito");
        pesquisa3.setValor(0L);
        pesquisa3.setTipo(1);
        listaPesq.add(pesquisa3);

        RespFitoBean respFitoBean = new RespFitoBean();
        List<RespFitoBean> respFitoList = respFitoBean.get(listaPesq);
        listaPesq.clear();

        return respFitoList;
    }

    public List<RespFitoBean> respFitoListAberto(Long idCabecFito, Long ponto){

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
        pesquisa3.setCampo("statusRespFito");
        pesquisa3.setValor(0L);
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

    private List<RespFitoBean> respFitoList(Long idCabecFito){
        RespFitoBean respFitoBean = new RespFitoBean();
        return respFitoBean.get("idCabecRespFito", idCabecFito);
    }

    private List<RespFitoBean> respFitoAbertoList(){
        RespFitoBean respFitoBean = new RespFitoBean();
        return respFitoBean.get("statusRespFito", 0L);
    }

}
