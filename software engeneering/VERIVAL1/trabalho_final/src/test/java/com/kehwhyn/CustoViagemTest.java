package com.kehwhyn;

import com.kehwhyn.entidades.Bairro;
import com.kehwhyn.entidades.Roteiro;
import com.kehwhyn.entidades.Passageiro;
import com.kehwhyn.entidades.geometria.Ponto;
import com.kehwhyn.casos_de_uso.politicas.CustoViagem;
import com.kehwhyn.casos_de_uso.politicas.CalculoCustoViagemVerao;
import com.kehwhyn.casos_de_uso.politicas.CalculoCustoViagemBasico;
import com.kehwhyn.casos_de_uso.politicas.CalculoCustoViagemRelampago;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class CustoViagemTest {
    private static Passageiro passageiro;
    private static ArrayList<Bairro> todosBairros;

    @BeforeAll
    static void setUp() {
        var b1 = Bairro.novoBairroRetangular("Bairro 1", new Ponto(0, 20), 30, 20, 12);
        var b2 = Bairro.novoBairroRetangular("Bairro 2", new Ponto(30, 20), 60, 20, 12);
        var b7 = Bairro.novoBairroRetangular("Bairro 7", new Ponto(90, 20), 30, 20, 12);
        var b10 = Bairro.novoBairroRetangular("Bairro 10", new Ponto(120, 20), 30, 20, 12);

        todosBairros = new ArrayList<Bairro>() {
            {
                add(b1);
                add(b2);
                add(b7);
                add(b10);
            }
        };

        passageiro = Passageiro.novoPassageiro("64649646469", "KEVIN I.A");
    }

    @ParameterizedTest
    @CsvSource({
        "12, 0, 0",
        "24, 0, 1",
        "36, 0, 2",
    })
    void testaCalculoCustoViagemBasico(double custoEsperado, int indexBairro1, int indexBairro2) {
        var calculoViagemBasica = new CustoViagem(new CalculoCustoViagemBasico());
        var roteiro = new Roteiro(todosBairros.get(indexBairro1), todosBairros.get(indexBairro2),todosBairros);

        var custoViagemBasica = calculoViagemBasica.custoViagem(roteiro, passageiro);

        Assertions.assertEquals(custoEsperado, custoViagemBasica);
    }

    @ParameterizedTest
    @CsvSource({
        "24, 0, 1, 80, 10",
        "24, 0, 1, 90, 10",
        "21.84, 0, 1, 100, 10",
        "32.4, 0, 2, 8, 1",
        "29.159999999999997, 0, 2, 100, 10",
    })
    void testaCalculoCustoViagemVerao(double descontoEsperado, int indexBairro1, int indexBairro2, int pontuacao, int qntAvaliacoes) {
        var calculoViagemVerao = new CustoViagem(new CalculoCustoViagemVerao());
        var roteiro = new Roteiro(todosBairros.get(indexBairro1), todosBairros.get(indexBairro2), todosBairros);
        var passageiro = Passageiro.passageiroExistente("64649646469", "KEVIN I.A", pontuacao, qntAvaliacoes);

        var custoViagemVerao = calculoViagemVerao.custoViagem(roteiro, passageiro);

        Assertions.assertEquals(descontoEsperado, custoViagemVerao);
    }

    @ParameterizedTest
    @CsvSource({
        "24, 0, 1, 168, 28",
        "24, 0, 1, 180, 30",
        "24, 0, 1, 170, 35",
        "24, 0, 1, 175, 35",
        "22.8, 0, 1, 210, 35",
        "36, 0, 2, 4, 25",
        "45.6, 0, 3, 4, 25",
        "43.2, 0, 3, 210, 35",
    })
    void testaCalculoCustoViagemRelampago(double descontoEsperado, int indexBairro1, int indexBairro2, int pontuacao, int qntAvaliacoes) {
        var calculoViagemRelampago = new CustoViagem(new CalculoCustoViagemRelampago());
        var roteiro = new Roteiro(todosBairros.get(indexBairro1), todosBairros.get(indexBairro2), todosBairros);
        var passageiro = Passageiro.passageiroExistente("64649646469", "KEVIN I.A", pontuacao, qntAvaliacoes);

        var custoViagemRelampago = calculoViagemRelampago.custoViagem(roteiro, passageiro);

        Assertions.assertEquals(descontoEsperado, custoViagemRelampago);
    }
}
