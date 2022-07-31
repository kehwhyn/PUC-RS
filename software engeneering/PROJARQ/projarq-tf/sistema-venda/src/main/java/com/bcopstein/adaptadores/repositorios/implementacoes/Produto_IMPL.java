package com.bcopstein.adaptadores.repositorios.implementacoes;

import java.util.List;

import com.bcopstein.adaptadores.repositorios.interfaces.IProdutoRepositoryJPA;
import com.bcopstein.negocio.entidades.Produto;
import com.bcopstein.negocio.repositorios.IProdutoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Produto_IMPL implements IProdutoRepository {

  private IProdutoRepositoryJPA produtoRepository;

  @Autowired
  public Produto_IMPL(IProdutoRepositoryJPA produtoRepository) {
    this.produtoRepository = produtoRepository;
  }

  @Override
  public List<Produto> todos() {
    return produtoRepository.findAll();
  }

}
