package ru.job4j.cache;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class ConsoleInput implements Input {

    private final Scanner scanner = new Scanner(System.in);

    @Override
    public String askStr(String question) {
        System.out.print(question);
        return scanner.nextLine();
    }

    @Override
    public int askInt(String question) {
        return Integer.parseInt(askStr(question));
    }

    @Override
    public Path askFiles(String question) {
        return Paths.get(askStr(question));
    }
}
