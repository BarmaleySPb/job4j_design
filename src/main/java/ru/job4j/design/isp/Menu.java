package ru.job4j.design.isp;

import java.util.*;


public class Menu<I> implements InterfaceMenu<I> {

    private final Item<I> menu;
    int numberOfItem = 0;

    public Menu(final I menu) {
        this.menu = new Item<>(menu);
        this.menu.setNumber(numberOfItem++ + "");
    }

    @Override
    public boolean add(I parentName, I childName, Action action) {
        Optional<Item<I>> parentItem = getItem(parentName);
        String number;
        if (parentItem.isPresent()) {
            if (parentItem.get().getNumber().equals("0")) {
                number = numberOfItem++ + ".";
            } else {
                number = parentItem.get().getNumber() + (parentItem.get().getChildrenItems().size() + 1) + ".";
            }
            Item<I> childItem = new Item<>(childName, action);
            childItem.setNumber(number);
            parentItem.get().getChildrenItems().add(childItem);
            return true;
        }
        return false;
    }

    @Override
    public Action select(I itemName) {
        return getItem(itemName).get().getAction();
    }

    @Override
    public String print() {
        StringBuilder result = new StringBuilder()
                .append(menu.getName())
                .append(System.lineSeparator());
        List<Item<I>> list = menu.getChildrenItems();
        list.forEach(item -> result.append(printOneItem(item))
                  .append(System.lineSeparator()));
        return result.toString();
    }

    private String printOneItem(Item<I> item) {
        StringBuilder result = new StringBuilder()
                .append(item.getNumber())
                .append(" ")
                .append(item.getName());
        if (item.getChildrenItems().size() > 0) {
            item.getChildrenItems().forEach(iItem -> {
                int steps = item.getNumber().split("\\.").length;
                result.append(System.lineSeparator())
                .append("  ".repeat(steps))
                .append(printOneItem(iItem));
            });
        }
        return result.toString();
    }

    private Optional<Item<I>> getItem(I itemName) {
        Optional<Item<I>> rsl = Optional.empty();
        Queue<Item<I>> data = new LinkedList<>();
        data.offer(this.menu);
        while (!data.isEmpty()) {
            Item<I> item = data.poll();
            if (item.getName().equals(itemName)) {
                rsl = Optional.of(item);
                break;
            }
            data.addAll(item.getChildrenItems());
        }
        return rsl;
    }
}
