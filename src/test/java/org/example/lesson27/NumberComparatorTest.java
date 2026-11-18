package org.example.lesson27;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NumberComparatorTest {

    @ParameterizedTest(name = "compare({0}, {1}) = {2}")
    @CsvSource({
            "1, 2, -1",
            "2, 1, 1",
            "7, 7, 0",
            "0, 0, 0",
            "-3, -2, -1",
            "-2, -3, 1",
            "-1, 0, -1",
            "0, -1, 1",
            "-2147483648, 2147483647, -1",
            "2147483647, -2147483648, 1"
    })
    void comparesPositiveNegativeZeroAndBoundaryValues(int a, int b, int expected) {
        assertEquals(expected, NumberComparator.compare(a, b));
    }
}
