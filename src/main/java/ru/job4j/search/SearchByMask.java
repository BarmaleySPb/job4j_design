package ru.job4j.search;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public class SearchByMask implements SearchByType {

    @Override
    public Predicate<Path> search(String file) {
        file = file.replace(".", "\\.")
                .replace("*", ".+")
                .replace("?", ".");
        Pattern pattern = Pattern.compile(file);

        return p -> pattern.matcher(p.toFile().getName()).matches();
    }
}
