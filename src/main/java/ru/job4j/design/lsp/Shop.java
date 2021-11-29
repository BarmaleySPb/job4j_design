package ru.job4j.design.lsp;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Shop implements Storage {

    private final List<Food> shop = new ArrayList<>();

    @Override
    public List<Food> findBy(Predicate<Food> filter) {
        return shop.stream().filter(filter).collect(Collectors.toList());
    }

    @Override
    public void add(Food food) {
        shop.add(food);
    }

    @Override
    public void remove(Food food) {
        shop.remove(food);
    }

    @Override
    public int size() {
        return shop.size();
    }
}
