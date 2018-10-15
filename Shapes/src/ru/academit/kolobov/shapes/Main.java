package ru.academit.kolobov.shapes;

import ru.academit.kolobov.shapes.Shapes.Circle;
import ru.academit.kolobov.shapes.Shapes.Rectangle;
import ru.academit.kolobov.shapes.Shapes.Square;
import ru.academit.kolobov.shapes.Shapes.Triangle;

public class Main {
    public static void main(String[] args) {
        Square square1 = new Square(4);
        Square square2 = new Square(6);

        Triangle triangle1 = new Triangle(1, 3, 4, 1, 3, 2);
        Triangle triangle2 = new Triangle(2, 6, 7, 1, 2, 8);

        Rectangle rectangle1 = new Rectangle(3, 5);
        Rectangle rectangle2 = new Rectangle(8, 6);

        Circle circle1 = new Circle(2);
        Circle circle2 = new Circle(4);
    }
}
