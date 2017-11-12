package com.max.epi.dynamic;

import java.util.*;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * 17.12. Variant.
 * <p>
 * Find longest subset of points.
 * Each point must be right and above from the previous one.
 */
final class LongestSubsetOfPoints {

    private static final XYPoint MIN_POINT = new XYPoint(Integer.MIN_VALUE, Integer.MIN_VALUE);

    private LongestSubsetOfPoints() {
        throw new IllegalStateException("Can't instantiate LongestSubsetOfPoints class");
    }

    /**
     * N - points.length
     * <p>
     * time: O(N*lgN) + O(N^2) = O(N^2)
     * space: O(N)
     */
    static List<XYPoint> longestSubsetOfPoints(XYPoint[] points) {

        checkNotNull(points);

        if (points.length == 0) {
            return Collections.emptyList();
        }

        if (points.length < 2) {
            return Collections.singletonList(points[0]);
        }

        Arrays.sort(points, XYPoint.ASC_CMP);

        int[] sol = new int[points.length];
        sol[0] = 1;

        for (int i = 1; i < sol.length; ++i) {

            sol[i] = 1;
            XYPoint cur = points[i];

            for (int j = i - 1; j >= 0; --j) {
                XYPoint other = points[j];

                if (other.x <= cur.x && other.y <= cur.y) {
                    sol[i] = Math.max(sol[i], sol[j] + 1);
                }
            }
        }

        return reconstructSolution(sol, points);
    }

    private static List<XYPoint> reconstructSolution(int[] sol, XYPoint[] points) {

        int maxIndex = 0;

        for (int i = 1; i < sol.length; ++i) {
            if (sol[i] > sol[maxIndex]) {
                maxIndex = i;
            }
        }

        int subsetLength = sol[maxIndex];
        int index = maxIndex;

        List<XYPoint> res = new ArrayList<>();

        while (subsetLength != 0) {

            XYPoint cur = points[index];
            res.add(cur);

            for (int j = index - 1; j >= 0; --j) {

                XYPoint other = points[j];

                if (other.x <= cur.x && other.y <= cur.y && sol[j] == sol[index] - 1) {
                    index = j;
                    break;
                }
            }

            --subsetLength;
        }

        Collections.reverse(res);

        return res;
    }

    /**
     * Backtracking solution to construct longest subset of points.
     * <p>
     * N - points.length
     * <p>
     * time: O(N*lgN + 2^N)= O(2^N)
     * space: O(N)
     */
    static List<XYPoint> longestSubsetOfPointsBacktracking(XYPoint[] points) {

        Arrays.sort(points, XYPoint.ASC_CMP);

        List<XYPoint> res = new ArrayList<>();

        longestSubsetOfPointsRec(points, 0, new ArrayDeque<>(), res);

        return res;
    }

    private static void longestSubsetOfPointsRec(XYPoint[] points, int index, Deque<XYPoint> partialSol,
                                                 List<XYPoint> res) {

        if (index == points.length) {
            if (res.size() < partialSol.size()) {
                res.clear();
                res.addAll(partialSol);
            }
            return;
        }

        XYPoint cur = points[index];
        XYPoint last = partialSol.isEmpty() ? MIN_POINT : partialSol.peekLast();

        // use 'cur'
        if (last.x <= cur.x && last.y <= cur.y) {
            partialSol.addLast(cur);
            longestSubsetOfPointsRec(points, index + 1, partialSol, res);
            partialSol.pollLast();
        }

        // skip 'cur'
        longestSubsetOfPointsRec(points, index + 1, partialSol, res);
    }


    static class XYPoint {

        /**
         * Compare by 'x' coordinate in ASC order first and
         * then by 'y' coordinate in ASC order.
         */
        private static final Comparator<XYPoint> ASC_CMP = (p1, p2) -> {

            int xCmp = Integer.compare(p1.x, p2.x);

            if (xCmp != 0) {
                return xCmp;
            }

            return Integer.compare(p1.y, p2.y);
        };

        final int x;
        final int y;

        public XYPoint(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null || obj.getClass() != XYPoint.class) {
                return false;
            }

            XYPoint other = (XYPoint) obj;

            return Objects.equals(x, other.x) &&
                    Objects.equals(y, other.y);
        }

        @Override
        public String toString() {
            return "(" + x + ", " + y + ")";
        }
    }
}
