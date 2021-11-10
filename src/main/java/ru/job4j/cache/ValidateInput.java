package ru.job4j.cache;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ValidateInput implements Input {

    private final Input in;

    public ValidateInput(Input input) {
        this.in = input;
    }

    @Override
    public String askStr(String question) {
        return in.askStr(question);
    }

    @Override
    public int askInt(String question) {
        boolean invalid = true;
        int value = -1;
        do {
            try {
                value = in.askInt(question);
                invalid = false;
            } catch (NumberFormatException nfe) {
                System.out.println("Please enter validate data again.");
            }
        } while (invalid);
        return value;
    }

    @Override
    public Path askFiles(String question) {
        boolean invalid = true;
        Path value = Paths.get("");
        while (invalid) {
            value = in.askFiles(question);
            if (!Files.exists(Paths.get(String.valueOf(value)))) {
                System.out.println("Directory not found. Try again.");
                continue;
            }
            invalid = false;
        }
        return value;
    }
}
