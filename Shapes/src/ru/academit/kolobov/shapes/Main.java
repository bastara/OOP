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

        System.out.println("Площадь всех фигур =" + getAreaAllShapes(shapes));

        System.out.println();
        System.out.println("Фигура с максимальной площадью это " + getMaxArea(shapes));
        System.out.println("Площади фигур:");
        for (Shape s : shapes) {
            System.out.println(s + ": " + s.getArea() + " кв.ед.");
        }

        System.out.println();
        System.out.println("Фигура со вторым по величине периметром это " + getSecondByPerimeter(shapes));
        System.out.println("Периметры фигур:");
        for (Shape s : shapes) {
            System.out.println(s + ": " + s.getPerimeter() + " ед.");
        }

        System.out.println();
        System.out.println("Сравниваем " + circle1.toString() + " и " + circle2.toString());
        if (circle1.equals(circle2)) {
            System.out.println("Фигуры одинаковы");
        } else {
            System.out.println("Фигуры разные");
        }
    }

    private static int getAreaAllShapes(Shape[] array) {
        int result = 0;
        for (Shape s : array) {
            result += s.getArea();
        }
        return result;
    }

    private static Shape getMaxArea(Shape[] array) {
        Arrays.sort(array, new AreaComparator());
        return array[0];
    }

    private static Shape getSecondByPerimeter(Shape[] array) {
        Arrays.sort(array, new PerimeterComparator());
        return array[1];
    }
}

