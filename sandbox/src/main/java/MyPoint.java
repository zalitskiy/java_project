public class MyPoint {
    public double x;
    public double y;

    public MyPoint(double x, double y) {
        this.x = x;
        this.y = y;
    }
    public static double distanceFromPoint(MyPoint a, MyPoint b) {
        return Math.sqrt((b.x - a.x) * 2 + (b.y - a.y) * 2);
    }
}

