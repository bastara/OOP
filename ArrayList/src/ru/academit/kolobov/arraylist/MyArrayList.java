package ru.academit.kolobov.arraylist;

import java.util.*;

public class MyArrayList<T> implements List<T> {
    private int length = 0;
    private int modCount = 0;
    private T[] items;


    @SuppressWarnings("unused")
    public MyArrayList() {
        //noinspection unchecked
        items = (T[]) new Object[10];
    }

    MyArrayList(int capacity) {
        //noinspection unchecked
        items = (T[]) new Object[capacity];
    }

    MyArrayList(T[] array) {
        //noinspection unchecked
        items = (T[]) new Object[array.length];
        increaseCapacity(array.length);
        //noinspection SuspiciousSystemArraycopy
        System.arraycopy(array, 0, items, 0, array.length);
        length = array.length;
    }

    //видимо зря я его сделал, нужен следующий метод
    private void increaseCapacity(int sizeItems) {
        int newSize = Math.max(sizeItems, length);
        items = Arrays.copyOf(items, items.length + newSize);
    }

    @SuppressWarnings("unused")
    public void ensureCapacity(int capacity) {
        if (items.length >= capacity) {
            return;
        }
        items = Arrays.copyOf(items, capacity);
    }

    @SuppressWarnings("unused")
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

    int getCapacity() {
        return items.length;
    }

    @Override
    public boolean isEmpty() {
        return length == 0;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("[");
        for (int i = 0; i < length; i++) {
            if (i > 0) {
                str.append("],[");
            }
            str.append(items[i]);
        }
        str.append("]");
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
    public <T> T[] toArray(T[] array) {
        if (array.length <= length) {
            //noinspection unchecked
            return (T[]) toArray();
        }
        //noinspection SuspiciousSystemArraycopy
        System.arraycopy(items, 0, array, 0, length);
        if (array.length > length) {
            array[length] = null;
        }
        return array;
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

        if (index == length) {
            add(element);
            return;
        }
        increaseCapacity(1);
        System.arraycopy(items, index, items, index + 1, items.length - index - 1);
        items[index] = element;
        ++length;
        ++modCount;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        if (c.size() == 0) {
            return false;
        }

        if (length + c.size() >= items.length) {
            increaseCapacity(c.size());
        }

        for (Object e : c) {
            add((T) e);
        }

        modCount++;
        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        if (c.size() == 0) {
            return false;
        }

        if (index < length) {
            int p = 0;
            for (Object e : c) {
                add(index + p, (T) e);
                p++;
            }
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

    @SuppressWarnings("unchecked")
    @Override
    public List subList(int fromIndex, int toIndex) {
        //TODO не нужен
        return null;
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

        T element = items[index];
        System.arraycopy(items, index + 1, items, index, length - index - 1);
        --length;
        modCount++;
        return element;
    }

    @Override
    public boolean remove(Object o) {
        int tmpModCount = modCount;
        remove(indexOf(o));
        return tmpModCount != modCount;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        int tmpModCount = modCount;
        for (Object e : c) {
            while (remove(e)) {
            }
        }
        return tmpModCount != modCount;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        int tmpModCount = modCount;
        for (int i = 0; i < length; ) {
            if (!c.contains(items[i])) {
                remove(i);
            }
            i++;
        }
        return tmpModCount != modCount;
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
        for (int i = length - 1; i >= 0; i--) {
            if (Objects.equals(items[i], o)) {
                return i;
            }
        }
        return -1;
    }

    @SuppressWarnings("unchecked")
    @Override
    public ListIterator listIterator() {
        //TODO не нужен
        //noinspection ConstantConditions
        return null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public ListIterator listIterator(int index) {
        //TODO не нужен
        //noinspection ConstantConditions
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
