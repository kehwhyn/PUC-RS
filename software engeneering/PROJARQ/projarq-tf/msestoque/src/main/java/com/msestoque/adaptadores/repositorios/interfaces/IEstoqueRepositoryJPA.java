package com.msestoque.adaptadores.repositorios.interfaces;

import java.util.List;

import com.msestoque.negocio.entidades.ItemEstoque;

import org.springframework.data.repository.CrudRepository;

public interface IEstoqueRepositoryJPA extends CrudRepository<ItemEstoque, Integer> {
 
    ItemEstoque findByProdutoCodigo(Integer codProduto);

    List<ItemEstoque> findByQtdadeGreaterThanEqual(Integer quantity);
}
