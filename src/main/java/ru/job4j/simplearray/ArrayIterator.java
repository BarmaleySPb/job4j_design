package ru.job4j.simplearray;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayIterator<T> implements Iterator<T> {

    private final T[] data;
    private final int lastIndex;
    private int point = 0;

    public ArrayIterator(T[] data, int lastIndex) {
        this.data = data;
        this.lastIndex = lastIndex;
    }

    @Override
    public boolean hasNext() {
        return point < lastIndex;
    }

    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return data[point++];
    }
}
