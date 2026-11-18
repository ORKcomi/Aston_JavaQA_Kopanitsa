package org.example.lesson27;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TriangleAreaTest {

    private static final double EPS = 1e-9;

    @DataProvider(name = "validTriangles")
    public Object[][] validTriangles() {
        return new Object[][] {
                {3.0, 4.0, 5.0, 6.0},
                {5.0, 5.0, 6.0, 12.0},
                {2.0, 2.0, 2.0, Math.sqrt(3.0)},
                {0.3, 0.4, 0.5, 0.06},
                {5.0, 3.0, 4.0, 6.0},
                {7.0, 8.0, 9.0, 26.832815729997478}
        };
    }

    @Test(dataProvider = "validTriangles")
    public void calculatesArea(double a, double b, double c, double expected) {
        Assert.assertEquals(TriangleArea.calculate(a, b, c), expected, EPS);
    }

    @DataProvider(name = "invalidTriangles")
    public Object[][] invalidTriangles() {
        return new Object[][] {
                {0.0, 3.0, 4.0},
                {3.0, 0.0, 4.0},
                {3.0, 4.0, 0.0},
                {-1.0, 3.0, 4.0},
                {Double.NaN, 3.0, 4.0},
                {3.0, Double.NaN, 4.0},
                {3.0, 4.0, Double.NaN},
                {Double.POSITIVE_INFINITY, 3.0, 4.0},
                {3.0, Double.POSITIVE_INFINITY, 4.0},
                {3.0, 4.0, Double.POSITIVE_INFINITY},
                {3.0, 1.0, 2.0},
                {1.0, 3.0, 2.0},
                {1.0, 2.0, 3.0},
                {4.0, 1.0, 2.0},
                {1.0, 4.0, 2.0},
                {1.0, 2.0, 4.0},
                {1e-200, 1e-200, 1e-200},
                {Double.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE}
        };
    }

    @Test(dataProvider = "invalidTriangles")
    public void rejectsInvalidSidesOrUnrepresentableArea(double a, double b, double c) {
        Assert.assertThrows(IllegalArgumentException.class, () -> TriangleArea.calculate(a, b, c));
    }
}
