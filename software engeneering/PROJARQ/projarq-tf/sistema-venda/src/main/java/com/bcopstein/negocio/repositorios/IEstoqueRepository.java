package com.bcopstein.negocio.repositorios;

import com.bcopstein.negocio.entidades.ItemEstoque;

public interface IEstoqueRepository {

  ItemEstoque getProduto(int codigo);

  void atualizaProduto(ItemEstoque itemEstoque);

}
