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
            for (int j = 0; j < x; j++) {
                array[i][j] = matrix.array[i][j];
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
        return matrix.array[0].length;
    }

    public int getSizeY(Matrix matrix) {
        return matrix.array.length;
    }

    public Vector getVector(int i) {
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

    public int determinant() {
        int y = array.length;
        int x = array[0].length;

        for (int i = 0; i < x; i++) {
            for (int j = 0; j < x; j++) {
                System.out.printf("%8.2f", array[i][j]);

            }
            System.out.println();
        }
        System.out.println();
        System.out.println();

        if (array[0][0] == 0) {
            int notZeroPos = 0;
            for (int i = 1; i < y; i++) {
                if (array[i][0] == 0) {
                    continue;
                }
                notZeroPos = i;
                break;
            }

            if (notZeroPos == 0) {
                return 0;
            } else {
                for (int i = 0; i < x; i++) {
                    array[0][i] += array[notZeroPos][i];
                }
            }

            for (int i = 0; i < x; i++) {
                for (int j = 0; j < x; j++) {
                    System.out.printf("%8.2f", array[i][j]);

                }
                System.out.println();
            }
            System.out.println();
            System.out.println();


            for (int i = 1; i < y; i++) {
                for (int j = 0; j < x; j++) {
                    array[i][j] = array[i][j]+ array[0][j] * (-array[i][0] / array[0][0]);
                }
            }

            for (int i = 0; i < x; i++) {
                for (int j = 0; j < x; j++) {
                    System.out.printf("%8.2f", array[i][j]);
                }
                System.out.println();
            }
        }
        return 0;
    }
}

