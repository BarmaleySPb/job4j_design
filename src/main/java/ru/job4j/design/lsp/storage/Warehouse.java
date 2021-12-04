package ru.job4j.design.lsp.storage;

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
    public boolean add(Food food) {
        if (accept(food)) {
            return warehouse.add(food);
        }
        return false;
    }

    @Override
    public boolean remove(Food food) {
        return warehouse.remove(food);
    }

    @Override
    public boolean accept(Food food) {
        return remainingShelfLife(food) > 75;
    }

    @Override
    public int size() {
        return warehouse.size();
    }
}
