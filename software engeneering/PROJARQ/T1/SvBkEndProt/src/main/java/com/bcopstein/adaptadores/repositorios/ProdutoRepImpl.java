package com.bcopstein.adaptadores.repositorios;

import java.util.List;

import com.bcopstein.negocio.entidades.Produto;
import com.bcopstein.negocio.repositorios.ProdutoRepository;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;

@Repository
public class ProdutoRepImpl implements ProdutoRepository {
    private ProdutoRepJPA produtoRepJPA;

    @Autowired
    public ProdutoRepImpl(ProdutoRepJPA produtoRepJPA) {
        this.produtoRepJPA = produtoRepJPA;
    }

    @Override
    public List<Produto> todos() {
        return produtoRepJPA.findAll();
    }

    @Override
    public boolean cadastraProduto(Produto produto) {
        produtoRepJPA.save(produto);
        return true;
    }
}
