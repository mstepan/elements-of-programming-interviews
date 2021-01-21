package com.max.epi.primitive.rectangle.rectangle;


public record Rectangle(Point leftDown, Point rightUp) {

    public static Rectangle of(int leftX, int bottomY, int rightX, int topY) {
        return new Rectangle(new Point(leftX, bottomY), new Point(rightX, topY));
    }

}
