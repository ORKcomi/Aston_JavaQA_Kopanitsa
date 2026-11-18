package org.example.lesson27;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TriangleAreaTest {

    private static final double EPS = 1e-9;

    @ParameterizedTest(name = "sides {0}, {1}, {2} have area {3}")
    @CsvSource({
            "3, 4, 5, 6",
            "5, 5, 6, 12",
            "2, 2, 2, 1.7320508075688772",
            "0.3, 0.4, 0.5, 0.06",
            "13, 14, 15, 84"
    })
    void calculatesAreaForValidTriangles(double a, double b, double c, double expected) {
        assertEquals(expected, TriangleArea.calculate(a, b, c), EPS);
    }

    @ParameterizedTest(name = "rejects {0}")
    @MethodSource("invalidTriangles")
    void rejectsInvalidSidesOrUnrepresentableArea(String reason, double a, double b, double c) {
        assertThrows(IllegalArgumentException.class, () -> TriangleArea.calculate(a, b, c), reason);
    }

    static Stream<Arguments> invalidTriangles() {
        return Stream.of(
                Arguments.of("zero first side", 0.0, 1.0, 1.0),
                Arguments.of("zero second side", 1.0, 0.0, 1.0),
                Arguments.of("zero third side", 1.0, 1.0, 0.0),
                Arguments.of("negative side", -1.0, 2.0, 2.0),
                Arguments.of("NaN first side", Double.NaN, 1.0, 1.0),
                Arguments.of("NaN second side", 1.0, Double.NaN, 1.0),
                Arguments.of("NaN third side", 1.0, 1.0, Double.NaN),
                Arguments.of("infinite first side", Double.POSITIVE_INFINITY, 1.0, 1.0),
                Arguments.of("infinite second side", 1.0, Double.POSITIVE_INFINITY, 1.0),
                Arguments.of("infinite third side", 1.0, 1.0, Double.POSITIVE_INFINITY),
                Arguments.of("negative infinite side", Double.NEGATIVE_INFINITY, 1.0, 1.0),
                Arguments.of("first side equals other two", 2.0, 1.0, 1.0),
                Arguments.of("second side equals other two", 1.0, 2.0, 1.0),
                Arguments.of("third side equals other two", 1.0, 1.0, 2.0),
                Arguments.of("first side exceeds other two", 4.0, 1.0, 2.0),
                Arguments.of("second side exceeds other two", 1.0, 4.0, 2.0),
                Arguments.of("third side exceeds other two", 1.0, 2.0, 4.0),
                Arguments.of("area underflows to zero", 1e-200, 1e-200, 1e-200),
                Arguments.of("area overflows to infinity", 1e200, 1e200, 1e200)
        );
    }
}
