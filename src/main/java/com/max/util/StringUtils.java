package com.max.util;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Stateless utility class.
 *
 * @author Maksym Stepanenko.
 */
public final class StringUtils {

    private static final Random RAND = ThreadLocalRandom.current();
    private static final char[] DNA_CHARS = "ACTG".toCharArray();

    private static final char[] LOWER_CASE_ALPHA_NUM_ASCII = new char[('z' - 'a' + 1) + ('9' - '0' + 1)];

    static {
        int index = 0;
        for (int ch = 'a'; ch <= 'z'; ++ch, ++index) {
            LOWER_CASE_ALPHA_NUM_ASCII[index] = (char) ch;
        }
        for (int ch = '0'; ch <= '9'; ++ch, ++index) {
            LOWER_CASE_ALPHA_NUM_ASCII[index] = (char) ch;
        }
    }

    private StringUtils() {
        throw new IllegalStateException("Can't instantiate utility only class");
    }


    /**
     * Generate random alpha-numeric lower case ASCII string.
     */
    public static String generateLowCaseAsciiString(int length) {
        checkArgument(length >= 0, "Negative 'length' passed: %s", length);

        char[] arr = new char[length];

        for (int i = 0; i < arr.length; ++i) {
            arr[i] = LOWER_CASE_ALPHA_NUM_ASCII[RAND.nextInt(LOWER_CASE_ALPHA_NUM_ASCII.length)];
        }

        return new String(arr);
    }

    /**
     * Combine two strings in random order.
     */
    public static String combineInRandomOrder(String s1, String s2) {
        checkNotNull(s1);
        checkNotNull(s2);

        int i = 0;
        int j = 0;

        char[] combined = new char[s1.length() + s2.length()];

        for (int k = 0; k < combined.length; ++k) {

            // 's1' fully exhausted
            if (i == s1.length()) {
                combined[k] = s2.charAt(j);
                ++j;
            }

            // 's2' fully exhausted
            else if (j == s2.length()) {
                combined[k] = s1.charAt(i);
                ++i;
            }
            // read randomly character from s1 or s2
            else {
                if (RAND.nextBoolean()) {
                    combined[k] = s1.charAt(i);
                    ++i;
                }
                else {
                    combined[k] = s2.charAt(j);
                    ++j;
                }
            }
        }

        return new String(combined);
    }

    /**
     * Check is any character sequence, such as 'String' or 'StringBuilder' is palindrome.
     * <p>
     * time: O(N), N - buf.length()
     * space: O(1)
     */
    public static boolean isPalindrome(CharSequence buf) {

        checkNotNull(buf, "null 'buf' passed");
        if (buf.length() < 2) {
            return true;
        }

        int left = 0;
        int right = buf.length() - 1;

        while (left < right) {

            if (buf.charAt(left) != buf.charAt(right)) {
                return false;
            }

            ++left;
            --right;
        }

        return true;
    }


    /**
     * time: O(N)
     * space: O(N)
     */
    public static String randomShuffle(String str) {
        checkNotNull(str);

        if (str.length() < 2) {
            return str;
        }

        char[] arr = str.toCharArray();

        ArrayUtils.randomShuffle(arr);

        return new String(arr);
    }


    /**
     * Generate random DNA string with specified length.
     */
    public static String generateDNAString(int length) {

        StringBuilder buf = new StringBuilder(length);

        for (int i = 0; i < length; ++i) {
            buf.append(DNA_CHARS[RAND.nextInt(DNA_CHARS.length)]);
        }

        return buf.toString();
    }

}
