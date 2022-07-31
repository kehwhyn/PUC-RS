package com.kehwhyn.casos_de_uso.politicas;

public class CalculoCustoViagemVerao extends CalculoCustoViagemBasico {
    //ERRO, o desconto devia ser 0.09 (9%) e não 0.9 (90%)
    @Override
    public double descontoPontuacao() {
        double custobasico = calculoCustoBasico();
        if (getPassageiro().getPontuacaoMedia() > 9.0)
            return custobasico * 0.09;
        return 0.0;
    }

    @Override
    public double descontoPromocaoSazonal() {
        int qtdadeBairros = getRoteiro().bairrosPercoridos().size();
        double cb = calculoCustoBasico();
        if (qtdadeBairros > 2)
            return cb * 0.1;
        return 0.0;
    }
}