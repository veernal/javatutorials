package com.sprint1_assessment.sprint1;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CalculatorDivideTest {


    @Autowired
    CalculatorDivide calculatorDivide;

    @Test
    void divide_whenDenominatorIsZero_shouldThrow() {
        assertThrows(ArithmeticException.class, () -> calculatorDivide.divide(1, 0));
    }

    @Test
    void divide_NumByNum() {
        int result = calculatorDivide.divide(14,2);
        assertNotNull(result);
        assertEquals(7, result);
    }



}
