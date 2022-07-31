package com.msvenda.negocio.servicos;

import java.util.List;

import com.msvenda.negocio.entidades.Venda;
import com.msvenda.negocio.repositorios.IVendaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicoVenda {
  private IVendaRepository vendaRepository;

  @Autowired
  public ServicoVenda(IVendaRepository vendaRepository) {
    this.vendaRepository = vendaRepository;
  }

  public List<Venda> todos() {
    return vendaRepository.todos();
  }

  public boolean cadastraVenda(Venda novaVenda) {
    this.vendaRepository.cadastra(novaVenda);

    return true;
  }
}