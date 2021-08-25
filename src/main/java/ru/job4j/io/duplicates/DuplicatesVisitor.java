package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    List<Path> duplicates = new ArrayList<>();
    Set<FileProperty> findDupl = new HashSet<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if (!findDupl.add(new FileProperty(file.toFile().length(), file.getFileName().toString()))) {
            duplicates.add(file.toAbsolutePath());
        }
        return super.visitFile(file, attrs);
    }
}