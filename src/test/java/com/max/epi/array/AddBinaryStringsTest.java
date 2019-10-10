package com.max.epi.array;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public final class AddBinaryStringsTest {

    @Test
    public void addBinaryStrings() {
        assertThat(AddBinaryStrings.addBinaryStrings("0110", "1101")).isEqualTo("10011");
    }

}
