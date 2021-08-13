package ru.job4j.question;

import java.util.*;
import java.util.stream.Collectors;

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {
        Info info = new Info(0, 0, 0);

        Map<Integer, List<User>> prev = new HashMap<>(
                previous.stream()
                        .collect(Collectors.groupingBy(User::getId))
        );
        Map<Integer, List<User>> curr = new HashMap<>(
                current.stream()
                        .collect(Collectors.groupingBy(User::getId))
        );

        int changed = 0;
        int twin = 0;
        for (Integer keyCurr : curr.keySet()) {
            if (prev.containsKey(keyCurr)) {
                var i = prev.get(keyCurr).equals(curr.get(keyCurr)) ? twin++ : changed++;
            }
        }

        info.setChanged(changed);
        info.setAdded(curr.size() - twin - changed);
        info.setDeleted(prev.size() - twin - changed);
        return info;
    }
}
