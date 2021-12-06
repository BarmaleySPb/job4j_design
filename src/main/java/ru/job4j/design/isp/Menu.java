package ru.job4j.design.isp;

import java.util.*;


public class Menu {
    TreeMap<String, Item> menu;
    int numberOfItem = 1;

    Menu() {
        this.menu = new TreeMap<>();
    }

    public void addItem(Item item) {
        item.number = numberOfItem++ + ".";
        menu.put(item.number, item);
    }

    public void addSubItem(Item parent, Item childrenItem) {
        childrenItem.levelSub = parent.levelSub + 1;
        childrenItem.number = parent.number + (parent.childrenItems.size() + 1) + ".";
        menu.put(childrenItem.number, childrenItem);
        parent.childrenItems.put(childrenItem.number, childrenItem);
    }

    public void doIt(String numberOfItem) {
        menu.get(numberOfItem).action.action();
    }

    public Item getItem(String number) {
        return menu.get(number);
    }

    public void displayFullMenu() {
        displayList(menu);
    }

    public void displayOneItem(Item item) {
        System.out.print(item.number);
        System.out.println(" " + item.name);
        displayList(item.childrenItems);
    }

    private void displayList(TreeMap<String, Item> treeMap) {
        treeMap.forEach((key, value) -> {
            int steps = key.split("\\.").length - 1;
            for (int i = 0; i < steps; i++) {
                System.out.print("  ");
            }
            System.out.print(key);
            System.out.println(" " + value.name);
        });
    }
}
