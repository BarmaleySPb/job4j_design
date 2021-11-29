package ru.job4j.design.lsp;

import java.util.List;

import static ru.job4j.design.lsp.ControlQuality.DISCOUNT;

public class MovingToShopAndSetDiscount implements Strategy {
    @Override
    public void movementFood(List<Storage> storages, Food food) {
       storages.forEach(storage -> {
           if (storage.getClass() == Shop.class) {
               food.setDiscount(DISCOUNT);
               storage.add(food);
           }
       });
    }
}
