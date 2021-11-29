package ru.job4j.design.lsp;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Trash implements Storage {

    private final List<Food> trash = new ArrayList<>();

    @Override
    public List<Food> findBy(Predicate<Food> filter) {
        return trash.stream().filter(filter).collect(Collectors.toList());
    }

    @Override
    public void add(Food food) {
        trash.add(food);
    }

    @Override
    public void remove(Food food) {
        trash.remove(food);
    }

    @Override
    public int size() {
        return trash.size();
    }
}
