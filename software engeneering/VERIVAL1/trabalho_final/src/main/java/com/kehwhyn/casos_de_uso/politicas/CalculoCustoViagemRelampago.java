package com.kehwhyn.casos_de_uso.politicas;

public class CalculoCustoViagemRelampago extends CalculoCustoViagemBasico {
    @Override
    public double descontoPontuacao() {
        double custobasico = calculoCustoBasico();
        if (getPassageiro().getPontuacaoMedia() > 5.0 && getPassageiro().getQtdadeAvaliacoes() > 30)
            return custobasico * 0.05;    
        return 0.0;
    }

    @Override
    public double descontoPromocaoSazonal() {
        int qtdadeBairros = getRoteiro().bairrosPercoridos().size();
        double cb = calculoCustoBasico();
        if (qtdadeBairros > 3)
            return cb * 0.05;    
        return 0.0;
    }
}