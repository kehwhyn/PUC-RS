package com.kehwhyn.entidades;

import java.time.LocalDateTime;

public class Viagem {
    private int id;
    private Roteiro roteiro;
    private double valorCobrado;
    private Passageiro passageiro;
    private LocalDateTime dataHora;

    public Viagem(int id, LocalDateTime dataHora, Roteiro roteiro, Passageiro passageiro,double valorCobrado) {
        this.id = id;
        this.roteiro = roteiro;
        this.dataHora = dataHora;
        this.passageiro = passageiro;
        this.valorCobrado = valorCobrado;
    }

    public int getId() {
        return id;
    }

    public Roteiro getRoteiro() {
        return roteiro;
    }

    public double getValorCobrado() {
        return valorCobrado;
    }

    public Passageiro getPassageiro() {
        return passageiro;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    @Override
    public String toString() {
        return "Viagem [valor cobrado=" + valorCobrado + ", dataHora=" + dataHora + ", id=" + id +
                ", passageiro=" + passageiro + ", roteiro=" + roteiro + "]";
    }
}