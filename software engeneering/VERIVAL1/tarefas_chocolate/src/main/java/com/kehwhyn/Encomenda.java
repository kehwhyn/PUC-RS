package com.kehwhyn;

public class Encomenda {
    private int estqUmKg;
    private int estqCincoKg;

    public Encomenda(int estqUmKg,int estqCincoKg) { 
        this.estqUmKg = estqUmKg;
        this.estqCincoKg = estqCincoKg;
    }

    public int[] qtdadeBarras(int peso) {    
        if(peso < 0) {
            return new int[] { -1, 0 };
        }

        if(peso == 0) {
            return new int[2];
        }

        int[] response = { peso/5, peso%5 };

        if(response[0] > this.estqCincoKg || response[1] > this.estqUmKg) {
            return new int[] { -1, 0 };
        }

        if(response[0] > this.estqCincoKg) {
            int diferenca = response[0] - this.estqCincoKg;
            response[1] += diferenca * 5;
        }

        return response;
    }
}
