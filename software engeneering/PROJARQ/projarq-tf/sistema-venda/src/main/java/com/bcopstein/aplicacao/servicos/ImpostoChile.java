package com.bcopstein.aplicacao.servicos;

import java.util.List;

import com.bcopstein.negocio.entidades.ItemCarrinho;

public class ImpostoChile implements ICalculoImposto {
  private static final Integer VALOR_PARA_IVA_MINIMO = 8000;

  @Override
  public Integer calculaImposto(List<ItemCarrinho> produtos) {
    Integer porcentagemImposto = 15;
    Integer valorTotalCompra = 0;

    for (ItemCarrinho produto : produtos) {
      valorTotalCompra += produto.getPrecoUnitario() * produto.getQuantidade();
    }

    if (valorTotalCompra > VALOR_PARA_IVA_MINIMO) {

      Integer valorRestante = valorTotalCompra - VALOR_PARA_IVA_MINIMO;
      Integer valorParcial = VALOR_PARA_IVA_MINIMO * porcentagemImposto / 100;

      porcentagemImposto = 20;
      valorRestante = valorRestante * porcentagemImposto / 100;

      return valorParcial + valorRestante;
    }

    return valorTotalCompra * porcentagemImposto / 100;
  }

}
