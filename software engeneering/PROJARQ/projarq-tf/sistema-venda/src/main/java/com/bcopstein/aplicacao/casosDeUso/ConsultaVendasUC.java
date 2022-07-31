package com.bcopstein.aplicacao.casosDeUso;

import com.bcopstein.negocio.servicos.ServicoVenda;

import java.util.List;

import com.bcopstein.negocio.entidades.Venda;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConsultaVendasUC {
  private ServicoVenda servicoVenda;

  @Autowired
  public ConsultaVendasUC(ServicoVenda servicoVenda) {
    this.servicoVenda = servicoVenda;
  }

  public List<Venda> executar() {
    return servicoVenda.todos();
  }
}
