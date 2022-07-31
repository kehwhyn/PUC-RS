package com.bcopstein.CtrlCorredor.adaptadores.repositorios;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;

import com.bcopstein.CtrlCorredor.negocio.entidades.Evento;
import com.bcopstein.CtrlCorredor.negocio.repositorios.IEventoRepository;

@Repository
public class EventoRepository {

    private IEventoRepository eventoRep;

    @Autowired
    public EventoRepository(IEventoRepository eventoRep) {
        this.eventoRep = eventoRep;
    }

    public List<Evento> listaEventos() {
        var resp = this.eventoRep.listaEventos();
        return resp;
    }

    public void deletaEventos() {
        this.eventoRep.deletaEventos();
    }

    public boolean cadastraEvento(Evento evento) {
        this.eventoRep.cadastra(evento);
        return true;
    }
}
