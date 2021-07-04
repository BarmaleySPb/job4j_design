package ru.job4j.collection;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;

public class SimpleArray<T> implements Iterable<T> {

    private T[] models;
    private int lastIndex = 0;
    private int size = 1;
    private static int modCount = 0;

    public SimpleArray() {
        models = (T[]) new Object[size];
    }

    public SimpleArray(int size) {
        models = (T[]) new Object[size];
    }

    public T get(int index) {
        Objects.checkIndex(index, lastIndex);
        return models[index];
    }

    public void add(T model) {
        if (lastIndex == size) {
            models = Arrays.copyOf(models, models.length * 2);
            size++;
        }
        models[lastIndex] = model;
        lastIndex++;
        modCount++;
    }

    public int size() {
        return size;
    }

    public static int getModCount() {
        return modCount;
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayIterator<T>(models, lastIndex, modCount);
    }
}