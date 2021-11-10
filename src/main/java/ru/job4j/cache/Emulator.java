package ru.job4j.cache;

import java.nio.file.Path;
import java.util.ArrayList;


public class Emulator {

    public Emulator() {

    }

    public void init(Input input, DirFileCache dirFileCache, ArrayList<Action> actions) {
        boolean run = true;
        while (run) {
            menu(actions);
            int select = input.askInt("select: ");
            if (select < 0 || select >= actions.size()) {
                System.out.println("Wrong input, you can select: 0 .. " + (actions.size() - 1));
                continue;
            }
            Action action = actions.get(select);
            run = action.execute(dirFileCache);
        }
    }

    private void menu(ArrayList<Action> actions) {
        System.out.println("Menu.");
        for (int index = 0; index < actions.size(); index++) {
            System.out.println(index + ". " + actions.get(index).name());
        }
    }

    public static void main(String[] args) {

        Input input = new ValidateInput(new ConsoleInput());
        Path dirForCache = input.askFiles("Enter path for caching: ");
        DirFileCache cache = new DirFileCache(dirForCache.toString());

        ArrayList<Action> actions = new ArrayList<>();
        actions.add(new AddFileToCache());
        actions.add(new ReadFileFromCache());
        actions.add(new Exit());

        Emulator emulator = new Emulator();
        emulator.init(input, cache, actions);
    }
}
