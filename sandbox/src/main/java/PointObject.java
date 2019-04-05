public class PointObject {
    public static void main(String[] args) {
        MyPoint p1 = new MyPoint(1, 1);
        MyPoint p2 = new MyPoint(10, 10);

        System.out.println(p1.distanceFromPoint(p1, p2));

        MyPoint p3 = new MyPoint(2, 2);
        MyPoint p4 = new MyPoint(18, 18);

        System.out.println(distanceFromNew(p3, p4));

    }

    public static double distanceFromNew(MyPoint a, MyPoint b) {
        return Math.sqrt((b.x - a.x) * 2 + (b.y - a.y) * 2);
    }
}
