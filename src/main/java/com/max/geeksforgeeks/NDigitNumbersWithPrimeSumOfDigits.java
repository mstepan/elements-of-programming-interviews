package com.max.geeksforgeeks;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Count N-digit numbers having sum of digits equal to a Prime Number.
 * <p>
 * https://www.geeksforgeeks.org/count-n-digit-numbers-having-sum-of-digits-equal-to-a-prime-number/
 * <p>
 * time: O(N * 9*N * 10) ~ O(N^2)
 * space: O(N*9*N), can be reduced to O (9*N)
 */
public class NDigitNumbersWithPrimeSumOfDigits {

    public static void main(String[] args) throws Exception {

        // n = 2, count = 33
        // n = 6, count = 222638
        final int n = 6;
        System.out.println(countSumOfDigitsAsPrimes(n));

        System.out.printf("java version: %s. NDigitNumbersWithPrimeSumOfDigits done...", System.getProperty("java.version"));
    }

    private static int countSumOfDigitsAsPrimes(int n) {

        if (n < 0) {
            throw new IllegalArgumentException("Can't count values with negative number of digits: " + n);
        }
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 4;
        }

        final int rows = n + 1;
        final int cols = 9 * n + 1;

        int[][] sol = new int[rows][cols];
        sol[1] = createOneDigitSolution(cols);

        for (int row = 2; row < rows; ++row) {
            for (int col = 1; col < cols; ++col) {
                for (int digit = 0; digit < 10 && (col + digit < cols); ++digit) {
                    sol[row][col + digit] += sol[row - 1][col];
                }
            }
        }

        Set<Integer> primes = sievePrimes(cols);
        final int lastRow = rows - 1;

        int cnt = 0;

        for (int col = 1; col < cols; ++col) {
            if (primes.contains(col)) {
                cnt += sol[lastRow][col];
            }
        }

        return cnt;
    }

    private static int[] createOneDigitSolution(int length) {
        int[] arr = new int[length];
        for (int i = 0; i < 10; ++i) {
            arr[i] = 1;
        }
        return arr;
    }

    private static Set<Integer> sievePrimes(int n) {
        boolean[] primes = new boolean[n];
        Arrays.fill(primes, true);
        primes[0] = false;
        primes[1] = false;

        final int lastValue = ((int) Math.sqrt(n)) + 1;

        for (int i = 2; i <= lastValue; ++i) {
            if (primes[i]) {
                for (int composite = i + i; composite < primes.length; composite += i) {
                    primes[composite] = false;
                }
            }
        }

        Set<Integer> result = new HashSet<>();
        for (int i = 2; i < primes.length; ++i) {
            if (primes[i]) {
                result.add(i);
            }
        }
        return result;
    }


}
