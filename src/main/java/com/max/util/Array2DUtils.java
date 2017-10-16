package com.max.util;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Utility class for 2D arrays.
 */
public final class Array2DUtils {

    private Array2DUtils() {
        throw new IllegalStateException("Utility only class");
    }

    /**
     * Deep copy of 2D array.
     */
    public static void deepCopy(boolean[][] from, boolean[][] to) {
        checkNotNull(from);
        checkNotNull(to);
        checkArgument(from.length == to.length, "Dimensions aren't equals ('rows' not equal), can't copy 2D arrays.");

        for (int row = 0; row < from.length; ++row) {
            checkArgument(from[row].length == to[row].length,
                    "Dimensions aren't equals ('cols' not equal), can't copy 2D arrays.");

            System.arraycopy(from[row], 0, to[row], 0, to[row].length);
        }
    }


    /**
     * Fill 2D boolean array with appropriate values.
     */
    public static void fill(boolean[][] data, boolean value) {
        checkNotNull(data);

        for (int row = 0; row < data.length; ++row) {
            for (int col = 0; col < data[row].length; ++col) {
                data[row][col] = value;
            }
        }
    }

    public static boolean[][] create(int rows, int cols) {

        boolean[][] data = new boolean[rows][cols];

        for (int row = 0; row < rows; ++row) {
            data[row] = new boolean[cols];
        }

        return data;
    }

}

