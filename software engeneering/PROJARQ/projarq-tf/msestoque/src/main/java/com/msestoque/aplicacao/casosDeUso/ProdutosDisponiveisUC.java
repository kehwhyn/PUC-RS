package com.msestoque.aplicacao.casosDeUso;

import java.util.List;

import com.msestoque.negocio.entidades.ItemEstoque;
import com.msestoque.negocio.servicos.ServicoEstoque;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProdutosDisponiveisUC {

  private ServicoEstoque servicoEstoque;

  @Autowired
  public ProdutosDisponiveisUC(ServicoEstoque servicoEstoque) {
    this.servicoEstoque = servicoEstoque;
  }

  public List<ItemEstoque> executar() {
    return servicoEstoque.getProdutosDisponiveis();
  }
    
}
