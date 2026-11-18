package org.example.lesson27;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class CalculatorTest {

    private static final double EPS = 1e-9;

    @DataProvider(name = "additions")
    public Object[][] additions() {
        return new Object[][] {
                {2, 3, 5L},
                {-7, 5, -2L},
                {Integer.MAX_VALUE, 1, 2_147_483_648L},
                {Integer.MIN_VALUE, -1, -2_147_483_649L}
        };
    }

    @Test(dataProvider = "additions")
    public void addsWithoutIntOverflow(int left, int right, long expected) {
        Assert.assertEquals(Calculator.add(left, right), expected);
    }

    @DataProvider(name = "subtractions")
    public Object[][] subtractions() {
        return new Object[][] {
                {7, 3, 4L},
                {3, 7, -4L},
                {Integer.MIN_VALUE, 1, -2_147_483_649L},
                {Integer.MAX_VALUE, -1, 2_147_483_648L}
        };
    }

    @Test(dataProvider = "subtractions")
    public void subtractsWithoutIntOverflow(int left, int right, long expected) {
        Assert.assertEquals(Calculator.subtract(left, right), expected);
    }

    @DataProvider(name = "multiplications")
    public Object[][] multiplications() {
        return new Object[][] {
                {6, 7, 42L},
                {-6, 7, -42L},
                {Integer.MIN_VALUE, 0, 0L},
                {Integer.MAX_VALUE, Integer.MAX_VALUE, 4_611_686_014_132_420_609L},
                {Integer.MIN_VALUE, -1, 2_147_483_648L}
        };
    }

    @Test(dataProvider = "multiplications")
    public void multipliesWithoutIntOverflow(int left, int right, long expected) {
        Assert.assertEquals(Calculator.multiply(left, right), expected);
    }

    @DataProvider(name = "divisions")
    public Object[][] divisions() {
        return new Object[][] {
                {7, 2, 3.5},
                {-7, 2, -3.5},
                {7, -2, -3.5},
                {0, 7, 0.0},
                {Integer.MIN_VALUE, -1, 2_147_483_648.0}
        };
    }

    @Test(dataProvider = "divisions")
    public void dividesWithFractionalResult(int numerator, int denominator, double expected) {
        Assert.assertEquals(Calculator.divide(numerator, denominator), expected, EPS);
    }

    @DataProvider(name = "zeroDenominators")
    public Object[][] zeroDenominators() {
        return new Object[][] {
                {1},
                {0}
        };
    }

    @Test(dataProvider = "zeroDenominators")
    public void rejectsDivisionByZero(int numerator) {
        Assert.assertThrows(ArithmeticException.class, () -> Calculator.divide(numerator, 0));
    }
}
