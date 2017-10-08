package com.max.epi.heaps;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for QueueApiUsingHeap
 */
final class QueueApiUsingHeapTest {

    @Test
    void enqAndDeq() {

        QueueApiUsingHeap.QueueAdapter queue = new QueueApiUsingHeap.QueueAdapter();

        assertTrue(queue.isEmpty());
        assertEquals(0, queue.size());
        assertSame(null, queue.peek());

        queue.add(5);
        queue.add(7);
        queue.add(12);

        assertEquals(Integer.valueOf(5), queue.poll());
        assertEquals(Integer.valueOf(7), queue.poll());

        queue.add(3);
        queue.add(4);
        queue.add(10);
        queue.add(6);
        queue.add(8);

        assertEquals(Integer.valueOf(12), queue.poll());
        assertEquals(Integer.valueOf(3), queue.poll());
    }

    @Test
    void pollFromEmptyQueue() {
        assertThrows(IllegalStateException.class, () -> {
            QueueApiUsingHeap.QueueAdapter queue = new QueueApiUsingHeap.QueueAdapter();
            queue.poll();
        });


    }

    @Test
    void peekFromEmptyQueue() {
        QueueApiUsingHeap.QueueAdapter queue = new QueueApiUsingHeap.QueueAdapter();
        assertSame(null, queue.peek());
    }

    @Test
    void enqAndDeqWithOverflow() {
        QueueApiUsingHeap.QueueAdapter queue = new QueueApiUsingHeap.QueueAdapter();

        for (long i = 0; i < (2L * Integer.MAX_VALUE) + 2L; ++i) {
            queue.add((int) i);
            queue.poll();
        }
    }

}
