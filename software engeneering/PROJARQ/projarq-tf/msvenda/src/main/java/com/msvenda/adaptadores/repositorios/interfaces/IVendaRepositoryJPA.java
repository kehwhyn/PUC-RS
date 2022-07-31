package com.msvenda.adaptadores.repositorios.interfaces;

import java.util.List;

import com.msvenda.negocio.entidades.Venda;

import org.springframework.data.repository.CrudRepository;

public interface IVendaRepositoryJPA extends CrudRepository<Venda, Integer> {
  List<Venda> findAll();
}
