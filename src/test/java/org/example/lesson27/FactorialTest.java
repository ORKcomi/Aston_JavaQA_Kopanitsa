package org.example.lesson27;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FactorialTest {

    @ParameterizedTest(name = "{0}! = {1}")
    @CsvSource({
            "0, 1",
            "1, 1",
            "2, 2",
            "3, 6",
            "5, 120",
            "10, 3628800",
            "20, 2432902008176640000"
    })
    void calculatesFactorialIncludingSupportedBoundaries(int number, long expected) {
        assertEquals(expected, Factorial.calculate(number));
    }

    @ParameterizedTest(name = "rejects {0}")
    @ValueSource(ints = {Integer.MIN_VALUE, -1, 21, Integer.MAX_VALUE})
    void rejectsNumbersOutsideSupportedRange(int number) {
        assertThrows(IllegalArgumentException.class, () -> Factorial.calculate(number));
    }
}
