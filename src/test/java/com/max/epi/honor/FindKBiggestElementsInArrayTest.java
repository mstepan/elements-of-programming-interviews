package com.max.epi.honor;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import static com.max.epi.honor.FindKBiggestElementsInArray.findKthBiggest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

public final class FindKBiggestElementsInArrayTest {

    @Rule
    public final ExpectedException thrown = ExpectedException.none();

    @Test
    public void findKthBiggestNormalFlow() {
        assertThat(findKthBiggest(new int[]{3, 5, 7, 2, 12, 34, 56, 8, 9, 13, 14}, 3)).isEqualTo(new int[]{14, 34, 56});
    }

    @Test
    public void findKthBiggestKBiggerThanArrayLength() {
        assertThat(findKthBiggest(new int[]{9, 13, 14}, 10)).isEqualTo(new int[]{9, 13, 14});
    }

    @Test
    public void findKthBiggestKEqualsArrayLength() {
        assertThat(findKthBiggest(new int[]{9, 13, 14}, 3)).isEqualTo(new int[]{9, 13, 14});
    }

    @Test
    public void findKthBiggestZeroKPassed() {
        assertThat(findKthBiggest(new int[]{9, 13, 14}, 0)).isEqualTo(new int[]{});
    }

    @Test
    public void findKthBiggestRandomArrays() {

        Random rand = ThreadLocalRandom.current();

        for (int it = 0; it < 1000; ++it) {
            int[] arr = generateRandomArray(10 + rand.nextInt(1000), rand, 1000);
            int[] arrCopy = Arrays.copyOf(arr, arr.length);

            int k = rand.nextInt(arr.length);

            Arrays.sort(arrCopy);

            int[] expected = Arrays.copyOfRange(arrCopy, arrCopy.length - k, arrCopy.length);
            int[] actual = findKthBiggest(arr, k);

            Arrays.sort(actual);

            assertThat(actual).isEqualTo(expected);
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

    private static int[] generateRandomArray(int length, Random rand, int maxValue) {
        int[] arr = new int[length];

        for (int i = 0; i < arr.length; ++i) {
            arr[i] = rand.nextInt(maxValue);
        }

        return arr;
    }
}
