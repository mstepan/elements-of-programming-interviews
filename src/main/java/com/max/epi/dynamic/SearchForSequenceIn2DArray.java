package com.max.epi.dynamic;


import com.max.util.Array2DUtils;
import com.max.util.ArrayUtils;

import static com.google.common.base.Preconditions.checkNotNull;


final class SearchForSequenceIn2DArray {

    private SearchForSequenceIn2DArray() {
        throw new IllegalStateException("Utility only class");
    }


    /**
     * Search for a sequence in a 2D array.
     * Cells from 2D sequence can be used multiple times.
     * <p>
     * N = data rows
     * M = data cols
     * K = sequence length
     * <p>
     * time: O(K*N*M)
     * space: O(N*M)
     */
    static boolean exists(int[] sequence, int[][] data) {

        checkNotNull(sequence);
        checkNotNull(data);

        if (sequence.length == 0) {
            return true;
        }

        if (data.length == 0) {
            return false;
        }

        ArrayUtils.checkAllDimensionsNotNull(data);

        final int rows = data.length;
        final int cols = data[0].length;

        final boolean[][] prevSol = Array2DUtils.create(rows, cols);
        final boolean[][] curSol = Array2DUtils.create(rows, cols);

        for (int i = 0; i < sequence.length; ++i) {

            Array2DUtils.fill(curSol, false);

            boolean orValues = false;

            int value = sequence[i];

            for (int row = 0; row < rows; ++row) {
                for (int col = 0; col < cols; ++col) {
                    if (value == data[row][col]) {
                        curSol[row][col] = (i == 0) || (siblingsOrValue(prevSol, row, col));
                        orValues = orValues || curSol[row][col];
                    }
                }
            }

            // complete search if all values are 'false'
            if (!orValues) {
                return false;
            }

            // deep copy here
            Array2DUtils.deepCopy(curSol, prevSol);
        }


        return ArrayUtils.or(prevSol);
    }

    private static boolean siblingsOrValue(boolean[][] data, int row, int col) {
        // up cell
        return (row != 0 && data[row - 1][col]) ||

                // left cell
                (col != 0 && data[row][col - 1]) ||

                // down  cell
                (row != data.length - 1 && data[row + 1][col]) ||

                // right cell
                (col != data[row].length - 1 && data[row][col + 1]);
    }

}
