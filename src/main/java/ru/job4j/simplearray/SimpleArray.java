package ru.job4j.simplearray;

import java.util.Iterator;
import java.util.Objects;

public class SimpleArray<T> implements Iterable<T> {

    private T[] models;
    private int lastIndex = 0;

    public SimpleArray() {
        models = (T[]) new Object[10];
    }

    public void add(T model) {
        models[lastIndex] = model;
        lastIndex++;
    }

    public void set(int index, T model) {
        Objects.checkIndex(index, lastIndex);
        models[index] = model;
    }

    public void remove(int index) {
        Objects.checkIndex(index, lastIndex);
        System.arraycopy(models, index + 1, models, index, lastIndex);
        lastIndex--;
    }

    public T get(int index) {
        Objects.checkIndex(index, lastIndex);
        return models[index];
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayIterator<T>(models, lastIndex);
    }
}
