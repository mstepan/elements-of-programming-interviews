package com.max.util;


public final class NumberUtils {

    private NumberUtils() {
        throw new IllegalStateException("Can't instantiate utility only class");
    }


    /**
     * Compare two double values up to 'decimalPlaces' after comma.
     */
    public static int compareRounded(double first, double second, int decimalPlaces) {
        double scale = Math.pow(10.0, decimalPlaces);
        return Double.compare(Math.round(first * scale),
                Math.round(second * scale));
    }

    /**
     * Return minimum of 2 values.
     * Optimized version: can use instruction set parallelism.
     */
    public static int min(int x, int y) {
        return y ^ ((x ^ y) & (x < y ? -1 : 0));
    }


}
