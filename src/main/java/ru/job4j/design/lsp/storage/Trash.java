package ru.job4j.design.lsp.storage;

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
    public List<Food> getListOfFood() {
        return trash;
    }

    @Override
    public boolean add(Food food) {
        if (accept(food)) {
            return trash.add(food);
        }
        return false;
    }

    @Override
    public boolean remove(Food food) {
        return trash.remove(food);
    }

    @Override
    public boolean accept(Food food) {
        return remainingShelfLife(food) <= 0;
    }

    @Override
    public void removeAllFood() {
        trash.clear();
    }

    @Override
    public int size() {
        return trash.size();
    }
}
