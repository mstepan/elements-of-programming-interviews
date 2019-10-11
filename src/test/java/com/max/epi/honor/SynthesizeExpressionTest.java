package com.max.epi.honor;

import org.junit.Test;

import static com.max.epi.honor.SynthesizeExpression.canBeSynthesized;
import static org.assertj.core.api.Assertions.assertThat;

public final class SynthesizeExpressionTest {

    @Test
    public void canBeSynthesizedNormalFlowSimpleCase() {
        assertThat(canBeSynthesized(new int[]{1, 2, 3}, 7)).isTrue();
        assertThat(canBeSynthesized(new int[]{1, 2, 3}, 123)).isTrue();
        assertThat(canBeSynthesized(new int[]{1, 2, 3}, 6)).isTrue();
        assertThat(canBeSynthesized(new int[]{1, 2, 3}, 15)).isTrue();
        assertThat(canBeSynthesized(new int[]{1, 2, 3}, 5)).isTrue();
        assertThat(canBeSynthesized(new int[]{1, 2, 3}, 24)).isTrue();
        assertThat(canBeSynthesized(new int[]{1, 2, 3}, 36)).isTrue();
    }

    @Test
    public void canBeSynthesizedComplexCase() {
        assertThat(canBeSynthesized(new int[]{1, 2, 3, 2, 5, 3, 7, 8, 5, 9}, 995)).isTrue();
    }

    @Test
    public void canBeSynthesizedSingleDigitArray() {
        assertThat(canBeSynthesized(new int[]{3}, 3)).isTrue();

        assertThat(canBeSynthesized(new int[]{3}, 2)).isFalse();
        assertThat(canBeSynthesized(new int[]{3}, 1)).isFalse();
    }
}
