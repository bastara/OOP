package ru.academit.kolobov.matrix.matrix;

import ru.academit.kolobov.vector.Vector.Vector;

import java.util.Arrays;

public class Matrix {
    private Vector[] arrayV;

    public Matrix(Vector[] vectors) {
        int y = vectors.length;
        arrayV = Arrays.copyOf(vectors, y);
    }

    public Matrix(int n, int m) {
        if (n <= 0 || m <= 0) {
            throw new IllegalArgumentException("Размер массив 0 или отрицательный");
        }
        arrayV = new Vector[n];
        for (int i = 0; i < n; i++) {
            arrayV[i] = new Vector(m);
        }
    }

    public Matrix(Matrix matrix) {
        arrayV = matrix.arrayV;
    }

    public Matrix(double[][] a) {
        int y = a.length;
        int x = a[0].length;
        if (x == 0 || y == 0) {
            throw new IllegalArgumentException("Размер массив 0");
        }
        arrayV = new Vector[y];
        for (int i = 0; i < y; i++) {
            double[] m = Arrays.copyOf(a[i], x);
            arrayV[i] = new Vector(m);
        }
    }

    public int getSizeX(Matrix matrix) {
        return matrix.arrayV[0].getSize();
    }

    public int getSizeY(Matrix matrix) {
        return matrix.arrayV.length;
    }

    public Vector getVector(int i) {
        if (i > arrayV.length - 1) {
            System.out.println("Индекс вектора больше длины матрицы!");
            return null;
        }
        return new Vector(arrayV[i]);
    }

    public void setVector(int i, Vector v) {
        arrayV[i] = v;
    }

// переопределил 2мя вариантами
//    @Override
//    public String toString() {
//        return Arrays.deepToString(arrayV).replaceAll("\\[", "{").replaceAll("]", "}");
//    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("{");
        for (int i = 0; i < arrayV.length; i++) {
            if (i > 0) {
                str.append(",");
            }
            str.append("{");
            for (int j = 0; j < arrayV[0].getSize() - 1; j++) {
                str.append(arrayV[i].getElement(j)).append(", ");
            }
            str.append(arrayV[i].getElement(arrayV[0].getSize() - 1)).append("}");
        }
        return str.append("}").toString();
    }

    public Matrix getTransposition() {
        int y = arrayV.length;
        int x = arrayV[0].getSize();
        double[][] arrayTrp = new double[y][x];
        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {
                arrayTrp[i][j] = arrayV[j].getElement(i);
            }
        }
        return new Matrix(arrayTrp);
    }

    public Vector getVectorColumn(int i) {
        Matrix m = getTransposition();
        return m.getVector(i);
    }

    public void multiplicationByScalar(int s) {
        int x = arrayV[0].getSize();
        for (Vector v : arrayV) {
            for (int k = 0; k < x; k++) {
                v.setElement(k, v.getElement(k) * s);
            }
        }
    }

    public double determinant() {
        int y = arrayV.length;
        int x = arrayV[0].getSize();

        if (x != y) {
            System.out.println("Матрица должна быть квадратного вида");
            return 0;
        }

        if (x == 2) {
            return arrayV[0].getElement(0) * arrayV[1].getElement(1) - arrayV[0].getElement(1) * arrayV[1].getElement(0);
        }

        if (arrayV[0].getElement(0) == 0) {
            int notZeroFirstPosRow = 0;
            for (int i = 1; i < y; i++) {
                if (arrayV[i].getElement(0) == 0) {
                    continue;
                }
                notZeroFirstPosRow = i;
                break;
            }

            if (notZeroFirstPosRow == 0) {
                return 0;
            } else {
                for (int i = 0; i < x; i++) {
                    arrayV[0].setElement(i, arrayV[0].getElement(i) + arrayV[notZeroFirstPosRow].getElement(i));
                }
            }
        }

        for (int j = 1; j < y; j++) {
            double rowRatio = -arrayV[j].getElement(0) / arrayV[0].getElement(0);
            for (int i = 0; i < x; i++) {
                double tmp = arrayV[j].getElement(i) + arrayV[0].getElement(i) * rowRatio;
                arrayV[j].setElement(i, tmp);
                double epsilon = 1.0e-10;
                if (Math.abs(arrayV[j].getElement(i)) < epsilon) {
                    arrayV[j].setElement(i, 0);
                }
            }
        }

        double[][] arrayTmp = new double[y - 1][x - 1];
        for (int j = 0; j < y - 1; j++) {
            for (int i = 0; i < x - 1; i++) {
                arrayTmp[j][i] = arrayV[j + 1].getElement(i + 1);
            }
        }

        Matrix m = new Matrix(arrayTmp);
        return arrayV[0].getElement(0) * m.determinant();
    }

    public Vector multiplicationByVector(Vector v2) {
        int y = arrayV.length;
        int x = arrayV[0].getSize();
        if (y != v2.getSize()) {
            System.out.println("Длина вектора должна быть равна числу строк в матрице!");
            return null;
        }
        double[] arr = new double[y];
        for (int j = 0; j < y; j++) {
            double tmp = 0;
            for (int i = 0; i < x; i++) {
                tmp += arrayV[j].getElement(i) * v2.getElement(i);
            }
            arr[j] = tmp;
        }
        return new Vector(arr);
    }

    public void addMatrix(Matrix m1) {
        int y = arrayV.length;
        int x = arrayV[0].getSize();
        int y1 = m1.arrayV.length;
        int x1 = m1.arrayV[0].getSize();
        if (y != y1 || x != x1) {
            throw new IllegalArgumentException("Размеры матриц должны совпадать!");
        }
        for (int j = 0; j < y; j++) {
            for (int i = 0; i < x; i++) {
                double tmp = arrayV[j].getElement(i) + m1.arrayV[j].getElement(i);
                arrayV[j].setElement(i, tmp);
            }
        }
    }

    public void difMatrix(Matrix m1) {
        int y = arrayV.length;
        int x = arrayV[0].getSize();
        int y1 = m1.arrayV.length;
        int x1 = m1.arrayV[0].getSize();
        if (y != y1 || x != x1) {
            throw new IllegalArgumentException("Размеры матриц должны совпадать!");
        }
        for (int j = 0; j < y; j++) {
            for (int i = 0; i < x; i++) {
                double tmp = arrayV[j].getElement(i) - m1.arrayV[j].getElement(i);
                arrayV[j].setElement(i, tmp);
            }
        }
    }

    public static Matrix additionMatrix(Matrix m1, Matrix m2) {
        Matrix m = new Matrix(m1);
        m.addMatrix(m2);
        return m;
    }

    public static Matrix subtractMatrix(Matrix m1, Matrix m2) {
        Matrix m = new Matrix(m1);
        m.difMatrix(m2);
        return m;
    }

    public static Matrix matrixMultiplication(Matrix m, Matrix m1) {
        int y = m.arrayV.length;
        int x = m.arrayV[0].getSize();
        int x1 = m1.arrayV[0].getSize();
        if (y != x1) {
            throw new IllegalArgumentException("Количество столбцов первой матрицы должно быть равно количеству строк второй матрицы!");
        }
        double[][] tmpArray = new double[y][x1];
        for (int j = 0; j < y; j++) {
            for (int i = 0; i < x1; i++) {
                for (int k = 0; k < x; k++) {
                    tmpArray[j][i] += m.arrayV[j].getElement(k) * m1.arrayV[k].getElement(i);
                }
            }
        }
        return new Matrix(tmpArray);
    }
}
