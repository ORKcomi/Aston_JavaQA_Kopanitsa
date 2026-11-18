package org.example.lesson27;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CalculatorTest {

    private static final double EPS = 1e-9;

    @ParameterizedTest(name = "{0} + {1} = {2}")
    @CsvSource({
            "2, 3, 5",
            "-2, -3, -5",
            "-2, 3, 1",
            "0, 0, 0",
            "2147483647, 2147483647, 4294967294",
            "-2147483648, -2147483648, -4294967296"
    })
    void addsWithoutIntegerOverflow(int a, int b, long expected) {
        assertEquals(expected, Calculator.add(a, b));
    }

    @ParameterizedTest(name = "{0} - {1} = {2}")
    @CsvSource({
            "7, 3, 4",
            "-7, -3, -4",
            "0, 5, -5",
            "2147483647, -2147483648, 4294967295",
            "-2147483648, 2147483647, -4294967295"
    })
    void subtractsWithoutIntegerOverflow(int a, int b, long expected) {
        assertEquals(expected, Calculator.subtract(a, b));
    }

    @ParameterizedTest(name = "{0} * {1} = {2}")
    @CsvSource({
            "6, 7, 42",
            "-6, 7, -42",
            "0, 2147483647, 0",
            "2147483647, 2147483647, 4611686014132420609",
            "-2147483648, -2147483648, 4611686018427387904"
    })
    void multipliesWithoutIntegerOverflow(int a, int b, long expected) {
        assertEquals(expected, Calculator.multiply(a, b));
    }

    @ParameterizedTest(name = "{0} / {1} = {2}")
    @CsvSource({
            "7, 2, 3.5",
            "-7, 2, -3.5",
            "0, 4, 0.0",
            "-2147483648, -1, 2147483648.0"
    })
    void dividesWithFractionalResultsAndIntegerBoundary(int a, int b, double expected) {
        assertEquals(expected, Calculator.divide(a, b), EPS);
    }

    @ParameterizedTest(name = "rejects {0} / 0")
    @ValueSource(ints = {0, 1, -5})
    void rejectsDivisionByZero(int numerator) {
        assertThrows(ArithmeticException.class, () -> Calculator.divide(numerator, 0));
    }
}
