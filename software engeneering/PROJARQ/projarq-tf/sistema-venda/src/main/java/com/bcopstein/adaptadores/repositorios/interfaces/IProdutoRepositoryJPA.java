package com.bcopstein.adaptadores.repositorios.interfaces;

import java.util.List;

import com.bcopstein.negocio.entidades.Produto;
import org.springframework.data.repository.CrudRepository;

public interface IProdutoRepositoryJPA extends CrudRepository<Produto, Integer> {
  List<Produto> findAll();
}