package com.bcopstein.CtrlCorredor.dominio;

public class EstatisticasDTO {

    private double media, mediana, desvioPadrao;

    public EstatisticasDTO(double media, double mediana, double desvioPadrao) {
        this.media = media;
        this.mediana = mediana;
        this.desvioPadrao = desvioPadrao;
    }

    double getMedia() {
        return this.media;
    }

    double getMediana() {
        return this.mediana;
    }

    double getDesvioPadrao() {
        return this.desvioPadrao;
    }
}
