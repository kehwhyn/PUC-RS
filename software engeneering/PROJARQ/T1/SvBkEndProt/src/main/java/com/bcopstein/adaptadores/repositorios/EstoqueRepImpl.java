package com.bcopstein.adaptadores.repositorios;

import java.util.List;

import com.bcopstein.negocio.entidades.Estoque;
import com.bcopstein.negocio.repositorios.EstoqueRepository;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;

@Repository
public class EstoqueRepImpl implements EstoqueRepository {

    private EstoqueRepJPA estoqueRepJPA;

    @Autowired
    public EstoqueRepImpl(EstoqueRepJPA estoqueRepJPA) {
        this.estoqueRepJPA = estoqueRepJPA;
    }

    @Override
    public List<Estoque> todos() {
        return this.estoqueRepJPA.findAll();
    }

    @Override
    public void cadastraEstoque(Estoque estoque) {
        this.estoqueRepJPA.save(estoque);
    }
}
