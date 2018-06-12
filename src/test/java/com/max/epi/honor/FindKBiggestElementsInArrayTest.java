package com.max.epi.honor;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static com.max.epi.honor.FindKBiggestElementsInArray.findKthBiggest;
import static org.junit.Assert.assertTrue;

public class FindKBiggestElementsInArrayTest {

    @Rule
    public final ExpectedException thrown = ExpectedException.none();

    @Test
    public void findKthBiggestNormalFlow() {
        assertArrayEquals(new int[]{14, 34, 56}, findKthBiggest(new int[]{3, 5, 7, 2, 12, 34, 56, 8, 9, 13, 14}, 3));
    }

    @Test
    public void findKthBiggestKBiggerThanArrayLength() {
        assertArrayEquals(new int[]{9, 13, 14}, findKthBiggest(new int[]{9, 13, 14}, 10));
    }

    @Test
    public void findKthBiggestKEqualsArrayLength() {
        assertArrayEquals(new int[]{9, 13, 14}, findKthBiggest(new int[]{9, 13, 14}, 3));
    }

    @Test
    public void findKthBiggestZeroKPassed() {
        assertArrayEquals(new int[]{}, findKthBiggest(new int[]{9, 13, 14}, 0));
    }

    @Test
    public void findKthBiggestRandomArrays() {

        Random rand = ThreadLocalRandom.current();

        for (int it = 0; it < 1000; ++it) {
            int[] arr = new int[10 + rand.nextInt(1000)];
            int[] arrCopy = Arrays.copyOf(arr, arr.length);

            int k = rand.nextInt(arr.length);

            Arrays.sort(arrCopy);

            assertArrayEquals(Arrays.copyOfRange(arrCopy, arrCopy.length - k, arrCopy.length), findKthBiggest(arr, k));
        }
    }

    @Test
    public void findKthBiggestNullArrayThrowsException() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("can't process null 'arr'");

        findKthBiggest(null, 3);
    }

    @Test
    public void findKthBiggestNegativeKThrowsException() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("negative 'k' passed");

        findKthBiggest(new int[]{10, 8, 3}, -3);
    }

    private static void assertArrayEquals(int[] expected, int[] actual) {
        assertTrue("Arrays aren't equals, expected: " + Arrays.toString(expected) + ", actual: " + Arrays.toString(actual),
                   Arrays.equals(expected, actual));
    }
}
