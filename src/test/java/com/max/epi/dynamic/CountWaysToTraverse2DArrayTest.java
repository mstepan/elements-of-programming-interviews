package com.max.epi.dynamic;

import org.junit.Test;

import static groovy.util.GroovyTestCase.assertEquals;
import static org.assertj.core.api.Assertions.assertThat;


public class CountWaysToTraverse2DArrayTest {

    @Test
    public void countWays() {
        assertThat(CountWaysToTraverse2DArray.countWays(3)).isEqualTo(6);
        assertEquals(20, CountWaysToTraverse2DArray.countWays(4));
        assertEquals(70, CountWaysToTraverse2DArray.countWays(5));
        assertEquals(252, CountWaysToTraverse2DArray.countWays(6));
    }

    @Test
    public void countWaysBiggerSize() {
        for (int size = 7; size < 15; ++size) {
            assertEquals(CountWaysToTraverse2DArray.countWaysBruteforce(size),
                         CountWaysToTraverse2DArray.countWays(size));
        }
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
