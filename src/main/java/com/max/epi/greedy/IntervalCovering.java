package com.max.epi.greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * 18.3. The interval covering problem.
 * <p>
 * Find minimum set of points covering all intervals.
 */
final class IntervalCovering {

    private IntervalCovering() {
        throw new IllegalStateException("Can't instantiate utility only class");
    }

    /**
     * time: O(N*lgN)
     * space: O(N)
     */
    static List<Integer> coveringPoints(Interval[] intervals) {
        checkNotNull(intervals, "null 'intervals' passed");

        if (intervals.length == 0) {
            return Collections.emptyList();
        }

        if (intervals.length == 1) {
            return Collections.singletonList(intervals[0].end);
        }

        Arrays.sort(intervals, Interval.FROM_ASC_CMP);

        List<Integer> points = new ArrayList<>();

        Interval prev = intervals[0];

        for (int i = 1; i < intervals.length; ++i) {
            Interval cur = intervals[i];

            Optional<Interval> intersectionInterval = prev.intersection(cur);

            if (intersectionInterval.isPresent()) {
                prev = intersectionInterval.get();
            }
            else {
                points.add(prev.end);
                prev = cur;
            }
        }

        points.add(prev.end);

        return points;
    }


    static class Interval {

        private static final Comparator<Interval> FROM_ASC_CMP = Comparator.comparing(interval -> interval.start);

        final int start;
        final int end;

        Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }

        private Optional<Interval> intersection(Interval other) {

            Interval first = this;
            Interval second = other;

            if (second.start < first.start) {
                first = other;
                second = this;
            }

            if (first.end < second.end) {
                return Optional.empty();
            }

            return Optional.of(new Interval(second.start, Math.min(first.end, second.end)));
        }

        @Override
        public String toString() {
            return "[" + start + ", " + end + "]";
        }
    }
}
