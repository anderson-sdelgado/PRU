package br.com.usinasantafe.pru.model.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pru.model.bean.estaticas.FuncBean;
import br.com.usinasantafe.pru.model.pst.EspecificaPesquisa;

public class FuncDAO {

    public boolean verFunc(Long matricFunc){
        List funcList = funcMatricList(matricFunc);
        boolean ret = funcList.size() > 0;
        funcList.clear();
        return ret;
    }

    public boolean verFunc(Long matricFunc, Long idTurma){

        FuncBean funcBean = new FuncBean();

        ArrayList listaPesq = new ArrayList();
        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("idTurma");
        pesquisa.setValor(idTurma);
        listaPesq.add(pesquisa);

        EspecificaPesquisa pesquisa2 = new EspecificaPesquisa();
        pesquisa2.setCampo("matricFunc");
        pesquisa2.setValor(matricFunc);
        listaPesq.add(pesquisa2);

        List funcList = funcBean.get(listaPesq);
        boolean ret = (funcList.size() > 0);
        funcList.clear();
        listaPesq.clear();

        return ret;

    }

    private List funcMatricList(Long matricFunc){
        FuncBean funcBean = new FuncBean();
        List funcList = funcBean.get("matricFunc", matricFunc);
        return funcList;
    }

    private List funcIdList(Long idFunc){
        FuncBean funcBean = new FuncBean();
        List funcList = funcBean.get("idFunc", idFunc);
        return funcList;
    }

    public List getFuncAlocList(Long idTurma){
        FuncBean funcBean = new FuncBean();
        List funcList = funcBean.getAndOrderBy("idTurma", idTurma, "nomeFunc", true);
        return funcList;
    }

    public List getFuncAlocList(){
        FuncBean funcBean = new FuncBean();
        List funcList = funcBean.getAndOrderBy("tipoAlocaFunc", 1L, "nomeFunc", true);
        return funcList;
    }

    public void atualFuncAloc(List<FuncBean> funcList){
        for (FuncBean funcBean : funcList) {
            funcBean.update();
        }
    }

    public void alocFunc(FuncBean funcBean){
        funcBean.setTipoAlocaFunc(1L);
        funcBean.update();
    }

    public FuncBean getFuncMatric(Long matricFunc){
        List funcList = funcMatricList(matricFunc);
        FuncBean funcBean = (FuncBean) funcList.get(0);
        funcList.clear();
        return funcBean;
    }

    public FuncBean getFuncId(Long idFunc){
        List funcList = funcIdList(idFunc);
        FuncBean funcBean = (FuncBean) funcList.get(0);
        funcList.clear();
        return funcBean;
    }

}
