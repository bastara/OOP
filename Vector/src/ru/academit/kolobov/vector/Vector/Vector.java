package ru.academit.kolobov.vector.Vector;

import java.util.Arrays;

public class Vector {
    private double[] coordinates;

    public Vector(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Размерность прстранства 0 или отрицательная");
        }
        coordinates = new double[n];
    }

    public Vector(Vector vector) {
        coordinates = Arrays.copyOf(vector.coordinates, vector.coordinates.length);
    }

    public Vector(double[] a) {
        if (a.length == 0) {
            throw new IllegalArgumentException("Размерность прстранства 0 или отрицательная");
        }
        coordinates = Arrays.copyOf(a, a.length);
    }

    public Vector(int n, double[] a) {
        if (a.length == 0 || n == 0) {
            throw new IllegalArgumentException("Размерность прстранства 0 или отрицательная");
        }
        coordinates = Arrays.copyOf(a, n);
    }

    public static Vector sumNewVector(Vector v1, Vector v2) {
        Vector v = new Vector(v1);
        v.add(v2);
        return v;
    }

    public static Vector difNewVector(Vector v1, Vector v2) {
        Vector v = new Vector(v1);
        v.subtraction(v2);
        return v;
    }

    public static double scalarProductOfVectors(Vector v1, Vector v2) {
        int nMin = Math.min(v1.coordinates.length, v2.coordinates.length);
        double scalar = 0;
        for (int i = 0; i < nMin; i++) {
            scalar += v1.coordinates[i] * v2.coordinates[i];
        }
        return scalar;
    }

    public int getSize() {
        return coordinates.length;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("{");
        for (int i = 0; i < coordinates.length - 1; i++) {
            str.append(coordinates[i]).append(", ");
        }
        return str.append(coordinates[coordinates.length - 1]).append("}").toString();
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(coordinates);
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
        return Arrays.equals(this.coordinates, p.coordinates);
    }

    public void add(Vector v2) {
        if (coordinates.length < v2.coordinates.length) {
            double[] arr = Arrays.copyOf(v2.coordinates, v2.coordinates.length);
            System.arraycopy(coordinates, 0, arr, 0, coordinates.length);
            coordinates = arr;
        }
        for (int i = 0; i < coordinates.length; i++) {
            coordinates[i] += v2.coordinates[i];
        }
    }

    public void subtraction(Vector v2) {
        if (coordinates.length < v2.coordinates.length) {
            double[] arr = new double[v2.coordinates.length];
            System.arraycopy(coordinates, 0, arr, 0, coordinates.length);
            coordinates = arr;
        }
        for (int i = 0; i < v2.coordinates.length; i++) {
            coordinates[i] -= v2.coordinates[i];
        }
    }

    public void invert() {
        for (int i = 0; i < this.coordinates.length; i++) {
            coordinates[i] = (this.coordinates[i] == 0) ? 0 : this.coordinates[i] * (-1);
        }
    }

    public double getLength() {
        double sum = 0;
        for (double c : this.coordinates) {
            sum += c * c;
        }
        return Math.sqrt(sum);
    }

    public double getElement(int index) {
        return this.coordinates[index];
    }

    public void setElement(int index, double c) {
        this.coordinates[index] = c;
    }

    public void multiplicationVectorByScalar(int s) {
        for (int j = 0; j < coordinates.length; j++) {
            coordinates[j] *= s;
        }
    }
}

