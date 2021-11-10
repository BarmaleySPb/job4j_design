package ru.job4j.cache;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

public class DirFileCache extends AbstractCache<String, String> {

    private final String cachingDir;

    public DirFileCache(String cachingDir) {
        if (cachingDir.endsWith("/")) {
            this.cachingDir = cachingDir;
        } else {
            this.cachingDir = cachingDir + "/";
        }
    }

    @Override
    protected String load(String key) {
        String value = "";
        try (BufferedReader in = new BufferedReader(new FileReader(cachingDir + key, StandardCharsets.UTF_8))) {
            value = in.lines().collect(Collectors.joining(System.lineSeparator()));
        } catch (Exception e) {
            System.out.println("File " + key + " not exist!!!");
        }
        return value;
    }
}