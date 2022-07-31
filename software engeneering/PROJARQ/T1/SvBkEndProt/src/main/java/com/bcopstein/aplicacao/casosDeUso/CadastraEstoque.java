package com.bcopstein.aplicacao.casosDeUso;

import com.bcopstein.negocio.entidades.Estoque;
import com.bcopstein.negocio.servicos.ServicoEstoque;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class CadastraEstoque {
    private ServicoEstoque servicoEstoque;

    @Autowired
    public CadastraEstoque(ServicoEstoque servicoEstoque) {
        this.servicoEstoque = servicoEstoque;
    }

    public void run(Estoque estoque) {
        this.servicoEstoque.cadastraEstoque(estoque);
    }
}
