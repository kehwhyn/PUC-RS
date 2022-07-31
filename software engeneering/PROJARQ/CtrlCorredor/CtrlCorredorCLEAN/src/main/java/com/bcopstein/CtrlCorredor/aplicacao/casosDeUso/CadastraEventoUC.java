package com.bcopstein.CtrlCorredor.aplicacao.casosDeUso;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import com.bcopstein.CtrlCorredor.negocio.entidades.Evento;
import com.bcopstein.CtrlCorredor.negocio.servicos.ServicoEvento;

@Component
public class CadastraEventoUC {
    private ServicoEvento servicoEvento;

    @Autowired
    public CadastraEventoUC(ServicoEvento servicoEvento) {
        this.servicoEvento = servicoEvento;
    }

    public void run(Evento evento) {
        servicoEvento.cadastra(evento);
    }
}
