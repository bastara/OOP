package ru.academit.kolobov.vector.Vector;

import java.util.Arrays;

public class Vector {
    private double[] array;

    public Vector(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Размерность прстранства 0 или отрицательная");
        }
        array = new double[n];
    }

    public Vector(Vector vector) {
        array = new double[vector.array.length];
        array = Arrays.copyOf(vector.array, vector.array.length);
    }

    public Vector(double[] a) {
        if (a.length == 0) {
            throw new IllegalArgumentException("Размерность прстранства 0 или отрицательная");
        }
        array = new double[a.length];
        array = Arrays.copyOf(a, a.length);
    }

    public Vector(int n, double[] a) {
        if (a.length == 0 || n == 0) {
            throw new IllegalArgumentException("Размерность прстранства 0 или отрицательная");
        }
        array = new double[n];
        System.arraycopy(a, 0, array, 0, a.length);
//            array = Arrays.copyOf(a, a.length);
        // почему то не могу это использовать, вроде и созадется массив на 5 элементов, но потом они пропадают...
    }

    public static Vector sumVector(Vector v1, Vector v2) {
        Vector v = new Vector(1);
        v.addVector(v1);
        v.addVector(v2);
        return v;
    }

    public static Vector difVector(Vector v1, Vector v2) {
        Vector v = v1;
        v.subtractionVector(v2);
        return v;
    }

    public static double scalarVector(Vector v1, Vector v2) {
        int nMin = Math.min(v1.array.length, v2.array.length);
        double scalar = 0;
        for (int i = 0; i < nMin; i++) {
            scalar += v1.array[i] * v2.array[i];
        }
        return scalar;
    }

    public int getSize() {
        return array.length;
    }

    public double[] getArray() {
        return array;
    }

    @Override
    public String toString() {
        String str = "{";
        for (int i = 0; i < array.length - 1; i++) {
            str += array[i] + ", ";
        }
        return str + array[array.length - 1] + "}";
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(array);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }

        Vector p = (Vector) o;
        if (!Arrays.equals(this.array, p.array)) {
            return false;
        }
        return array.length == p.array.length;// я так понимаю что размерность массивов проверится ранее и данной проверке нет необходимости, но т.к. стоит в условии задачи то оставил.
    }

    public void addVector(Vector v2) {
        boolean b = this.array.length > v2.array.length;
        int nMax = Math.max(this.array.length, v2.array.length);
        int nMin = Math.min(this.array.length, v2.array.length);
        double[] arr = new double[nMax];
        for (int i = 0; i < nMin; i++) {
            arr[i] = this.array[i] + v2.array[i];
        }
        if (b) {
            System.arraycopy(this.array, nMin, arr, nMin, nMax - nMin);
        } else {
            System.arraycopy(v2.array, nMin, arr, nMin, nMax - nMin);
        }
        this.array = arr;
    }

    public void subtractionVector(Vector v2) {
        boolean b = this.array.length > v2.array.length;
        int nMax = Math.max(this.array.length, v2.array.length);
        int nMin = Math.min(this.array.length, v2.array.length);
        double[] arr = new double[nMax];
        for (int i = 0; i < nMin; i++) {
            arr[i] = this.array[i] - v2.array[i];
        }
        if (b) {
            System.arraycopy(this.array, nMin, arr, nMin, nMax - nMin);
        } else {
            System.arraycopy(v2.array, nMin, arr, nMin, nMax - nMin);
        }
        this.array = arr;
    }

    public void invertVector() {
        for (int i = 0; i < this.array.length; i++) {
            array[i] = (this.array[i] == 0) ? 0 : this.array[i] * (-1);
        }
    }

    public double getLength() {
        double sum = 0;
        for (double c : this.array) {
            sum += c * c;
        }
        return Math.sqrt(sum);
    }

    public double getElementVector(int index) {
        return this.array[index];
    }

    public void setElementVector(int index, double c) {
        this.array[index] = c;
    }

    public void getMultiplicationVectorByScalar(int i) {
        for (int j = 0; j < array.length; j++) {
            array[j] *= i;
        }
    }
}

