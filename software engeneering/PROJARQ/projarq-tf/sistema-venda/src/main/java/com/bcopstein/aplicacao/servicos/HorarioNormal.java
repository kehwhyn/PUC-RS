package com.bcopstein.aplicacao.servicos;

import com.bcopstein.negocio.entidades.Venda;

public class HorarioNormal implements IRestricaoHorarioVenda {
  private static final int LIMITE_ITENS_VENDA = 10;
  private static final int LIMITE_QUANTIDADE_ITEM = 5;
  private static final int LIMITE_VALOR_TOTAL_VENDA = 25000;

  @Override
  public boolean vendaIsValida(Venda venda) {
    if (venda.getTotal() > LIMITE_VALOR_TOTAL_VENDA) {
      return false;
    }

    if (venda.getProdutos().size() > LIMITE_ITENS_VENDA) {
      return false;
    }

    for (int i = 0; i < venda.getProdutos().size(); i++) {
      if (venda.getProdutos().get(i).getQuantidade() > LIMITE_QUANTIDADE_ITEM) {
        return false;
      }
    }

    return true;
  }

}
