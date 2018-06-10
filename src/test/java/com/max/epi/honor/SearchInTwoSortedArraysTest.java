package com.max.epi.honor;

import java.util.Arrays;
import java.util.Random;

import com.max.util.ArrayUtils;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static com.max.epi.honor.SearchInTwoSortedArrays.findOrderStat;
import static org.junit.Assert.assertEquals;

public class SearchInTwoSortedArraysTest {

    @Rule
    public final ExpectedException thrown = ExpectedException.none();

    @Test
    public void findOrderStatNormalFlowRandomArrays() {

        Random rand = new Random();

        for (int it = 0; it < 100; ++it) {
            int[] arr1 = ArrayUtils.generateRandomArray(10 + rand.nextInt(1000));
            Arrays.sort(arr1);

            int[] arr2 = ArrayUtils.generateRandomArray(10 + rand.nextInt(1000));
            Arrays.sort(arr2);

            int[] mergedArr = mergeSortedArrays(arr1, arr2);

            for (int i = 0; i < mergedArr.length; ++i) {
                assertEquals(mergedArr[i], findOrderStat(arr1, arr2, i + 1));
            }
        }
    }

    private static int[] mergeSortedArrays(int[] arr1, int[] arr2) {

        int[] res = new int[arr1.length + arr2.length];

        int index1 = 0;
        int index2 = 0;

        for (int i = 0; i < res.length; ++i) {

            if (index1 < arr1.length && (index2 >= arr2.length || arr1[index1] <= arr2[index2])) {
                res[i] = arr1[index1];
                ++index1;
            }
            else {
                res[i] = arr2[index2];
                ++index2;
            }
        }

        return res;
    }

    @Test
    public void findOrderStatNormalFlow() {

        int[] arr1 = {1, 2, 5, 12, 24, 27, 34, 38};
        int[] arr2 = {4, 8, 8, 12, 29, 33};

        assertEquals(1, findOrderStat(arr1, arr2, 1));
        assertEquals(2, findOrderStat(arr1, arr2, 2));
        assertEquals(4, findOrderStat(arr1, arr2, 3));
        assertEquals(5, findOrderStat(arr1, arr2, 4));
        assertEquals(8, findOrderStat(arr1, arr2, 5));
        assertEquals(8, findOrderStat(arr1, arr2, 6));
        assertEquals(12, findOrderStat(arr1, arr2, 7));
        assertEquals(12, findOrderStat(arr1, arr2, 8));

        assertEquals(24, findOrderStat(arr1, arr2, 9));
        assertEquals(27, findOrderStat(arr1, arr2, 10));
        assertEquals(29, findOrderStat(arr1, arr2, 11));
        assertEquals(33, findOrderStat(arr1, arr2, 12));
        assertEquals(34, findOrderStat(arr1, arr2, 13));
        assertEquals(38, findOrderStat(arr1, arr2, 14));
    }

    @Test
    public void findOrderStatKBiggerThanLengthThowsException() {

        int[] arr1 = {1, 2, 5};
        int[] arr2 = {4, 12};

        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("k = 10, should be in range [1;5]");

        findOrderStat(arr1, arr2, 10);
    }

    @Test
    public void findOrderStatKOneBiggerThowsException() {

        int[] arr1 = {1, 2, 5};
        int[] arr2 = {4, 12};

        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("k = 6, should be in range [1;5]");

        findOrderStat(arr1, arr2, 6);
    }

    @Test
    public void findOrderStatKZeroThrowsException() {

        int[] arr1 = {1, 2, 5};
        int[] arr2 = {4, 12};

        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("k = 0, should be in range [1;5]");

        findOrderStat(arr1, arr2, 0);
    }

    @Test
    public void findOrderStatKNegativeThrowsException() {

        int[] arr1 = {1, 2, 5};
        int[] arr2 = {4, 12};

        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("k = -1, should be in range [1;5]");

        findOrderStat(arr1, arr2, -1);
    }
}
