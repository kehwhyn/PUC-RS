package com.bcopstein.negocio.servicos;

import java.util.List;

import com.bcopstein.negocio.entidades.Estoque;
import com.bcopstein.negocio.repositorios.EstoqueRepository;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class ServicoEstoque {
    private EstoqueRepository estoqueRepository;

    @Autowired
    public ServicoEstoque(EstoqueRepository estoqueRepository) {
        this.estoqueRepository = estoqueRepository;
    }

    public List<Estoque> todos() {
        return this.estoqueRepository.todos();
    }

    public void cadastraEstoque(Estoque estoque) {
        this.estoqueRepository.cadastraEstoque(estoque);
    }
}
