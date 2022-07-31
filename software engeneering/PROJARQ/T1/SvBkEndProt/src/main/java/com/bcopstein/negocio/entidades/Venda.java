package com.bcopstein.negocio.entidades;

import java.util.Set;
import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.JoinColumn;
import javax.persistence.CascadeType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
@Table(name = "venda")
public class Venda {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "venda_id")
    private Set<ItemVenda> itens;

    private Date date;

    protected Venda() {
    }

    public Venda(long identificador, Date date, Set<ItemVenda> itens) {
        this.date = date;
        this.itens = itens;
    }

    public long getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public Set<ItemVenda> getItens() {
        return itens;
    }
}
