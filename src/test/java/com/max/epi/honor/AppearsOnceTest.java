package com.max.epi.honor;

import com.max.util.ArrayUtils;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import static com.max.epi.honor.AppearsOnce.findUnique;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public final class AppearsOnceTest {

    @Test
    public void findUniqueNormalFlow() {
        assertThat(findUnique(new int[]{2, 4, 2, 5, 2, 5, 5})).isEqualTo(4);
        assertThat(findUnique(new int[]{1, 1, 1, 2, 2, 2, 3, 4, 4, 4})).isEqualTo(3);
        assertThat(findUnique(new int[]{-2, -4, -2, -5, -2, -5, -5})).isEqualTo(-4);
        assertThat(findUnique(new int[]{2, -4, 2, 5, 2, 5, 5})).isEqualTo(-4);
        assertThat(findUnique(new int[]{2, 0, 2, 5, 2, 5, 5})).isEqualTo(0);
        assertThat(findUnique(new int[]{0, 0, 2, 5, 0, 5, 5})).isEqualTo(2);
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

            assertThat(findUnique(arr)).isEqualTo(uniqueValue);
        }
    }

    @SuppressWarnings("all")
    @Test
    public void findUniqueNullArrayThrowsException() {
        assertThatIllegalArgumentException().
                isThrownBy(() -> findUnique(null)).
                withMessage("null array passed");
    }

    @Test
    public void findUniqueEmptyArrayThrowsException() {
        assertThatIllegalArgumentException().
                isThrownBy(() -> findUnique(new int[]{})).
                withMessage("incorrect array length detected");
    }

    @Test
    public void findUniqueIncorrectArrayLength1ThrowsException() {
        assertThatIllegalArgumentException().
                isThrownBy(() -> findUnique(new int[]{3, 3, 3})).
                withMessage("incorrect array length detected");
    }

    @Test
    public void findUniqueIncorrectArrayLength2ThrowsException() {
        assertThatIllegalArgumentException().
                isThrownBy(() -> findUnique(new int[]{3, 3, 3, 5, 5})).
                withMessage("incorrect array length detected");
    }

    @Test
    public void findUniqueIncorrectArrayLength3ThrowsException() {
        assertThatIllegalArgumentException().
                isThrownBy(() -> findUnique(new int[]{3, 3, 3, 5, 5, 5})).
                withMessage("incorrect array length detected");
    }

    @Test
    public void findUniqueIncorrectArrayLength4ThrowsException() {
        assertThatIllegalArgumentException().
                isThrownBy(() -> findUnique(new int[]{177})).
                withMessage("incorrect array length detected");
    }

    @Test
    public void callConstructorThrowsException() throws ReflectiveOperationException {
        Constructor<AppearsOnce> constructor = AppearsOnce.class.getDeclaredConstructor();
        constructor.setAccessible(true);

        assertThatExceptionOfType(InvocationTargetException.class).
                isThrownBy(constructor::newInstance);
    }

}
