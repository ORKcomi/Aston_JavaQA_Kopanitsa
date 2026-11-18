package org.example.lesson27;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class FactorialTest {

    @DataProvider(name = "validFactorials")
    public Object[][] validFactorials() {
        return new Object[][] {
                {0, 1L},
                {1, 1L},
                {2, 2L},
                {5, 120L},
                {10, 3_628_800L},
                {20, 2_432_902_008_176_640_000L}
        };
    }

    @Test(dataProvider = "validFactorials")
    public void calculatesFactorial(int number, long expected) {
        Assert.assertEquals(Factorial.calculate(number), expected);
    }

    @DataProvider(name = "invalidFactorials")
    public Object[][] invalidFactorials() {
        return new Object[][] {
                {-1},
                {Integer.MIN_VALUE},
                {21},
                {Integer.MAX_VALUE}
        };
    }

    @Test(dataProvider = "invalidFactorials")
    public void rejectsNumbersOutsideSupportedRange(int number) {
        Assert.assertThrows(IllegalArgumentException.class, () -> Factorial.calculate(number));
    }
}
