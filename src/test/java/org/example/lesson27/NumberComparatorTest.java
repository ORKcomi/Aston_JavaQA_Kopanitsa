package org.example.lesson27;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class NumberComparatorTest {

    @DataProvider(name = "comparisons")
    public Object[][] comparisons() {
        return new Object[][] {
                {1, 2, -1},
                {2, 1, 1},
                {5, 5, 0},
                {-5, -3, -1},
                {-3, -5, 1},
                {Integer.MIN_VALUE, Integer.MAX_VALUE, -1},
                {Integer.MAX_VALUE, Integer.MIN_VALUE, 1},
                {Integer.MIN_VALUE, Integer.MIN_VALUE, 0},
                {Integer.MAX_VALUE, Integer.MAX_VALUE, 0}
        };
    }

    @Test(dataProvider = "comparisons")
    public void returnsOrderingSignForFullIntRange(int left, int right, int expected) {
        Assert.assertEquals(NumberComparator.compare(left, right), expected);
    }
}
