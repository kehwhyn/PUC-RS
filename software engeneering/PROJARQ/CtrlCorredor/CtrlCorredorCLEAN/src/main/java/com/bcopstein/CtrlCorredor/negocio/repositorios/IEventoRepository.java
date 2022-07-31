package com.bcopstein.CtrlCorredor.negocio.repositorios;

import java.util.List;

import com.bcopstein.CtrlCorredor.negocio.entidades.Evento;

public interface IEventoRepository {
    List<Evento> todos();
    void deletaEventos();
    List<Evento> listaEventos();
    boolean cadastra(Evento evento);
    boolean cadastraEvento(Evento evento);
}
