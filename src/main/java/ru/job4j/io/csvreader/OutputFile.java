package ru.job4j.io.csvreader;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

public class OutputFile implements Output {

    @Override
    public void print(Object obj) {
        try (PrintWriter out = new PrintWriter(new FileWriter(CSVReader.out, StandardCharsets.UTF_8, true))) {
            out.print(obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
