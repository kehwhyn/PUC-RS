package com.msvenda.adaptadores.controllers;

import java.util.List;

import com.msvenda.aplicacao.casosDeUso.ConsultaVendasUC;
import com.msvenda.negocio.entidades.Venda;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/msvendas")
public class VendaController {
  private ConsultaVendasUC consultaVendas;

  @Autowired
  public VendaController(ConsultaVendasUC consultaVendas) {
    this.consultaVendas = consultaVendas;
  }

  @GetMapping("/historico")
  @CrossOrigin(origins = "*")
  public List<Venda> vendasEfetuadas() {
    return consultaVendas.executar();
  }
}
