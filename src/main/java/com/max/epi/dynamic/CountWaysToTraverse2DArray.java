package com.max.epi.dynamic;


import java.util.Arrays;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * 17.3 - Count the number of ways to traverse a 2D array starting from upper left corner and reaching bottom right.
 * You can only move horizontally or vertically.
 */
final class CountWaysToTraverse2DArray {

    private CountWaysToTraverse2DArray() {
    }

    /**
     * time: O(N^2)
     * space: O(N)
     */
    static int countWays(int n) {
        checkArgument(n >= 0);

        if (n < 3) {
            return n;
        }

        int[] prev = new int[n];
        Arrays.fill(prev, 1);

        int[] cur = new int[n];

        for (int row = 1; row < n; ++row) {
            cur[0] = 1;

            for (int col = 1; col < n; ++col) {
                cur[col] = cur[col - 1] + prev[col];
            }

            System.arraycopy(cur, 0, prev, 0, cur.length);
            Arrays.fill(cur, 0);
        }


        return prev[n - 1];
    }

    /**
     * Bruteforce solution for testing purpose with time O(2^N) and space O(N).
     */
    static int countWaysBruteforce(int n) {
        return countWaysRec(n - 1, n - 1);
    }

    private static int countWaysRec(int row, int col) {
        if (row == 0 || col == 0) {
            return 1;
        }

        return countWaysRec(row, col - 1) + countWaysRec(row - 1, col);
    }

}
