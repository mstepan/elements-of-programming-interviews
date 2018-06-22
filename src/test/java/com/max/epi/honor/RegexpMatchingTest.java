package com.max.epi.honor;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class RegexpMatchingTest {

    @Rule
    public final ExpectedException thrown = ExpectedException.none();

    @Test
    public void matchingComplexRegexp() {
        assertTrue(RegexpMatching.matches("rtyabccczNt7ty", "abc*z.t"));
        assertTrue(RegexpMatching.matches("rtyazNt7ty", "a.*z"));
        assertTrue(RegexpMatching.matches("rtyabccczNt7ty", "a.*z"));

        assertFalse(RegexpMatching.matches("rtyabccczNt7ty", "^abc*z.t"));
        assertFalse(RegexpMatching.matches("abccczNt7ty", "abc*z.t$"));
        assertFalse(RegexpMatching.matches("rtzyactyNt7ty", "a.*z"));
        assertFalse(RegexpMatching.matches("rtyabcccZNt7ty", "a.*z"));
    }

    @Test
    public void matchingAlphanumeric() {
        assertTrue(RegexpMatching.matches("abc", "abc"));
        assertTrue(RegexpMatching.matches("z45abc", "abc"));
        assertTrue(RegexpMatching.matches("abcz45", "abc"));
        assertTrue(RegexpMatching.matches("z45abcdt7", "abc"));

        assertFalse(RegexpMatching.matches("z45abdct7", "abc"));
    }

    @Test
    public void matchingWithDot() {
        assertTrue(RegexpMatching.matches("z45abdct7", "ab.c"));
        assertTrue(RegexpMatching.matches("z45abd76ct7", "ab...c"));
        assertTrue(RegexpMatching.matches("tta9bc8xyz", "a.bc.xy"));

        assertFalse(RegexpMatching.matches("z45abd76ct7", "ab.c"));
        assertFalse(RegexpMatching.matches("z45abd76ct7", "ab..c"));
    }

    @Test
    public void matchingWithStar() {
        assertTrue(RegexpMatching.matches("z45abbbct7", "ab*c"));
        assertTrue(RegexpMatching.matches("z45abbbct7", "ab*c"));
        assertTrue(RegexpMatching.matches("z45abbbbbbct7", "ab*c"));
        assertTrue(RegexpMatching.matches("z45act7", "ab*c"));
        assertTrue(RegexpMatching.matches("z45act7", "ab*"));

        assertFalse(RegexpMatching.matches("z45act7", "a*b"));
    }

    @Test
    public void matchFromBeginning() {
        assertTrue(RegexpMatching.matches("abct7", "^abc"));

        assertFalse(RegexpMatching.matches("zxyabct7", "^abc"));
        assertFalse(RegexpMatching.matches("zxyabc", "^abc"));
    }

    @Test
    public void matchFromEnd() {
        assertTrue(RegexpMatching.matches("t78abc", "abc$"));

        assertFalse(RegexpMatching.matches("zxyabct7", "abc$"));
        assertFalse(RegexpMatching.matches("abczxyab", "abc$"));
    }

    @Test
    public void matchEmptyString() {

        assertTrue(RegexpMatching.matches("", "a*"));
        assertTrue(RegexpMatching.matches("", "a*b*"));
        assertTrue(RegexpMatching.matches("", "a*.*"));
        assertTrue(RegexpMatching.matches("", ".*"));
        assertTrue(RegexpMatching.matches("", ""));
        assertTrue(RegexpMatching.matches("", "^"));
        assertTrue(RegexpMatching.matches("", "$"));
        assertTrue(RegexpMatching.matches("", "^$"));
        assertTrue(RegexpMatching.matches("", "a*b*"));

        assertFalse(RegexpMatching.matches("", "abc"));
        assertFalse(RegexpMatching.matches("", "."));
        assertFalse(RegexpMatching.matches("", "a*c"));
    }

    @Test
    public void incorrectStarPositionThrowsException() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("'*' appears at the beginning");

        RegexpMatching.matches("z45act7", "*c");
    }

    @Test
    public void nullStringThrowsException() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("'str' can't be null");

        RegexpMatching.matches(null, "*c");
    }

    @Test
    public void nullRegexpThrowsException() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("'regexp' can't be null");

        RegexpMatching.matches("abc", null);
    }

    @Test
    public void incorrectRegexpCharacterThrowsException() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Invalid regexp character detected '-' inside regexp string");

        RegexpMatching.matches("z45act7", "a-c");
    }

}
