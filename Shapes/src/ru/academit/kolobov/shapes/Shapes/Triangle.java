package ru.academit.kolobov.shapes.Shapes;

import java.util.Comparator;

public class Triangle implements Shape, Comparable<Shape> {
    private double x1;
    private double x2;
    private double x3;
    private double y1;
    private double y2;
    private double y3;

    public Triangle(double x1, double x2, double x3, double y1, double y2, double y3) {
        this.x1 = x1;
        this.x2 = x2;
        this.x3 = x3;
        this.y1 = y1;
        this.y2 = y2;
        this.y3 = y3;
    }

    private static double getLengthSide(double x1, double y1, double x2, double y2) {//надо сделать статик т.к. не работает с полями.
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }

    @Override
    public double getWidth() {
        return Math.max(x1, Math.max(x2, x3)) - Math.min(x1, Math.min(x2, x3));
    }

    @Override
    public double getHeight() {
        return Math.max(y1, Math.max(y2, y3)) - Math.min(y1, Math.min(y2, y3));
    }

    @Override
    public double getArea() {
        double halfPerimetr = getPerimeter() / 2;
        return Math.sqrt(halfPerimetr * (halfPerimetr - getLengthSide(x1, y1, x2, y2)) * (halfPerimetr - getLengthSide(x2, y2, x3, y3)) * (halfPerimetr - getLengthSide(x3, y3, x1, y1)));
    }

    @Override
    public double getPerimeter() {
        return getLengthSide(x1, y1, x2, y2) + getLengthSide(x2, y2, x3, y3) + getLengthSide(x3, y3, x1, y1);
    }

    @Override
    public int compareTo(Shape f) {
        return (int) (this.getArea() - f.getArea());
    }

    public static Comparator<Shape> perimetrComparator = new Comparator<Shape>() {

        @Override
        public int compare(Shape f1, Shape f2) {
            return (int) (f1.getPerimeter() - f2.getPerimeter());
        }
    };

    @Override
    public String toString() {
        return "[Треугольник (" + this.x1 + "-" + this.y1 + "), (" + this.x2 + "-" + this.y2 + "), (" + this.x3 + "-" + this.y3 + ")]";
    }

    @Override
    public int hashCode() {
        return 37 * 3 + (int) (this.x1 + this.y1 + this.x2 + this.y2 + this.x3 + this.y3);
    }
}
