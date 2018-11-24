package ru.academit.kolobov.list.listclass;

import java.util.Objects;

public class SinglyLinkedList<T> {
    private ListItem<T> head;
    private ListItem<T> last;
    private int count;

    public SinglyLinkedList() {
    }

    public int getSizeList() {
        return count;
    }

    public T getFirstElement() {
        if (count == 0) {
            throw new IndexOutOfBoundsException("Список пуст");
        }
        return head.getData();
    }

    public T getElementData(int index) {
        if (index >= count || index < 0) {
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
        if (index == 0) {
            addFirstElement(data);
        } else if (index == count) {
            last.setNext(element);
            last = element;
            count++;
        } else {
            ListItem<T> tmp = getElement(index - 1);
            element.setNext(tmp.getNext());
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

    public T set(T data, int index) {
        if (index >= count || index < 0) {
            throw new IndexOutOfBoundsException("Не верный индекс");
        }

        ListItem<T> tmp = getElement(index);
        T oldData = tmp.getData();
        tmp.setData(data);
        return oldData;
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
        ListItem<T> tmp1;
        if (index + 1 == count) {
            last = getElement(index - 1);
            tmp1 = last.getNext();
            last.setNext(null);
        } else {
            ListItem<T> tmp2 = getElement(index - 1);
            tmp1 = tmp2.getNext();
            tmp2.setNext(tmp1.getNext());
        }
        --count;
        return tmp1.getData();
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
        if (count == 0) {
            throw new IndexOutOfBoundsException("Список пуст");
        }

        if (Objects.equals(head.getData(), data)) {
            head = head.getNext();
            --count;
            if (count == 0) {
                last = null;
            }
            return true;
        }
        for (ListItem<T> p = head; p != null && p.getNext() != null; p = p.getNext()) {
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

        return false;
    }

    public T getLast() {
        if (count == 0) {
            throw new IndexOutOfBoundsException("Список пуст");
        }
        return last.getData();
    }

    public void reverse() {
        if (head != null) {
            for (ListItem<T> p = head, prev = null, prevPrev = null; p != null; prev = p, p = p.getNext()) {
                if (p == head) {
                    last = p;
                    continue;
                }
                prev.setNext(prevPrev);
                prevPrev = prev;
                if (p.getNext() == null) {
                    head = p;
                    head.setNext(prev);
                    break;
                }
            }
        }
    }

    //•	копирование списка
    public SinglyLinkedList copyList() {
        SinglyLinkedList<T> newList = new SinglyLinkedList<>();
        for (ListItem<T> p = head; p != null; p = p.getNext()) {
            newList.addElement(p.getData());
        }
        return newList;
    }
}
