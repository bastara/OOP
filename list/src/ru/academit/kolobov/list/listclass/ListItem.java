package ru.academit.kolobov.list.listclass;

public class ListItem<T> {
    private T data;
    private ListItem<T> next;

    ListItem(T data) {
        this.data = data;
    }

    ListItem(ListItem<T> sourceItem) {
        this.data = sourceItem.data;
        this.next = sourceItem.next;
    }

    public ListItem(T data, ListItem<T> next) {
        this.data = data;
        this.next = next;
    }

    public T getData() {
        return data;
    }

    void setData(T data) {
        this.data = data;
    }

    ListItem<T> getNext() {
        return next;
    }

    void setNext(ListItem<T> next) {
        this.next = next;
    }
}
