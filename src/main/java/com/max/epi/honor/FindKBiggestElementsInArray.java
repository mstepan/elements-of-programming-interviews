package com.max.epi.honor;

import java.util.Arrays;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * 25.17. Find the K-th largest element — large N, small K.
 */
final class FindKBiggestElementsInArray {

    private FindKBiggestElementsInArray() {
        throw new IllegalStateException("Can't instantiate utility only class");
    }

    /**
     * time: O(N * K), when N >> K, O(N)
     * space: O(2K), when N >> K, O(1)
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
                rearrangeOrderStat(buffer, k);
                index = 0;
            }
        }

        rearrangeOrderStat(buffer, k);

        return Arrays.copyOfRange(buffer, k, buffer.length);
    }

    private static void rearrangeOrderStat(int[] arr, int k) {

        int from = 0;
        int to = arr.length - 1;
        int index = k;

        while (index != 0) {
            int pivot = arr[to];

            int boundary = from - 1;

            for (int i = from; i < to; ++i) {
                if (arr[i] <= pivot) {
                    swap(arr, boundary + 1, i);
                    ++boundary;
                }
            }

            swap(arr, boundary + 1, to);
            ++boundary;

            int elems = boundary - from + 1;

            if (elems <= index) {
                index -= elems;
                from = boundary + 1;
            }
            else {
                to = boundary - 1;
            }
        }
    }

    private static void swap(int[] arr, int from, int to) {
        int temp = arr[from];
        arr[from] = arr[to];
        arr[to] = temp;
    }


}
