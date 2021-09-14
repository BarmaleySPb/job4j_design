package ru.job4j.search;

import ru.job4j.io.ArgsName;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;


public class Search {

    private final String resultFile;
    private SearchByType searchByType;

    private Search(ArgsName args) throws IOException {
        Path path = Path.of(args.get("d"));
        String file = args.get("n");
        resultFile = args.get("o");

        setSearchByType(args.get("t"));
        Predicate<Path> predicate = searchByType.search(file);
        List<Path> listToLog = ru.job4j.io.Search.search(path, predicate);
        saveToFile(listToLog);
    }

    private void setSearchByType(String type) {
        Map<String, SearchByType> typeOfSearch = Map.of(
                "mask", new SearchByMask(),
                "name", new SearchByName(),
                "regex", new SearchByRegex()
        );
        this.searchByType = typeOfSearch.get(type);
    }

    private void saveToFile(List<Path> list) {
        try (PrintWriter out = new PrintWriter(new FileWriter(resultFile, StandardCharsets.UTF_8, false))) {
            list.forEach(out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        if (args.length != 4) {
            throw new IllegalArgumentException(
                    "Wrong arguments. Usage java -jar -d=DIRECTORY -n=FILE_NAME,MASK,REGEX -t=TYPE_SEARCH("
                            + "mask=SEARCH_BY_MASK,name=SEARCH_BY_FILE_NAME,regex=SEARCH_BY_REGEX) -o=RESULT_FILE_NAME"
            );
        }
        ArgsName arguments = ArgsName.of(args);
        new Search(arguments);
    }
}
