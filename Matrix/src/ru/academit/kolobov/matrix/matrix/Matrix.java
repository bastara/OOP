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

    public Matrix(double [][] array) {
        int y = array.length;
        int x = array[0].length;
        array = new double[y][x];
        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {
                array[i][j] = array[i][j];
            }
        }
    }

}
