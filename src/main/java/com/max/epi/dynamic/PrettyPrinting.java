package com.max.epi.dynamic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * 17.11. The pretty printing problem.
 */
final class PrettyPrinting {

    private static final String DELIMITER = "\\s+";

    private PrettyPrinting() {
        throw new IllegalStateException("Can't instantiate utility only class.");
    }

    /**
     * If messiness calculated as left spaces at the end of a line, we can use greedy approach instead of dynamic.
     * <p>
     * N - words count in 'text'
     * <p>
     * time: O(N)
     * space: O(1)
     */
    static List<List<String>> layoutTextGreedy(String text, int lineLength) {

        String[] words = checkPreconditions(text, lineLength);

        List<List<String>> res = new ArrayList<>();

        List<String> curLine = new ArrayList<>();
        curLine.add(words[0]);

        int curLength = words[0].length();

        for (int i = 1; i < words.length; ++i) {

            final String singleWord = words[i];

            // 'singleWord' fit current line
            if (curLength + 1 + singleWord.length() <= lineLength) {
                curLine.add(singleWord);

                // add current word length and space
                curLength += (singleWord.length() + 1);
            }
            // 'singleWord' moved to next line
            else {
                res.add(curLine);

                curLine = new ArrayList<>();
                curLine.add(singleWord);

                curLength = singleWord.length();
            }

        }

        res.add(curLine);

        return res;
    }

    /**
     * N - words count in 'text'
     * L - line length
     * <p>
     * time: O(N*L)
     * space: O(N),
     * Space can be reduced to O(L) if we just need the an optimal
     * solution messiness indicator without reconstructing the whole solution.
     * <p>
     * Actual space will be O(M), where 'M' is 'text.length',
     * because Strings in java are immutable and we are splitting 'text' into words.
     */
    static List<List<String>> layoutText(String text, int lineLength) {
        String[] words = checkPreconditions(text, lineLength);

        if (words.length == 1 && words[0].length() == 0) {
            return Collections.emptyList();
        }

        int[] opt = new int[words.length + 1];

        for (int last = 1; last < opt.length; ++last) {
            int curLength = 0;
            int minSoFar = Integer.MAX_VALUE;

            for (int i = last; i != 0; --i) {

                assert i > 0 && i <= last;

                if (curLength != 0) {
                    ++curLength;
                }

                assert words[i - 1].length() > 0;

                curLength += words[i - 1].length();

                if (curLength > lineLength) {
                    break;
                }

                final int mess = lineLength - curLength;

                minSoFar = Math.min(minSoFar, (mess * mess) + opt[i - 1]);
            }

            opt[last] = minSoFar;
        }

        return reconstructSolution(opt, words, lineLength);
    }

    private static String[] checkPreconditions(String text, int lineLength) {
        checkNotNull(text, "null 'text' passed");
        checkArgument(lineLength > 0, "lineLength should be positive value: %s", lineLength);

        String str = text.trim();

        String[] words = str.split(DELIMITER);
        checkAllWordsFit(words, lineLength);

        return words;
    }

    private static void checkAllWordsFit(String[] words, int lineLength) {
        for (String singleWord : words) {
            checkArgument(singleWord.length() <= lineLength,
                    "word %s is too long to fit on a single %d line",
                    singleWord, lineLength);
        }
    }

    private static List<List<String>> reconstructSolution(int[] opt, String[] words, int lineLength) {

        final List<List<String>> res = new ArrayList<>();

        int curLength = 0;
        int last = opt.length - 1;

        for (int i = last; i != 0; --i) {

            // if not first word in a line, add space character to count
            if (curLength != 0) {
                ++curLength;
            }

            curLength += words[i - 1].length();

            assert curLength <= lineLength;

            final int mess = lineLength - curLength;

            // line completed
            if (opt[last] - (mess * mess) == opt[i - 1]) {

                res.add(createLine(words, i - 1, last - 1));

                curLength = 0;
                last = i - 1;
            }
        }

        Collections.reverse(res);

        return res;
    }

    private static List<String> createLine(String[] words, int from, int to) {
        return Arrays.stream(words, from, to + 1).collect(Collectors.toList());
    }

}
