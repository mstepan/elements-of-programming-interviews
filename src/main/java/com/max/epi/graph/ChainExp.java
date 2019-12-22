package com.max.epi.graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * 19.7. Variant. Additional chain exponentiation program to compute X^n. Use BFS to find the shortest chain.
 */
final class ChainExp {

    private static final int MAX_QUEUE_THRESHOLD = 1_000_000;

    private static final int[] EMPTY = new int[]{};

    private static final int[] ONE_ELEMENT = new int[]{1};
    private static final int[] TWO_ELEMENTS = new int[]{1, 2};

    static int[] findShortestChain(int expValue) {

        checkArgument(expValue >= 0, "negative exponent detected: " + expValue);

        if (expValue == 0) {
            return EMPTY;
        }
        if (expValue == 1) {
            return ONE_ELEMENT;
        }
        if (expValue == 2) {
            return TWO_ELEMENTS;
        }

        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(TWO_ELEMENTS);

        while (!queue.isEmpty()) {

            int[] curChain = queue.poll();

            for (int[] nextChain : nextPossibleChains(curChain)) {

                if (containsValue(nextChain, expValue)) {
                    return nextChain;
                }

                queue.add(nextChain);
                checkQueueSize(queue);
            }
        }

        return EMPTY;
    }

    private static void checkQueueSize(Queue<int[]> queue) {
        if (queue.size() >= MAX_QUEUE_THRESHOLD) {
            throw new IllegalStateException("Queue size too big, queue.size = " + queue.size() +
                                                    ", but MAX_QUEUE_THRESHOLD = " + MAX_QUEUE_THRESHOLD);
        }
    }

    private static Iterable<int[]> nextPossibleChains(int[] base) {

        List<int[]> res = new ArrayList<>();

        // try square exponent values: (X^3) ^ 2 = X ^ (3*2)
        for (int baseValue : base) {

            int value = baseValue * 2;

            int[] next = checkNextChain(value, base);

            if (next != null) {
                res.add(next);
            }
        }

        // try multiply exponent values: X^3 * X^5 = X^(3+5)
        for (int i = 0; i < base.length - 1; ++i) {
            for (int j = i + 1; j < base.length; ++j) {

                int value = base[i] + base[j];

                int[] next = checkNextChain(value, base);

                if (next != null) {
                    res.add(next);
                }
            }
        }

        return res;
    }

    private static int[] checkNextChain(int value, int[] base) {
        if (containsValue(base, value)) {
            return null;
        }
        int[] baseCopy = Arrays.copyOf(base, base.length + 1);
        baseCopy[baseCopy.length - 1] = value;
        return baseCopy;
    }

    private static boolean containsValue(int[] chain, int searchValue) {
        for (int value : chain) {
            if (value == searchValue) {
                return true;
            }
        }

        return false;
    }

//    private static boolean hasValue(int searchValue, int[] chain) {
//        for (int value : chain) {
//            if (value == searchValue) {
//                return true;
//            }
//        }
//
//        return false;
//    }

}
