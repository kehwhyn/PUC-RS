package com.kehwhyn;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CalculatorTest {
    private Calculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new Calculator();
    }

    @Test
    public void testSum() {
        var sum = calculator.evaluate("10+3");
        Assertions.assertEquals(13, sum);
    }

    @Test
    public void testSub() {
        var sum = calculator.evaluate("10-3");
        Assertions.assertEquals(7, sum);
    }
}
