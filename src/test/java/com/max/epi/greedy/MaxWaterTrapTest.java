package com.max.epi.greedy;

import com.max.util.Pair;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;

public final class MaxWaterTrapTest {

    @Rule
    public final ExpectedException expectedEx = ExpectedException.none();

    @Test
    public void findMaxWaterTrap() {
        int[] arr = {1, 2, 1, 3, 4, 4, 5, 6, 2, 1, 3, 1, 3, 2, 1, 2, 4, 1};
        assertEquals(Pair.of(4, 16), MaxWaterTrap.findMaxWaterTrap(arr));
    }

    @Test
    public void findMaxWaterTrapTwoElementsArray() {
        assertEquals(Pair.of(0, 1), MaxWaterTrap.findMaxWaterTrap(new int[]{4, 7}));
    }

    @Test
    public void findMaxWaterTrapEmptyArray() {
        assertEquals(Pair.empty(), MaxWaterTrap.findMaxWaterTrap(new int[0]));
    }

    @Test
    public void findMaxWaterTrapOneElementArray() {
        assertEquals(Pair.empty(), MaxWaterTrap.findMaxWaterTrap(new int[]{4}));
    }

    @Test
    public void findMaxWaterTrapWithNullArrayThrowsException() {
        expectedEx.expect(NullPointerException.class);
        expectedEx.expectMessage("null 'arr' passed");
        MaxWaterTrap.findMaxWaterTrap(null);
    }

}
