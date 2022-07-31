package com.bcopstein.adaptadores.repositorios.implementacoes;

import java.util.List;

import com.bcopstein.adaptadores.repositorios.interfaces.IVendaRepositoryJPA;
import com.bcopstein.negocio.entidades.Venda;
import com.bcopstein.negocio.repositorios.IVendaRepository;

import org.springframework.beans.factory.annotation.Autowired;

public class VendaJpa_IMPL implements IVendaRepository {

  @Autowired
  private IVendaRepositoryJPA vendaRepository;

  @Override
  public void cadastra(Venda venda) {
    vendaRepository.save(venda);
  }

  @Override
  public List<Venda> todos() {
    List<Venda> vendas = vendaRepository.findAll();
    return vendas;
  }

}
