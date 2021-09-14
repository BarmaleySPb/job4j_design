package ru.job4j.search;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public class SearchByRegex implements SearchByType {

    @Override
    public Predicate<Path> search(String file) {
        return p -> Pattern.compile(file).matcher(p.toFile().getName()).matches();
    }
}