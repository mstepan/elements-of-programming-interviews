package com.max.epi.heaps;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.invoke.MethodHandles;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 11.7. Variant. Implement a queue API using a heap.
 */
public class QueueApiUsingHeap {

    private static final Logger LOG = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    static final class QueueAdapter {

        private int sequenceNo = Integer.MIN_VALUE;

        private Queue<QueueEntry> minHeap = new PriorityQueue<>();

        /**
         * time: O(lgN), but more probably will be O(1) in a typical min heap implementation.
         */
        public void add(Integer value) {

            // If 'sequenceNo' overflowed, reinsert elements again.
            if (sequenceNo == Integer.MAX_VALUE) {

                Queue<QueueEntry> oldMinHeap = new PriorityQueue<>();
                minHeap = new PriorityQueue<>();
                sequenceNo = Integer.MIN_VALUE;

                while (!oldMinHeap.isEmpty()) {
                    minHeap.add(new QueueEntry(oldMinHeap.poll().value, sequenceNo));
                }

                LOG.info("Reinsert completed");
            }

            minHeap.add(new QueueEntry(value, sequenceNo));

            ++sequenceNo;
        }

        /**
         * time: O(lgN)
         */
        Integer poll() {

            if (isEmpty()) {
                throw new IllegalStateException("Empty queue");
            }

            return minHeap.poll().value;

        }

        /**
         * time: O(1)
         */
        Integer peek() {

            if (isEmpty()) {
                return null;
            }

            return minHeap.peek().value;
        }

        /**
         * time: O(1)
         */
        public int size() {
            return minHeap.size();
        }

        /**
         * time: O(1)
         */
        public boolean isEmpty() {
            return size() == 0;
        }

        private static final class QueueEntry implements Comparable<QueueEntry> {
            final Integer value;
            final int sequence;

            QueueEntry(int value, int sequence) {
                this.value = value;
                this.sequence = sequence;
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) {
                    return true;
                }
                if (o == null || getClass() != o.getClass()) {
                    return false;
                }

                QueueEntry that = (QueueEntry) o;

                return sequence == that.sequence;
            }

            @Override
            public int hashCode() {
                return sequence;
            }

            @Override
            public int compareTo(QueueEntry other) {
                return Integer.compare(sequence, other.sequence);
            }
        }
    }

}
