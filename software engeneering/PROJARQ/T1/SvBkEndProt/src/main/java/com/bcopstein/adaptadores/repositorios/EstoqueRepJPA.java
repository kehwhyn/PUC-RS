package com.bcopstein.adaptadores.repositorios;

import java.util.List;

import com.bcopstein.negocio.entidades.Estoque;

import org.springframework.data.repository.CrudRepository;

public interface EstoqueRepJPA extends CrudRepository<Estoque, Long> {
    List<Estoque> findAll();
}
