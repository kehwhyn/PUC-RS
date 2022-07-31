package com.kehwhyn;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class DepCombTest {
    @ParameterizedTest
    @CsvSource({
        "125, 2500, 625, 0, EMERGENCIA",
        "250, 5000, 625, 625, SOBRAVISO", 
        "500, 10000, 1250, 1250, NORMAL",
    })
    public void testaSituacao(int tAditivo, int tGasolina, int tAlcool1, int tAlcool2, DepComb.SITUACAO situcaoEsperada) {
        DepComb depComb = new DepComb(tAditivo, tGasolina, tAlcool1, tAlcool2);
        Assertions.assertEquals(situcaoEsperada, depComb.getSituacao());
    }

    @ParameterizedTest
    @CsvSource({
        "0, 0, 0, 0, -700, -1",
        "0, 0, 0, 0, 500, 500",
        "0, 9500, 0, 0, 700, 500",
        "0, 10000, 0, 0, 500, 0", 
    })
    public void testaRecebeGasolina(int tAditivo, int tGasolina, int tAlcool1, int tAlcool2, int quantidadeAbastecida, int quantidadeEsperada) {
        DepComb depComb = new DepComb(tAditivo, tGasolina, tAlcool1, tAlcool2);
        int quantidadeRetornada = depComb.recebeGasolina(quantidadeAbastecida);
        Assertions.assertEquals(quantidadeEsperada, quantidadeRetornada);
    }

    @ParameterizedTest
    @CsvSource({
        "0, 0, 0, 0, -700, -1",
        "0, 0, 0, 0, 500, 500",
        "250, 0, 0, 0, 500, 250",
        "500, 0, 0, 0, 250, 0", 
    })
    public void testaRecebeAditivo(int tAditivo, int tGasolina, int tAlcool1, int tAlcool2, int quantidadeAbastecida, int quantidadeEsperada) {
        DepComb depComb = new DepComb(tAditivo, tGasolina, tAlcool1, tAlcool2);
        int quantidadeRetornada = depComb.recebeAditivo(quantidadeAbastecida);
        Assertions.assertEquals(quantidadeEsperada, quantidadeRetornada);
    }

    @ParameterizedTest
    @CsvSource({
        "0, 0, 0, 0, -700, -1",
        "500, 10000, 100, 0, 400, 400",
        "500, 10000, 1250, 0, 3000, 1250",
        "500, 1000, 1250, 1250, 400, 0",
    })
    public void testaRecebeAlcool(int tAditivo, int tGasolina, int tAlcool1, int tAlcool2, int quantidadeAbastecida, int quantidadeEsperada) {
        DepComb depComb = new DepComb(tAditivo, tGasolina, tAlcool1, tAlcool2);
        int quantidadeRetornada = depComb.recebeAlcool(quantidadeAbastecida);
        Assertions.assertEquals(quantidadeEsperada, quantidadeRetornada);
    }

    @ParameterizedTest
    @CsvSource({
        "0, 0, 0, 0, -50, COMUM, -7, 0, 0, 0, 0",
        "125, 2500, 625, 0, 120, COMUM, -14, 0, 0, 0, 0",
        "250, 5000, 625, 625, 9000, COMUM, -21, 0, 0, 0, 0",
        "125, 2500, 625, 0, 120, ESTRATEGICO, 0, 119, 2416, 297, 297",
        "0, 2500, 625, 0, 300, ESTRATEGICO, 0, 0, 2290, 274, 274",
        "126, 10000, 1250, 1250, 3000, COMUM, -21, 0, 0, 0, 0",
        "250, 5000, 625, 625, 200, COMUM, 0, 245, 4930, 612, 612",
        "500, 10000, 1250, 1250, 550, COMUM, 0, 473, 9615, 1181, 1181",
        "250, 5000, 625, 625, 200, ESTRATEGICO, 0, 240, 4860, 600, 600",
        "500, 1000, 1250, 1250, 300, ESTRATEGICO, 0, 485, 790, 1212, 1212",
    })
    public void testaEncomendaCombustivel(
        int tAditivo, int tGasolina, int tAlcool1, int tAlcool2, int quantidadeSolicitada, DepComb.TIPOPOSTO tipoPosto, int codigo, int aditivo, int gasolina, int alcool1, int alcool2) {
        DepComb depComb = new DepComb(tAditivo, tGasolina, tAlcool1, tAlcool2);
        int[] quantidadeRetornada = depComb.encomendaCombustivel(quantidadeSolicitada, tipoPosto);
        int[] retornoEsperado = new int[] {codigo, aditivo, gasolina, alcool1, alcool2};
        Assertions.assertArrayEquals(retornoEsperado, quantidadeRetornada);
    }
}
