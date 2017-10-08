package com.max.epi.array;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


final class AddBinaryStringsTest {

    @Test
    void addBinaryStrings() {
        assertEquals("10011", AddBinaryStrings.addBinaryStrings("0110", "1101"));
    }

}
