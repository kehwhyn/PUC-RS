package com.bcopstein.negocio.servicos;

import com.bcopstein.negocio.repositorios.VendaRepository;

import java.util.List;

import com.bcopstein.negocio.entidades.Venda;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class ServicoVenda {
    private VendaRepository vendasRepository;

    @Autowired
    public ServicoVenda(VendaRepository vendasRepository) {
        this.vendasRepository = vendasRepository;
    }

    public List<Venda> todos() {
        return vendasRepository.todos();
    }

    public void salvaVenda(Venda venda) {
        vendasRepository.salvar(venda);
    }
}
