package ru.academit.kolobov.arraylist;

import java.util.*;

public class MyArrayList<T> implements List<T> {
    private int length = 0;
    private int modCount = 0;
    private T[] items;


    public MyArrayList() {
        items = (T[]) new Object[10];
    }

    public MyArrayList(int capacity) {
        items = (T[]) new Object[capacity];
    }


    public MyArrayList(Object[] array) {
        items = (T[]) new Object[array.length];
        if (array.length >= items.length) {
            increaseCapacity(array.length);
        }
        System.arraycopy(array, 0, items, 0, array.length);
        length = array.length;
    }

    private void increaseCapacity(int s) {
        int newSize = (s > length) ? s : length;
        items = Arrays.copyOf(items, items.length + newSize);
    }

    public void ensureCapacity(int capacity) {
        if (items.length >= capacity) {
            return;
        }
        items = Arrays.copyOf(items, capacity);
    }

    public void trimToSize() {
        if (items.length == length) {
            return;
        }
        items = Arrays.copyOf(items, length);
    }

    @Override
    public int size() {
        return length;
    }

    public int getCapacity() {
        return items.length;
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
    public T[] toArray() {
        return Arrays.copyOf(items, length);
    }

    @Override
    public <T> T[] toArray(T[] a) {
        if (a.length <= length) {
            return (T[]) toArray();
        }
        System.arraycopy(items, 0, a, 0, length);
        if (a.length > length) {
            a[length] = null;
        }
        return a;
    }

    @Override
    public boolean add(T element) {
        if (length + 1 >= items.length) {
            increaseCapacity(1);
        }
        items[length] = element;
        length++;
        modCount++;
        return true;
    }

    @Override
    public void add(int index, T element) {
        if (index > length || index < 0) {
            throw new IndexOutOfBoundsException("не корректный индекс");
        }

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
            List tmp = subList(index, length);
            length = index;
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
    public T get(int index) {
        if (index >= length || index < 0) {
            throw new IndexOutOfBoundsException("не корректный индекс");
        }
        return items[index];
    }

    @Override
    public T set(int index, T element) {
        if (index >= length || index < 0) {
            throw new IndexOutOfBoundsException("не корректный индекс");
        }
        T tmpE = items[index];
        items[index] = element;
        return tmpE;
    }

    @Override
    public T remove(int index) {
        if (index >= length || index < 0) {
            throw new IndexOutOfBoundsException("не корректный индекс");
        }

        T element;
        element = items[index];
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
        for (Object e : c) {
            if (!contains(e)) {
                return false;
            }
        }
        return true;
    }


    @Override
    public Iterator<T> iterator() {
        return new MyListIterator();
    }

    private class MyListIterator implements Iterator<T> {
        private int currentIndex = -1;
        private int iteratorModCount = modCount;

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
            return items[currentIndex];
        }
    }
}
