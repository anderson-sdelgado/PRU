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

public class AtivDAO {

    public AtivDAO() {
    }

    public void verAtiv(String dado, Context telaAtual, Class telaProx, ProgressDialog progressDialog){
        VerifDadosServ.getInstance().setVerTerm(true);
        VerifDadosServ.getInstance().verDados(dado, "Atividade", telaAtual, telaProx, progressDialog);
    }

    public void recDadosAtiv(String result) {

        try {

            if (!result.contains("exceeded")) {

                int posicao = result.indexOf("_") + 1;
                String objPrim = result.substring(0, (posicao - 1));
                String objSeg = result.substring(posicao);

                JSONObject jObj = new JSONObject(objPrim);
                JSONArray jsonArray = jObj.getJSONArray("dados");

                if (jsonArray.length() > 0) {

                    ROSAtivBean rosAtivBean = new ROSAtivBean();
                    rosAtivBean.deleteAll();

                    for (int j = 0; j < jsonArray.length(); j++) {

                        JSONObject objeto = jsonArray.getJSONObject(j);
                        Gson gson = new Gson();
                        ROSAtivBean rosAtiv = gson.fromJson(objeto.toString(), ROSAtivBean.class);
                        rosAtiv.insert();

                    }

                }

                jObj = new JSONObject(objSeg);
                jsonArray = jObj.getJSONArray("dados");

                AtividadeBean atividadeBean = new AtividadeBean();
                atividadeBean.deleteAll();

                for (int j = 0; j < jsonArray.length(); j++) {

                    JSONObject objeto = jsonArray.getJSONObject(j);
                    Gson gson = new Gson();
                    AtividadeBean atividade = gson.fromJson(objeto.toString(), AtividadeBean.class);
                    atividade.insert();

                }

                VerifDadosServ.getInstance().pulaTelaSemTerm();

            } else {
                VerifDadosServ.getInstance().msgSemTerm("EXCEDEU TEMPO LIMITE DE PESQUISA! POR FAVOR, PROCURE UM PONTO MELHOR DE CONEXÃO DOS DADOS.");
            }

        } catch (Exception e) {
            VerifDadosServ.getInstance().msgSemTerm("FALHA DE PESQUISA DE OS! POR FAVOR, TENTAR NOVAMENTE COM UM SINAL MELHOR.");
        }

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
