package com.max.epi.hashing;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.invoke.MethodHandles;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * 13.13 Test the Collatz conjecture for 1st billion of numbers.
 */
public final class CollatzConjecture {

    private static final Logger LOG = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    /*
    Time: 9865.0 ms
    com.max.epi.hashing.CollatzConjecture  - All OK
     */
    private CollatzConjecture() throws Exception {

        final long startTime = System.nanoTime();

        boolean collatzHolds = isCollatzHolds(1_000_000_000);

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


    private static final int MAX_ITERATIONS_TO_CHECK_COUNT = 1_000_000;

    static boolean isCollatzHolds(int n) {
        checkArgument(n >= 0, "Negative value passed to check Collatz conjecture: n = %s", n);

        if (n < 2) {
            return true;
        }

//        Set<Long> cache = new HashSet<>();

        // start from '3' and skip all even numbers, because Collatz conjecture holds for all even values (next = current/2)
        for (int valueToCheck = 3; valueToCheck <= n; valueToCheck += 2) {

            long cur = valueToCheck;
            boolean converged = false;

            for (int it = 0; it < MAX_ITERATIONS_TO_CHECK_COUNT; ++it) {

                long next = collatz(cur);

                assert next > 0 : "Negative value detected";

                if (next < valueToCheck /*|| cache.contains(next) */) {
                    converged = true;
                    break;
                }

//                cache.add(next);
                cur = next;
            }

            if (!converged) {
                LOG.warn("Collatz conjecture wasn't converged for value " + valueToCheck +
                                 " after " + MAX_ITERATIONS_TO_CHECK_COUNT + " iterations");
                return false;
            }

//            cache.remove((long)valueToCheck);
        }

//        LOG.info("cache.size: " + cache.size());

        return true;
    }

    /**
     * We skill all even number, so we just need to calculate Collatz for odd values.
     */
    private static long collatz(long val) {

        assert val > 0 : "Negative or zero value detected for collatz: " + val;

        //even case, next = val/2
        if ((val & 1) == 0) {
            return val >> 1;
        }

        // odd case: next = 3*val + 1
        else {
            long next = 3L * val + 1L;

            assert next > val : "Overflow detected for val: " + val + ", next: " + next;

            return next;
        }
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

