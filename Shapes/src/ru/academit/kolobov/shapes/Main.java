package ru.academit.kolobov.shapes;

import ru.academit.kolobov.shapes.Shapes.*;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Square square1 = new Square(4);
        Square square2 = new Square(9);

        Triangle triangle1 = new Triangle(1, 3, 3, 1, 3, 1);
        Triangle triangle2 = new Triangle(2, 6, 7, 1, 2, 8);

        Rectangle rectangle1 = new Rectangle(3, 5);
        Rectangle rectangle2 = new Rectangle(4, 6);
        Rectangle rectangle3 = new Rectangle(5, 7);

        Circle circle1 = new Circle(2);
        Circle circle2 = new Circle(4);

        Shape[] shapes = {rectangle1, rectangle2, rectangle3, square1, square2, triangle1, triangle2, circle1, circle2};

        System.out.println("Площадь всех фигур =" + getSumArea(shapes));

        System.out.println("Фигура с максимальной площадью это " + getMaxArea(shapes));

        System.out.println("Фигура со вторым по величине периметром это " + getMaxPerimetr(shapes));
    }

    private static int getSumArea(Shape[] array) {
        int result = 0;
        for (Shape s : array) {
            result += s.getArea();
            System.out.println(s.toString() + " площадь " + s.getArea());
        }
        return result;
    }

    private static Shape getMaxArea(Shape[] array) {
        Arrays.sort(array);
        return array[array.length - 1];
    }

    private static Shape getMaxPerimetr(Shape[] array) {
        Arrays.sort(array, Square.perimetrComparator);
        return array[array.length - 2];
    }
}

