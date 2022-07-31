package com.bcopstein.adaptadores.controlers;

import java.util.List;
import java.util.HashSet;
import java.util.ArrayList;

import com.bcopstein.negocio.entidades.Venda;
import com.bcopstein.negocio.entidades.Estoque;
import com.bcopstein.negocio.entidades.Produto;
import com.bcopstein.negocio.entidades.ItemVenda;
import com.bcopstein.negocio.entidades.ItemEstoque;
import com.bcopstein.aplicacao.servicos.Autorizacao;
import com.bcopstein.aplicacao.servicos.CalculaSubtotal;
import com.bcopstein.aplicacao.casosDeUso.EfetivarVenda;
import com.bcopstein.aplicacao.casosDeUso.CadastraEstoque;
import com.bcopstein.aplicacao.casosDeUso.CadastraProduto;
import com.bcopstein.aplicacao.casosDeUso.ConsultaEstoque;
import com.bcopstein.aplicacao.casosDeUso.HistoricoVendas;
import com.bcopstein.aplicacao.casosDeUso.ConsultaProdutos;
import com.bcopstein.aplicacao.casosDeUso.CadastraItemEstoque;
import com.bcopstein.aplicacao.casosDeUso.ConsultaItemEstoque;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vendas")
public class SvFachadaRemota {
  private final Autorizacao autorizacao;
  private final EfetivarVenda efetivarVenda;
  private final CalculaSubtotal calculaSubtotal;
  private final HistoricoVendas historicoVendas;
  private final CadastraProduto cadastraProduto;
  private final CadastraEstoque cadastraEstoque;
  private final ConsultaEstoque consultaEstoque;
  private final ConsultaProdutos consultaProdutos;
  private final CadastraItemEstoque cadastraItemEstoque;
  private final ConsultaItemEstoque consultaItemEstoque;

  public SvFachadaRemota(Autorizacao autorizacao, EfetivarVenda efetivarVenda, CalculaSubtotal calculaSubtotal,
      HistoricoVendas historicoVendas, CadastraProduto cadastraProduto, CadastraEstoque cadastraEstoque,
      ConsultaEstoque consultaEstoque, ConsultaProdutos consultaProdutos, CadastraItemEstoque cadastraItemEstoque,
      ConsultaItemEstoque consultaItemEstoque) {
    this.autorizacao = autorizacao;
    this.efetivarVenda = efetivarVenda;
    this.calculaSubtotal = calculaSubtotal;
    this.historicoVendas = historicoVendas;

    var produtos = new ArrayList<Produto>() {
      {
        add(new Produto("Geladeira", 2500.0));
        add(new Produto("Fogao", 1200.0));
        add(new Produto("Lava louça", 4300.0));
        add(new Produto("Lava roupa", 3350.0));
        add(new Produto("Aspirador de pó", 780.0));
      }
    };

    this.cadastraProduto = cadastraProduto;
    this.cadastraProduto.run(produtos.get(0));
    this.cadastraProduto.run(produtos.get(1));
    this.cadastraProduto.run(produtos.get(2));
    this.cadastraProduto.run(produtos.get(3));
    this.cadastraProduto.run(produtos.get(4));

    var itensEstoque = new ArrayList<ItemEstoque>() {
      {
        add(new ItemEstoque(produtos.get(0), 10));
        add(new ItemEstoque(produtos.get(1), 0));
        add(new ItemEstoque(produtos.get(2), 7));
        add(new ItemEstoque(produtos.get(3), 11));
        add(new ItemEstoque(produtos.get(4), 22));
      }
    };

    this.cadastraItemEstoque = cadastraItemEstoque;
    this.cadastraItemEstoque.run(itensEstoque.get(0));
    this.cadastraItemEstoque.run(itensEstoque.get(1));
    this.cadastraItemEstoque.run(itensEstoque.get(2));
    this.cadastraItemEstoque.run(itensEstoque.get(3));
    this.cadastraItemEstoque.run(itensEstoque.get(4));

    var estoque = new Estoque(new HashSet<ItemEstoque>(itensEstoque));
    this.cadastraEstoque = cadastraEstoque;
    this.cadastraEstoque.run(estoque);

    this.consultaEstoque = consultaEstoque;
    this.consultaProdutos = consultaProdutos;
    this.consultaItemEstoque = consultaItemEstoque;
  }

  @GetMapping("/produtos")
  @CrossOrigin(origins = "*")
  public List<Produto> listaProdutos() {
    return consultaProdutos.run();
  }

  @GetMapping("/estoque")
  @CrossOrigin(origins = "*")
  public List<Estoque> listaEstoque() {
    return consultaEstoque.run();
  }

  @GetMapping("/itemEstoque")
  @CrossOrigin(origins = "*")
  public List<ItemEstoque> listaItemEstoque() {
    return consultaItemEstoque.run();
  }

  @GetMapping("/autorizacao")
  @CrossOrigin(origins = "*")
  public boolean podeVender(@RequestParam final Integer codProd, @RequestParam final Integer qtdade) {
    return autorizacao.run(codProd, qtdade);
  }

  /**
   * Esse acontece quando o usuário aperta o botão então todos os items da venda
   * vão para a DTO lá
   */
  @PostMapping("/confirmacao")
  @CrossOrigin(origins = "*")
  public boolean confirmaVenda(@RequestBody final ItemVenda[] itens) {
    return efetivarVenda.run(itens);
  }

  /// retorna as vendas efetuadas
  @GetMapping("/historico")
  @CrossOrigin(origins = "*")
  public List<Venda> vendasEfetuadas() {
    return historicoVendas.run();
  }

  /// calcula o preco que aparece na interface
  @PostMapping("/subtotal")
  @CrossOrigin(origins = "*")
  public double[] calculaPrecos(@RequestBody final ItemVenda[] itens) {
    return calculaSubtotal.run(itens);
  }
}
