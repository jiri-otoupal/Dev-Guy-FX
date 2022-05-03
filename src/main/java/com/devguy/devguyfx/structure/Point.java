package com.devguy.devguyfx.structure;

public class Point {
    public int x;
    public int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean equals(Point point) {
        return point.x == this.x && point.y == this.y;
    }
}
