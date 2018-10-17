package ru.academit.kolobov.shapes.Shapes;

public class Rectangle implements Shape, Comparable<Shape> {
    private double side1;
    private double side2;

    public Rectangle(double side1, double side2) {
        this.side1 = side1;
        this.side2 = side2;
    }


    @Override
    public double getWidth() {
        return side1;
    }

    @Override
    public double getHeight() {
        return side2;
    }

    @Override
    public double getArea() {
        return side1 * side2;
    }

    @Override
    public double getPerimeter() {
        return 2 * (side1 + side2);
    }

    @Override
    public int compareTo(Shape f) {
        return (int) (this.getArea() - f.getArea());
    }

    @Override
    public String toString() {
        return "[Прямоугольник (" + this.side1 + "*" + this.side2 + ")]";
    }

    @Override
    public int hashCode() {
        return 37 * 2 + (int) (this.side1 + this.side2);
    }
}
