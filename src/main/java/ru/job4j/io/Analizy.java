package ru.job4j.io;

import java.io.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class Analizy {

    public static void unavailable(String source, String target) {
        AtomicBoolean active = new AtomicBoolean(false);

        try (BufferedReader in = new BufferedReader(new FileReader(source));
        PrintWriter out = new PrintWriter(new BufferedOutputStream(new FileOutputStream(target)))) {

            in.lines().forEach(line -> {
                String[] status = line.split(" ");
                int statusCode = Integer.parseInt(status[0]);
                boolean req = active.get();
                if (statusCode > 300 && !req) {
                    active.set(true);
                    out.printf("%s%s", status[1], ";");
                }
                if (statusCode <= 300 && req) {
                    active.set(false);
                    out.printf("%s%s%n", status[1], ";");
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String source = "server.log";
        String target = "report.txt";
        unavailable(source, target);
    }
}