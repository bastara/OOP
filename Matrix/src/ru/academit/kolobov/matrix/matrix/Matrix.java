package ru.academit.kolobov.matrix.matrix;

import ru.academit.kolobov.vector.Vector.Vector;

import java.util.Arrays;

public class Matrix {
    private Vector[] matrixOfRow;

    public Matrix(Vector[] vectors) {
        int col = 0;
        for (Vector v : vectors) {
            if (v.getSize() > col) {
                col = v.getSize();
            }
        }
        int row = vectors.length;
        matrixOfRow = new Vector[row];
        for (int j = 0; j < row; j++) {
            matrixOfRow[j] = new Vector(col);
            int size = vectors[j].getSize();
            for (int i = 0; i < size; i++) {
                matrixOfRow[j].setElement(i, vectors[j].getElement(i));
            }
        }
    }

    public Matrix(int row, int col) {
        if (row <= 0 || col <= 0) {
            throw new IllegalArgumentException("Размер массив 0 или отрицательный");
        }
        matrixOfRow = new Vector[row];
        for (int i = 0; i < row; i++) {
            matrixOfRow[i] = new Vector(col);
        }
    }

    public Matrix(Matrix matrix) {
        matrixOfRow = new Vector[matrix.matrixOfRow.length];
        for (int i = 0; i < matrix.matrixOfRow.length; i++) {
            matrixOfRow[i] = matrix.getRow(i);
        }
    }

    public Matrix(double[][] a) {
        int row = a.length;
        int col = 0;
        for (double[] r : a) {
            if (col < r.length) {
                col = r.length;
            }
        }
        if (row == 0 || col == 0) {
            throw new IllegalArgumentException("Размер массив 0");
        }
        matrixOfRow = new Vector[row];
        for (int i = 0; i < row; i++) {
            double[] m = Arrays.copyOf(a[i], col);
            matrixOfRow[i] = new Vector(m);
        }
    }

    public int getColumnsCount() {
        return matrixOfRow[0].getSize();
    }

    public int getRowsCount() {
        return matrixOfRow.length;
    }

    public Vector getRow(int i) {
        if (i < 0 || i >= matrixOfRow.length) {
            throw new ArrayIndexOutOfBoundsException("Индекс вектора не соответствует размерам матрицы!");
        }
        return new Vector(matrixOfRow[i]);
    }

    //как быть когда может встретиться 2 одинаковых исключения?
    public void setRow(int i, Vector v) {
        if (i < 0 || i >= matrixOfRow.length) {
            throw new ArrayIndexOutOfBoundsException("Индекс вектора не соответствует размерам матрицы!");
        }
        if (v.getSize() != this.getColumnsCount()) {
            throw new ArrayIndexOutOfBoundsException("Размер вектора отличается от размера матрицы!");
        }
        matrixOfRow[i] = new Vector(v);
    }

    //через toString векторов
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("{");
        for (int i = 0; i < matrixOfRow.length; i++) {
            if (i > 0) {
                str.append(",");
            }
            str.append(matrixOfRow[i].toString());
        }
        return str.append("}").toString();
    }

    public void transposition() {
        int row = matrixOfRow.length;
        Vector[] V = new Vector[row];
        for (int i = 0; i < row; i++) {
            V[i] = getColumn(i);
        }
        matrixOfRow = V;
    }

    public Vector getColumn(int i) {//можно конечно 2 раза транспонировать, но я так понимаю что это тоже не правильно?
        if (i < 0 || i >= matrixOfRow.length) {
            throw new ArrayIndexOutOfBoundsException("Индекс вектора не соответствует размерам матрицы!");
        }
        double[] tmpV = new double[matrixOfRow.length];
        for (int j = 0; j < matrixOfRow.length; j++) {
            tmpV[j] = matrixOfRow[j].getElement(i);
        }
        return new Vector(tmpV);
    }

    public void multiplicationByScalar(int s) {
        for (Vector v : matrixOfRow) {
            v.multiplyByScalar(s);
        }
    }

    public double determinant() {
        int row = matrixOfRow.length;
        int col = matrixOfRow[0].getSize();

        if (col != row) {
            throw new IllegalArgumentException("Матрица должна быть квадратного вида");
        }

        if (col == 1) {
            return matrixOfRow[0].getElement(0);
        }

        if (col == 2) {
            return matrixOfRow[0].getElement(0) * matrixOfRow[1].getElement(1) - matrixOfRow[0].getElement(1) * matrixOfRow[1].getElement(0);
        }

        if (matrixOfRow[0].getElement(0) == 0) {
            int notZeroFirstPosRow = 0;
            for (int i = 1; i < row; i++) {
                if (matrixOfRow[i].getElement(0) == 0) {
                    continue;
                }
                notZeroFirstPosRow = i;
                break;
            }

            if (notZeroFirstPosRow == 0) {
                return 0;
            } else {
                for (int i = 0; i < col; i++) {
                    matrixOfRow[0].setElement(i, matrixOfRow[0].getElement(i) + matrixOfRow[notZeroFirstPosRow].getElement(i));
                }
            }
        }

        for (int j = 1; j < row; j++) {
            double rowRatio = -matrixOfRow[j].getElement(0) / matrixOfRow[0].getElement(0);
            for (int i = 0; i < col; i++) {
                double tmp = matrixOfRow[j].getElement(i) + matrixOfRow[0].getElement(i) * rowRatio;
                matrixOfRow[j].setElement(i, tmp);
                double epsilon = 1.0e-10;
                if (Math.abs(matrixOfRow[j].getElement(i)) < epsilon) {
                    matrixOfRow[j].setElement(i, 0);
                }
            }
        }

        double[][] arrayTmp = new double[row - 1][col - 1];
        for (int j = 0; j < row - 1; j++) {
            for (int i = 0; i < col - 1; i++) {
                arrayTmp[j][i] = matrixOfRow[j + 1].getElement(i + 1);
            }
        }

        Matrix m = new Matrix(arrayTmp);
        return matrixOfRow[0].getElement(0) * m.determinant();
    }

    public Vector multiplicationByVector(Vector v2) {
        int row = matrixOfRow.length;
        int col = matrixOfRow[0].getSize();
        if (row != v2.getSize()) {
            throw new IllegalArgumentException("Длина вектора должна быть равна числу строк в матрице!");
        }
        double[] arr = new double[row];
        for (int j = 0; j < row; j++) {
            double tmp = 0;
            for (int i = 0; i < col; i++) {
                tmp += matrixOfRow[j].getElement(i) * v2.getElement(i);
            }
            arr[j] = tmp;
        }
        return new Vector(arr);
    }

    public void addMatrix(Matrix m1) {
        int row = matrixOfRow.length;
        int col = matrixOfRow[0].getSize();
        int row1 = m1.matrixOfRow.length;
        int col1 = m1.matrixOfRow[0].getSize();
        if (row != row1 || col != col1) {
            throw new IllegalArgumentException("Размеры матриц должны совпадать!");
        }
        for (int j = 0; j < row; j++) {
            for (int i = 0; i < col; i++) {
                double tmp = matrixOfRow[j].getElement(i) + m1.matrixOfRow[j].getElement(i);
                matrixOfRow[j].setElement(i, tmp);
            }
        }
    }

    public void difMatrix(Matrix m1) {
        int row = matrixOfRow.length;
        int col = matrixOfRow[0].getSize();
        int row1 = m1.matrixOfRow.length;
        int col1 = m1.matrixOfRow[0].getSize();
        if (row != row1 || col != col1) {
            throw new IllegalArgumentException("Размеры матриц должны совпадать!");
        }
        for (int j = 0; j < row; j++) {
            for (int i = 0; i < col; i++) {
                double tmp = matrixOfRow[j].getElement(i) - m1.matrixOfRow[j].getElement(i);
                matrixOfRow[j].setElement(i, tmp);
            }
        }
    }

    public static Matrix additionMatrix(Matrix m1, Matrix m2) {
        int row1 = m1.matrixOfRow.length;
        int col1 = m1.matrixOfRow[0].getSize();
        int row2 = m2.matrixOfRow.length;
        int col2 = m2.matrixOfRow[0].getSize();
        if (row2 != row1 || col2 != col1) {
            throw new IllegalArgumentException("Размеры матриц должны совпадать!");
        }
        Matrix m = new Matrix(m1);
        m.addMatrix(m2);
        return m;
    }

    public static Matrix subtractMatrix(Matrix m1, Matrix m2) {
        int row1 = m1.matrixOfRow.length;
        int col1 = m1.matrixOfRow[0].getSize();
        int row2 = m2.matrixOfRow.length;
        int col2 = m2.matrixOfRow[0].getSize();
        if (row2 != row1 || col2 != col1) {
            throw new IllegalArgumentException("Размеры матриц должны совпадать!");
        }
        Matrix m = new Matrix(m1);
        m.difMatrix(m2);
        return m;
    }

    public static Matrix multiplicationMatrix(Matrix m, Matrix m1) {
        int row = m.matrixOfRow.length;
        int col = m.matrixOfRow[0].getSize();
        int row1 = m1.matrixOfRow.length;
        int col1 = m1.matrixOfRow[0].getSize();
        if (row != col1 || row1 != col) {
            throw new IllegalArgumentException("Количество столбцов первой матрицы должно быть равно количеству строк второй матрицы!");
        }
        double[][] tmpArray = new double[row][col1];
        for (int j = 0; j < row; j++) {
            for (int i = 0; i < col1; i++) {
                for (int k = 0; k < col; k++) {
                    tmpArray[j][i] += m.matrixOfRow[j].getElement(k) * m1.matrixOfRow[k].getElement(i);
                }
            }
        }
        return new Matrix(tmpArray);
    }
}
