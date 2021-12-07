package ru.job4j.design.isp;

import java.util.Objects;
import java.util.TreeMap;


public class Item {

    private String name;
    private String number;
    private int levelSub;
    private TreeMap<String, Item> childrenItems;
    private Action action;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getLevelSub() {
        return levelSub;
    }

    public void setLevelSub(int levelSub) {
        this.levelSub = levelSub;
    }

    public TreeMap<String, Item> getChildrenItems() {
        return childrenItems;
    }

    public void setChildrenItems(TreeMap<String, Item> childrenItems) {
        this.childrenItems = childrenItems;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }
}
