package com.max.epi.hashing;

import org.junit.Test;

import static com.max.epi.hashing.LongestSubarrayWithDistinctEntries.longestSubarrayWithDistinctElements;
import static groovy.util.GroovyTestCase.assertEquals;

public class LongestSubarrayWithDistinctEntriesTest {

    @Test
    public void normalCase() {
        assertEquals(5, longestSubarrayWithDistinctElements("fsfetwenwe".toCharArray()));

        assertEquals(1, longestSubarrayWithDistinctElements("aaaaa".toCharArray()));

        assertEquals(2, longestSubarrayWithDistinctElements("aaaaabbbbb".toCharArray()));

        assertEquals(3, longestSubarrayWithDistinctElements("abcabcabc".toCharArray()));
    }

    @Test
    public void emptyArrayShouldReturnZero() {
        assertEquals(0, longestSubarrayWithDistinctElements("".toCharArray()));
    }

    @Test
    public void oneElementArrayShouldReturnOne() {
        assertEquals(1, longestSubarrayWithDistinctElements("a".toCharArray()));
    }

    @Test(expected = NullPointerException.class)
    public void nullArrayShouldThrowError() {
        longestSubarrayWithDistinctElements(null);
    }
}
