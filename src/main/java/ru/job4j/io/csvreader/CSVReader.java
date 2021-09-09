package ru.job4j.io.csvreader;

import ru.job4j.io.ArgsName;

import java.nio.file.Path;
import java.util.*;


public class CSVReader {

    private final Output output;
    private final String delimiter;
    private final String[] filter;
    private final Path path;
    static String out;


    public CSVReader(ArgsName argsName, Output output) {
        this.output = output;
        path = Path.of(argsName.get("path"));
        out = argsName.get("out");
        delimiter = argsName.get("delimiter");
        filter = argsName.get("filter").split(",");

        List<String> lines = readFile();
        List<Integer> listOfColumnsForOut = searchColumnsForOut(lines);
        outOut(lines, listOfColumnsForOut);
    }

    private void outOut(List<String> lines, List<Integer> listOfColumnsForOut) {
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
        for (String s : filter) {
            for (int j = 0; j < column.length; j++) {
                if (s.equals(column[j])) {
                    listOfColumns.add(j);
                }
            }
        }
        return listOfColumns;
    }

    public static void main(String[] args) {
        if (args.length != 4) {
            throw new IllegalArgumentException(
                    "Wrong arguments. Usage java -jar target/csvReader.jar -path=FILE_NAME -delimiter=\"DELIMITER\"  -out=FILE_NAME_or_\"stdou\" -filter=COLUMN_NAME."
            );
        }
        ArgsName arguments = ArgsName.of(args);
        Output output = arguments.get("out").equals("stdout") ? new OutputConsole() : new OutputFile();
        new CSVReader(arguments, output);
    }
}
