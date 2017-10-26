package com.max.epi.dynamic;

import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

final class KnapsackProblemReducedSpace {

    private KnapsackProblemReducedSpace() {
        throw new IllegalStateException("Utility only class");
    }

    static class Item {
        final int weight;
        final int value;

        Item(int weight, int value) {
            this.weight = weight;
            this.value = value;
        }

        @Override
        public String toString() {
            return "weight = " + weight + ", value = " + value;
        }
    }

    /**
     * N - items.length
     * K - knapsackWeight
     * C - number of weights between 0 and K, that can be achieved.
     * <p>
     * time: O(N*K*lgC)
     * space: O(C)
     */
    static int findMaxKnapsackValue(Item[] items, int knapsackWeight) {

        NavigableMap<Integer, Integer> prev = new TreeMap<>();

        for (Item item : items) {

            NavigableMap<Integer, Integer> cur = new TreeMap<>();

            for (int w = 1; w <= knapsackWeight; ++w) {
                int curMaxValue = getFloorValue(prev, w);

                if (item.weight <= w) {
                    curMaxValue = Math.max(curMaxValue, item.value + getFloorValue(prev, w - item.weight));
                }

                if (curMaxValue != 0) {
                    cur.put(w, curMaxValue);
                }
            }

            prev = cur;
        }


        return getFloorValue(prev, knapsackWeight);
    }

    private static int getFloorValue(NavigableMap<Integer, Integer> map, int weight) {
        Map.Entry<Integer, Integer> entry = map.floorEntry(weight);
        return entry == null ? 0 : entry.getValue();
    }

    static int findMaxKnapsackValueBruteforce(Item[] items, int knapsackWeight) {
        return findKnapsackRec(items, 0, knapsackWeight);
    }

    private static int findKnapsackRec(Item[] items, int index, int leftWeight) {

        if (index == items.length) {
            return 0;
        }

        Item cur = items[index];

        if (cur.weight <= leftWeight) {
            return Math.max(cur.value + findKnapsackRec(items, index + 1, leftWeight - cur.weight),
                    findKnapsackRec(items, index + 1, leftWeight));
        }

        return findKnapsackRec(items, index + 1, leftWeight);
    }


}
