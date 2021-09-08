package ru.job4j.io.csvreader;

import ru.job4j.io.ArgsName;
import java.nio.file.Path;
import java.util.*;


public class CSVReader {

    private final String delimiter;
    private final String[] filter;
    private final Path path;
    static String out;

    public CSVReader(ArgsName argsName) {
        Output output;
        path = Path.of(argsName.get("path"));
        out = argsName.get("out");
        delimiter = argsName.get("delimiter");
        filter = argsName.get("filter").split(",");

        if (out.equals("stdout")) {
            output = new OutputConsole();
        } else {
            output = new OutputFile();
        }

        List<String> lines = readFile();
        List<Integer> listOfColumnsForOut = searchColumnsForOut(lines);
        outOut(lines, listOfColumnsForOut, output);
    }

    private void outOut(List<String> lines, List<Integer> listOfColumnsForOut, Output output) {
        for (int i = 1; i < lines.size(); i++) {
            String[] asd = lines.get(i).split(delimiter);
            output.print("\n");
            for (int j = 0; j < asd.length; j++) {
                if (listOfColumnsForOut.contains(j)) {
                    output.print(asd[j] + " ");
                }
            }
        }
    }

    private List<String> readFile() {
        List<String> lines = new LinkedList<>();
        try (Scanner scanner = new Scanner(path)) {
            while (scanner.hasNextLine()) {
                lines.add(scanner.nextLine());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lines;
    }

    private List<Integer> searchColumnsForOut(List<String> lines) {
        List<Integer> listOfColumns = new ArrayList<>();
        String[] column = lines.get(0).split(delimiter);
        for (int i = 0; i < filter.length; i++) {
            for (String s : column) {
                if (filter[i].equals(s)) {
                    listOfColumns.add(i);
                }
            }
        }
        return listOfColumns;
    }

    public static void main(String[] args) {
        new CSVReader(ArgsName.of(args));
    }
}
