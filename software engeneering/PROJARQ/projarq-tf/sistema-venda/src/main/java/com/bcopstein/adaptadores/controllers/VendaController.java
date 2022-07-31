package com.bcopstein.adaptadores.controllers;

import java.util.List;

import com.bcopstein.adaptadores.controllers.DTO.SubtotalDTO;
import com.bcopstein.aplicacao.casosDeUso.CadastraVendaUC;
import com.bcopstein.aplicacao.casosDeUso.ConsultaVendaUC;
import com.bcopstein.aplicacao.casosDeUso.ConsultaVendasUC;
import com.bcopstein.aplicacao.casosDeUso.VerificaEstoqueProdutoUC;
import com.bcopstein.negocio.entidades.Venda;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vendas")
public class VendaController {
  private VerificaEstoqueProdutoUC verificaEstoqueProduto;
  private CadastraVendaUC cadastraVenda;
  private ConsultaVendaUC consultaVenda;
  private ConsultaVendasUC consultaVendas;

  @Autowired
  public VendaController(VerificaEstoqueProdutoUC verificaEstoqueProdutoUC, CadastraVendaUC cadastraVenda,
      ConsultaVendaUC consultaVenda, ConsultaVendasUC consultaVendas) {
    this.verificaEstoqueProduto = verificaEstoqueProdutoUC;
    this.cadastraVenda = cadastraVenda;
    this.consultaVenda = consultaVenda;
    this.consultaVendas = consultaVendas;
  }

  @GetMapping("/autorizacao")
  @CrossOrigin(origins = "*")
  public boolean podeVender(@RequestParam final Integer codProd, @RequestParam final Integer qtdade) {
    return verificaEstoqueProduto.executar(codProd, qtdade);
  }

  @PostMapping("/confirmacao")
  @CrossOrigin(origins = "*")
  public boolean confirmaVenda(@RequestBody final List<SubtotalDTO> itens) {
    return cadastraVenda.executar(itens);
  }

  @PostMapping("/subtotal")
  @CrossOrigin(origins = "*")
  public Integer[] calculaSubtotal(@RequestBody final List<SubtotalDTO> itens) {
    return consultaVenda.executar(itens);
  }

  @GetMapping("/historico")
  @CrossOrigin(origins = "*")
  public List<Venda> vendasEfetuadas() {
    return consultaVendas.executar();
  }
}
