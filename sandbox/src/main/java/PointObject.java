public class PointObject {
    public static void main(String[] args) {
        Point p1 = new Point(1, 1);
        Point p2 = new Point(10, 10);

        System.out.println("Вычисление расстояния между двумя точками (p1 и p2), с помощью метода distanceFromPoint, из класса Point: расстояние равно " + p1.distanceFromPoint(p1, p2));

        Point p3 = new Point(2, 2);
        Point p4 = new Point(18, 18);

        System.out.println("Вычисление расстояния между двумя точками (p3 и p4), с помощью метода distanceFromPointObject, из класса PointObject: расстояние равно " +distanceFromPointObject(p3, p4));

    }

    public static double distanceFromPointObject(Point a, Point b) {
        return Math.sqrt((b.x - a.x) * 2 + (b.y - a.y) * 2);
    }
}
