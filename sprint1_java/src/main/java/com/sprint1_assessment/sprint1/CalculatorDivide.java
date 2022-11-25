package com.sprint1_assessment.sprint1;

import org.springframework.stereotype.Component;

@Component
public class CalculatorDivide {

    public int divide(int a, int b) throws ArithmeticException {
        if (b == 0) {
            throw new ArithmeticException("Division by 0");
        } else {
            return a / b;
        }
    }

}
