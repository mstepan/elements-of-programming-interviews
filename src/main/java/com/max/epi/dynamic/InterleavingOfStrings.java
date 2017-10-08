package com.max.epi.dynamic;


import java.util.Arrays;

import static com.google.common.base.Preconditions.checkNotNull;


final class InterleavingOfStrings {

    private InterleavingOfStrings() {
    }

    /**
     * Check is 'base' string is interleaving of 's1' and 's2' strings
     * using dynamic programming approach.
     * <p>
     * N - s1.length()
     * M - s2.length()
     * <p>
     * time: O(N*M)
     * space: O(min(N, M))
     */
    static boolean isInterleaving(String base, String s1, String s2) {
        checkNotNull(base);
        checkNotNull(s1);
        checkNotNull(s2);

        if (base.length() != s1.length() + s2.length()) {
            return false;
        }

        if (s1.length() == 0) {
            return base.equals(s2);
        }

        if (s2.length() == 0) {
            return base.equals(s1);
        }

        String minStr = s1;
        String otherStr = s2;

        if (s2.length() < s1.length()) {
            minStr = s2;
            otherStr = s1;
        }

        return checkInterleavingOfString(base, minStr, otherStr);
    }

    private static boolean checkInterleavingOfString(String base, String minStr, String otherStr) {
        final int rows = otherStr.length() + 1;
        final int cols = minStr.length() + 1;

        final boolean[] prev = new boolean[cols];
        prev[0] = true;

        // we will reuse 'prev' and 'cur' arrays and do not allocate additional memory
        final boolean[] cur = new boolean[cols];

        // fill 1st row
        for (int col = 1; col < cols; ++col) {
            prev[col] = prev[col - 1] && (base.charAt(col - 1) == minStr.charAt(col - 1));
        }

        for (int row = 1; row < rows; ++row) {
            cur[0] = prev[0] && (base.charAt(row - 1) == otherStr.charAt(row - 1));

            boolean lastRowCombinedOrValue = cur[0];

            for (int col = 1; col < cols; ++col) {

                char baseCh = base.charAt(row + col - 1);
                char otherCh = otherStr.charAt(row - 1);
                char minCh = minStr.charAt(col - 1);

                cur[col] = (otherCh == baseCh && prev[col]) ||
                        (minCh == baseCh && cur[col - 1]);

                lastRowCombinedOrValue = lastRowCombinedOrValue || cur[col];
            }

            // last row was fully 'false',
            // just return 'false' and do not proceed further
            if (!lastRowCombinedOrValue) {
                return false;
            }

            System.arraycopy(cur, 0, prev, 0, cur.length);
            Arrays.fill(cur, false);
        }

        return prev[cols - 1];
    }
}
