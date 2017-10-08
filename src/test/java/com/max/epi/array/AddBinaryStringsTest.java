package com.max.epi.array;


import org.junit.Test;

import static org.junit.Assert.assertEquals;


public final class AddBinaryStringsTest {

    @Test
    public void addBinaryStrings() {
        assertEquals("10011", AddBinaryStrings.addBinaryStrings("0110", "1101"));
    }

}
