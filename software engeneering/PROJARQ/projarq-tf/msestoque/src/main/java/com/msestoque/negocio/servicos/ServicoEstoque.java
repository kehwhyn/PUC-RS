package com.msestoque.negocio.servicos;

import java.util.List;

import com.msestoque.negocio.entidades.ItemEstoque;
import com.msestoque.negocio.repositorios.IEstoqueRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicoEstoque {

  private IEstoqueRepository estoqueRepository;

  @Autowired
  public ServicoEstoque(IEstoqueRepository estoqueRepository) {
    this.estoqueRepository = estoqueRepository;
  }

  public void atualizaProduto(ItemEstoque itemEstoque) {
    estoqueRepository.atualizaProduto(itemEstoque);
  }

  public List<ItemEstoque> getProdutosDisponiveis() {
    return estoqueRepository.getProdutosDisponiveis();
  }

  public ItemEstoque getProduto(int codProduto) {
    return estoqueRepository.getProduto(codProduto);
  }

}
