package com.bcopstein.negocio.servicos;

import java.util.List;

import com.bcopstein.negocio.entidades.ItemEstoque;
import com.bcopstein.negocio.repositorios.ItemEstoqueRepository;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class ServicoItemEstoque {
    private ItemEstoqueRepository itemEstoqueRepository;

    @Autowired
    public ServicoItemEstoque(ItemEstoqueRepository itemEstoqueRepository) {
        this.itemEstoqueRepository = itemEstoqueRepository;
    }

    public List<ItemEstoque> todos() {
        return this.itemEstoqueRepository.todos();
    }

    public void cadastraItemEstoque(ItemEstoque itemEstoque) {
        this.itemEstoqueRepository.cadastraItemEstoque(itemEstoque);
    }
}
