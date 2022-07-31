package com.kehwhyn;

import com.kehwhyn.entidades.Bairro;
import com.kehwhyn.entidades.geometria.Area;
import com.kehwhyn.entidades.geometria.Ponto;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class BairroTest {
    private static Ponto pSupEsq;
    private static Bairro bairroQuadrado;
    private static Bairro bairroRetangular;

    @BeforeAll
    static void setUp () {
        pSupEsq = new Ponto(0, 20);
        bairroQuadrado = Bairro.novoBairroQuadrado("Bairro Quadrado", pSupEsq, 10, 12);
        bairroRetangular = Bairro.novoBairroRetangular("Bairro Retangular", pSupEsq, 20, 10, 12);
    }

    @Test
    void testaNovoBairroQuadrado() {
        var pInfDir = new Ponto(10, 10);
        var area = new Area(pSupEsq, pInfDir);
        Assertions.assertEquals(true, bairroQuadrado.getArea().equals(area));
    }

    @Test
    void testaNovoBairroRetangular() {
        Ponto pInfDir = new Ponto(20, 10);
        Area area = new Area(pSupEsq, pInfDir);

        Assertions.assertEquals(true, bairroRetangular.getArea().equals(area));
    }

    @Test
    void testaAlteraCustoTransporteInvalido() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> { bairroQuadrado.alteraCustoTransporte(-12); });
    }

    @Test
    void testaEqualsTrueQuadrado() {
        Bairro outroBairro = Bairro.novoBairroQuadrado("Bairro Quadrado", pSupEsq, 10, 12);
        Assertions.assertEquals(true, bairroQuadrado.equals(outroBairro));
    }

    @ParameterizedTest
    @CsvSource({
        "Bairre Quadrade, 0, 20, 10, 12", // nome  diferente
        "Bairro Quadrado, 1, 20, 10, 12", // ponto diferente
        "Bairro Quadrado, 0, 20, 20, 12", // lado  diferente -> erro 1 -> erro 1 -> acontece quando bairro2 é super set do bairro1 e vice versa
        "Bairro Quadrado, 0, 20, 10, 15"  // custo diferente
    })
    void testaEqualsFalseQuadrado(String nome, int xSupEsq, int ySupEsq, int lado, int custo) {
        var pSupEsq = new Ponto(xSupEsq, ySupEsq);
        var outroBairro = Bairro.novoBairroQuadrado(nome, pSupEsq, lado, custo);
        Assertions.assertEquals(false, bairroQuadrado.equals(outroBairro));
    }

    @Test
    void testaEqualsTrueRetangular() {
        Bairro outroBairro = Bairro.novoBairroRetangular("Bairro Retangular", pSupEsq, 20, 10, 12);
        Assertions.assertEquals(true, bairroRetangular.equals(outroBairro));
    }

    @ParameterizedTest
    @CsvSource({
        "Bairre Retangular, 0, 20, 20, 10, 12", // nome  diferente
        "Bairro Retangular, 1, 20, 20, 10, 12", // ponto diferente
        "Bairro Retangular, 0, 20, 30, 10, 12", // ladoH  diferente
        "Bairro Retangular, 0, 20, 20, 20, 12", // ladoV  diferente
        "Bairro Retangular, 0, 20, 30, 20, 12", // ladoH && ladoV  diferente -> erro 1 -> acontece quando bairro2 é super set do bairro1 e vice versa
        "Bairro Retangular, 0, 20, 20, 10, 15"  // custo diferente
    })
    void testaEqualsFalseRetangular(String nome, int xSupEsq, int ySupEsq, int ladoH, int ladoV, int custo) {
        var pSupEsq = new Ponto(xSupEsq, ySupEsq);
        var outroBairro = Bairro.novoBairroRetangular(nome, pSupEsq, ladoH, ladoV, custo);
        Assertions.assertEquals(false, bairroRetangular.equals(outroBairro));
    }
}