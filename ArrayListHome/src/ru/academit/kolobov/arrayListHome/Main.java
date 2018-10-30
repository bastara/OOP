package ru.academit.kolobov.arrayListHome;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Заполнение списка строками из файла");
        try (Scanner scanner = new Scanner(new FileInputStream("input.txt"))) {
            ArrayList<String> names = new ArrayList<>();

            while (scanner.hasNextLine()) {
                names.add(scanner.nextLine());
            }

            for (String s : names) {
                System.out.println(s);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Упссс. Файл не найден.");
        }

        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(1, 4, 2, 7, -8, 12, 13, 54, 64, 23, 12, 17, 18));

        System.out.println("Удаление четных чисел из списка.");
        System.out.println(list);

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) % 2 == 0) {
                list.remove(i);
                i--;
            }
        }

        System.out.println("Полученный список:");
        System.out.println(list);
        System.out.println();

        ArrayList<Integer> intList = new ArrayList<>(Arrays.asList(1, 4, 2, 7, -8, 12, 13, 4, 18, 23, 54, 64, 1, -8, 23, 12, 17, 18));
        ArrayList<Integer> intListWithoutRepeat = new ArrayList<>();

        System.out.println("Создаем новый список без повторяющихся элементов.");
        System.out.println("Исходный список: ");
        System.out.println(intList);

        for (Integer element : intList) {
            if (!intListWithoutRepeat.contains(element)) {
                intListWithoutRepeat.add(element);
            }
        }

        System.out.println("Полученный список: ");
        System.out.println(intListWithoutRepeat);
    }
}
