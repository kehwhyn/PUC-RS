package com.bcopstein.aplicacao.servicos;

import java.util.List;

import com.bcopstein.negocio.entidades.ItemCarrinho;

public interface ICalculoImposto {
  Integer calculaImposto(List<ItemCarrinho> produtos);
}
