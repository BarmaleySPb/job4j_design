package ru.job4j.cache;

public class Exit implements Action {

    @Override
    public String name() {
        return "Exit";
    }

    @Override
    public boolean execute(DirFileCache dirFileCache) {
        System.out.println("Bye-bye!");
        return false;
    }
}
