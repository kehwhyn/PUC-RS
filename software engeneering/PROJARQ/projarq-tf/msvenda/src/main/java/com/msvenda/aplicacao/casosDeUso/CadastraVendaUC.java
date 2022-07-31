package com.msvenda.aplicacao.casosDeUso;

import com.msvenda.negocio.entidades.Venda;
import com.msvenda.negocio.servicos.ServicoVenda;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CadastraVendaUC {
  private ServicoVenda servicoVenda;

  @Autowired
  public CadastraVendaUC(ServicoVenda servicoVenda) {
    this.servicoVenda = servicoVenda;
  }

  public boolean executar(Venda novaVenda) {
    return servicoVenda.cadastraVenda(novaVenda);
  }
}
