package com.max.epi.greedy;

import java.util.*;

import static com.google.common.base.Preconditions.checkNotNull;

final class SetPartitionByDifferencing {

    private SetPartitionByDifferencing() {
        throw new IllegalStateException("Utility only class");
    }

    /**
     *
     * 1. provide feedback to ARR policy.
     * 2. provide feedback to all DSS blockers.
     * 3. provide feedback about team problems in a general. (like Matt JWT and Oauth)
     */

    /**
     * Greedy approach to partition a set into 2 subsets using differencing method.
     * <p>
     * See https://www2.eecs.berkeley.edu/Pubs/TechRpts/1983/CSD-83-113.pdf
     */
    static int[][] partition(int[] arr) {
        checkNotNull(arr);

        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
        for (int value : arr) {
            maxHeap.add(value);
        }

        Set<Integer> set1 = new HashSet<>(arr.length);
        Set<Integer> set2 = new HashSet<>(arr.length);

        partitionRec(maxHeap, set1, set2);

        return new int[][]{
                toIntArrSorted(set1),
                toIntArrSorted(set2),
        };
    }

    private static void partitionRec(PriorityQueue<Integer> maxHeap, Set<Integer> set1, Set<Integer> set2) {

        if (maxHeap.size() == 1) {
            set1.add(maxHeap.poll());
            return;
        }

        assert maxHeap.size() > 1 : "One 'maxHeap' detected";
        int a = maxHeap.poll();
        int b = maxHeap.poll();

        assert a >= b : ("a < b: " + a + " < " + b);

        int diff = a - b;

        assert diff >= 0 : ("diff is negative: " + diff);

        maxHeap.add(diff);

        partitionRec(maxHeap, set1, set2);

        assert set1.contains(diff) || set2.contains(diff) : "'diff' not fond in set1 or set2";

        if (set1.contains(diff)) {
            set1.remove(diff);
            set1.add(a);
            set2.add(b);
        }
        else {
            set2.remove(diff);
            set2.add(a);
            set1.add(b);
        }
    }

    private static int[] toIntArrSorted(Set<Integer> set) {
        int[] arr = new int[set.size()];

        int index = 0;
        for (int value : set) {
            assert index < arr.length;
            arr[index] = value;
            ++index;
        }

        Arrays.sort(arr);
        return arr;
    }

}
