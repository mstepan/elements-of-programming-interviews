package com.max.epi.array;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Random;


public class OnlineSampling {

    private static final Logger LOG = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    private OnlineSampling() throws Exception {

        List<Integer> data = new ArrayList<>();
        for (int i = 0; i < 100; ++i) {
            data.add(i);
        }

        int sampleSize = 5;
        int[] sample = onlineSampling(data.iterator(), sampleSize);

        System.out.println(Arrays.toString(sample));

        System.out.printf("Main done: java-%s %n", System.getProperty("java.version"));
    }

    /**
     * time: O(N)
     * space: O(K)
     */
    public static int[] onlineSampling(Iterator<Integer> it, int k) {

        int[] sample = new int[k];

        for (int i = 0; i < k && it.hasNext(); ++i) {
            sample[i] = it.next();
        }

        Random rand = new Random();

        // elements handled so far
        int elemsCount = k + 1;

        while (it.hasNext()) {

            int value = it.next();

            int randIndex = rand.nextInt(elemsCount);

            /*
            Add element to any 'k' bucket with probability = k/elemsCount
             */
            if (randIndex < k) {
                sample[randIndex] = value;
            }

            ++elemsCount;
        }

        return sample;
    }

    public static void main(String[] args) {
        try {
            new OnlineSampling();
        }
        catch (Exception ex) {
            LOG.error(ex.getMessage(), ex);
        }
    }
}
