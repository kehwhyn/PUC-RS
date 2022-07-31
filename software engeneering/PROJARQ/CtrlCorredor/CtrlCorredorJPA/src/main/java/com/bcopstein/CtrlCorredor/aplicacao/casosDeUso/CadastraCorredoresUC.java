package com.bcopstein.CtrlCorredor.aplicacao.casosDeUso;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import com.bcopstein.CtrlCorredor.negocio.entidades.Corredor;
import com.bcopstein.CtrlCorredor.negocio.servicos.ServicoCorredor;

@Component
public class CadastraCorredoresUC {
    private ServicoCorredor servicoCorredor;

    @Autowired
    public CadastraCorredoresUC(ServicoCorredor servicoCorredor) {
        this.servicoCorredor = servicoCorredor;
    }

    public void run(Corredor corredor) {
        servicoCorredor.cadastraCorredor(corredor);
    }

}
