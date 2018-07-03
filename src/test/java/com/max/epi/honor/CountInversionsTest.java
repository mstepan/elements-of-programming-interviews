package com.max.epi.honor;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static com.max.epi.honor.SynthesizeExpression.canBeSynthesized;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CountInversionsTest {

    @Rule
    public final ExpectedException thrown = ExpectedException.none();

    @Test
    public void canBeSynthesizedNormalFlowSimpleCase() {
        assertTrue(canBeSynthesized(new int[]{1, 2, 3}, 7));
        assertTrue(canBeSynthesized(new int[]{1, 2, 3}, 123));
        assertTrue(canBeSynthesized(new int[]{1, 2, 3}, 6));
        assertTrue(canBeSynthesized(new int[]{1, 2, 3}, 15));
        assertTrue(canBeSynthesized(new int[]{1, 2, 3}, 5));
        assertTrue(canBeSynthesized(new int[]{1, 2, 3}, 24));
        assertTrue(canBeSynthesized(new int[]{1, 2, 3}, 36));
    }

    @Test
    public void canBeSynthesizedComplexCase() {
        assertTrue(canBeSynthesized(new int[]{1, 2, 3, 2, 5, 3, 7, 8, 5, 9}, 995));
    }

    @Test
    public void canBeSynthesizedSingleDigitArray() {
        assertTrue(canBeSynthesized(new int[]{3}, 3));
        assertFalse(canBeSynthesized(new int[]{3}, 2));
        assertFalse(canBeSynthesized(new int[]{3}, 1));
    }
}
