package ru.job4j.search;

import java.nio.file.Path;
import java.util.function.Predicate;

public interface SearchByType {

    Predicate<Path> search(String file);
}
