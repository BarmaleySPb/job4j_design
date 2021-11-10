package ru.job4j.cache;

public interface Action {

    String name();

    boolean execute(DirFileCache dirFileCache);
}
