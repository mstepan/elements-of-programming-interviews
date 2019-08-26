package com.max.epi.hashing;

import org.junit.Test;

import java.util.Optional;

import static com.max.epi.hashing.SmallestSubarraySequentiallyCoveringAllValues.Range;
import static com.max.epi.hashing.SmallestSubarraySequentiallyCoveringAllValues.findShortestSubarrayCoveringSequentiallyAllValues;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class SmallestSubarraySequentiallyCoveringAllValuesTest {

    @Test
    public void findCoverageNormalCase() {

        String[] keywords = createArrayOfWords("union save");

        String[] paragraph = createArrayOfWords("the best choice for any person is to save the union. All that you can " +
                                                        "do is to save all the people. Otherwise they will save the union. " +
                                                        "And we will save you.");

        Optional<Range> res = findShortestSubarrayCoveringSequentiallyAllValues(paragraph, keywords);

        assertTrue("Not found", res.isPresent());

        assertEquals("union and we will save", combineRange(paragraph, res.get()));
    }

    @Test
    public void notFoundCoverage() {

        String[] keywords = createArrayOfWords("save best");

        String[] paragraph = createArrayOfWords("the best choice for any person is to save the union. All that you can " +
                                                        "do is to save all the people. Otherwise they will save the union. " +
                                                        "And we will save you.");

        Optional<Range> res = findShortestSubarrayCoveringSequentiallyAllValues(paragraph, keywords);

        assertFalse("Covergae found, but should not", res.isPresent());

    }

    private static String[] createArrayOfWords(String paragraphStr) {
        return normalize(tokenize(paragraphStr));
    }

    private static String[] tokenize(String text) {
        assert text != null;
        return text.split("\\s+");
    }

    private static String[] normalize(String[] arr) {

        assert arr != null;

        String[] res = new String[arr.length];

        for (int i = 0; i < res.length; ++i) {
            res[i] = normalizeWord(arr[i]);
        }

        return res;
    }

    private static String normalizeWord(String word) {
        assert word != null;
        return word.toLowerCase().replace('.', ' ').trim();
    }

    private static String combineRange(String[] paragraph, Range range) {

        StringBuilder buf = new StringBuilder();

        for (int i = range.from; i <= range.to; ++i) {
            buf.append(" ").append(paragraph[i]);
        }

        return buf.toString().trim();
    }
}
