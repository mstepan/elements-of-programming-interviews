package com.max.epi.dynamic;

import com.max.util.ArrayUtils;
import com.max.util.StringUtils;
import org.junit.Test;

import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;



public final class InterleavingOfStringsTest {

    @Test
    public void isInterleavingNormalCase() {
        assertTrue(InterleavingOfStrings.isInterleaving("hewlorllod", "hello", "world"));
        assertTrue(InterleavingOfStrings.isInterleaving("hewlolo", "hello", "wo"));
    }

    @Test
    public void isInterleavingNormalCaseWithRandomization() {

        Random rand = new Random();

        for (int it = 0; it < 100; ++it) {
            String s1 = StringUtils.generateLowCaseAsciiString(10 + rand.nextInt(1000));
            String s2 = StringUtils.generateLowCaseAsciiString(10 + rand.nextInt(1000));
            String base = StringUtils.combineInRandomOrder(s1, s2);

            assertThat(InterleavingOfStrings.isInterleaving(base, s1, s2)).isTrue();
        }
    }

    @Test
    public void isInterleavingAllStringsEmpty() {
        assertTrue(InterleavingOfStrings.isInterleaving("", "", ""));
    }

    @Test
    public void isInterleavingFirstStringEmpty() {
        assertTrue(InterleavingOfStrings.isInterleaving("abc", "", "abc"));
    }

    @Test
    public void isInterleavingSecondStringEmpty() {
        assertTrue(InterleavingOfStrings.isInterleaving("abc", "abc", ""));
    }

    @Test
    public void notInterleavingStrings() {
        assertFalse(InterleavingOfStrings.isInterleaving("hewlrloold", "hello", "world"));
    }


    @Test
    public void notInterleavingIfLengthDifferent() {
        assertFalse(InterleavingOfStrings.isInterleaving("hello", "helabc", "loxyz"));
    }

    @Test
    public void notInterleavingRandomStrings() {

        Random rand = new Random();

        for (int it = 0; it < 100; ++it) {
            String s1 = StringUtils.generateLowCaseAlphaString(10 + rand.nextInt(1000));
            char[] arr2 = StringUtils.generateLowCaseAlphaString(10 + rand.nextInt(1000)).toCharArray();

            int i, j;

            do {
                i = rand.nextInt(arr2.length);
                j = rand.nextInt(arr2.length);
            }
            while (i == j);

            arr2[i] = '1';
            arr2[j] = '2';

            String base = StringUtils.combineInRandomOrder(s1, new String(arr2));

            ArrayUtils.swap(arr2, i, j);
            String s2 = new String(arr2);

            assertFalse(InterleavingOfStrings.isInterleaving(base, s1, s2));
        }
    }

}
