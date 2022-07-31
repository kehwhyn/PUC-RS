package com.bcopstein.aplicacao.casosDeUso;

import com.bcopstein.negocio.entidades.ItemEstoque;
import com.bcopstein.negocio.servicos.ServicoItemEstoque;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class CadastraItemEstoque {
    private ServicoItemEstoque servicoItemEstoque;

    @Autowired
    public CadastraItemEstoque(ServicoItemEstoque servicoItemEstoque) {
        this.servicoItemEstoque = servicoItemEstoque;
    }

    public void run(ItemEstoque itemEstoque) {
        this.servicoItemEstoque.cadastraItemEstoque(itemEstoque);
    }
}
