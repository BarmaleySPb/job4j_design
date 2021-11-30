package ru.job4j.design.lsp;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static ru.job4j.design.lsp.ControlQuality.DISCOUNT;

public class Shop implements Storage {

    private final List<Food> shop = new ArrayList<>();

    @Override
    public List<Food> findBy(Predicate<Food> filter) {
        return shop.stream().filter(filter).collect(Collectors.toList());
    }

    @Override
    public boolean add(Food food) {
        if (accept(food)) {
            return shop.add(food);
        }
        return false;
    }

    @Override
    public boolean remove(Food food) {
        return shop.remove(food);
    }

    @Override
    public boolean accept(Food food) {
        int remainingPercent = remainingShelfLife(food);
        if (remainingPercent >= 25 && remainingPercent < 75) {
            return true;
        } else if (remainingPercent < 25 && remainingPercent > 0) {
            food.setDiscount(DISCOUNT);
            return true;
        }
        return false;
    }

    @Override
    public int size() {
        return shop.size();
    }
}
