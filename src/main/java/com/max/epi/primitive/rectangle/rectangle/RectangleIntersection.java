package com.max.epi.primitive.rectangle.rectangle;

import java.util.Optional;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * 5.11. Rectangle intersection.
 */
public class RectangleIntersection {

    /**
     * Find intersection of two rectangles.
     * <p>
     * time: O(1)
     * space: O(1)
     *
     * @param r1 - first rectangle
     * @param r2 - second rectangle
     */
    public static Optional<Rectangle> findIntersection(Rectangle r1, Rectangle r2) {

        checkArgument(r1 != null, "Rectangle 'r1' is NULL");
        checkArgument(r2 != null, "Rectangle 'r2' is NULL");

        if (hasIntersection(r1, r2)) {
            int leftX = Math.max(r1.leftDown().x(), r2.leftDown().x());
            int bottomY = Math.max(r1.leftDown().y(), r2.leftDown().y());

            int rightX = Math.min(r1.rightUp().x(), r2.rightUp().x());
            int topY = Math.min(r1.rightUp().y(), r2.rightUp().y());

            return Optional.of(new Rectangle(new Point(leftX, bottomY), new Point(rightX, topY)));
        }

        return Optional.empty();
    }

    private static boolean hasIntersection(Rectangle r1, Rectangle r2) {
        // case-1: 'r2' lies below 'r1', no intersection
        int bottomY = r1.leftDown().y();
        int otherTopY = r2.rightUp().y();
        if (bottomY > otherTopY) {
            return false;
        }

        // case-2: 'r2' lies above 'r1', no intersection
        int topY = r1.rightUp().y();
        int otherBottomY = r2.leftDown().y();
        if (topY < otherBottomY) {
            return false;
        }

        // case-3: 'r2' lies left to 'r1', no intersection
        int leftX = r1.leftDown().x();
        int otherRightX = r2.rightUp().x();
        if (leftX > otherRightX) {
            return false;
        }

        // case-4: 'r2' lies right to 'r1', no intersection
        int rightX = r1.rightUp().x();
        int otherLeftX = r2.leftDown().x();
        if (rightX < otherLeftX) {
            return false;
        }

        return true;
    }

}
