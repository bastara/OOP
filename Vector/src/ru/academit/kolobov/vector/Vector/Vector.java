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
        array = Arrays.copyOf(vector.array, vector.array.length);
    }

    public Vector(double[] a) {
        if (a.length == 0) {
            throw new IllegalArgumentException("Размерность прстранства 0 или отрицательная");
        }
        array = Arrays.copyOf(a, a.length);
    }

    public Vector(int n, double[] a) {
        if (a.length == 0 || n == 0) {
            throw new IllegalArgumentException("Размерность прстранства 0 или отрицательная");
        }
        array = Arrays.copyOf(a, n);
    }

    public static Vector sumStatic(Vector v1, Vector v2) {
        v1.add(v2);
        return v1;
    }

    public static Vector difStatic(Vector v1, Vector v2) {
        v1.subtraction(v2);
        return v1;
    }

    public static double scalar(Vector v1, Vector v2) {
        int nMin = Math.min(v1.array.length, v2.array.length);
        double scalar = 0;
        for (int i = 0; i < nMin; i++) {
            scalar += v1.array[i] * v2.array[i];
        }
        return scalar;
    }

    public int getSize() {
        if (array == null) {
            throw new NullPointerException("Массив 0 размерности");
        }
        return array.length;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("{");
        for (int i = 0; i < array.length - 1; i++) {
            str.append(array[i]).append(", ");
        }
        return str.toString() + array[array.length - 1] + "}";
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

    public void add(Vector v2) {
        int nMax = Math.max(this.array.length, v2.array.length);
        int nMin = Math.min(this.array.length, v2.array.length);
        if (array.length > v2.array.length) {
            for (int i = 0; i < nMin; i++) {
                array[i] = array[i] + v2.array[i];
            }
        } else {
            double[] arr = new double[nMax];
            for (int i = 0; i < nMin; i++) {
                arr[i] = array[i] + v2.array[i];
            }
            System.arraycopy(v2.array, nMin, arr, nMin, nMax - nMin);
            array = arr;
        }
    }

    public void subtraction(Vector v2) {
        int nMax = Math.max(this.array.length, v2.array.length);
        int nMin = Math.min(this.array.length, v2.array.length);
        if (array.length > v2.array.length) {
            for (int i = 0; i < nMin; i++) {
                array[i] = array[i] - v2.array[i];
            }
        } else {
            double[] arr = new double[nMax];
            for (int i = 0; i < nMin; i++) {
                arr[i] = array[i] - v2.array[i];
            }
            for (int i = nMin; i < nMax; i++) {
                arr[i] = -v2.array[i];
            }
            array = arr;
        }
    }

    public void invert() {
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

    public double getElement(int index) {
        return this.array[index];
    }

    public void setElement(int index, double c) {
        this.array[index] = c;
    }

    public void multiplicationVectorByScalar(int i) {
        for (int j = 0; j < array.length; j++) {
            array[j] *= i;
        }
    }
}

