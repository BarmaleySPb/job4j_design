package ru.job4j.design.lsp;

import java.util.List;
import java.util.function.Predicate;

public interface Storage {

    List<Food> findBy(Predicate<Food> filter);

    void add(Food food);

    void remove(Food food);

    int size();
}
