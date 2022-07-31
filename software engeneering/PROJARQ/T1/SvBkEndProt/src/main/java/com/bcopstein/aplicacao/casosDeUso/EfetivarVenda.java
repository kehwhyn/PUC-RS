package com.bcopstein.aplicacao.casosDeUso;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.time.Instant;

import com.bcopstein.negocio.entidades.Venda;
import com.bcopstein.negocio.entidades.ItemVenda;
import com.bcopstein.negocio.servicos.ServicoVenda;
import com.bcopstein.negocio.servicos.ServicoEstoque;
import com.bcopstein.negocio.servicos.ServicoProduto;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class EfetivarVenda {
    private ServicoVenda servicoVenda;
    private ServicoEstoque servicoEstoque;
    private ServicoProduto servicoProduto;

    @Autowired
    public EfetivarVenda(ServicoVenda servicoVenda, ServicoEstoque servicoEstoque, ServicoProduto servicoProduto) {
        this.servicoVenda = servicoVenda;
        this.servicoEstoque = servicoEstoque;
        this.servicoProduto = servicoProduto;
    }

    public boolean run(final ItemVenda[] itens) {

        var estoque = servicoEstoque.todos().get(0);
        var produtos = servicoProduto.todos();

        for (final var item : itens) {
            var produto = produtos.stream().filter(p -> p.getId() == item.getProdutoId()).findAny()
                    .orElse(null);

            if (produto == null) {
                return false;
            }

            var a = estoque.getItemById(produto.getId());
            a.atualizaQuantidadeDisponivel(item.getQuantidade());
        }

        var tmp = new HashSet<ItemVenda>(Arrays.asList(itens));
        var venda = new Venda(100L, Date.from(Instant.now()), tmp);
        servicoVenda.salvaVenda(venda);
        return true;
    }
}
