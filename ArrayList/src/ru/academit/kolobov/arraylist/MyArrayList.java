package ru.academit.kolobov.arraylist;

import java.util.*;

public class MyArrayList<T> implements List {
    private Object[] items = new Object[10];
    private int length = 0;
    private int capacity = 10;
    private int modCount = 0;


    public MyArrayList(Object[] items, int capacity) {
        this.items = items;
        this.capacity = capacity;
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
        System.arraycopy(array, 0, items, 0, array.length);
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
        System.arraycopy(items, 0, array, 0, length);
        return array;
    }

    @Override
    public boolean add(Object element) {
        if (length + 1 >= capacity) {
            increaseCapacity(1);
        }
        items[length] = element;
        length++;
        modCount++;
        return true;
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
    public boolean addAll(Collection c) {
        Object[] a = c.toArray();

        if (c.size() == 0) {
            return false;
        }

        if (length + a.length >= items.length) {
            increaseCapacity(a.length);
        }
//TODo через итератор
        if (length + a.length - length >= 0)
            System.arraycopy(a, 0, items, length, length + a.length - length);
        length += a.length;
        modCount++;
        return true;
    }

    @Override
    public boolean addAll(int index, Collection c) {
        if (c.size() == 0) {
            return false;
        }

        if (index < length) {
            //TODO проверить работоспособность
            //TODo через итератор
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

        modCount++;
        return true;
    }

    @Override
    public List subList(int fromIndex, int toIndex) {
        if (fromIndex > length || fromIndex < 0 || toIndex > length || toIndex < 0 || fromIndex >= toIndex) {
            throw new IndexOutOfBoundsException("не корректный индекс");
        }

        Object[] a = new Object[toIndex - fromIndex];
        if (toIndex - fromIndex >= 0) System.arraycopy(items, fromIndex, a, 0, toIndex - fromIndex);
        return new MyArrayList<>(a);
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
    public T remove(int index) {
        if (index >= length || index < 0) {
            throw new IndexOutOfBoundsException("не корректный индекс");
        }

        T element;
        element = (T) items[index];
        System.arraycopy(items, index + 1, items, index, length - index - 1);
        --length;
        modCount++;
        return element;
    }

    @Override
    public boolean remove(Object o) {
        int tmpModCount = modCount;
        remove(indexOf(o));
        return tmpModCount == modCount;
    }

    @Override
    public boolean removeAll(Collection c) {
        int tmpModCount = modCount;
        for (Object e : c) {
            remove(e);
        }
        return tmpModCount == modCount;
    }

    @Override
    public boolean retainAll(Collection c) {
        int tmpModCount = modCount;
        for (int i = 0; i < length; i++) {
            boolean check = false;
            for (Object e : c) {
                if (Objects.equals(items[i], e)) {
                    check = true;
                    break;
                }
            }
            if (!check) {
                remove(i);
                i--;
                length--;
            }
        }
        return tmpModCount == modCount;
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
        for (int i = length - 1; i > 0; i--) {
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
    public boolean containsAll(Collection c) {
        return false;
    }

    @Override
    public Object[] toArray(Object[] a) {
        return new Object[0];
    }

    @Override
    public Iterator<T> iterator() {
        return new MyListIterator();
    }

    private class MyListIterator implements Iterator<T> {
        private int currentIndex = -1;
        private int iteratorModCount = modCount;

        //TODO modCount

        public boolean hasNext() {
            return currentIndex + 1 < length;
        }

        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException("Нет следущего элемента");
            }

            if (modCount != iteratorModCount) {
                throw new ConcurrentModificationException("Список изменился");
            }
            ++currentIndex;
            return (T) items[currentIndex];
        }
    }
}
