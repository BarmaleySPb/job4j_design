package ru.job4j.design.lsp;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Warehouse implements Storage {

    private final List<Food> warehouse = new ArrayList<>();

    @Override
    public List<Food> findBy(Predicate<Food> filter) {
        return warehouse.stream().filter(filter).collect(Collectors.toList());
    }

    @Override
    public void add(Food food) {
        warehouse.add(food);
    }

    @Override
    public void remove(Food food) {
        warehouse.remove(food);
    }

    @Override
    public int size() {
        return warehouse.size();
    }
}
