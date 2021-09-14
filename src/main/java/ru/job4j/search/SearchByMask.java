package ru.job4j.search;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public class SearchByMask implements SearchByType {

    @Override
    public Predicate<Path> search(String file) {
        String newFile = file.replace(".", "\\.")
                .replace("*", ".+")
                .replace("?", ".");

        return p -> Pattern.compile(newFile).matcher(p.toFile().getName()).matches();
    }
}
