package com.bcopstein.CtrlCorredor.dominio;

public class PerformanceDTO {
    private double diferenca;
    private String prova1, prova2;

    public PerformanceDTO(String prova1, String prova2, double diferenca) {
        this.prova1 = prova1;
        this.prova2 = prova2;
        this.diferenca = diferenca;
    }

    String getProva1() {
        return this.prova1;
    }

    String getProva2() {
        return this.prova2;
    }

    Double getDiferenca() {
        return this.diferenca;
    }
}
