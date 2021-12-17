package ru.job4j.design.isp;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class Item<I> {

    private I name;
    private String number;
    private List<Item<I>> childrenItems = new ArrayList<>();
    private Action action;

    Item() {

    }

    Item(I name) {
        this.name = name;
    }

    Item(I name, Action action) {
        this.name = name;
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
        return name.equals(item.name) && number.equals(item.number)
                && Objects.equals(childrenItems, item.childrenItems)
                && Objects.equals(action, item.action);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, number, childrenItems, action);
    }

    public I getName() {
        return name;
    }

    public void setName(I name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public List<Item<I>> getChildrenItems() {
        return childrenItems;
    }

    public void setChildrenItems(List<Item<I>> childrenItems) {
        this.childrenItems = childrenItems;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }
}
