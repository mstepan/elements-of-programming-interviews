package com.max.epi.honor;

import java.util.Arrays;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * 25.28. Design an algorithm that takes an array in integers and
 * returns the number of inverted pairs of indices.
 */
final class CountInversions {

    private CountInversions() {
        throw new IllegalStateException("Can't instantiate utility only class");
    }


    /**
     * time: O(N*lgN)
     * space: O(lg N)
     */
    static int countInversions(int[] arr) {
        checkArgument(arr != null, "null 'arr' passed");
        return countInvRec(arr, 0, arr.length - 1);
    }

    private static int countInvRec(int[] arr, int from, int to) {

        final int elems = to - from + 1;

        if (elems < 2) {
            return 0;
        }

        final int mid = from + (to - from) / 2;

        // count inversion in left side
        int cnt = countInvRec(arr, from, mid);

        // count inversion in right side
        cnt += countInvRec(arr, mid + 1, to);

        // count inversion on mid boundary
        Arrays.sort(arr, from, mid + 1);
        Arrays.sort(arr, mid + 1, to + 1);

        int left = from;
        int right = mid + 1;
        int boundary = mid + 1;

        while (left < boundary) {
            while (right <= to && arr[left] > arr[right]) {
                ++right;
            }

            cnt += (right - boundary);
            ++left;
        }

        return cnt;
    }

    /**
     * time: O(N^2)
     * space: O(1)
     */
    static int countInversionsBruteforce(int[] arr) {

        int cnt = 0;

        for (int i = 0; i < arr.length - 1; ++i) {
            for (int j = i + 1; j < arr.length; ++j) {
                if (arr[i] > arr[j]) {
                    ++cnt;
                }
            }
        }

        return cnt;
    }

}
