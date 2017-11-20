package com.max.epi.greedy;

import java.util.Optional;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * 18.7. Find majority element from array.
 * <p>
 * time: O(N)
 * space: O(1)
 */
final class FindMajorityElement {

    private FindMajorityElement() {
        throw new IllegalStateException("Can't instantiate utility only class");
    }

    static Optional<Character> findMajority(char[] arr) {
        checkNotNull(arr);

        if (arr.length == 0) {
            return Optional.empty();
        }

        if (arr.length == 1) {
            return Optional.of(arr[0]);
        }

        char candidate = findPossibleMajority(arr);

        int freq = calculateFreq(arr, candidate);

        if (freq > arr.length / 2) {
            return Optional.of(candidate);
        }

        return Optional.empty();
    }

    private static char findPossibleMajority(char[] arr){
        char candidate = arr[0];
        int cnt = 1;

        for (int i = 1; i < arr.length; ++i) {
            if (candidate == arr[i]) {
                ++cnt;
            }
            else {
                --cnt;

                assert cnt >= 0;

                if (cnt == 0) {
                    candidate = arr[i];
                    cnt = 1;
                }
            }
        }

        return candidate;
    }

    private static int calculateFreq(char[] arr, char baseCh) {
        assert arr != null;

        int freq = 0;

        for (char ch : arr) {
            if (ch == baseCh) {
                ++freq;
            }
        }

        return freq;
    }

}
