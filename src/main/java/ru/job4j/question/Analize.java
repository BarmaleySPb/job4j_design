package ru.job4j.question;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {
        Info info = new Info(0, 0, 0);

        Map<Integer, User> prev = previous.stream()
                .collect(Collectors.toMap(User::getId, Function.identity()));

        Map<Integer, User> curr = current.stream()
                .collect(Collectors.toMap(User::getId, Function.identity()));

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
