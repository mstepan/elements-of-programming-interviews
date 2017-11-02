package com.max.epi.dynamic;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TriangleMinPathTest {

    @Test
    public void findMinPathWeight() {
        int[][] triangle = {
                {2},
                {4, 4},
                {8, 5, 6},
                {4, 2, 6, 2},
                {1, 5, 2, 3, 4}
        };

        assertEquals(15, TriangleMinPath.findMinPathWeight(triangle));
    }

    @Test
    public void findMinPathWeightSingleElement() {
        assertEquals(7, TriangleMinPath.findMinPathWeight(new int[][]{{7}}));
    }

    @Test
    public void findMinPathWeightEmptyTriangle() {
        assertEquals(0, TriangleMinPath.findMinPathWeight(new int[][]{}));
    }

    @Test(expected = NullPointerException.class)
    public void findMinPathWeightNullTriangleThrowsNPE() {
        assertEquals(0, TriangleMinPath.findMinPathWeight(null));
    }

    @Test(expected = IllegalArgumentException.class)
    public void findMinPathWeightNotTriangleThrowsException1() {
        assertEquals(0, TriangleMinPath.findMinPathWeight(new int[][]{
                {2},
                {4, 4},
                {5}
        }));
    }

    @Test(expected = IllegalArgumentException.class)
    public void findMinPathWeightNotTriangleThrowsException2() {
        assertEquals(0, TriangleMinPath.findMinPathWeight(new int[][]{
                {2},
                {4, 4},
                {5, 6, 7, 8}
        }));
    }

    @Test(expected = IllegalArgumentException.class)
    public void findMinPathWeightNotTriangleThrowsException3() {
        assertEquals(0, TriangleMinPath.findMinPathWeight(new int[][]{
                {2},
                {}
        }));
    }

    @Test(expected = IllegalArgumentException.class)
    public void findMinPathWeightNotTriangleThrowsException4() {
        assertEquals(0, TriangleMinPath.findMinPathWeight(new int[][]{
                {}
        }));
    }
}
