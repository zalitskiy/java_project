public class PointObject {
    public static void main(String[] args) {
        Point p1 = new Point(1, 1);
        Point p2 = new Point(10, 10);

        System.out.println(p1.distanceFromPoint(p1, p2));

        Point p3 = new Point(2, 2);
        Point p4 = new Point(18, 18);

        System.out.println(distanceFromNew(p3, p4));

    }

    public static double distanceFromNew(Point a, Point b) {
        return Math.sqrt((b.x - a.x) * 2 + (b.y - a.y) * 2);
    }
}
