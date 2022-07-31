package com.bcopstein.aplicacao.casosDeUso;

import java.util.List;

import com.bcopstein.negocio.entidades.Estoque;
import com.bcopstein.negocio.servicos.ServicoEstoque;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class ConsultaEstoque {
    private ServicoEstoque servicoEstoque;

    @Autowired
    public ConsultaEstoque(ServicoEstoque servicoEstoque) {
        this.servicoEstoque = servicoEstoque;
    }

    public List<Estoque> run() {
        return this.servicoEstoque.todos();
    }
}
