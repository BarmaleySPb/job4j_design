package ru.job4j.collection;

import java.util.Iterator;
import java.util.Objects;

public class SimpleArray<T> implements Iterable<T> {

    private T[] models;
    private int lastIndex = 0;
    private int size = 2;

    public static int modCount = 0;

    public SimpleArray() {
        models = (T[]) new Object[size];
    }

    public T get(int index) {
        Objects.checkIndex(index, lastIndex);
        return models[index];
    }

    public void add(T model) {
        if (lastIndex == size) {
            T[] temp = models;
            models = (T[]) new Object[size + 1];
            System.arraycopy(temp, 0, models, 0, lastIndex);
            size++;
        }
        models[lastIndex] = model;
        lastIndex++;
        modCount++;
    }

    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayIterator<T>(models, lastIndex, modCount);
    }
}