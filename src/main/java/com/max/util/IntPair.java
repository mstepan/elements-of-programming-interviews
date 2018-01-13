package com.max.util;

/**
 * Specialization of {@link Pair} class which represents pair of integer values.
 */
public final class IntPair extends Pair<Integer, Integer> {

    public IntPair(Integer first, Integer second) {
        super(first, second);
    }

    public static IntPair of(int first, int second) {
        return new IntPair(first, second);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
