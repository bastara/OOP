package ru.academit.kolobov.matrix.matrix;

import ru.academit.kolobov.vector.Vector.Vector;

public class Matrix {
    private double[][] array;

    public Matrix(Vector[] vectors) {
        int y = vectors.length;
        int x = vectors[0].getSize();
        array = new double[y][x];
        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {
                array[i][j] = vectors[i].getElement(j);
            }
        }
    }

    public Matrix(int n, int m) {
        array = new double[n][m];
    }

    public Matrix(Matrix matrix) {
        int y = matrix.array.length;
        int x = matrix.array[0].length;
        array = new double[y][x];
        for (int i = 0; i < y; i++) {
            System.arraycopy(matrix.array[i], 0, array[i], 0, x);
        }
    }

    public Matrix(double[][] a) {
        int y = a.length;
        int x = a[0].length;
        array = new double[y][x];
        for (int i = 0; i < y; i++) {
            System.arraycopy(a[i], 0, array[i], 0, x);
        }
    }

    public int getSizeX(Matrix matrix) {
        return matrix.array[0].length;
    }

    public int getSizeY(Matrix matrix) {
        return matrix.array.length;
    }

    public Vector getVector(int i) {
        if (i > array.length-1) {
            System.out.println("Индекс вектора больше длины матрицы!");
            return null;
        }
        return new Vector(array[i]);
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("{");
        for (int i = 0; i < array.length; i++) {
            if (i > 0) {
                str.append(",");
            }
            str.append("{");
            for (int j = 0; j < array[0].length - 1; j++) {
                str.append(array[i][j]).append(", ");
            }
            str.append(array[i][array[0].length - 1]).append("}");
        }
        str.append("}");
        return str.toString();
    }

    public Matrix getTransposition() {
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
        Matrix m = getTransposition();
        return m.getVector(i);
    }

    public void multiplicationByScalar(int s) {
        int y = array.length;
        int x = array[0].length;
        for (int j = 0; j < y; j++) {
            for (int k = 0; k < x; k++) {
                array[j][k] *= s;
            }
        }
    }

    public double determinant() {
        int y = array.length;
        int x = array[0].length;

        if (x != y) {
            System.out.println("Матрица должна быть квадратного вида");
            return 0;
        }

        if (x == 2) {
            return array[0][0] * array[1][1] - array[0][1] * array[1][0];
        }

        if (array[0][0] == 0) {
            int notZeroFirstPosRow = 0;
            for (int i = 1; i < y; i++) {
                if (array[i][0] == 0) {
                    continue;
                }
                notZeroFirstPosRow = i;
                break;
            }

            if (notZeroFirstPosRow == 0) {
                return 0;
            } else {
                for (int i = 0; i < x; i++) {
                    array[0][i] += array[notZeroFirstPosRow][i];
                }
            }
        }

        for (int j = 1; j < y; j++) {
            double rowRatio = -array[j][0] / array[0][0];
            for (int i = 0; i < x; i++) {
                array[j][i] = array[j][i] + array[0][i] * rowRatio;
                double epsilon = 1.0e-10;
                if (Math.abs(array[j][i]) < epsilon) {
                    array[j][i] = 0;
                }
            }
        }

        double[][] arrayTmp = new double[y - 1][x - 1];
        for (int j = 0; j < y - 1; j++) {
            for (int i = 0; i < x - 1; i++) {
                arrayTmp[j][i] = array[j + 1][i + 1];
            }
        }
        Matrix m = new Matrix(arrayTmp);
        //как сделать так что бы в методе -0 менялся на 0???
        return array[0][0] * m.determinant();
    }

    public void multiplicationByVector(Vector v2) {
        int y = array.length;
        int x = array[0].length;
        if (y != v2.getSize()) {
            System.out.println("Длина вектора должна быть равна числу строк в матрице!");
            return;
        }
        for (int j = 0; j < y; j++) {
            for (int i = 0; i < x; i++) {
                array[j][i] *= v2.getElement(i);
            }
        }
    }

    public void addMatrix(Matrix m1) {
        int y = array.length;
        int x = array[0].length;
        int y1 = m1.array.length;
        int x1 = m1.array[0].length;
        if (y != y1 || x != x1) {
            throw new IllegalArgumentException("Размеры матриц должны совпадать!");
        }
        for (int j = 0; j < y; j++) {
            for (int i = 0; i < x; i++) {
                array[j][i] += m1.array[j][i];
            }
        }
    }

    public static Matrix matrixAddition(Matrix m, Matrix m1) {
        m.addMatrix(m1);
        return m;
    }

    public void difMatrix(Matrix m1) {
        int y = array.length;
        int x = array[0].length;
        int y1 = m1.array.length;
        int x1 = m1.array[0].length;
        if (y != y1 || x != x1) {
            throw new IllegalArgumentException("Размеры матриц должны совпадать!");
        }
        for (int j = 0; j < y; j++) {
            for (int i = 0; i < x; i++) {
                array[j][i] -= m1.array[j][i];
            }
        }
    }

    public static Matrix subtractMatrix(Matrix m1, Matrix m) {
    m1.difMatrix(m);
        return m1;
    }

    public static Matrix matrixMultiplication(Matrix m, Matrix m1) {
        int y = m.array.length;
        int x = m.array[0].length;
        int y1 = m1.array.length;
        int x1 = m1.array[0].length;
        if (y != x1) {
            throw new IllegalArgumentException("Количество столбцов первой матрицы должно быть равно количеству строк второй матрицы!");
        }
        double[][] tmpArray = new double[y][x1];
        for (int j = 0; j < y; j++) {
            for (int i = 0; i < x1; i++) {
                for (int k = 0; k < x; k++) {
                        tmpArray[j][i] += m.array[j][k] * m1.array[k][i];
                }
            }
        }
        return new Matrix(tmpArray);
    }
}
