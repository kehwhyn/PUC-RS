package com.bcopstein.aplicacao.casosDeUso;

import com.bcopstein.negocio.entidades.ItemEstoque;
import com.bcopstein.negocio.servicos.ServicoEstoque;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AtualizaProdutoEstoqueUC {

  private ServicoEstoque servicoEstoque;

  @Autowired
  public AtualizaProdutoEstoqueUC(ServicoEstoque servicoEstoque) {
    this.servicoEstoque = servicoEstoque;
  }

  public void executar(ItemEstoque itemEstoque) {
    servicoEstoque.atualizaProduto(itemEstoque);
  }
}
