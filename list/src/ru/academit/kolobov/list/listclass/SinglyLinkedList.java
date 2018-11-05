package ru.academit.kolobov.list.listclass;

import java.util.Objects;

public class SinglyLinkedList<T> {
    private ListItem<T> head;
    private ListItem<T> last;//решил что будет требоваться достаточно часто, поэтому дешевле добавить переменную
    private int count;

    public SinglyLinkedList() {
    }

    //конструктор копирования
    public SinglyLinkedList copyList() {
        SinglyLinkedList<T> newList = new SinglyLinkedList<>();
//        newList.head = new ListItem<>(sourceList.head);
        newList.head=new ListItem<>(head);
        this.last = head;
        this.count = 1;
        int i = 0;
        for (ListItem<T> p = head.getNext(); p != null; p = p.getNext()) {
             newList.addElement(p.getData(),i++);
            count++;
        }
        return newList;
    }

    //конструктор копирования
//    public SinglyLinkedList(SinglyLinkedList<T> sourceList) {
//        for (ListItem<T> p = sourceList.head; p != null; p = p.getNext()) {
//            ListItem<T> tmp = new ListItem<>(p);
//            if (p == sourceList.head) {
//                head = tmp;
//            }
//        }
//        head = new ListItem<>(sourceList.head);
//        last = sourceList.last;
//        count = sourceList.count;
//    }

    public int getSizeList() {
        return count;
    }

    public T getFirstElement() {
        return head.getData();
    }

    public T getElementData(int index) {
        if (index >= count || index < 0) {
            throw new ArrayIndexOutOfBoundsException("Не верный индекс");
        }
        return getElement(index).getData();
    }

    public void addFirstElement(T data) {
        ListItem<T> element = new ListItem<>(data);
        element.setNext(head);
        head = element;
    }

    public void addElement(T data) {
        ListItem<T> element = new ListItem<>(data);

        if (head == null) {
            head = element;
            last = element;
            count++;
        } else {
            last.setNext(element);
            last = element;
            count++;
        }
    }

    public void addElement(T data, int index) {
        if (index > count || index < 0) {
            throw new ArrayIndexOutOfBoundsException("Не верный индекс");
        }

        ListItem<T> element = new ListItem<>(data);

        if (head == null && index == 0) {
            head = element;
            last = element;
            count++;
        } else if (index == count) {
            last.setNext(element);
            last = element;
            count++;
        } else if (index == 0) {
            addFirstElement(data);
        } else {
            ListItem<T> tmp = getElement(index - 1);
            element.setNext(getElement(index));
            tmp.setNext(element);
        }
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (ListItem<T> p = head; p != null; p = p.getNext()) {
            str.append(p.getData()).append(System.lineSeparator());
        }
        return str.toString();
    }

    public void change(T data, int index) {
        if (index >= count || index < 0) {
            throw new ArrayIndexOutOfBoundsException("Не верный индекс");
        }
        getElement(index).setData(data);
    }

    private ListItem<T> getElement(int index) {
        int countElement = 0;
        for (ListItem<T> p = head; p != null; p = p.getNext()) {
            if (countElement == index) {
                return p;
            }
            countElement++;
        }
        return null;
    }

    public ListItem delete(int index) {
        if (index > count + 1 || index < 0) {
            throw new ArrayIndexOutOfBoundsException("Не верный индекс");
        }
        if (index == 0) {
            return deleteFirst();
        }
        ListItem<T> tmp = getElement(index - 1).getNext();
        getElement(index - 1).setNext(getElement(index - 1).getNext().getNext());
        return tmp;
    }


    public ListItem<T> deleteFirst() {
        if (head == null) {
            throw new ArrayIndexOutOfBoundsException("Не верный индекс");
        }
        ListItem<T> tmp = head;
        head = head.getNext();
        return tmp;
    }

    public boolean deleteValue(T data) {
        if (head.getData().equals(data)) {
            head = head.getNext();
            return true;
        } else {
            for (ListItem<T> p = head; p != null; p = p.getNext()) {
                if (Objects.equals(p.getNext().getData(),data)) {
                    p.setNext(p.getNext().getNext());
                    return true;
                }
                if (p.getNext().getNext() == null) {
                    return false;
                }
            }
        }
        return false;
    }

    public ListItem<T> getLast() {
        return last;
    }

    public void reverse() {
        last = head;
        ListItem<T> tmp1 = new ListItem<>(head);
        tmp1.setNext(null);
        ListItem<T> tmp2;
        for (ListItem<T> p = head.getNext(); p != null; p = p.getNext()) {
            tmp2 = new ListItem<>(p);
            tmp2.setNext(tmp1);
            tmp1 = tmp2;
            if (p.getNext() == null) {//можно съэкономить память на последнем элементе
                head = tmp1;
            }
        }
    }
}
