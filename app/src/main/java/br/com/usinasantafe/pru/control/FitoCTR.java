package br.com.usinasantafe.pru.control;

import java.util.List;

import br.com.usinasantafe.pru.model.bean.variaveis.CabecFitoBean;
import br.com.usinasantafe.pru.model.dao.AmostraDAO;
import br.com.usinasantafe.pru.model.dao.CabecFitoDAO;
import br.com.usinasantafe.pru.model.dao.CaracOrganDAO;
import br.com.usinasantafe.pru.model.dao.OrganDAO;
import br.com.usinasantafe.pru.model.dao.TalaoDAO;

public class FitoCTR {

    private CabecFitoBean cabecFitoBean;

    public FitoCTR() {
    }

    public void salvarCabecFitoAberto(){
        CabecFitoDAO cabecFitoDAO = new CabecFitoDAO();
        cabecFitoDAO.salvarCabecFitoAberto(cabecFitoBean);
    }

    ////////////////////////////////// VERIFICAR CAMPOS ///////////////////////////////////////////

    public boolean verTalhao(Long codTalhao){
        ConfigCTR configCTR = new ConfigCTR();
        TalaoDAO talaoDAO  = new TalaoDAO();
        if(configCTR.verOS(configCTR.getConfig().getNroOSConfig())){
            return  talaoDAO.verTalhao(configCTR.getOS().getIdSecao(), codTalhao);
        }
        else{
            return true;
        }
    }

    public boolean verAmostra(){
        AmostraDAO amostraDAO = new AmostraDAO();
        return amostraDAO.verAmostra(cabecFitoBean.getIdOrgCabecFito(), cabecFitoBean.getIdCaracOrgCabecFito());
    }

    public boolean verTipoAmostra(){
        AmostraDAO amostraDAO = new AmostraDAO();
        return amostraDAO.verTipoAmostra(cabecFitoBean.getIdOrgCabecFito(), cabecFitoBean.getIdCaracOrgCabecFito());
    }

    /////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////// GET DE CAMPOS ///////////////////////////////////////////

    public CabecFitoBean getCabecFitoBean() {
        return cabecFitoBean;
    }

    public List getOrganList(){
        OrganDAO organDAO = new OrganDAO();
        return organDAO.getOrganList();
    }

    public List getCaracOrganList(){
        CaracOrganDAO caracOrganDAO = new CaracOrganDAO();
        return caracOrganDAO.getCaracOrganList(cabecFitoBean.getIdOrgCabecFito());
    }

    ///////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////// SET DE CAMPOS ///////////////////////////////////////////

    public void setCabecFitoBean(CabecFitoBean cabecFitoBean) {
        this.cabecFitoBean = cabecFitoBean;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////

}
