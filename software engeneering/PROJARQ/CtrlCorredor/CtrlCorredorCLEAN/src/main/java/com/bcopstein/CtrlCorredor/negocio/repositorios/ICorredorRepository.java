package com.bcopstein.CtrlCorredor.negocio.repositorios;

import java.util.List;

import com.bcopstein.CtrlCorredor.negocio.entidades.Corredor;

public interface ICorredorRepository {
    void removeTodos();
    List<Corredor> todos();
    boolean cadastra(Corredor corredor);
}
