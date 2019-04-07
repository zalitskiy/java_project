package ru.stqa.pft.sandbox;

public class Point {
    public double x;
    public double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double distanceFromPoint(Point a) {
        return Math.sqrt((a.x - this.x) * 2 + (a.y - this.y) * 2);
    }
}

