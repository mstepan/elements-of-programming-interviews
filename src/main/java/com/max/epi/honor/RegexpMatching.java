package com.max.epi.honor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkState;

/**
 * 25.26. Implement regular expression matching.
 * <p>
 * Regexp characters:
 * 1. alphanumeric, aka 'a', '6', etc.
 * 2. dot, aka '.'
 * 3. star, aka '*'
 * 4. match at the beginning, aka '^'
 * 5. match at the end, aka '$'
 */
final class RegexpMatching {

    private RegexpMatching() {
        throw new IllegalStateException("Can't instantiate utility only class");
    }

    static boolean matches(String str, String regexp) {
        checkArgument(str != null, "'str' can't be null");
        checkArgument(regexp != null, "'regexp' can't be null");
        checkAllRegexpCharactersValid(regexp);

        final Token[] tokens = tokenize(regexp);
        final char[] arr = str.toCharArray();

        // empty string match some regexp values like: a*, a*b* or a*.*
        if (arr.length == 0) {
            return canMatchEmptyString(tokens);
        }

        // match only from beginning, '^'
        if (tokens[0].type == TokenType.BEGIN) {
            return tryMatch(arr, 0, tokens, 1);
        }

        // match only from end, '$'
        if (tokens[tokens.length - 1].type == TokenType.END) {

            char[] reversedArr = reverse(arr);
            Token[] reversedTokens = reverse(tokens, tokens.length - 1);

            return tryMatch(reversedArr, 0, reversedTokens, 0);
        }

        // match in any place
        for (int i = 0; i < arr.length; ++i) {

            if (tryMatch(arr, i, tokens, 0)) {
                return true;
            }
        }

        return false;
    }

    private static boolean canMatchEmptyString(Token[] tokens) {
        if (tokens.length == 0) {
            return true;
        }

        int i = 0;

        if (tokens[0].type == TokenType.BEGIN) {
            ++i;
        }

        int last = tokens.length - 1;

        if (tokens[tokens.length - 1].type == TokenType.END) {
            --last;
        }

        while (i <= last) {
            if (tokens[i].type != TokenType.STAR) {
                return false;
            }
            ++i;
        }

        return true;
    }

    /**
     * recursive matching happens inside this function.
     */
    private static boolean tryMatch(char[] arr, int i, Token[] tokens, int j) {

        if (j == tokens.length || i == arr.length) {
            return j == tokens.length;
        }

        char ch = arr[i];
        Token token = tokens[j];

        if (token.type == TokenType.DOT) {
            return tryMatch(arr, i + 1, tokens, j + 1);
        }

        if (token.type == TokenType.ALPHANUMERIC) {
            if (ch == token.ch) {
                return tryMatch(arr, i + 1, tokens, j + 1);
            }

            return false;
        }

        if (token.type == TokenType.STAR) {

            char starCh = token.ch;

            if (starCh == ch || starCh == '.') {
                return tryMatch(arr, i + 1, tokens, j) || tryMatch(arr, i, tokens, j + 1);
            }

            return tryMatch(arr, i, tokens, j + 1);
        }

        checkState(false, "You should never reach this line.");
        return false;
    }

    private static <T> T[] reverse(T[] arr, int length) {

        T[] copy = Arrays.copyOf(arr, length);

        int left = 0;
        int right = length - 1;

        while (left < right) {
            T temp = copy[left];

            copy[left] = copy[right];
            copy[right] = temp;

            ++left;
            --right;
        }
        return copy;
    }

    private static char[] reverse(char[] arr) {

        char[] copy = Arrays.copyOf(arr, arr.length);

        int left = 0;
        int right = copy.length - 1;

        while (left < right) {
            char temp = copy[left];

            copy[left] = copy[right];
            copy[right] = temp;

            ++left;
            --right;
        }
        return copy;
    }

    private static void checkAllRegexpCharactersValid(String regexp) {

        for (int i = 0; i < regexp.length(); ++i) {
            char ch = regexp.charAt(i);
            checkArgument(isValidRegexpChar(ch), "Invalid regexp character detected '" + ch +
                    "' inside regexp string '" + regexp + "'");
        }
    }

    private static boolean isValidRegexpChar(char ch) {
        return Character.isAlphabetic(ch) || Character.isDigit(ch) || ch == '.' || ch == '*' || ch == '^' || ch == '$';
    }

    private static Token[] tokenize(String regexp) {
        List<Token> tokens = new ArrayList<>();

        final int length = regexp.length();
        int i = 0;

        while (i < length) {
            char ch = regexp.charAt(i);

            if (ch == '^') {
                checkState(i == 0, "Incorrect place for regexp char '^'");
                tokens.add(new Token(TokenType.BEGIN, ch));
                ++i;
            }
            else if (ch == '$') {
                checkState(i == length - 1, "Incorrect place for regexp char '$'");
                tokens.add(new Token(TokenType.END, ch));
                ++i;
            }
            else if (Character.isAlphabetic(ch) || Character.isDigit(ch) || ch == '.') {
                if (i + 1 < length && regexp.charAt(i + 1) == '*') {
                    tokens.add(new Token(TokenType.STAR, ch));
                    i += 2;
                }
                else {
                    if (ch == '.') {
                        tokens.add(new Token(TokenType.DOT, ch));
                    }
                    else {
                        tokens.add(new Token(TokenType.ALPHANUMERIC, ch));
                    }
                    ++i;
                }
            }
            else {
                throw new IllegalArgumentException("'*' appears at the beginning");
            }
        }

        return tokens.toArray(new Token[tokens.size()]);
    }


    private static final class Token {
        final TokenType type;
        final char ch;

        Token(TokenType type, char ch) {
            this.type = type;
            this.ch = ch;
        }
    }

    private enum TokenType {
        ALPHANUMERIC,
        DOT,
        STAR,
        BEGIN,
        END
    }


}
