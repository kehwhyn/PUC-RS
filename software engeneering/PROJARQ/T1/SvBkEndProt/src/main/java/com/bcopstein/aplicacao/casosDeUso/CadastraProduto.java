package com.bcopstein.aplicacao.casosDeUso;

import com.bcopstein.negocio.entidades.Produto;
import com.bcopstein.negocio.servicos.ServicoProduto;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class CadastraProduto {
    private ServicoProduto servicoDeProduto;

    @Autowired
    public CadastraProduto(ServicoProduto servicoDeProduto) {
        this.servicoDeProduto = servicoDeProduto;
    }

    public void run(Produto produto) {
        this.servicoDeProduto.cadastraProduto(produto);
    }
}
