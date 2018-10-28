package ru.academit.kolobov.vector.Vector;

import java.util.Arrays;

public class Vector {
    private double[] arrayOfCoordinates;

    public Vector(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Размерность прстранства 0 или отрицательная");
        }
        arrayOfCoordinates = new double[n];
    }

    public Vector(Vector vector) {
        arrayOfCoordinates = Arrays.copyOf(vector.arrayOfCoordinates, vector.arrayOfCoordinates.length);
    }

    public Vector(double[] a) {
        if (a.length == 0) {
            throw new IllegalArgumentException("Размерность прстранства 0 или отрицательная");
        }
        arrayOfCoordinates = Arrays.copyOf(a, a.length);
    }

    public Vector(int n, double[] a) {
        if (a.length == 0 || n == 0) {
            throw new IllegalArgumentException("Размерность прстранства 0 или отрицательная");
        }
        arrayOfCoordinates = Arrays.copyOf(a, n);
    }

    public static Vector sumStatic(Vector v1, Vector v2) {
        Vector v = new Vector(v1);
        v.add(v2);
        return v;
    }

    public static Vector difStatic(Vector v1, Vector v2) {
        Vector v = new Vector(v1);
        v.subtraction(v2);
        return v;
    }

    public static double scalar(Vector v1, Vector v2) {
        int nMin = Math.min(v1.arrayOfCoordinates.length, v2.arrayOfCoordinates.length);
        double scalar = 0;
        for (int i = 0; i < nMin; i++) {
            scalar += v1.arrayOfCoordinates[i] * v2.arrayOfCoordinates[i];
        }
        return scalar;
    }

    public int getSize() {
        return arrayOfCoordinates.length;
    }

    @Override
    public String toString() {
        return Arrays.toString(arrayOfCoordinates).replaceAll("\\[","{").replaceAll("]","}");
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(arrayOfCoordinates);
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
        return !Arrays.equals(this.arrayOfCoordinates, p.arrayOfCoordinates);
    }

    public void add(Vector v2) {
        int nMax = Math.max(this.arrayOfCoordinates.length, v2.arrayOfCoordinates.length);
        int nMin = Math.min(this.arrayOfCoordinates.length, v2.arrayOfCoordinates.length);
        for (int i = 0; i < nMin; i++) {
            arrayOfCoordinates[i] += v2.arrayOfCoordinates[i];
        }
        if (arrayOfCoordinates.length < v2.arrayOfCoordinates.length) {
            double[] arr = new double[nMax];
            System.arraycopy(arrayOfCoordinates, 0, arr, 0, nMin);
            System.arraycopy(v2.arrayOfCoordinates, nMin, arr, nMin, nMax - nMin);
            arrayOfCoordinates = arr;
        }
    }


    public void subtraction(Vector v2) {
        int nMax = Math.max(this.arrayOfCoordinates.length, v2.arrayOfCoordinates.length);
        int nMin = Math.min(this.arrayOfCoordinates.length, v2.arrayOfCoordinates.length);
        if (arrayOfCoordinates.length < v2.arrayOfCoordinates.length) {
            double[] arr = new double[nMax];
            System.arraycopy(arrayOfCoordinates, 0, arr, 0, nMin);
            arrayOfCoordinates = arr;
        }
        for (int i = 0; i < v2.arrayOfCoordinates.length; i++) {
            arrayOfCoordinates[i] -= v2.arrayOfCoordinates[i];
        }
    }

    public void invert() {
        for (int i = 0; i < this.arrayOfCoordinates.length; i++) {
            arrayOfCoordinates[i] = (this.arrayOfCoordinates[i] == 0) ? 0 : this.arrayOfCoordinates[i] * (-1);
        }
    }

    public double getLength() {
        double sum = 0;
        for (double c : this.arrayOfCoordinates) {
            sum += c * c;
        }
        return Math.sqrt(sum);
    }

    public double getElement(int index) {
        return this.arrayOfCoordinates[index];
    }

    public void setElement(int index, double c) {
        this.arrayOfCoordinates[index] = c;
    }

    public void multiplicationVectorByScalar(int i) {
        for (int j = 0; j < arrayOfCoordinates.length; j++) {
            arrayOfCoordinates[j] *= i;
        }
    }
}

