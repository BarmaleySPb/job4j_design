package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    public static void packFiles(List<File> sources, File target) {
            try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
                for (File source : sources) {
                    zip.putNextEntry(new ZipEntry(source.getPath()));
                    try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                        zip.write(out.readAllBytes());
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
    }

    public static void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        ArgsName a = ArgsName.of(args);
        if (a.get("d") == null || a.get("e") == null || a.get("o") == null) {
            throw new IllegalArgumentException("Miss one of arguments, usage -d, -e and -o");
        }
        Path sourceDir = Path.of(a.get("d"));
        String outPut = a.get("o");
        if (!sourceDir.toFile().exists()) {
            throw new IllegalArgumentException("Directory is not exist");
        }
        Predicate<Path> predicate = p -> !p.toFile().getName().endsWith(a.get("e"));
        List<Path> listOfFiles = Search.search(sourceDir, predicate);
        List<File> listToZip = listOfFiles.stream().map(Path::toFile).toList();
        packFiles(listToZip, new File(outPut));
    }
}