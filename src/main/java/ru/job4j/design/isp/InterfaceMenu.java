package ru.job4j.design.isp;

public interface InterfaceMenu {

    void addItem(Item item);
    void addSubItem(Item parent, Item childrenItem);
    void doIt(String numberOfItem);
    Item getItem(String number);
    void displayFullMenu();
    void displayOneItem(Item item);
}
