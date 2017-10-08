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
     * space: O(N^2)
     */
    static int countWays(int n) {
        checkArgument(n >= 0);

        if (n < 3) {
            return n;
        }

        int[][] sol = new int[n][n];
        Arrays.fill(sol[0], 1);

        for (int row = 1; row < n; ++row) {
            sol[row][0] = 1;

            for (int col = 1; col < n; ++col) {
                sol[row][col] = sol[row][col - 1] + sol[row - 1][col];
            }
        }


        return sol[n - 1][n - 1];
    }

}
