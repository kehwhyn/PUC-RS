package com.bcopstein.aplicacao.servicos;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import com.bcopstein.negocio.entidades.ItemVenda;
import com.bcopstein.negocio.servicos.ServicoProduto;

@Component
public class CalculaSubtotal {
    private ServicoProduto servicoProduto;

    @Autowired
    public CalculaSubtotal(ServicoProduto servicoProduto) {
        this.servicoProduto = servicoProduto;
    }

    public double[] run(ItemVenda[] itens) {
        var produtos = servicoProduto.todos();
        Double imposto = 0.0;
        Double subtotal = 0.0;

        for (var item : itens) {
            // Procurar o produto pelo código
            var produto = produtos.stream().filter(p -> p.getId() == item.getProdutoId()).findAny().orElse(null);

            if (produto != null) {
                subtotal += (int) (produto.getPreco() * item.getQuantidade());
            } else {
                throw new IllegalArgumentException("Codigo invalido");
            }
        }
        imposto = subtotal * 0.1;

        return new double[] { subtotal, imposto, subtotal + imposto };
    }
}
