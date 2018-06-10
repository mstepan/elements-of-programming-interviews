package com.max.epi.honor;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkState;

/**
 * Find k-th order statistic in two sorted arrays. 'k' counted from 1.
 */
final class SearchInTwoSortedArrays {

    private SearchInTwoSortedArrays() {
        throw new IllegalStateException("Can't instantiate utility only class");
    }

    /**
     * time: O(lg K)
     * space: O(1)
     */
    static int findOrderStat(int[] arr1, int[] arr2, int k) {
        checkArgument(arr1 != null);
        checkArgument(arr2 != null);
        checkArgument(k > 0 && k <= arr1.length + arr2.length, "k = %s, should be in range [1;%s]",
                      k, arr1.length + arr2.length);

        int lo1 = 0;
        int hi1 = Math.min(k - 1, arr1.length - 1);

        int lo2 = 0;
        int hi2 = Math.min(k - 1, arr2.length - 1);

        int index = k;

        while (lo1 <= hi1 && lo2 <= hi2) {

            int mid1 = lo1 + (hi1 - lo1) / 2;
            int mid2 = lo2 + (hi2 - lo2) / 2;

            int cnt1 = mid1 - lo1 + 1;
            int cnt2 = mid2 - lo2 + 1;

            checkState(cnt1 > 0);
            checkState(cnt2 > 0);

            if (cnt1 + cnt2 > index) {
                if (arr1[mid1] > arr2[mid2]) {
                    hi1 = mid1 - 1;
                }
                else {
                    hi2 = mid2 - 1;
                }
            }
            else {
                if (arr1[mid1] < arr2[mid2]) {
                    lo1 = mid1 + 1;
                    index -= cnt1;
                }
                else {
                    lo2 = mid2 + 1;
                    index -= cnt2;
                }
            }

            checkState(index > 0);
        }

        checkState(lo1 <= hi1 || lo2 <= hi2);

        if (lo1 <= hi1) {
            return arr1[lo1 + (index - 1)];
        }

        return arr2[lo2 + (index - 1)];
    }

}
