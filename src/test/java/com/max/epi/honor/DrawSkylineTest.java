package com.max.epi.honor;

import org.junit.Test;

import java.util.List;

import static com.max.epi.honor.DrawSkyline.skyline;
import static org.assertj.core.api.Assertions.assertThat;

public final class DrawSkylineTest {

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


        assertThat(res).containsExactly(
                new DrawSkyline.Building(0, 1, 1),
                new DrawSkyline.Building(1, 4, 3),
                new DrawSkyline.Building(4, 8, 4),
                new DrawSkyline.Building(8, 10, 3),
                new DrawSkyline.Building(10, 12, 6),
                new DrawSkyline.Building(12, 14, 3),
                new DrawSkyline.Building(14, 16, 2),
                new DrawSkyline.Building(16, 17, 1)
        );
    }

}
