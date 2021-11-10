package ru.job4j.cache;

import java.util.Scanner;

public class ReadFileFromCache implements Action {

    @Override
    public String name() {
        return "Read file from cache";
    }

    public boolean execute(DirFileCache dirFileCache) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter file name: ");
        String data = scanner.nextLine();
        System.out.println(dirFileCache.get(data));
        return true;
    }
}
