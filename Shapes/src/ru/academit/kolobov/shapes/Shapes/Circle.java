package ru.academit.kolobov.shapes.Shapes;

public class Circle implements Shape, Comparable<Shape> {
    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    @Override
    public double getWidth() {
        return 2 * radius;
    }

    @Override
    public double getHeight() {
        return 2 * radius;
    }

    @Override
    public double getArea() {
        return Math.PI * radius * radius;
    }

    @Override
    public double getPerimeter() {
        return 2 * Math.PI * radius;
    }

    @Override
    public int compareTo(Shape f) {
        return (int) (this.getArea() - f.getArea());
    }

    @Override
    public String toString() {
        return "[Круг (R=" + this.radius + ")]";
    }

    @Override
    public int hashCode() {
        return 37 * 314 + (int) this.radius;
    }
}
