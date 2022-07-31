package com.bcopstein.adaptadores.repositorios;

import java.util.List;

import com.bcopstein.negocio.entidades.Venda;

import org.springframework.data.repository.CrudRepository;

public interface VendasRepJPA extends CrudRepository<Venda, Long> {
    List<Venda> findAll();
    Venda findById(long id);
}
