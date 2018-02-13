package com.max.epi.honor;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class LongestSubstringWithBalancedParensTest {

    @Test
    public void findLongest() {
        assertEquals("(())()", LongestSubstringWithBalancedParens.findLongest("((())()(()("));

        assertEquals("()", LongestSubstringWithBalancedParens.findLongest("((()"));
        assertEquals("()()", LongestSubstringWithBalancedParens.findLongest(")()())"));
        assertEquals("()(())", LongestSubstringWithBalancedParens.findLongest("()(()))))"));
        assertEquals("", LongestSubstringWithBalancedParens.findLongest("))(("));
    }
}
