package com.max.epi.heaps;

import org.junit.Test;

public class StackApiUsingHeapExhaustiveTest {

    @Test
    public void pushAndPopNoOverflow() {
        StackApiUsingHeap.StackAdapter stack = new StackApiUsingHeap.StackAdapter();

        for (long i = 0; i < (2L * Integer.MAX_VALUE) + 2L; ++i) {
            stack.push((int) i);
            stack.pop();
        }

    }

}
