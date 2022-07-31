package com.bcopstein.aplicacao.casosDeUso;

import java.util.List;

import com.bcopstein.negocio.entidades.ItemEstoque;
import com.bcopstein.negocio.servicos.ServicoItemEstoque;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class ConsultaItemEstoque {
    private ServicoItemEstoque servicoItemEstoque;

    @Autowired
    public ConsultaItemEstoque(ServicoItemEstoque servicoItemEstoque) {
        this.servicoItemEstoque = servicoItemEstoque;
    }

    public List<ItemEstoque> run() {
        return this.servicoItemEstoque.todos();
    }
}

