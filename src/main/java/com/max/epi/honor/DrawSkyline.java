package com.max.epi.honor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkState;

/**
 * 25.29. Draw the skyline.
 */
final class DrawSkyline {

    private DrawSkyline() {
        throw new IllegalStateException("Can't instantiate utility only class");
    }

    /**
     * N = building.length
     * W = (max_start - min_end + 1)
     * <p>
     * time: O(N*W)
     * space: O(N+W)
     */
    static List<Building> skyline(Building[] buildings) {
        checkArgument(buildings != null, "null 'buildings' passed");

        int startIndex = findMinStart(buildings);
        int endIndex = findMaxEnd(buildings);

        int[] heightsArr = calculateHeightPerPoints(buildings, startIndex, endIndex);

        return combinePoints(startIndex, heightsArr);
    }

    private static int[] calculateHeightPerPoints(Building[] buildings, int startIndex, int endIndex) {

        checkState(startIndex <= endIndex);

        int[] heightsArr = new int[endIndex - startIndex + 1];

        for (int x = startIndex; x <= endIndex; ++x) {

            int maxCur = 0;

            for (Building singleBuilding : buildings) {
                if (singleBuilding.overlaps(x) && singleBuilding.height > maxCur) {
                    maxCur = singleBuilding.height;
                }
            }

            heightsArr[x - startIndex] = maxCur;
        }

        return heightsArr;
    }

    private static List<Building> combinePoints(int startIndex, int[] heightsArr) {
        checkState(startIndex >= 0);
        checkState(heightsArr != null);

        List<Building> res = new ArrayList<>();

        int prevHeight = heightsArr[0];
        int prevStart = startIndex;

        for (int i = 1; i < heightsArr.length; ++i) {
            if (heightsArr[i] != prevHeight) {

                int prevEnd = (heightsArr[i] > prevHeight) ? (i + startIndex) : (i - 1) + startIndex;

                checkState(prevStart < prevEnd);

                res.add(new Building(prevStart, prevEnd, prevHeight));

                prevHeight = heightsArr[i];
                prevStart = prevEnd;
            }
        }

        res.add(new Building(prevStart, heightsArr.length - 1 + startIndex, prevHeight));

        return res;
    }

    private static int findMinStart(Building[] buildings) {
        return Arrays.stream(buildings).mapToInt(building -> building.start).min().orElse(-1);
    }

    private static int findMaxEnd(Building[] buildings) {
        return Arrays.stream(buildings).mapToInt(building -> building.end).max().orElse(-1);
    }

    static final class Building {
        final int start;
        final int end;
        final int height;

        Building(int start, int end, int height) {
            this.start = start;
            this.end = end;
            this.height = height;
        }

        boolean overlaps(int x) {
            return start <= x && end >= x;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }

            Building building = (Building) obj;

            return Objects.equals(start, building.start) &&
                    Objects.equals(end, building.end) &&
                    Objects.equals(height, building.height);
        }

        @Override
        public int hashCode() {
            return Objects.hash(start, end, height);
        }

        @Override
        public String toString() {
            return "[" + start + "; " + end + "], h = " + height;
        }
    }

}
