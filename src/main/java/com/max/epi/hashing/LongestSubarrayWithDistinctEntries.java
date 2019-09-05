package com.max.epi.hashing;


import java.util.HashSet;
import java.util.Set;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * 13.9. FIND THE LONGEST SUBARRAY WITH DISTINCT ENTRIES.
 */
final class LongestSubarrayWithDistinctEntries {

    private LongestSubarrayWithDistinctEntries() {
        throw new IllegalStateException("Can't instantiate utility only class");
    }

    /**
     * time: O(N), where N - arr.length
     * space: O(K), where K - longest subarray length with unique elements, can be up to O(N)
     */
    static int longestSubarrayWithDistinctElements(char[] arr) {

        checkNotNull(arr);

        if (arr.length < 2) {
            return arr.length;
        }

        Set<Character> elementsInSubarray = new HashSet<>();

        int maxSub = 0;

        for (int to = 0, from = 0; to < arr.length; ++to) {

            char ch = arr[to];

            if (elementsInSubarray.contains(ch)) {
                while (from < to) {

                    char elemToRemove = arr[from];

                    boolean wasRemoved = elementsInSubarray.remove(elemToRemove);
                    ++from;

                    assert wasRemoved : "elements wasn't removed: " + elemToRemove;

                    if (elemToRemove == ch) {
                        break;
                    }
                }

                elementsInSubarray.add(ch);
            }
            else {
                elementsInSubarray.add(ch);
                maxSub = Math.max(maxSub, elementsInSubarray.size());
            }

            assert !elementsInSubarray.isEmpty() : "elementsInSubarray is empty";
        }

        assert maxSub >= 1 : "incorrect subarray length detected";

        return maxSub;
    }
}

