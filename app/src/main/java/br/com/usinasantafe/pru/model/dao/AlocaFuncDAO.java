package br.com.usinasantafe.pru.model.dao;

import java.util.List;

import br.com.usinasantafe.pru.model.bean.estaticas.FuncBean;
import br.com.usinasantafe.pru.model.bean.variaveis.AlocaFuncBean;
import br.com.usinasantafe.pru.model.bean.variaveis.BoletimRuricolaBean;
import br.com.usinasantafe.pru.util.Tempo;

public class AlocaFuncDAO {

    public AlocaFuncDAO() {
    }

    public void alocaFunc(BoletimRuricolaBean boletimRuricolaBean) {
        AlocaFuncBean alocaFuncBean = new AlocaFuncBean();
        alocaFuncBean.setIdBolAlocaFunc(boletimRuricolaBean.getIdBol());
        alocaFuncBean.setIdExtBolAlocaFunc(boletimRuricolaBean.getIdExtBol());
        alocaFuncBean.setMatricFuncAlocaFunc(boletimRuricolaBean.getIdLiderBol());
        alocaFuncBean.setDthrAlocaFunc(Tempo.getInstance().data());
        alocaFuncBean.setTipoAlocaFunc(1L);
        alocaFuncBean.insert();
    }

    public void alocaFunc(BoletimRuricolaBean boletimRuricolaBean, List<FuncBean> funcAlocList, List<FuncBean> funcList) {
        for (FuncBean funcAlocBean : funcAlocList) {
            for (FuncBean funcBean : funcList) {
                if((funcAlocBean.getMatricFunc() == funcBean.getMatricFunc())
                    && (funcAlocBean.getTipoAlocaFunc() != funcBean.getTipoAlocaFunc())){
                    AlocaFuncBean alocaFuncBean = new AlocaFuncBean();
                    alocaFuncBean.setIdBolAlocaFunc(boletimRuricolaBean.getIdBol());
                    alocaFuncBean.setIdExtBolAlocaFunc(boletimRuricolaBean.getIdExtBol());
                    alocaFuncBean.setMatricFuncAlocaFunc(funcAlocBean.getMatricFunc());
                    alocaFuncBean.setDthrAlocaFunc(Tempo.getInstance().data());
                    alocaFuncBean.setTipoAlocaFunc(funcAlocBean.getTipoAlocaFunc());
                    alocaFuncBean.insert();
                }
            }
        }

    }

}
