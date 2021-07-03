package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayIterator<T> implements Iterator<T> {

    private final T[] data;
    private final int lastIndex;
    private final int expectedModCount;
    private int point = 0;

    public ArrayIterator(T[] data, int lastIndex, int modCount) {
        this.data = data;
        this.lastIndex = lastIndex;
        this.expectedModCount = modCount;
    }

    @Override
    public boolean hasNext() {
        return point < lastIndex;
    }

    @Override
    public T next() {
        if (expectedModCount != SimpleArray.modCount) {
            throw new ConcurrentModificationException();
        }
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return data[point++];
    }
}