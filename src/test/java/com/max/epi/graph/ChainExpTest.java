package com.max.epi.graph;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ChainExpTest {

    @Test
    public void findShortestChain() {
        assertThat(ChainExp.findShortestChain(15)).contains(1, 2, 4, 5, 10, 15);
    }

    @Test
    public void findShortestChainVeryBigExponentShouldFail() {
        assertThatThrownBy(() -> ChainExp.findShortestChain(999_999)).
                isInstanceOf(IllegalStateException.class).
                hasMessage("Queue size too big, queue.size = 1000000, but MAX_QUEUE_THRESHOLD = 1000000");

    }

    @Test
    public void findShortestChainForZero() {
        assertThat(ChainExp.findShortestChain(0)).isEmpty();
    }

    @Test
    public void findShortestChainForOne() {
        assertThat(ChainExp.findShortestChain(1)).contains(1);
    }

    @Test
    public void findShortestChainForTwo() {
        assertThat(ChainExp.findShortestChain(2)).contains(1, 2);
    }

    @Test
    public void findShortestChainForNegativeShouldFail() {
        assertThatThrownBy(() -> ChainExp.findShortestChain(-3)).
                isInstanceOf(IllegalArgumentException.class).
                hasMessage("negative exponent detected: -3");
    }
}
