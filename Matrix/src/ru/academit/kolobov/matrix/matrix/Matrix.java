package ru.academit.kolobov.matrix.matrix;

import ru.academit.kolobov.vector.Vector.Vector;

public class Matrix {
    private double[][] array;

    public double[][] getArray() {
        return array;
    }

    public Matrix(Vector[] vectors) {
        //помню что у меня была еще переменная dimension, тут я ее упускаю. Может в векторе ее тоже убрать и размер брать от длинны? Или оставить и разместить например в [0] индексе?
        int y = vectors.length;
        int x = vectors[0].getArray().length;
        array = new double[y][x];
        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {
                array[i][j] = vectors[i].getArray()[j];
            }
        }
    }

    public Matrix(int n, int m) {
        array = new double[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                array[i][j] = 0;
            }
        }
    }

    public Matrix(Matrix matrix) {
        int y = matrix.getArray().length;
        int x = matrix.getArray()[0].length;
        array = new double[y][x];
        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {
                array[i][j] = matrix.getArray()[i][j];
            }
        }
    }

    public Matrix(double[][] a) {
        int y = a.length;
        int x = a[0].length;
        array = new double[y][x];
        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {
                array[i][j] = a[i][j];
            }
        }
    }

    public int getSizeX(Matrix matrix) {
        return matrix.getArray()[0].length;
    }

    public int getSizeY(Matrix matrix) {
        return matrix.getArray().length;
    }

    public Vector getVector(int i) {
        return new Vector(this.getArray()[i]);
    }

    @Override
    public String toString() {
        String str = "{";
        for (int i = 0; i < array.length; i++) {
            if (i > 0) {
                str += ",";
            }
            str += "{";
            for (int j = 0; j < array[0].length - 1; j++) {
                str += array[i][j] + ", ";
            }
            str += array[i][array[0].length - 1] + "}";
        }
        str += "}";
        return str;
    }

    public Matrix getTranspositionMatrix() {
        int y = array.length;
        int x = array[0].length;
        double[][] arrayTrp = new double[x][y];
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                arrayTrp[i][j] = array[j][i];
            }
        }
        return new Matrix(arrayTrp);
    }

    public Vector getVectorColumn(int i) {
        Matrix m = getTranspositionMatrix();
        return m.getVector(i);
    }
}

