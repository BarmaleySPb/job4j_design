package ru.job4j.design.isp;

import java.util.*;


public class Menu implements InterfaceMenu {
    TreeMap<String, Item> menu;
    int numberOfItem = 1;

    Menu() {
        this.menu = new TreeMap<>();
    }

    @Override
    public void addItem(Item item) {
        item.setNumber(numberOfItem++ + ".");
        menu.put(item.getNumber(), item);
    }

    @Override
    public void addSubItem(Item parent, Item childrenItem) {
        childrenItem.setLevelSub(parent.getLevelSub() + 1);
        childrenItem.setNumber(parent.getNumber() + (parent.getChildrenItems().size() + 1) + ".");
        menu.put(childrenItem.getNumber(), childrenItem);
        parent.getChildrenItems().put(childrenItem.getNumber(), childrenItem);
    }

    @Override
    public void doIt(String numberOfItem) {
        menu.get(numberOfItem).getAction().action();
    }

    @Override
    public Item getItem(String number) {
        return menu.get(number);
    }

    @Override
    public void displayFullMenu() {
        displayList(menu);
    }

    @Override
    public void displayOneItem(Item item) {
        System.out.print(item.getNumber());
        System.out.println(" " + item.getName());
        displayList(item.getChildrenItems());
    }

    private void displayList(TreeMap<String, Item> treeMap) {
        treeMap.forEach((key, value) -> {
            int steps = key.split("\\.").length - 1;
            for (int i = 0; i < steps; i++) {
                System.out.print("  ");
            }
            System.out.print(key);
            System.out.println(" " + value.getName());
        });
    }
}
