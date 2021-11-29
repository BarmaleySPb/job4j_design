package ru.job4j.design.lsp;

import java.util.List;

public class MovingToTrash implements Strategy {
    @Override
    public void movementFood(List<Storage> storages, Food food) {
       storages.forEach(storage -> {
           if (storage.getClass() == Trash.class) {
               storage.add(food);
           }
       });
    }
}
