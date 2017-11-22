package com.max.epi.greedy;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;

public final class LargestRectangleUnderSkylineTest {

    @Rule
    public final ExpectedException expectedEx = ExpectedException.none();

    @Test
    public void largestRectangle() {
        int[] arr = {1, 4, 2, 5, 6, 3, 2, 6, 6, 5, 2, 1, 3};
        assertEquals(20, LargestRectangleUnderSkyline.largestRectangle(arr));
    }

    @Test
    public void largestRectangleNegativeValue() {
        expectedEx.expect(IllegalArgumentException.class);
        expectedEx.expectMessage("incorrect value -6, should be positive greater than 0");
        LargestRectangleUnderSkyline.largestRectangle(new int[]{1, 4, 2, 5, 6, 3, 2, 6, -6, 5, 2, 1, 3});
    }

    @Test
    public void largestRectangleOneElementArray() {
        assertEquals(4, LargestRectangleUnderSkyline.largestRectangle(new int[]{4}));
    }

    @Test
    public void largestRectangleEmptyArray() {
        assertEquals(0, LargestRectangleUnderSkyline.largestRectangle(new int[0]));
    }

    @Test
    public void largestRectangleDivideAndConquer() {
        int[] arr = {1, 4, 2, 5, 6, 3, 2, 6, 6, 5, 2, 1, 3};
        assertEquals(20, LargestRectangleUnderSkyline.largestRectangleDivideAndConquer(arr));
    }
}
