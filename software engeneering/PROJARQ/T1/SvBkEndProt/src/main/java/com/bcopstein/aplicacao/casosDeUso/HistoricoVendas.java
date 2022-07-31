package com.bcopstein.aplicacao.casosDeUso;

import java.util.List;

import com.bcopstein.negocio.entidades.Venda;
import com.bcopstein.negocio.servicos.ServicoVenda;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class HistoricoVendas {
    private ServicoVenda servicoDeVenda;

    @Autowired
    public HistoricoVendas(ServicoVenda servicoDeVenda) {
        this.servicoDeVenda = servicoDeVenda;
    }

    public List<Venda> run() {
        return this.servicoDeVenda.todos();
    }
}
