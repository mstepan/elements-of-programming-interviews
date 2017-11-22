package com.max.epi.greedy;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

public final class IntervalCoveringTest {

    @Rule
    public final ExpectedException expectedEx = ExpectedException.none();

    @Test
    public void coveringPoints() {
        IntervalCovering.Interval[] intervals = new IntervalCovering.Interval[]{
                new IntervalCovering.Interval(3, 4),
                new IntervalCovering.Interval(7, 9),
                new IntervalCovering.Interval(2, 6),
                new IntervalCovering.Interval(0, 15),
                new IntervalCovering.Interval(1, 2)
        };

        List<Integer> expectedPoints = Arrays.asList(2, 4, 9);

        assertEquals(expectedPoints, IntervalCovering.coveringPoints(intervals));
    }

    @Test
    public void coveringPointsNotIntersectedIntervals() {
        IntervalCovering.Interval[] intervals = new IntervalCovering.Interval[]{
                new IntervalCovering.Interval(5, 8),
                new IntervalCovering.Interval(1, 3),
                new IntervalCovering.Interval(10, 15),
        };

        List<Integer> expectedPoints = Arrays.asList(3, 8, 15);

        assertEquals(expectedPoints, IntervalCovering.coveringPoints(intervals));
    }

    @Test
    public void coveringPointsEmptyIntervals() {
        assertEquals(Collections.emptyList(), IntervalCovering.coveringPoints(new IntervalCovering.Interval[0]));
    }

    @Test
    public void coveringPointsSingleInterval() {
        IntervalCovering.Interval[] intervals = new IntervalCovering.Interval[]{
                new IntervalCovering.Interval(2, 6)
        };

        assertEquals(Collections.singletonList(6), IntervalCovering.coveringPoints(intervals));
    }

    @Test
    public void coveringPointsNullArrayThrowsException() {
        expectedEx.expect(NullPointerException.class);
        expectedEx.expectMessage("ull 'intervals' passed");
        IntervalCovering.coveringPoints(null);
    }

}
