package com.msvenda.negocio.repositorios;

import java.util.List;

import com.msvenda.negocio.entidades.Venda;

public interface IVendaRepository {
  void cadastra(Venda venda);

  List<Venda> todos();
}
