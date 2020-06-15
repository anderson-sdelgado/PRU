package br.com.usinasantafe.pru.control;

import br.com.usinasantafe.pru.model.dao.BoletimDAO;

public class RuricolaCTR {

    public RuricolaCTR() {
    }

    public boolean verBolAberto(){
        BoletimDAO boletimDAO = new BoletimDAO();
        return boletimDAO.verBolAberto();
    }

}
