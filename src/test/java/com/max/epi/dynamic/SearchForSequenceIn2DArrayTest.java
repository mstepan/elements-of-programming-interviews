package com.max.epi.dynamic;


import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class SearchForSequenceIn2DArrayTest {

    @Test
    public void sequenceExistsNoOverlap() {

        final int[][] data = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 8, 7, 6},
                {4, 3, 2, 1}
        };

        assertTrue(SearchForSequenceIn2DArray.exists(arr(2, 3, 4, 8, 6, 7, 7), data));

        assertTrue(SearchForSequenceIn2DArray.exists(arr(1, 5, 9, 4), data));
        assertTrue(SearchForSequenceIn2DArray.exists(arr(1, 2, 3, 4), data));
        assertTrue(SearchForSequenceIn2DArray.exists(arr(4, 8, 6, 1), data));
        assertTrue(SearchForSequenceIn2DArray.exists(arr(4, 3, 2, 1), data));
        assertTrue(SearchForSequenceIn2DArray.exists(arr(1, 6, 8, 4), data));
    }

    @Test
    public void sequenceExistsWithOverlap() {
        final int[][] data = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 8, 7, 6},
                {4, 3, 2, 1}
        };

        assertTrue(SearchForSequenceIn2DArray.exists(arr(2, 3, 4, 8, 6, 7, 7, 3, 2, 1), data));
        assertTrue(SearchForSequenceIn2DArray.exists(arr(1, 2, 3, 4, 8, 6, 1, 2, 3, 4, 9, 5, 1, 2, 3, 4), data));
    }

    @Test
    public void sequenceNotExists() {
        final int[][] data = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 8, 7, 6},
                {4, 3, 2, 1}
        };

        assertFalse(SearchForSequenceIn2DArray.exists(arr(2, 3, 4, 8, 6, 2, 3), data));
        assertFalse(SearchForSequenceIn2DArray.exists(arr(4, 7), data));
    }

    @Test
    public void zeroLengthSequenceReturnsAlwaysTrue() {
        assertTrue(SearchForSequenceIn2DArray.exists(new int[]{}, new int[][]{{1, 2, 3}}));
        assertTrue(SearchForSequenceIn2DArray.exists(new int[]{}, new int[][]{{}}));
    }

    @Test
    public void zeroLength2DArrayReturnsFalse() {
        assertFalse(SearchForSequenceIn2DArray.exists(new int[]{1, 2, 3}, new int[][]{}));
    }

    @Test(expected = NullPointerException.class)
    public void nullSequenceThrowsNPE() {
        SearchForSequenceIn2DArray.exists(null, new int[][]{{1, 2, 3}});
    }

    @Test(expected = NullPointerException.class)
    public void null2DArrayThrowsNPE() {
        SearchForSequenceIn2DArray.exists(new int[]{1, 2, 3}, null);
    }

    @Test(expected = NullPointerException.class)
    public void nullAtLeastOneDimensionIn2DArrayThrowsNPE() {
        SearchForSequenceIn2DArray.exists(new int[]{1, 2, 3}, new int[][]{
                {1, 2, 3},
                null,
                {4, 5, 6}
        });
    }

    @Test(expected = IllegalStateException.class)
    public void callConstructorThrowsException() throws NoSuchMethodException, InstantiationException,
            IllegalAccessException, InvocationTargetException {

        Constructor constructor = SearchForSequenceIn2DArray.class.getDeclaredConstructor();
        constructor.setAccessible(true);

        try {
            constructor.newInstance();
        }
        catch (InvocationTargetException ex) {
            Throwable th = ex.getTargetException();
            if (th instanceof IllegalStateException) {
                throw (IllegalStateException) th;
            }
        }
    }

    private static int[] arr(int... sequence) {
        return sequence;
    }
}
