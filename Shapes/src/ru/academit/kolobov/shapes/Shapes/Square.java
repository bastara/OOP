package ru.academit.kolobov.shapes.Shapes;

public class Square implements Shape {
    private double a;

    public Square(double a) {
        this.a = a;
    }

    @Override
    public double getWidth() {
        return a;
    }

    @Override
    public double getHeight() {
        return a;
    }

    @Override
    public double getArea() {
        return a * a;
    }

    @Override
    public double getPerimeter() {
        return 4 * a;
    }
}
