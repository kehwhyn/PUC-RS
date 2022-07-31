package com.msestoque.adaptadores.repositorios.implementacoes;

import java.util.List;

import com.msestoque.adaptadores.repositorios.interfaces.IEstoqueRepositoryJPA;
import com.msestoque.negocio.entidades.ItemEstoque;
import com.msestoque.negocio.repositorios.IEstoqueRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Estoque_IMPL implements IEstoqueRepository {

  private IEstoqueRepositoryJPA estoqueRepository;

  @Autowired
  public Estoque_IMPL(IEstoqueRepositoryJPA estoqueRepository) {
    this.estoqueRepository = estoqueRepository;
  }

  @Override
  public void atualizaProduto(ItemEstoque itemEstoque) {
    estoqueRepository.save(itemEstoque);
  }

  @Override
  public ItemEstoque getProduto(int codigo) {
    return estoqueRepository.findByProdutoCodigo(codigo);
  }

  @Override
  public List<ItemEstoque> getProdutosDisponiveis() {
     return estoqueRepository.findByQtdadeGreaterThanEqual(1);
  }

}
