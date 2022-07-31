package com.kehwhyn.casos_de_uso.politicas;

import com.kehwhyn.entidades.Bairro;
import com.kehwhyn.entidades.Roteiro;
import com.kehwhyn.entidades.Passageiro;


public class CalculoCustoViagemBasico implements CalculoCustoViagem {
    private Roteiro roteiro;
    private Passageiro passageiro;

    @Override
    public double custoViagem() {
        return calculoCustoBasico() - descontoPontuacao() - descontoPromocaoSazonal();
    }

    public Roteiro getRoteiro() {
        return roteiro;
    }

    public Passageiro getPassageiro() {
        return passageiro;
    }

    @Override
    public double descontoPontuacao() {
        return 0.0;
    }

    @Override
    public double calculoCustoBasico() {
        return roteiro.bairrosPercoridos()
                .stream()
                .mapToDouble(Bairro::getCustoTransporte)
                .sum();
    }

    @Override
    public double descontoPromocaoSazonal() {
        return 0.0;
    }

    @Override
    public void defineRoteiro(Roteiro roteiro) {
        this.roteiro = roteiro;
    }

    @Override
    public void definePassageiro(Passageiro passageiro) {
        this.passageiro = passageiro;
    }
}