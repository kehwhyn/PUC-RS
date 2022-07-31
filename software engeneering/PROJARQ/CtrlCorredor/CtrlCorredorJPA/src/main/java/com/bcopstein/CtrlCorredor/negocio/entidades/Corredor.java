package com.bcopstein.CtrlCorredor.negocio.entidades;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToMany;

import org.springframework.data.annotation.Id;

@Entity
public class Corredor {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String cpf;
    private String nome;
    private String genero;
    @Embedded
    private Data data;
    @OneToMany(
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    private List<Evento> eventos;

    protected Corredor() {}

    public Corredor(String cpf, String nome, Data data, String genero) {
        this.cpf = cpf;
        this.nome = nome;
        this.data = data;
        this.genero = genero;
    }

    public String getCpf() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }

    public String getGenero() {
        return genero;
    }
}