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

    private static double getLengthSide(double x1, double y1, double x2, double y2) {//надо сделать статик т.к. не работает с полями.
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));   //static функции рекомендуется всегда вызывать через имя класса: A.f();
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
    public String toString() {
        return "[Треугольник (" + this.x1 + "-" + this.y1 + "), (" + this.x2 + "-" + this.y2 + "), (" + this.x3 + "-" + this.y3 + ")]";
    }

    @Override
    public int hashCode() {
        final int prime = 37;
        int hash = 1;
        hash = prime * hash + Double.hashCode(x1);
        hash = prime * hash + Double.hashCode(x2);
        hash = prime * hash + Double.hashCode(x3);
        hash = prime * hash + Double.hashCode(y1);
        hash = prime * hash + Double.hashCode(y2);
        hash = prime * hash + Double.hashCode(y3);
        return hash;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }

        Triangle p = (Triangle) o;
        return x1 == p.x1 && x2 == p.x2 && x3 == p.x3 && y1 == p.y1 && y2 == p.y2 && y3 == p.y3;
    }
}

