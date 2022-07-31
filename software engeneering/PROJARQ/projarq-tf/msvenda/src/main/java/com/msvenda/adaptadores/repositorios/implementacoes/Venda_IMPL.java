package com.msvenda.adaptadores.repositorios.implementacoes;

import java.util.List;

import com.msvenda.adaptadores.repositorios.interfaces.IVendaRepositoryJPA;
import com.msvenda.negocio.entidades.Venda;
import com.msvenda.negocio.repositorios.IVendaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Venda_IMPL implements IVendaRepository {

  private IVendaRepositoryJPA vendaRepository;

  @Autowired
  public Venda_IMPL(IVendaRepositoryJPA vendaRepository) {
    this.vendaRepository = vendaRepository;
  }

  @Override
  public List<Venda> todos() {
    List<Venda> vendas = vendaRepository.findAll();
    return vendas;
  }

  @Override
  public void cadastra(Venda venda) {
    vendaRepository.save(venda);
  }
}
