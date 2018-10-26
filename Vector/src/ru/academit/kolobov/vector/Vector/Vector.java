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
        if (vector.array.length >= 0) {
            System.arraycopy(vector.array, 0, array, 0, vector.array.length);
        }
    }

    public Vector(double[] a) {
        array = new double[a.length];
        if (a.length >= 0) {
            System.arraycopy(a, 0, array, 0, a.length);//вижу варнинги, лучше исправлять или не стоит?
        }
    }

    public Vector(int n, double[] a) {
        array = new double[n];
        if (a.length >= 0) {
            System.arraycopy(a, 0, array, 0, a.length);
        }
        for (int i = a.length; i < n; i++) {
            array[i] = 0;
        }
    }

    public static Vector sumVector(Vector v1, Vector v2) {
        boolean b = v1.array.length > v2.array.length;
        int nMax = Math.max(v1.array.length, v2.array.length);
        int nMin = Math.min(v1.array.length, v2.array.length);
        double[] arr = new double[nMax];
        for (int i = 0; i < nMin; i++) {
            arr[i] = v1.array[i] + v2.array[i];
        }
        if (b) {
            System.arraycopy(v1.array, nMin, arr, nMin, nMax - nMin);
        } else {
            System.arraycopy(v2.array, nMin, arr, nMin, nMax - nMin);
        }
        return new Vector(nMax, arr);
    }

    public static Vector difVector(Vector v1, Vector v2) {
        boolean b = v1.array.length > v2.array.length;
        int nMax = Math.max(v1.array.length, v2.array.length);
        int nMin = Math.min(v1.array.length, v2.array.length);
        double[] arr = new double[nMax];
        for (int i = 0; i < nMin; i++) {
            arr[i] = v1.array[i] - v2.array[i];
        }
        if (b) {
            System.arraycopy(v1.array, nMin, arr, nMin, nMax - nMin);
        } else {
            System.arraycopy(v2.array, nMin, arr, nMin, nMax - nMin);
        }
        return new Vector(nMax, arr);
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

    public Vector addVector(Vector v2) {
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
        return new Vector(nMax, arr);
    }

    public Vector subtractionVector(Vector v2) {
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
        return new Vector(nMax, arr);
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

