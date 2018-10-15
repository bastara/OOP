package ru.academit.kolobov.shapes.Shapes;

public class Circle implements Shape {
    private double r;

    public Circle(double r) {
        this.r = r;
    }

    @Override
    public double getWidth() {
        return 2 * r;
    }

    @Override
    public double getHeight() {
        return 2 * r;
    }

    @Override
    public double getArea() {
        return Math.PI * r * r;
    }

    @Override
    public double getPerimeter() {
        return 2 * Math.PI * r;
    }
}
