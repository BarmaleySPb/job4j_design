package ru.job4j.search;

import java.nio.file.Path;
import java.util.function.Predicate;

public class SearchByRegex implements SearchByType {

    @Override
    public Predicate<Path> search(String file) {
        return p -> p.toFile().getName().matches(file);
    }
}