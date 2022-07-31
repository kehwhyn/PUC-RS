package com.bcopstein.aplicacao.servicos;

import com.bcopstein.negocio.servicos.ServicoEstoque;
import com.bcopstein.negocio.servicos.ServicoProduto;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class Autorizacao {
    private ServicoEstoque servicoEstoque;
    private ServicoProduto servicoProduto;

    @Autowired
    public Autorizacao(ServicoProduto servicoProduto, ServicoEstoque servicoEstoque) {
        this.servicoProduto = servicoProduto;
        this.servicoEstoque = servicoEstoque;
    }

    public boolean run(Integer codProd, Integer qtdade) {
        var estoque = this.servicoEstoque.todos();
        var produtoEstoque = estoque.get(0).getItemById(codProd);
        return this.servicoProduto.todos().stream()
        .anyMatch(p -> p.getId() == codProd && produtoEstoque.getQuantidadeDisponivel() >= qtdade);
    }
}
