package com.bcopstein.aplicacao.casosDeUso;

import java.util.ArrayList;
import java.util.List;

import com.bcopstein.adaptadores.controllers.DTO.SubtotalDTO;
import com.bcopstein.negocio.entidades.ItemCarrinho;
import com.bcopstein.negocio.entidades.Produto;
import com.bcopstein.negocio.entidades.Venda;
import com.bcopstein.negocio.servicos.ServicoProduto;
import com.bcopstein.negocio.servicos.ServicoVenda;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CadastraVendaUC {
  private ServicoVenda servicoVenda;
  private ServicoProduto servicoProduto;

  @Autowired
  public CadastraVendaUC(ServicoVenda servicoVenda, ServicoProduto servicoProduto) {
    this.servicoVenda = servicoVenda;
    this.servicoProduto = servicoProduto;
  }

  public boolean executar(List<SubtotalDTO> itensDTO) {

    List<ItemCarrinho> itens = new ArrayList<>(0);
    List<Produto> produtos = servicoProduto.todos();

    for (SubtotalDTO item : itensDTO) {
      Produto novoProduto = produtos.stream().filter(p -> p.getCodigo() == item.getCodigo()).findFirst().orElse(null);

      itens.add(new ItemCarrinho(novoProduto.getCodigo(), Double.valueOf(novoProduto.getPreco()).intValue(),
          item.getQuantidade()));
    }

    Integer[] valores = servicoVenda.consultaVenda(itens);
    Venda novaVenda = new Venda(valores[0], valores[1], valores[2], itens);
    return servicoVenda.cadastraVenda(novaVenda);
  }
}
