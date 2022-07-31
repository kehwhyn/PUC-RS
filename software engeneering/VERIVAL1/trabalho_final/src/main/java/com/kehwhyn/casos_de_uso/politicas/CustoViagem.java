package com.kehwhyn.casos_de_uso.politicas;

import com.kehwhyn.entidades.Roteiro;
import com.kehwhyn.entidades.Passageiro;

public class CustoViagem {
    private CalculoCustoViagem ccv;

    public CustoViagem(CalculoCustoViagem ccv) {
        this.ccv = ccv;
    }

    public double custoViagem(Roteiro roteiro, Passageiro passageiro) {
        ccv.defineRoteiro(roteiro);
        ccv.definePassageiro(passageiro);
        return ccv.custoViagem();
    }
}