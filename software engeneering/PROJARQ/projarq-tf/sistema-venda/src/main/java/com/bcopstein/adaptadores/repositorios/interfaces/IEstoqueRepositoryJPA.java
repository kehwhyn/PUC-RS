package com.bcopstein.adaptadores.repositorios.interfaces;

import com.bcopstein.negocio.entidades.ItemEstoque;

import org.springframework.data.repository.CrudRepository;

public interface IEstoqueRepositoryJPA extends CrudRepository<ItemEstoque, Integer> {
  ItemEstoque findByProdutoCodigo(Integer codProduto);
}
