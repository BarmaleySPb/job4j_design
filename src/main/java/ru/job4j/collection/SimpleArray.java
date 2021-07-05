package ru.job4j.collection;

import java.util.*;

public class SimpleArray<T> implements Iterable<T> {

    private T[] models;
    private int lastIndex = 0;
    private int size = 1;
    private int modCount = 0;

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

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int point = 0;
            private final int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                return point < lastIndex;
            }

            @Override
            public T next() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return models[point++];
            }
        };
    }
}