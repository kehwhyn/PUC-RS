package com.bcopstein.adaptadores.repositorios;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;

import com.bcopstein.negocio.entidades.ItemEstoque;
import com.bcopstein.negocio.repositorios.ItemEstoqueRepository;

@Repository
public class ItemEstoqueRepImpl implements ItemEstoqueRepository {
    private ItemEstoqueRepJPA itemEstoqueRepJPA;

    @Autowired
    public ItemEstoqueRepImpl(ItemEstoqueRepJPA itemEstoqueRepJPA) {
        this.itemEstoqueRepJPA = itemEstoqueRepJPA;
    }

    @Override
    public List<ItemEstoque> todos() {
        return itemEstoqueRepJPA.findAll();
    }

    @Override
    public void cadastraItemEstoque(ItemEstoque itemEstoque) {
        // TODO Auto-generated method stub

    }

}
