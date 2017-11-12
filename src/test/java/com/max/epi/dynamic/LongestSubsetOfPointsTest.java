package com.max.epi.dynamic;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.max.epi.dynamic.LongestSubsetOfPoints.XYPoint;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class LongestSubsetOfPointsTest {

    @Test
    public void longestSubsetOfPoints() {

        XYPoint[] points = {
                new XYPoint(3, 6),
                new XYPoint(1, 2),
                new XYPoint(2, 4),
                new XYPoint(4, 3),
                new XYPoint(6, 6),
                new XYPoint(1, 8),
                new XYPoint(3, 2),
        };

        List<XYPoint> longestSubset = LongestSubsetOfPoints.longestSubsetOfPoints(points);

        assertNotNull(longestSubset);

        assertEquals(list(
                new XYPoint(1, 2),
                new XYPoint(2, 4),
                new XYPoint(3, 6),
                new XYPoint(6, 6)), longestSubset);

    }

    @Test
    public void longestSubsetOfPointsSamePoints() {

        XYPoint[] points = {
                new XYPoint(1, 1),
                new XYPoint(1, 1),
                new XYPoint(1, 1),
                new XYPoint(1, 1),
        };

        List<XYPoint> longestSubset = LongestSubsetOfPoints.longestSubsetOfPoints(points);

        assertNotNull(longestSubset);

        assertEquals(list(
                new XYPoint(1, 1),
                new XYPoint(1, 1),
                new XYPoint(1, 1),
                new XYPoint(1, 1)), longestSubset);

    }

    @Test
    public void longestSubsetOfPointsTwoPointsSameX() {

        List<XYPoint> longestSubset = LongestSubsetOfPoints.longestSubsetOfPoints(
                new XYPoint[]{new XYPoint(1, 1), new XYPoint(1, 3)});

        assertNotNull(longestSubset);
        assertEquals(list(new XYPoint(1, 1), new XYPoint(1, 3)), longestSubset);
    }

    @Test
    public void longestSubsetOfPointsTwoPointsSameY() {

        List<XYPoint> longestSubset = LongestSubsetOfPoints.longestSubsetOfPoints(
                new XYPoint[]{new XYPoint(1, 1), new XYPoint(4, 1)});

        assertNotNull(longestSubset);
        assertEquals(list(new XYPoint(1, 1), new XYPoint(4, 1)), longestSubset);
    }

    @Test
    public void longestSubsetOfPointsTwoPointsOnDiagonal() {

        List<XYPoint> longestSubset = LongestSubsetOfPoints.longestSubsetOfPoints(
                new XYPoint[]{new XYPoint(1, 1), new XYPoint(2, 2)});

        assertNotNull(longestSubset);
        assertEquals(list(new XYPoint(1, 1), new XYPoint(2, 2)), longestSubset);
    }

    @Test
    public void longestSubsetOfPointsTwoPointsNotSubset() {

        List<XYPoint> longestSubset = LongestSubsetOfPoints.longestSubsetOfPoints(
                new XYPoint[]{new XYPoint(1, 3), new XYPoint(2, 1)});

        assertNotNull(longestSubset);
        assertEquals(list(new XYPoint(2, 1)), longestSubset);
    }

    @Test
    public void longestSubsetOfPointsEmptyPoints() {

        List<XYPoint> longestSubset = LongestSubsetOfPoints.longestSubsetOfPoints(new XYPoint[]{});

        assertNotNull(longestSubset);
        assertEquals(Collections.emptyList(), longestSubset);
    }

    @Test
    public void longestSubsetOfPointsSinglePoint() {

        List<XYPoint> longestSubset = LongestSubsetOfPoints.longestSubsetOfPoints(new XYPoint[]{new XYPoint(1, 1)});

        assertNotNull(longestSubset);
        assertEquals(Collections.singletonList(new XYPoint(1, 1)), longestSubset);
    }

    @Test(expected = NullPointerException.class)
    public void longestSubsetOfPointsNullPointsThrowException() {
        LongestSubsetOfPoints.longestSubsetOfPoints(null);
    }

    private static final List<XYPoint> list(XYPoint... points) {
        List<XYPoint> list = new ArrayList<>();

        for (XYPoint p : points) {
            list.add(p);
        }

        return list;
    }
}
