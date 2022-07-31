package com.bcopstein.negocio.entidades;

import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.JoinColumn;
import javax.persistence.CascadeType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
@Table(name = "item_estoque")
public class ItemEstoque {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "produto_id", referencedColumnName = "id")
    private Produto produto;

    private int quantidadeDisponivel;

    protected ItemEstoque() {
    }

    @Autowired
    public ItemEstoque(Produto produto, int quantidadeDisponivel) {
        this.produto = produto;
        this.quantidadeDisponivel = quantidadeDisponivel;
    }

    public long getId() {
        return id;
    }

    public long getProdutoId() {
        return produto.getId();
    }
    
    public Produto getProduto() {
        return produto;
    }

    public int getQuantidadeDisponivel() {
        return quantidadeDisponivel;
    }

    public void atualizaQuantidadeDisponivel(int quantidade) {
        this.quantidadeDisponivel -= quantidade;
    }
}
