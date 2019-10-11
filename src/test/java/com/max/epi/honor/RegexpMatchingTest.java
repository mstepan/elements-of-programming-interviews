package com.max.epi.honor;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public final class RegexpMatchingTest {

    @Test
    public void matchingComplexRegexp() {

        assertThat(RegexpMatching.matches("rtyabccczNt7ty", "abc*z.t")).isTrue();
        assertThat(RegexpMatching.matches("rtyazNt7ty", "a.*z")).isTrue();
        assertThat(RegexpMatching.matches("rtyabccczNt7ty", "a.*z")).isTrue();

        assertThat(RegexpMatching.matches("rtyabccczNt7ty", "^abc*z.t")).isFalse();
        assertThat(RegexpMatching.matches("abccczNt7ty", "abc*z.t$")).isFalse();
        assertThat(RegexpMatching.matches("rtzyactyNt7ty", "a.*z")).isFalse();
        assertThat(RegexpMatching.matches("rtyabcccZNt7ty", "a.*z")).isFalse();
    }

    @Test
    public void matchingAlphanumeric() {
        assertThat(RegexpMatching.matches("abc", "abc")).isTrue();
        assertThat(RegexpMatching.matches("z45abc", "abc")).isTrue();
        assertThat(RegexpMatching.matches("abcz45", "abc")).isTrue();
        assertThat(RegexpMatching.matches("z45abcdt7", "abc")).isTrue();

        assertThat(RegexpMatching.matches("z45abdct7", "abc")).isFalse();
    }

    @Test
    public void matchingWithDot() {
        assertThat(RegexpMatching.matches("z45abdct7", "ab.c")).isTrue();
        assertThat(RegexpMatching.matches("z45abd76ct7", "ab...c")).isTrue();
        assertThat(RegexpMatching.matches("tta9bc8xyz", "a.bc.xy")).isTrue();

        assertThat(RegexpMatching.matches("z45abd76ct7", "ab.c")).isFalse();
        assertThat(RegexpMatching.matches("z45abd76ct7", "ab..c")).isFalse();
    }

    @Test
    public void matchingWithStar() {
        assertThat(RegexpMatching.matches("z45abbbct7", "ab*c")).isTrue();
        assertThat(RegexpMatching.matches("z45abbbct7", "ab*c")).isTrue();
        assertThat(RegexpMatching.matches("z45abbbbbbct7", "ab*c")).isTrue();
        assertThat(RegexpMatching.matches("z45act7", "ab*c")).isTrue();
        assertThat(RegexpMatching.matches("z45act7", "ab*")).isTrue();

        assertThat(RegexpMatching.matches("z45act7", "a*b")).isFalse();
    }

    @Test
    public void matchFromBeginning() {
        assertThat(RegexpMatching.matches("abct7", "^abc")).isTrue();

        assertThat(RegexpMatching.matches("zxyabct7", "^abc")).isFalse();
        assertThat(RegexpMatching.matches("zxyabc", "^abc")).isFalse();
    }

    @Test
    public void matchFromEnd() {
        assertThat(RegexpMatching.matches("t78abc", "abc$")).isTrue();

        assertThat(RegexpMatching.matches("zxyabct7", "abc$")).isFalse();
        assertThat(RegexpMatching.matches("abczxyab", "abc$")).isFalse();
    }

    @Test
    public void matchEmptyString() {
        assertThat(RegexpMatching.matches("", "a*")).isTrue();
        assertThat(RegexpMatching.matches("", "a*b*")).isTrue();
        assertThat(RegexpMatching.matches("", "a*.*")).isTrue();
        assertThat(RegexpMatching.matches("", ".*")).isTrue();
        assertThat(RegexpMatching.matches("", "")).isTrue();
        assertThat(RegexpMatching.matches("", "^")).isTrue();
        assertThat(RegexpMatching.matches("", "$")).isTrue();
        assertThat(RegexpMatching.matches("", "^$")).isTrue();
        assertThat(RegexpMatching.matches("", "a*b*")).isTrue();

        assertThat(RegexpMatching.matches("", "abc")).isFalse();
        assertThat(RegexpMatching.matches("", ".")).isFalse();
        assertThat(RegexpMatching.matches("", "a*c")).isFalse();
    }

    @Test
    public void incorrectStarPositionThrowsException() {
        assertThatIllegalArgumentException().
                isThrownBy(() -> RegexpMatching.matches("z45act7", "*c")).
                withMessage("'*' appears at the beginning");
    }

    @SuppressWarnings("all")
    @Test
    public void nullStringThrowsException() {
        assertThatIllegalArgumentException().
                isThrownBy(() -> RegexpMatching.matches(null, "*c")).
                withMessage("'str' can't be null");
    }

    @Test
    public void nullRegexpThrowsException() {
        assertThatIllegalArgumentException().
                isThrownBy(() -> RegexpMatching.matches("abc", null)).
                withMessage("'regexp' can't be null");
    }

    @Test
    public void incorrectRegexpCharacterThrowsException() {
        assertThatIllegalArgumentException().
                isThrownBy(() -> RegexpMatching.matches("z45act7", "a-c")).
                withMessage("Invalid regexp character detected '-' inside regexp string 'a-c'");
    }

}
