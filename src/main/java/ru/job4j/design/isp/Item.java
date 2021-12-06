package ru.job4j.design.isp;

import java.util.Objects;
import java.util.TreeMap;


public class Item {

    String name;
    String number;
    int levelSub;
    TreeMap<String, Item> childrenItems;
    Action action;

    Item() {

    }

    Item(String name) {
        this.name = name;
        this.childrenItems = new TreeMap<>();
        this.levelSub = 0;
    }

    Item(String name, Action action) {
        this.name = name;
        this.childrenItems = new TreeMap<>();
        this.levelSub = 0;
        this.action = action;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Item)) {
            return false;
        }
        Item item = (Item) o;
        return levelSub == item.levelSub && name.equals(item.name) && number.equals(item.number)
                && Objects.equals(childrenItems, item.childrenItems)
                && Objects.equals(action, item.action);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, number, levelSub, childrenItems, action);
    }
}
