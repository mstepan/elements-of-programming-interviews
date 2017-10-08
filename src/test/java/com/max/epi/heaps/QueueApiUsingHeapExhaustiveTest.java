package com.max.epi.heaps;

import org.junit.Test;

public class QueueApiUsingHeapExhaustiveTest {

    @Test
    public void enqAndDeqWithOverflow() {
        QueueApiUsingHeap.QueueAdapter queue = new QueueApiUsingHeap.QueueAdapter();

        for (long i = 0; i < (2L * Integer.MAX_VALUE) + 2L; ++i) {
            queue.add((int) i);
            queue.poll();
        }
    }
}
