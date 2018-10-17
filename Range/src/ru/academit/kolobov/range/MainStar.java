package ru.academit.kolobov.range;

import ru.academit.kolobov.range.RangeClass.Range;

public class MainStar {
    public static void main(String[] args) {
        double point1Start = 1;
        double point1End = 3;
        double point2Start = 3;
        double point2End = 5;

        Range interval1 = new Range(point1Start, point1End);
        Range interval2 = new Range(point2Start, point2End);

        System.out.println("Начальная точка отрезка это " + interval1.getFrom());
        System.out.println("Конечная точка отрезка это " + interval1.getTo());
        System.out.println("Длина 1го интервала равна " + interval1.getLength());
        System.out.println();

        System.out.println("Начальная точка отрезка это " + interval2.getFrom());
        System.out.println("Конечная точка отрезка это " + interval2.getTo());
        System.out.println("Длина 2го интервала равна " + interval2.getLength());
        System.out.println();

        Range interval3 = interval1.getIntervalIntersection(interval2);
        if (interval3 == null) {
            System.out.println("null");
        } else {
            System.out.println("Интервал пересечения 1го и 2го интервалов: " + interval3.getFrom() + "-" + interval3.getTo());
        }

        Range[] array = interval1.getIntervalMerge(interval2);
        if (array.length == 1) {
            System.out.println("Интервал объединения 1го и 2го интервалов: " + array[0].getFrom() + "-" + array[0].getTo());
        } else {
            System.out.println("Получены интервалы объединения 1го и 2го интервалов: " + array[0].getFrom() + "-" + array[0].getTo() + " и " + array[1].getFrom() + "-" + array[1].getTo());
        }

        array = interval1.getIntervalDifference(interval2);
        if (array == null) {
            System.out.println("null");
        } else if (array.length == 2) {
            System.out.println("Получены интервалы разности 1го и 2го интервалов: " + array[0].getFrom() + "-" + array[0].getTo() + " и " + array[1].getFrom() + "-" + array[1].getTo());
        } else if (array.length == 1) {
            System.out.println("Интервал разности 1го и 2го интервалов: " + array[0].getFrom() + "-" + array[0].getTo());
        } else {
            System.out.println("null");
        }
    }
}

