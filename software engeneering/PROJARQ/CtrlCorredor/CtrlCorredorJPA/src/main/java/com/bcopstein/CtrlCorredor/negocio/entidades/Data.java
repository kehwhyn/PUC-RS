package com.bcopstein.CtrlCorredor.negocio.entidades;

import javax.persistence.Embeddable;

@Embeddable
public class Data {
    private int dia;
    private int mes;
    private int ano;

    public int getDia() {
        return dia;
    }

    public int getMes() {
        return mes;
    }

    public int getAno() {
        return ano;
    }
}
