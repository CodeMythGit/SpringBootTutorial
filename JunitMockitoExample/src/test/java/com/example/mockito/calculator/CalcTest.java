package com.example.mockito.calculator;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class CalcTest {

    @ParameterizedTest
    @CsvSource({"1,2", "3,4"})
    void testAdd(int num1, int num2) {
        Calc c = new Calc();
        int expected = num1 + num2;
        int result = c.add(num1, num2);
        System.out.println("result: " + result + " expected" + expected);
        assertThat(result).isEqualTo(expected);
    }

    @ParameterizedTest
    @ValueSource(ints = {10, 20, 30, 40, 50})
    void testMultiply(int num) {
        Calc c = new Calc();
        int expectedResult = num * 5;
        int result = c.multiply(num);
        assertThat(result).isEqualTo(expectedResult);

    }
}