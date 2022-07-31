package com.bcopstein.aplicacao.servicos;

import com.bcopstein.negocio.entidades.Venda;

public interface IRestricaoHorarioVenda {
  public boolean vendaIsValida(Venda venda);
}
