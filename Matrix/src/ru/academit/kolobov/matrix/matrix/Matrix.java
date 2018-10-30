package ru.academit.kolobov.matrix.matrix;

import ru.academit.kolobov.vector.Vector.Vector;

import java.util.Arrays;

public class Matrix {
    private Vector[] array;

    public Matrix(Vector[] vectors) {
        int y = vectors.length;
        array = new Vector[y];
        for (int i = 0; i < y; i++) {
            array[i] = vectors[i];
        }
    }

    public Matrix(int n, int m) {
        if (n <= 0 || m <= 0) {
            throw new IllegalArgumentException("Размер массив 0 или отрицательный");
        }
        array = new Vector[n];
        for (int i = 0; i < n; i++) {
            array[i] = new Vector(m);
        }
    }

    public Matrix(Matrix matrix) {
        array = matrix.array;
    }

    public Matrix(double[][] a) {
        int y = a.length;
        int x = a[0].length;
        if (x <= 0 || y <= 0) {
            throw new IllegalArgumentException("Размер массив 0 или отрицательный");
        }
        array = new Vector[y];
        for (int i = 0; i < y; i++) {
            double[] m = new double[x];
            for (int j = 0; j < x; j++) {
                m[j] = a[i][j];
            }
            array[i] = new Vector(m);
        }
    }

    public int getSizeX(Matrix matrix) {
        return matrix.array[0].getSize();
    }

    public int getSizeY(Matrix matrix) {
        return matrix.array.length;
    }

    public Vector getVector(int i) {
        if (i > array.length - 1) {
            System.out.println("Индекс вектора больше длины матрицы!");
            return null;
        }
        return new Vector(array[i]);
    }

    @Override
    public String toString() {
        return Arrays.deepToString(array).replaceAll("\\[", "{").replaceAll("]", "}");
    }

    public Matrix getTransposition() {
        int y = array.length;
        int x = array[0].getSize();
        double[][] arrayTrp = new double[y][x];
        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {
                arrayTrp[i][j] = array[j].getElement(i);
            }
        }
        return new Matrix(arrayTrp);
    }

    public Vector getVectorColumn(int i) {
        Matrix m = getTransposition();
        return m.getVector(i);
    }

    public void multiplicationByScalar(int s) {
        int y = array.length;
        int x = array[0].getSize();
        for (int j = 0; j < y; j++) {
            for (int k = 0; k < x; k++) {
                array[j].setElement(k, array[j].getElement(k) * s);
            }
        }
    }

    public double determinant() {
        int y = array.length;
        int x = array[0].getSize();

        if (x != y) {
            System.out.println("Матрица должна быть квадратного вида");
            return 0;
        }

        if (x == 2) {
            return array[0].getElement(0) * array[1].getElement(1) - array[0].getElement(1) * array[1].getElement(0);
        }

        if (array[0].getElement(0) == 0) {
            int notZeroFirstPosRow = 0;
            for (int i = 1; i < y; i++) {
                if (array[i].getElement(0) == 0) {
                    continue;
                }
                notZeroFirstPosRow = i;
                break;
            }

            if (notZeroFirstPosRow == 0) {
                return 0;
            } else {
                for (int i = 0; i < x; i++) {
                    array[0].setElement(i, array[0].getElement(i) + array[notZeroFirstPosRow].getElement(i));
                }
            }
        }

        for (int j = 1; j < y; j++) {
            double rowRatio = -array[j].getElement(0) / array[0].getElement(0);
            for (int i = 0; i < x; i++) {
                double tmp = array[j].getElement(i) + array[0].getElement(i) * rowRatio;
                array[j].setElement(i, tmp);
                double epsilon = 1.0e-10;
                if (Math.abs(array[j].getElement(i)) < epsilon) {
                    array[j].setElement(i, 0);
                }
            }
        }

        double[][] arrayTmp = new double[y - 1][x - 1];
        for (int j = 0; j < y - 1; j++) {
//            System.arraycopy(array[j + 1], 1, arrayTmp[j], 0, x - 1);
            for (int i = 0; i < x - 1; i++) {
                arrayTmp[j][i] = array[j + 1].getElement(i + 1);
            }
        }

        Matrix m = new Matrix(arrayTmp);
        //как сделать так что бы в методе -0 менялся на 0???
        return array[0].getElement(0) * m.determinant();
    }

    public Vector multiplicationByVector(Vector v2) {
        int y = array.length;
        int x = array[0].getSize();
        if (y != v2.getSize()) {
            System.out.println("Длина вектора должна быть равна числу строк в матрице!");
            return null;
        }
        double[] arr = new double[y];
        for (int j = 0; j < y; j++) {
            double tmp = 0;
            for (int i = 0; i < x; i++) {
                tmp += array[j].getElement(i) * v2.getElement(i);
            }
            arr[j] = tmp;
        }
        return new Vector(arr);
    }

//    public void addMatrix(Matrix m1) {
//        int y = array.length;
//        int x = array[0].length;
//        int y1 = m1.array.length;
//        int x1 = m1.array[0].length;
//        if (y != y1 || x != x1) {
//            throw new IllegalArgumentException("Размеры матриц должны совпадать!");
//        }
//        for (int j = 0; j < y; j++) {
//            for (int i = 0; i < x; i++) {
//                array[j][i] += m1.array[j][i];
//            }
//        }
//    }
//
//    public void difMatrix(Matrix m1) {
//        int y = array.length;
//        int x = array[0].length;
//        int y1 = m1.array.length;
//        int x1 = m1.array[0].length;
//        if (y != y1 || x != x1) {
//            throw new IllegalArgumentException("Размеры матриц должны совпадать!");
//        }
//        for (int j = 0; j < y; j++) {
//            for (int i = 0; i < x; i++) {
//                array[j][i] -= m1.array[j][i];
//            }
//        }
//    }
//
//    public static Matrix additionMatrix(Matrix m1, Matrix m2) {
//        Matrix m = m1;
//        m.addMatrix(m2);
//        return m;
//    }
//
//    public static Matrix subtractMatrix(Matrix m1, Matrix m2) {
//        Matrix m = m1;
//        m.difMatrix(m2);
//        return m;
//    }
//
//    public static Matrix matrixMultiplication(Matrix m, Matrix m1) {
//        int y = m.array.length;
//        int x = m.array[0].length;
//        int x1 = m1.array[0].length;
//        if (y != x1) {
//            throw new IllegalArgumentException("Количество столбцов первой матрицы должно быть равно количеству строк второй матрицы!");
//        }
//        double[][] tmpArray = new double[y][x1];
//        for (int j = 0; j < y; j++) {
//            for (int i = 0; i < x1; i++) {
//                for (int k = 0; k < x; k++) {
//                    tmpArray[j][i] += m.array[j][k] * m1.array[k][i];
//                }
//            }
//        }
//        return new Matrix(tmpArray);
//    }
}
