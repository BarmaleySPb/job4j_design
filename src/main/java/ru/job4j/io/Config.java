package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class Config {

    private final String path;
    private final Map<String, String> values = new HashMap<String, String>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        String in = new Config(path).toString();

        in.lines().forEach(line -> {
            if (line.startsWith("=")) {
                throw new IllegalArgumentException();
            }
            if (!line.startsWith("#") && line.contains("=")) {
                String[] val = line.split("=");
                var i = val.length == 2 ? values.put(val[0], val[1]) : values.put(val[0], null);
            }
        });
    }

    public String value(String key) {
        return values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Config("./data/pair_without_comment.properties"));
    }
}