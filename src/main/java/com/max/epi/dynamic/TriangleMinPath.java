package com.max.epi.dynamic;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * 17.8. Find min weight path in triangle like 2 dimensional array.
 */
final class TriangleMinPath {

    private TriangleMinPath() {
        throw new IllegalStateException("TriangleMinPath is utility class");
    }

    /**
     * N - triangle.length
     * <p>
     * time: O(N^2)
     * space: O(N)
     */
    static int findMinPathWeight(int[][] triangle) {
        checkNotNull(triangle);
        checkIsTriangle(triangle);

        if (triangle.length == 0) {
            return 0;
        }

        int[] prev = {triangle[0][0]};

        for (int row = 1; row < triangle.length; ++row) {

            final int curCols = triangle[row].length;
            final int[] cur = new int[curCols];

            cur[0] = prev[0] + triangle[row][0];
            cur[cur.length - 1] = prev[prev.length - 1] + triangle[row][curCols - 1];

            for (int col = 1; col < curCols - 1; ++col) {
                int val = triangle[row][col];
                cur[col] = Math.min(prev[col - 1], prev[col]) + val;
            }

            prev = cur;
        }

        return findMin(prev);
    }

    private static void checkIsTriangle(int[][] triangle) {
        for (int row = 0; row < triangle.length; ++row) {
            if (triangle[row] == null || triangle[row].length != row + 1) {
                throw new IllegalArgumentException("Not a triangle passed");
            }
        }
    }

    /**
     * M - arr.length
     * <p>
     * time: O(M)
     * space: O(1)
     */
    private static int findMin(int[] arr) {
        assert arr != null && arr.length > 0;

        int minValue = arr[0];

        for (int i = 1; i < arr.length; ++i) {
            minValue = Math.min(minValue, arr[i]);
        }
        return minValue;
    }
}
