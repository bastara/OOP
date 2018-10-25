package ru.academit.kolobov.matrix;

import ru.academit.kolobov.matrix.matrix.Matrix;
import ru.academit.kolobov.vector.Vector.Vector;

public class Main {
    public static void main(String[] args) {
        double[] c1 = {2, 5, 6, 3};
        Vector v1 = new Vector(c1);

        double[] c2 = {4, -7, -8, 4};
        Vector v2 = new Vector(c2);

        double[] c3 = {-6, 12, 3, 5};
        Vector v3 = new Vector(c3);

        Vector[] arrayV = {v1, v2, v3};

        Matrix m = new Matrix(arrayV);

        //копируем матрицу m
        Matrix m1 = new Matrix(m);
        System.out.println(m1.getArray()[0][2]);
        System.out.println(m1.getArray()[2][3]);
    }
}
