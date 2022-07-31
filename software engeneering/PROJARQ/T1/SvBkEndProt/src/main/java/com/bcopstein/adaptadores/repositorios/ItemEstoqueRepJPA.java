package com.bcopstein.adaptadores.repositorios;

import java.util.List;

import com.bcopstein.negocio.entidades.ItemEstoque;

import org.springframework.data.repository.CrudRepository;

public interface ItemEstoqueRepJPA extends CrudRepository<ItemEstoque, Long> {
    List<ItemEstoque> findAll();
}
