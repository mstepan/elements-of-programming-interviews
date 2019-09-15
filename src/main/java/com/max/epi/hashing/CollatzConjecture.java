package com.max.epi.hashing;


import org.apache.log4j.Logger;

import java.util.HashSet;
import java.util.Set;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * 13.13 Test the Collatz conjecture.
 */
public final class CollatzConjecture {

    private static final Logger LOG = Logger.getLogger(CollatzConjecture.class);

    /*

    jvm parameters: -Xmx10G
    Cache size: 102_661_014 (Set<Integer> cache)
    Time 341389.6 ms => ~5.7 minutes
    com.max.epi.hashing.CollatzConjecture  - All OK
     */
    private CollatzConjecture() throws Exception {

        final long startTime = System.nanoTime();

        boolean collatzHolds = isCollatzHolds(100_000_000);

        final double elapsed = (System.nanoTime() - startTime) / 1E6;

        System.out.printf("time %.1f ms%n", elapsed);

        if (collatzHolds) {
            LOG.info("All OK");
        }
        else {
            LOG.info("Failure for Collatz");
        }

        System.out.printf("CollatzConjecture: java-%s %n", System.getProperty("java.version"));
    }


    private static final int MAX_ITERATIONS_TO_CHECK_COUNT = 10_000;

    static boolean isCollatzHolds(int n) {
        checkArgument(n >= 0, "Negative value passed to check Collatz conjecture: n = %s", n);

        if (n < 2) {
            return true;
        }

        // Bloom filter can be used here instead of ordinary hash table to reduce the space.
        Set<Integer> cache = new HashSet<>();

        for (int valueToCheck = 3; valueToCheck <= n; ++valueToCheck) {

            int cur = valueToCheck;
            boolean converged = false;

            for (int it = 0; it < MAX_ITERATIONS_TO_CHECK_COUNT; ++it) {

                int next = collatz(cur);

                if (next < valueToCheck || cache.contains(next)) {
                    converged = true;
                    break;
                }

                cache.add(next);
                cur = next;
            }

            if (!converged) {
                LOG.warn("Collatz conjecture wasn't converged for value " + valueToCheck +
                                 " after " + MAX_ITERATIONS_TO_CHECK_COUNT + " iterations");
                return false;
            }

            cache.remove(valueToCheck);
        }

        LOG.info("Cache size: " + cache.size());

        return true;
    }

    private static int collatz(int val) {

        assert val > 0 : "Negative or zero value detected for collatz: " + val;

        // base case
        if (val == 1) {
            return 1;
        }

        // even case: next = 3*val + 1
        if ((val & 1) == 0) {
            return 3 * val + 1;
        }

        // odd case: next = val/2
        return val >> 1;
    }

    public static void main(String[] args) {
        try {
            new CollatzConjecture();
        }
        catch (Exception ex) {
            LOG.error(ex.getMessage(), ex);
        }
    }
}

