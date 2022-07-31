package com.bcopstein.negocio.entidades;

import java.util.Set;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.JoinColumn;
import javax.persistence.CascadeType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import org.springframework.beans.factory.annotation.Autowired;

@Entity
@Table(name = "estoque")
public class Estoque {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "estoque_id")
    private Set<ItemEstoque> itens;

    protected Estoque() {
    }

    @Autowired
    public Estoque(Set<ItemEstoque> itens) {
        this.itens = itens;
    }

    public long getId() {
        return id;
    }

    public Set<ItemEstoque> getItens() {
        return itens;
    }

    public ItemEstoque getItemById(long id) {
        for(var item : itens) {
            if (item.getProdutoId() == id)
                return item;
        }
        return null;
    }
}
