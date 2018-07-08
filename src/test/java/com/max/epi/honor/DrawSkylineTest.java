package com.max.epi.honor;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.List;

import static com.max.epi.honor.DrawSkyline.skyline;
import static junit.framework.TestCase.assertEquals;

public class DrawSkylineTest {

    @Rule
    public final ExpectedException thrown = ExpectedException.none();

    @Test
    public void skylineNormalFlow() {
        List<DrawSkyline.Building> res = skyline(new DrawSkyline.Building[]{
                new DrawSkyline.Building(0, 3, 1),
                new DrawSkyline.Building(1, 6, 3),
                new DrawSkyline.Building(4, 8, 4),
                new DrawSkyline.Building(5, 9, 2),
                new DrawSkyline.Building(11, 17, 1),
                new DrawSkyline.Building(10, 12, 6),
                new DrawSkyline.Building(7, 14, 3),
                new DrawSkyline.Building(13, 16, 2),
        });

        List<DrawSkyline.Building> expected = new ArrayList<>();
        expected.add(new DrawSkyline.Building(0, 1, 1));
        expected.add(new DrawSkyline.Building(1, 4, 3));
        expected.add(new DrawSkyline.Building(4, 8, 4));
        expected.add(new DrawSkyline.Building(8, 10, 3));
        expected.add(new DrawSkyline.Building(10, 12, 6));
        expected.add(new DrawSkyline.Building(12, 14, 3));
        expected.add(new DrawSkyline.Building(14, 16, 2));
        expected.add(new DrawSkyline.Building(16, 17, 1));

        assertEquals(expected, res);
    }

}
