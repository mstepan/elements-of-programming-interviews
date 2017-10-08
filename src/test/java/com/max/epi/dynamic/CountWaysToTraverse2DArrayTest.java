package com.max.epi.dynamic;

import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class CountWaysToTraverse2DArrayTest {

    @Test
    public void countWays() {
        assertEquals(6, CountWaysToTraverse2DArray.countWays(3));
        assertEquals(20, CountWaysToTraverse2DArray.countWays(4));
        assertEquals(70, CountWaysToTraverse2DArray.countWays(5));
    }

    @Test
    public void countWaysZeroSize() {
        assertEquals(0, CountWaysToTraverse2DArray.countWays(0));
    }

    @Test
    public void countWaysOneSize() {
        assertEquals(1, CountWaysToTraverse2DArray.countWays(1));
    }

    @Test
    public void countWaysTwoSize() {
        assertEquals(2, CountWaysToTraverse2DArray.countWays(2));
    }

    @Test(expected = IllegalArgumentException.class)
    public void countWaysNegativeSize() {
        CountWaysToTraverse2DArray.countWays(-5);
    }
}
