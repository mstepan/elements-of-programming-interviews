package com.max.epi.greedy;

/**
 * 18.8. COMPUTE THE LARGEST RECTANGLE UNDER THE SKYLINE.
 */
final class LargestRectangleUnderSkyline {

    private LargestRectangleUnderSkyline() {
        throw new IllegalStateException("Can't instantiate utility only class");
    }

    /**
     * Divide and conquer solution. Find largest from left side, right side and
     * crossing current middle index.
     * <p>
     * time: O(N*lgN)
     * space: O(lgN)
     */
    static int largestRectangleDivideAndConquer(int[] arr) {
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
