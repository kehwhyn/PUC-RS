package com.bcopstein.CtrlCorredor.negocio.entidades;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import org.springframework.data.annotation.Id;

@Entity
public class Evento {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String nome;
    // Data do evento
    @Embedded
    private Data data;
    // Distancia percorrida em metros
    private int distancia;
    // Tempo que o corredor levou para percorrer a distancia
    @Embedded
    private Hora hora;

    protected Evento() {}

    public Evento(String nome, Data data, int distancia, Hora hora) {
        this.nome = nome;
        this.data = data;
        this.distancia = distancia;
        this.hora = hora;
    }

    public int getDistancia() {
        return distancia;
    }

    public String getNome() {
        return nome;
    }

    public Data getData() {
        return data;
    }

    public Hora getHora() {
        return hora;
    }

    public int lessThan(Evento evento) {
        var t1 = this.hora.getHoras() * 3600 + this.hora.getMinutos() * 60 + this.hora.getSegundos();
        var t2 = evento.getHora().getHoras() * 3600 + evento.getHora().getMinutos() * 60 + evento.getHora().getSegundos();
        return t1 - t2;
    }
}
