package ru.academit.kolobov.matrix;

import ru.academit.kolobov.matrix.matrix.Matrix;
import ru.academit.kolobov.vector.Vector.Vector;

public class Main {
    public static void main(String[] args) {
        double[] c1 = {2, 4, 1};
        Vector v1 = new Vector(c1);
        double[] c2 = {0, 2, 1};
        Vector v2 = new Vector(c2);
        double[] c3 = {2, 1, 1};
        Vector v3 = new Vector(c3);
        Vector[] arrayV = {v1, v2, v3};

        Matrix m = null;
        try {
            m = new Matrix(arrayV);
        } catch (IllegalArgumentException e) {
            System.out.println("EXCEPTIONS! Размер массив 0 или отрицательный. Матрица не создана");
            System.out.println();
        }

        //toString определить так, чтобы результат получался в таком виде: { { 1, 2 }, { 2, 3 } }
        System.out.println("Вывод матрицы m переопределенным методом toString:");
        System.out.println(m.toString());
        System.out.println();

        //копируем матрицу m
        System.out.println("Копируем матрицу m в m1, маторица m1:");
        Matrix m1 = new Matrix(m);

        System.out.println(m1);
        System.out.println();

        //получаем размер матрицы m1
        System.out.println("Размер матрицы m1: " + m1.getSizeY(m1) + "-" + m1.getSizeX(m1));

        //Получение и задание вектора-строки по индексу
        Vector v8 = m1.getVector(2);
        System.out.println("Получение вектора по индексу 2: " + m1.getVector(2));
        //Вывод вектора методом toString из класса Vector
        System.out.println("Координаты вектора по индексу 2(через класс векторов): " + v8);
        System.out.println();
        System.out.println("Задание ветора {3, 4, 7, 8} по индексу 2:");
        double[] c9 = {3, 4, 7, 8};
        Vector v9 = new Vector(c9);
        try {
            m1.setVector(2, v9);
        } catch (IllegalArgumentException e) {
            System.out.println("EXCEPTIONS! Размер вектора отличается от размера матрицы!");
            System.out.println();
        }
        System.out.println("Получена матрица m1: " + m1);
        System.out.println();

        //Получение вектора-столбца по индексу
        Vector v10 = m1.getVectorColumn(0);
        System.out.println("Получение вектора по столбцу 0: " + v10);
        System.out.println();

        //Транспонирование матрицы
        m1.transposition();
        System.out.println("Вывод транспонированной матрицы m1:");
        System.out.println(m1);
        System.out.println();

        //Умножение на скаляр
        System.out.println("m1: " + m1);
        m1.multiplicationByScalar(-4);
        System.out.println("m1 умноженная на скаляр (-4): " + m1);
        System.out.println();

        //Вычисление определителя матрицы
        double determinant = m.determinant();//пока не понял может ли он быть интом
        System.out.println("Вычисляем определительматрицы m: " + m);
        System.out.println("Определитель матрицы m равен: " + determinant);
        System.out.println();

        //умножение матрицы на вектор
        System.out.println("Умножение матрицы m1 на вектор v2");
        System.out.println("   Вектор v2: " + v2);
        System.out.println("  Матрица m1: " + m1);
        Vector v7 = m1.multiplicationByVector(v2);
        System.out.println("Результат v7: " + v7);
        System.out.println();

        double[] c4 = {2, 4, 1};
        Vector v4 = new Vector(c4);
        double[] c5 = {0, 2, 1};
        Vector v5 = new Vector(c5);
        double[] c6 = {2, 1, 1};
        Vector v6 = new Vector(c6);
        Vector[] arrayV1 = {v4, v5, v6};

        Matrix m10 = null;
        try {
            m10 = new Matrix(arrayV1);
        } catch (IllegalArgumentException e) {
            System.out.println("EXCEPTIONS! Размер массив 0 или отрицательный. Матрица не создана");
            System.out.println();
        }

        //Сложение матриц
        try {
            System.out.println("Сложение матриц m и m10");
            System.out.println("         Матрица m: " + m);
            System.out.println("       Матрица m10: " + m10);
            m.addMatrix(m10);
            System.out.println("Результат сложения: " + m);
            System.out.println();
        } catch (IllegalArgumentException e) {
            System.out.println("EXCEPTIONS! Размеры матриц должны совпадать! Сложение не выполнено.");
        }

        //Вычитание матриц
        try {
            System.out.println("Вычитание матриц m10 и m");
            System.out.println("        Матрица m10: " + m10);
            System.out.println("          Матрица m: " + m);
            m10.difMatrix(m);
            System.out.println("Результат вычитания: " + m10);
            System.out.println();
        } catch (IllegalArgumentException e) {
            System.out.println("EXCEPTIONS! Размеры матриц должны совпадать! Вычитание не выполнено.");
        }

        //Сложение матриц static
        try {
            System.out.println("Сложение матриц m и m10 (static)");
            System.out.println("         Матрица m: " + m);
            System.out.println("       Матрица m10: " + m10);
            Matrix m11 = Matrix.additionMatrix(m, m10);
            System.out.println("Результат сложения: " + m11);
            System.out.println();
        } catch (
                IllegalArgumentException e) {
            System.out.println("EXCEPTIONS! Размеры матриц должны совпадать! Сложение не выполнено.");
        }

        //Вычитание матриц static
        try {
            System.out.println("Вычитание матриц m10 и m (static)");
            System.out.println("        Матрица m10: " + m10);
            System.out.println("          Матрица m: " + m);
            Matrix m12 = Matrix.subtractMatrix(m10, m);
            System.out.println("Результат вычитания: " + m12);
            System.out.println();
        } catch (IllegalArgumentException e) {
            System.out.println("EXCEPTIONS! Размеры матриц должны совпадать! Вычитание не выполнено.");
        }


        //Умножение матриц
        try {
            System.out.println("Умножение матриц m и m10");
            System.out.println("          Матрица m: " + m);
            System.out.println("        Матрица m10: " + m10);
            Matrix m13 = Matrix.matrixMultiplication(m, m10);
            System.out.println("Результат умножения: " + m13);
            System.out.println();
        } catch (IllegalArgumentException e) {
            System.out.println("EXCEPTIONS! Количество столбцов первой матрицы должно быть равно количеству строк второй матрицы!");
        }
    }
}
