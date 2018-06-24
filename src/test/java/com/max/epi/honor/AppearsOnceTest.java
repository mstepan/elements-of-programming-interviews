package com.max.epi.honor;

import com.max.util.ArrayUtils;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import static com.max.epi.honor.AppearsOnce.findUnique;
import static org.junit.Assert.assertEquals;

public class AppearsOnceTest {

    @Rule
    public final ExpectedException thrown = ExpectedException.none();

    @Test
    public void findUniqueNormalFlow() {
        assertEquals(4, findUnique(new int[]{2, 4, 2, 5, 2, 5, 5}));
        assertEquals(3, findUnique(new int[]{1, 1, 1, 2, 2, 2, 3, 4, 4, 4}));
        assertEquals(-4, findUnique(new int[]{-2, -4, -2, -5, -2, -5, -5}));
        assertEquals(-4, findUnique(new int[]{2, -4, 2, 5, 2, 5, 5}));
        assertEquals(0, findUnique(new int[]{2, 0, 2, 5, 2, 5, 5}));
        assertEquals(2, findUnique(new int[]{0, 0, 2, 5, 0, 5, 5}));
    }

    @Test
    public void findUniqueNormalFlowRandomArray() {
        Random rand = ThreadLocalRandom.current();

        for (int it = 0; it < 100; ++it) {
            int numberOfTriples = 10 + rand.nextInt(1000);

            int[] arr = new int[3 * numberOfTriples + 1];
            int index = 0;

            Set<Integer> triplesSet = new HashSet<>();

            for (int i = 0; i < numberOfTriples; ++i) {
                int randValue = rand.nextInt();

                while (triplesSet.contains(randValue)) {
                    randValue = rand.nextInt();
                }

                triplesSet.add(randValue);

                arr[index] = randValue;
                arr[index + 1] = randValue;
                arr[index + 2] = randValue;

                index += 3;
            }

            int uniqueValue = rand.nextInt();

            while (triplesSet.contains(uniqueValue)) {
                uniqueValue = rand.nextInt();
            }

            arr[index] = uniqueValue;

            ArrayUtils.randomShuffle(arr);

            assertEquals(uniqueValue, findUnique(arr));
        }
    }

    @Test
    public void findUniqueNullArrayThrowsException() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("null array passed");

        findUnique(null);
    }

    @Test
    public void findUniqueEmptyArrayThrowsException() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("incorrect array length detected");

        findUnique(new int[]{});
    }

    @Test
    public void findUniqueIncorrectArrayLength1ThrowsException() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("incorrect array length detected");

        findUnique(new int[]{3, 3, 3});
    }

    @Test
    public void findUniqueIncorrectArrayLength2ThrowsException() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("incorrect array length detected");

        findUnique(new int[]{3, 3, 3, 5, 5});
    }

    @Test
    public void findUniqueIncorrectArrayLength3ThrowsException() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("incorrect array length detected");

        findUnique(new int[]{3, 3, 3, 5, 5, 5});
    }

    @Test
    public void findUniqueIncorrectArrayLength4ThrowsException() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("incorrect array length detected");

        findUnique(new int[]{177});
    }

    @Test
    public void callConstructorThrowsException() throws ReflectiveOperationException {
        thrown.expect(InvocationTargetException.class);

        Constructor<AppearsOnce> constructor = AppearsOnce.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        constructor.newInstance();
    }

}
