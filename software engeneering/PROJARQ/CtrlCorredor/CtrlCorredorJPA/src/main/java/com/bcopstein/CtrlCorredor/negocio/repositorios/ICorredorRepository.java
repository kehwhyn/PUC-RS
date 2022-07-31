package com.bcopstein.CtrlCorredor.negocio.repositorios;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.bcopstein.CtrlCorredor.negocio.entidades.Corredor;

public interface ICorredorRepository extends CrudRepository<Corredor, Long> {
    void removeTodos();
    List<Corredor> todos();
    boolean cadastra(Corredor corredor);
}
