package com.msestoque.aplicacao.casosDeUso;

import com.msestoque.negocio.entidades.ItemEstoque;
import com.msestoque.negocio.servicos.ServicoEstoque;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AtualizaProdutoEstoqueUC {
    
    private ServicoEstoque servicoEstoque;

    @Autowired
    public AtualizaProdutoEstoqueUC(ServicoEstoque servicoEstoque) {
        this.servicoEstoque = servicoEstoque;
    }

    public void executar(ItemEstoque itemEstoque){
        servicoEstoque.atualizaProduto(itemEstoque);
    }
}

