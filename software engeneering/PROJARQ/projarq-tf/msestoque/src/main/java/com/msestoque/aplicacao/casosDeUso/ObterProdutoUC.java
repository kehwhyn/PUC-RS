package com.msestoque.aplicacao.casosDeUso;

import com.msestoque.negocio.entidades.ItemEstoque;
import com.msestoque.negocio.servicos.ServicoEstoque;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ObterProdutoUC {
    
    private ServicoEstoque servicoEstoque;

  @Autowired
  public ObterProdutoUC(ServicoEstoque servicoEstoque) {
    this.servicoEstoque = servicoEstoque;
  }

  public ItemEstoque executar(int codProduto) {
    return servicoEstoque.getProduto(codProduto);
  }
}
