package com.bcopstein.negocio.entidades;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.JoinColumn;
import javax.persistence.CascadeType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
@Table(name = "item_venda")
public class ItemVenda {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "produto_id", referencedColumnName = "id")
    private Produto produto;

    private long imposto;
    private int quantidade;

    protected ItemVenda() {
    }

    public ItemVenda(long imposto, int quantidade, Produto produto) {
        this.imposto = imposto;
        this.produto = produto;
        this.quantidade = quantidade;
    }

    public long getId() {
        return id;
    }

    public long getProdutoId() {
        return this.produto.getId();
    }

    public Produto getProduto() {
        return produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public double getImposto() {
        return imposto;
    }

    public double getPrecoUnitVenda() {
        return this.produto.getPreco();
    }
}
