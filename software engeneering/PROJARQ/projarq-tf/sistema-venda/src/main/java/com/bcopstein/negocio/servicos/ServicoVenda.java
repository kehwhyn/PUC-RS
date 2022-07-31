package com.bcopstein.negocio.servicos;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import com.bcopstein.aplicacao.servicos.ICalculoImposto;
import com.bcopstein.aplicacao.servicos.IRestricaoHorarioVenda;
import com.bcopstein.aplicacao.servicos.RestricaoVendaFactory;
import com.bcopstein.negocio.entidades.ItemCarrinho;
import com.bcopstein.negocio.entidades.ItemEstoque;
import com.bcopstein.negocio.entidades.Venda;
import com.bcopstein.negocio.repositorios.IVendaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicoVenda {
  private IVendaRepository vendaRepository;
  private ICalculoImposto calculoImposto;
  private ServicoEstoque servicoEstoque;

  @Autowired
  public ServicoVenda(IVendaRepository vendaRepository, ICalculoImposto calculoImposto, ServicoEstoque servicoEstoque) {
    this.vendaRepository = vendaRepository;
    this.calculoImposto = calculoImposto;
    this.servicoEstoque = servicoEstoque;
  }

  public boolean cadastraVenda(Venda novaVenda) {
    IRestricaoHorarioVenda restricaoVenda = RestricaoVendaFactory.getInstance(LocalTime.now());
    boolean vendaIsValida = restricaoVenda.vendaIsValida(novaVenda);

    if (!vendaIsValida) {
      return false;
    }

    List<ItemCarrinho> produtos = novaVenda.getProdutos();
    List<ItemEstoque> bkpEstoque = new ArrayList<>(0);

    for (ItemCarrinho produto : produtos) {
      boolean podeVender = servicoEstoque.podeVender(produto.getCodProduto(), produto.getQuantidade());

      if (!podeVender) {
        return false;
      }
    }

    for (ItemCarrinho produto : produtos) {
      ItemEstoque itemEstoque = servicoEstoque.getProduto(produto.getCodProduto());
      bkpEstoque.add(itemEstoque);

      itemEstoque.setQtdade(itemEstoque.getQtdade() - produto.getQuantidade());

      try {
        servicoEstoque.atualizaProduto(itemEstoque);
      } catch (Exception ex) {
        desfazerBaixaEstoque(bkpEstoque);
        System.out.println("Não foi possível efetuar venda. Desfazendo baixa no estoque...");
        return false;
      }
    }

    this.vendaRepository.cadastra(novaVenda);

    return true;
  }

  public Integer[] consultaVenda(List<ItemCarrinho> itens) {
    Integer subtotal = 0;
    Integer imposto = 0;

    for (final ItemCarrinho prod : itens) {
      if (prod != null) {
        subtotal += (int) prod.getPrecoUnitario() * prod.getQuantidade();
      } else {
        throw new IllegalArgumentException("Codigo invalido");
      }
    }

    imposto = calculoImposto.calculaImposto(itens);

    final Integer[] resp = new Integer[3];

    resp[0] = subtotal;
    resp[1] = imposto;
    resp[2] = subtotal + imposto;

    return resp;
  }

  public List<Venda> todos() {
    return vendaRepository.todos();
  }

  void desfazerBaixaEstoque(List<ItemEstoque> bkpEstoque) {
    for (ItemEstoque itemEstoque : bkpEstoque) {
      servicoEstoque.atualizaProduto(itemEstoque);
    }
  }
}
