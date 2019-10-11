package com.max.epi.honor;

import com.max.util.ArrayUtils;
import org.junit.Test;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import static com.max.epi.honor.CountInversions.countInversions;
import static com.max.epi.honor.CountInversions.countInversionsBruteforce;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.junit.Assert.assertEquals;

public final class CountInversionsTest {

    @Test
    public void countInversionsNormalFlow() {
        assertEquals(10, countInversions(new int[]{3, 6, 4, 2, 5, 1}));
    }

    @Test
    public void countInversionsRandomArrays() {

        final Random rand = ThreadLocalRandom.current();

        for (int it = 0; it < 100; ++it) {
            final int[] arr = ArrayUtils.generateRandomArray(10 + rand.nextInt(1000));
            assertThat(countInversions(arr)).isEqualTo(countInversionsBruteforce(arr));
        }
    }


    @Test
    public void countInversionsNullArrayThrowsException() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> countInversions(null))
                .withMessage("null 'arr' passed");
    }
}
