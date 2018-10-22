package ru.academit.kolobov.shapes;

import ru.academit.kolobov.shapes.Shapes.Shape;

import java.util.Comparator;

public class SortedByArea implements Comparator<Shape> {

    @Override
    public int compare(Shape f1, Shape f2) {
        double area1 = f1.getArea();
        double area2 = f2.getArea();
        double result = area2 - area1;
        if (result != 0) {
            return (int) (result / Math.abs(result));
        }
        return 0;
    }
}
