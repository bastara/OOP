package ru.academit.kolobov.list;

import ru.academit.kolobov.list.listclass.SinglyLinkedList;

public class Main {
    public static void main(String[] args) {
        SinglyLinkedList<String> list = new SinglyLinkedList<>();

        list.addElement("первый");
        list.addElement(null);
        list.addElement("третий");
        list.addElement("четвертый");
        list.addElement("пятый");
        list.addElement("шестой");

        //печать списка
        System.out.println("Печать списка:");
        System.out.println(list.toString());
        System.out.println();

        //•	получение размера списка
        System.out.println("     Размер текущего списка: " + list.getSizeList());

        //•	получение значение первого элемента
        try {
            System.out.println("  Значение первого элемента: " + list.getFirstElement());
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("EXCEPTIONS! Список пуст.");
            System.out.println();
        }

        //•	получение/изменение значения по указанному индексу.
        try {
            System.out.println("       Элемент по индексу 3: " + list.getElementData(3));
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("EXCEPTIONS! Не верный индекс.");
            System.out.println();
        }
        System.out.println();

        try {
            System.out.println("Меняем элемент по индексу 3 на \"НОВЫЙ ЭЛЕМЕНТ\"");
            list.set("НОВЫЙ ЭЛЕМЕНТ", 3);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("EXCEPTIONS! Не верный индекс.");
            System.out.println();
        }
        System.out.println("Теперь элемент по индексу 3: " + list.getElementData(3));

        //печать списка
        System.out.println();
        System.out.println("Печать списка:");
        System.out.println(list.toString());
        System.out.println();

        //•	удаление элемента по индексу, пусть выдает значение элемента
        try {
            System.out.println();
            System.out.println("Удаление элемента под индексом 1");
            String delItem = list.delete(1);
            System.out.println("Был удален элемент: " + delItem);
            System.out.println("Печать списка:");
            System.out.println(list.toString());
            System.out.println();
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("EXCEPTIONS! Не верный индекс.");
            System.out.println();
        }

        //•	вставка элемента в начало
        System.out.println("Вставка элемента в начало. Вставляем элемент \"седьмой\"");
        list.addFirstElement("седьмой");
        //печать списка
        System.out.println();
        System.out.println("Печать списка:");
        System.out.println(list.toString());
        System.out.println();

        //•	вставка элемента по индексу
        try {
            System.out.println("Вставка элемента по индексу. Вставляем элемент \"восьмой\" по индексу 1");
            list.addElement("восьмой", 1);
            System.out.println(list.toString());
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("EXCEPTIONS! Не верный индекс.");
            System.out.println();
        }

        //•	удаление первого элемента, пусть выдает значение элемента
        try {
            System.out.println();
            System.out.println("Удаление первого элемента");
            String deleteItem = list.deleteFirst();
            System.out.println("Был удален первый элемент,это: " + deleteItem);
            System.out.println(list.toString());
            System.out.println();
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("EXCEPTIONS! Не верный индекс.");
            System.out.println();
        }

        //•	удаление узла по значению, пусть выдает true, если элемент был удален
        System.out.println("Удаление элемента \"третий\": " + list.deleteValue("третий"));
        System.out.println(list.toString());
        System.out.println();

        //•	разворот списка за линейное время
        list.reverse();
        System.out.println("Разворот списка");
        System.out.println(list.toString());
        System.out.println();

        //•	копирование списка
        System.out.println("Копирование списка.");
        SinglyLinkedList newList = list.copyList();
        System.out.println("Меняем в старом списке элемент под 3 индексом и печатаем список.");
        list.set("новый элемент в старом списке", 3);
        System.out.println(list.toString());
        System.out.println();
        System.out.println("Новый список");
        System.out.println(newList.toString());

        System.out.println();
        System.out.println("Меняем в новом списке элемент под индексом 2 на null и печатаем список.");
        System.out.println("ранее под этим индексом было значение:" + newList.set(null, 2));
        System.out.println(newList.toString());
        System.out.println("Удаляем null  в новом списке: " + newList.deleteValue(null));
        System.out.println(newList.toString());
    }
}
