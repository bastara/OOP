package ru.academit.kolobov.matrix.matrix;

import ru.academit.kolobov.vector.Vector.Vector;

import java.util.Arrays;

public class Matrix {
    private Vector[] row;

    public Matrix(Vector[] vectors) {
        int colCount = 0;
        if (vectors == null || vectors.length <= 0) {
            throw new IllegalArgumentException("Размер массива 0 или отрицательный");
        }
        for (Vector v : vectors) {
            if (v.getSize() > colCount) {
                colCount = v.getSize();
            }
        }
        int rowCount = vectors.length;
        row = new Vector[rowCount];
        for (int j = 0; j < rowCount; j++) {
            row[j] = new Vector(colCount);
            int size = vectors[j].getSize();
            for (int i = 0; i < size; i++) {
                row[j].setElement(i, vectors[j].getElement(i));
            }
        }
    }

    public Matrix(int rowCount, int colCount) {
        if (rowCount <= 0 || colCount <= 0) {
            throw new IllegalArgumentException("Размер массива 0 или отрицательный");
        }
        row = new Vector[rowCount];
        for (int i = 0; i < rowCount; i++) {
            row[i] = new Vector(colCount);
        }
    }

    public Matrix(Matrix matrix) {
        row = new Vector[matrix.row.length];
        for (int i = 0; i < matrix.row.length; i++) {
            row[i] = matrix.getRow(i);
        }
    }

    public Matrix(double[][] a) {
        int rowCount = a.length;
        int colCount = 0;
        for (double[] r : a) {
            if (colCount < r.length) {
                colCount = r.length;
            }
        }
        if (colCount == 0) {
            throw new IllegalArgumentException("Размер массива 0");
        }
        row = new Vector[rowCount];
        for (int i = 0; i < rowCount; i++) {
            double[] m = Arrays.copyOf(a[i], colCount);
            row[i] = new Vector(m);
        }
    }

    public int getColumnsCount() {
        return row[0].getSize();
    }

    public int getRowsCount() {
        return row.length;
    }

    public Vector getRow(int i) {
        if (i < 0 || i >= row.length) {
            throw new ArrayIndexOutOfBoundsException("Индекс вектора не соответствует размерам матрицы!");
        }
        return new Vector(row[i]);
    }

    public void setRow(int i, Vector v) {
        if (i < 0 || i >= row.length || v.getSize() != getColumnsCount()) {
            throw new ArrayIndexOutOfBoundsException("Индекс вектора не соответствует размерам матрицы!");
        }
        row[i] = new Vector(v);
    }

    //через toString векторов
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("{");
        for (int i = 0; i < row.length; i++) {
            if (i > 0) {
                str.append(",");
            }
            str.append(row[i].toString());
        }
        return str.append("}").toString();
    }

    public void transposition() {
        int rowCount = row.length;
        Vector[] V = new Vector[rowCount];
        for (int i = 0; i < rowCount; i++) {
            V[i] = getColumn(i);
        }
        row = V;
    }

    public Vector getColumn(int i) {
        if (i < 0 || i >= row.length) {
            throw new ArrayIndexOutOfBoundsException("Индекс вектора не соответствует размерам матрицы!");
        }
        double[] tmpV = new double[row.length];
        for (int j = 0; j < row.length; j++) {
            tmpV[j] = row[j].getElement(i);
        }
        return new Vector(tmpV);
    }

    public void multiplicationByScalar(int s) {
        for (Vector v : row) {
            v.multiplyByScalar(s);
        }
    }

    public double determinant() {
        int rowCount = row.length;
        int colCount = getColumnsCount();

        if (colCount != rowCount) {
            throw new IllegalArgumentException("Матрица должна быть квадратного вида");
        }

        if (colCount == 1) {
            return row[0].getElement(0);
        }

        if (colCount == 2) {
            return row[0].getElement(0) * row[1].getElement(1) - row[0].getElement(1) * row[1].getElement(0);
        }

        if (row[0].getElement(0) == 0) {
            int notZeroFirstPosRow = 0;
            for (int i = 1; i < rowCount; i++) {
                if (row[i].getElement(0) == 0) {
                    continue;
                }
                notZeroFirstPosRow = i;
                break;
            }

            if (notZeroFirstPosRow == 0) {
                return 0;
            } else {
                for (int i = 0; i < colCount; i++) {
                    row[0].setElement(i, row[0].getElement(i) + row[notZeroFirstPosRow].getElement(i));
                }
            }
        }


        for (int j = 1; j < rowCount; j++) {
            double rowRatio = -row[j].getElement(0) / row[0].getElement(0);
            for (int i = 0; i < colCount; i++) {
                double tmp = row[j].getElement(i) + row[0].getElement(i) * rowRatio;
                row[j].setElement(i, tmp);
                double epsilon = 1.0e-10;
                if (Math.abs(row[j].getElement(i)) < epsilon) {
                    row[j].setElement(i, 0);
                }
            }
        }

        double[][] arrayTmp = new double[rowCount - 1][colCount - 1];
        for (int j = 0; j < rowCount - 1; j++) {
            for (int i = 0; i < colCount - 1; i++) {
                arrayTmp[j][i] = row[j + 1].getElement(i + 1);
            }
        }

        Matrix m = new Matrix(arrayTmp);
        return row[0].getElement(0) * m.determinant();
    }

    public Vector multiplicationByVector(Vector v) {
        int rowCount = row.length;
        int colCount = getColumnsCount();
        if (rowCount != v.getSize()) {
            throw new IllegalArgumentException("Длина вектора должна быть равна числу столбцов в матрице!");
        }
        double[] arr = new double[rowCount];
        for (int j = 0; j < rowCount; j++) {
            double tmp = 0;
            for (int i = 0; i < colCount; i++) {
                tmp += row[j].getElement(i) * v.getElement(i);
            }
            arr[j] = tmp;
        }
        return new Vector(arr);
    }

    public void addMatrix(Matrix m1) {
        int rowCount = row.length;
        int colCount = getColumnsCount();
        int lengthM1 = m1.row.length;
        int colM1 = m1.getColumnsCount();
        if (rowCount != lengthM1 || colCount != colM1) {
            throw new IllegalArgumentException("Размеры матриц должны совпадать!");
        }
        for (int j = 0; j < rowCount; j++) {
            for (int i = 0; i < colCount; i++) {
                double tmp = row[j].getElement(i) + m1.row[j].getElement(i);
                row[j].setElement(i, tmp);
            }
        }
    }

    public void difMatrix(Matrix m1) {
        int rowCount = row.length;
        int colCount = getColumnsCount();
        int rowCountM1 = m1.row.length;
        int colCountM1 = m1.getColumnsCount();
        if (rowCount != rowCountM1 || colCount != colCountM1) {
            throw new IllegalArgumentException("Размеры матриц должны совпадать!");
        }
        for (int j = 0; j < rowCount; j++) {
            for (int i = 0; i < colCount; i++) {
                double tmp = row[j].getElement(i) - m1.row[j].getElement(i);
                row[j].setElement(i, tmp);
            }
        }
    }

    public static Matrix additionMatrix(Matrix m1, Matrix m2) {
        int rowCountM1 = m1.row.length;
        int colCountM1 = m1.getColumnsCount();
        int rowCountM2 = m2.row.length;
        int colCountM2 = m2.getColumnsCount();
        if (rowCountM2 != rowCountM1 || colCountM2 != colCountM1) {
            throw new IllegalArgumentException("Размеры матриц должны совпадать!");
        }
        Matrix m = new Matrix(m1);
        m.addMatrix(m2);
        return m;
    }

    public static Matrix subtractMatrix(Matrix m1, Matrix m2) {
        int rowCountM1 = m1.row.length;
        int colCountM1 = m1.getColumnsCount();
        int rowCountM2 = m2.row.length;
        int colCountM2 = m2.getColumnsCount();
        if (rowCountM2 != rowCountM1 || colCountM2 != colCountM1) {
            throw new IllegalArgumentException("Размеры матриц должны совпадать!");
        }
        Matrix m = new Matrix(m1);
        m.difMatrix(m2);
        return m;
    }

    public static Matrix multiplicationMatrix(Matrix m, Matrix m1) {
        int rowCountM1 = m.row.length;
        int colCountM1 = m.getColumnsCount();
        int colCountM2 = m1.getColumnsCount();
        if (rowCountM1 != colCountM2) {
            throw new IllegalArgumentException("Количество столбцов первой матрицы должно быть равно количеству строк второй матрицы!");
        }
        double[][] tmpArray = new double[rowCountM1][colCountM2];
        for (int j = 0; j < rowCountM1; j++) {
            for (int i = 0; i < colCountM2; i++) {
                for (int k = 0; k < colCountM1; k++) {
                    tmpArray[j][i] += m.row[j].getElement(k) * m1.row[k].getElement(i);
                }
            }
        }
        return new Matrix(tmpArray);
    }
}
