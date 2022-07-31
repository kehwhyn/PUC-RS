package com.bcopstein.CtrlCorredor.negocio.entidades;

import javax.persistence.Embeddable;

@Embeddable
public class Hora {
    private int horas;
    private int minutos;
    private int segundos;

    public int getHoras() {
        return horas;
    }

    public int getMinutos() {
        return minutos;
    }

    public int getSegundos() {
        return segundos;
    }
}
