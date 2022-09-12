package com.max.epi.array;

import java.util.Arrays;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 6.15. Generate nonuniform random numbers.
 */
public class GenerateNonUniformRandomNumbers {

    public static void main(String[] args) {

        String[] arr = {"READ", "CREATE", "UPDATE", "DELETE"};
        double[] probabilities = {0.5, 0.33, 0.07, 0.1};

        DistributionGenerator<String> gen = new DistributionGenerator<>(arr, probabilities);

        for (int i = 0; i < 100; ++i) {
            System.out.println(gen.randomValue());
        }

        System.out.printf("Main done... java version: %s%n", System.getProperty("java.version"));
    }

    private static final class DistributionGenerator<T> {

        private final T[] arr;
        private final double[] sumProb;

        private final Random rand = ThreadLocalRandom.current();

        public DistributionGenerator(T[] arr, double[] probabilities) {
            this.arr = Objects.requireNonNull(arr, "null 'arr' parameter passed");
            this.sumProb = prefixSum(Objects.requireNonNull(probabilities, "null 'probabilities' parameter passed"));
        }

        private double[] prefixSum(double[] probabilities) {
            double sum = 0.0;
            double[] res = new double[probabilities.length];

            for (int i = 0; i < res.length; ++i) {
                sum += probabilities[i];
                res[i] = sum;
            }

            return res;
        }

        public T randomValue() {
            double randVal = rand.nextDouble();

            int randIndex = Arrays.binarySearch(sumProb, randVal);

            int index = (randIndex >= 0) ? randIndex + 1 : Math.abs(randIndex) - 1;
            return arr[index];

        }
    }
}




