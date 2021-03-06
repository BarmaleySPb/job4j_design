package ru.job4j.io;

import java.io.File;

public class Dir {
    private static final int GB_SIZE = 1073741824;
    private static final int KB_SIZE = 1024;

    public static void main(String[] args) {

        File file = new File("C:\\Users\\Barmaley\\IdeaProjects");
        if (!file.exists()) {
            throw new IllegalArgumentException(String.format("Not exist %s", file.getAbsoluteFile()));
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(String.format("Not directory %s", file.getAbsoluteFile()));
        }
        System.out.printf("Total disk size: %s Gb%n", file.getTotalSpace() / GB_SIZE);
        for (File subfile : file.listFiles()) {
            System.out.printf("%s: %s Kb%n", subfile.getName(), subfile.length() / KB_SIZE);
        }
    }
}