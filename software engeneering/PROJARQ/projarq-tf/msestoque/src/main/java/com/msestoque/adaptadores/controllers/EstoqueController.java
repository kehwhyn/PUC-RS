package com.msestoque.adaptadores.controllers;

import java.util.List;

import com.msestoque.aplicacao.casosDeUso.ObterProdutoUC;
import com.msestoque.aplicacao.casosDeUso.ProdutosDisponiveisUC;
import com.msestoque.negocio.entidades.ItemEstoque;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/msestoque")
public class EstoqueController {
  private ProdutosDisponiveisUC produtosDisponiveis;
  private ObterProdutoUC obterProduto;

  @Autowired
  public EstoqueController(ProdutosDisponiveisUC produtosDisponiveis, ObterProdutoUC obterProduto) {
    this.produtosDisponiveis = produtosDisponiveis;
    this.obterProduto = obterProduto;
  }

  @GetMapping("/produto/{codProduto}")
  @CrossOrigin(origins = "*")
  public ItemEstoque getProduto(@PathVariable("codProduto") int codProduto) {
    return obterProduto.executar(codProduto);

  }

  @GetMapping("/todos")
  @CrossOrigin(origins = "*")
  public List<ItemEstoque> todos() {
    return produtosDisponiveis.executar();
  }

}
