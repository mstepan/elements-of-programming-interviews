package com.max.util;


import java.util.Arrays;

public final class MatrixUtils {


    private static final String LINE_SEPARATOR = System.getProperty("line.separator");


    private MatrixUtils() {
        throw new IllegalStateException("Can't instantiate utility only class");
    }


    /**
     * Deep copy of 2 dimensional array.
     */
    public static int[][] deepCopy(int[][] matrix) {

        final int rows = matrix.length;

        int[][] res = new int[rows][];

        for (int i = 0; i < rows; ++i) {
            res[i] = Arrays.copyOf(matrix[i], matrix[i].length);
        }

        return res;
    }

    public static String toString(char[][] matrix) {

        StringBuilder buf = new StringBuilder();

        for (char[] row : matrix) {
            for (int j = 0; j < row.length; j++) {
                if (j > 0) {
                    buf.append(", ");
                }
                buf.append(row[j]);
            }

            buf.append(LINE_SEPARATOR);
        }


        return buf.toString();
    }

    public static String toString(int[][] matrix) {

        StringBuilder buf = new StringBuilder();

        for (int[] row : matrix) {
            for (int j = 0; j < row.length; j++) {
                if (j > 0) {
                    buf.append(", ");
                }
                buf.append(row[j]);
            }

            buf.append(LINE_SEPARATOR);
        }


        return buf.toString();
    }

    public static String toString(double[][] matrix) {

        StringBuilder buf = new StringBuilder();

        for (double[] row : matrix) {
            for (int j = 0; j < row.length; j++) {
                if (j > 0) {
                    buf.append(", ");
                }
                buf.append(row[j]);
            }

            buf.append(LINE_SEPARATOR);
        }


        return buf.toString();
    }

    public static <T> String toString(T[][] matrix) {

        StringBuilder buf = new StringBuilder();

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (j > 0) {
                    buf.append(", ");
                }
                buf.append(matrix[i][j]);
            }

            buf.append(LINE_SEPARATOR);
        }


        return buf.toString();
    }


}
