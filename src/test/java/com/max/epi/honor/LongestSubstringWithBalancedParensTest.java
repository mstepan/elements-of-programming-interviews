package com.max.epi.honor;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public final class LongestSubstringWithBalancedParensTest {

    @Test
    public void findLongest() {
        assertThat(LongestSubstringWithBalancedParens.findLongest("((())()(()(")).isEqualTo("(())()");
        assertThat(LongestSubstringWithBalancedParens.findLongest("((()")).isEqualTo("()");
        assertThat(LongestSubstringWithBalancedParens.findLongest(")()())")).isEqualTo("()()");
        assertThat(LongestSubstringWithBalancedParens.findLongest("()(()))))")).isEqualTo("()(())");
        assertThat(LongestSubstringWithBalancedParens.findLongest("))((")).isEqualTo("");
    }
}
