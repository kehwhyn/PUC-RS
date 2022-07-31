package com.kehwhyn;

import java.util.ArrayList;

import com.kehwhyn.entidades.Bairro;
import com.kehwhyn.entidades.Roteiro;
import com.kehwhyn.entidades.geometria.Ponto;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class RoteiroTest {
    private static ArrayList<Roteiro> roteiros;
    private static Bairro b1, b2, b3, b4, b5, b6, b7, b8, b9;

    @BeforeAll
    static void setUp() {
        b1 = Bairro.novoBairroRetangular("Bairro 1", new Ponto(00, 20), 30, 20, 12);
        b2 = Bairro.novoBairroRetangular("Bairro 2", new Ponto(30, 20), 60, 20, 12);
        b3 = Bairro.novoBairroRetangular("Bairro 3", new Ponto(00, 40), 60, 20, 12);
        b4 = Bairro.novoBairroRetangular("Bairro 4", new Ponto(40, 60), 30, 20, 12);
        b5 = Bairro.novoBairroRetangular("Bairro 5", new Ponto(00, 60), 30, 20, 12);
        b6 = Bairro.novoBairroRetangular("Bairro 6", new Ponto(30, 60), 60, 20, 12);
        b7 = Bairro.novoBairroRetangular("Bairro 7", new Ponto(90, 20), 30, 20, 12);
        b8 = Bairro.novoBairroRetangular("Bairro 8", new Ponto(90, 40), 30, 20, 12);
        b9 = Bairro.novoBairroRetangular("Bairro 9", new Ponto(90, 60), 30, 20, 12);

        var todosBairros = new ArrayList<Bairro>() {
            {
                add(b1);
                add(b2);
                add(b3);
                add(b4);
                add(b5);
                add(b6);
                add(b7);
                add(b8);
                add(b9);
            }
        };

        roteiros = new ArrayList<Roteiro>() {
            {
                add(new Roteiro(b3, b4, todosBairros)); // bairros adjacentes
                add(new Roteiro(b3, b3, todosBairros)); // roteiro para mesmo bairro
                add(new Roteiro(b5, b7, todosBairros)); // cópia para comparar igualdade
                add(new Roteiro(b3, b8, todosBairros)); // intersecta da esquerda pra direita o bairro 4
                add(new Roteiro(b5, b1, todosBairros)); // intersecta verticalmente de cima para baixo o bairro 3
                add(new Roteiro(b5, b3, todosBairros)); // intersecta diagonalmente só o bairro 3
                add(new Roteiro(b5, b7, todosBairros)); // intersecta diagonalmente, da esquerca para a direita, de cima para baixo, o bairro 3 e 4
            }
        };
    }

    @ParameterizedTest
    @CsvSource({
        "2, 6, true",
        "0, 2, false"
    })
    void testaEqualsTrue(int roteiro1, int roteiro2, boolean expected) {
        Assertions.assertEquals(expected, roteiros.get(roteiro1).equals(roteiros.get(roteiro2)));
    }

    @ParameterizedTest
    @CsvSource({
        //"3, 3",
        "3, 4",
        "3, 5",
        //"6, 6"
    })
    void testaBairrosPercorridos(int qntBairrosPercorridos, int indiceRoteiro) {
        Assertions.assertEquals(qntBairrosPercorridos, roteiros.get(indiceRoteiro).bairrosPercoridos().size());
    }
}
