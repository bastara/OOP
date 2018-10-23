package ru.academit.kolobov.vector.Vector;

public class Vector {
    private int dimension;
    private double[] array;

    public Vector(int n) {
        dimension = n;
        array = new double[n];
        for (int i = 0; i < n; i++) {
            array[i] = 0;
        }
    }

    public Vector(Vector vector) {
        dimension = vector.dimension;
        array = new double[vector.array.length];
        for (int i = 0; i < dimension; i++) {
            array[i] = vector.array[i];
        }
    }

    public Vector(double[] a) {
        array = new double[a.length];
        dimension = a.length;
        for (int i = 0; i < dimension; i++) {
            array[i] = a[i];
        }
    }

//    public Vector(double... x) {// конструктор на случай заполнения не из массива а координатами
//        array = new double[x.length];
//        dimension = x.length;
//        for (int i = 0; i < dimension; i++) {
//            array[i] = x[i];
//        }
//    }

    public Vector(int n, double[] a) {
        array = new double[n];
        dimension = n;
        for (int i = 0; i < a.length; i++) {
            array[i] = a[i];
        }
        for (int i = a.length; i < n; i++) {
            array[i] = 0;
        }
    }


    public int getSize() {
        return dimension;
    }

    public double[] getArray() {
        return array;
    }
}

