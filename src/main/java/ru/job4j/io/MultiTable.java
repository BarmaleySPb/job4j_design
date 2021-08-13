package ru.job4j.io;

import java.io.FileOutputStream;

public class MultiTable {

    public static void tableTofile(int size) {
        try (FileOutputStream out = new FileOutputStream("matrix.txt")) {
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    out.write(Integer.toString((i + 1) * (j + 1)).getBytes());
                    out.write(System.lineSeparator().getBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        MultiTable.tableTofile(9);
    }
}
