package br.com.usinasantafe.pru.model.dao;

import android.app.ProgressDialog;
import android.content.Context;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import br.com.usinasantafe.pru.model.bean.estaticas.AtividadeBean;
import br.com.usinasantafe.pru.model.bean.estaticas.ROSAtivBean;
import br.com.usinasantafe.pru.util.VerifDadosServ;

public class AtividadeDAO {

    public AtividadeDAO() {
    }

    public ArrayList retAtivArrayList(Long nroOS){

        ArrayList atividadeArrayList = new ArrayList();
        List atividadeList;

        ROSAtivBean rOSAtivBean = new ROSAtivBean();
        List rOSAtivList = rOSAtivBean.get("nroOS", nroOS);

        AtividadeBean atividadeBean = new AtividadeBean();

        if (rOSAtivList.size() > 0) {

            ArrayList<Long> rOSAtivArrayList = new ArrayList<Long>();

            for (int i = 0; i < rOSAtivList.size(); i++) {
                rOSAtivBean = (ROSAtivBean) rOSAtivList.get(i);
                rOSAtivArrayList.add(rOSAtivBean.getIdAtiv());
            }

            atividadeList = atividadeBean.in("idAtiv", rOSAtivArrayList);
            rOSAtivArrayList.clear();

        } else {

            atividadeList = atividadeBean.all();

        }

        for (int i = 0; i < atividadeList.size(); i++) {
            atividadeBean = (AtividadeBean) atividadeList.get(i);
            atividadeArrayList.add(atividadeBean);
        }


        atividadeList.clear();
        rOSAtivList.clear();

        return atividadeArrayList;

    }

    public void deleteAll(){
        ROSAtivBean rosAtivBean = new ROSAtivBean();
        rosAtivBean.deleteAll();
    }

    public AtividadeBean getAtividade(Long idAtiv){
        AtividadeBean atividadeBean = new AtividadeBean();
        List atividadeList = atividadeBean.get("idAtiv", idAtiv);
        atividadeBean = (AtividadeBean) atividadeList.get(0);
        atividadeList.clear();
        return atividadeBean;
    }

}
