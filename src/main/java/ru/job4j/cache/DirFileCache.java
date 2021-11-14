package ru.job4j.cache;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DirFileCache extends AbstractCache<String, String> {

    private final String cachingDir;

    public DirFileCache(String cachingDir) {
        this.cachingDir = cachingDir;
    }

    @Override
    protected String load(String key) {
        String value = "";
        try {
            value = Files.readString(Paths.get(cachingDir, key), StandardCharsets.UTF_8);
        } catch (Exception e) {
            System.out.println("File " + key + " not exist!!!");
        }
        return value;
    }
}