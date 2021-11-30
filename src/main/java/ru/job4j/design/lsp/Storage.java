package ru.job4j.design.lsp;

import java.util.List;
import java.util.function.Predicate;

public interface Storage {

    List<Food> findBy(Predicate<Food> filter);

    boolean add(Food food);

    boolean remove(Food food);

    boolean accept(Food food);

    int size();

    default int remainingShelfLife(Food food) {
        long expiryDate = food.getExpiryDate().getTimeInMillis();
        long createDate = food.getCreateDate().getTimeInMillis();
        long percent = (expiryDate - createDate) / 100;
        long remain = (expiryDate - System.currentTimeMillis()) / percent;
        return Math.round(remain);
    }
}
