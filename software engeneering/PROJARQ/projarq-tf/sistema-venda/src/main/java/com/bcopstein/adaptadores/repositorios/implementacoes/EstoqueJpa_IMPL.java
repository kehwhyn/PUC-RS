package com.bcopstein.adaptadores.repositorios.implementacoes;

import com.bcopstein.adaptadores.repositorios.interfaces.IEstoqueRepositoryJPA;
import com.bcopstein.negocio.entidades.ItemEstoque;
import com.bcopstein.negocio.repositorios.IEstoqueRepository;

import org.springframework.beans.factory.annotation.Autowired;

public class EstoqueJpa_IMPL implements IEstoqueRepository {

  @Autowired
  private IEstoqueRepositoryJPA estoqueRepository;

    
  @Override
  public ItemEstoque getProduto(int codigo) {
    return estoqueRepository.findByProdutoCodigo(codigo);
  }

  @Override
  public void atualizaProduto(ItemEstoque itemEstoque) {
    estoqueRepository.save(itemEstoque);
  }

}
