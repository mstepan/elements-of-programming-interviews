package com.max.epi.honor;

import com.max.util.ArrayUtils;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import static com.max.epi.honor.CountInversions.countInversions;
import static com.max.epi.honor.CountInversions.countInversionsBruteforce;
import static org.junit.Assert.assertEquals;

public class CountInversionsTest {

    @Rule
    public final ExpectedException thrown = ExpectedException.none();

    @Test
    public void countInversionsNormalFlow() {
        assertEquals(10, countInversions(new int[]{3, 6, 4, 2, 5, 1}));
    }

    @Test
    public void countInversionsRandomArrays() {

        final Random rand = ThreadLocalRandom.current();

        for (int it = 0; it < 100; ++it) {
            int[] arr = ArrayUtils.generateRandomArray(10 + rand.nextInt(1000));

            assertEquals(countInversionsBruteforce(arr), countInversions(arr));
        }

    }


    @Test
    public void countInversionsNullArrayThrowsException() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("null 'arr' passed");

        countInversions(null);
    }
}
