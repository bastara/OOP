package ru.academit.kolobov.list;

import ru.academit.kolobov.list.listclass.ListItem;
import ru.academit.kolobov.list.listclass.SinglyLinkedList;

public class Main {
    public static void main(String[] args) {
        SinglyLinkedList<String> list = new SinglyLinkedList<>();

        list.addElement("первый");
        list.addElement("второй");
        list.addElement("третий");
        list.addElement("четвертый", 4);
        list.addElement("пятый");
        list.addElement("шестой");

        //печать списка
        System.out.println("Печать списка:");
        list.print();
        System.out.println();

        //•	получение размера списка
        System.out.println("     Размер текущего списка: " + list.getSizeList());

        //•	получение значение первого элемента
        System.out.println("  Значение первого элемента: " + list.getFirstElement());

        //•	получение/изменение значения по указанному индексу.
        try {
            System.out.println("       Элемент по индексу 3: " + list.getElement(3));
        } catch (IllegalArgumentException e) {
            System.out.println("EXCEPTIONS! Не верный индекс.");
            System.out.println();
        }
        System.out.println();

        try {
            System.out.println("Меняем элемент по индексу 3 на \"НОВЫЙ ЭЛЕМЕНТ\"");
            list.change("НОВЫЙ ЭЛЕМЕНТ", 3);
        } catch (IllegalArgumentException e) {
            System.out.println("EXCEPTIONS! Не верный индекс.");
            System.out.println();
        }
        System.out.println("Теперь элемент по индексу 3: " + list.getElement(3));

        //печать списка
        System.out.println();
        System.out.println("Печать списка:");
        list.print();
        System.out.println();

        //•	удаление элемента по индексу, пусть выдает значение элемента
        try {
            System.out.println();
            System.out.println("Удаление элемента под индексом 1");
            ListItem delItem = list.delete(1);
            System.out.println("Был удален элемент: " + delItem.getData());
            System.out.println("Печать списка:");
            list.print();
            System.out.println();
        } catch (IllegalArgumentException e) {
            System.out.println("EXCEPTIONS! Не верный индекс.");
            System.out.println();
        }

        //•	вставка элемента в начало
        System.out.println("Вставка элемента в начало. Вставляем элемент \"седьмой\"");
        list.addFirstElement("седьмой");
        //печать списка
        System.out.println();
        System.out.println("Печать списка:");
        list.print();
        System.out.println();

        //•	вставка элемента по индексу
        try {
            System.out.println("Вставка элемента по индексу. Вставляем элемент \"восьмой\" по индексу 1");
            list.addElement("восьмой", 1);
            list.print();
        } catch (IllegalArgumentException e) {
            System.out.println("EXCEPTIONS! Не верный индекс.");
            System.out.println();
        }

        //•	удаление первого элемента, пусть выдает значение элемента
        try {
            System.out.println();
            System.out.println("Удаление первого элемента");
            ListItem<String> deleteItem = list.deleteFirst();
            System.out.println("Был удален первый элемент,это: " + deleteItem.getData());
            list.print();
            System.out.println();
        } catch (IllegalArgumentException e) {
            System.out.println("EXCEPTIONS! Не верный индекс.");
            System.out.println();
        }

        //•	удаление узла по значению, пусть выдает true, если элемент был удален
        System.out.println("Удаление элемента \"третий\": " + list.deleteValue("третий"));
        list.print();
        System.out.println();

        //•	разворот списка за линейное время
        list.reverse();
        System.out.println("Разворот списка");
        list.print();
        System.out.println();

        //•	копирование списка
        System.out.println("Копирование списка. Новый список.");
        SinglyLinkedList<String> newList = new SinglyLinkedList<>(list);
        newList.print();
    }
}
