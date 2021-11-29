package ru.job4j.design.lsp;

import java.util.List;


public class ControlQuality {
    private final List<Storage> storages;
    private Strategy strategy;

    public static final int DISCOUNT = 20;

    public ControlQuality(List<Storage> storages) {
        this.storages = storages;
    }

    public void setStrategy(int percent) {
        if (percent > 75) {
            strategy = new MovingToWarehouse();
        }
        if (percent >= 25 && percent <= 75) {
            strategy = new MovingToShop();
        }
        if (percent < 25 && percent > 0) {
            strategy = new MovingToShopAndSetDiscount();
        }
        if (percent <= 0) {
            strategy = new MovingToTrash();
        }
    }

    void sorting(List<Food> foods) {
        for (Food food : foods) {
            setStrategy(remainingShelfLife(food));
            strategy.movementFood(storages, food);
        }
    }

    public int remainingShelfLife(Food food) {
        long expiryDate = food.getExpiryDate().getTimeInMillis();
        long createDate = food.getCreateDate().getTimeInMillis();
        long percent = (expiryDate - createDate) / 100;
        long remain = (expiryDate - System.currentTimeMillis()) / percent;
        return Math.round(remain);
    }
}
