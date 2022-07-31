package com.kehwhyn;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class NumFinderTest {
    private NumFinder nf = null;

    @BeforeEach
    void setUp() {
        nf = new NumFinder();
    }

    @Test
    public void testeMaiorQQOrdem() {
        nf.find(new int[] { 10, 8, 2, 14, 7 });
        int maior = nf.getLargest();
        Assertions.assertEquals(14, maior);
    }

    @Test
    public void testeMenorQQOrdem() {
        nf.find(new int[] { 10, 8, 2, 14, 7 });
        int menor = nf.getSmallest();
        Assertions.assertEquals(2, menor);
    }

    @Test
    public void testeMaiorOrdemCrescente() {
        nf.find(new int[] { 1, 2, 3, 4, 5 });
        int maior = nf.getLargest();
        Assertions.assertEquals(5, maior);
    }

    @Test
    public void testeMenorOrdemCrescente() {
        nf.find(new int[] { 1, 2, 3, 4, 5 });
        int menor = nf.getSmallest();
        Assertions.assertEquals(1, menor);
    }
}
