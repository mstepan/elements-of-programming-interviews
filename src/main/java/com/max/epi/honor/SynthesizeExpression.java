package com.max.epi.honor;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkState;

/**
 * 25.27. Synthesize an expression.
 */
final class SynthesizeExpression {

    private SynthesizeExpression() {
        throw new IllegalStateException("Can't instantiate utility only class");
    }

    private static final char NOOP = '_';
    private static final char ADD = '+';
    private static final char MUL = '*';

    /**
     * time: O(3^N * N)
     * space: O(N)
     */
    static boolean canBeSynthesized(int[] arr, int target) {
        checkArgument(arr != null, "null 'arr' passed");
        checkArgument(target >= 0, "Can't handle negative target");

        Iterator<char[]> it = new OpsIterator(arr.length - 1);

        while (it.hasNext()) {
            char[] ops = it.next();

            if (evaluate(arr, ops) == target) {
                return true;
            }
        }

        return false;
    }

    private static long evaluate(int[] args, char[] ops) {

        Deque<Long> argsStack = new ArrayDeque<>();
        Deque<Character> opsStack = new ArrayDeque<>();

        long cur = args[0];

        for (int i = 0; i < ops.length; ++i) {

            checkState(ops[i] == NOOP || ops[i] == ADD || ops[i] == MUL);

            if (ops[i] == NOOP) {

                cur = 10L * cur + args[i + 1];

                // check overflow
                if (cur > Integer.MAX_VALUE) {
                    return cur;
                }
            }
            else if (ops[i] == ADD) {
                argsStack.push(cur);

                while (!opsStack.isEmpty() && opsStack.peekFirst() == '*') {

                    checkState(argsStack.size() >= 2);

                    long second = argsStack.pop();
                    long first = argsStack.pop();

                    long res = first * second;

                    // check overflow
                    if (res > Integer.MAX_VALUE) {
                        return res;
                    }
                    argsStack.push(first * second);

                    opsStack.pop();
                }

                cur = args[i + 1];
                opsStack.push(ADD);
            }
            // '*'
            else {
                argsStack.push(cur);
                cur = args[i + 1];

                opsStack.push(MUL);
            }
        }

        argsStack.push(cur);

        return evaluateOnStack(argsStack, opsStack);
    }

    private static long evaluateOnStack(Deque<Long> argsStack, Deque<Character> opsStack) {
        while (!opsStack.isEmpty()) {

            char singleOperation = opsStack.pop();

            checkState(argsStack.size() >= 2);

            long second = argsStack.pop();
            long first = argsStack.pop();

            long res = (singleOperation == '+') ? (first + second) : (first * second);

            // check here
            if (res > Integer.MAX_VALUE) {
                return res;
            }

            argsStack.push(res);
        }

        return argsStack.pop();
    }

    private static final class OpsIterator implements Iterator<char[]> {

        private static final char[] ALL_OPS = {NOOP, ADD, MUL};

        private long curIndex;
        private final long totalCount;

        private final int[] ops;

        OpsIterator(int length) {
            this.ops = new int[length];
            this.totalCount = (long) Math.pow(ALL_OPS.length, length);
        }

        @Override
        public boolean hasNext() {
            return curIndex < totalCount;
        }

        @Override
        public char[] next() {

            if (!hasNext()) {
                throw new NoSuchElementException("OpsIterator fully exhausted");
            }

            char[] opsAsChars = constructOps(ops);

            moveNext(ops);
            ++curIndex;

            return opsAsChars;
        }

        private char[] constructOps(int[] ops) {
            char[] res = new char[ops.length];

            for (int i = 0; i < res.length; ++i) {
                res[i] = ALL_OPS[ops[i]];
            }

            return res;
        }

        private void moveNext(int[] ops) {

            final int mod = ALL_OPS.length;
            int carry = 1;

            for (int i = ops.length - 1; i >= 0 && carry != 0; --i) {
                int value = ops[i] + carry;
                ops[i] = value % mod;
                carry = value / mod;

            }
        }
    }


}
