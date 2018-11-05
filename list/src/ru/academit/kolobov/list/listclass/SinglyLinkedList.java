package ru.academit.kolobov.list.listclass;

public class SinglyLinkedList<T> {
    private ListItem<T> head;
    private ListItem<T> last;//решил что будет требоваться достаточно часто, поэтому дешевле добавить переменную
    private int count;

    public SinglyLinkedList() {
    }

    //конструктор копирования
    public SinglyLinkedList(SinglyLinkedList<T> sourceList) {
        head = new ListItem<>(sourceList.head);
        last = sourceList.last;
        count = sourceList.count;
        //как конструкторо понимает какие элементы присваивать новому списку??? Или он берет что угодно не принадлежащие другим листам?
        for (ListItem<T> p = head.getNext(); p != null; p = p.getNext()) {
            ListItem<T> tmp = new ListItem<>(p);
        }
    }

    public int getSizeList() {
        return count;
    }

    public T getFirstElement() {
        return head.getData();
    }

    public T getElement(int index) {
        if (index + 1 > count || index < 0) {
            throw new IllegalArgumentException("Не верный индекс.");
        }
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
            if (index + 1 == count) {
                last = element;
            }
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
        if (index + 1 > count || index < 0) {
            throw new IllegalArgumentException("Не верный индекс");
        }

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
        if (index > count + 1 || index < 0) {
            throw new IllegalArgumentException("Не верный индекс");
        }
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
        if (head == null) {
            throw new IllegalArgumentException("Не верный индекс");
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
                if (p.getNext().getData().equals(data)) {
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
