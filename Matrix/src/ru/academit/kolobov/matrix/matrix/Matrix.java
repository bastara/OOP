package ru.academit.kolobov.matrix.matrix;

import ru.academit.kolobov.vector.Vector.Vector;

import java.util.Arrays;

public class Matrix {
    private Vector[] matrixOfVector;

    public Matrix(Vector[] vectors) {
        int x = 0;
        for (Vector v : vectors) {
            if (v.getSize() > x) {
                x = v.getSize();
            }
        }
        int y = vectors.length;
        matrixOfVector = new Vector[y];
        for (int j = 0; j < y; j++) {
            matrixOfVector[j] = new Vector(x);
            int size = vectors[j].getSize();//обратил внимание что метод вызывается каждый раз, решил сделать переменную, наверное так лучше будет?
            for (int i = 0; i < size; i++) {
                matrixOfVector[j].setElement(i, vectors[j].getElement(i));
            }
        }
    }

    public Matrix(int r, int c) {
        if (r <= 0 || c <= 0) {
            throw new IllegalArgumentException("Размер массив 0 или отрицательный");
        }
        matrixOfVector = new Vector[r];
        for (int i = 0; i < r; i++) {
            matrixOfVector[i] = new Vector(c);
        }
    }

    public Matrix(Matrix matrix) {
        matrixOfVector = new Vector[matrix.matrixOfVector.length];
        for (int i = 0; i < matrix.matrixOfVector.length; i++) {
//            this.setVector(i,matrix.getVector(i));
            matrixOfVector[i] = matrix.getVector(i);
        }
    }

    public Matrix(double[][] a) {
        int y = a.length;
        int x = a[0].length;
        if (x == 0 || y == 0) {
            throw new IllegalArgumentException("Размер массив 0");
        }
        matrixOfVector = new Vector[y];
        for (int i = 0; i < y; i++) {
            double[] m = Arrays.copyOf(a[i], x);
            matrixOfVector[i] = new Vector(m);
        }
    }

    public int getSizeX(Matrix matrix) {
        return matrix.matrixOfVector[0].getSize();
    }

    public int getSizeY(Matrix matrix) {
        return matrix.matrixOfVector.length;
    }

    public Vector getVector(int i) {
        if (i > matrixOfVector.length - 1) {
            System.out.println("Индекс вектора больше длины матрицы!");
            return null;
        }
        return new Vector(matrixOfVector[i]);
    }

    public void setVector(int i, Vector v) {
        if (v.getSize() != this.getSizeX(this)) {
            throw new IllegalArgumentException("Размер вектора отличается от размера матрицы!");
        }
        matrixOfVector[i] = v;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("{");
        for (int i = 0; i < matrixOfVector.length; i++) {
            if (i > 0) {
                str.append(",");
            }
            str.append("{");
            for (int j = 0; j < matrixOfVector[i].getSize() - 1; j++) {
                str.append(matrixOfVector[i].getElement(j)).append(", ");
            }
            str.append(matrixOfVector[i].getElement(matrixOfVector[i].getSize() - 1)).append("}");
        }
        return str.append("}").toString();
    }

    public void transposition() {
        int r = matrixOfVector.length;
        int c = matrixOfVector[0].getSize();
        Matrix m = new Matrix(c, r);
        for (int i = 0; i < c; i++) {
            for (int j = 0; j < r; j++) {
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
        int x = matrixOfVector[0].getSize();
        for (Vector v : matrixOfVector) {
            for (int k = 0; k < x; k++) {
                v.setElement(k, v.getElement(k) * s);
            }
        }
    }

    public double determinant() {
        int y = matrixOfVector.length;
        int x = matrixOfVector[0].getSize();

        if (x != y) {
            System.out.println("Матрица должна быть квадратного вида");
            return 0;
        }

        if (x == 2) {
            return matrixOfVector[0].getElement(0) * matrixOfVector[1].getElement(1) - matrixOfVector[0].getElement(1) * matrixOfVector[1].getElement(0);
        }

        if (matrixOfVector[0].getElement(0) == 0) {
            int notZeroFirstPosRow = 0;
            for (int i = 1; i < y; i++) {
                if (matrixOfVector[i].getElement(0) == 0) {
                    continue;
                }
                notZeroFirstPosRow = i;
                break;
            }

            if (notZeroFirstPosRow == 0) {
                return 0;
            } else {
                for (int i = 0; i < x; i++) {
                    matrixOfVector[0].setElement(i, matrixOfVector[0].getElement(i) + matrixOfVector[notZeroFirstPosRow].getElement(i));
                }
            }
        }

        for (int j = 1; j < y; j++) {
            double rowRatio = -matrixOfVector[j].getElement(0) / matrixOfVector[0].getElement(0);
            for (int i = 0; i < x; i++) {
                double tmp = matrixOfVector[j].getElement(i) + matrixOfVector[0].getElement(i) * rowRatio;
                matrixOfVector[j].setElement(i, tmp);
                double epsilon = 1.0e-10;
                if (Math.abs(matrixOfVector[j].getElement(i)) < epsilon) {
                    matrixOfVector[j].setElement(i, 0);
                }
            }
        }

        double[][] arrayTmp = new double[y - 1][x - 1];
        for (int j = 0; j < y - 1; j++) {
            for (int i = 0; i < x - 1; i++) {
                arrayTmp[j][i] = matrixOfVector[j + 1].getElement(i + 1);
            }
        }

        Matrix m = new Matrix(arrayTmp);
        return matrixOfVector[0].getElement(0) * m.determinant();
    }

    public Vector multiplicationByVector(Vector v2) {
        int y = matrixOfVector.length;
        int x = matrixOfVector[0].getSize();
        if (y != v2.getSize()) {
            System.out.println("Длина вектора должна быть равна числу строк в матрице!");
            return null;
        }
        double[] arr = new double[y];
        for (int j = 0; j < y; j++) {
            double tmp = 0;
            for (int i = 0; i < x; i++) {
                tmp += matrixOfVector[j].getElement(i) * v2.getElement(i);
            }
            arr[j] = tmp;
        }
        return new Vector(arr);
    }

    public void addMatrix(Matrix m1) {
        int y = matrixOfVector.length;
        int x = matrixOfVector[0].getSize();
        int y1 = m1.matrixOfVector.length;
        int x1 = m1.matrixOfVector[0].getSize();
        if (y != y1 || x != x1) {
            throw new IllegalArgumentException("Размеры матриц должны совпадать!");
        }
        for (int j = 0; j < y; j++) {
            for (int i = 0; i < x; i++) {
                double tmp = matrixOfVector[j].getElement(i) + m1.matrixOfVector[j].getElement(i);
                matrixOfVector[j].setElement(i, tmp);
            }
        }
    }

    public void difMatrix(Matrix m1) {
        int y = matrixOfVector.length;
        int x = matrixOfVector[0].getSize();
        int y1 = m1.matrixOfVector.length;
        int x1 = m1.matrixOfVector[0].getSize();
        if (y != y1 || x != x1) {
            throw new IllegalArgumentException("Размеры матриц должны совпадать!");
        }
        for (int j = 0; j < y; j++) {
            for (int i = 0; i < x; i++) {
                double tmp = matrixOfVector[j].getElement(i) - m1.matrixOfVector[j].getElement(i);
                matrixOfVector[j].setElement(i, tmp);
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
        int y = m.matrixOfVector.length;
        int x = m.matrixOfVector[0].getSize();
        int y1 = m1.matrixOfVector.length;
        int x1 = m1.matrixOfVector[0].getSize();
        if (y != x1 || y1 != x) {
            throw new IllegalArgumentException("Количество столбцов первой матрицы должно быть равно количеству строк второй матрицы!");
        }
        double[][] tmpArray = new double[y][x1];
        for (int j = 0; j < y; j++) {
            for (int i = 0; i < x1; i++) {
                for (int k = 0; k < x; k++) {
                    tmpArray[j][i] += m.matrixOfVector[j].getElement(k) * m1.matrixOfVector[k].getElement(i);
                }
            }
        }
        return new Matrix(tmpArray);
    }
}
