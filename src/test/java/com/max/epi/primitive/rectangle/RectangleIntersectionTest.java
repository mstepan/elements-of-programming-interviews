package com.max.epi.primitive.rectangle;

import com.max.epi.primitive.rectangle.rectangle.Rectangle;
import com.max.epi.primitive.rectangle.rectangle.RectangleIntersection;
import org.junit.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class RectangleIntersectionTest {

    @Test
    public void r1IntersectsWithR2InOnePoint() {
        Rectangle r1 = Rectangle.of(6, 1, 9, 9);
        Rectangle r2 = Rectangle.of(9, 9, 13, 12);

        // single point as intersection
        assertThat(RectangleIntersection.findIntersection(r1, r2)).
                hasValue(Rectangle.of(9, 9, 9, 9));
    }

    @Test
    public void r1HasBorderWithR2ToTheLeft() {
        Rectangle r1 = Rectangle.of(5, 3, 10, 6);
        Rectangle r2 = Rectangle.of(3, 4, 5, 6);

        // single line border
        assertThat(RectangleIntersection.findIntersection(r1, r2)).
                hasValue(Rectangle.of(5, 4, 5, 6));
    }

    @Test
    public void r2IntersectsR1FromTopAndBottom() {
        Rectangle r1 = Rectangle.of(5, 3, 10, 6);
        Rectangle r2 = Rectangle.of(6, 1, 9, 9);

        assertThat(RectangleIntersection.findIntersection(r1, r2)).
                hasValue(Rectangle.of(6, 3, 9, 6));
    }


    @Test
    public void r2IntersectsR1FromAbove() {
        Rectangle r1 = Rectangle.of(5, 3, 10, 6);
        Rectangle r2 = Rectangle.of(6, 5, 8, 10);

        assertThat(RectangleIntersection.findIntersection(r1, r2)).
                hasValue(Rectangle.of(6, 5, 8, 6));
    }

    @Test
    public void r2FullyInsideR1() {
        Rectangle r1 = Rectangle.of(5, 3, 10, 6);
        Rectangle r2 = Rectangle.of(7, 3, 10, 5);

        assertThat(RectangleIntersection.findIntersection(r1, r2)).
                hasValue(Rectangle.of(7, 3, 10, 5));
    }

    @Test
    public void noIntersectionR1AboveR2() {
        Rectangle r1 = Rectangle.of(5, 3, 10, 6);
        Rectangle r2 = Rectangle.of(7, 1, 9, 2);

        assertThat(RectangleIntersection.findIntersection(r1, r2)).isEmpty();
    }

    @Test
    public void noIntersectionR1BelowR2() {
        Rectangle r1 = Rectangle.of(5, 3, 10, 6);
        Rectangle r2 = Rectangle.of(6, 7, 10, 8);

        assertThat(RectangleIntersection.findIntersection(r1, r2)).isEmpty();
    }

    @Test
    public void noIntersectionR1LeftToR2() {
        Rectangle r1 = Rectangle.of(5, 3, 10, 6);
        Rectangle r2 = Rectangle.of(12, 3, 14, 5);

        assertThat(RectangleIntersection.findIntersection(r1, r2)).isEmpty();
    }

    @Test
    public void noIntersectionR1RightToR2() {
        Rectangle r1 = Rectangle.of(5, 3, 10, 6);
        Rectangle r2 = Rectangle.of(2, 1, 4, 7);

        assertThat(RectangleIntersection.findIntersection(r1, r2)).isEmpty();
    }

}
