package com.max.epi.greedy;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public final class FindMajorityElementTest {

    @Rule
    public final ExpectedException expectedException = ExpectedException.none();

    @Test
    public void findMajority() {
        assertEquals(Character.valueOf('a'), FindMajorityElement.findMajority("abcaa".toCharArray()).orElse(null));
        assertEquals(Character.valueOf('a'), FindMajorityElement.findMajority("bcaaa".toCharArray()).orElse(null));
        assertEquals(Character.valueOf('a'), FindMajorityElement.findMajority("aaabc".toCharArray()).orElse(null));
        assertEquals(Character.valueOf('a'), FindMajorityElement.findMajority("aababaca".toCharArray()).orElse(null));
    }

    @Test
    public void findMajorityEmptyArray() {
        assertFalse(FindMajorityElement.findMajority(new char[0]).isPresent());
    }

    @Test
    public void findMajoritySingleElement() {
        assertEquals(Character.valueOf('a'), FindMajorityElement.findMajority(new char[]{'a'}).orElse(null));
    }

    @Test
    public void findNoMajority() {
        assertFalse(FindMajorityElement.findMajority("abccaa".toCharArray()).isPresent());
        assertFalse(FindMajorityElement.findMajority("abcdef".toCharArray()).isPresent());
    }

    @Test
    public void findMajorityNullArrayThrowsException() {
        expectedException.expect(NullPointerException.class);
        FindMajorityElement.findMajority(null);
    }

}
