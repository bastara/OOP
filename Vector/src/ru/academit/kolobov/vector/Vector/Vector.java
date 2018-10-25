package ru.academit.kolobov.vector.Vector;

public class Vector {
    private int dimension;
    private double[] array;

    public Vector(int n) {
        try {
            if (n <= 0) {
                throw new IllegalArgumentException("Размерность прстранства 0 или отрицательная");
            }
            dimension = n;
            array = new double[n];
            for (int i = 0; i < n; i++) {
                array[i] = 0;
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Размерность прстранства 0 или отрицательная");
            dimension = 0;
            array = new double[dimension];
        }
    }

    public Vector(Vector vector) {
        dimension = vector.dimension;
        array = new double[vector.array.length];
        if (dimension >= 0) System.arraycopy(vector.array, 0, array, 0, dimension);
    }

    public Vector(double[] a) {
        array = new double[a.length];
        dimension = a.length;
        if (dimension >= 0) System.arraycopy(a, 0, array, 0, dimension);//вижу варнинги, лучше исправлять или не стоит?
    }

    public Vector(int n, double[] a) {
        array = new double[n];
        dimension = n;
        if (a.length >= 0) System.arraycopy(a, 0, array, 0, a.length);
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
        final int prime = 37;
        int hash = 1;
        hash = prime * hash + dimension;
        for (double c : array) {
            hash = prime * hash + Double.hashCode(c);
        }
        return hash;
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
        for (int i = 0; i < p.array.length; i++) {
            if (array[i] != p.array[i]) {
                return false;
            }
        }
        return dimension == p.dimension;
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

    public double lengthVector() {
        double sum = 0;
        for (double c : this.array) {
            sum += c * c;
        }
        return Math.sqrt(sum);
    }

    public double getElementVector(int e) {
        return this.array[e];
    }

    public void setElementVector(int e, double c) {
        this.array[e] = c;
    }

    public static double scalarVector(Vector v1, Vector v2) {
        int nMin = Math.min(v1.array.length, v2.array.length);
        double scalar = 0;
        for (int i = 0; i < nMin; i++) {
            scalar += v1.array[i] * v2.array[i];
        }
        return scalar;
    }
}

