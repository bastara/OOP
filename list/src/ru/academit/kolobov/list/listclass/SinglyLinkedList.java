package ru.academit.kolobov.list.listclass;

import java.util.Objects;

public class SinglyLinkedList<T> {
    private ListItem<T> head;
    private ListItem<T> last;//решил что будет требоваться достаточно часто, поэтому дешевле добавить переменную
    private int count;

    public SinglyLinkedList() {
    }

    public int getSizeList() {
        return count;
    }

    public T getFirstElement() {
        return head.getData();
    }

    public T getElementData(int index) {
        if (index > count || index < 0) {
            throw new IndexOutOfBoundsException("Не верный индекс");
        }
        return getElement(index).getData();
    }

    public void addFirstElement(T data) {
        ListItem<T> element = new ListItem<>(data, head);
        head = element;
        ++count;
        if (last == null) {
            last = element;
        }
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
            throw new IndexOutOfBoundsException("Не верный индекс");
        }

        ListItem<T> element = new ListItem<>(data);

        if (index == count) {
            last.setNext(element);
            last = element;
            count++;
        } else if (index == 0) {
            addFirstElement(data);
        } else {
            ListItem<T> tmp = getElement(index - 1);
            element.setNext(getElement(index));
            tmp.setNext(element);
            ++count;
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

    public void set(T data, int index) {
        if (index > count || index < 0) {
            throw new IndexOutOfBoundsException("Не верный индекс");
        }
        getElement(index).setData(data);
    }

    private ListItem<T> getElement(int index) {
        int countElement = 0;
        ListItem<T> tmpE = head;

        while (countElement != index) {
            ++countElement;
            tmpE = tmpE.getNext();
        }
        return tmpE;
    }


    public T delete(int index) {
        if (index >= count || index < 0) {
            throw new IndexOutOfBoundsException("Не верный индекс");
        }
        if (index == 0) {
            return deleteFirst();
        }
        T tmp = getElement(index).getData();
        if (index + 1 == count) {
            last = getElement(index - 1);
            getElement(index - 1).setNext(null);
        } else {
            getElement(index - 1).setNext(getElement(index + 1));
        }
        --count;
        return tmp;
    }

    public T deleteFirst() {
        if (head == null) {
            throw new IndexOutOfBoundsException("Не верный индекс");
        }
        T tmp = head.getData();
        head = head.getNext();
        --count;
        if (count == 0) {
            last = null;
        }
        return tmp;
    }

    public boolean deleteValue(T data) {
        if (Objects.equals(head.getData(), data)) {
            head = head.getNext();
            --count;
            if (count == 0) {
                last = null;
            }
            return true;
        } else {
            for (ListItem<T> p = head; p != null; p = p.getNext()) {
                if (Objects.equals(p.getNext().getData(), data)) {
                    if (last == p.getNext()) {
                        last = p;
                    }
                    p.setNext(p.getNext().getNext());
                    --count;
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
        if (head != null) {
            last = head;
            ListItem<T> tmp1 = new ListItem<>(head);
            tmp1.setNext(null);
            for (ListItem<T> p = head.getNext(); p != null; p = p.getNext()) {
                ListItem<T> tmp2 = new ListItem<>(p);
                tmp2.setNext(tmp1);
                tmp1 = tmp2;
                if (p.getNext() == null) {//можно съэкономить память на последнем элементе
                    head = tmp1;
                }
            }
        }
    }

    //•	копирование списка
    public SinglyLinkedList copyList() {
        SinglyLinkedList<T> newList = new SinglyLinkedList<>();
        if (head == null) {
            return newList;
        }
        newList.head = new ListItem<>(head);
        newList.last = head;
        newList.count = 1;
        for (ListItem<T> p = head.getNext(); p != null; p = p.getNext()) {
            newList.addElement(p.getData());
        }
        return newList;
    }
}
