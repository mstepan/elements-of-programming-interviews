package com.max.epi.honor;

import java.util.Arrays;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * Find K-th biggest elements in array of length N. Very big N and small K.
 */
final class FindKBiggestElementsInArray {

    private FindKBiggestElementsInArray() {
        throw new IllegalStateException("Can't instantiate utility only class");
    }

    /**
     * time: O(N * (2K*lgK + K)) ~ O(N)
     * space: O(2K) ~ O(1)
     */
    static int[] findKthBiggest(int[] arr, int k) {
        checkArgument(arr != null, "can't process null 'arr'");
        checkArgument(k >= 0, "negative 'k' passed");

        if (k == 0) {
            return new int[]{};
        }

        if (arr.length <= k) {
            return Arrays.copyOf(arr, arr.length);
        }

        final int[] buffer = new int[k << 1];

        System.arraycopy(arr, 0, buffer, k, k);

        int index = 0;
        for (int i = k; i < arr.length; ++i) {

            buffer[index] = arr[i];
            ++index;

            if (index == k) {
                Arrays.sort(buffer);
                index = 0;
            }
        }

        Arrays.sort(buffer);

        return Arrays.copyOfRange(buffer, k, buffer.length);
    }


}
