package com.msestoque.negocio.repositorios;

import java.util.List;

import com.msestoque.negocio.entidades.ItemEstoque;

public interface IEstoqueRepository {

  void atualizaProduto(ItemEstoque itemEstoque);

  ItemEstoque getProduto(int codigo);

  List<ItemEstoque> getProdutosDisponiveis();



}
