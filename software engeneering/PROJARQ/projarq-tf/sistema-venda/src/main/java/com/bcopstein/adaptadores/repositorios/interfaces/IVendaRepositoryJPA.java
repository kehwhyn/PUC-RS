package com.bcopstein.adaptadores.repositorios.interfaces;

import java.util.List;

import com.bcopstein.negocio.entidades.Venda;

import org.springframework.data.repository.CrudRepository;

public interface IVendaRepositoryJPA extends CrudRepository<Venda, Integer> {
  List<Venda> findAll();
}
