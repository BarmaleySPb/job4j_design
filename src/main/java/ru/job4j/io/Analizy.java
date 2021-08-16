package ru.job4j.io;

import java.io.*;
import java.util.*;

public class Analizy {

    public static void unavailable(String source, String target) {
        Map<String, String> map = new LinkedHashMap<>();

        try (BufferedReader in = new BufferedReader(new FileReader(source))) {
            in.lines().forEach(line -> {
                String[] status = line.split(" ");
                map.put(status[1], Integer.parseInt(status[0]) > 300 ? "start" : "finish");
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

        boolean active = false;
        try (PrintWriter out = new PrintWriter(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (String key : map.keySet()) {
                if (map.get(key).contains("start") && !active) {
                    active = true;
                    out.printf("%s%s", key, ";");
                }
                if (map.get(key).contains("finish") && active) {
                    active = false;
                    out.printf("%s%s%n", key, ";");
                }
            }
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