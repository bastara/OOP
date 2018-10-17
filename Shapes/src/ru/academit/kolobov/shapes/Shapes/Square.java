package ru.academit.kolobov.shapes.Shapes;

import java.util.Comparator;

public class Square implements Shape, Comparable<Shape> {
    private double side;

    public Square(double side) {
        this.side = side;
    }

    @Override
    public double getWidth() {
        return side;
    }

    @Override
    public double getHeight() {
        return side;
    }

    @Override
    public double getArea() {
        return side * side;
    }

    @Override
    public double getPerimeter() {
        return 4 * side;
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
        return "[Квадрата (" + this.side + ")]";
    }

    @Override
    public int hashCode() {
        return 37*3+(int) this.side;
    }
}
