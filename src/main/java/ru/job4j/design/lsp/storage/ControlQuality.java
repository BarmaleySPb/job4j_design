package ru.job4j.design.lsp.storage;

import java.util.ArrayList;
import java.util.List;


public class ControlQuality {
    private final List<Storage> storages;

    public static final int DISCOUNT = 20;


    public ControlQuality(List<Storage> storages) {
        this.storages = storages;
    }

    public void distribute(Food food) {
        storages.forEach(storage -> {
            if (storage.accept(food)) {
                storage.add(food);
            }
        });
    }

    public void resort() {
        ArrayList<Food> foods = new ArrayList<>();
        for (Storage storage : storages) {
            foods.addAll(storage.getListOfFood());
            storage.removeAllFood();
        }
        foods.forEach(this::distribute);
    }
}
