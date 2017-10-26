package com.max.epi.dynamic;

import org.junit.Test;

import java.util.Random;

import static com.max.epi.dynamic.KnapsackProblemReducedSpace.Item;
import static org.junit.Assert.assertEquals;

public class KnapsackProblemReducedSpaceTest {

    @Test
    public void findMaxKnapsackValue() {
        Item[] itemsArr1 = new Item[]{
                new Item(3, 4),
                new Item(5, 5),
                new Item(5, 5),
                new Item(10, 7)
        };

        assertEquals(14, KnapsackProblemReducedSpace.findMaxKnapsackValue(itemsArr1, 15));
        assertEquals(14, KnapsackProblemReducedSpace.findMaxKnapsackValueBruteforce(itemsArr1, 15));

        Item[] itemsArr2 = new Item[]{
                new Item(2, 1),
                new Item(3, 2),
                new Item(3, 5),
                new Item(4, 9),
                new Item(6, 4)
        };
        assertEquals(16, KnapsackProblemReducedSpace.findMaxKnapsackValue(itemsArr2, 10));
        assertEquals(16, KnapsackProblemReducedSpace.findMaxKnapsackValueBruteforce(itemsArr2, 10));
    }

    @Test
    public void findMaxKnapsackRandomItems() {

        final Random rand = new Random();

        for (int it = 0; it < 10; ++it) {

            Item[] items = new Item[10 + rand.nextInt(10)];

            for (int i = 0; i < items.length; ++i) {
                items[i] = new Item(rand.nextInt(10), rand.nextInt(10));
            }

            int knapsackWeight = (10 + rand.nextInt(100)) * 5;

            assertEquals("KnapsackProblemReducedSpace dynamic solution returns values different from bruteforce",
                    KnapsackProblemReducedSpace.findMaxKnapsackValueBruteforce(items, knapsackWeight),
                    KnapsackProblemReducedSpace.findMaxKnapsackValue(items, knapsackWeight));
        }

    }
}
