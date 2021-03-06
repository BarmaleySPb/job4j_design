package ru.job4j.design.lsp.storage;

import java.util.List;
import java.util.function.Predicate;


public interface Storage {

    List<Food> findBy(Predicate<Food> filter);
    List<Food> getListOfFood();
    boolean add(Food food);
    boolean remove(Food food);
    boolean accept(Food food);
    void removeAllFood();
    int size();

    default int remainingShelfLife(Food food) {
        long expiryDate = food.getExpiryDate().getTimeInMillis();
        long createDate = food.getCreateDate().getTimeInMillis();
        long percent = (expiryDate - createDate) / 100;
        long remain = (expiryDate - System.currentTimeMillis()) / percent;
        return Math.round(remain);
    }
}
