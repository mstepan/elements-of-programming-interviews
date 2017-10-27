package com.max.epi.greedy;

import org.junit.Test;

import static org.junit.Assert.*;

public class SetPartitionByDifferencingTest {

    @Test
    public void partition() {
        int[] arr = {3, 6, 13, 20, 30, 40, 73};

        int[][] subsets = SetPartitionByDifferencing.partition(arr);

        assertNotNull("NULL 'subsets' returned", subsets);
        assertEquals("Incorrect subsets count", 2, subsets.length);

        assertArrayEquals("1-st subset is incorrect", subsets[0], new int[]{20, 73});
        assertArrayEquals("2-nd subset is incorrect", subsets[1], new int[]{3, 6, 13, 30, 40});
    }
}
