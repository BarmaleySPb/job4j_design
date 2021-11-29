package ru.job4j.design.lsp;

import java.util.List;

public interface Strategy {
    void movementFood(List<Storage> storages, Food food);
}
