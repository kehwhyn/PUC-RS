package com.bcopstein.negocio.servicos;

import java.util.List;

import com.bcopstein.negocio.entidades.Produto;
import com.bcopstein.negocio.repositorios.ProdutoRepository;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class ServicoProduto {
    private ProdutoRepository produtosRepository;

    @Autowired
    public ServicoProduto(ProdutoRepository produtosRepository) {
        this.produtosRepository = produtosRepository;
    }

    public List<Produto> todos() {
        return produtosRepository.todos();
    }

    public boolean cadastraProduto(Produto produto) {
        return produtosRepository.cadastraProduto(produto);
    }
}
