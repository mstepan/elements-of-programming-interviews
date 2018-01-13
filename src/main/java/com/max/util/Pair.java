package com.max.util;

import java.util.Objects;

/**
 * Immutable pair class.
 *
 * @author Maksym Stepanenko.
 */
public class Pair<K, V> {

    private static final Pair EMPTY = new Pair<>(null, null);

    protected final K first;
    private final V second;

    private int hash;

    public Pair(K first, V second) {
        this.first = first;
        this.second = second;
    }

    public static <T> Pair<T, T> of(T first, T second) {
        return new Pair<>(first, second);
    }

    public static <T> Pair<T, T> from(T value) {
        return new Pair<>(value, value);
    }

    @SuppressWarnings("unchecked")
    public static <T> Pair<T, T> empty() {
        return EMPTY;
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

            int hashedValue = 17;

            hashedValue = 31 * hashedValue + (first == null ? 0 : first.hashCode());
            hashedValue = 31 * hashedValue + (second == null ? 0 : second.hashCode());

            hash = hashedValue;
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

        return Objects.equals(first, other.first) &&
                Objects.equals(second, other.second);
    }

    @Override
    public String toString() {
        return "(" + first + ", " + second + ")";
    }

}
