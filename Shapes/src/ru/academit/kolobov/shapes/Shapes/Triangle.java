package ru.academit.kolobov.shapes.Shapes;

public class Triangle implements Shape {
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

    private double getLengthSide(double x1, double y1, double x2, double y2) {
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
        double p = getPerimeter();
        return Math.sqrt(p * (p - getLengthSide(x1, y1, x2, y2)) * (p - getLengthSide(x2, y2, x3, y3)) * (p - getLengthSide(x3, y3, x1, y1)));
    }

    @Override
    public double getPerimeter() {
        return getLengthSide(x1, y1, x2, y2) + getLengthSide(x2, y2, x3, y3) + getLengthSide(x3, y3, x1, y1);
    }
}
