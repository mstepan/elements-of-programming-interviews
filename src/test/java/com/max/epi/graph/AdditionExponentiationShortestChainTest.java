package com.max.epi.graph;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.List;

import static com.max.epi.graph.AdditionExponentiationShortestChain.calculateShortestChain;
import static org.junit.Assert.assertEquals;

public final class AdditionExponentiationShortestChainTest {

    @Rule
    public final ExpectedException expectedException = ExpectedException.none();

    @Test
    public void calculateShortestChainBaseCases() {

        assertEquals(list(0), calculateShortestChain(0));

        assertEquals(list(1), calculateShortestChain(1));

        assertEquals(list(1, 2), calculateShortestChain(2));

        assertEquals(list(1, 2, 4, 5, 10, 15), calculateShortestChain(15));

        assertEquals(list(1, 2, 4, 8, 16, 32, 33, 66, 82, 115), calculateShortestChain(115));

    }

    @Test
    public void calculateShortestChainNegativeValue() {
        expectedException.expect(IllegalArgumentException.class);
        calculateShortestChain(-2);
    }

    private static List<Integer> list(int... values) {
        List<Integer> data = new ArrayList<>();

        for (int val : values) {
            data.add(val);
        }
        return data;
    }
}
