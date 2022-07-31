package com.bcopstein.negocio.servicos;

import com.bcopstein.negocio.entidades.ItemEstoque;
import com.bcopstein.negocio.repositorios.IEstoqueRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicoEstoque {

  private IEstoqueRepository estoqueRepository;

  @Autowired
  public ServicoEstoque(IEstoqueRepository estoqueRepository) {
    this.estoqueRepository = estoqueRepository;
  }

  public boolean podeVender(int codProduto, int quantidade) {

    boolean disponivel = false;
    ItemEstoque produtoEncontrado = estoqueRepository.getProduto(codProduto);
    disponivel = produtoEncontrado.getQtdade() >= quantidade;

    return disponivel;
  }

  public void atualizaProduto(ItemEstoque itemEstoque) {
    estoqueRepository.atualizaProduto(itemEstoque);
  }

  public ItemEstoque getProduto(int codProduto) {
    return estoqueRepository.getProduto(codProduto);
  }
}
