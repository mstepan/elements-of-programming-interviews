package com.max.epi.greedy;

import java.util.ArrayDeque;
import java.util.Deque;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * 18.8. Compute the largest rectangle under the skyline.
 */
final class LargestRectangleUnderSkyline {

    private LargestRectangleUnderSkyline() {
        throw new IllegalStateException("Can't instantiate utility only class");
    }

    /**
     * time: O(N)
     * space: O(N)
     */
    static int largestRectangle(int[] arr) {
        checkNotNull(arr, "null 'arr' passed");
        checkAllElementsPositive(arr);

        if (arr.length == 0) {
            return 0;
        }

        if (arr.length == 1) {
            return arr[0];
        }

        int maxArea = 0;

        Deque<ValueAndIndex> stack = new ArrayDeque<>();

        for (int i = 0; i < arr.length + 1; ++i) {
            final int val = (i == arr.length) ? 0 : arr[i];

            int index = i;

            while (!stack.isEmpty() && stack.peekFirst().value > val) {
                ValueAndIndex last = stack.pop();

                assert last.value > val;
                assert last.index < i;

                int lastArea = (i - last.index) * last.value;
                assert lastArea > 0;

                maxArea = Math.max(maxArea, lastArea);

                index = last.index;
            }

            if (val != 0) {
                stack.push(new ValueAndIndex(val, index));
            }
        }

        assert maxArea > 0;
        assert stack.isEmpty() : "not empty 'stack' detected at the end of algorithm";

        return maxArea;
    }

    private static void checkAllElementsPositive(int[] arr) {
        for (int val : arr) {
            checkArgument(val > 0, "incorrect value %s, should be positive greater than 0", val);
        }
    }

    private static class ValueAndIndex {
        final int value;
        final int index;

        ValueAndIndex(int value, int index) {
            this.value = value;
            this.index = index;
        }
    }

    /**
     * Divide and conquer solution. Find largest start left side, right side and
     * crossing current middle index.
     * <p>
     * time: O(N*lgN)
     * space: O(lgN)
     */
    static int largestRectangleDivideAndConquer(int[] arr) {
        checkAllElementsPositive(arr);
        return largestRectangleRec(arr, 0, arr.length - 1);
    }

    private static int largestRectangleRec(int[] arr, int from, int to) {

        final int elementsCount = to - from + 1;

        if (elementsCount == 0) {
            return 0;
        }
        else if (elementsCount == 1) {
            return arr[from];
        }
        else if (elementsCount == 2) {
            return 2 * Math.min(arr[from], arr[to]);
        }

        final int mid = from + (to - from) / 2;

        final int leftLargest = largestRectangleRec(arr, from, mid - 1);
        final int rightLargest = largestRectangleRec(arr, mid + 1, to);
        final int largestMiddle = findLargestCrossIndex(arr, mid);

        return max(leftLargest, rightLargest, largestMiddle);
    }

    private static int findLargestCrossIndex(int[] arr, int index) {

        int largest = arr[index];

        int left = index - 1;
        int right = index + 1;

        int minSoFar = arr[index];

        for (int elems = arr.length - 1; elems > 0; --elems) {

            if (right == arr.length || (left >= 0 && arr[left] > arr[right])) {
                minSoFar = Math.min(minSoFar, arr[left]);
                --left;
            }
            else {
                minSoFar = Math.min(minSoFar, arr[right]);
                ++right;
            }

            largest = Math.max(largest, (right - left - 1) * minSoFar);
        }

        return largest;
    }

    private static int max(int first, int second, int third) {
        return Math.max(Math.max(first, second), third);
    }
}
