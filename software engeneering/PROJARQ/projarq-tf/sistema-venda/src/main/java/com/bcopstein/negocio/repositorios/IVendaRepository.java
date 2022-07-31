package com.bcopstein.negocio.repositorios;

import java.util.List;

import com.bcopstein.negocio.entidades.Venda;

public interface IVendaRepository {

  void cadastra(Venda venda);

  List<Venda> todos();
}
