package ru.job4j.design.isp;


public interface InterfaceMenu {

    boolean add(String parentName, Action action);
    boolean add(String parentName, String childName, Action action);
    Action select(String itemName);
    String print();
}
