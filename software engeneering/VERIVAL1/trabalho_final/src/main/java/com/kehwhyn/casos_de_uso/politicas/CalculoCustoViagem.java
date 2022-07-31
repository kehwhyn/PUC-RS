package com.kehwhyn.casos_de_uso.politicas;

import com.kehwhyn.entidades.Roteiro;
import com.kehwhyn.entidades.Passageiro;

public interface CalculoCustoViagem {
    double custoViagem();
    double descontoPontuacao();
    public Roteiro getRoteiro();
    double calculoCustoBasico();
    double descontoPromocaoSazonal();
    public Passageiro getPassageiro();
    void defineRoteiro(Roteiro roteiro);
    void definePassageiro(Passageiro passageiro);
}