package ru.job4j.io.consolechat;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class ConsoleChat {

    private final String path;
    private final String botAnswers;
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        List<String> botReply = readPhrases();
        List<String> toLog = new LinkedList<>();
        boolean active = true;
        String bot = "";
        String answer = "";

        while (!answer.equals(OUT)) {
            Scanner scanner = new Scanner(System.in);
            answer = scanner.nextLine();

            switch (answer) {
                case STOP, OUT -> {
                    active = false;
                    toLog.add("command: " + answer);
                }
                case CONTINUE -> {
                    active = true;
                    toLog.add("command: " + answer);
                }
                default -> {
                    toLog.add("you: " + answer);
                    if (active) {
                        bot = botReply.get((int) (Math.random() * botReply.size()));
                        System.out.println(bot);
                        toLog.add("bot: " + bot);
                    }
                }
            }
        }
        saveLog(toLog);
    }

    private List<String> readPhrases() {
        List<String> phrases = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(botAnswers, StandardCharsets.UTF_8))) {
            in.lines().forEach(phrases::add);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return phrases;
    }

    private void saveLog(List<String> log) {
        try (PrintWriter out = new PrintWriter(new FileWriter(path, StandardCharsets.UTF_8, false))) {
            log.forEach(out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("src/main/java/ru/job4j/io/consolechat/log.txt",
                "src/main/java/ru/job4j/io/consolechat/botanswers.txt");
        cc.run();
    }
}