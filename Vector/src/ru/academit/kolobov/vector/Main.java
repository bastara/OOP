package ru.academit.kolobov.vector;

import ru.academit.kolobov.vector.Vector.Vector;

public class Main {
    public static void main(String[] args) {
        //Vector(double[]) – заполнение вектора значениями из массива
        double[] c1 = {2, 4, 8};
        Vector v1 = new Vector(c1);
        double[] c2 = {3, -7, 2, -4};
        Vector v2 = new Vector(c2);
        double[] c5 = {1, -4, 1, 1};
        Vector v4 = new Vector(5, c1);
        Vector v5 = new Vector(c5);

        //отлавливаем исключение когда размерность вектора <=0
        try {
            int n = -3;
            Vector v0 = new Vector(n);
            System.out.println("Размер вектора v0: " + v0.getSize());
        } catch (IllegalArgumentException e) {
            System.out.println("Размерность прстранства 0 или отрицательная");
        }

        //Метод getSize() для получения размерности вектора
        System.out.println("Размер вектора v1: " + v1.toString() + ": " + v1.getSize());
        System.out.println();

        //Vector(Vector) – конструктор копирования
        System.out.println("Копируем вектрор v1 в v3");
        Vector v3 = new Vector(v1);
        //Реализовать метод toString()
        System.out.println("Координаты вектора v3:" + v3.toString());
        System.out.println();

        //Vector(n, double[]) – заполнение вектора значениями из массива. Если длина массива меньше n, то считать что в остальных компонентах 0
        System.out.println("Координаты вектора v4(конструктор new Vector(5, c1)):" + v4.toString());
        System.out.println();

        //a.	Прибавление к вектору другого вектора
        Vector v6 = v1.addVector(v4);
        System.out.println("Координаты вектора v6(сложение v1 и v4):" + v6.toString());
        System.out.println();

        //b.	Вычитание из вектора другого вектора
        Vector v7 = v4.subtractionVector(v6);
        System.out.println("Координаты вектора v7(вычитание из v4 вектора v6): " + v7.toString());
        System.out.println();

        //c.	Умножение вектора на скаляр
        v7.getMultiplicationVectorByScalar(4);
        System.out.println("Координаты вектора v7(скалярное произведение на 4): " + v7.toString());
        System.out.println();


        //d.	Разворот вектора (умножение всех компонент на -1)
        v7.invertVector();
        System.out.println("Разворот вектора v7 и его новые координаты: " + v7.toString());
        System.out.println();

        //e.	Получение длины вектора
        double[] c8 = {1, 1, 9};
        Vector v8 = new Vector(c8);
        System.out.println("Длина вектора v8-" + v8.toString() + " равна " + v8.getLength());
        System.out.println();

        //f.	Получение и установка компоненты вектора по индексу
        System.out.println("Вектор v5-" + v5.toString());
        System.out.println("3 элемент вектора v5 равен: " + v5.getElementVector(3));
        System.out.println("3 элемент вектора v5 меняем на 8");
        v5.setElementVector(3, 8);
        System.out.println("Вектор v5-" + v5.toString());
        System.out.println("3 элемент вектора v5 равен: " + v5.getElementVector(3));
        System.out.println();

        //g.	Переопределить метод equals, чтобы был true  векторы имеют одинаковую размерность и
        // соответствующие компоненты равны. Соответственно, переопределить hashCode
        System.out.println("Вектор v2-" + v2.toString() + " и v5-" + v5.toString());
        System.out.println("Сравниваем v2 и v5:");
        if (v2.equals(v5)) {
            System.out.println("Векторы равны");
        } else {
            System.out.println("Векторы не равны");
        }

        double[] c9 = {3, -7, 2, -4};
        Vector v9 = new Vector(c9);
        System.out.println("Вектор v2-" + v2.toString() + " и v9-" + v9.toString());
        System.out.println("Сравниваем v2 и v9:");
        if (v2.equals(v9)) {
            System.out.println("Векторы равны");
        } else {
            System.out.println("Векторы не равны");
        }
        System.out.println();

        //5.	Реализовать статические методы – должны создаваться новые векторы:
        //a.	Сложение двух векторов
        System.out.println("Сложение векторов v5-" + v5.toString() + " и v6-" + v6.toString());
        Vector v10 = Vector.sumVector(v5, v6);
        System.out.println("Координаты вектора v10:" + v10.toString());
        System.out.println();

        //b.	Вычитание векторов
        System.out.println("Вычитание из v5-" + v5.toString() + " вектора v6-" + v6.toString());
        Vector v11 = Vector.difVector(v5, v6);
        System.out.println("Координаты вектора v11:" + v11.toString());
        System.out.println();

        //a.	Скалярное произведение векторов
        System.out.println("Вектор v5-" + v5.toString() + " и v8-" + v8.toString());
        System.out.println("Скалярное произведение векторов v5 и v8 будет: " + Vector.scalarVector(v5, v8));
    }
}
