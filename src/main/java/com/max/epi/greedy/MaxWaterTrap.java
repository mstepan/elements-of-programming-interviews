package com.max.epi.greedy;

import com.max.util.Pair;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * 18.7. Find maximum water trapped by a pair of vertical lines.
 */
final class MaxWaterTrap {

    private MaxWaterTrap() {
        throw new IllegalStateException("Can't instantiate utility only class");
    }

    /**
     * time: O(N)
     * space: O(1)
     */
    static Pair<Integer, Integer> findMaxWaterTrap(int[] arr) {
        checkNotNull(arr, "null 'arr' passed");

        if (arr.length < 2) {
            return Pair.empty();
        }

        int from = 0;
        int to = arr.length - 1;

        int maxTrap = 0;
        Pair<Integer, Integer> maxPair = Pair.empty();

        while (from < to) {
            int valFrom = arr[from];
            int valTo = arr[to];

            int curTrap = (to - from) * Math.min(valFrom, valTo);

            if (curTrap > maxTrap) {
                maxTrap = curTrap;
                maxPair = Pair.of(from, to);
            }

            if (valFrom == valTo) {
                ++from;
                --to;
            }
            else if (valFrom < valTo) {
                ++from;
            }
            else {
                --to;
            }
        }

        return maxPair;
    }

}
