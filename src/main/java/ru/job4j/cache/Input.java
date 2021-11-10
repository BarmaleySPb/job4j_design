package ru.job4j.cache;

import java.nio.file.Path;


public interface Input {

    String askStr(String question);

    int askInt(String question);

    Path askFiles(String question);
}
