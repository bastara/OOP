package ru.academit.kolobov.matrix;

import ru.academit.kolobov.matrix.matrix.Matrix;
import ru.academit.kolobov.vector.Vector.Vector;

public class Main {
    public static void main(String[] args) {
//        double[] c1 = {2, 5, 6, 3};
//        Vector v1 = new Vector(c1);
//        double[] c2 = {4, -7, -8, 4};
//        Vector v2 = new Vector(c2);
//        double[] c3 = {-6, 12, 3, 5};
//        Vector v3 = new Vector(c3);

//        double[] c1 = {0, 5, 6, 3, 23};
//        Vector v1 = new Vector(c1);
//        double[] c2 = {4, -7, -8, 4, 1};
//        Vector v2 = new Vector(c2);
//        double[] c3 = {-6, 12, 3, 5, -4};
//        Vector v3 = new Vector(c3);
//        double[] c4 = {8, 5, -4, - 4, 0};
//        Vector v6 = new Vector(c4);
//        double[] c5 = {3, 3, 0, 19, -8};
//        Vector v7 = new Vector(c5);

        double[] c1 = {0, 3, -1, 2, 6};
        Vector v1 = new Vector(c1);
        double[] c2 = {2, 1, 0, 0, 3};
        Vector v2 = new Vector(c2);
        double[] c3 = {-2, -1, 0, 2, 5};
        Vector v3 = new Vector(c3);
        double[] c4 = {-5, 7, 1, 1, 1};
        Vector v6 = new Vector(c4);
        double[] c5 = {2, 0, 2, -2, 1};
        Vector v7 = new Vector(c5);

        Vector[] arrayV = {v1, v2, v3, v6, v7};

        Matrix m = new Matrix(arrayV);

        //копируем матрицу m
        Matrix m1 = new Matrix(m);

        //toString определить так, чтобы результат получался в таком виде: { { 1, 2 }, { 2, 3 } }
        System.out.println("Вывод матрицы m1 переопределенным методом toString:");
        System.out.println(m1);
        System.out.println();

        //получаем размер матрицы m1
        System.out.println("Размер матрицы m1: " + m1.getSizeY(m1) + "-" + m1.getSizeX(m1));

        //Получение и задание вектора-строки по индексу
        Vector v4 = m1.getVector(2);
        System.out.println("Получение вектора по индексу 2: " + m1.getVector(2));
        //Вывод вектора методом toString из класса Vector
        System.out.println("Координаты вектора по индексу 2(через класс веторов): " + v4);
        System.out.println();

        //Получение вектора-столбца по индексу
        Vector v5 = m1.getVectorColumn(2);
        System.out.println("Получение вектора по столбцу 2: " + v5);
        System.out.println();

        //Транспонирование матрицы
        Matrix m2 = m1.getTransposition();
        System.out.println("Вывод транспонированной матрицы m2:");
        System.out.println(m2);
        System.out.println();


        //Умножение на скаляр
        System.out.println("m1: " + m1);
        m1.multiplicationByScalar(-4);
        System.out.println("m1 умноженная на скаляр (-4): " + m1);
        System.out.println();

        //Вычисление определителя матрицы
        int determinamt = m.determinant();
    }
}
