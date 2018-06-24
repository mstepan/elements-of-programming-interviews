package com.max.epi.honor;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkState;

/**
 * 25.18. Find an element that appears only once.
 * <p>
 * All elements appears 3 times except of one element that appear only once.
 * Find the element that appears only once.
 */
final class AppearsOnce {

    private AppearsOnce() {
        throw new IllegalStateException("Can't instantiate utility only class");
    }

    private static final int REPETITIONS_COUNT = 3;

    /**
     * time: O(N)
     * space: O(1)
     * <p>
     * Important: we have an assumption here that array always contains one unique element
     * and all other appears exactly 3 times, otherwise algorithm will return arbitrary value.
     */
    static int findUnique(int[] arr) {
        checkArgument(arr != null, "null array passed");
        checkArgument(arr.length >= 4 && (arr.length % 3) == 1, "incorrect array length detected");

        final int[] counter = new int[Integer.SIZE];

        for (int value : arr) {
            for (int i = 0; i < counter.length; ++i) {

                checkState(i <= Integer.SIZE, "i > 32");

                if ((value & (1 << i)) != 0) {
                    ++counter[i];
                }
            }
        }

        return intFromBits(counter, REPETITIONS_COUNT);
    }

    private static int intFromBits(int[] counter, int mod) {

        int res = 0;

        for (int i = 0; i < counter.length; ++i) {
            if (counter[i] % mod != 0) {
                res |= (1 << i);
            }
        }

        return res;
    }

}
