package com.bcopstein.negocio.repositorios;

import java.util.List;

import com.bcopstein.negocio.entidades.Produto;

public interface IProdutoRepository {

  List<Produto> todos();

}
