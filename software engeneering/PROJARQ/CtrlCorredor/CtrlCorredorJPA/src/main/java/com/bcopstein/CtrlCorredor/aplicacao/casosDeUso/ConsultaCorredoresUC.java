package com.bcopstein.CtrlCorredor.aplicacao.casosDeUso;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import com.bcopstein.CtrlCorredor.negocio.entidades.Corredor;
import com.bcopstein.CtrlCorredor.negocio.servicos.ServicoCorredor;

@Component
public class ConsultaCorredoresUC {
    private ServicoCorredor servicoCorredor;

    @Autowired
    public ConsultaCorredoresUC(ServicoCorredor servicoCorredor) {
        this.servicoCorredor = servicoCorredor;
    }

    public List<Corredor> run() {
        return servicoCorredor.listaCorredores();
    }
}
