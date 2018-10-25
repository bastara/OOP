package ru.academit.kolobov.matrix;

import ru.academit.kolobov.matrix.matrix.Matrix;
import ru.academit.kolobov.vector.Vector.Vector;

public class Main {
    public static void main(String[] args) {
        double[] c1 = {2, 5, 6, 3};
        Vector v1 = new Vector(c1);

        double[] c2 = {4, -7, -8, 4};
        Vector v2 = new Vector(c2);

        double[] c3 = {-6, 12, 3, 5};
        Vector v3 = new Vector(c3);

        Vector[] arrayV = {v1, v2, v3};

        Matrix m = new Matrix(arrayV);

        //копируем матрицу m
        Matrix m1 = new Matrix(m);
        System.out.println(m1.getArray()[0][2]);
        System.out.println(m1.getArray()[2][3]);

        //получаем размер матрицы m1
        System.out.println("Размер матрицы m1: " + m1.getSizeY(m1) + "-" + m1.getSizeX(m1));

        //Получение и задание вектора-строки по индексу
        Vector v4 = m1.getVector(2);
        System.out.println("Получение вектора по индексу 2: " + m1.getVector(2));
        //Вывод вектора методом toString из класса Vector
        System.out.println("Координаты вектора по индексу 2: " + v4.toString());


        //toString определить так, чтобы результат получался в таком виде: { { 1, 2 }, { 2, 3 } }
        System.out.println("Вывод матрицы переопределенным методом toString:");
        System.out.println(m1.toString());

        //Транспонирование матрицы
        Matrix m2 = m1.getTranspositionMatrix();
        System.out.println("Вывод транспонированной матрицы:");
        System.out.println(m2.toString());
        System.out.println();

        //Получение вектора-столбца по индексу
        Vector v5 = m1.getVectorColumn(2);
        System.out.println("Получение вектора по столбцу 2: " + v5.toString());
    }
}
