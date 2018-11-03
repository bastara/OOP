package ru.academit.kolobov.matrix.matrix;

import ru.academit.kolobov.vector.Vector.Vector;

import java.util.Arrays;

public class Matrix {
    private Vector[] matrixOfVector;

    public Matrix(Vector[] vectors) {
        int col = 0;
        for (Vector v : vectors) {
            if (v.getSize() > col) {
                col = v.getSize();
            }
        }
        int row = vectors.length;
        matrixOfVector = new Vector[row];
        for (int j = 0; j < row; j++) {
            matrixOfVector[j] = new Vector(col);
            int size = vectors[j].getSize();//обратил внимание что метод вызывается каждый раз, решил сделать переменную, наверное так лучше будет?
            for (int i = 0; i < size; i++) {
                matrixOfVector[j].setElement(i, vectors[j].getElement(i));
            }
        }
    }

    public Matrix(int row, int col) {
        if (row <= 0 || col <= 0) {
            throw new IllegalArgumentException("Размер массив 0 или отрицательный");
        }
        matrixOfVector = new Vector[row];
        for (int i = 0; i < row; i++) {
            matrixOfVector[i] = new Vector(col);
        }
    }

    public Matrix(Matrix matrix) {
        matrixOfVector = new Vector[matrix.matrixOfVector.length];
        for (int i = 0; i < matrix.matrixOfVector.length; i++) {
            matrixOfVector[i] = matrix.getRow(i);
        }
    }

    public Matrix(double[][] a) {
        int row = a.length;
        //считаю длину массива, предполагая что длина не одного размера в строках.
        //не нашел ситуации когда бы длина могла бы варироваться в массиве.
        int col = 0;
        for (double[] r : a) {
            if (col < r.length) {
                col = r.length;
            }
        }
        if (col == 0 || row == 0) {
            throw new IllegalArgumentException("Размер массив 0");
        }
        matrixOfVector = new Vector[row];
        for (int i = 0; i < row; i++) {
            double[] m = Arrays.copyOf(a[i], col);
            matrixOfVector[i] = new Vector(m);
        }
    }

    public int getColumnsCount() {
        return matrixOfVector[0].getSize();
    }

    public int getRowsCount() {
        return matrixOfVector.length;
    }

    public Vector getRow(int i) {
        if (i < 0 || i > matrixOfVector.length - 1) {
            throw new IllegalArgumentException("Индекс вектора не соответствует размерам матрицы!");
        }
        return new Vector(matrixOfVector[i]);
    }

    //как быть когда может встретиться 2 одинаковых исключения?
    public void setRow(int i, Vector v) {
        if (i < 0 || i > matrixOfVector.length - 1) {
            throw new IllegalArgumentException("Индекс вектора не соответствует размерам матрицы!");
        }
        if (v.getSize() != this.getColumnsCount()) {
            throw new IllegalArgumentException("Размер вектора отличается от размера матрицы!");
        }
        matrixOfVector[i] = new Vector(v);
    }

    //через toString векторов
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("{");
        for (int i = 0; i < matrixOfVector.length; i++) {
            if (i > 0) {
                str.append(",");
            }
            str.append(matrixOfVector[i].toString());
        }
        return str.append("}").toString();
    }

    public void transposition() {
        int row = matrixOfVector.length;
        int col = matrixOfVector[0].getSize();
        Matrix m = new Matrix(col, row);
        for (int i = 0; i < col; i++) {
            for (int j = 0; j < row; j++) {
                m.matrixOfVector[i].setElement(j, matrixOfVector[j].getElement(i));
            }
        }
        matrixOfVector = m.matrixOfVector;
    }

    public Vector getVectorColumn(int i) {//можно конечно 2 раза транспонировать, но я так понимаю что это тоже не правильно?
        double[] tmpV = new double[matrixOfVector.length];
        for (int j = 0; j < matrixOfVector.length; j++) {
            tmpV[j] = matrixOfVector[j].getElement(i);
        }
        return new Vector(tmpV);
    }

    public void multiplicationByScalar(int s) {
        int col = matrixOfVector[0].getSize();
        for (Vector v : matrixOfVector) {
            for (int k = 0; k < col; k++) {
                v.setElement(k, v.getElement(k) * s);
            }
        }
    }

    public double determinant() {
        int row = matrixOfVector.length;
        int col = matrixOfVector[0].getSize();

        if (col != row) {
            throw new IllegalArgumentException("Матрица должна быть квадратного вида");
        }

        if (col == 2) {
            return matrixOfVector[0].getElement(0) * matrixOfVector[1].getElement(1) - matrixOfVector[0].getElement(1) * matrixOfVector[1].getElement(0);
        }

        if (matrixOfVector[0].getElement(0) == 0) {
            int notZeroFirstPosRow = 0;
            for (int i = 1; i < row; i++) {
                if (matrixOfVector[i].getElement(0) == 0) {
                    continue;
                }
                notZeroFirstPosRow = i;
                break;
            }

            if (notZeroFirstPosRow == 0) {
                return 0;
            } else {
                for (int i = 0; i < col; i++) {
                    matrixOfVector[0].setElement(i, matrixOfVector[0].getElement(i) + matrixOfVector[notZeroFirstPosRow].getElement(i));
                }
            }
        }

        for (int j = 1; j < row; j++) {
            double rowRatio = -matrixOfVector[j].getElement(0) / matrixOfVector[0].getElement(0);
            for (int i = 0; i < col; i++) {
                double tmp = matrixOfVector[j].getElement(i) + matrixOfVector[0].getElement(i) * rowRatio;
                matrixOfVector[j].setElement(i, tmp);
                double epsilon = 1.0e-10;
                if (Math.abs(matrixOfVector[j].getElement(i)) < epsilon) {
                    matrixOfVector[j].setElement(i, 0);
                }
            }
        }

        double[][] arrayTmp = new double[row - 1][col - 1];
        for (int j = 0; j < row - 1; j++) {
            for (int i = 0; i < col - 1; i++) {
                arrayTmp[j][i] = matrixOfVector[j + 1].getElement(i + 1);
            }
        }

        Matrix m = new Matrix(arrayTmp);
        return matrixOfVector[0].getElement(0) * m.determinant();
    }

    public Vector multiplicationByVector(Vector v2) {
        int row = matrixOfVector.length;
        int col = matrixOfVector[0].getSize();
        if (row != v2.getSize()) {
            throw new IllegalArgumentException("Длина вектора должна быть равна числу строк в матрице!");
        }
        double[] arr = new double[row];
        for (int j = 0; j < row; j++) {
            double tmp = 0;
            for (int i = 0; i < col; i++) {
                tmp += matrixOfVector[j].getElement(i) * v2.getElement(i);
            }
            arr[j] = tmp;
        }
        return new Vector(arr);
    }

    public void addMatrix(Matrix m1) {
        int row = matrixOfVector.length;
        int col = matrixOfVector[0].getSize();
        int row1 = m1.matrixOfVector.length;
        int col1 = m1.matrixOfVector[0].getSize();
        if (row != row1 || col != col1) {
            throw new IllegalArgumentException("Размеры матриц должны совпадать!");
        }
        for (int j = 0; j < row; j++) {
            for (int i = 0; i < col; i++) {
                double tmp = matrixOfVector[j].getElement(i) + m1.matrixOfVector[j].getElement(i);
                matrixOfVector[j].setElement(i, tmp);
            }
        }
    }

    public void difMatrix(Matrix m1) {
        int row = matrixOfVector.length;
        int col = matrixOfVector[0].getSize();
        int row1 = m1.matrixOfVector.length;
        int col1 = m1.matrixOfVector[0].getSize();
        if (row != row1 || col != col1) {
            throw new IllegalArgumentException("Размеры матриц должны совпадать!");
        }
        for (int j = 0; j < row; j++) {
            for (int i = 0; i < col; i++) {
                double tmp = matrixOfVector[j].getElement(i) - m1.matrixOfVector[j].getElement(i);
                matrixOfVector[j].setElement(i, tmp);
            }
        }
    }

    public static Matrix additionMatrix(Matrix m1, Matrix m2) {
        int row1 = m1.matrixOfVector.length;
        int col1 = m1.matrixOfVector[0].getSize();
        int row2 = m2.matrixOfVector.length;
        int col2 = m2.matrixOfVector[0].getSize();
        if (row2 != row1 || col2 != col1) {
            throw new IllegalArgumentException("Размеры матриц должны совпадать!");
        }
        Matrix m = new Matrix(m1);
        m.addMatrix(m2);
        return m;
    }

    public static Matrix subtractMatrix(Matrix m1, Matrix m2) {
        int row1 = m1.matrixOfVector.length;
        int col1 = m1.matrixOfVector[0].getSize();
        int row2 = m2.matrixOfVector.length;
        int col2 = m2.matrixOfVector[0].getSize();
        if (row2 != row1 || col2 != col1) {
            throw new IllegalArgumentException("Размеры матриц должны совпадать!");
        }
        Matrix m = new Matrix(m1);
        m.difMatrix(m2);
        return m;
    }

    public static Matrix multiplicationMatrix(Matrix m, Matrix m1) {
        int row = m.matrixOfVector.length;
        int col = m.matrixOfVector[0].getSize();
        int row1 = m1.matrixOfVector.length;
        int col1 = m1.matrixOfVector[0].getSize();
        if (row != col1 || row1 != col) {
            throw new IllegalArgumentException("Количество столбцов первой матрицы должно быть равно количеству строк второй матрицы!");
        }
        double[][] tmpArray = new double[row][col1];
        for (int j = 0; j < row; j++) {
            for (int i = 0; i < col1; i++) {
                for (int k = 0; k < col; k++) {
                    tmpArray[j][i] += m.matrixOfVector[j].getElement(k) * m1.matrixOfVector[k].getElement(i);
                }
            }
        }
        return new Matrix(tmpArray);
    }
}
