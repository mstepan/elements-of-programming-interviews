package com.max.epi.honor;

import java.util.HashMap;
import java.util.Map;

import com.max.util.Pair;

import static org.valid4j.Assertive.*;

/**
 * Find longest substring with balanced parens using divide-and-conquer technique.
 */
final class LongestSubstringWithBalancedParens {

    private LongestSubstringWithBalancedParens() {
        throw new IllegalStateException("Can't instantiate utility only class");
    }

    /**
     * time: O(N*lgN)
     * space: O(N)
     */
    static String findLongest(String str) {
        ensure(str != null);
        return findLongestRec(str, 0, str.length() - 1);
    }

    private static String findLongestRec(String str, int from, int to) {

        final int rangeSize = to - from + 1;

        ensure(rangeSize >= 0);

        if (rangeSize < 2) {
            return "";
        }

        if (rangeSize == 2) {
            char first = str.charAt(from);
            char second = str.charAt(to);

            if (first == '(' && second == ')') {
                return "()";
            }
            return "";
        }

        final int mid = from + (to - from) / 2;

        final String leftSide = findLongestRec(str, from, mid);
        ensure(leftSide != null);

        final String rightSide = findLongestRec(str, mid + 1, to);
        ensure(rightSide != null);

        final String crossSide = findLongestCross(str, mid);
        ensure(crossSide != null);

        return maxLengthString(leftSide, rightSide, crossSide);
    }

    /**
     * time: O(N)
     * space: O(N), can be O(1)
     */
    private static String findLongestCross(String str, int index) {

        Map<Integer, Integer> negValueToIndexMap = new HashMap<>();
        int rightBalance = 0;
        for (int right = index + 1; right < str.length(); ++right) {
            char ch = str.charAt(right);

            if (ch == '(') {
                ++rightBalance;
            }
            else if (ch == ')') {
                --rightBalance;
                if (rightBalance < 0) {
                    negValueToIndexMap.put(rightBalance, right);
                }
            }
        }

        if (negValueToIndexMap.isEmpty()) {
            return "";
        }

        Pair<Integer, Integer> range = new Pair<>(index, index);

        int maxLeftBalance = 0;
        int leftBalance = 0;
        for (int left = index; left >= 0; --left) {

            char ch = str.charAt(left);

            if (ch == '(') {
                ++leftBalance;
                maxLeftBalance = Math.max(maxLeftBalance, leftBalance);
            }
            else if (ch == ')') {
                --leftBalance;
            }

            if (leftBalance > 0) {
                Integer rightIndex = negValueToIndexMap.get(-maxLeftBalance);

                if (rightIndex != null) {
                    range = Pair.of(left, rightIndex);
                }
            }
        }

        return (range.getFirst().equals(range.getSecond())) ?
                "" : str.substring(range.getFirst(), range.getSecond() + 1);
    }

    private static String maxLengthString(String str, String... others) {

        require(str != null);
        require(others != null && others.length == 2);

        String maxStr = str;

        for (String val : others) {
            if (val.length() > maxStr.length()) {
                maxStr = val;
            }
        }
        return maxStr;
    }
}
