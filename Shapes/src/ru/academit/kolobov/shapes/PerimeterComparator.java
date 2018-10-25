package ru.academit.kolobov.shapes;

import ru.academit.kolobov.shapes.Shapes.Shape;

import java.util.Comparator;

public class PerimeterComparator implements Comparator<Shape> {

    @Override
    public int compare(Shape f1, Shape f2) {
        return Double.compare(f2.getPerimeter(),f1.getPerimeter());
    }
}
