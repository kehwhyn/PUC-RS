package com.bcopstein.aplicacao.servicos;

import java.util.List;

import com.bcopstein.negocio.entidades.ItemCarrinho;

public class ImpostoBrasil implements ICalculoImposto {

  private static final Integer PORCENTAGEM_IMPOSTO = 12;

  @Override
  public Integer calculaImposto(List<ItemCarrinho> produtos) {
    Integer valorTotalCompra = 0;

    for (ItemCarrinho produto : produtos) {
      valorTotalCompra += produto.getPrecoUnitario() * produto.getQuantidade();
    }

    return valorTotalCompra * PORCENTAGEM_IMPOSTO / 100;
  }

}
