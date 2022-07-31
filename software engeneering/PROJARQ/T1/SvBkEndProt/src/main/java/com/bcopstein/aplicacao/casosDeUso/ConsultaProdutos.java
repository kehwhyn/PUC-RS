package com.bcopstein.aplicacao.casosDeUso;

import java.util.List;

import com.bcopstein.negocio.entidades.Produto;
import com.bcopstein.negocio.servicos.ServicoProduto;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class ConsultaProdutos {
    private ServicoProduto servicoDeProduto;

    @Autowired
    public ConsultaProdutos(ServicoProduto servicoDeProduto) {
        this.servicoDeProduto = servicoDeProduto;
    }

    public List<Produto> run() {
        return this.servicoDeProduto.todos();
    }
}
