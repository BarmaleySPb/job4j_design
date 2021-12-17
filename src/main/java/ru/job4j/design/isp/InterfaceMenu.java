package ru.job4j.design.isp;


public interface InterfaceMenu<I> {

    boolean add(I parentName, I childName, Action action);
    Action select(I itemName);
    String print();
}
