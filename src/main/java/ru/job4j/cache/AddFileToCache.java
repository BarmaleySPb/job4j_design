package ru.job4j.cache;

import java.util.Scanner;

public class AddFileToCache implements Action {

    @Override
    public String name() {
        return "Add file to cache";
    }

    public boolean execute(DirFileCache dirFileCache) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter file name: ");
        String data = scanner.nextLine();
        dirFileCache.put(data, dirFileCache.load(data));
        return true;
    }
}
