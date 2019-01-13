package ru.academit.kolobov.arraylist;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        MyArrayList<String> list = new MyArrayList<>(10);

        //Проверка списка на наличие элементов
        System.out.println("Список пуст: " + list.isEmpty());
        System.out.println();

        //Длина списка
        System.out.println("Длина списка: " + list.size());
        System.out.println();

        //добавление элементов
        list.add("0 элемент");
        list.add("1 элемент");

        //полученный список
        System.out.println("Полученный список");
        System.out.println(list);

//        String[] randomList = {"первый из шорта", "второй из шорта", "третий из шорта"};
//        MyArrayList<String> shortList = new MyArrayList<>(randomList);
        //TODO как правилее делать, как закоментировано 2 строчки выше или строка та что ниже?
        MyArrayList<String> shortList = new MyArrayList<>(new String[]{"первый из шорта", "второй из шорта", "третий из шорта"});

        //TODO сделал как в лекции выдает ошибку
//        MyArrayList<String> shortList = new MyArrayList<>(Arrays.asList("первый из шорта", "второй из шорта", "третий из шорта"));

        //Добавление коллекции
        System.out.println("Добавление коллекции");
        System.out.println(shortList);
        list.addAll(shortList);
        System.out.println("Полученный список");
        System.out.println(list);

        list.add("5 элемент");
        list.add("6 элемент");
        list.add("7 элемент");
        list.add("8 элемент");
        list.add("9 элемент");
        list.add("10 элемент");
        list.add(5, "11 элемент");

        System.out.println("Список после добавления еще 6 элементов:");
        System.out.println(list);
        System.out.println("Размер списка: " + list.size());
        System.out.println("Текущая вместимость списка: " + list.getCapacity());



        //проверка вхождения
        System.out.println();
        System.out.println("Проверка вхождения \"6 элемент\" в список");
        System.out.println("\"6 элемент\" входит в список: " + list.contains("6 элемент"));
        System.out.println();

        //индекс первого вхождения элемента
        System.out.println("Поиск элемента \"третий из шорта\"");
        System.out.println("Элемент \"третий из шорта\" находится на " + list.indexOf("третий из шорта") + " позиции");
        System.out.println();

        //индекс последнего вхождения элемента
        System.out.println("Поиск последнего вхождения элемента \"5 элемент\"");
        System.out.println("Элемент \"5 элемент\" находится на " + list.lastIndexOf("5 элемент") + " позиции");
        System.out.println();

        //удаление элемента
        System.out.println();
        System.out.println("Удаление элемента на 3 позиции");
        list.remove(3);
        System.out.println(list.toString());
        System.out.println();

        //удаление элемента
        System.out.println();
        System.out.println("Удаление элемента \"8 элемент\"");
        list.remove("8 элемент");
        System.out.println(list.toString());
        System.out.println();



        //создание массива
        System.out.println();
        Object[] array = list.toArray();
        System.out.println("Печать первого массива" + Arrays.toString(array));
        System.out.println();

        //создание массива
        System.out.println();
        //TODO не работает следующая строчка со string, хотя все объекты string
        Object[] newArray = list.toArray(new String[list.size()]);
        System.out.println("Печать нового массива" + Arrays.toString(newArray));
        System.out.println();
    }
}
