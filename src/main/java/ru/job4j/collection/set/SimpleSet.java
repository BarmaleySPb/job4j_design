package ru.job4j.collection.set;

import ru.job4j.collection.SimpleArray;

import java.util.Iterator;

public class SimpleSet<T> implements Set<T> {

    private SimpleArray<T> set = new SimpleArray<>();

    @Override
    public boolean add(T value) {
        if (value == null) {
            for (T t : set) {
                if (t == null) {
                    return false;
                }
            }
            set.add(value);
            return true;
        }
        for (T t : set) {
            if (t == null) {
                continue;
            }
            if (t.equals(value)) {
                return false;
            }
        }
        set.add(value);
        return true;
    }

    @Override
    public boolean contains(T value) {
        if (value == null) {
            for (T t : set) {
                if (t == null) {
                    return true;
                }
            }
        }
        for (T t : set) {
            if (t == null) {
                continue;
            }
            if (t.equals(value)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return set.iterator();
    }
}