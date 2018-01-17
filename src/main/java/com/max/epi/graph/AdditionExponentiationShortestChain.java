package com.max.epi.graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * 19.7. Variant.
 * <p>
 * Calculate shortest chain of exponentiation or product.
 */
final class AdditionExponentiationShortestChain {

    private AdditionExponentiationShortestChain() {
        throw new IllegalStateException("Can't instantiate utility only class");
    }

    static List<Integer> calculateShortestChain(int n) {
        checkArgument(n >= 0);

        if (n == 0) {
            return Collections.singletonList(0);
        }
        if (n == 1) {
            return Collections.singletonList(1);
        }

        int maxChainsCount = 1;

        Queue<List<Integer>> allChains = new ArrayDeque<>();
        allChains.add(Collections.singletonList(1));

        while (!allChains.isEmpty()) {

            List<Integer> singleChain = allChains.poll();
            assertIncreased(singleChain);

            int lastValue = singleChain.get(singleChain.size() - 1);

            Set<Integer> newAddedValues = new HashSet<>();

            // increase chain by squaring
            for (int value : singleChain) {

                int newValue = value * 2;

                if (newValue > n) {
                    break;
                }

                if (newValue == n) {
                    System.out.println("maxChainsCount: " + maxChainsCount);
                    return copyAndAdd(singleChain, newValue);
                }
                else if (newValue > lastValue) {
                    newAddedValues.add(newValue);
                    allChains.add(copyAndAdd(singleChain, newValue));
                }
            }

            maxChainsCount = Math.max(maxChainsCount, allChains.size());

            // increase chain by multiplying
            for (int i = 0; i < singleChain.size() - 1; ++i) {
                for (int j = i + 1; j < singleChain.size(); ++j) {

                    int x = singleChain.get(i);
                    int y = singleChain.get(j);

                    int newValue = x + y;

                    if (newValue > n) {
                        break;
                    }

                    if (newValue == n) {
                        System.out.println("maxChainsCount: " + maxChainsCount);
                        return copyAndAdd(singleChain, newValue);
                    }

                    // `!newAddedValues.contains(newValue)` decrease number of duplicates by 3 times
                    if (newValue > lastValue && !newAddedValues.contains(newValue)) {
                        allChains.add(copyAndAdd(singleChain, newValue));
                    }
                }
            }

            maxChainsCount = Math.max(maxChainsCount, allChains.size());
        }

        return Collections.emptyList();
    }

    private static List<Integer> copyAndAdd(List<Integer> singleChain, int newValue) {
        List<Integer> singleChainCopy = new ArrayList<>(singleChain);
        singleChainCopy.add(newValue);
        return singleChainCopy;
    }

    private static void assertIncreased(List<Integer> list) {
        if (list.size() > 1) {
            for (int i = 1; i < list.size(); ++i) {
                checkArgument(list.get(i - 1) < list.get(i));
            }
        }
    }
}
