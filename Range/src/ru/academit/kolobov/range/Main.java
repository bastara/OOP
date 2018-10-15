package ru.academit.kolobov.range;

import ru.academit.kolobov.range.RangeClass.Range;

public class Main {
    public static void main(String[] args) {
        double endPosition = 10;
        double startPosition = 5.5;

        Range segment = new Range(startPosition, endPosition);

        System.out.println("Начальная точка отрезка это " + segment.getFrom());
        System.out.println("Конечная точка отрезка это " + segment.getTo());
        System.out.println("Длина отрезка равна " + segment.getLength());

        double point = 12;
        System.out.println("Точка " + point + (segment.isInside(point) ? " принадлежит отрезку" : " не принадлежит отрезку"));

        double newEndPosition = 14;
        double newStartPosition = 7.3;

        segment.setTo(newEndPosition);
        segment.setFrom(newStartPosition);

        System.out.println();
        System.out.println("Начальная точка отрезка это " + segment.getFrom());
        System.out.println("Конечная точка отрезка это " + segment.getTo());

        System.out.println("Точка " + point + (segment.isInside(point) ? " принадлежит отрезку" : " не принадлежит отрезку"));
    }
}
