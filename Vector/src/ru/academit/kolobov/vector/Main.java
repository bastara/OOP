package ru.academit.kolobov.vector;

import ru.academit.kolobov.vector.Vector.Vector;

public class Main {
    public static void main(String[] args) {
        //Vector(double[]) – заполнение вектора значениями из массива


        int ccc = 3;

        double[] c1 = {2, 4, 8, -3, -1, 4, 2};
        Vector v1 = null;
        try {
            v1 = new Vector(c1);
        } catch (IllegalArgumentException e) {
            System.out.println("EXCEPTIONS! Размерность прстранства 0 или отрицательная. Вектор не создан");
            System.out.println();
        }

        double[] c2 = {3, -7, 2, -4};
        Vector v2 = null;
        try {
            v2 = new Vector(c2);
        } catch (IllegalArgumentException e) {
            System.out.println("EXCEPTIONS! Размерность прстранства 0 или отрицательная. Вектор не создан");
            System.out.println();
        }

        //отлавливаем исключение когда размерность вектора <=0
        try {
            System.out.println("Создаем вектор в размерности -3");
            int n = -3;
            Vector v0 = new Vector(n);
            System.out.println("Размер вектора v0: " + v0.getSize());
        } catch (IllegalArgumentException e) {
            System.out.println("EXCEPTIONS! Размерность прстранства 0 или отрицательная. Вектор не создан");
            System.out.println();
        }

        //Метод getSize() для получения размерности вектора
        try {
            System.out.println("Размер вектора v1: " + v1 + ": " + v1.getSize());
            System.out.println("Размер вектора v2: " + v2 + ": " + v2.getSize());
            System.out.println();
        } catch (NullPointerException e) {
            System.out.println("Массив 0 размерности");
        }

        //Vector(Vector) – конструктор копирования
        System.out.println("Копируем вектрор v1 в v3");
        Vector v3 = new Vector(v1);
        //Реализовать метод toString()
        System.out.println("Координаты вектора v3:" + v3);
        System.out.println();

        //Vector(n, double[]) – заполнение вектора значениями из массива. Если длина массива меньше n, то считать что в остальных компонентах 0
        Vector v4 = null;
        try {
            v4 = new Vector(5, c1);
            System.out.println("Координаты вектора v4(конструктор new Vector(5, c1)):" + v4);
            System.out.println();
        } catch (IllegalArgumentException e) {
            System.out.println("EXCEPTIONS! Размерность прстранства 0 или отрицательная. Вектор не создан");
            System.out.println();
        }

        //Vector(0, double[]) – заполнение вектора значениями из массива. Если длина массива меньше n, то считать что в остальных компонентах 0
        try {
            System.out.println("Координаты вектора v6(конструктор new Vector(0, c1)):");
            Vector v6 = new Vector(0, c1);
            System.out.println("Координаты вектора v6(конструктор new Vector(0, c1)):" + v6);
            System.out.println();
        } catch (IllegalArgumentException e) {
            System.out.println("EXCEPTIONS! Размерность прстранства 0 или отрицательная. Вектор не создан");
            System.out.println();
        }

        //a.	Прибавление к вектору другого вектора
        System.out.println("Размер вектора v1: " + v1);
        System.out.println("Размер вектора v4: " + v4);
        v1.add(v4);
        System.out.println("Координаты вектора v1(сложение v1 и v4):" + v1);
        System.out.println();

        //b.	Вычитание из вектора другого вектора
        System.out.println("Размер вектора v3: " + v3);
        System.out.println("Размер вектора v2: " + v2);
        v3.subtraction(v2);
        System.out.println("Координаты вектора v3(вычитание из v3 вектора v2): " + v3);
        System.out.println();

        //c.	Умножение вектора на скаляр
        double[] c5 = {1, -4, 1, 1};
        Vector v5 = new Vector(c5);
        v5.multiplyByScalar(4);
        System.out.println("Координаты вектора v7(скалярное произведение на 4): " + v5);
        System.out.println();

        //d.	Разворот вектора (умножение всех компонент на -1)
        v5.invert();
        System.out.println("Разворот вектора v7 и его новые координаты: " + v5);
        System.out.println();

        //e.	Получение длины вектора
        double[] c8 = {1, 1, 9};
        Vector v8 = new Vector(c8);
        System.out.println("Длина вектора v8-" + v8 + " равна " + v8.getLength());
        System.out.println();

        //f.	Получение и установка компоненты вектора по индексу
        System.out.println("Вектор v5-" + v5);
        System.out.println("3 элемент вектора v5 равен: " + v5.getElement(3));
        System.out.println("3 элемент вектора v5 меняем на 8");
        v5.setElement(3, 8);
        System.out.println("Вектор v5-" + v5);
        System.out.println("3 элемент вектора v5 равен: " + v5.getElement(3));
        System.out.println();

        //g.	Переопределить метод equals, чтобы был true  векторы имеют одинаковую размерность и
        // соответствующие компоненты равны. Соответственно, переопределить hashCode
        System.out.println("Вектор v2-" + v2 + " и v5-" + v5);
        System.out.println("Сравниваем v2 и v5:");
        if (v2.equals(v5)) {
            System.out.println("Векторы равны");
        } else {
            System.out.println("Векторы не равны");
        }

        double[] c9 = {3, -7, 2, -4};
        Vector v9 = new Vector(c9);
        System.out.println("Вектор v2-" + v2 + " и v9-" + v9);
        System.out.println("Сравниваем v2 и v9:");
        if (v2.equals(v9)) {
            System.out.println("Векторы равны");
        } else {
            System.out.println("Векторы не равны");
        }
        System.out.println();

        //5.	Реализовать статические методы – должны создаваться новые векторы:
        //a.	Сложение двух векторов
        System.out.println("Сложение векторов v5-" + v5 + " и v2-" + v2);
        Vector v10 = Vector.sum(v5, v2);
        System.out.println("Координаты вектора v10:" + v10);
        System.out.println();

        //b.	Вычитание векторов
        System.out.println("Вычитание из v5-" + v5 + " вектора v2-" + v2);
        Vector v11 = Vector.diff(v5, v2);
        System.out.println("Координаты вектора v11:" + v11);
        System.out.println();

        //a.	Скалярное произведение векторов
        System.out.println("Вектор v5-" + v5 + " и v8-" + v8);
        System.out.println("Скалярное произведение векторов v5 и v8 будет: " + Vector.scalarProductOfVectors(v5, v8));
    }
}
