package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTest {

    @Test
    public void testPointOne() {
        Point p1 = new Point(11,11);
        Point p2 = new Point(20,20);

        Assert.assertEquals(p1.distanceFromPoint(p1, p2), 6.0);
    }

    @Test
    public void testPointTwo() {
        Point p3 = new Point(64,256);
        Point p4 = new Point(128,320);

        Assert.assertEquals(p3.distanceFromPoint(p3, p4), 16.0);
    }
}
