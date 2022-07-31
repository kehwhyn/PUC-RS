package com.kehwhyn;

import com.kehwhyn.entidades.geometria.Area;
import com.kehwhyn.entidades.geometria.Reta;
import com.kehwhyn.entidades.geometria.Ponto;
import com.kehwhyn.entidades.geometria.SituacaoReta;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class AreaTest {
    @ParameterizedTest
    @CsvSource({
        "10, 11, 10, 5", // pSupEsq.x >= pInfDir.x
        "5, 5, 10, 5",   // pSupEsq.y <= pInfDir.y
        "10, 5, 10, 5"   // pSupEsq == pInfDir
    })
    void testaAreaRetanguloInvalida (int xSupEsq, int ySupEsq, int xInfDir, int yInfDir) {
        var pSupEsq = new Ponto(xSupEsq, ySupEsq);
        var pInfDir = new Ponto(xInfDir, yInfDir);
        Assertions.assertThrows(IllegalArgumentException.class, () -> { new Area(pSupEsq, pInfDir); });
    }

    @ParameterizedTest
    @CsvSource({
        "20, 50, 50, 20, 35, 35", // ponto central quadrado
        "20, 50, 60, 20, 40, 35"  // ponto central retangulo
    })
    void testaPontoCentral (int xSupEsq, int ySupEsq, int xInfDir, int yInfDir, int xExpected, int yExpected) {
        var pSupEsq = new Ponto(xSupEsq, ySupEsq);
        var pInfDir = new Ponto(xInfDir, yInfDir);
        var area = new Area(pSupEsq, pInfDir);

        var pCentalExpected = new Ponto(xExpected, yExpected);

        Assertions.assertEquals(pCentalExpected, area.pontoCentral());
    }

    @ParameterizedTest
    @CsvSource({
        "30, 30, 50, 30, TODA_DENTRO",
        "30, 40, 50, 30, TODA_DENTRO",
        "30, 30, 50, 40, TODA_DENTRO",
        "10, 40, 30, 40, INTERSECTA",
        "30, 40, 30, 60, INTERSECTA",
        "50, 10, 50, 30, INTERSECTA", // 6
        "50, 30, 70, 30, INTERSECTA",
        "40, 10, 40, 60, INTERSECTA", // 8
        "10, 30, 70, 30, INTERSECTA",
        "40, 60, 70, 30, INTERSECTA",
        "10, 40, 40, 10, INTERSECTA",
        "10, 30, 40, 60, INTERSECTA",
        "40, 10, 70, 40, INTERSECTA",
        "10, 60, 30, 40, INTERSECTA",
        "50, 40, 70, 60, INTERSECTA",
        "50, 30, 70, 10, INTERSECTA",
        "10, 10, 30, 30, INTERSECTA",
        "10, 10, 10, 30, TODA_FORA",
        "10, 30, 10, 40, TODA_FORA", // 19
        "10, 40, 10, 60, TODA_FORA",
        "10, 10, 10, 60, TODA_FORA",
        //"00, 40, 30, 70, TODA_FORA",
        "10, 60, 30, 60, TODA_FORA",
        "30, 60, 50, 60, TODA_FORA",
        "50, 60, 70, 60, TODA_FORA",
        "10, 60, 70, 60, TODA_FORA",
        //"50, 70, 80, 40, TODA_FORA",
        "70, 60, 70, 40, TODA_FORA",
        "70, 40, 70, 30, TODA_FORA",
        "70, 30, 70, 10, TODA_FORA",
        "70, 60, 70, 10, TODA_FORA",
        //"80, 30, 50, 00, TODA_FORA",
        "70, 10, 50, 10, TODA_FORA",
        "50, 10, 30, 10, TODA_FORA",
        "70, 10, 10, 10, TODA_FORA",
        //"30, 00, 00, 30, TODA_FORA"
    })
    void testaClassificaReta (int x1, int y1, int x2, int y2, SituacaoReta situacaoReta) {
        var p1 = new Ponto(x1, y1);
        var p2 = new Ponto(x2, y2);
        var reta = new Reta(p1, p2);

        var area = new Area(new Ponto(20, 50), new Ponto(60, 20));

        Assertions.assertEquals(situacaoReta, area.classifica(reta));
    }

    @ParameterizedTest
    @CsvSource({
        "20, 50, 50, 20, 20, 50, 50, 20, true", // quadrado == quadrado
        "20, 50, 60, 20, 20, 50, 60, 20, true", // retangulo == retangulo
        "20, 50, 50, 20, 20, 50, 60, 20, false" // quadrado != retangulo
    })
    void testaIgualdade (int xSupEsq1, int ySupEsq1, int xInfDir1, int yInfDir1, int xSupEsq2, int ySupEsq2, int xInfDir2, int yInfDir2, boolean expected) {
        var area1 = new Area(
            new Ponto(xSupEsq1, ySupEsq1),
            new Ponto(xInfDir1, yInfDir1)
        );
        var area2 = new Area(
            new Ponto(xSupEsq2, ySupEsq2),
            new Ponto(xInfDir2, yInfDir2)
        );
        Assertions.assertEquals(expected, area1.equals(area2));
    }
}
