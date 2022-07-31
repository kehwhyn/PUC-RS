package com.kehwhyn;

public class DepComb {
    public enum SITUACAO {
        NORMAL, SOBRAVISO, EMERGENCIA
    }

    public enum TIPOPOSTO {
        COMUM, ESTRATEGICO
    }

    public static final int MAX_ALCOOL = 2500;
    public static final int MAX_ADITIVO = 500;
    public static final int MAX_GASOLINA = 10000;

    private static final int ALCOOL_25 = (int) (MAX_ALCOOL * .25);
    private static final int ADITIVO_25 = (int) (MAX_ADITIVO * .25);
    private static final int GASOLINA_25 = (int) (MAX_GASOLINA * .25);

    private static final int ALCOOL_50 = (int) (MAX_ALCOOL * .5);
    private static final int ADITIVO_50 = (int) (MAX_ADITIVO * .5);
    private static final int GASOLINA_50 = (int) (MAX_GASOLINA * .5);

    private SITUACAO situacao;
    private int tanqueAditivo, tanqueGasolina, tanqueAlcool1, tanqueAlcool2;

    public DepComb(int tanqueAditivo, int tanqueGasolina, int tanqueAlcool1, int tanqueAlcool2)
            throws IllegalArgumentException {
        // 4 -> Não é daqui, mas tem que fazer casos de testes pras excessões do construtor também
        if (tanqueAditivo < 0 || tanqueAditivo > MAX_ADITIVO)
            throw new IllegalArgumentException("Quantidade de aditivo inválida.");

        if (tanqueGasolina < 0 || tanqueGasolina > MAX_GASOLINA)
            throw new IllegalArgumentException("Quantidade de gasolina inválida.");

        if (tanqueAlcool1 < 0 || tanqueAlcool2 < 0 || tanqueAlcool1 + tanqueAlcool2 > MAX_ALCOOL)
            throw new IllegalArgumentException("Quantidade de álcool inválida.");

        this.tanqueAditivo = tanqueAditivo;
        this.tanqueAlcool1 = (tanqueAlcool1 + tanqueAlcool2) / 2;
        this.tanqueAlcool2 = this.tanqueAlcool1;
        this.tanqueGasolina = tanqueGasolina;
        defineSituacao();
    }

    public void defineSituacao() {
        if (umDosTanquesAbaixo25())
            this.situacao = SITUACAO.EMERGENCIA;
        else if (umDosTanquesAbaixo50())
            this.situacao = SITUACAO.SOBRAVISO;
        else
            this.situacao = SITUACAO.NORMAL;
    }

    private boolean umDosTanquesAbaixo25() {
        int quantidadeTanqueAlcool = getTanqueAlcool1() + getTanqueAlcool2();
        return getTanqueAditivo() <= ADITIVO_25 || getTanqueGasolina() <= GASOLINA_25
                || quantidadeTanqueAlcool <= ALCOOL_25;
    }

    private boolean umDosTanquesAbaixo50() {
        int quantidadeTanqueAlcool = getTanqueAlcool1() + getTanqueAlcool2();
        return getTanqueAditivo() <= ADITIVO_50 || getTanqueGasolina() <= GASOLINA_50
                || quantidadeTanqueAlcool <= ALCOOL_50;
    }

    public SITUACAO getSituacao() {
        return this.situacao;
    }

    public int getTanqueGasolina() {
        return this.tanqueGasolina;
    }

    public int getTanqueAditivo() {
        return this.tanqueAditivo;
    }

    public int getTanqueAlcool1() {
        return this.tanqueAlcool1;
    }

    public int getTanqueAlcool2() {
        return this.tanqueAlcool2;
    }

    public int recebeAditivo(int quantidade) {
        if (quantidadeInvalida(quantidade))
            return -1;

        int quantoPossoArmazenar = MAX_ADITIVO - getTanqueAditivo();

        if (quantidade <= quantoPossoArmazenar) {
            this.tanqueAditivo += quantidade;
            return quantidade;
        }

        this.tanqueAditivo += quantoPossoArmazenar;

        defineSituacao();

        return quantoPossoArmazenar;
    }

    private boolean quantidadeInvalida(int quantidade) {
        return quantidade <= 0;
    }

    public int recebeGasolina(int quantidade) {
        if (quantidadeInvalida(quantidade))
            return -1;

        int quantoPossoArmazenar = MAX_GASOLINA - getTanqueGasolina();

        if (quantidade <= quantoPossoArmazenar) {
            this.tanqueAditivo += quantidade;
            return quantidade;
        }

        this.tanqueAditivo += quantoPossoArmazenar;

        defineSituacao();

        return quantoPossoArmazenar;
    }

    public int recebeAlcool(int quantidade) {
        if (quantidadeInvalida(quantidade))
            return -1;

        int quantoPossoArmazenar = MAX_ALCOOL - getTanqueAlcool1() - getTanqueAlcool2();

        if (quantidade <= quantoPossoArmazenar) {
            this.tanqueAlcool1 = (getTanqueAlcool1() + getTanqueAlcool2() + quantidade) / 2;
            this.tanqueAlcool2 = this.tanqueAlcool1;
            return quantidade;
        }

        this.tanqueAlcool1 = (getTanqueAlcool1() + getTanqueAlcool2() + quantoPossoArmazenar) / 2;
        this.tanqueAlcool2 = this.tanqueAlcool1;

        defineSituacao();

        return quantoPossoArmazenar;
    }

    public int[] encomendaCombustivel(int quantidadeSolicitada, TIPOPOSTO tipoPosto) {
        if (quantidadeInvalida(quantidadeSolicitada))
            return new int[] { -7, 0, 0, 0, 0 };

        int quantidadeAlcool = quantidadeSolicitada * 25 / 100;
        int quantidadeAditivo = quantidadeSolicitada * 5 / 100;
        int quantidadeGasolina = quantidadeSolicitada * 70 / 100;

        int totalTanqueAlcool = getTanqueAlcool1() + getTanqueAlcool2();
        if (getTanqueGasolina() < quantidadeGasolina || totalTanqueAlcool < quantidadeAlcool)
            return new int[] { -21, 0, 0, 0, 0 };

        int novoTanqueAlcool = totalTanqueAlcool - quantidadeAlcool;
        int novoTanqueAditivo = getTanqueAditivo() - quantidadeAditivo;
        int novoTanqueGasolina = getTanqueGasolina() - quantidadeGasolina;

        if (this.situacao == SITUACAO.EMERGENCIA) {
            if (tipoPosto == TIPOPOSTO.COMUM)
                return new int[] { -14, 0, 0, 0, 0 };

            if(getTanqueAditivo() < quantidadeAditivo) {
                atualizaValoresTanques(0, novoTanqueGasolina, novoTanqueAlcool/2);
                return new int[] { 0, 0, novoTanqueGasolina, novoTanqueAlcool/2, novoTanqueAlcool/2 };
            }

            atualizaValoresTanques(novoTanqueAditivo, novoTanqueGasolina, novoTanqueAlcool/2);
            return new int[] { 0, novoTanqueAditivo, novoTanqueGasolina, novoTanqueAlcool/2, novoTanqueAlcool/2 };
        }

        if (getTanqueAditivo() < quantidadeAditivo)
            return new int[] { -21, 0, 0, 0, 0 };

        if (this.situacao == SITUACAO.SOBRAVISO && tipoPosto == TIPOPOSTO.COMUM) {
            novoTanqueAlcool = (novoTanqueAlcool + quantidadeAlcool/2) /2;
            novoTanqueAditivo = novoTanqueAditivo + quantidadeAditivo/2;
            novoTanqueGasolina = novoTanqueGasolina + quantidadeGasolina/2;
            atualizaValoresTanques(novoTanqueAditivo, novoTanqueGasolina, novoTanqueAlcool);
            return new int[] { 0, novoTanqueAditivo, novoTanqueGasolina, novoTanqueAlcool, novoTanqueAlcool };
        }

        atualizaValoresTanques(novoTanqueAditivo, novoTanqueGasolina, novoTanqueAlcool/2);
        return new int[] { 0, novoTanqueAditivo, novoTanqueGasolina, novoTanqueAlcool/2, novoTanqueAlcool/2 };
    }

    private void atualizaValoresTanques(int novoAditivo, int novaGasolina, int novoAlcool) {
        this.tanqueAditivo = novoAditivo;
        this.tanqueAlcool1 = novoAlcool;
        this.tanqueAlcool2 = novoAlcool;
        this.tanqueGasolina = novaGasolina;
        defineSituacao();
    }
}
