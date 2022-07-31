package com.kehwhyn;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class EncomendaTest {
    private Encomenda encomenda;

    @Test
    public void testarValoresSuperiroresAoEstoque() {
        encomenda = new Encomenda(0,0);
        
        int[] expected = {-1, 0};
        int[] result = encomenda.qtdadeBarras(10);

        Assertions.assertArrayEquals(expected, result);
    }


    @Test
    public void testarPrioridadeBarras5Kg() {
        encomenda = new Encomenda(5,1);
        
        int[] expected = {1, 0};
        int[] result = encomenda.qtdadeBarras(5);

        Assertions.assertArrayEquals(expected, result);
    }

    @Test
    public void testarPesoMaiorQue5kg() {
        encomenda = new Encomenda(3,8);
        
        int[] expected = {1, 2};
        int[] result = encomenda.qtdadeBarras(7);

        Assertions.assertArrayEquals(expected, result);
    }
    
    @Test
    public void testarValoresNegativos() {
        encomenda = new Encomenda(3,1);
        
        int[] expected = {-1, 0};
        int[] result = encomenda.qtdadeBarras(-3);
        
        Assertions.assertArrayEquals(expected, result);
    }
    
    @Test
    public void testarSomenteBarrasDe1Kg() {
        encomenda = new Encomenda(8,10);
        
        int[] expected = {0, 4};
        int[] result = encomenda.qtdadeBarras(4);
        
        Assertions.assertArrayEquals(expected, result);
    } 

    @Test
    public void testarPesosExtremos() {
        encomenda = new Encomenda(25, 75);
        
        int[] expected = {40, 1};
        int[] result = encomenda.qtdadeBarras(201);
        
        Assertions.assertArrayEquals(expected, result);
    }
}
