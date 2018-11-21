package ru.academit.kolobov.matrix.matrix;

import ru.academit.kolobov.vector.Vector.Vector;

import java.util.Arrays;

public class Matrix {
    private Vector[] rows;

    public Matrix(Vector[] vectors) {
        if (vectors == null || vectors.length <= 0) {
            throw new IllegalArgumentException("Размер матрицы 0 или отрицательный");
        }

        int colsCount = 0;
        for (Vector v : vectors) {
            if (v.getSize() > colsCount) {
                colsCount = v.getSize();
            }
        }

        int rowsCount = vectors.length;
        rows = new Vector[rowsCount];
        for (int j = 0; j < rowsCount; j++) {
            rows[j] = new Vector(colsCount);
            int size = vectors[j].getSize();
            for (int i = 0; i < size; i++) {
                rows[j].setElement(i, vectors[j].getElement(i));
            }
        }
    }

    public Matrix(int rowsCount, int colsCount) {
        if (rowsCount <= 0 || colsCount <= 0) {
            throw new IllegalArgumentException("Размер матрицы 0 или отрицательный");
        }
        rows = new Vector[rowsCount];
        for (int i = 0; i < rowsCount; i++) {
            rows[i] = new Vector(colsCount);
        }
    }

    public Matrix(Matrix matrix) {
        rows = new Vector[matrix.rows.length];
        for (int i = 0; i < matrix.rows.length; i++) {
            rows[i] = matrix.getRow(i);
        }
    }

    public Matrix(double[][] a) {
        int rowsCount = a.length;
        int colsCount = 0;
        for (double[] r : a) {
            if (colsCount < r.length) {
                colsCount = r.length;
            }
        }
        if (colsCount == 0) {
            throw new IllegalArgumentException("Размер матрицы 0");
        }
        rows = new Vector[rowsCount];
        for (int i = 0; i < rowsCount; i++) {
            double[] m = Arrays.copyOf(a[i], colsCount);
            rows[i] = new Vector(m);
        }
    }

    public int getColumnsCount() {
        return rows[0].getSize();
    }

    public int getRowsCount() {
        return rows.length;
    }

    public Vector getRow(int i) {
        if (i < 0 || i >= rows.length) {
            throw new IndexOutOfBoundsException("Индекс вектора не соответствует размерам матрицы!");
        }
        return new Vector(rows[i]);
    }

    public void setRow(int i, Vector v) {
        if (i < 0 || i >= rows.length) {
            throw new IndexOutOfBoundsException("Индекс вектора не соответствует размерам матрицы!");
        }
        if (v.getSize() != getColumnsCount()) {
            throw new IllegalArgumentException("Размер вектора не соответствует размеру матрицы");
        }
        rows[i] = new Vector(v);
    }

    //через toString векторов
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("{");
        for (int i = 0; i < rows.length; i++) {
            if (i > 0) {
                str.append(",");
            }
            str.append(rows[i].toString());
        }
        return str.append("}").toString();
    }

    public void transposition() {
        int rowsCount = getColumnsCount();
        Vector[] vectors = new Vector[rowsCount];
        for (int i = 0; i < rowsCount; i++) {
            vectors[i] = getColumn(i);
        }
        rows = vectors;
    }

    public Vector getColumn(int i) {
        if (i < 0 || i >= getColumnsCount()) {
            throw new IndexOutOfBoundsException("Индекс вектора не соответствует размерам матрицы!");
        }
        double[] tmpV = new double[rows.length];
        for (int j = 0; j < rows.length; j++) {
            tmpV[j] = rows[j].getElement(i);
        }
        return new Vector(tmpV);
    }

    public void multiplicationByScalar(int s) {
        for (Vector v : rows) {
            v.multiplyByScalar(s);
        }
    }

    public double determinant() {
        int rowsCount = rows.length;
        int colsCount = getColumnsCount();

        if (colsCount != rowsCount) {
            throw new IllegalArgumentException("Матрица должна быть квадратного вида");
        }

        if (colsCount == 1) {
            return rows[0].getElement(0);
        }

        if (colsCount == 2) {
            return rows[0].getElement(0) * rows[1].getElement(1) - rows[0].getElement(1) * rows[1].getElement(0);
        }

        Matrix tmpM = new Matrix(this);
        double det = 0;
        for (int i = 0; i < rowsCount; i++) {
            double[][] arrayTmp = new double[rowsCount - 1][colsCount - 1];
            for (int j = 1; j < rowsCount; j++) {
                int colunmCount = 0;
                for (int k = 0; k < colsCount; k++) {
                    if (k == i) {
                        continue;
                    }
                    arrayTmp[j - 1][colunmCount] = tmpM.rows[j].getElement(k);
                    colunmCount++;
                }
            }
            det = det + tmpM.rows[0].getElement(i) * (new Matrix(arrayTmp)).determinant() * Math.pow(-1, i);
        }
        return det;
    }

    public Vector multiplicationByVector(Vector v) {
        if (getColumnsCount() != v.getSize()) {
            throw new IllegalArgumentException("Длина вектора должна быть равна числу столбцов в матрице!");
        }

        int rowsCount = rows.length;
        double[] arr = new double[rowsCount];
        for (int j = 0; j < rowsCount; j++) {
            arr[j] = Vector.scalarProductOfVectors(rows[j], v);
        }
        return new Vector(arr);
    }

    public void addMatrix(Matrix m1) {
        int rowsCount = rows.length;
        int colsCount = getColumnsCount();
        int rowsCountM1 = m1.rows.length;
        int colsCountM1 = m1.getColumnsCount();
        if (rowsCount != rowsCountM1 || colsCount != colsCountM1) {
            throw new IllegalArgumentException("Размеры матриц должны совпадать!");
        }

        for (int j = 0; j < rowsCount; j++) {
            rows[j].add(m1.rows[j]);
        }
    }

    public void difMatrix(Matrix m1) {
        int rowsCount = rows.length;
        int colsCount = getColumnsCount();
        int rowsCountM1 = m1.rows.length;
        int colsCountM1 = m1.getColumnsCount();
        if (rowsCount != rowsCountM1 || colsCount != colsCountM1) {
            throw new IllegalArgumentException("Размеры матриц должны совпадать!");
        }
        for (int j = 0; j < rowsCount; j++) {
            rows[j].subtraction(m1.rows[j]);
        }
    }

    public static Matrix additionMatrix(Matrix m1, Matrix m2) {
        int rowsCountM1 = m1.rows.length;
        int colsCountM1 = m1.getColumnsCount();
        int rowsCountM2 = m2.rows.length;
        int colsCountM2 = m2.getColumnsCount();
        if (rowsCountM2 != rowsCountM1 || colsCountM2 != colsCountM1) {
            throw new IllegalArgumentException("Размеры матриц должны совпадать!");
        }
        Matrix m = new Matrix(m1);
        m.addMatrix(m2);
        return m;
    }

    public static Matrix subtractMatrix(Matrix m1, Matrix m2) {
        int rowsCountM1 = m1.rows.length;
        int colsCountM1 = m1.getColumnsCount();
        int rowsCountM2 = m2.rows.length;
        int colsCountM2 = m2.getColumnsCount();
        if (rowsCountM2 != rowsCountM1 || colsCountM2 != colsCountM1) {
            throw new IllegalArgumentException("Размеры матриц должны совпадать!");
        }
        Matrix m = new Matrix(m1);
        m.difMatrix(m2);
        return m;
    }

    public static Matrix multiplicationMatrix(Matrix m1, Matrix m2) {
        int rowsCountM1 = m1.rows.length;
        int colsCountM1 = m1.getColumnsCount();
        int rowsCountM2 = m2.rows.length;
        int colsCountM2 = m2.getColumnsCount();
        if (colsCountM1 != rowsCountM2) {
            throw new IllegalArgumentException("Количество столбцов первой матрицы должно быть равно количеству строк второй матрицы!");
        }

        double[][] tmpArray = new double[rowsCountM1][colsCountM2];
        for (int j = 0; j < rowsCountM1; j++) {
            for (int i = 0; i < colsCountM2; i++) {
                for (int k = 0; k < colsCountM1; k++) {
                    tmpArray[j][i] += m1.rows[j].getElement(k) * m2.rows[k].getElement(i);
                }
            }
        }
        return new Matrix(tmpArray);
    }
}
