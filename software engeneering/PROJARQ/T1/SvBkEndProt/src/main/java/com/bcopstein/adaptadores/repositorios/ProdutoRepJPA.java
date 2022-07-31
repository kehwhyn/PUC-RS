package com.bcopstein.adaptadores.repositorios;

import java.util.List;

import com.bcopstein.negocio.entidades.Produto;
import org.springframework.data.repository.CrudRepository;

public interface ProdutoRepJPA extends CrudRepository<Produto, Long> {
    List<Produto> findAll();
}
