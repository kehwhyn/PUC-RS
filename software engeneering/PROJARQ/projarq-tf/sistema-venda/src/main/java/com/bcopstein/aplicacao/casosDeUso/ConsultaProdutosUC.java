package com.bcopstein.aplicacao.casosDeUso;

import java.util.List;

import com.bcopstein.negocio.entidades.Produto;
import com.bcopstein.negocio.servicos.ServicoProduto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConsultaProdutosUC {
  private ServicoProduto servicoProduto;

  @Autowired
  public ConsultaProdutosUC(ServicoProduto servicoProduto) {
    this.servicoProduto = servicoProduto;
  }

  public List<Produto> executar() {
    return servicoProduto.todos();
  }
}
