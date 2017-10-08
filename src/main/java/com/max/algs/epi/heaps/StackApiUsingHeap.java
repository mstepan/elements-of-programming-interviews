package com.max.algs.epi.heaps;


import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 11.7. Implement a stack API using a heap.
 */
public final class StackApiUsingHeap {

    /**
     * Not thread safe.
     */
    static final class StackAdapter {

        private final Queue<StackEntry> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        // allow to store 2^32 values
        private int sequenceNo = Integer.MIN_VALUE;

        /**
         * time: O(lgN)
         */
        void push(Integer value) {

            if (sequenceNo == Integer.MAX_VALUE) {
                throw new IllegalStateException("Possible overflow");
            }

            maxHeap.add(new StackEntry(value, sequenceNo));
            ++sequenceNo;
        }

        /**
         * time: O(lgN)
         */
        Integer pop() {

            if (isEmpty()) {
                throw new IllegalStateException("Can't 'pop' from empty array");
            }

            StackEntry entry = maxHeap.poll();

            --sequenceNo;

            return entry.value;
        }

        /**
         * time: O(1)
         */
        Integer top() {
            if (isEmpty()) {
                return null;
            }
            return maxHeap.peek().value;
        }

        /**
         * time: O(1)
         */
        public int size() {
            return maxHeap.size();
        }

        /**
         * time: O(1)
         */
        public boolean isEmpty() {
            return size() == 0;
        }

        private static final class StackEntry implements Comparable<StackEntry> {
            final Integer value;
            final int sequence;

            StackEntry(Integer value, int sequence) {
                this.value = value;
                this.sequence = sequence;
            }

            @Override
            public int compareTo(StackEntry other) {
                return Integer.compare(sequence, other.sequence);
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;

                StackEntry that = (StackEntry) o;

                return sequence == that.sequence;
            }

            @Override
            public int hashCode() {
                return sequence;
            }
        }
    }


}
