package com.max.util;

import java.util.Objects;

/**
 * Immutable pair class.
 *
 * @author Maksym Stepanenko.
 */
public final class Pair<K, V> {

    protected final K first;
    private final V second;

    private int hash;

    public Pair(K first, V second) {
        this.first = first;
        this.second = second;
    }

    public static <T> Pair<T, T> of(T val1, T val2) {
        return new Pair<>(val1, val2);
    }

    public static <T> Pair<T, T> empty() {
        return new Pair<>(null, null);
    }

    public K getFirst() {
        return first;
    }

    public V getSecond() {
        return second;
    }

    @Override
    public int hashCode() {

        if (hash == 0) {
            hash = Objects.hash(first, second);
        }

        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Pair<?, ?> other = (Pair<?, ?>) obj;

        return Objects.equals(first, other.first) && Objects.equals(second, other.second);
    }

    @Override
    public String toString() {
        return "(" + first + ", " + second + ")";
    }

}
