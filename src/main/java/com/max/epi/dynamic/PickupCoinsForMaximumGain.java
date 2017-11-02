package com.max.epi.dynamic;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * 17.9. Pick up coins for maximum gain.
 */
final class PickupCoinsForMaximumGain {

    private PickupCoinsForMaximumGain() {
        throw new IllegalStateException("Utility only class can't be instantiated");
    }

    /**
     * Precalculate prefix sum, which allow us quickly
     * obtain sum for any subarray.
     */
    private static final class SliceSum {

        // use long here to prevent any integer overflow
        final long[] prefixSum;

        SliceSum(int[] arr) {
            assert arr != null : "null 'arr' dected in constructor";

            prefixSum = new long[arr.length];
            prefixSum[0] = arr[0];

            for (int i = 1; i < prefixSum.length; ++i) {
                prefixSum[i] = prefixSum[i - 1] + arr[i];
            }
        }

        long sum(int from, int to) {
            assert from <= to : "from > to";
            assert from >= 0 && to < prefixSum.length : "out of array bounds request detected";

            if (from == 0) {
                return prefixSum[to];
            }

            return prefixSum[to] - prefixSum[from - 1];
        }
    }

    private static final int PLAYERS_COUNT = 2;

    /**
     * time: O(N^2)
     * space: O(N)
     * <p>
     * N = coins.length
     */
    static long maxPoints(int[] coins) {
        checkNotNull(coins);
        checkValidCoins(coins);

        final int rows = coins.length;
        final int cols = coins.length;

        final long[][][] sol = new long[2][rows][cols];
        SliceSum slice = new SliceSum(coins);

        for (int row = rows - 1; row >= 0; --row) {
            for (int col = row; col < cols; ++col) {

                assert row <= col;

                for (int p = 0; p < PLAYERS_COUNT; ++p) {
                    int elements = col - row + 1;

                    assert elements > 0;

                    // 1 or 2 elements, just pick max
                    if (elements < 3) {
                        sol[p][row][col] = Math.max(coins[row], coins[col]);
                    }
                    // more then 2 elements
                    else {
                        sol[p][row][col] = Math.max(
                                coins[row] + (slice.sum(row + 1, col) - sol[(p + 1) % 2][row + 1][col]),
                                coins[col] + (slice.sum(row, col - 1) - sol[(p + 1) % 2][row][col - 1])
                        );
                    }
                }
            }
        }

        return sol[0][0][cols - 1];
    }

    private static void checkValidCoins(int[] coins) {
        assert coins != null;
        for (int singleCoin : coins) {
            checkArgument(singleCoin > 0, "Negative coin detected %s", singleCoin);
        }
    }

}
