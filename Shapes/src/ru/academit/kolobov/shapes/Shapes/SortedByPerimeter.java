package ru.academit.kolobov.shapes.Shapes;

import java.util.Comparator;

public class SortedByPerimeter implements Comparator<Shape> {

    @Override
    public int compare(Shape f1, Shape f2) {
        double area1 = f1.getPerimeter();
        double area2 = f2.getPerimeter();
        double result = area2 - area1;
        if (result != 0) {
            return (int) (result / Math.abs(result));
        }
        return 0;
    }
}
