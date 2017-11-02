package com.max.epi.dynamic;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PickupCoinsForMaximumGainTest {

    @Test
    public void maxPoints() {
        final int[] coins = {25, 5, 10, 5, 10, 5, 10, 25, 1, 25, 1, 25, 1, 25, 5, 10};
        assertEquals(140L, PickupCoinsForMaximumGain.maxPoints(coins));
    }

    @Test(expected = IllegalArgumentException.class)
    public void maxPointsWithNegativeCoinThrowsException() {
        PickupCoinsForMaximumGain.maxPoints(new int[]{25, 5, 1, 25, 1, -25, 1});
    }

    @Test(expected = NullPointerException.class)
    public void maxPointsWithNullThrowsNPE() {
        PickupCoinsForMaximumGain.maxPoints(null);
    }
}
