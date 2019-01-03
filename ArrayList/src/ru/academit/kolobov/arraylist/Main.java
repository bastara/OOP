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

        System.out.println("Список после добавления еще 6 элементов:");
        System.out.println(list);
        System.out.println("Размер списка: " + list.size());
        System.out.println("Текущая вместимость списка: " + list.getCapacity());

        //sublist
        System.out.println("Получение списка с 4 по 8 индексы:");
        MyArrayList<String> subList = (MyArrayList) list.subList(4, 8);
        System.out.println("__________________");
        System.out.println(subList);

        //добавление колекции с определенного индекса
        System.out.println("Добавление subList к list по 8 индексу");
        list.addAll(19, subList);
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

        //очистка списка
        System.out.println("Очистка списка subList.");
        subList.clear();
        System.out.println("Печать списка subList: " + subList);

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
        Object[] array = new String[list.size()];
        array = list.toArray();
        System.out.println("Печать массива" + Arrays.toString(array));
        System.out.println();

        //удаление списка
        System.out.println();
        System.out.println("Удаление списка элементов с 8 по 15й");
        System.out.println(list.subList(8, 15));
        list.removeAll(list.subList(8, 15));
        System.out.println(list.toString());
        System.out.println();

        //удаление списка
        System.out.println();
        System.out.println("Удаление всех элементов списка кроме диапозона со 2 по 6й");
        System.out.println(list.subList(2, 6));
        list.retainAll(list.subList(2, 6));
        System.out.println(list.toString());
        System.out.println();
    }
}
