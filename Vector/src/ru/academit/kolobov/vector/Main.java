package ru.academit.kolobov.vector;

import ru.academit.kolobov.vector.Vector.Vector;

public class Main {
    public static void main(String[] args) {
        double[] c1 = {2, 4, 8};
        Vector v1 = new Vector(c1);
        double[] c2 = {3, -7, 2, -4};
        Vector v2 = new Vector(c2);
        Vector v3 = new Vector(v1);

        System.out.println(v1.getSize());

        System.out.println(v2.getSize());

        System.out.println(v3.getSize());
        for (double v : v3.getArray()) {
            System.out.println(v);
        }

        Vector v4 = new Vector(5, c1);

        System.out.println(v4.getSize());
        for (double v : v4.getArray()) {
            System.out.println(v);
        }

        System.out.println("Размер вектора v1: " + v1.getSize());
        System.out.println("Размер вектора v5: " + v4.getSize());
    }
}
