package com.bcopstein.CtrlCorredor.negocio.servicos;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import com.bcopstein.CtrlCorredor.negocio.entidades.Evento;
import com.bcopstein.CtrlCorredor.negocio.repositorios.IEventoRepository;

@Component
public class ServicoEvento {
    private IEventoRepository eventoRep;

    @Autowired
    public ServicoEvento(IEventoRepository eventoRep) {
        this.eventoRep = eventoRep;
    }

    public List<Evento> todos() {
        return eventoRep.todos();
    }

    public void cadastra(Evento evento) {
        eventoRep.cadastra(evento);
    }
}
