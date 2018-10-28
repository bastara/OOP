package ru.academit.kolobov.matrix;

import ru.academit.kolobov.matrix.matrix.Matrix;
import ru.academit.kolobov.vector.Vector.Vector;

public class Main {
    public static void main(String[] args) {
        //прямоугольная матрица
//        double[] c1 = {2, 5, 6, 3};
//        Vector v1 = new Vector(c1);
//        double[] c2 = {4, -7, -8, 4};
//        Vector v2 = new Vector(c2);
//        double[] c3 = {-6, 12, 3, 5};
//        Vector v3 = new Vector(c3);

//квадратная матрица 5*5
//        double[] c1 = {0, 3, -1, 2, 6};
//        Vector v1 = new Vector(c1);
//        double[] c2 = {2, 1, 0, 0, 3};
//        Vector v2 = new Vector(c2);
//        double[] c3 = {-2, -1, 0, 2, 5};
//        Vector v3 = new Vector(c3);
//        double[] c4 = {-5, 7, 1, 1, 1};
//        Vector v4 = new Vector(c4);
//        double[] c5 = {2, 0, 2, -2, 1};
//        Vector v5 = new Vector(c5);

//квадратная матрица 6*6
//        double[] c1 = {1, 3, -2, 0, 0, 1};
//        Vector v1 = new Vector(c1);
//        double[] c2 = {2, 0, 2, 5, 3, -1};
//        Vector v2 = new Vector(c2);
//        double[] c3 = {0, -3, 6, 2, -7, 0};
//        Vector v3 = new Vector(c3);
//        double[] c4 = {-3, 6, -4, -5, 0, 2};
//        Vector v4 = new Vector(c4);
//        double[] c5 = {3, 15, -4, 2, -4, 5};
//        Vector v5 = new Vector(c5);
//        double[] c6 = {2, 7, -7, -2, 0, 3};
//        Vector v6 = new Vector(c6);
//
//        Vector[] arrayV = {v1, v2, v3, v4, v5, v6};

//квадратная матрица 4*4
//        double[] c1 = {2, 4, 1, 1};
//        Vector v1 = new Vector(c1);
//        double[] c2 = {0, 2, 0, 0};
//        Vector v2 = new Vector(c2);
//        double[] c3 = {2, 1, 1, 3};
//        Vector v3 = new Vector(c3);
//        double[] c4 = {4, 0, 2, 3};
//        Vector v4 = new Vector(c4);
//        Vector[] arrayV = {v1, v2, v3, v4};

//квадратная матрица 3*3
//        double[] c1 = {2, 4, 1};
//        Vector v1 = new Vector(c1);
//        double[] c2 = {0, 2, 1};
//        Vector v2 = new Vector(c2);
//        double[] c3 = {2, 1, 1};
//        Vector v3 = new Vector(c3);
//        Vector[] arrayV = {v1, v2, v3};
//        Matrix m = new Matrix(arrayV);
//
//        double[] c4 = {2, 4, 1};
//        Vector v4 = new Vector(c4);
//        double[] c5 = {0, 2, 1};
//        Vector v5 = new Vector(c5);
//        double[] c6 = {2, 1, 1};
//        Vector v6 = new Vector(c6);
//        Vector[] arrayV1 = {v4, v5, v6};
//        Matrix m10 = new Matrix(arrayV1);

        //матрицы для умножения
        double[] c1 = {2, 1};
        Vector v1 = new Vector(c1);
        double[] c2 = {-3, 0};
        Vector v2 = new Vector(c2);
        double[] c3 = {4, -1};
        Vector v3 = new Vector(c3);
        Vector[] arrayV = {v1, v2, v3};
        Matrix m = new Matrix(arrayV);

        double[] c4 = {5, -1, 6};
        Vector v4 = new Vector(c4);
        double[] c5 = {-3, 0, 7};
        Vector v5 = new Vector(c5);
        Vector[] arrayV1 = {v4, v5};
        Matrix m10 = new Matrix(arrayV1);

        //копируем матрицу m
        Matrix m1 = new Matrix(m);

        //toString определить так, чтобы результат получался в таком виде: { { 1, 2 }, { 2, 3 } }
        System.out.println("Вывод матрицы m1 переопределенным методом toString:");
        System.out.println(m1);
        System.out.println();

        //получаем размер матрицы m1
        System.out.println("Размер матрицы m1: " + m1.getSizeY(m1) + "-" + m1.getSizeX(m1));
//
//        //Получение и задание вектора-строки по индексу
//        Vector v8 = m1.getVector(2);
//        System.out.println("Получение вектора по индексу 2: " + m1.getVector(2));
//        //Вывод вектора методом toString из класса Vector
//        System.out.println("Координаты вектора по индексу 2(через класс векторов): " + v8);
//        System.out.println();
//
//        //Получение вектора-столбца по индексу
//        Vector v9 = m1.getVectorColumn(2);
//        System.out.println("Получение вектора по столбцу 2: " + v9);
//        System.out.println();
//
//        //Транспонирование матрицы
//        Matrix m2 = m1.getTransposition();
//        System.out.println("Вывод транспонированной матрицы m2:");
//        System.out.println(m2);
//        System.out.println();
//
//
//        //Умножение на скаляр
//        System.out.println("m1: " + m1);
//        m1.multiplicationByScalar(-4);
//        System.out.println("m1 умноженная на скаляр (-4): " + m1);
//        System.out.println();
//
//        //Вычисление определителя матрицы
//        double determinamt = m.determinant();//пока не понял может ли он быть интом
//        System.out.println("Вычисляем определительматрицы m: " + m);
//        System.out.println("Определитель матрицы m равен: " + determinamt);
//        System.out.println();
//
//        //умножение матрицы на вектор
//        System.out.println("Умножение матрицы m на вектор v2");
//        System.out.println("Векторо v2:" + v2);
//        System.out.println("Матрица m: " + m);
//        m.multiplicationByVector(v2);
//        System.out.println("Результат m: " + m);
//        System.out.println();
//
//        //Сложение матриц
//        System.out.println("Сложение матриц m и m10");
//        System.out.println("Матрица m: " + m);
//        System.out.println("Матрица m10: " + m10);
//        m.addMatrix(m10);
//        System.out.println("Результат сложения: " + m);
//        System.out.println();
//
//        //Вычитание матриц
//        System.out.println("Вычитание матриц m10 и m");
//        System.out.println("Матрица m10: " + m10);
//        System.out.println("Матрица m: " + m);
//        m10.difMatrix(m);
//        System.out.println("Результат вычитания: " + m10);
//        System.out.println();
//
//        //Сложение матриц static
//        System.out.println("Сложение матриц m и m10");
//        System.out.println("Матрица m: " + m);
//        System.out.println("Матрица m10: " + m10);
//        Matrix m11 = Matrix.matrixAddition(m, m10);
//        System.out.println("Результат сложения: " + m11);
//        System.out.println();
//
//        //Вычитание матриц static
//        System.out.println("Вычитание матриц m10 и m");
//        System.out.println("Матрица m10: " + m10);
//        System.out.println("Матрица m: " + m);
//        Matrix m12 = Matrix.subtractMatrix(m10, m);
//        System.out.println("Результат вычитания: " + m12);
//        System.out.println();

        //Умножение матриц
        System.out.println("Умножение матриц m и m10");
        System.out.println("Матрица m: " + m);
        System.out.println("Матрица m10: " + m10);
        Matrix m13 = Matrix.matrixMultiplication(m, m10);
        System.out.println("Результат умножения: " + m13);
        System.out.println();
    }
}
