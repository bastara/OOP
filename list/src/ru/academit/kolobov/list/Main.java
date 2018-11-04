package ru.academit.kolobov.list;

import ru.academit.kolobov.list.listclass.ListItem;
import ru.academit.kolobov.list.listclass.SinglyLinkedList;

public class Main {
    public static void main(String[] args) {
        SinglyLinkedList<String> list = new SinglyLinkedList();

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
        System.out.println("       Элемент по индексу 3: " + list.getElement(3));
        list.change("НОВЫЙ ЭЛЕМЕНТ", 3);
        System.out.println("Теперь элемент по индексу 3: " + list.getElement(3));

        //печать списка
        System.out.println();
        System.out.println("Печать списка:");
        list.print();
        System.out.println();

        //•	удаление элемента по индексу, пусть выдает значение элемента
        System.out.println();
        System.out.println("Удаление элемента под индексом 1");
        ListItem<String> delItem = list.delete(1);
        System.out.println("Был удален элемент: " + delItem.getData());
        System.out.println("Печать списка:");
        list.print();
        System.out.println();

        //•	вставка элемента в начало
        System.out.println("Вставка элемента в начало. Вставляем элемент \"седьмой\"");
        list.addFirstElement("седьмой");
        //печать списка
        System.out.println();
        System.out.println("Печать списка:");
        list.print();
        System.out.println();

        //•	вставка элемента по индексу
        System.out.println("Вставка элемента по индексу. Вставляем элемент \"восьмой\" по индексу 1");
        list.addElement("восьмой", 1);
        list.print();

        //•	удаление первого элемента, пусть выдает значение элемента
        System.out.println();
        ListItem<String> deleteItem = list.deleteFirst();
        System.out.println("Был удален первый элемент,это: " + deleteItem.getData());
        list.print();
        System.out.println();

        //•	удаление узла по значению, пусть выдает true, если элемент был удален
        System.out.println("Удаление элемента \"третий\": "+ list.deleteValue("третий"));
        list.print();
    }
}
