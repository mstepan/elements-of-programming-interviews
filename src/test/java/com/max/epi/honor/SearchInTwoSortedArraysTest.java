package com.max.epi.honor;

import com.max.util.ArrayUtils;
import org.junit.Test;

import java.util.Arrays;
import java.util.Random;

import static com.max.epi.honor.SearchInTwoSortedArrays.findOrderStat;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public final class SearchInTwoSortedArraysTest {

    @Test
    public void findOrderStatNormalFlowRandomArrays() {

        final Random rand = new Random();

        for (int it = 0; it < 100; ++it) {
            int[] arr1 = ArrayUtils.generateRandomArray(10 + rand.nextInt(1000));
            Arrays.sort(arr1);

            int[] arr2 = ArrayUtils.generateRandomArray(10 + rand.nextInt(1000));
            Arrays.sort(arr2);

            int[] mergedArr = mergeSortedArrays(arr1, arr2);

            for (int i = 0; i < mergedArr.length; ++i) {
                assertThat(findOrderStat(arr1, arr2, i + 1)).isEqualTo(mergedArr[i]);
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

        assertThat(findOrderStat(arr1, arr2, 1)).isEqualTo(1);
        assertThat(findOrderStat(arr1, arr2, 2)).isEqualTo(2);
        assertThat(findOrderStat(arr1, arr2, 3)).isEqualTo(4);
        assertThat(findOrderStat(arr1, arr2, 4)).isEqualTo(5);
        assertThat(findOrderStat(arr1, arr2, 5)).isEqualTo(8);
        assertThat(findOrderStat(arr1, arr2, 6)).isEqualTo(8);
        assertThat(findOrderStat(arr1, arr2, 7)).isEqualTo(12);
        assertThat(findOrderStat(arr1, arr2, 8)).isEqualTo(12);

        assertThat(findOrderStat(arr1, arr2, 9)).isEqualTo(24);
        assertThat(findOrderStat(arr1, arr2, 10)).isEqualTo(27);
        assertThat(findOrderStat(arr1, arr2, 11)).isEqualTo(29);
        assertThat(findOrderStat(arr1, arr2, 12)).isEqualTo(33);
        assertThat(findOrderStat(arr1, arr2, 13)).isEqualTo(34);
        assertThat(findOrderStat(arr1, arr2, 14)).isEqualTo(38);
    }

    @Test
    public void findOrderStatKBiggerThanLengthThowsException() {
        final int[] arr1 = {1, 2, 5};
        final int[] arr2 = {4, 12};

        assertThatIllegalArgumentException().
                isThrownBy(() -> findOrderStat(arr1, arr2, 10)).
                withMessage("k = 10, should be in range [1;5]");
    }

    @Test
    public void findOrderStatKOneBiggerThowsException() {
        final int[] arr1 = {1, 2, 5};
        final int[] arr2 = {4, 12};

        assertThatIllegalArgumentException().
                isThrownBy(() -> findOrderStat(arr1, arr2, 6)).
                withMessage("k = 6, should be in range [1;5]");
    }

    @Test
    public void findOrderStatKZeroThrowsException() {
        final int[] arr1 = {1, 2, 5};
        final int[] arr2 = {4, 12};

        assertThatIllegalArgumentException().
                isThrownBy(() -> findOrderStat(arr1, arr2, 0)).
                withMessage("k = 0, should be in range [1;5]");
    }

    @Test
    public void findOrderStatKNegativeThrowsException() {
        final int[] arr1 = {1, 2, 5};
        final int[] arr2 = {4, 12};

        assertThatIllegalArgumentException().
                isThrownBy(() -> findOrderStat(arr1, arr2, -1)).
                withMessage("k = -1, should be in range [1;5]");
    }
}
