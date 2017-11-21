package com.max.epi.greedy;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public final class LargestRectangleUnderSkylineTest {

    @Test
    public void largestRectangleDivideAndConquer() {
        int[] arr = {1, 4, 2, 5, 6, 3, 2, 6, 6, 5, 2, 1, 3};
        assertEquals(20, LargestRectangleUnderSkyline.largestRectangleDivideAndConquer(arr));
    }
}
