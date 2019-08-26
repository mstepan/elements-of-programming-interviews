package com.max.epi.hashing;


import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * 13.8 Find smallest subarray sequentially covering all values.
 * <p>
 * N = paragraph.length
 * K = keywords.length
 * <p>
 * time: O(N)
 * space: O(K)
 */
final class SmallestSubarraySequentiallyCoveringAllValues {

    static Optional<Range> findShortestSubarrayCoveringSequentiallyAllValues(String[] paragraph, String[] keywords) {

        if (paragraph.length < keywords.length) {
            return Optional.empty();
        }

        if (paragraph.length == keywords.length) {
            return Arrays.equals(paragraph, keywords) ? Optional.of(new Range(0, paragraph.length - 1)) : Optional.empty();
        }

        Map<String, Integer> keyToIndex = createWordToIndexMap(keywords);

        IndexAndLength[] offsets = new IndexAndLength[keywords.length];

        int minCoverageLength = Integer.MAX_VALUE;
        int minCoverageFrom = -1;
        int minCoverageTo = -1;

        for (int i = 0; i < paragraph.length; ++i) {
            String word = paragraph[i];

            Integer index = keyToIndex.get(word);

            if (index != null) {
                // first word found
                if (index == 0) {
                    offsets[0] = new IndexAndLength(i, 0);
                }
                else if (offsets[index - 1] != null) {
                    IndexAndLength prev = offsets[index - 1];
                    offsets[index] = new IndexAndLength(i, prev.length + (i - prev.index));
                }

                if (isLastWord(index, offsets)) {

                    // calculate new coverage length
                    int curFrom = offsets[index].index - offsets[index].length;
                    int curTo = offsets[index].index;

                    int curLength = curTo - curFrom;

                    if (curLength < minCoverageLength) {
                        minCoverageLength = curLength;
                        minCoverageFrom = curFrom;
                        minCoverageTo = curTo;
                    }
                }
            }
        }

        return minCoverageFrom < 0 ? Optional.empty() : Optional.of(new Range(minCoverageFrom, minCoverageTo));
    }

    /**
     * Check if last word found and not null.
     */
    private static boolean isLastWord(int index, IndexAndLength[] offsets) {
        return index == offsets.length - 1 && offsets[index] != null;
    }

    private static Map<String, Integer> createWordToIndexMap(String[] keywords) {
        Map<String, Integer> keyToIndex = new HashMap<>();

        for (int i = 0; i < keywords.length; ++i) {
            keyToIndex.put(keywords[i], i);
        }
        return keyToIndex;
    }

    private static final class IndexAndLength {
        final int index;
        final int length;

        IndexAndLength(int index, int length) {
            this.index = index;
            this.length = length;
        }

        @Override
        public String toString() {
            return "index: " + index + ", length: " + length;
        }
    }


    static final class Range {

        final int from;
        final int to;

        Range(int from, int to) {
            this.from = from;
            this.to = to;
        }

        @Override
        public String toString() {
            return String.format("[%s; %s], length: %s", from, to, (to - from) + 1);
        }
    }

}
