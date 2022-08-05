package com.max;

import java.util.Arrays;

/**
 * https://www.geeksforgeeks.org/sprinklr-interview-experience-on-campus/
 * <p>
 * A medical team wants to send a minimum number of workers for vaccinating people in the city, each worker can vaccinate
 * people in an area whose radius is D, and can start from anywhere, an array is given which tells the location of people on
 * X-axis, there is no one on Y-axis, help the medical team to know the minimum number of people needed to vaccinate the
 * whole town.
 * Example : D = 2 ,
 * arr = {1,2,4,7,8,9,10,12,14,16,18,20}
 * Output : 4
 * Explanation :(1,2,4) , (7,8,9,10), (12,14,16) , (18,20) workers will be divided like this.
 */
final class Main {

    public static void main(String[] args) {

        final int d = 2;
        System.out.println(calculateMinNumberOfWorkers(new int[]{1, 2, 4, 7, 8, 9, 10, 12, 14, 16, 18, 20}, d));

        System.out.println("java version: " + System.getProperty("java.version"));
    }

    /**
     * time: O(N*lgN) if array is not sorted, otherwise O(N)
     * space: O(1)
     */
    static int calculateMinNumberOfWorkers(int[] patientsLocation, int diameter) {
        if (patientsLocation == null) {
            throw new IllegalArgumentException("NULL 'arr' passed");
        }
        if (diameter <= 0) {
            throw new IllegalArgumentException("Can't calculate min. workers coverage, diameter is negative, d = " + diameter);
        }

        Arrays.sort(patientsLocation);

        int from = 0;

        int workersCnt = 0;

        while (from < patientsLocation.length) {
            ++workersCnt;
            from = findNextUncoveredSegment(patientsLocation, from, diameter) + 1;
        }

        return workersCnt;
    }

    private static int findNextUncoveredSegment(int[] arr, int from, int d) {
        assert arr != null;
        assert from >= 0;
        assert d > 0;

        if (from >= arr.length) {
            return from;
        }

        final int leftMaxVal = arr[from] + d;

        int index = from;

        while (index < arr.length && leftMaxVal >= arr[index]) {
            ++index;
        }

        final int rightMaxVal = arr[index - 1] + d;

        while (index < arr.length && rightMaxVal >= arr[index]) {
            ++index;
        }
        return index - 1;
    }

}
