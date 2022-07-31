package com.bcopstein.adaptadores.controllers;

import java.util.List;

import com.bcopstein.aplicacao.casosDeUso.AtualizaProdutoEstoqueUC;
import com.bcopstein.aplicacao.casosDeUso.ConsultaProdutosUC;
import com.bcopstein.negocio.entidades.ItemEstoque;
import com.bcopstein.negocio.entidades.Produto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vendas")
public class ProdutoController {
  private ConsultaProdutosUC consultaProdutos;
  private AtualizaProdutoEstoqueUC atualizaEstoque;

  @Autowired
  public ProdutoController(ConsultaProdutosUC consultaProdutos, AtualizaProdutoEstoqueUC atualizaEstoque) {
    this.consultaProdutos = consultaProdutos;
    this.atualizaEstoque = atualizaEstoque;
  }

  @GetMapping("/produtos")
  @CrossOrigin(origins = "*")
  public List<Produto> listaProdutos() {
    return consultaProdutos.executar();
  }

  @PatchMapping("/produto")
  @CrossOrigin(origins = "*")
  public void atualizaProduto(@RequestBody final ItemEstoque itemEstoque) {
    atualizaEstoque.executar(itemEstoque);
  }
}
