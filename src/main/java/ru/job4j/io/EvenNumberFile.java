package ru.job4j.io;

import java.io.FileInputStream;

public class EvenNumberFile {
    public static void main(String[] args) {
        try (FileInputStream in = new FileInputStream("even.txt")) {
            StringBuilder text = new StringBuilder();
            int read;
            while ((read = in.read()) != -1) {
                text.append((char) read);
            }
            String[] numbers = text.toString().split(System.lineSeparator());
            for (String num : numbers) {
                System.out.println(Integer.parseInt(num) % 2 == 0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
