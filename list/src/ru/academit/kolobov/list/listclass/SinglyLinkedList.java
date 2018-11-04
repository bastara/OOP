package ru.academit.kolobov.list.listclass;

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

    public T getElement(int index) {
        int countElement = 0;
        for (ListItem<T> p = head; p != null; p = p.getNext()) {
            if (countElement == index) {
                return p.getData();
            }
            countElement++;
        }
        return null;
    }

    public void addFirstElement(T data) {
        ListItem<T> element = new ListItem<>();
        element.setData(data);

        element.setNext(head);
        head = element;
    }

    public void addElement(T data) {
        ListItem<T> element = new ListItem<>();
        element.setData(data);

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
        if (index > count + 1 || index < 0) {
            throw new IllegalArgumentException("Не верный индекс");
        }

        ListItem<T> element = new ListItem<>();
        element.setData(data);

        if (head == null && index == 0) {
            head = element;
            last = element;
            count++;
        } else if (index == count + 1) {
            last.setNext(element);
            last = element;
            count++;
        } else if (index == 0) {
            addFirstElement(data);
        } else {
            int countElement = 0;
            ListItem<T> tmp = null;
            for (ListItem<T> p = head; p != null; p = p.getNext()) {
                if (countElement == index) {
                    element.setNext(p);
                    tmp.setNext(element);
                    break;
                }
                if (countElement + 1 == index) {
                    tmp = p;
                }
                countElement++;
            }
        }
    }

    public void print() {
        for (ListItem<T> p = head; p != null; p = p.getNext()) {
            System.out.println(p.getData());
        }
    }

    public void change(T data, int index) {
        int countElement = 0;
        for (ListItem<T> p = head; p != null; p = p.getNext()) {
            if (countElement == index) {
                p.setData(data);
                break;
            }
            countElement++;
        }
    }

    public ListItem delete(int index) {
        if (index == 0) {
            return deleteFirst();
        }
        int countElement = 0;
        ListItem<T> tmp = null;
        for (ListItem<T> p = head; p != null; p = p.getNext()) {
            if (countElement + 1 == index) {
                tmp = p.getNext();
                p.setNext(p.getNext().getNext());
            }
            countElement++;
        }
        return tmp;
    }


    public ListItem<T> deleteFirst() {
        ListItem<T> tmp = head;
        head = head.getNext().getNext();
        return tmp;
    }

    public boolean deleteValue(T data) {
        if (head.getData().equals(data)) {
            head = head.getNext();
            return true;
        } else {
            for (ListItem<T> p = head; p != null; p = p.getNext()) {
                if (p.getNext().getData().equals(data)) {
                    p.setNext(p.getNext().getNext());
                    return true;
                }
            }
        }
        return false;
    }
}
