package com.kehwhyn;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RomanNumeralTest {
    private RomanNumeral romanNumeral;

    @BeforeEach
    void setUp() {
        romanNumeral = new RomanNumeral();
    }

    @Test
    public void testSum() {
        var convertedNumber = romanNumeral.convert("VIII");
        Assertions.assertEquals(8, convertedNumber);
    }

    @Test
    public void testSub() {
        var convertedNumber = romanNumeral.convert("IX");
        Assertions.assertEquals(9, convertedNumber);
    }

    @Test
    public void testSumSub() {
        var convertedNumber = romanNumeral.convert("MMCM");
        Assertions.assertEquals(2900, convertedNumber);
    }

    @Test
    public void testEmptyString() {
        var convertedNumber = romanNumeral.convert("");
        Assertions.assertEquals(0, convertedNumber);
    }
    
    @Test
    public void testStringLowerCase() {
        var convertedNumber = romanNumeral.convert("mcmxcviii");
        Assertions.assertNull(convertedNumber);
    }

    @Test
    public void testInvalidString() {
        var convertedNumber = romanNumeral.convert("ABY");
        Assertions.assertNotEquals(0, convertedNumber);
    }
}
