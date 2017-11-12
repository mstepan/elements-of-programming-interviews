package com.max.epi.dynamic;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static com.max.epi.dynamic.LongestSubsetOfPoints.XYPoint;
import static org.junit.Assert.assertEquals;

public class LongestSubsetOfPointsExhaustiveTest {

    @Test
    public void longestSubsetOfPointsRandomData() {

        final Random rand = new Random();

        for (int it = 0; it < 100; ++it) {

            XYPoint[] points = new XYPoint[10 + rand.nextInt(100)];

            for (int i = 0; i < points.length; ++i) {
                points[i] = new XYPoint(rand.nextInt(), rand.nextInt());
            }

            List<XYPoint> longestSubsetBacktracking =
                    LongestSubsetOfPoints.longestSubsetOfPointsBacktracking(Arrays.copyOf(points, points.length));

            List<XYPoint> longestSubsetDynamic =
                    LongestSubsetOfPoints.longestSubsetOfPoints(Arrays.copyOf(points, points.length));

            assertEquals(longestSubsetBacktracking.size(), longestSubsetDynamic.size());
        }
    }
}
