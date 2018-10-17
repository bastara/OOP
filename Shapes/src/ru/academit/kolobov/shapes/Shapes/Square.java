package ru.academit.kolobov.shapes.Shapes;

import java.util.Comparator;

public class Square implements Shape, Comparable<Shape> {
    public static Comparator<Shape> perimetrComparator = new Comparator<Shape>() {
        @Override
        public int compare(Shape f1, Shape f2) {
            return (int) (f1.getPerimeter() - f2.getPerimeter());
        }
    };
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

    @Override
    public String toString() {
        return "[Квадрат (" + this.side + ")]";
    }

    @Override
    public int hashCode() {
        return 37 * 3 + (int) this.side;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        return this.toString().equals(o.toString());

    }
}
