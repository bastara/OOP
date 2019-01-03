package ru.academit.kolobov.arraylist;

import java.util.*;

public class MyArrayList<T> implements List {
    private Object[] items = new Object[10];
    private int length = 0;
    private int capacity = 10;


    public MyArrayList(Object[] items, int capacity) {
        this.items = items;
        this.length = length;
    }

    public MyArrayList(int capacity) {
        this.capacity = capacity;
    }

    public MyArrayList() {
    }

    public MyArrayList(Object[] array) {
        if (array.length >= capacity) {
            increaseCapacity(array.length);
        }
        for (int i = 0; i < array.length; i++) {
            items[i] = array[i];
        }
        length = array.length;
    }

    private void increaseCapacity(int s) {
        // в 2 раза не слишком много?
        int newSize = (s > length) ? s : length;
        items = Arrays.copyOf(items, capacity + newSize);
        capacity += newSize;
    }

    @Override
    public int size() {
        return length;
    }

    public int getCapacity() {
        return capacity;
    }

    @Override
    public boolean isEmpty() {
        return length == 0;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < length; i++) {
            str.append(items[i]).append(System.lineSeparator());
        }
        return str.toString();
    }

    @Override
    public boolean contains(Object o) {
        for (int i = 0; i < length; i++) {
            if (Objects.equals(items[i], o)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[length];
        for (int i = 0; i < length; i++) {
            array[i] = items[i];
        }
        return array;
    }

    @Override
    public boolean add(Object element) {
        if (length + 1 >= capacity) {
            increaseCapacity(1);
        }
        items[length] = element;
        length++;
        return items[length - 1] == element;
    }

    @Override
    public boolean addAll(Collection c) {
        Object[] a = c.toArray();
        int tmpLength = length;

        if (length + a.length >= items.length) {
            increaseCapacity(a.length);
        }
        for (int i = length; i < length + a.length; i++) {
            items[i] = a[i - length];
        }
        length += a.length;
        return tmpLength != length;
    }

    @Override
    public boolean addAll(int index, Collection c) {
        int tmpLength = length;

        if (index < length) {
            MyArrayList<T> tmp = (MyArrayList) subList(index, length);
            length = length - (length - index);
            addAll(c);
            addAll(tmp);
            return true;
        } else if (index == length) {
            addAll(c);
            return true;
        } else {
            length = index;
            addAll(c);
        }

        return tmpLength != length;
    }

    @Override
    public List subList(int fromIndex, int toIndex) {
        Object[] a = new Object[toIndex - fromIndex];
        for (int i = 0; i < toIndex - fromIndex; i++) {
            a[i] = items[i + fromIndex];
        }
        MyArrayList<T> tmp = new MyArrayList<>(a);
        return tmp;
    }

    @Override
    public void clear() {
        for (int i = 0; i < length; i++) {
            items[i] = null;
        }
        length = 0;
    }

    @Override
    public Object get(int index) {
        if (index >= length || index < 0) {
            throw new IndexOutOfBoundsException("не корректный индекс");
        }
        return items[index];
    }

    @Override
    public Object set(int index, Object element) {
        if (index >= length || index < 0) {
            throw new IndexOutOfBoundsException("не корректный индекс");
        }
        Object tmpE = items[index];
        items[index] = element;
        return tmpE;
    }

    @Override
    public void add(int index, Object element) {
        if (length >= items.length) {
            increaseCapacity(1);
        }
        items[length] = element;
        ++length;
    }

    @Override
    public T remove(int index) {
        T element;

        if (index >= length || index < 0) {
            throw new IndexOutOfBoundsException("не корректный индекс");
        }

        element = (T) items[index];
        System.arraycopy(items, index + 1, items, index, length - index - 1);
        --length;
        return element;
    }

    @Override
    public boolean remove(Object o) {
        int tmpSize = length;
        remove(indexOf(o));
        return tmpSize != length;
    }

    @Override
    public boolean removeAll(Collection c) {
        int tmpSize = length;
        for (Object e : c) {
            remove(e);
        }
        return tmpSize != length;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < length; i++) {
            if (Objects.equals(items[i], o)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int i = length; i > 0; i--) {
            if (Objects.equals(items[i], o)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public ListIterator listIterator() {
        //TODO не нужен
        return null;
    }

    @Override
    public ListIterator listIterator(int index) {
        //TODO не нужен
        return null;
    }

    @Override
    public boolean retainAll(Collection c) {
        return false;
    }

    @Override
    public boolean containsAll(Collection c) {
        return false;
    }

    @Override
    public Object[] toArray(Object[] a) {
        return new Object[0];
    }

    @Override
    public Iterator iterator() {
        return null;
    }
}
