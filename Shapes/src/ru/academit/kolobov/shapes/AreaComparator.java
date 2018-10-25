package ru.academit.kolobov.shapes;

import ru.academit.kolobov.shapes.Shapes.Shape;

import java.util.Comparator;

public class AreaComparator implements Comparator<Shape> {

    @Override
    public int compare(Shape f1, Shape f2) {
        return Double.compare(f2.getArea(),f1.getArea());
    }
}
