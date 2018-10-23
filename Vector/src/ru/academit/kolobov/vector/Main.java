package ru.academit.kolobov.vector;

import ru.academit.kolobov.vector.Vector.Vector;

public class Main {
    public static void main(String[] args) {
        double[] c1 = {2, 4, 8};
        Vector v1 = new Vector(c1);
        double[] c2 = {3, -7, 2, -4};
        Vector v2 = new Vector(c2);
        Vector v3 = new Vector(v1);
        double[] c5 = {1, -4, 1, 1};
        Vector v4 = new Vector(5, c1);
        Vector v5 = new Vector(c5);


        System.out.println("Размер вектора v1: " + v1.getSize());
        System.out.println("Размер вектора v5: " + v4.getSize());

        System.out.println("Координаты вектора v3:" + v3.toString());
        System.out.println("Координаты вектора v4:" + v4.toString());

        Vector v6 = v1.addVector(v5);
        System.out.println("Координаты вектора v6(сложение v1 и v5):" + v6.toString());

        Vector v7 = v4.substractinonVector(v6);
        System.out.println("Координаты вектора v7(вычитание из v4 вектора v6): " + v7.toString());

        v7.invertVector();
        System.out.println("Разворот вектора v7 и его новые координаты: " + v7.toString());

        double[] c8 = {1, 1, 9};
        Vector v8 = new Vector(c8);
        System.out.println("Длина вектора v8: " + v8.lengthVector());

        System.out.println("3 элемент вектора v5 равен: " + v5.getElementVector(3));

        v5.setElementVector(3, 8);
        System.out.println("3 элемент вектора v5 равен: " + v5.getElementVector(3));

        System.out.println("Сравниваем v2 и v5:");
        if (v2.equals(v5)) {
            System.out.println("Векторы равны");
        } else {
            System.out.println("Векторы не равны");
        }

        double[] c9 = {3, -7, 2, -4};
        Vector v9 = new Vector(c9);

        System.out.println("Сравниваем v2 и v9:");
        if (v2.equals(v9)) {
            System.out.println("Векторы равны");
        } else {
            System.out.println("Векторы не равны");
        }

        System.out.println("Сложение векторов v4 и v6:");
        Vector v10 = Vector.sum(v4, v6);
        System.out.println("Координаты вектора v10:" + v10.toString());

        System.out.println("Вычитание из v4 вектора v6:");
        Vector v11 = Vector.dif(v4, v6);
        System.out.println("Координаты вектора v11:" + v11.toString());

        System.out.println("Скалярное произведение векторов v5 и v8 будет: " + Vector.scalarVector(v5, v8));
    }
}
