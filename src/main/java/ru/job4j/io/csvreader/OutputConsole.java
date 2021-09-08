package ru.job4j.io.csvreader;

public class OutputConsole implements Output {

    @Override
    public void print(Object obj) {
        System.out.print(obj);
    }
}
