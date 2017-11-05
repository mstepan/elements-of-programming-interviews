package com.max.epi.dynamic;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PrettyPrintingTest {

    @Test
    public void layoutTextLotsAdditionalSpaces() {
        assertFormattedText(
                " hello   world   this is a long  line  of  text  ",
                new String[]{
                        "hello",
                        "world",
                        "this is a",
                        "long line",
                        "of text"
                },
                9);
    }


    @Test
    public void layoutTextSmallText() {
        assertFormattedText(
                "hello world this is a long line of text",
                new String[]{
                        "hello",
                        "world",
                        "this is a",
                        "long line",
                        "of text"
                },
                9);
    }

    @Test
    public void layoutTextBiggerText() {
        assertFormattedText(
                "I have inserted a large number of new examples from the papers " +
                        "for the Mathematical Tripos during the last twenty years, which should " +
                        "be useful to Cambridge students.",
                new String[]{
                        "I have inserted a large number of",
                        "new examples from the papers for",
                        "the Mathematical Tripos during the",
                        "last twenty years, which should",
                        "be useful to Cambridge students."
                },
                36);
    }

    @Test(expected = IllegalArgumentException.class)
    public void layoutTextNotFitWordThrowsException() {
        assertFormattedText(
                "hello all",
                new String[]{
                        "hello",
                        "all"
                },
                4);
    }

    @Test
    public void layoutEmptyText() {
        assertFormattedText(
                "",
                new String[]{},
                4);
    }

    @Test
    public void layoutTextGreedy() {
        assertFormattedText(
                "hello world this is a long line of text",
                new String[]{
                        "hello",
                        "world",
                        "this is a",
                        "long line",
                        "of text"
                },
                9);
    }


    private static void assertFormattedText(String text, String[] expected, int lineLength) {

        List<List<String>> actual = PrettyPrinting.layoutText(text, lineLength);

        assertEquals("Lines count is incorrect", expected.length, actual.size());

        for (int row = 0; row < expected.length; ++row) {

            final String[] line = expected[row].split(" ");

            for (int col = 0; col < line.length; ++col) {
                assertEquals("line: " + row + " position: " + col + " is incorrect",
                        line[col],
                        actual.get(row).get(col));

                assertTrue(calculateLineLength(actual.get(row)) <= lineLength);

            }
        }
    }

    private static int calculateLineLength(List<String> lineWords) {

        // count spaces
        int length = lineWords.size() - 1;

        // count words length
        for (String word : lineWords) {
            length += word.length();
        }

        return length;
    }

}
