package ru.academit.kolobov.shapes.Shapes;

public class Rectangle implements Shape {
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
    public String toString() {
        return "[Прямоугольник (" + this.side1 + "*" + this.side2 + ")]";
    }

    @Override
    public int hashCode() {
        final int prime = 37;
        int hash = 1;
        hash = prime * hash + Double.hashCode(side1);
        hash = prime * hash + Double.hashCode(side2);
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

        Rectangle p = (Rectangle) o;
        return side1 == p.side1 && side2 == p.side2;
    }

}
