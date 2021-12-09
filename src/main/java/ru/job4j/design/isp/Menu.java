package ru.job4j.design.isp;

import java.util.*;


public class Menu implements InterfaceMenu {
    TreeMap<String, Item> menu;
    int numberOfItem = 1;

    Menu() {
        this.menu = new TreeMap<>();
    }

    @Override
    public boolean add(String parentName, Action action) {
        if (getItem(parentName) == null) {
            String number = numberOfItem++ + ".";
            Item parentItem = new Item(parentName, action);
            parentItem.setNumber(number);
            menu.put(number, parentItem);
            return true;
        }
        return false;
    }

    @Override
    public boolean add(String parentName, String childName, Action action) {
        if (getItem(parentName) != null) {
            Item parentItem = getItem(parentName);
            Item childItem = new Item(childName, action);
            childItem.setLevelSub(parentItem.getLevelSub() + 1);
            childItem.setNumber(parentItem.getNumber() + (parentItem.getChildrenItems().size() + 1) + ".");
            menu.put(childItem.getNumber(), childItem);
            parentItem.getChildrenItems().put(childItem.getNumber(), childItem);
            return true;
        }
        return false;
    }

    @Override
    public Action select(String itemName) {
        return getItem(itemName).getAction();
    }

    @Override
    public String print() {
        StringBuilder asd = new StringBuilder();
        menu.forEach((key, value) -> {
            int steps = key.split("\\.").length - 1;
            asd.append("  ".repeat(Math.max(0, steps)))
                    .append(key)
                    .append(" ")
                    .append(value.getName())
                    .append(System.lineSeparator());
        });
        return asd.toString();
    }

    private Item getItem(String nameItem) {
        for (Item item : menu.values()) {
            if (item.getName().equals(nameItem)) {
                return item;
            }
        }
        return null;
    }
}
