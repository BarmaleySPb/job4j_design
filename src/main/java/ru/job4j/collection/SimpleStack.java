package ru.job4j.collection;

public class SimpleStack<T> {
    private ForwardLinked<T> linked = new ForwardLinked<T>();

    public T pop() {
        T firstElement = linked.iterator().next();
        linked.deleteFirst();
        return firstElement;
    }

    public void push(T value) {
        linked.addFirst(value);
    }
}