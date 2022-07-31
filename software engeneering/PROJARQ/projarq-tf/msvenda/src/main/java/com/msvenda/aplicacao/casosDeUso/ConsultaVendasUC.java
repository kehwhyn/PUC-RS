package com.msvenda.aplicacao.casosDeUso;

import java.util.List;

import com.msvenda.negocio.entidades.Venda;
import com.msvenda.negocio.servicos.ServicoVenda;

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
