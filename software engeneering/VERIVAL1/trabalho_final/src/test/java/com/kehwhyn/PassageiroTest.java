package com.kehwhyn;

import com.kehwhyn.entidades.Passageiro;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class PassageiroTest {
    private static Passageiro passageiro;

    @BeforeAll
    static void setUp() {
        passageiro = Passageiro.novoPassageiro("64649646469", "KEVIN I.A");
    }

    @Test
    void testaPontuacaoMedia() {
        Assertions.assertEquals(8, passageiro.getPontuacaoMedia());
    }

    @Test
    void testaPontuacaoMediaInvalida() {
        var passageiro = Passageiro.passageiroExistente("64649646469", "KEVIN I.A", 8, 0);
        Assertions.assertThrows(ArithmeticException.class, () -> { passageiro.getPontuacaoMedia(); });
    }

    @Test
    void testaInfoPontuacao() {
        passageiro.infoPontuacao(20);
        Assertions.assertEquals(28, passageiro.getPontuacaoAcumulada());
        Assertions.assertEquals(2, passageiro.getQtdadeAvaliacoes());
    }

    @ParameterizedTest
    @CsvSource({"0", "-1"})
    void testaInfoPontuacaoInvalida(int novaPontuacao) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> { passageiro.infoPontuacao(novaPontuacao); });
    }
}
